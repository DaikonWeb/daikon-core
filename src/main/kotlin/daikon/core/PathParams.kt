package daikon.core

class PathParams(private val path: String) {
    private val prefix = ":"

    fun path(): String {
        return path.split("/").joinToString(separator = "/") { if (it.startsWith(prefix)) "[^/]+" else it }
    }

    fun valueOf(ulrPath: String): Map<String, String> {
        val parts = ulrPath.split("/")
        val placeholders = path.split("/").mapIndexed { index, part -> index to part }.toMap()
        return placeholders
                .filter { entry -> entry.value.startsWith(prefix) }
                .map { entry -> entry.value to parts[entry.key] }.toMap()
    }
}
