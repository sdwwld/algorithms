## [617. 合并二叉树](https://leetcode-cn.com/problems/merge-two-binary-trees/)（简单）

给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

你需要将他们合并为一个新的二叉树。合并的规则是**如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。**

**示例 1:**

```
输入: 
	Tree 1                     Tree 2                  
          1                         2                             
         / \                       / \                            
        3   2                     1   3                        
       /                           \   \                      
      5                             4   7                  
输出: 
合并后的树:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7

```

**注意:** 合并必须从两个树的根节点开始。

<br/>

### 答案：

#### 1，DFS解决

合并两棵二叉树，会有下面3种情况

<br>

- **树的两个节点都是空，那就不需要合并了**
- **树的两个节点一个为空一个不为空，那么合并的结果肯定是不为空的那个节点**
- **树的两个节点都不为空，那么合并的节点值就是这个两个节点的和**

<br>

来画个简图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0617/642.png)

代码如下

```java
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    //如果两个节点都为空，直接返回空就行了
    if (t1 == null && t2 == null)
        return null;
    //如果t1节点为空，就返回t2节点
    if (t1 == null)
        return t2;
    //如果t2节点为空，就返回t1节点
    if (t2 == null)
        return t1;
    //走到这一步，说明两个节点都不为空，然后需要把这两个节点
    //合并成一个新的节点
    TreeNode newNode = new TreeNode(t1.val + t2.val);
    //当前节点t1和t2合并完之后，还要继续合并t1和t2的子节点
    newNode.left = mergeTrees(t1.left, t2.left);
    newNode.right = mergeTrees(t1.right, t2.right);
    return newNode;
}
```

<br>

#### 2，BFS解决

除了DFS我们还可以使用BFS，就是一层一层的遍历，合并的原理和上面一样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/641.png)

这里是把第2棵树合并到第1棵树上，

<br>

- **如果树1的左子节点为空，直接把第2棵树的左子节点赋给第1棵树的左子节点即可**
- **如果树1的左子节点不为空，树2的左子节点为空，直接返回树1的左子节点即可**
- **如果树1的左子节点和树2的左子节点都不为空，直接相加。**

<br>

右子树和上面原理一样，来看下代码

```java
//把第2棵树合并到第1棵树上
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
    //如果t1节点为空，就返回t2节点
    if (t1 == null)
        return t2;
    //如果t2节点为空，就返回t1节点
    if (t2 == null)
        return t1;
    //队列中两棵树的节点同时存在，
    Queue<TreeNode> queue = new LinkedList<>();
    //把这两棵树的节点同时入队
    queue.add(t1);
    queue.add(t2);
    while (!queue.isEmpty()) {
        //两棵树的节点同时出队
        TreeNode node1 = queue.poll();
        TreeNode node2 = queue.poll();
        //把这两个节点的值相加，然后合并到第1棵树的节点上
        node1.val += node2.val;
        if (node1.left == null) {
            //如果node1左子节点为空，我们直接让node2的
            //左子结点成为node1的左子结点，
            node1.left = node2.left;
        } else {
            //执行到这一步，说明node1的左子节点不为空，
            //如果node2的左子节点为空就不需要合并了，
            //只有node2的左子节点不为空的时候才需要合并
            if (node2.left != null) {
                queue.add(node1.left);
                queue.add(node2.left);
            }
        }

        //原理同上，上面判断的是左子节点，这里判断的是右子节点
        if (node1.right == null) {
            node1.right = node2.right;
        } else {
            if (node2.right != null) {
                queue.add(node1.right);
                queue.add(node2.right);
            }
        }
    }
    //把第2棵树合并到第1棵树上，所以返回的是第1棵树
    return t1;
}
```

<br>

#### 3，总结

合并两棵树，这里要分为3种情况来讨论，除了这3种情况，基本上也没有其他需要注意的地方。如果对二叉树的DFS和BFS比较熟悉的话，这题很容易写出来。



 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
