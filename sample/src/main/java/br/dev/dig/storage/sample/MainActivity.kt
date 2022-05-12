package br.dev.dig.storage.sample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.dev.dig.storage.core.Storage
import br.dev.dig.storage.core.common.encrypter.StorageSaltAppend
import br.dev.dig.storage.simple.android.StorageSimpleAndroidBuilder

class MainActivity : AppCompatActivity() {
    private val storage: Storage by lazy { createStorage() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storage.put("batata", "Formiga")
        Log.d("Foi?", storage.get("batata"))
    }

    private fun createStorage(): Storage {
        val builder = StorageSimpleAndroidBuilder()
        builder.masterKey("123456")
        builder.preferences(getSharedPreferences("local", Context.MODE_PRIVATE))
        builder.builder.salter(StorageSaltAppend("android"))
        return builder.build()
    }
}