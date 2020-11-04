## [695. 岛屿的最大面积](https://leetcode-cn.com/problems/max-area-of-island/)（中等）

给定一个包含了一些 0 和 1 的非空二维数组 grid 。

一个 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在水平或者竖直方向上相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为 0 。)

 <br/>

**示例 1:**

```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
```

对于上面这个给定矩阵应返回 `6`。注意答案不应该是 `11` ，因为岛屿只能包含水平或垂直的四个方向的 `1` 。

**示例 2:**

```
[[0,0,0,0,0,0,0,0]]
```

对于上面这个给定的矩阵, 返回 `0`。

<br/>

**注意:** 给定的矩阵`grid` 的长度和宽度都不超过 50。

<br/>

### 答案：

#### 1，DFS解决

这题无论使用DFS还是BFS都很好解决，DFS就是沿着一个方向一直走下去，直到不满足条件为止（要么走出grid的边缘，要么当前位置是0），就像下面这样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0695/640.png)

代码如下

```java
public int maxAreaOfIsland(int[][] grid) {
    int maxArea = 0;
    for (int i = 0; i < grid.length; i++)
        for (int j = 0; j < grid[0].length; j++)
            if (grid[i][j] == 1) {//如果当前位置是1，开始计算
                maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
    return maxArea;
}

public int dfs(int[][] grid, int i, int j) {
    //边界条件的判断
    if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
        //当前位置如果是1，为了防止重复计算就把他置为0，然后再从他的上下左右四个方向开始查找
        grid[i][j] = 0;
        return 1 + dfs(grid, i + 1, j) + dfs(grid, i - 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);
    }
    return 0;
}
```

<br>

#### 2，BFS解决

BFS我们可以使用一个队列来实现，他的实现原理就是如果一个位置是1，我们就把他上下左右为1的点的坐标全部加入到队列中，然后改变当前位置的坐标为0，防止重复计算。加入队列之后再一个个出队，然后再以出队的那个点重复上面的操作……，直到队列为空为止。就像下面这样，假如遍历到红色的1，我们就把他上下左右为1的位置坐标全部加入到队列中。

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0695/641.png)

```java
public int maxAreaOfIsland(int[][] grid) {
    int maxArea = 0;
    for (int i = 0; i < grid.length; i++)
        for (int j = 0; j < grid[0].length; j++)
            if (grid[i][j] == 1) {//如果当前位置是1，开始计算
                maxArea = Math.max(maxArea, bfs(grid, i, j));
            }
    return maxArea;
}

public int bfs(int[][] grid, int i, int j) {
    int m = grid.length, n = grid[0].length;
    if (grid[i][j] == 0)
        return 0;
    grid[i][j] = 0;
    //队列中存储的是个二维数组，这个二维数组就是格子的坐标
    Queue<int[]> queue = new LinkedList<>();
    //offer表示添加到队列的末尾
    queue.offer(new int[]{i, j});
    //分别表示右，左，下，上，四个方向
    int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int res = 1;
    while (!queue.isEmpty()) {
        //poll表示从队列的头部移除一个元素
        int[] pos = queue.poll();
        //然后从pos坐标的4个方向再分别查找
        for (int[] dir : dirs) {
            int x = dir[0] + pos[0];
            int y = dir[1] + pos[1];
            //边界条件的判断
            if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                continue;
            }
            grid[x][y] = 0;
            res++;
            queue.offer(new int[]{x, y});
        }
    }
    return res;
}
```

<br>

#### 3，总结

如果对图的遍历比较了解的话，这两种方式很容易想到，一个是沿着一个方向一直走下去，一个就像波浪一样，沿着一个点然后往四周一圈一圈的发散。

<br>



 ![](https://img-blog.csdnimg.cn/20200807155236311.png)

####   
