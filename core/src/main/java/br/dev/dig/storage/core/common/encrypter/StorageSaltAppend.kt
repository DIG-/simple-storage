package br.dev.dig.storage.core.common.encrypter

import br.dev.dig.storage.core.operation.encrypter.Salt

class StorageSaltAppend(private val prefix: String) : Salt {

    override fun salt(password: String): String {
        return prefix + password
    }

}