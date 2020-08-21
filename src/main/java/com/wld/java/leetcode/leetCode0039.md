## [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)（中等）

给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

**说明：**

- 所有数字（包括 `target`）都是正整数。
- 解集不能包含重复的组合。 

**示例 1：**

```
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]
```

**示例 2：**

```
输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

<br/>

**提示：**

- 1 <= candidates.length <= 30
- 1 <= candidates[i] <= 200
- candidate 中的每个元素都是独一无二的。
- 1 <= target <= 500

<br/>

### 答案：

文中说的很明白，从candidates中找到一些数字让他们的和等于target，总共有多少种方式，并且candidates中的数字可以重复使用。我们可以先选择一个数字，用target减去他，然后再重复选择……，当target等于0的时候说明我们找到了一种组合。当target小于0的时候，说明没有找到合适的，我们回到上一步再重新选择数字……。看到这题我们首先想到的是N叉树，我们就以示例2为例画个图来看下

![image.png](https://pic.leetcode-cn.com/1598016828-fLafVI-image.png)

这和二叉树的前序遍历非常相似，他先从根节点一直往左走，直到走到叶子节点为止，然后再回到父节点按同样的方式走右节点的路径，不了解前序遍历的可以看一下[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)，代码如下

```java
public void preOrder(TreeNode tree) {
    if (tree == null)
        return;
    System.out.println(tree.val);
    preOrder(tree.left);
    preOrder(tree.right);
}
```

而N叉树的前序遍历和他类似

```java
public void preOrder(TreeNode tree) {
    if (tree == null)
        return;
    System.out.println(tree.val);
    preOrder("第一个子节点");
    preOrder("第二个子节点");
    ……
    preOrder("第N个子节点");
}
```

这样写也是可以的，但不方便，我们一般会使用一个for循环来写

```java
public void preOrder(TreeNode root) {
    if (root == null)
        return;
    System.out.println(root.val);
    //root.children获取root节点的所有子节点
    for (int i = 0; i < root.children.size(); i++) {
        preOrder(root.children.get(i));
    }
}
```

搞懂了上面的分析过程，代码就简单多了，我们来看下

```java
public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), candidates, target);
    return result;
}

private static void backtrack(List<List<Integer>> result, List<Integer> cur, int candidates[], int target) {
    if (target == 0) {
        result.add(new ArrayList<>(cur));
        return;
    }
    //相当于遍历N叉树的子节点
    for (int i = 0; i < candidates.length; i++) {
        //如果当前节点大于target我们就不要选了
        if (target < candidates[i])
            continue;
        //由于在java中List是引用传递，所以这里要重新创建一个
        List<Integer> list = new ArrayList<>(cur);
        list.add(candidates[i]);
        backtrack(result, list, candidates, target - candidates[i]);
    }
}
```

我们来看下运行结果

```
[2, 2, 2, 2]
[2, 3, 3]
[3, 2, 3]
[3, 3, 2]
[3, 5]
[5, 3]
```

完全出乎我们的意料之外，这是因为出现了重复的数据，[2,3,3],[3,2,3],[3,3,2]其实应该只算一个。在上面的图中我们分析过，如果选择了后面的数字就不能再选择前面的了，因为这样会出现重复，所以我们可以添加一个变量start表示访问的数组中元素的位置，我们只能访问start和start后面的数字，我们再来看下代码

```java
public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(result, new ArrayList<>(), candidates, target, 0);
    return result;
}

private static void backtrack(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start) {
    if (target == 0) {
        result.add(new ArrayList<>(cur));
        return;
    }
    //相当于遍历N叉树的子节点
    for (int i = start; i < candidates.length; i++) {
        //如果当前节点大于target我们就不要选了
        if (target < candidates[i])
            continue;
        //由于在java中List是引用传递，所以这里要重新创建一个
        List<Integer> list = new ArrayList<>(cur);
        list.add(candidates[i]);
        backtrack(result, list, candidates, target - candidates[i], i);
    }
}
```

注意这里第13行的for循环不是从0开始了，再来看下运行结果

```
[2, 2, 2, 2]
[2, 3, 3]
[3, 5]
```

和我们图中分析的完全一致，并且也没有了重复的。在上面的18行我们是新建了一个list，其实我们还可以不用新建，在回溯的时候把它移除即可，可以这样写

```java
public static List<List<Integer>> combinationSum(int[] candidates, int target) {
    List<List<Integer>> result = new ArrayList<>();
    getResult(result, new ArrayList<>(), candidates, target, 0);
    return result;
}


private static void getResult(List<List<Integer>> result, List<Integer> cur, int candidates[], int target, int start) {
    if (target == 0) {
        result.add(new ArrayList<>(cur));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        if (target < candidates[i])
            continue;
        //选择当前节点，类似于从当前节点开始往下遍历
        cur.add(candidates[i]);
        getResult(result, cur, candidates, target - candidates[i], i);
        //回到当前节点的时候我们把当前节点给移除,
        // 然后通过循环走同一层的其他节点。
        //举个例子，比如上面图中，最开始的时候
        // 我们先选择2，然后沿着这个分支走下去，
        //当回到当前分支的时候我们把2给移除，然后
        // 选择同一层的下一个3，沿着这个节点
        //分支走下去……
        cur.remove(cur.size() - 1);
    }
}
```



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（38. 外观数列）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0038.md)

#### [下一题（40. 组合总和 II）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0040.md)