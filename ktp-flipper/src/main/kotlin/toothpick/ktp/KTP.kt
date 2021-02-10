/*
 * Copyright 2019 Stephane Nicolas
 * Copyright 2019 Daniel Molinero Reguera
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package toothpick.ktp

import toothpick.*
import toothpick.configuration.Configuration
import toothpick.debug.ToothpickOnScopeTreeChangeListener
import toothpick.ktp.delegate.DelegateNotifier

/**
 * Main Toothpick API entry point for Kotlin.
 */
object KTP {

    val delegateNotifier = DelegateNotifier()

    var ktpFlipperPluginListener: (() -> Unit)? = null
        set(value) {
            field = value
            Toothpick.setOnScopeTreeChangeListener(object : ToothpickOnScopeTreeChangeListener {
                override fun onScopeChanged() {
                    refreshFlipperPlugin()
                }
            })
        }

    init {
        setToothpickInjector(object : InjectorImpl() {
            override fun <T : Any> inject(obj: T, scope: Scope) {
                if (delegateNotifier.hasDelegates(obj)) {
                    delegateNotifier.notifyDelegates(obj, scope)
                }
                super.inject(obj, scope)
            }
        })
    }

    fun openRootScope(): Scope =
        Toothpick.openRootScope().also { refreshFlipperPlugin() }

    fun openRootScope(scopeConfig: (Scope) -> Unit): Scope =
        Toothpick.openRootScope(scopeConfig).also { refreshFlipperPlugin() }

    fun isRootScopeOpen(): Boolean =
        Toothpick.isRootScopeOpen().also { refreshFlipperPlugin() }

    fun openScope(name: Any): Scope =
        Toothpick.openScope(name).also { refreshFlipperPlugin() }

    fun openScope(name: Any, scopeConfig: (Scope) -> Unit): Scope =
        Toothpick.openScope(name, scopeConfig).also { refreshFlipperPlugin() }

    fun openScopes(vararg names: Any): Scope =
        Toothpick.openScopes(*names).also { refreshFlipperPlugin() }

    fun closeScope(name: Any) =
        Toothpick.closeScope(name).also { refreshFlipperPlugin() }

    fun isScopeOpen(name: Any) = Toothpick.isScopeOpen(name)

    fun setConfiguration(configuration: Configuration) = Toothpick.setConfiguration(configuration)

    fun reset() = Toothpick.reset().also { refreshFlipperPlugin() }

    fun reset(scope: Scope) = Toothpick.reset(scope).also { refreshFlipperPlugin() }

    fun release(scope: Scope) = Toothpick.release(scope).also { refreshFlipperPlugin() }

    private fun refreshFlipperPlugin() = ktpFlipperPluginListener?.invoke()
}
