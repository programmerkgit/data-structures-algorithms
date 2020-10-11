package tree.alds1_11_B

fun main(arg: Array<String>) {
    val count = readLine()!!.toInt()
    val visitations = MutableList(count) { 0 }
    val times = MutableList(count) { 0 }
    val finished = MutableList(count) { 0 }
    var time = 0;
    val matrix = MutableList(count) { MutableList(count) { 0 } }
    (0 until count).forEach { i ->
        val line = readLine()!!.split(" ").map { it.toInt() }
        val vertices = line.slice(2 until line.size)
        vertices.forEach {
            matrix[i][it - 1] = 1
        }
    }

    fun dfsVisit(i: Int) {
        if (visitations[i] > 0) return
        visitations[i] = 1
        times[i] = ++time
        (0 until count).forEach { v ->
            if (matrix[i][v] == 1) dfsVisit(v)
        }
        visitations[i] = 2
        finished[i] = ++time
    }
    (0 until count).forEach {
        dfsVisit(it)
    }
    (0 until count).forEach {
        println("${it + 1} ${times[it]} ${finished[it]}")
    }
}

