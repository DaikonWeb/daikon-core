package daikon.core

class TestRequest(
        private val method: Method = Method.GET,
        private val path: String = "/"
): Request {
    override fun param(name: String): String {
        TODO("Not yet implemented")
    }

    override fun hasParam(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun header(name: String): String {
        TODO("Not yet implemented")
    }

    override fun hasHeader(name: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun body(): String {
        TODO("Not yet implemented")
    }

    override fun url(): String {
        TODO("Not yet implemented")
    }

    override fun path(): String {
        return path
    }

    override fun <T> attribute(key: String, value: T) {
        TODO("Not yet implemented")
    }

    override fun <T> attribute(key: String): T {
        TODO("Not yet implemented")
    }

    override fun hasAttribute(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun method(): Method {
        return method
    }

    override fun withPathParams(value: String): Request {
        return this
    }
}