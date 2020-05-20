package daikon.core

import daikon.core.HttpStatus.MOVED_TEMPORARILY_302

interface Response {
    fun write(text: String)
    fun write(byteArray: ByteArray)
    fun status(code: Int)
    fun status(): Int
    fun type(contentType: String)
    fun type(): String
    fun header(name: String, value: String)
    fun body(): String
    fun redirect(path: String, status: Int = MOVED_TEMPORARILY_302)
}