package tree.alds1_11_A

fun main(arg: Array<String>) {
    val count = readLine()!!.toInt()
    (0 until count).forEach { _ ->
        val line = readLine()!!.split(" ").map { it.toInt() }
        val vertices = line.slice(2 until line.size)
        val result = MutableList(count) { 0 }
        vertices.forEach {
            result[it - 1] = 1
        }
        println(result.joinToString(" ") { "$it" })
    }
}