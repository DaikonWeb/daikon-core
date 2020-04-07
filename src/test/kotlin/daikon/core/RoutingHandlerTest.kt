package daikon.core

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import daikon.core.Method.ANY
import daikon.core.Method.GET
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.contains
import java.lang.RuntimeException


class RoutingHandlerTest {
    private val befores = Routing()
    private val routes = Routing()
    private val exceptions = mutableListOf<ExceptionRoute>()
    private val afters = Routing()
    private val request: Request = mock()
    private val response: Response = mock()
    private val routingHandler = RoutingHandler(befores, routes,  afters, mock(), mock())

    @BeforeEach
    fun setUp() {
        whenever(request.withPathParams(any())).thenReturn(request)
    }

    @Test
    fun `do action before calling route`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")
        befores.add(Route(ANY, "/", DummyRouteAction { _, res -> res.write("Hello") }))
        routes.add(Route(GET, "/", DummyRouteAction { _, res -> res.write(" world") }))

        routingHandler.execute(request, response)

        verify(response).write("Hello")
        verify(response).write(" world")
    }

    @Test
    fun `do action after route was called`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")
        routes.add(Route(GET, "/", DummyRouteAction { _, res -> res.write("Hello") }))
        afters.add(Route(ANY, "/", DummyRouteAction { _, res -> res.write(" world") }))

        routingHandler.execute(request, response)

        verify(response).write("Hello")
        verify(response).write(" world")
    }

    @Test
    fun `do multiple action before calling route`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/second/third")
        befores.add(Route(ANY, "/*", DummyRouteAction { _, res -> res.write("Bye") }))
        befores.add(Route(ANY, "/second/*", DummyRouteAction { _, res -> res.write("dear") }))
        befores.add(Route(ANY, "/second/third", DummyRouteAction { _, res -> res.write("friend") }))
        routes.add(Route(GET, "/second/third", DummyRouteAction { _, res -> res.write("Bob") }))

        routingHandler.execute(request, response)

        verify(response).write("Bye")
        verify(response).write("dear")
        verify(response).write("friend")
        verify(response).write("Bob")
    }

    @Test
    fun `rendered on root`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")
        RoutingHandler(Routing(), routes, Routing(), mock(), mock()).execute(request, response)

        verify(response).status(HttpStatus.OK_200)
    }

    @Test
    fun `override welcome page`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")

        routes.add(Route(GET, "/", DummyRouteAction { _, res -> res.status(HttpStatus.INTERNAL_SERVER_ERROR_500) }))
        RoutingHandler(Routing(), routes, Routing(), mock(), mock()).execute(request, response)

        verify(response).status(HttpStatus.INTERNAL_SERVER_ERROR_500)
    }

    @Test
    fun `handles exceptions by default`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")
        routes.add(Route(GET, "/", DummyRouteAction { _, res -> throw RuntimeException("Something") }))

        RoutingHandler(Routing(), routes, Routing(), mock(), exceptions).execute(request, response)

        verify(response).write(contains("<a href=\"https://daikonweb.github.io\">Powered by Daikon</a>"))
    }

    @Test
    fun `overrides default exception handler`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")
        routes.add(Route(GET, "/", DummyRouteAction { _, res -> throw RuntimeException("Something") }))

        exceptions.add(ExceptionRoute(Throwable::class.java, DummyRouteAction { _, res -> res.write("Ops, there was an error") } ))
        RoutingHandler(Routing(), routes, Routing(), mock(), exceptions).execute(request, response)

        verify(response).write("Ops, there was an error")
    }

    @Test
    fun `exact match for the exceptions added`() {
        whenever(request.method()).thenReturn(GET)
        whenever(request.path()).thenReturn("/")
        routes.add(Route(GET, "/", DummyRouteAction { _, res -> throw RuntimeException("Something") }))

        exceptions.add(ExceptionRoute(Throwable::class.java, DummyRouteAction { _, res -> res.write("Thrown Throwable") } ))
        exceptions.add(ExceptionRoute(RuntimeException::class.java, DummyRouteAction { _, res -> res.write("Thrown RuntimeException") } ))
        RoutingHandler(Routing(), routes, Routing(), mock(), exceptions).execute(request, response)

        verify(response).write("Thrown RuntimeException")
    }
}