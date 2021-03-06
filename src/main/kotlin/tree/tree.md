## Tree

節点の数が変化しない時の表現
left-child, right-sibling representation

## 再帰で深さを設定
再帰処理で深さを設定。全てのノードを一度ずつ訪れるのでO(n)

## Left-child right-sibling binary tree
- [wiki](https://en.wikipedia.org/wiki/Left-child_right-sibling_binary_tree)

The LCRS representation is more space-efficient than a traditional multiway tree, but comes at the cost that looking up a node's children by index becomes slower. Therefore, the LCRS representation is preferable if

Memory efficiency is a concern, and/or
Random access of a node's children is not required.
Case (1) applies when large multi-way trees are necessary, especially when the trees contains a large set of data. For example, if storing a phylogenetic tree, the LCRS representation might be suitable.

Case (2) arises in specialized data structures in which the tree structure is being used in very specific ways. For example, many types of heap data structures that use multi-way trees can be space optimized by using the LCRS representation. (Examples include Fibonacci heaps, pairing heaps and weak heaps.) The tree.alds1_8_B.tree.alds1_8_C.tree.alds1_9_A.tree.alds1_9_C.tree.alds1_11_A.tree.alds1_11_B.tree.alds1_11_C.tree.alds1_11_D.tree.alds1_12_A.tree.alds1_12_B.tree.alds1_12_C.main reason for this is that in heap data structures, the most common operations tend to be

Remove the root of a tree and process each of its children, or
Join two trees together by making one tree a child of the other.
Operation (1) it is very efficient. In LCRS representation, it organizes the tree to have a right child because it does not have a sibling, so it is easy to remove the root.

Operation (2) it is also efficient. It is easy to join two trees together.[7]


## Binary tree
[binry tree](https://en.wikipedia.org/wiki/Binary_tree#:~:text=In%20computer%20science%2C%20a%20binary,child%20and%20the%20right%20child.&text=It%20is%20also%20possible%20to,is%20an%20ordered%2C%20rooted%20tree.)


## Binary Search Tree
- 挿入操作のOrder => O(ツリーの高さ) = O(logn) 
- 探索・挿入・削除ともに常にOrder(logN)
- 単純なリストは探索・挿入・削除がOrder(logN)

## Heap
- buildMaxHeapfy(Order(N))
- 
#