package tree.alds1_9_C


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

fun insert(heap: MutableList<Int>, key: Int) {
    heap.add(key)
    var i = heap.size - 1
    while (1 < i && heap[i / 2] < heap[i]) {
        val tmp = heap[i]
        heap[i] = heap[i / 2]
        heap[i / 2] = tmp
        i /= 2
    }
}

fun extract(heap: MutableList<Int>): Int {
    val top = heap[1]
    if (heap.size > 2) {
        heap[1] = heap.removeAt(heap.size - 1)
        maxify(heap)
    } else {
        heap.removeAt(heap.size - 1)
    }
    return top
}

fun main(args: Array<String>) {
    val heap = mutableListOf<Int>(0)
    var end = false
    while (!end) {
        val line = readLine()!!.split(" ")
        when (line[0]) {
            "end" -> {
                end = true
            }
            "insert" -> {
                val key = line[1].toInt()
                insert(heap, key)
            }
            "extract" -> {
                println(extract(heap))
            }
        }
    }
}