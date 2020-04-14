package daikon.core

class TestResponse(
        var body: String = "",
        var status: Int = -1,
        var type: String = "",
        var outputStream: ByteArray = ByteArray(0)
): Response {
    override fun write(text: String) {
        body += outputStream
    }

    override fun write(byteArray: ByteArray) {
        body += byteArray.toString(Charsets.UTF_8)
        outputStream = byteArray
    }

    override fun status(code: Int) {
        status = code
    }

    override fun type(contentType: String) {
        type = contentType
    }

    override fun header(name: String, value: String) {
        TODO("Not yet implemented")
    }

    override fun body(): String {
        return body
    }

    override fun redirect(path: String, status: Int) {
        TODO("Not yet implemented")
    }

}