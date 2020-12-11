[flipper-ktp](../../index.md) / [fr.afaucogney.mobile.flipper](../index.md) / [KtpFlipperPlugin](./index.md)

# KtpFlipperPlugin

`class KtpFlipperPlugin : FlipperPlugin`

A Flipper Plugin for Ktp.

KtpFlipperPlugin is a plugin for @see [Flipper]("https://fbflipper.com/") to analyse @see [Toothpick-di](https://github.com/stephanenicolas/toothpick) (aka KTP, @see) runtime app scope tree.

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `KtpFlipperPlugin()`<br>A Flipper Plugin for Ktp. |

### Functions

| Name | Summary |
|---|---|
| [getId](get-id.md) | `fun getId(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>Setup the unique id of the plugin |
| [onConnect](on-connect.md) | `fun onConnect(connection: FlipperConnection?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>onConnect is triggered every time the plugin is shown on Flipper It does keep the connection And parse Ktp scope tree to then push it to the Desktop Flipper Client |
| [onDisconnect](on-disconnect.md) | `fun onDisconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Release the connection |
| [runInBackground](run-in-background.md) | `fun runInBackground(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Plugin doesn't run in background |
