package br.dev.dig.storage.core.common.encrypter

import br.dev.dig.storage.core.operation.encrypter.Salt

class StorageSaltNone : Salt {

    override fun salt(password: String): String {
        return password
    }

}