package tree.alds1_8_C


private class Node(
    var left: Node? = null,
    var right: Node? = null,
    var parent: Node? = null,
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
        node.parent = lastParent
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


    fun findNode(node: Node?, key: Int): Node? {
        if (node == null) {
            return null
        }
        return if (node.key == key) {
            node
        } else {
            if (key < node.key)
                findNode(node.left, key)
            else
                findNode(node.right, key)
        }
    }

    fun getMinimumNode(node: Node): Node {
        var tempN = node
        while (tempN.left != null) {
            tempN = tempN.left!!
        }
        return tempN
    }

    fun getSuccessorNode(x: Node): Node? {
        if (x.right != null) {
            return getMinimumNode(x.right!!)
        }
        var currentNode = x
        var parent = x.parent
        while (parent != null && parent.right == currentNode) {
            currentNode = parent
            parent = currentNode.parent
        }
        return parent
    }

    fun deleteKey(key: Int) {
        val z = findNode(rootNode, key) ?: return
        val y = if (z.left == null || z.right == null) {
            z
        } else {
            getSuccessorNode(z)!!
        }
        if (y != z) {
            z.key = y.key
        }
        if (y.left == null && y.right == null) {
            if (y.parent == null) {
                rootNode = null
            } else {
                if (y.parent!!.left == y) {
                    y.parent!!.left = null
                } else {
                    y.parent!!.right = null
                }
            }
        } else {
            val child = if (y.right != null) y.right!! else y.left!!
            if (y.parent == null) {
                rootNode = child
            }
            if (y.parent!!.left == y) {
                y.parent!!.left = child
            } else {
                y.parent!!.right = child
            }
            child.parent = y.parent
        }


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
                    insert(node, rootNode!!)
                }
            }
            "find" -> {
                val key = line[1].toInt()
                val isFind = findNode(rootNode, key) != null
                if (isFind) {
                    println("yes")
                } else {
                    println("no")
                }
            }
            "delete" -> {
                val key = line[1].toInt()
                deleteKey(key)
            }
        }
    }
}