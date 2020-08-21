## [44. 通配符匹配](https://leetcode-cn.com/problems/wildcard-matching/)（困难）

给定一个字符串 (`s`) 和一个字符模式 (`p`) ，实现一个支持 `'?'` 和 `'*'` 的通配符匹配。

```
'?' 可以匹配任何单个字符。
'*' 可以匹配任意字符串（包括空字符串）。
```

两个字符串**完全匹配**才算匹配成功。

**说明:**

- `s` 可能为空，且只包含从 `a-z` 的小写字母。
- `p` 可能为空，且只包含从 `a-z` 的小写字母，以及字符 `?` 和 `*`。

**示例 1:**

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```

**示例 2:**

```
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。
```

**示例 3:**

```
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
```

**示例 4:**

```
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
```

**示例 5:**

```
输入:
s = "acdcb"
p = "a*c?b"
输出: false
```

<br/>

### 答案：

#### 1，问题分析

通配符匹配，如果p的某个位置是字母，那么他只能和s的字母匹配，如果p的某个位置是“?”字符，那么他可以匹配s的任何字母，如果p的某个位置是“*”字符，那么他可以匹配s的任意多个字母。



**1，状态定义**

dp\[i][j]表示s的前i个字符和p的前j个字符是否匹配。我们最后只需要返回

dp\[s.length][p.length]即可



**2，状态转移**

这里分两种情况，一种是p的第j个字符不是\*，一个是p的第j个字符是\*。

- p的第j个字符不是*

  if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1)== '?') 

  ​    dp\[i][j] = dp\[i - 1][j - 1];

- p的第j个字符是*

  dp\[i][j] = dp\[i - 1][j] || dp\[i][j - 1];



**第一种**

第一种情况比较容易理解，我们可以根据下面的图来看下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHqQDvHsX0ESKicykQ8knNtO5FfftT96yxGuzWJqgEI10QdYdLbssLyVuzXPaM8W8pcicuy5b2upGdg/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

比如上面的s和p的第3个字符匹配成功（要么这两个字符相等，要么p的第3个字符是“?”），我们要看他们前一个字符是否也匹配成功，所以dp\[i][j]=dp\[i-1][j-1]。



**第二种**

第二种情况就是p的第j个字符是*，这个*可以匹配0个，也可以匹配多个。



1，如果要匹配0个，也就是判断p的前j-1个字符和s的前i个字符是否匹配，因为匹配0个的话，也就相当于p的第j个字符是空的，所以

dp\[i][j]=dp\[i][j-1]



2，如果要匹配1个，只需要判断s的前i-1个和p的前j-1个是否匹配，也可以理解为p的第j个和s的第i个同时消失，只需要前面的匹配即可。

dp\[i][j]=dp\[i-1][j-1]



3，如果要匹配2个的话，只需要判断s的前i-2个和p的前j-1个是否匹配，也可以理解为p的第j个和s的第i和第i-1个同时消失（因为p的*匹配两个，相当于把这两个抵消了，我们只需要判断前面的），只需要前面的匹配即可。

dp\[i][j]=dp\[i-2][j-1]



4，如果要匹配n个的话，只需要判断s的前i-n个和p的前j-1个是否匹配即可

dp\[i][j]=dp\[i-n][j-1]



那要这样写下去估计永远写不完了，我们再仔细观察一下。假如p的*要匹配s的n个字符，也就是下面这样

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHqQDvHsX0ESKicykQ8knNtOOp90VKosH2mweO2aUw1ibfdccKUzZ9Mzdia9Jibkv6w6icW8ibBzgmticb8A/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

如果要判断p的字符\*和s的字符f匹配的话，我们只需要判断p的字符\*和s的字符b是否匹配即可。如果要判断这一步我们只需要p的字符*和s的字符e是否匹配即可……，所以我们可以把上面的4个公式简写成一个

dp\[i][j]=dp\[i-1][j]



综上我们把这题的递推公式找出来了，就是

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHqQDvHsX0ESKicykQ8knNtO9lDUE0KFmibibjAlH79Khox7uUy21BdS4dGGGOJ0YaHd4YiaK3gFLfZcQ/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)



**3，边界条件**

边界条件很容易发现，如果s和p都为空，我们默认是可以匹配的。否则p最前面有\*也是可以匹配的，因为\*可以匹配空串。所以边界条件是

```java
boolean[][] dp = new boolean[slen + 1][plen + 1];
dp[0][0] = true;
for (int j = 1; j <= plen && dp[0][j - 1]; j++)
    dp[0][j] = p.charAt(j - 1) == '*';
```

**最终代码**

通过上面的分析我们来看下最终的完整代码

```java
public static boolean isMatch(String s, String p) {
    //如果s不为空，p为空，是匹配不成功的，直接返回false
    if (s.length() != 0 && p.length() == 0)
        return false;

    int slen = s.length(), plen = p.length();
    boolean[][] dp = new boolean[slen + 1][plen + 1];
    dp[0][0] = true;
    //边界条件的初始化
    for (int j = 1; j <= plen && dp[0][j - 1]; j++)
        dp[0][j] = p.charAt(j - 1) == '*';

    for (int i = 1; i <= slen; i++) {
        for (int j = 1; j <= plen; j++) {
            char si = s.charAt(i - 1), pj = p.charAt(j - 1);
            //下面是动态规划的状态转移方程
            if (si == pj || pj == '?') {
                dp[i][j] = dp[i - 1][j - 1];
            } else if (pj == '*') {
                dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
            }
        }
    }
    return dp[slen][plen];
}
```

<br/>

#### 代码优化

如果看过之前讲的[370，最长公共子串和子序列](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486892&idx=1&sn=4d4c122bf5139ba711b53e9ffd208408&chksm=fb419e8ccc36179a7518796a1339d348ef7786b89c8cc62ec26e9f5bc1c3ec5eb6e68a44e84d&scene=21#wechat_redirect)，我们发现上面的代码和第370题的最长公共子序列的代码有很类似的地方，所以我们也可以参照第370题的代码来优化一下，把上面的二维数组改为一维的，来看下代码

```java
public static boolean isMatch(String s, String p) {
    //如果s不为空，p为空，是匹配不成功的，直接返回false
    if (s.length() != 0 && p.length() == 0)
        return false;
    int slen = s.length(), plen = p.length();
    boolean[] dp = new boolean[plen + 1];
    dp[0] = true;
    //边界条件的初始化
    for (int j = 1; j <= plen && dp[j - 1]; j++)
        dp[j] = p.charAt(j - 1) == '*';

    for (int i = 1; i <= slen; i++) {
        //这里的last我们可以认为是上面代码没优化之前的
        //dp[i - 1][j - 1]
        boolean last = false;
        if (i == 1)
            last = true;
        for (int j = 1; j <= plen; j++) {
            //dp[j]使用之后值会被覆盖，所以我们这里在
            //使用前把它先保留下来
            boolean temp = dp[j];
            char si = s.charAt(i - 1), pj = p.charAt(j - 1);
            //下面是动态规划的状态转移方程
            if (si == pj || pj == '?') {
                dp[j] = last;
            } else if (pj == '*') {
                dp[j] = dp[j] || dp[j - 1];
            } else {
                dp[j] = false;
            }
            last = temp;
        }
    }
    return dp[plen];
}
```

<br/>

#### 总结

动态规划基本上都这同一套路，先定义状态表达式，在找转移方程，最后是边界条件。这题可能有一点难度的是当p的第j个字符是\*的时候状态方程该怎么写。其实也很好理解，因为\*可以匹配0个或多个字符。当匹配0个的时候，也就是相当于判断p的前j-1个字符和s的前i个字符是否匹配。当匹配多个的时候，\*可以把s中的多个字符同时消掉，我们先移除掉s中的一个看是否匹配，如果不匹配我们再移除2个……，所以这样很容易找出状态转移方程。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（43. 字符串相乘）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0043.md)

#### [下一题（45. 跳跃游戏 II）(困难)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/leetcode/leetCode0045.md)