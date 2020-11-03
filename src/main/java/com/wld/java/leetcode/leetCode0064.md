## [64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)（中等）

给定一个包含非负整数的 *m* x *n* 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：** 每次只能向下或者向右移动一步。

**示例:**

```
输入:
[
  [1,3,1],
  [1,5,1],
  [4,2,1]
]
输出: 7
解释: 因为路径 1→3→1→1→1 的总和最小。
```

<br/>

### 答案：

#### 1，动态规划求解

这题求的是从左上角到右下角，路径上的数字和最小，并且每次只能向下或向右移动。所以上面很容易想到动态规划求解。我们可以使用一个二维数组dp，dp\[i][j]表示的是从左上角到坐标(i，j)的最小路径和。那么走到坐标(i，j)的位置只有这两种可能，要么从上面(i-1，j)走下来，要么从左边（i，j-1）走过来，我们要选择路径和最小的再加上当前坐标的值就是到坐标（i，j）的最小路径。

<br/>

所以递推公式就是

**dp\[i][j]=min(dp\[i-1][j]+dp\[i][j-1])+grid\[i][j];**

<br/>

有了递推公式再来看一下边界条件，当在第一行的时候，因为不能从上面走下来，所以当前值就是前面的累加。同理第一列也一样，因为他不能从左边走过来，所以当前值只能是上面的累加。

![img](https://mmbiz.qpic.cn/mmbiz_png/PGmTibd8KQBF8hkbV85dzYgB1sRJnGFwTvnEp0woJ0QAuyfxduUdx1tdria1VhQcjb3MRLrFasndOZKib149t1yew/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

比如上面图中，如果我们走到中间这一步的话，我们可以从上面```1→3→5```走过来，也可以从左边```1→1→5```，我们取最小的即可。我们来看下代码

```java
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    int[][] dp = new int[m][n];
    dp[0][0] = grid[0][0];
    //第一列只能从上面走下来
    for (int i = 1; i < m; i++) {
        dp[i][0] = dp[i - 1][0] + grid[i][0];
    }
    //第一行只能从左边走过来
    for (int i = 1; i < n; i++) {
        dp[0][i] = dp[0][i - 1] + grid[0][i];
    }
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            //递推公式，取从上面走下来和从左边走过来的最小值+当前坐标的值
            dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
        }
    }
    return dp[m - 1][n - 1];
}
```

我们看到二维数组dp和二维数组grid的长和宽都是一样的，没必要再申请一个dp数组，完全可以使用grid，来看下代码

```java
public int minPathSum(int[][] grid) {
    int m = grid.length, n = grid[0].length;
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (i == 0 && j == 0)
                continue;
            if (i == 0) {
                //第一行只能从左边走过来
                grid[i][j] += grid[i][j - 1];
            } else if (j == 0) {
                //第一列只能从上面走下来
                grid[i][j] += grid[i - 1][j];
            } else {
                //递推公式，取从上面走下来和从左边走过来的最小值+当前坐标的值
                grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
            }
        }
    }
    return grid[m - 1][n - 1];
}
```

<br/>

#### 2，递归求解

我们还可以把上面的动态规划改为递归，定义一个函数

minPathSum(int\[][] grid, int i, int j)表示从左上角到坐标(i，j)的最短路径和，那么同样道理，要走到坐标(i，j)只能从上面下来或者左边过来。所以代码轮廓我们大致能写出来

```java
public int minPathSum(int[][] grid, int i, int j) {
    if (边界条件的判断) {
        return
    }

    //一些逻辑处理

    //取从上面走下来和从左边走过来的最小值+当前坐标的值
    return grid[i][j] + Math.min(minPathSum(grid, i - 1, j), minPathSum(grid, i, j - 1));
}
```

下面再来看下完整代码

```java
public int minPathSum(int[][] grid) {
    return minPathSum(grid, grid.length - 1, grid[0].length - 1);
}

public int minPathSum(int[][] grid, int i, int j) {
    if (i == 0 && j == 0)
        return grid[i][j];
    //第一行只能从左边走过来
    if (i == 0)
        return grid[i][j] + minPathSum(grid, i, j - 1);
    //第一列只能从上面走下来
    if (j == 0)
        return grid[i][j] + minPathSum(grid, i - 1, j);
    //取从上面走下来和从左边走过来的最小值+当前坐标的值
    return grid[i][j] + Math.min(minPathSum(grid, i - 1, j), minPathSum(grid, i, j - 1));
}
```

因为这里面的递归会导致大量的重复计算，所以还是老方法，就是把计算过的值存储到一个map中，下次计算的时候先看map中是否有，如果有就直接从map中取，如果没有再计算，计算之后再把结果放到map中，来看下代码

```java
public int minPathSum(int[][] grid) {
    return minPathSum(grid, grid.length - 1, grid[0].length - 1, new HashMap<String, Integer>());
}

public int minPathSum(int[][] grid, int i, int j, Map<String, Integer> map) {
    if (i == 0 && j == 0)
        return grid[i][j];
    String key = i + "*" + j;
    if (map.containsKey(key))
        return map.get(key);
    int res = 0;
    //第一行只能从左边走过来
    if (i == 0)
        res = grid[i][j] + minPathSum(grid, i, j - 1, map);
        //第一列只能从上面走下来
    else if (j == 0)
        res = grid[i][j] + minPathSum(grid, i - 1, j, map);
        //取从上面走下来和从左边走过来的最小值+当前坐标的值
    else
        res = grid[i][j] + Math.min(minPathSum(grid, i - 1, j, map), minPathSum(grid, i, j - 1, map));
    map.put(key, res);
    return res;
}
```

<br/>

#### 总结：

这题使用动态规划应该说是最容易理解的，也可以参照前面的[409，动态规划求不同路径](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487666&idx=1&sn=348938a0e110abdc081a07572a206561&chksm=fb418392cc360a84df035e84b8c08c6c38eeb603809cd13c373fe6840097054b798c93f94a6a&scene=21#wechat_redirect)和[411，动态规划和递归求不同路径 II](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487679&idx=1&sn=9e3487051872e33231d1c589de5b9277&chksm=fb41839fcc360a897e49934e6789a935f505aaf16f6e03a484b469dd8d0828e451685b30869c&scene=21#wechat_redirect)，只不过递推公式会有点差别。





![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 