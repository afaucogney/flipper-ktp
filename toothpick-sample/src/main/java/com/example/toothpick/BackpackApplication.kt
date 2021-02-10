package com.example.toothpick

import android.app.Application
import com.example.toothpick.annotation.ApplicationScope
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.android.utils.FlipperUtils
import com.facebook.flipper.core.FlipperClient
import com.facebook.flipper.plugins.crashreporter.CrashReporterPlugin
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import fr.afaucogney.mobile.flipper.KtpFlipperPlugin
import toothpick.Scope
import toothpick.ktp.KTP
import toothpick.ktp.binding.bind
import toothpick.ktp.binding.module


/**
 * BackpackApplication demonstrate usage of Ktp with KtpFlipperPlugin
 */
class BackpackApplication : Application() {

    lateinit var scope: Scope

    private val ktpFlipperPlugin = KtpFlipperPlugin()

    override fun onCreate() {
        super.onCreate()

        /**
         * Initialisation of Flipper
         */
        if (BuildConfig.DEBUG) {
            SoLoader.init(this, false)
            if (FlipperUtils.shouldEnableFlipper(this)) {
                val client: FlipperClient = AndroidFlipperClient.getInstance(this)
                with(client) {
                    addPlugin(
                        InspectorFlipperPlugin(
                            this@BackpackApplication,
                            DescriptorMapping.withDefaults()
                        )
                    )
                    /**
                     * initialisation of KtpFlipperPlugin with its default constructor
                     */
                    addPlugin(ktpFlipperPlugin)
                    start()
                }
            }
            KTP.ktpFlipperPluginListener = {
                ktpFlipperPlugin.refresh()
            }
        }

        scope = KTP.openScope(ApplicationScope::class.java)
            .installModules(module {
                bind<Application>().toInstance { this@BackpackApplication }
            })

    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        scope.release()
    }

}
