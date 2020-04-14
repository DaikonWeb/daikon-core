package daikon.core

import daikon.core.HttpStatus.OK_200
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File


class StaticFilesTest {
    @Test
    fun `serve a static css file`() {
        val response = TestResponse()
        val server = TestServer()
        server.assets("/foo/*").start()

        server.execute(TestRequest(Method.GET, "/foo/style.css"), response)

        assertThat(response.status).isEqualTo(OK_200)
        assertThat(response.type).isEqualTo("text/css")
        assertThat(response.body).isEqualTo("body {}")
    }

    @Test
    fun `serve a static png body`() {
        val response = TestResponse()
        val server = TestServer()
        server.assets("/foo/*").start()

        server.execute(TestRequest(Method.GET, "/foo/daikon.png"), response)

        assertThat(response.status).isEqualTo(OK_200)
        assertThat(response.type).isEqualTo("image/png")
        assertThat(response.outputStream).isEqualTo(File("src/test/resources/assets/foo/daikon.png").readBytes())
    }

    @Test
    fun `serve a static css file inside a path`() {
        val response = TestResponse()
        val server = TestServer()
        server.path("/foo") {
            assets("/bar/*").start()
        }
        server.execute(TestRequest(Method.GET, "/foo/bar/style.css"), response)

        assertThat(response.status).isEqualTo(OK_200)
        assertThat(response.type).isEqualTo("text/css")
        assertThat(response.body).isEqualTo("body {}")
    }
}

