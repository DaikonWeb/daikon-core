package daikon.core

import daikon.core.Method.ANY

class RoutingHandler(
        private val befores: Routing,
        private val routes: Routing,
        private val afters: Routing,
        private val context: Context,
        private val exceptions: List<ExceptionRoute>
) {
    fun execute(request: Request, response: Response) {
        try {
            befores
                .allFor(request.method(), request.path())
                .forEach { invoke(it, request, response) }

            routes
                .default(Route(ANY, "ignore", DefaultRouteAction()))
                .bestFor(request.method(), request.path())
                .also { invoke(it, request, response) }

            afters
                .allFor(request.method(), request.path())
                .forEach { invoke(it, request, response) }
        } catch(t: HaltException) {
        } catch(t: Throwable) {
            bestExceptionFor(t).handle(request, response, context)
        }
    }

    private fun bestExceptionFor(t: Throwable): RouteAction {
        return exceptions.firstOrNull { it.matchesExactly(t) }?.action
            ?: exceptions.firstOrNull { it.matches(t) }?.action
            ?: DefaultExceptionAction(t)
    }

    private fun invoke(route: Route, req: Request, res: Response) {
        route.action.handle(req.withPathParams(route.path), res, context)
    }
}