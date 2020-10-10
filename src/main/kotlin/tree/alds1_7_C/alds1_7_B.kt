package tree.alds1_7_C


class NodeB(
    var id: Int? = null,
    var left: Int = -1,
    var right: Int = -1,
    var parent: Int = -1
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
    fun preOrder(node: NodeB) {
        print(" ${node.id}")
        if (node.left != -1) {
            preOrder(nodes[node.left])
        }
        if (node.right != -1) {
            preOrder(nodes[node.right])
        }
    }

    fun inOrder(node: NodeB) {
        if (node.left != -1) {
            val leftNode = nodes[node.left]
            inOrder(leftNode)
        }
        print(" ${node.id}")
        if (node.right != -1) {
            val rightNode = nodes[node.right]
            inOrder(rightNode)
        }
    }

    fun postOrder(node: NodeB) {
        if (node.left != -1) {
            val leftNode = nodes[node.left]
            postOrder(leftNode)
        }
        if (node.right != -1) {
            val rightNode = nodes[node.right]
            postOrder(rightNode)
        }
        print(" ${node.id}")
    }

    println("Preorder")
    preOrder(r)
    println()
    println("Inorder")
    inOrder(r)
    println()
    println("Postorder")
    postOrder(r)
    println()
}
