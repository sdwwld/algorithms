## [113. 路径总和 II](https://leetcode-cn.com/problems/path-sum-ii/)（中等）

给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。

**说明:** 叶子节点是指没有子节点的节点。

**示例:**

给定如下二叉树，以及目标和 `sum = 22`，

```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
```

返回:

```
[
   [5,4,11,2],
   [5,8,4,5]
]
```

<br/>

### 答案：

#### 1，递归解法

这题没说sum是正数还是负数，也没说树中节点的值有没有负数。我们要做的是从根节点到叶子节点遍历他所有的路径，返回他所有路径中和等于sum的节点，这里有两种实现方式，一种是减，一种是加。减**就是从根节点开始，用sum不断的减去遍历到的每一个节点，一直到叶子节点** ，在减去叶子节点之前查看sum是否等于叶子节点，如果等于说明我们找到了一组，画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBGYLfsVbxTIL30DGjQPXgqC8SqNO8Nal8EyZQmPG6cjcJOv7bDdn8EjQ4MC2rEAoVXH9yNlibacW6Q/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



```java
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, sum, new ArrayList<>(), result);
    return result;
}

public void dfs(TreeNode root, int sum, List<Integer> list,
                List<List<Integer>> result) {
    //如果节点为空直接返回
    if (root == null)
        return;
    //因为list是引用传递，为了防止递归的时候分支污染，我们要在每个路径
    //中都要新建一个subList
    List<Integer> subList = new ArrayList<>(list);
    //把当前节点值加入到subList中
    subList.add(new Integer(root.val));
    //如果到达叶子节点，就不能往下走了，直接return
    if (root.left == null && root.right == null) {
        //如果到达叶子节点，并且sum等于叶子节点的值，说明我们找到了一组，
        //要把它放到result中
        if (sum == root.val)
            result.add(subList);
        //到叶子节点之后直接返回，因为在往下就走不动了
        return;
    }
    //如果没到达叶子节点，就继续从他的左右两个子节点往下找，注意到
    //下一步的时候，sum值要减去当前节点的值
    dfs(root.left, sum - root.val, subList, result);
    dfs(root.right, sum - root.val, subList, result);
}
```

<br/>

#### 2，回溯，从根节点开始相减

上面只是对二叉树的深度优先搜索（DFS），并没有使用**回溯**，之前讲递归的时候提到过为了防止分支污染我们还可以把使用过的值在返回的时候把它给remove掉，这就是大家常提的**回溯算法**，也可以看下之前讲的[426，什么是递归，通过这篇文章，让你彻底搞懂递归](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487910&idx=1&sn=2670aec7139c6b98e83ff66114ac1cf7&chksm=fb418286cc360b90741ed54fecd62fd45571b2caba3e41473a7ea0934f918d4b31537689c664&scene=21#wechat_redirect)，看下代码

```java
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, sum, new ArrayList<>(), result);
    return result;
}

public void dfs(TreeNode root, int sum, List<Integer> list,
                List<List<Integer>> result) {
    //如果节点为空直接返回
    if (root == null)
        return;
    //把当前节点值加入到list中
    list.add(new Integer(root.val));
    //如果到达叶子节点，就不能往下走了，直接return
    if (root.left == null && root.right == null) {
        //如果到达叶子节点，并且sum等于叶子节点的值，说明我们找到了一组，
        //要把它放到result中
        if (sum == root.val)
            result.add(new ArrayList(list));
        //注意别忘了把最后加入的结点值给移除掉，因为下一步直接return了，
        //不会再走最后一行的remove了，所以这里在rerurn之前提前把最后
        //一个结点的值给remove掉。
        list.remove(list.size() - 1);
        //到叶子节点之后直接返回，因为在往下就走不动了
        return;
    }
    //如果没到达叶子节点，就继续从他的左右两个子节点往下找，注意到
    //下一步的时候，sum值要减去当前节点的值
    dfs(root.left, sum - root.val, list, result);
    dfs(root.right, sum - root.val, list, result);
    //我们要理解递归的本质，当递归往下传递的时候他最后还是会往回走，
    //我们把这个值使用完之后还要把它给移除，这就是回溯
    list.remove(list.size() - 1);
}
```

<br/>

#### 3，回溯，从根节点开始累加

上面是减的方式，我们再来看一个加的方式，其实他就是从根节点开始到叶子节点把这个路径上的所有节点都加起来，最后查看是否等于sum，画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBGYLfsVbxTIL30DGjQPXgqCZxoJTpvMomBmUDibfqzDKLRvWfOxib9IZrH19uAXO73TKVTBClEu2uJA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

代码就很简单了，来看下

```java
public List<List<Integer>> pathSum(TreeNode root, int sum) {
    List<List<Integer>> result = new ArrayList<>();
    dfs(root, sum, 0, new ArrayList<>(), result);
    return result;
}

public void dfs(TreeNode root, int sum, int toal, List<Integer> list,
                List<List<Integer>> result) {
    //如果节点为空直接返回
    if (root == null)
        return;
    //把当前节点值加入到list中
    list.add(new Integer(root.val));
    //没往下走一步就要计算走过的路径和
    toal += root.val;
    //如果到达叶子节点，就不能往下走了，直接return
    if (root.left == null && root.right == null) {
        //如果到达叶子节点，并且sum等于toal，说明我们找到了一组，
        //要把它放到result中
        if (sum == toal)
            result.add(new ArrayList(list));
        //注意别忘了把最后加入的结点值给移除掉，因为下一步直接return了，
        //不会再走最后一行的remove了，所以这里在rerurn之前提前把最后
        //一个结点的值给remove掉。
        list.remove(list.size() - 1);
        //到叶子节点之后直接返回，因为在往下就走不动了
        return;
    }
    //如果没到达叶子节点，就继续从他的左右两个子节点往下找
    dfs(root.left, sum, toal, list, result);
    dfs(root.right, sum, toal, list, result);
    //我们要理解递归的本质，当递归往下传递的时候他最后还是会往回走，
    //我们把这个值使用完之后还要把它给移除，这就是回溯
    list.remove(list.size() - 1);
}
```

<br/>

#### 4，总结

原理很简单，就是从根节点到所有叶子节点路径上的结点值，总和是不是等于sum，如果等于就说明找到了一组。这里要注意因为list是引用传递，在往下传递的过程中不能干扰其他路径，有两种方式可以解决，一种是每个路径都新建一个list，一种就是使用回溯算法，就是在当前路径把当前值使用过之后，跳到其他路径的时候要把那个值给移除掉，防止带到下一个分支，给下一个分支造成干扰。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 