package daikon.core

import java.io.PrintWriter
import java.io.StringWriter

class DefaultExceptionAction(private val t: Throwable): RouteAction {
    override fun handle(request: Request, response: Response, context: Context) {
        response.status(HttpStatus.INTERNAL_SERVER_ERROR_500)
        response.type("text/html")
        response.write(html())
    }

    private fun html() = """
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<title>Error 500 ${t::class.java}: ${t.message}</title>
</head>
<body><h2>HTTP ERROR 500 ${t::class.java}: ${t.message}</h2>
<table>
<tr><th>URI:</th><td>/</td></tr>
<tr><th>STATUS:</th><td>500</td></tr>
<tr><th>MESSAGE:</th><td>${t::class.java}: ${t.message}</td></tr>
</table>
<h3>Caused by:</h3><pre>${t.printStackTraceToString()}</pre>
<hr><a href="https://daikonweb.github.io">Powered by Daikon</a><hr/>
</body>
</html>"""

    private fun Throwable.printStackTraceToString(): String {
        val stringWriter = StringWriter()
        this.printStackTrace(PrintWriter(stringWriter))
        return stringWriter.toString()
    }

}