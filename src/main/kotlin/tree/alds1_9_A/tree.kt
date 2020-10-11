package tree.alds1_9_A


fun maxify(heap: MutableList<Int>, i: Int = 1) {
    val a = heap[i]
    val leftI = i * 2
    val rightI = i * 2 + 1
    val left = heap.getOrElse(leftI) { -2_000_000_001 }
    val right = heap.getOrElse(rightI) { -2_000_000_001 }
    if (a < left || a < right) {
        if (left < right) {
            heap[i] = right
            heap[rightI] = a
            maxify(heap, rightI)
        } else {
            heap[i] = left
            heap[leftI] = a
            maxify(heap, leftI)
        }
    }
}

fun buildMaxify(heap: MutableList<Int>): List<Int> {
    for (i in heap.size / 2 downTo 1) {
        maxify(heap, i)
    }
    return heap
}


fun main(args: Array<String>) {
    readLine()
    var heap = listOf(0) + readLine()!!.split(" ").map { it.toInt() }
    heap = buildMaxify(heap.toMutableList())
    heap.forEachIndexed { i, n ->
        if (i == 0) {
            return@forEachIndexed
        }
        print(" $n")
    }
    println()
}