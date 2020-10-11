package tree.alds1_11_D


fun main(arg: Array<String>) {
    val (n, m) = readLine()!!.split(" ").map { it.toInt() }
    val relations = MutableList(n) { mutableListOf<Int>() }
    var cid = 0;
    val belongings = MutableList(n) { 0 }
    (0 until m).forEach {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        relations[a].add(b)
        relations[b].add(a)
    }
    val questionCount = readLine()!!.toInt()
    fun bfs(i: Int) {
        if (belongings[i] > 0) {
            return;
        }
        belongings[i] = ++cid
        val queue = mutableListOf(i)
        while (queue.isNotEmpty()) {
            val target = queue.removeAt(0)
            val friends = relations[target]
            friends.forEach { f ->
                if (belongings[f] > 0) return@forEach
                belongings[f] = cid
                queue.add(f)
            }
        }
    }
    (0 until n).forEach {
        bfs(it)
    }
    (0 until questionCount).forEach {
        val (a, b) = readLine()!!.split(" ").map { it.toInt() }
        if (belongings[a] == belongings[b]) {
            println("yes")
        } else {
            println("no")
        }
    }
}

