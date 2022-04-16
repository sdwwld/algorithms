## [392. 判断子序列](https://leetcode-cn.com/problems/is-subsequence/)（简单）

给定字符串 s 和 t ，判断 s 是否为 t 的子序列。

你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。

字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。

**示例 1:**
s = "abc", t = "ahbgdc"

返回 true.

**示例 2:**
s = "axc", t = "ahbgdc"

返回 false.

<br/>

### 答案：

#### 1，双指针求解

这题让求的是s是否是t的子序列，我们可以使用两个指针，一个指向s的某个字符，一个指向t的某个字符，其中**指向t的指针每次都会往右移一位**，指向s的指针每次和指向t的指针所对应的字符相同时才会往右移，否则就不移动，当指向s的指针指向s的末尾的时候，返回true。这里以示例1为例来画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0392/640.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0392/641.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0392/642.png)

原理很简单，我们来直接看下代码

```java
public boolean isSubsequence(String s, String t) {
    if (s.length() == 0)
        return true;
    int indexS = 0, indexT = 0;
    while (indexT < t.length()) {
        if (t.charAt(indexT) == s.charAt(indexS)) {
            //指向s的指针只有在两个字符串相同时才会往右移
            indexS++;
            if (indexS == s.length())
                return true;
        }
        //指向t的指针每次都会往右移一位
        indexT++;
    }
    return false;
}
```

<br>

#### 2，动态规划

我们用dp\[i][j]表示字符串t的前j个字符包含s的前i个字符

所以递归公式是

<br>

**1，s.charAt(i - 1) == t.charAt(j - 1)**

  **dp\[i][j] = dp\[i - 1][j - 1]**

<br>

**2，s.charAt(i - 1) != t.charAt(j - 1)**

  **dp\[i][j] = dp\[i][j - 1];**

<br>

那么边界条件是什么呢，当s为空的时候，我们默认t是包含s的，所以当s为空的时候，返回true。有了递推公式和边界条件，代码就很容易写了，来看下

```java
public boolean isSubsequence(String s, String t) {
    if (s.length() == 0)
        return true;
    boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];
    //边界条件
    for (int i = 0; i < t.length(); i++) {
        dp[0][i] = true;
    }
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 1; j <= t.length(); j++) {
            //递推公式
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = dp[i][j - 1];
            }
        }
    }
    return dp[s.length()][t.length()];
}
```

<br>

#### 3，逐个查找

如果熟悉java语言的都知道，在java中String类有这样一个方法

**public int indexOf(int ch, int fromIndex)** 

他表示的是在字符串中是否存在一个字符ch，并且是从字符串的下标fromIndex开始查找的。我们要做的是在t字符串中查找s中的每一个字符，如果没查到，直接返回false。如果查到，就从t的下一个位置继续开始查

```java
public boolean isSubsequence(String s, String t) {
    int index = -1;
    for (char c : s.toCharArray()) {
        //index表示上一次查找的位置(第一次查找的时候为-1)，
        // 所以这里要从t的下标(index+1)开始查找
        index = t.indexOf(c, index + 1);
        //没找到，返回false
        if (index == -1)
            return false;
    }
    return true;
}
```

<br>

#### 4，参照最长公共子序列

看到这道题我们还可以参照之前写的[370，最长公共子串和子序列](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486892&idx=1&sn=4d4c122bf5139ba711b53e9ffd208408&chksm=fb419e8ccc36179a7518796a1339d348ef7786b89c8cc62ec26e9f5bc1c3ec5eb6e68a44e84d&scene=21#wechat_redirect)，我们只要求出s和t的最长公共子序列的长度即可，如果长度等于s的长度，说明s就是t的子序列

```java
public boolean isSubsequence(String s, String t) {
    int[] dp = new int[t.length() + 1];
    int last = 0;
    for (int i = 1; i <= s.length(); i++) {
        for (int j = 1; j <= t.length(); j++) {
            int temp = dp[j];
            if (s.charAt(i - 1) == t.charAt(j - 1))
                dp[j] = last + 1;
            else
                dp[j] = Math.max(dp[j], dp[j - 1]);
            last = temp;
        }
    }
    return dp[t.length()] == s.length();
}
```

<br>

#### 5，总结

这题比较简单，但解法比较多，最容易想到的估计就是双指针了。

<br>



 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
