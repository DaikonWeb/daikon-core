package daikon.core

interface Context {
    fun addAttribute(key: String, value: Any)
    fun <T> getAttribute(key: String) : T
    fun port(): Int
}