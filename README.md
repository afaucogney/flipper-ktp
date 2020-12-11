WIP

[![Download](https://jitpack.io/v/afaucogney/flipper-ktp.svg)](https://jitpack.io/#afaucogney/flipper-ktp)

Android Ktp (Kotlin [Toothpick](https://github.com/stephanenicolas/toothpick)) driver for [Flipper](https://github.com/facebook/flipper).

Download
--------

- Configure [Flipper](https://fbflipper.com/docs/getting-started.html)
- Top level gradle:

```kotlin
allprojects {
    repositories {
        ...
    	maven { url 'https://jitpack.io' }
		}
	}

}
```
- Dependency:

Toothpick >= 3.1.0:

```kotlin
implementation 'com.github.afaucogney:flipper-ktp:master-SNAPSHOT'
```

- Instantiate and add plugin to the FlipperClient. 

```kotlin
val client: FlipperClient = AndroidFlipperClient.getInstance(this)
with(client) {
    addPlugin(InspectorFlipperPlugin(this@BackpackApplication, DescriptorMapping.withDefaults()))
    addPlugin(KtpFlipperPlugin())
    start()
}
```

Usage
-----

Open Flipper app and enable Ktp plugin

<img src="docs/images/screenshot-0.1.png" />

Features
--------

- Displaying runtime scope tree
- Displaying scope names
- Displaying bindings by scope

Currently the plugin doesn't refresh in case of KTP scope tree change. You have to update it manually going to another plugin and coming back. 

Credits
-------

- [Toothpick](https://github.com/stephanenicolas/toothpick) - As target for this plugin but also for the repo structure.
- [LeakCanary](https://github.com/square/leakcanary) - For its great documentation structure and process, that I almost copy.
- [Flipper](https://github.com/facebook/flipper) - For supporting mobile devs by offering a great mobile app analysis tool.
- [Flipper-Realm-Android](https://github.com/kamgurgul/Flipper-Realm) - For the idea to build a simple plugin.

Background
----------

This seems to maybe an overkill repo for just a the [KtpFlipperPlugin](flipper-ktp/src/main/java/fr/afaucogney/mobile/flipper/KtpFlipperPlugin.kt) class, but it has been for me the chance to build my first android library with a state-of-the-art documentation. 

Blagounette
-----------

<img src="docs/images/Dolphin-Automatic-Toothpick-Holder.gif"  height="512" class="center"/>

License
-------
    Copyright 2020 Anthony Faucogney

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
