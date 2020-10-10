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

    fun getSuccessorNode(node: Node): Node? {
        if (node.right != null) {
            return getMinimumNode(node)
        }
        var currentNode = node
        var parent = node.parent
        while (parent != null && parent.right == currentNode) {
            currentNode = parent
            parent = currentNode.parent
        }
        return parent
    }

    fun deleteKey(key: Int) {
        val node = findNode(rootNode, key) ?: return
        val targetNode = if (node.left == null || node.right == null) {
            node
        } else {
            getSuccessorNode(node)!!
        }
        val childNode = if (targetNode.left != null) targetNode.left else targetNode.right
        if (childNode != null) {
            childNode.parent = targetNode.parent
        }
        when {
            targetNode.parent == null -> {
                rootNode = childNode
            }
            targetNode == targetNode.parent!!.left -> {
                targetNode.parent!!.left = childNode
            }
            else -> {
                targetNode.parent!!.right = childNode
            }
        }
        if (targetNode != node) {
            node.key = targetNode.key
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