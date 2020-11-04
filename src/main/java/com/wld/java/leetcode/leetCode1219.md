## [1219. 黄金矿工](https://leetcode-cn.com/problems/path-with-maximum-gold/)（中等）

你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为``` m * n ```的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

- 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
- 矿工每次可以从当前位置向上下左右四个方向走。
- 每个单元格只能被开采（进入）一次。
- 不得开采（进入）黄金数目为 0 的单元格。
- 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。

<br>

**示例 1：**

```
输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
输出：24
解释：
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -> 8 -> 7。
```

**示例 2：**

```
输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
输出：28
解释：
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。
```

<br>

**提示：**

- 1 <= grid.length, grid[i].length <= 15
- 0 <= grid[i][j] <= 100
- 最多 **25** 个单元格中有黄金。

<br/>

### 答案：

这题上面说了一大堆，其实就是在一个二维数组中从任一位置开始，可以往他的上下左右4个方向走，然后返回走过的路线中值最大的，0其实就相当于障碍物，不能往位置为0的地方走，画个简单的图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode1/1219/640.png)

我们需要遍历每一个位置，从任何一个位置开始找到最大路径，所以代码大致轮廓如下

```java
public int getMaximumGold(int[][] grid) {
    //边界条件判断
    if (grid == null || grid.length == 0)
        return 0;
    //保存最大路径值
    int res = 0;
    //两个for循环，遍历每一个位置，让他们当做起点
    //查找最大路径值
    for (int i = 0; i < grid.length; i++) {
        for (int j = 0; j < grid[0].length; j++) {
            //函数dfs是以坐标(i,j)为起点，查找最大路径值
            res = Math.max(res, dfs(grid, i, j));
        }
    }
    //返回最大路径值
    return res;
}
```

代码的大致轮廓写出来了，这里主要是dfs这个函数，他表示的是以(i，j)为坐标点，沿着他的上下左右4个方向查找最大路径，这里我们很容易把它想象成为一颗4叉树，就像下面这样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/644.png)

看到这个图，很容易想到之前讲的[426，什么是递归，通过这篇文章，让你彻底搞懂递归](http://mp.weixin.qq.com/s?__biz=MzU0ODMyNDk0Mw==&mid=2247487910&idx=1&sn=2670aec7139c6b98e83ff66114ac1cf7&chksm=fb418286cc360b90741ed54fecd62fd45571b2caba3e41473a7ea0934f918d4b31537689c664&scene=21#wechat_redirect)。他会沿着每一个分支一直走下去，直到遇到终止条件，并且把走过的位置全部置为0，表示不能再走这个位置了。终止条件是什么呢，很明显，i和j都不能越界，并且当前位置不能是0，也就是下面这样

```java
if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
    return 0;
```

因为使用的是递归，往下走的时候把当前的值给置为0了，当递归往回走的时候我们当前位置的值给还原，所以上面dfs的最终代码如下

```java
public int dfs(int[][] grid, int x, int y) {
    //边界条件的判断，x,y都不能越界，同时当前坐标的位置如果是0，表示有障碍物
    //或者遍历过了
    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0)
        return 0;
    //先把当前坐标的值保存下来，最后再还原
    int temp = grid[x][y];
    //当前坐标已经访问过了，要把他标记为0，防止再次访问
    grid[x][y] = 0;
    //然后沿着当前坐标的上下左右4个方向查找
    int up = dfs(grid, x, y - 1);//往上找
    int down = dfs(grid, x, y + 1);//往下找
    int left = dfs(grid, x - 1, y);//往左找
    int right = dfs(grid, x + 1, y);//往右找
    //这里只要4个方向的最大值即可
    int max = Math.max(left, Math.max(right, Math.max(up, down)));
    //然后再把当前位置的值还原
    grid[x][y] = temp;
    //返回最大路径值
    return grid[x][y] + max;
}
```

<br>

**总结**

这题需要遍历所有的位置，然后以他为中心点往他的上下左右4个方向查找，最后返回找到的最大值即可。如果遍历到某个位置的时候先要把它置为0，表示已经访问过了，当最后访问完之后还要把它复原。

<br>

![](https://img-blog.csdnimg.cn/20200807155236311.png)