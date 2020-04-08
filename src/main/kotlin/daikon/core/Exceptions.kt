package daikon.core

class Exceptions {
    private var defaultAction = DefaultExceptionAction()
    private val exceptions = mutableListOf<ExceptionRoute>()

    fun bestFor(t: Throwable): ExceptionAction {
        return exceptions.firstOrNull { it.matchesExactly(t) }?.action
                ?: exceptions.firstOrNull { it.matches(t) }?.action
                ?: defaultAction
    }

    fun add(route: ExceptionRoute): Exceptions {
        exceptions.add(route)
        return this
    }
}
