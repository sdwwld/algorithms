## [105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)（中等）

根据一棵树的前序遍历与中序遍历构造二叉树。

**注意:**
你可以假设树中没有重复的元素。

例如，给出

```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：

```
    3
   / \
  9  20
    /  \
   15   7
```



### 答案：

#### 1，递归方式

做这题之前我们先来看一下树的几种遍历顺序。

**先序遍历：根节点→左子树→右子树。**

**中序遍历：左子树→根节点→右子树。**

**后续遍历：左子树→右子树→根节点。**

其实也很好记，他是根据根节点遍历的顺序来定义的，比如先遍历根节点就是先序遍历，中间遍历根节点就是中序遍历，最后遍历根节点就是后续遍历，至于左子树和右子树哪个先遍历，记住一点，这3种遍历顺序右节点永远都不可能比左节点先遍历。如果还不懂的可以看下之前写的[373，数据结构-6,树](https://mp.weixin.qq.com/s/mBXfpH4nuIltyHm72zLryw)。

我们就以上面的示例数据来看下，前序遍历是[3,9,20,15,7]，前序遍历先访问的是根节点，所以3就是根节点。中序遍历是[9,3,15,20,7]，由于中序遍历是在左子树都遍历完的时候才遍历根节点，所有在中序遍历中3前面的都是3的左子树节点，3后面的都是3的右子树节点。也就是下面这样

![image.png](https://pic.leetcode-cn.com/d43a140c3cc90ba624b07477efc7847409080f1bdd014ec210d992485711fdab-image.png)

然后我们再使用同样的方式对左右子树继续划分，一直这样下去，直到不能再分为止，我们来看下代码

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    //把前序遍历的值和中序遍历的值放到list中
    List<Integer> preorderList = new ArrayList<>();
    List<Integer> inorderList = new ArrayList<>();
    for (int i = 0; i < preorder.length; i++) {
        preorderList.add(preorder[i]);
        inorderList.add(inorder[i]);
    }
    return helper(preorderList, inorderList);
}

private TreeNode helper(List<Integer> preorderList, List<Integer> inorderList) {
    if (inorderList.size() == 0)
        return null;
    //前序遍历的第一个值就是根节点
    int rootVal = preorderList.remove(0);
    //创建跟结点
    TreeNode root = new TreeNode(rootVal);
    //查看根节点在中序遍历中的位置，然后再把中序遍历的数组劈两半，前面部分是
    //根节点左子树的所有值，后面部分是根节点右子树的所有值
    int mid = inorderList.indexOf(rootVal);
    //[0，mid)是左子树的所有值，inorderList.subList(0, mid)表示截取inorderList
    //的值，截取的范围是[0，mid)，包含0不包含mid。
    root.left = helper(preorderList, inorderList.subList(0, mid));
    //[mid+1，inorderList.size())是右子树的所有值，
    // inorderList.subList(mid + 1, inorderList.size())表示截取inorderList
    //的值，截取的范围是[mid+1，inorderList.size())，包含mid+1不包含inorderList.size()。
    root.right = helper(preorderList, inorderList.subList(mid + 1, inorderList.size()));
    return root;
}
```

上面代码中是先把数组转化为list集合，然后在list集合中进行截取，这样效率明显不是很高，实际上我们还可以不使用list，不对数组进行截取。

<br/>

#### 2，使用指针解决

我们只需要使用3个指针即可。一个是preStart，他表示的是前序遍历开始的位置，一个是inStart，他表示的是中序遍历开始的位置。一个是inEnd，他表示的是中序遍历结束的位置，我们主要是对中序遍历的数组进行拆解，下面就以下面的这棵树来画个图分析下

![image.png](https://pic.leetcode-cn.com/92dd453913765202b1ada14356a15818a4bd8e4fc0161d2725d98579522537c9-image.png)

**他的前序遍历是：[3,9,8,5,2,20,15,7]**

**他的中序遍历是：[5,8,9,2,3,15,20,7]**

![image.png](https://pic.leetcode-cn.com/ee8fd1b65e061e08a44c6cfa44721b6df1f038c9e484d4086e3b2483dac4e242-image.png)

这里只要找到了前序遍历的结点在中序遍历的位置，我们就可以把中序遍历数组分解为两部分了。如果index是前序遍历的某个值在中序遍历数组中的索引，以index为根节点划分的话，那么中序遍历中

**[0，index-1]就是根节点左子树的所有节点，**

**[index+1，inorder.length-1]就是根节点右子树的所有节点。**

**中序遍历好划分，那么前序遍历呢，如果是左子树：**

**preStart=index+1；**

**如果是右子树就稍微麻烦点，**

**preStart=preStart+(index-instart+1)；**

preStart是当前节点比如m先序遍历开始的位置，index-instart+1就是当前节点m左子树的数量加上当前节点的数量，所以preStart+(index-instart+1)就是当前节点m右子树前序遍历开始的位置，我们来看下完整代码

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    return helper(0, 0, inorder.length - 1, preorder, inorder);
}

public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
    if (preStart > preorder.length - 1 || inStart > inEnd) {
        return null;
    }
    //创建结点
    TreeNode root = new TreeNode(preorder[preStart]);
    int index = 0;
    //找到当前节点root在中序遍历中的位置，然后再把数组分两半
    for (int i = inStart; i <= inEnd; i++) {
        if (inorder[i] == root.val) {
            index = i;
            break;
        }
    }
    root.left = helper(preStart + 1, inStart, index - 1, preorder, inorder);
    root.right = helper(preStart + index - inStart + 1, index + 1, inEnd, preorder, inorder);
    return root;
}
```

<br/>

#### 3，使用栈解决

如果使用栈来解决首先要搞懂一个知识点，就是前序遍历挨着的两个值比如m和n，他们会有下面两种情况之一的关系。

**1，n是m左子树节点的值。**

**2，n是m右子树节点的值或者是m某个祖先节点的右节点的值。**

- 对于第一个知识点我们很容易理解，如果m的左子树不为空，那么n就是m左子树节点的值。

- 对于第二个问题，如果一个结点没有左子树只有右子树，那么n就是m右子树节点的值，如果一个结点既没有左子树也没有右子树，那么n就是m某个祖先节点的右节点，我们只要找到这个祖先节点就好办了。


搞懂了这点，代码就很容易写了，下面看下完整代码

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    if (preorder.length == 0)
        return null;
    Stack<TreeNode> s = new Stack<>();
    //前序的第一个其实就是根节点
    TreeNode root = new TreeNode(preorder[0]);
    TreeNode cur = root;
    for (int i = 1, j = 0; i < preorder.length; i++) {
        //第一种情况
        if (cur.val != inorder[j]) {
            cur.left = new TreeNode(preorder[i]);
            s.push(cur);
            cur = cur.left;
        } else {
            //第二种情况
            j++;
            //找到合适的cur，然后确定他的右节点
            while (!s.empty() && s.peek().val == inorder[j]) {
                cur = s.pop();
                j++;
            }
            //给cur添加右节点
            cur = cur.right = new TreeNode(preorder[i]);
        }
    }
    return root;
}
```

<br/>

#### 4，递归的另一种解法

我们知道前序遍历的第一个元素肯定是根节点，那么前序遍历的第一个节点在中序位置之前的都是根节点的左子节点，之后的都是根节点的右子节点，我们来简单画个图看一下

![image.png](https://pic.leetcode-cn.com/bbb780e75defe26988d6aa0d1cb5a9db1b4d34d6d4981b951c5dcc62e8dba5f6-image.png)

这里是随便举个例子，我们看到前序遍历的3肯定是根节点，那么在中序遍历中，3前面的都是3左子节点的值，3后面的都是3右子节点的值，

他真正的结构是这样的

![image.png](https://pic.leetcode-cn.com/f605585db4d6be8878d6d4844948b4265e85bf7a591d96f5b4e16d1ea81b15f5-image.png)

我们来看下代码

```java
private int in = 0;
private int pre = 0;

public TreeNode buildTree(int[] preorder, int[] inorder) {
    return build(preorder, inorder, Integer.MIN_VALUE);
}

private TreeNode build(int[] preorder, int[] inorder, int stop) {
    if (pre >= preorder.length)
        return null;
    if (inorder[in] == stop) {
        in++;
        return null;
    }

    TreeNode node = new TreeNode(preorder[pre++]);
    node.left = build(preorder, inorder, node.val);
    node.right = build(preorder, inorder, stop);
    return node;
}
```



参考：[剑指 Offer 7. 重建二叉树](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer07.md)

![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（104. 二叉树的最大深度）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0104.md)

#### [下一题（106. 从中序与后序遍历序列构造二叉树）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0106.md)