package daikon.core

interface Request {
    fun param(name: String): String
    fun hasParam(name: String): Boolean
    fun header(name: String): String
    fun hasHeader(name: String): Boolean
    fun body(): String
    fun multipart(name: String): Part
    fun url(): String
    fun path(): String
    fun <T> attribute(key: String, value: T)
    fun <T> attribute(key: String) : T
    fun hasAttribute(key: String) : Boolean
    fun method(): Method
    fun withPathParams(value: String): Request
}

