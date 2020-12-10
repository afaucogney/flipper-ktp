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
    private var nodeId = 0

    ///////////////////////////////////////////////////////////////////////////
    // SPECIALIZATION
    ///////////////////////////////////////////////////////////////////////////

    override fun getId(): String {
        return "KtpFlipper"
    }

    override fun onConnect(connection: FlipperConnection?) {
        this.connection = connection
        nodeId = 0
        Toothpick::class
            .staticProperties
            .find { it.name == "MAP_KEY_TO_SCOPE" }
            ?.let { prop ->
                prop.isAccessible = true
                val mapKeyToScope = prop.get() as ConcurrentHashMap<*, Scope>
                mapKeyToScope
                    .filter { it.value.isRootScope }
                    .toList()
                    .let { it[0] }
                    .let { (_, scope) ->
                        val flipperObject = getFlipperObjectFromScope(scope, getNextId())
                        Log.v("KtpFlipperPlugin", flipperObject.toJsonString())
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


    private fun getNextId(): Int {
        return nodeId.also { nodeId++ }
    }

    private fun getFlipperObjectFromScope(scope: Scope, id: Int): FlipperObject {
        return FlipperObject.Builder()
            .put("key", id)
            .put("title", scope.name.toString())
            .put("icon", getAndTreeIcon(scope))
            .apply {
                if (scope.childrenScopes.isNotEmpty()) {
                    put("children", scope.childrenScopes.let {
                        it.toList().mapIndexed { index, (obj, scope) ->
                            getFlipperObjectFromScope(
                                scope,
                                getNextId()
                            )
                        }
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
                    put(
                        "namedScoped",
                        getProviders(scope.mapClassesToNamedScopedProviders.keys.toList())
                    )
                }
                if (scope.mapClassesToUnNamedScopedProviders.isNotEmpty()) {
                    put("unNamedScopedCount", scope.mapClassesToUnNamedScopedProviders.count())
                    put(
                        "unNamedScoped",
                        getProviders(scope.mapClassesToUnNamedScopedProviders.keys.toList())
                    )
                }
                if (scope.mapClassesToUnNamedUnScopedProviders.isNotEmpty()) {
                    put("unNamedUnScopedCount", scope.mapClassesToUnNamedUnScopedProviders.count())
                    put(
                        "unNamedUnScoped",
                        getProviders(scope.mapClassesToUnNamedUnScopedProviders.keys.toList(), true)
                    )
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
                            .let { className ->
                                if (filter) {
                                    className
                                        .split(".", ignoreCase = true, limit = 0)
                                        .last()
                                } else {
                                    className
                                }
                            }
                    )
                }
            }
            .build()
    }

    private fun getAndTreeIcon(scope: Scope): String {
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
            return (this as ScopeNode).getPrivateProperty<ScopeNode, ConcurrentHashMap<Any, Scope>>(
                "childrenScopes"
            )
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
            return (this as ScopeImpl).getPrivateProperty<ScopeImpl, IdentityHashMap<Class<*>, Map<String, InternalScopedProvider<*>>>>(
                "mapClassesToNamedScopedProviders"
            )
                ?: IdentityHashMap()
        }

    private inline val Scope.mapClassesToUnNamedScopedProviders: IdentityHashMap<Class<*>, InternalScopedProvider<*>>
        get() {
            return (this as ScopeImpl).getPrivateProperty<ScopeImpl, IdentityHashMap<Class<*>, InternalScopedProvider<*>>>(
                "mapClassesToUnNamedScopedProviders"
            )
                ?: IdentityHashMap()
        }

    ///////////////////////////////////////////////////////////////////////////
    // REFLEXION HELPER
    ///////////////////////////////////////////////////////////////////////////

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
