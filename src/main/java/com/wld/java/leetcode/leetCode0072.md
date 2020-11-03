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

<br/>

### 答案：

#### 1，动态规划

1，如果想把word1变为word2，对于word1的操作我们有3种方式：

- 删除一个字符
- 添加一个字符
- 修改一个字符

这就好比对数据库的增删改查一样，不过这里没有查找。

我们用dp\[i][j]表示把word1的前i个字符变为word2的前j个字符所需要的最少编辑距离，这里要分两种情况

1，当word1[i]==word2[j]：也就是说word1的第i个字符和word2的第j个字符相等，我们不需要修改word1的第i个字符，所以这时dp\[i][j]=dp\[i-1][j-1]。



2，当word1[i]!=word2[j]：也就是说word1的第i个字符和word2的第j个字符不相等。这时我们可以有3种操作来计算dp\[i][j];

- **删**，dp\[i-1][j]：表示的是word1的前i-1个字符和word2的前j个字符的最小编辑距离，在dp\[i][j]中我们只需要把word1中第i个字符删除就是dp\[i-1][j]，所以dp\[i][j]=dp\[i-1][j]+1。
- **增**，dp\[i][j-1]：表示的是word1的前i个字符和word2的前j-1个字符的最小编辑距离，在dp\[i][j]中我们只需要把word2中的第j个字符删除就是dp\[i][j-1]，所以dp\[i][j]=dp\[i][j-1]+1。（注：我们这一步明明是增，但这里为什么是删，因为我们这里删的是word2的字符，增和删是相对的，word2字符的删除也可以认为是word1字符的添加，举个例子，比如word1="a"，word2="ab"，我们在word1中添加一个b或者在word2中删除一个b，最短编辑距离都是一样的）
- **改**，dp\[i-1][j-1]：表示的是word1的前i-1个字符和word2的前j-1个字符的最小编辑距离，我们只需要把word1的第i个字符修改为word2的第j个字符就可以求出dp\[i][j]，所以dp\[i][j]=dp\[i-1][j-1]+1。



上面三种情况我们要选最小的，所以递推公式

1，当word1[i]==word2[j]：

dp\[i][j]=dp\[i-1][j-1]



2，当word1[i]!=word2[j]：

dp\[i][j]=min{dp\[i-1][j-1]，dp\[i-1][j]，dp\[i][j-1]}+1



边界条件：

如果word1为空，我们要把word1变为word2就是不停的插入，

如果word2为空，我们要把word1变为word2就是不停的删除。



下面我们来画个图看一下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHr4J90kjUN8Q678Le7brl7ghWLUswXl7iboacLC5GQTlO9PgQJRjV9NBZic0c1hBaibQCVy7kIYjbvw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

```
举个例子，

比如（0，0）格内，我们只需要把h变为r即可，所以需要1步。

比如（0，1）格内，我们只需要把h变为r，然后删除O，所以需要2步。

比如（1，0）格内，我们只需要把h变为r，然后在添加一个O，所以需要2步。

比如（1，1）格内，因为O==O，我们只需要把h变为r即可，所以需要1步。


看懂了上面的分析过程，代码就容易多了，我们来看下代码
```

```java
public static int minDistance(String word1, String word2) {
    int length1 = word1.length();
    int length2 = word2.length();
    if (length1 * length2 == 0)
        return length1 + length2;//如果有一个为空，直接返回另一个的长度即可
    int dp[][] = new int[length1 + 1][length2 + 1];
    for (int i = 0; i <= length1; i++) {
        dp[i][0] = i;//边界条件，相当于word1的删除操作
    }
    for (int i = 0; i <= length2; i++) {
        dp[0][i] = i;//边界条件，相当于word1的添加操作
    }
    for (int i = 1; i <= word1.length(); i++) {
        for (int j = 1; j <= length2; j++) {//下面是上面分析的递推公式
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {//判断两个字符是否相等
                dp[i][j] = dp[i - 1][j - 1];
            } else {
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
            }
        }
    }
    Util.printTwoIntArrays(dp);//测试数据的打印，可去掉
    return dp[length1][length2];
}
```

代码比较简单，核心代码也就15到19行，其他的也就是一些边界的判断。

我们还用上面的数据测试一下，看一下打印结果

```java
public static void main(String args[]) {
    System.out.println(minDistance("horse", "ros"));
}
```

结果如下

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHr4J90kjUN8Q678Le7brl7znwgGdQIeNNBo97CAhKhzFWlpIhyh7R4RCJGdsm157Z5N7VfMmPc3w/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

和我们上面分析的完全一致。

<br/>

#### 2，代码优化

我们看到虽然dp是二维数组，但我们计算的时候每个元素只和他的左边，上边，左上角的3个值有关，所以这里我们还可以优化一下，使用一维数组，我们看下代码

```java
public static int minDistance2(String word1, String word2) {
    int length1 = word1.length();
    int length2 = word2.length();
    if (length1 * length2 == 0)
        return length1 + length2;
    int dp[] = new int[length2 + 1];
    for (int i = 1; i <= length2; i++) {
        dp[i] = i;
    }
    int last = 0;
    for (int i = 1; i <= word1.length(); i++) {
        last = dp[0];
        dp[0] = i;
        for (int j = 1; j <= length2; j++) {
            int temp = dp[j];
            if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                dp[j] = last;
            } else {
                dp[j] = Math.min(Math.min(dp[j - 1], dp[j]), last) + 1;
            }
            last = temp;
        }
        Util.printIntArrays(dp);//这两行代码仅做测试打印数据使用，可删除
        System.out.println();
    }
    return dp[length2];
}
```

代码中last记录的是左上角的值，因为这个值会被覆盖，所以我们提前记录了下来，我们还用上面的代码测试一下，再来看一下打印结果

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBHr4J90kjUN8Q678Le7brl7v4LYFicSuICLNn2ymVmFvJOj71vfibazQuOYxdSibEMgiaPWQsxI7Uq7SA/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

结果和我们上面分析的完全一致。

<br/>

#### 3，总结

这道题相对来说还是有一定的难度的，首先要了解什么是动态规划，然后再找出他的递推公式，还有一些边界条件的判断，最后是代码的优化。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 