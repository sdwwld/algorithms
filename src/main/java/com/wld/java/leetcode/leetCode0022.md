## [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/)（中等）

数字 *n* 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

<br/>

**示例：**

```
输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]
```

<br/>

### 答案：

#### 1，递归解决

通过观察我们可以发现，生成的任何括号组合中都有两个规律：

**1，括号组合中左括号的数量等于右括号的数量**

**2，括号组合中任何位置左括号的数量都是大于等于右括号的数量**

第一条很容易理解，我们来看第二条，比如有效括号"(())()"，在任何一个位置右括号的数量都不大于左括号的数量，所以他是有效的。如果像这样"())()"第3个位置的是右括号，那么他前面只有一个左括号，而他和他前面的右括号有2个，所以无论如何都不能组合成有效的括号。搞懂了上面的原理，我们就以n等于2为例来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBEfzz7jugGEQwZmQ93H5TDkMfz27a0mLocv2ia3RjsGgasOuh9m4L45FscKLfMXtuZeV2Ov8kngZGg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

看到上面的图我们很容易想到二叉树的前序遍历，可以看下之前写的[373，数据结构-6,树](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487028&idx=1&sn=e06a0cd5760e62890e60e43a279a472b&chksm=fb419d14cc36140257eb220aaeac182287b10c3cab5c803ebd54013ee3fc120d693067c2e960&scene=21#wechat_redirect)，所以这里我们可以参考一下，二叉树的前序遍历代码如下

```java
public static void preOrder(TreeNode tree) {
    if (tree == null)
        return;
    System.out.printf(tree.val + "");
    preOrder(tree.left);
    preOrder(tree.right);
}
```

使用的是递归的方式，有一个终止条件，然后后面是两个递归的调用，所以这题的参考代码如下

```java
public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    dfs(res, n, n, "");
    return res;
}

private void dfs(List<String> res, int left, int right, String curStr) {
    /**
     * 这里有终止条件
     *  return
     */
    //选择左括号
    dfs(res, left - 1, right, curStr + "(");
    // 选择右括号
    dfs(res, left, right - 1, curStr + ")");
}
```

其中left是左括号剩余的数量，right是右括号剩余的数量。代码的大致轮廓已经出来了，关键是终止条件。根据上面的分析，我们知道如果左括号和右括号剩余可选数量都等于0的时候，说明找到了有效的括号组合。如果左括号剩余可选数量为0的时候，我们不能再选择左括号了，但可以选择右括号。如果左括号剩余数量大于右括号剩余数量说明之前选择的是无效的。所以终止条件就呼之欲出了，最终代码如下

```java
public List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    dfs(res, n, n, "");
    return res;
}

private void dfs(List<String> res, int left, int right, String curStr) {
    if (left == 0 && right == 0) { // 左右括号都不剩余了，说明找到了有效的括号
        res.add(curStr);
        return;
    }
    //左括号只有剩余的时候才可以选，如果左括号的数量已经选完了，是不能再选左括号了。
    //如果选完了左括号我们是还可以选择右括号的。
    if (left < 0)
        return;
    // 如果右括号剩余数量小于左括号剩余的数量，说明之前选择的无效
    if (right < left)
        return;
    //选择左括号
    dfs(res, left - 1, right, curStr + "(");
    //选择右括号
    dfs(res, left, right - 1, curStr + ")");
}
```

<br/>

#### 2，动态规划

我们用dp[i]表示的是n等于i的时候生成的有效括号组合，那么递推公式就是



**dp[i]="("+dp[m]+")"+dp[k]**

**其中m+k=i-1**



因为他再加上我们添加的一对括号正好是i，（其中m是从0到i-1）所以这里我们需要枚举m的所有值。主要代码如下

```java
for (int m = 0; m < i; m++) {
    int k = i - 1 - m;
    List<String> str1 = dp[m];
    List<String> str2 = dp[k];
    for (String s1 : str1) {
        for (String s2 : str2) {
            cur.add("(" + s1 + ")" + s2);
        }
    }
}
```

这题的边界条件是dp[0]=""，因为0的时候是没有括号的。所以完整代码如下

```java
public static List<String> generateParenthesis(int n) {
    List<String>[] dp = new List[n + 1];
    List<String> dp0 = new ArrayList<>();
    dp0.add("");
    dp[0] = dp0;
    for (int i = 1; i <= n; i++) {
        List<String> cur = new ArrayList<>();
        for (int m = 0; m < i; m++) {
            int k = i - 1 - m;
            List<String> str1 = dp[m];
            List<String> str2 = dp[k];
            for (String s1 : str1) {
                for (String s2 : str2) {
                    cur.add("(" + s1 + ")" + s2);
                }
            }
        }
        dp[i] = cur;
    }
    return dp[n];
}
```

我们就用n等于3来测试一下打印的结果

```java
public static void main(String args[]) {
    System.out.println(Arrays.toString(generateParenthesis(3).toArray()));
}
```

运行结果如下

```java
[()()(), ()(()), (())(), (()()), ((()))]
```

<br/>

#### 3，动态规划改递归

我们看到上面动态规划中核心代码是dp[m]和dp[k]的组合，而dp[m]和dp[k]分别表示的是n等于m和k的时候有效括号的组合，所以如果函数

**List\<String> generateParenthesis(int n)**

表示的是n对有效括号的组合，那么

**List\<String> generateParenthesis(int m)**

和

**List\<String> generateParenthesis(int k)**

分别表示的是m对和k对有效括号的组合，所以上面的核心代码我们可以这样改

```java
for (int m = 0; m < n; m++) {
    int k = n - m - 1;
    List<String> first = generateParenthesis(m);
    List<String> second = generateParenthesis(k);
    for (String left : first) {
        for (String right : second) {
            list.add("(" + left + ")" + right);
        }
    }
}
```

所以完整代码如下

```java
public static List<String> generateParenthesis(int n) {
    List<String> list = new ArrayList<>();
    if (n == 0) {//边界条件的判断
        list.add("");
        return list;
    }
    for (int m = 0; m < n; m++) {
        int k = n - m - 1;
        List<String> first = generateParenthesis(m);
        List<String> second = generateParenthesis(k);
        for (String left : first) {
            for (String right : second) {
                list.add("(" + left + ")" + right);
            }
        }
    }
    return list;
}
```

<br/>

#### 4，总结

这题可能最容易想到的是暴力求解，就是生成所有的组合，然后再判断这些组合哪些是有效的，但这种效率很差，所以这里没写。上面第一种解法很好的利用了有效括号的特性，无效括号直接舍去，达到剪枝的目的。下面两种解法原理都是一样的，只不过一个使用的是动态规划，一个使用的是递归，都是根据已经生成的长度为i-1的有效括号，然后推出长度为i的有效括号。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 