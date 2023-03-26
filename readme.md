Simple Storage for Java and Android
==================================
[![AppVeyor](https://img.shields.io/appveyor/build/DIG-/simple-storage/main?logo=appveyor&logoColor=dddddd)](https://ci.appveyor.com/project/DIG-/simple-storage/branch/main)
[![AppVeyor tests](https://img.shields.io/appveyor/tests/DIG-/simple-storage/main?logo=appveyor&logoColor=dddddd)](https://ci.appveyor.com/project/DIG-/simple-storage/branch/main)
[![Maven Central](https://img.shields.io/maven-central/v/br.dev.dig.storage/core?label=maven)](https://central.sonatype.com/search?q=br.dev.dig.storage)
[![License](https://img.shields.io/static/v1?label=license&message=CC%20BY-ND%204.0&color=blue)](https://creativecommons.org/licenses/by-nd/4.0/)

![Windows - Supported](https://img.shields.io/badge/windows-supported-success?logo=windows&logoColor=dddddd)
![Linux - Supported](https://img.shields.io/badge/linux-supported-success?logo=linux&logoColor=dddddd)
![MacOS - Partial](https://img.shields.io/badge/macos-partial-orange?logo=apple&logoColor=dddddd)

- Like simple NoSQL database
- Supports customizable serialization (gson,moshi... your choice)
- Supports customizable encryption
- No external dependencies¹
- Requires simple NoSQL implementation²

 ¹ Core features does not require dependencies
 ² Simple Storage works like a layer above others type of storage

Reason
------
As an Android developer, **Hawk** is outdated and contains external outdated libraries.

How to use - Simple
===================
1. Include maven central as repository
2. Choose, at least, one logger printer (`br.dev.dig.logger.printer`) and import into your project.

Simple log for Android:
```groovy
dependencies {
    ⋮
    implementation "br.dev.dig.storage:simple-android:${lastest_version}"
    ⋮
}

```
3. Creating storage:
```kotlin
private fun createStorage(): Storage {
    val builder = StorageSimpleAndroidBuilder()
    // Hardcoded random string
    builder.masterKey("123456") 
    // Using SharedPreferences file `local`
    builder.preferences(getSharedPreferences("local", Context.MODE_PRIVATE))
    return builder.build()
}
```
4. Use `Storage` instance:
```kotlin
val storage = createStorage()

val name = storage.get("name", String::class.java)
// OR
val name = storage.get<String>("name")

val age = storage.get("name", Int::class.java)
// OR
val age = storage.get<Int>("name")
```

For java users, there is `StoragePrimitiveWrapper` to provide methods to mimic kotlin style.

Migration
=========
Every update in whatever operation of the Storage, old content will be unrecognizable by new configuration, then you need a migration.

Since key can be hashed (normal mode), there is no automatic way to migrate, unless you known every key used.

There is `StorageMigration` from `br.dev.dig.storage:migration` to migrate Storages to a new one, on demand. Just provide the new storage, alongside with olders. Also can be used to migrate your current storage library to this new storage library.

License
=======
[CC BY-ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/)
- You can use and re-dist freely.
- You can also modify, but only for yourself.
- You can use it as a part of your project, but without modifications in this project.
- You can expand this project creating implementations of each operation interface.