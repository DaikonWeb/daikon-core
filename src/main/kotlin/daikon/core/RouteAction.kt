package daikon.core

interface RouteAction {
    fun handle(request: Request, response: Response, context: Context)
}
