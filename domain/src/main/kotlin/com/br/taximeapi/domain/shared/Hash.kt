package com.br.taximeapi.domain.shared

import java.security.MessageDigest

@OptIn(ExperimentalStdlibApi::class)
fun String.hash(): String {
    val md = MessageDigest.getInstance("MD5")
    return md.digest(this.toByteArray()).toHexString()
}
