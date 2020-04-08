package daikon.core

interface RouteAction {
    fun handle(request: Request, response: Response, context: Context)
}

interface ExceptionAction {
    fun handle(request: Request, response: Response, context: Context, t: Throwable)
}
