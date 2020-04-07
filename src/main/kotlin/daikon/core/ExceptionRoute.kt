package daikon.core

class ExceptionRoute(val type: Class<out Throwable>, val action: RouteAction) {
    fun matchesExactly(t: Throwable) = type == t::class.java
    fun matches(t: Throwable) = type.isInstance(t)
}