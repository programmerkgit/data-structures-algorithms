package tree.alds1_12_B


private class Vector(
    val m: Int,
    val weight: Int
) {
    override fun toString(): String {
        return "m = $m, weight = $weight"
    }
}

fun main(arg: Array<String>) {
    val n = readLine()!!.toInt()
    val vectorsMatrix = MutableList(n) { mutableListOf<Vector>() }
    (0 until n).forEach { i ->
        val edges = readLine()!!.split(" ").map { it.toInt() }
        val c = edges[1]
        (0 until c).forEach { ci ->
            vectorsMatrix[i].add(Vector(m = edges[ci * 2 + 2], weight = edges[ci * 2 + 2 + 1]))
        }
    }
    val weights = MutableList(n) { 10_000_000 }
    fun dikstra() {
        var r = 0
        val visited = MutableList(n) { false }
        val ts = mutableListOf(r)
        visited[r] = true
        weights[r] = 0
        while (ts.size != n) {
            val edges = vectorsMatrix[r]
            edges.forEach() { v: Vector ->
                if (!visited[v.m] && weights[r] + v.weight < weights[v.m]) {
                    weights[v.m] = weights[r] + v.weight
                }
            }
            var min: Int = 10_000_000
            weights.forEachIndexed { i, w ->
                if (visited[i]) {
                    return@forEachIndexed
                }
                if (min > w) {
                    min = w
                    r = i
                }
            }
            visited[r] = true
            ts.add(r)
        }
    }
    dikstra()
    weights.forEachIndexed { i, w ->
        println("$i $w")
    }
}

