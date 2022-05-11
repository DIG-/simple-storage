package br.dev.dig.storage.core.common.encryption

import br.dev.dig.storage.core.operation.encryption.Salt

class StorageSaltAppend(private val prefix: String) : Salt {

    override fun salt(password: String): String {
        return prefix + password
    }

}