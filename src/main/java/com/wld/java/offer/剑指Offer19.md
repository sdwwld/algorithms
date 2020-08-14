## [剑指 Offer 19. 正则表达式匹配](https://leetcode-cn.com/problems/zheng-ze-biao-da-shi-pi-pei-lcof/)（困难）

请实现一个函数用来匹配包含'. '和'\*'的正则表达式。模式中的字符'.'表示任意一个字符，而'\*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab\*ac\*a"匹配，但与"aa.a"和"ab\*a"均不匹配。

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
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3:**

```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```

**示例 4:**

```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```

**示例 5:**

```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

- `s` 可能为空，且只包含从 `a-z` 的小写字母。
- `p` 可能为空，且只包含从 `a-z` 的小写字母以及字符 `.` 和 `*`，无连续的 `'*'`。

<br/>

### 答案：

**2，动态规划求解**

这题是剑指offer的第19题，难度是困难。我们也可以看下之前写的一道和这题非常类似的一道题[310. 正则表达式匹配](https://github.com/sdwwld/algorithms/blob/master/src/main/java/com/wld/java/leetcode/leetCode0010.md)，今天这题和第10题有一点不同的是，第10题的“\*”可以匹配任意字符串，而这题的“*”表示他前面的字符可以出现任意次（包含0次）。

<br/>

我们先定义一个二维数组dp，其中**```dp[i][j]```表示的是p的前j个字符和s的前i个字符匹配的结果**。

<br/>

**一，边界条件**

我们默认dp[0][0]=true；也就是p的前0个字符和s的前0个字符是可以匹配的。因为字符“\*”表示的是匹配他前面的字符0次或者多次，如果p的字符类似于“a\*b\*c”，那么字符“\*”是可以消去前面的一个字符的。我们就以字符"a\*b\*c"为例来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBF0wHBgxSMhqMKaznkHqE1cKfemkf2qzcjjvVPDAp0fj6l4Np4EskLuGuUR9GRZyIFkZuzFtk7kcw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBF0wHBgxSMhqMKaznkHqE1cyRcg9Fib4TeSVjEGGEt4bCdyevufLGkTw3aCZF0Jymiap85LqyLsgl7g/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

所以边界条件的代码如下

```java
public boolean isMatch(String s, String p) {
    if (s == null || p == null)
        return false;
    int m = s.length();
    int n = p.length();
    boolean[][] dp = new boolean[m + 1][n+1];
    dp[0][0] = true;
    for (int i = 0; i < n; i++) {
        //如果p的第i+1个字符也就是p.charAt(i)是"*"的话，
        //那么他就可以把p的第i个字符给消掉（也就是匹配0次）。
        //我们只需要判断p的第i-1个字符和s的前0个字符是否匹
        //配即可。比如p是"a*b*"，如果要判断p的第4个字符
        //"*"和s的前0个字符是否匹配，因为字符"*"可以消去
        //前面的任意字符，只需要判断p的"a*"和s的前0个字
        //符是否匹配即可
        if (p.charAt(i) == '*' && dp[0][i - 1]) {
            dp[0][i + 1] = true;
        }
    }
    ……
}
```

边界条件我们已经找到了，下面再来看一下递推公式。

<br/>

**二，递推公式**

**1，** 如果p的第j+1个字符和s的第i+1个字符相同，或者p的第j+1个字符是“.”（"."可以匹配任意字符），我们只需要判断p的前j个字符和s的前i个字符是否匹配，这个还好理解，我们画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBF0wHBgxSMhqMKaznkHqE1ckY0HmIH859zApOia96cqxGviaiaF1ibjM8iavlPabcOXESjXNWvoMlEtZJA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

p的第3个字符"."是可以和s的第3个字符"f"匹配成功的，我们只需要判断p的前2个字符和s的前2个字符是否匹配成功即可。

代码如下

 

```java

 if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
        dp[i + 1][j + 1] = dp[i][j];
 }
```

**2，** 如果p的第j+1个字符和s的第i+1个字符不能匹配，并且p的第j+1个字符是"\*"，那么就要分两种情况

（1）**p的第j个字符和s的第i+1个字符不能匹配，**

比如：s="abc"，p="abcd\*"

我们就让p的第j个和第j+1个字符同时消失，也就是让"d\*"消失，只需要判断p的前j-1个字符和s的前i+1个字符是否匹配即可。

也就是下面这样

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHyJ6wibZRgATib8BzAQibDiaOl6Lx3pwOYZCHSUaaWSI7icNDBtC56iaXiahicrEZbefFA3ek9XHnLks3ALw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

代码如下

```java
if (p.charAt(j) == '*') {
    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
        dp[i + 1][j + 1] = dp[i + 1][j - 1];
    }
}
```

（2）**p的第j个字符和s的第i+1个字符匹配成功**，有3种情况

- 类似于s="abc"，p="abcc\*";  我们就让\*匹配**0个**，把p的"c*"砍掉，判断s="abc"和p="abc"是否匹配

```java
dp[i+1][j+1] = dp[i+1][j-1]
```

- 类似于s="abc"，p="abc\*";  我们就让\*匹配**1个**，把p的字符"*"砍掉，判断s="abc"和p="abc"是否匹配


```java
dp[i+1][j+1] = dp[i+1][j]
```

- 类似于s="abcc"(或者s="abccc"，s="abcccc"……)，p="abc\*"; 我们就让\*匹配**多个**，把s的最后一个字符"c"砍掉，判断s="abc"(或者s="abcc"，s="abccc"……)和p="abc\*"是否匹配


```java
dp[i+1][j+1] = dp[i][j+1]
```

前面两个的递推公式很好理解，关键是第3个为什么要这样写。其实我们可以这样想，把"c\*"看做是一个整体，比如"abccc"的最后一个字符"c"和p的倒数第二个字符匹配成功，因为"c\*"可以匹配多个，我们就把"abccc"砍掉一个字符"c"，然后再判断"abcc"和"abc\*"是否匹配。

**上面三个递推公式只要有一个为true，就表示能够匹配成功**

我们来看下完整的递推公式

```java
if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
    dp[i + 1][j+1] = dp[i][j];
} else if (p.charAt(j) == '*') {
    if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
        dp[i + 1] [j+1]= dp[i + 1][j-1];
    } else {
        dp[i + 1][j+1] = (dp[i + 1][j] || dp[i][j+1]|| dp[i + 1][j-1]);
    }
}
```

其实上面代码有个重复的地方就是当p的第j+1个字符是"\*"的时候，里面的两种判断方式都会有一个匹配0个的判断，我们可以把它提取出来，像下面这样

```java
 if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
     dp[i + 1][j+1] = dp[i][j];
 } else if (p.charAt(j) == '*') {
     //递归公式
     if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
         dp[i + 1][j+1] = dp[i + 1] [j]|| dp[i][j+1];
     }
     dp[i + 1] [j+1]|= dp[i + 1][j-1];
 }
```

实际上匹配1个和匹配多个也可以合并，代码如下

```java
if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
    dp[i + 1][j + 1] = dp[i][j];
} else if (p.charAt(j) == '*') {
    //递归公式
    if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
        dp[i + 1][j + 1] = dp[i][j + 1];
    }
    dp[i + 1][j + 1] |= dp[i + 1][j - 1];
}
```

边界条件和递推公式都有了，我们再来看下完整代码

```java
public boolean isMatch(String s, String p) {
    if (s == null || p == null)
        return false;
    int m = s.length();
    int n = p.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 0; i < n; i++) {
        //如果p的第i+1个字符也就是p.charAt(i)是"*"的话，
        //那么他就可以把p的第i个字符给消掉（也就是匹配0次）。
        //我们只需要判断p的第i-1个字符和s的前0个字符是否匹
        //配即可。比如p是"a*b*"，如果要判断p的第4个字符
        //"*"和s的前0个字符是否匹配，因为字符"*"可以消去
        //前面的任意字符，只需要判断p的"a*"和s的前0个字
        //符是否匹配即可
        if (p.charAt(i) == '*' && dp[0][i - 1]) {
            dp[0][i + 1] = true;
        }
    }
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                dp[i + 1][j + 1] = dp[i][j];
            } else if (p.charAt(j) == '*') {
                //递归公式
                if (p.charAt(j - 1) == s.charAt(i) || p.charAt(j - 1) == '.') {
                    dp[i + 1][j + 1] = dp[i][j + 1];
                }
                dp[i + 1][j + 1] |= dp[i + 1][j - 1];
            }
        }
    }
    return dp[m][n];
}
```

如果觉得代码有点长，还可以看个更简洁的写法，不过原理都一样

```java
 public boolean isMatch(String s, String p) {
     int m = s.length(), n = p.length();
     boolean[][] dp = new boolean[m + 1][n + 1];
     dp[0][0] = true;
     for (int i = 0; i <= m; i++)
         for (int j = 1; j <= n; j++)
             if (p.charAt(j - 1) == '*')
                 dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
            else
                dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
    return dp[m][n];
}
```

**2，递归求解**

先来定义一个函数，他表示的是s的首字符和p的首字符是否匹配。

```java
//比较s的首字符和p的首字符是否匹配
private boolean comp(String s, String p) {
    return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
}
```

如果要判断字符串s和p是否匹配，我们来看一下递归函数的大致框架

```java
public boolean isMatch(String s, String p) {
    if (p.length() == 0) {
        return s.length() == 0;
    }
    if (p.length() > 1 && p.charAt(1) == '*') {
        // p的第二个字符是 '*'
        ……
    } else {
        // p的第二个字符不是 '*'
        ……
    }
}
```

因为字符"\*"不能单独存在，他需要和他前面的字符搭配使用，成为一个组合。

**1，** 当p的第二个字符不是"\*"的时候，那么p的第一个字符就可以单独和s的第一个字符进行比较。

**2，** 如果p的第二个字符是"\*"，那么p的第二个字符和第一个字符必须成为一个组合来进行匹配，也就类收于"a\*"。下面会分为两种情况

- 字符"\*"匹配0次，让字符"\*"和他前面的那个字符同时消失，然后判断字符串s和p.substring(2)是否匹配。
- 字符"\*"匹配1次或多次，让字符串s砍掉首字符，然后继续和字符串p匹配。

搞懂了上面的过程，代码就比较简单了，来看下完整代码

```java
public boolean isMatch(String s, String p) {
    if (p.length() == 0) {
        return s.length() == 0;
    }
    if (p.length() > 1 && p.charAt(1) == '*') {
        // p的第二个字符是 '*'
        //1,字符"*"把前面的字符消掉，也就是匹配0次
        //2,字符"*"匹配1次或多次
        return isMatch(s, p.substring(2)) || (s.length() > 0 && comp(s, p)) && isMatch(s.substring(1), p);
    } else {
        // p的第二个字符不是 '*'，判断首字符是否相同，如果相同再从第二位继续比较
        return s.length() > 0 && comp(s, p) && (isMatch(s.substring(1), p.substring(1)));
    }
}

//比较s的首字符和p的首字符是否匹配
private boolean comp(String s, String p) {
    return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
}
```





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（18. 删除链表的节点）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer18.md)

#### [下一题（20. 表示数值的字符串）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer20.md)