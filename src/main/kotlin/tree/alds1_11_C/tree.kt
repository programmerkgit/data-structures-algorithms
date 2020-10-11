package tree.alds1_11_C

class Node(
    var visited: Int = 0,
    var d: Int = -1
)


fun main(arg: Array<String>) {
    val count = readLine()!!.toInt()
    val nodes = MutableList(count) { Node() }
    val matrix = MutableList(count) { MutableList(count) { 0 } }
    (0 until count).forEach { i ->
        val line = readLine()!!.split(" ").map { it.toInt() }
        val vertices = line.slice(2 until line.size)
        vertices.forEach {
            matrix[i][it - 1] = 1
        }
    }
    fun bfsVisit(i: Int) {
        val queue = mutableListOf<Int>()
        queue.add(i)
        nodes[i].d = 0
        while (queue.isNotEmpty()) {
            val v = queue.removeAt(0)
            val current = nodes[v]
            matrix[v].forEachIndexed { index, result ->
                if (result == 0) return@forEachIndexed
                val next = nodes[index]
                if (next.visited == 0 && next.d == -1) {
                    next.visited = 1
                    next.d = current.d + 1
                    queue.add(index)
                }
            }
        }
    }
    bfsVisit(0)
    nodes.forEachIndexed { i, n ->
        println("${i + 1} ${n.d}")
    }
}

