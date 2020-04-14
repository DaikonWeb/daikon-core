package daikon.core

class TestServer: DaikonServer() {
    private lateinit var routingHandler: RoutingHandler

    fun execute(request: Request, response: Response) {
        routingHandler.execute(request, response)
    }

    override fun start(routingHandler: RoutingHandler): DaikonServer {
        this.routingHandler = routingHandler
        return this
    }

    override fun stop() {

    }
}