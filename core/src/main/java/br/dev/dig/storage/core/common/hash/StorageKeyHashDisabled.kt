package br.dev.dig.storage.core.common.hash

import br.dev.dig.storage.core.operation.KeyHash

class StorageKeyHashDisabled : KeyHash {

    override fun hash(key: String): String {
        return key
    }

}