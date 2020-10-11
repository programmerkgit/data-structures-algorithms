package tree.alds1_12_A

class Vector(
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
        val edges = readLine()!!.split(" ").slice(1..n).map { it.toInt() }
        edges.forEachIndexed { v, w ->
            if (w != -1) {
                vectorsMatrix[i].add(Vector(m = v, weight = w))
                vectorsMatrix[v].add(Vector(m = i, weight = w))
            }
        }
    }
    val t = mutableListOf<Int>()
    val visited = MutableList(n) { false }
    var sum = 0
    fun prim() {
        t.add(0)
        visited[0] = true
        while (t.size != n) {
            val maxEdge = t.map { r ->
                val vectors = vectorsMatrix[r]
                vectors.filter { !visited[it.m] }.minBy { it.weight }
            }.minBy { v: Vector? ->
                v?.weight ?: 3000000
            }!!
            t.add(maxEdge.m)
            visited[maxEdge.m] =true
            sum += maxEdge.weight
        }
    }
    prim()
    println(sum)
}

