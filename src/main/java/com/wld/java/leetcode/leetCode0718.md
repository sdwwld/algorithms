## [718. 最长重复子数组](https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/)（中等）

给两个整数数组 `A` 和 `B` ，返回两个数组中公共的、长度最长的子数组的长度。

 <br/>

**示例：**

```
输入：
A: [1,2,3,2,1]
B: [3,2,1,4,7]
输出：3
解释：
长度最长的公共子数组是 [3, 2, 1] 。
```

<br/>

**提示：**

- 1 <= len(A), len(B) <= 1000
- 0 <= A[i], B[i] < 100

<br/>

### 答案：

#### 1，动态规划

这题一看就知道其实就是求最长公共子串问题，不懂的可以看下前面的[370，最长公共子串和子序列](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486892&idx=1&sn=4d4c122bf5139ba711b53e9ffd208408&chksm=fb419e8ccc36179a7518796a1339d348ef7786b89c8cc62ec26e9f5bc1c3ec5eb6e68a44e84d&scene=21#wechat_redirect)，只不过换了种说法，换汤不换药，本质还是没变。我们就以题中的示例画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0718/640.png)

最长的公共子数组就是上面红色所对应的[3,2,1]，长度是3。

递推公式是

```java
if(s1.charAt(i) == s2.charAr(j))
    dp[i][j] = dp[i-1][j-1] + 1;
else
    dp[i][j] = 0;
```

有了递推公式，代码就容易多了，我们来看下完整代码

```java
public int findLength(int[] A, int[] B) {
    int max = 0;
    int[][] dp = new int[A.length + 1][B.length + 1];
    for (int i = 1; i <= A.length; i++) {
        for (int j = 1; j <= B.length; j++) {
            if (A[i - 1] == B[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
    }
    return max;
}
```

这里的二维数组dp长和宽都要加1是为了减少判断，当然也可以不加1，但这样会多了一些边界的判断，我们来看下不加1的代码

```java
public int findLength(int[] A, int[] B) {
    int max = 0;
    int[][] dp = new int[A.length][B.length];
    for (int i = 0; i < A.length; i++) {
        for (int j = 0; j < B.length; j++) {
            if (A[i] == B[j]) {
                if (i > 0 && j > 0)
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = 1;
                max = Math.max(max, dp[i][j]);
            }
        }
    }
    return max;
}
```

如果看过之前写的[370，最长公共子串和子序列](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247486892&idx=1&sn=4d4c122bf5139ba711b53e9ffd208408&chksm=fb419e8ccc36179a7518796a1339d348ef7786b89c8cc62ec26e9f5bc1c3ec5eb6e68a44e84d&scene=21#wechat_redirect)，我们还可以把二维数组改为一维数组来减少空间复杂度

```java
public int findLength(int[] A, int[] B) {
    int max = 0;
    int[] dp = new int[B.length + 1];
    for (int i = 1; i <= A.length; i++) {
        for (int j = B.length; j >= 1; j--) {
            if (A[i - 1] == B[j - 1])
                dp[j] = dp[j - 1] + 1;
            else
                dp[j] = 0;
            max = Math.max(max, dp[j]);
        }
    }
    return max;
}
```

注意这里第二个for循环是从后往前遍历的，这是因为dp后面的值会依赖前面的值，但前面的值不会依赖后面的值，如果我们改变后面的值对前面的值不会有影响，但改变前面的值会影响面的值，所以这里我们从后往前计算是最合适的。

<br>

#### 2，滑动窗口

第2种方式是滑动窗口，文字叙述不好理解，我们就以[1, 2, 3, 2, 1]和[3,2,1,4]为例来举例说明，这两个数组我故意弄成两个长度不一样的，我们画个图来看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0718/641.png)

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0718/642.png)

相当于说第一个数组位置不动，第二个数组每次往右移一位，搞懂了上的分析过程，代码就容易多了，我们来看下

```java
public int findLength(int[] A, int[] B) {
    if (A.length < B.length)
        return findLengthHelper(A, B);
    return findLengthHelper(B, A);
}

public int findLengthHelper(int[] A, int[] B) {
    int aLength = A.length, bLength = B.length;
    //total是总共运行的次数
    int total = aLength + bLength - 1;
    int max = 0;
    for (int i = 0; i < total; i++) {
        //下面一大坨主要判断数组A和数组B比较的起始位置和比较的长度
        int aStart = 0;
        int bStart = 0;
        int len = 0;
        if (i < aLength) {
            aStart = aLength - i - 1;
            bStart = 0;
            len = i + 1;
        } else {
            aStart = 0;
            bStart = i - aLength + 1;
            len = Math.min(bLength - bStart, aLength);
        }
        int maxlen = maxLength(A, B, aStart, bStart, len);
        max = Math.max(max, maxlen);
    }
    return max;
}

//计算A和B在上面图中红色框内的最大长度
public int maxLength(int[] A, int[] B, int aStart, int bStart, int len) {
    int max = 0, count = 0;
    for (int i = 0; i < len; i++) {
        if (A[aStart + i] == B[bStart + i]) {
            count++;
            max = Math.max(max, count);
        } else {
            count = 0;
        }
    }
    return max;
}
```

<br>

#### 3，总结

其实这道题求的就是最长公共子串问题，通过上面的图分析，可以发现第一种方式和第二种方式都比较好理解，但第一种方式代码明显比第二种少了很多。

<br>



 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
