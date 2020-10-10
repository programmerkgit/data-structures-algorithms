package tree.alds1_7_B


class NodeB(
    var id: Int? = null,
    var left: Int = -1,
    var right: Int = -1,
    var parent: Int = -1,
    var depth: Int = 0,
    var height: Int = 0
) {
    val nodeType: String
        get() {
            return when {
                parent == -1 -> {
                    "root"
                }
                (left != -1 || right != -1) -> {
                    "internal node"
                }
                else -> {
                    "leaf"
                }
            }
        }
    val degree: Int
        get() {
            return (if (left == -1) 0 else 1) + (if (right == -1) 0 else 1)
        }
}


fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val nodes: MutableList<NodeB> = MutableList(n) { NodeB() }
    for (i in 0 until n) {
        val line = readLine()!!.split(" ").map { it.toInt() }
        val id = line[0]
        val left = line[1]
        val right = line[2]
        val node = nodes[id]
        node.left = left
        node.right = right
        node.id = id

        if (left != -1) {
            val leftChild = nodes[left]
            leftChild.parent = id
        }

        if (right != -1) {
            val rightChild = nodes[right]
            rightChild.parent = id
        }

    }

    val r = nodes.find { it.parent == -1 }!!

    fun setDepth(node: NodeB, depth: Int = 0) {
        node.depth = depth
        if (node.left != -1) {
            val leftNode = nodes[node.left]
            setDepth(leftNode, depth + 1)
        }
        if (node.right != -1) {
            val rightNode = nodes[node.right]
            setDepth(rightNode, depth + 1)
        }
    }
    setDepth(r)

    fun setHeight(node: NodeB): Int {
        var h1 = 0
        var h2 = 0
        if (node.left != -1) {
            val leftNode = nodes[node.left]
            h1 = setHeight(leftNode) + 1
        }
        if (node.right != -1) {
            val rightNode = nodes[node.right]
            h2 = setHeight(rightNode) + 1
        }
        val height = if (h1 < h2) h2 else h1
        node.height = height
        return height
    }
    setHeight(r)

    fun printLine(n: NodeB) {
        val parentId = n.parent
        var sibling = -1
        if (parentId != -1) {
            val parentNode = nodes[parentId]
            if (parentNode.left == n.id) {
                sibling = parentNode.right
            }
            if (parentNode.right == n.id) {
                sibling = parentNode.left
            }
        }
        println("node ${n.id}: parent = ${n.parent}, sibling = $sibling, degree = ${n.degree}, depth = ${n.depth}, height = ${n.height}, ${n.nodeType}")
    }
    nodes.forEach { printLine(it) }
}
