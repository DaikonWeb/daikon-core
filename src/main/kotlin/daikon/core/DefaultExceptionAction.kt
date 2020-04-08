package daikon.core

import daikon.core.HttpStatus.INTERNAL_SERVER_ERROR_500
import java.io.PrintWriter
import java.io.StringWriter

class DefaultExceptionAction: ExceptionAction {
    override fun handle(request: Request, response: Response, context: Context, t: Throwable) {
        response.status(INTERNAL_SERVER_ERROR_500)
        response.type("text/html")
        response.write(html(t))
    }

    private fun html(t: Throwable) = """
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