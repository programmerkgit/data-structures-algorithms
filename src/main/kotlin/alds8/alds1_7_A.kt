package alds8

class Node(
    var id: Int? = null,
    var left: Int? = null,
    var right: Int? = null,
    var parent: Int = -1,
    var depth: Int = 0
)


fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val nodes: MutableList<Node> = MutableList(n) { Node() }
    for (i in 0 until n) {
        val line = readLine()!!.split(" ").map { it.toInt() }
        val id = line[0]
        val node = nodes[id]
        node.id = id
        val children = line.slice(2 until line.size)
        node.left = if (children.isNotEmpty()) children[0] else null
        children.forEachIndexed { indx, cId ->
            val childNode = nodes[cId]
            childNode.parent = node.id!!
            val right = children.getOrNull(indx + 1)
            if (right != null) {
                childNode.right = right
            }
        }
    }

    fun setDepth(n: Node, d: Int = 0) {
        n.depth = d
        if (n.right != null) {
            setDepth(nodes[n.right!!], d)
        }
        if (n.left != null) {
            setDepth(nodes[n.left!!], d + 1)
        }
    }

    val r = nodes.find { it.parent == -1 }!!
    setDepth(r)

    fun printLine(n: Node) {
        val nodeType = when {
            n.parent == -1 -> {
                "root"
            }
            n.left != null -> {
                "internal node"
            }
            else -> {
                "leaf"
            }
        }
        val children = mutableListOf<Int>()
        val childNode = n.left
        if (childNode != null) {
            children.add(n.left!!)
            var right = nodes[childNode].right
            while (right != null) {
                children.add(right)
                right = nodes[right].right
            }
        }

        println("node ${n.id}: parent = ${n.parent}, depth = ${n.depth}, $nodeType, ${children}")
    }
    nodes.forEach { printLine(it) }
}
