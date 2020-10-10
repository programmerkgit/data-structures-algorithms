package tree.alds1_8_A

class Node(
    var parent: Node? = null,
    var left: Node? = null,
    var right: Node? = null,
    var key: Int = 0
)


fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    var rootNode: Node? = null;
    fun insert(node: Node, parentNode: Node) {
        var currentParent: Node? = parentNode
        var lastParent = currentParent!!
        while (currentParent != null) {
            lastParent = currentParent
            currentParent = if (node.key < currentParent.key) {
                currentParent.left
            } else {
                currentParent.right
            }
        }
        if (node.key < lastParent.key) {
            lastParent.left = node
        } else {
            lastParent.right = node
        }
    }

    fun inOrder(node: Node?) {
        if (node == null) {
            return
        }
        inOrder(node.left)
        print(" ${node.key}")
        inOrder(node.right)
    }

    fun preOrder(node: Node?) {
        if (node == null) {
            return
        }
        print(" ${node.key}")
        preOrder(node.left)
        preOrder(node.right)
    }

    for (i in 0 until n) {
        val line = readLine()!!.split(" ")
        val instruction = line[0];
        when (instruction) {
            "print" -> {
                inOrder(rootNode)
                println()
                preOrder(rootNode)
                println()
            }
            "insert" -> {
                val key = line[1].toInt()
                val node = Node(key = key)
                if (rootNode == null) {
                    rootNode = node
                } else {
                    insert(node, rootNode)
                }
            }
        }
    }
}