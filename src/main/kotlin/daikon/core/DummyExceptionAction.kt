package daikon.core

class DummyExceptionAction(private val action: (Request, Response, Throwable) -> Unit) : ExceptionAction {
    override fun handle(request: Request, response: Response, context: Context, t: Throwable) {
        action.invoke(request, response, t)
    }
}

class ContextExceptionAction(private val action: (Request, Response, Context, Throwable) -> Unit) : ExceptionAction {
    override fun handle(request: Request, response: Response, context: Context, t: Throwable) {
        action.invoke(request, response, context, t)
    }
}