package alds8


class Node(
    var id: Int,
    var left: Int? = null,
    var right: Int? = null,
    var parent: Int = -1,
    var depth: Int = 0,
    val children: List<Int> = listOf()
)


fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    val nodes: MutableList<Node> = mutableListOf()
    val nodeMap: MutableMap<Int, Node> = mutableMapOf()
    for (i in 0 until n) {
        val line = readLine()!!.split(" ").map { it.toInt() }
        val id = line[0]
        val children = line.slice(2 until line.lastIndex)
        val left = if (children.isNotEmpty()) children[0] else null
        val node = Node(id, left, children = children)
        nodeMap[id] = node
        nodes.add(node)
    }

    /* set right and parent*/
    nodes.forEach { node ->
        node.children.forEach { cId ->
            val childNode = nodeMap[cId]!!
            childNode.parent = node.id
            val right = node.children.getOrNull(cId + 1)
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
        val nodeType = if (n.parent == -1) {
            "root"
        } else if (n.left != null) {
            "internal node"
        } else {
            "leaf"
        }
        val children = mutableListOf<Int>()
        var right = n.right
        while (right != null) {
            children.add(right)
            right = nodes[right].right
        }
        println("node ${n.id}: parent = ${n.parent}, depth = ${n.depth}, $nodeType, $children")
    }
    nodes.forEach { printLine(it) }

}
