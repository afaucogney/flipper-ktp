package fr.afaucogney.mobile.flipper

import android.util.Log
import com.facebook.flipper.core.FlipperArray
import com.facebook.flipper.core.FlipperConnection
import com.facebook.flipper.core.FlipperObject
import com.facebook.flipper.core.FlipperPlugin
import toothpick.*
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.staticProperties
import kotlin.reflect.jvm.isAccessible


class KtpFlipperPlugin : FlipperPlugin {

    ///////////////////////////////////////////////////////////////////////////
    // DATA
    ///////////////////////////////////////////////////////////////////////////

    private var connection: FlipperConnection? = null

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getId(): String {
        return "KtpFlipper"
    }

    override fun onConnect(connection: FlipperConnection?) {
        this.connection = connection
        id = 0
        val f = Toothpick::class.staticProperties.find { it.name == "MAP_KEY_TO_SCOPE" }
        f?.let {
            it.isAccessible = true
            val w = it.get() as ConcurrentHashMap<Object, Scope>
            w.filter { it.value.isRootScope }.toList().let { it[0] }.let { (obj, scope) ->
                val flipperObject = getFlipperObjectFromScope(scope, getNextId())
                Log.v("@@", flipperObject.toJsonString())
                connection?.send("newData", flipperObject)
            }
        }
    }

    override fun onDisconnect() {
        connection = null
    }

    override fun runInBackground(): Boolean {
        return false
    }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

    var id = 0

    private fun getNextId(): Int {
        return id.also { id++ }
    }

    private fun getFlipperObjectFromScope(scope: Scope, id: Int): FlipperObject {
        return FlipperObject.Builder()
            .put("key", id)
            .put("title", scope.name.toString())
            .put("icon", getAndtTreeIcon(scope))
            .apply {
                if (scope.childrenScopes.isNotEmpty()) {
                    put("children", scope.childrenScopes.let {
                        it.toList().mapIndexed { index, (obj, scope) -> getFlipperObjectFromScope(scope, getNextId()) }
                            .toList()
                            .let {
                                val arr = FlipperArray.Builder()
                                it.forEach { arr.put(it) }
                                arr.build()
                            }
                    })
                }
                if (scope.mapClassesToNamedScopedProviders.isNotEmpty()) {
                    put("namedScopedCount", scope.mapClassesToNamedScopedProviders.count())
                    put("namedScoped", getProviders(scope.mapClassesToNamedScopedProviders.keys.toList()))
                }
                if (scope.mapClassesToUnNamedScopedProviders.isNotEmpty()) {
                    put("unNamedScopedCount", scope.mapClassesToUnNamedScopedProviders.count())
                    put("unNamedScoped", getProviders(scope.mapClassesToUnNamedScopedProviders.keys.toList()))
                }
                if (scope.mapClassesToUnNamedUnScopedProviders.isNotEmpty()) {
                    put("unNamedUnScopedCount", scope.mapClassesToUnNamedUnScopedProviders.count())
                    put("unNamedUnScoped", getProviders(scope.mapClassesToUnNamedUnScopedProviders.keys.toList(),true))
                }
            }
            .build()
    }

    private fun getProviders(map: List<Class<*>>, filter: Boolean = false): FlipperArray {
        return FlipperArray
            .Builder()
            .apply {
                map.forEach {
                    this.put(
                        (it as Class<*>)
                            .toString()
                            .let {
                                if (filter) {
                                    it.split(".", ignoreCase = true, limit = 0).last()
                                } else {
                                    it
                                }
                            }
                    )
                }
            }
            .build()
    }

    private fun getAndtTreeIcon(scope: Scope): String {
        return when {
            scope.name.toString().contains("class toothpick.Toothpick", true) -> "<HomeOutlined />"
            scope.name.toString().contains("viewmodel", true) -> "<AndroidOutlined />"
            scope.name.toString().contains("fragment", true) -> "<WindowsOutlined />"
            scope.name.toString().contains("activity", true) -> "<PartitionOutlined />"
            else -> "<MehOutlined />"
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // DOMAIN HELPER
    ///////////////////////////////////////////////////////////////////////////


//scope::class.staticProperties.filter { it.name == "mapClassesToUnNamedUnScopedProviders"}.let { it.first() }.get()

    private inline val Scope.isRootScope: Boolean
        get() {
            return this == this.rootScope
        }

    private inline val Object.className: String
        get() {
            return this.javaClass.name
        }

    // Parent

    private inline val Scope.parentName: String
        get() {
            return this.parentScope?.name.toString()
        }

    // Children

    private inline val Scope.childrenScopesCount: Int
        get() {
            return this.childrenScopes.count()
        }

    private inline val Scope.childrenScopes: ConcurrentHashMap<Any, Scope>
        get() {
            return (this as ScopeNode).getPrivateProperty<ScopeNode, ConcurrentHashMap<Any, Scope>>("childrenScopes")
                ?: ConcurrentHashMap()
        }

    // Providers

    private inline val Scope.mapClassesToUnNamedUnScopedProviders: IdentityHashMap<Class<*>, InternalScopedProvider<*>>
        get() {
            return (this as ScopeImpl).getStaticProperty("mapClassesToUnNamedUnScopedProviders")
                ?: IdentityHashMap()
        }

    private inline val Scope.mapClassesToNamedScopedProviders: IdentityHashMap<Class<*>, Map<String, InternalScopedProvider<*>>>
        get() {
            return (this as ScopeImpl).getPrivateProperty<ScopeImpl, IdentityHashMap<Class<*>, Map<String, InternalScopedProvider<*>>>>("mapClassesToNamedScopedProviders")
                ?: IdentityHashMap()
        }

    private inline val Scope.mapClassesToUnNamedScopedProviders: IdentityHashMap<Class<*>, InternalScopedProvider<*>>
        get() {
            return (this as ScopeImpl).getPrivateProperty<ScopeImpl, IdentityHashMap<Class<*>, InternalScopedProvider<*>>>("mapClassesToUnNamedScopedProviders")
                ?: IdentityHashMap()
        }

//            /*@VisibleForTesting */ static final IdentityHashMap<Class, InternalProvider>
//    mapClassesToUnNamedUnScopedProviders = new IdentityHashMap<>();
//
//    /*
//     * These 2 maps contain the internal bindings / providers specific to a scope.
//     */
//    /*@VisibleForTesting */ final IdentityHashMap<Class, Map<String, InternalScopedProvider>>
//    mapClassesToNamedScopedProviders = new IdentityHashMap<>();
//    /*@VisibleForTesting */ final IdentityHashMap<Class, InternalScopedProvider>
//    mapClassesToUnNamedScopedProviders = new IdentityHashMap<>();

//    private inline val ScopeNode.childrenScopes: ConcurrentHashMap<Any, Scope>
//        get() {
//            return this.getPrivateProperty<ScopeNode, ConcurrentHashMap<Any, Scope>>("childrenScopes")
//                    ?: ConcurrentHashMap()
//        }

//    private inline val Scope.unNamedProviderCount: Int
//        get() {
//            return (this as ScopeImpl).class.staticPropertiesy<Scope, List<JvmType.Object>>("unNamedProviders")?.size
//                    ?: 0
//        }

//    private inline val Scope.unNamedProviderCount: List<Any>
//        get() {
//            return (this as ScopeImpl).class.staticProperties
//        }


//    private inline val Scope.namedProviderCount: Int
//        get() {
//            return this.getPrivateProperty<Scope, List<JvmType.Object>>("namedProviders")?.size ?: 0
//        }

    ///////////////////////////////////////////////////////////////////////////
    // HELPER
    ///////////////////////////////////////////////////////////////////////////

//    inline fun <reified T> T.callPrivateFunc(name: String, vararg args: Any?): Any? =
//            T::class
//                    .declaredMemberFunctions
//                    .firstOrNull { it.name == name }
//                    ?.apply { isAccessible = true }
//                    ?.call(this, *args)

    private inline fun <reified T : Any, R> T.getPrivateProperty(name: String): R? =
        T::class
            .memberProperties
            .firstOrNull { it.name == name }
            ?.apply { isAccessible = true }
            ?.get(this) as? R

    private inline fun <reified T : Any, R> T.getStaticProperty(name: String): R? =
        T::class
            .staticProperties
            .firstOrNull { it.name == name }
            ?.apply { isAccessible = true }
            ?.get() as? R
}
