## [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/)（困难）

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。

你可以对一个单词进行如下三种操作：

1. 插入一个字符
2. 删除一个字符
3. 替换一个字符

**示例 1：**

```
输入：word1 = "horse", word2 = "ros"
输出：3
解释：
horse -> rorse (将 'h' 替换为 'r')
rorse -> rose (删除 'r')
rose -> ros (删除 'e')
```

**示例 2：**

```
输入：word1 = "intention", word2 = "execution"
输出：5
解释：
intention -> inention (删除 't')
inention -> enention (将 'i' 替换为 'e')
enention -> exention (将 'n' 替换为 'x')
exention -> exection (将 'n' 替换为 'c')
exection -> execution (插入 'u')
```



### 答案：



#### [上一题（71. 无重复字符的最长子串）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0071.md)

#### [下一题（73. 最长回文子串）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0073.md)