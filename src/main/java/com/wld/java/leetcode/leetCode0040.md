## [40. 组合总和 II](https://leetcode-cn.com/problems/combination-sum-ii/)（中等）

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

**说明：**

- 所有数字（包括目标数）都是正整数。
- 解集不能包含重复的组合。 

**示例 1:**

```
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

**示例 2:**

```
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

<br/>

### 答案：

这道题和[39. 组合总和](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0039.md)不同的是，这道题数组中的数字只能被选择一次，而第39题数组中的数字可以被选中无数次。解题思想还是一样的，我们要做的是怎么过滤掉重复的，首先可以对原数组进行排序，排序之后相同的肯定是挨着的，if（candidates[i] == candidates[i - 1]）我们就过滤掉candidates[i]，我们就仿照第39题来写下这道题的答案

```java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    List<List<Integer>> list = new LinkedList<>();
    Arrays.sort(candidates);//先排序
    backtrack(list, new ArrayList<>(), candidates, target, 0);
    return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> cur, int[] candidates, int target, int start) {
    if (target == 0) {
        list.add(new ArrayList<>(cur));
        return;
    }
    for (int i = start; i < candidates.length; i++) {
        if (target < candidates[i])
            continue;
        if (i > start && candidates[i] == candidates[i - 1])
            continue; //去掉重复的
        cur.add(candidates[i]);
        backtrack(list, cur, candidates, target - candidates[i], i + 1);
        cur.remove(cur.size() - 1);
    }
}
```

我们发现和[39. 组合总和](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0039.md)很相似，只不过在第3行先做了排序，第16-17行做了去重，由于不能选择重复的，所以在第19行选择一个之后我们从当前元素的下一个进行选择

<br/>

#### 总结

类似这样的题我们可以把它想象为一棵N叉树，我们先选择一个，然后再递归选择（根据是否可以选择重复的有不同的选择，如果允许有重复的，我们递归的时候还可以再选择当前的，如果不允许有重复的，我们递归的时候就从当前的下一个开始选择），沿着这个分支走完之后我们会把当前节点删除，然后再从下一个分支走……



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（39. 组合总和）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0039.md)

#### [下一题（41. 缺失的第一个正数）(困难)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0041.md)