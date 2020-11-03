## [130. 被围绕的区域](https://leetcode-cn.com/problems/surrounded-regions/)（中等）

给定一个二维的矩阵，包含 `'X'` 和 `'O'`（**字母 O**）。

找到所有被 `'X'` 围绕的区域，并将这些区域里所有的 `'O'` 用 `'X'` 填充。

**示例:**

```
X X X X
X O O X
X X O X
X O X X
```

运行你的函数后，矩阵变为：

```
X X X X
X X X X
X X X X
X O X X
```

**解释:**

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

<br/>

### 答案：

#### 1，DFS解决

这题说的是如果被X围绕的区域有O，就用X把这个区域内的O给替换掉。那么我们怎么判断O是被X包围的呢，直接判断可能不太好操作，我们可以换种思路。**如果矩阵的四周都是X，那么矩阵中只要有O，肯定是被X包围的**，这个很好理解，就像下面这样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0130/640.png)

如果矩阵的四周只要有一个是O，那么和这个O挨着（挨着仅指上下左右，斜对角不算）的O都不可能被X包围，比如下面这样

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0130/641.png)

所以一种比较简单的判断方式就是查找这个矩阵的四周，查看有没有O，如果有O，说明他不能被X给包围，也就是不能被替换成X，我们先把他变为大写的A（其他值也可以，只要不是X和O就行），然后再遍历他的上下左右查看有没有O，如果有O，那么这个O也是不能被替换成X的，也要被标记为A……。

<br>

最后矩阵中最多会有3种状态，一种是X，一种是A，一种是O。

<br>

- 如果是X不用动，

- 如果是O，说明他是被X包围的，需要把它替换成X。

- 如果是A说明是不能被X包围的，还要把它还原为O。

  

<br>

我们随便举个找个数据画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0130/642.png)

弄懂了上面的过程，代码就容易多了

```java
public void solve(char[][] board) {
    //边界条件判断
    if (board == null || board.length == 0)
        return;
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            //从矩阵的四周开始判断，也就是矩阵的4条边上有O的地方开始遍历
            if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                if (board[i][j] == 'O')
                    dfs(i, j, board);
            }
        }
    }
    //重新复原
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            //把矩阵中是'A'的还变为O，其他的都变成X
            if (board[i][j] == 'A')
                board[i][j] = 'O';
            else
                board[i][j] = 'X';
        }
    }
    return;
}

private void dfs(int i, int j, char[][] board) {
    //边界条件判断，首先不能跑到矩阵的外边
    if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
        return;
    //如果当前位置不是O，就不用再判断了
    if (board[i][j] != 'O')
        return;
    //如果当前位置是O，先把他变为'A'，然后往他的上下左右4个方向开始递归计算。
    board[i][j] = 'A';
    dfs(i - 1, j, board);//上
    dfs(i + 1, j, board);//下
    dfs(i, j - 1, board);//左
    dfs(i, j + 1, board);//右
}
```

<br>

#### 2，BFS解决

DFS是沿着一个方向一直走下去，BFS是先遍历四周的，然后再往外扩散，如下图所示

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/common/643.png)

原理还是一样的，从矩阵的四周开始，找到一个O之后，把它变为A，然后把他的四周在遍历一遍，如果有O就加入到队列中，然后继续遍历队列中的元素……

```java
public void solve(char[][] board) {
    //边界条件判断
    if (board == null || board.length == 0)
        return;
    int rows = board.length, columns = board[0].length;
    for (int i = 0; i < rows; i++)
        for (int j = 0; j < columns; j++) {
            //从矩阵的四周开始判断，也就是矩阵的4条边上有O的地方开始遍历
            if (i == 0 || i == rows - 1 || j == 0 || j == columns - 1) {
                if (board[i][j] == 'O')
                    bfs(i, j, board);
            }
        }
    //重新复原
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            //把矩阵中是'A'的还变为O，其他的都变成X
            if (board[i][j] == 'A')
                board[i][j] = 'O';
            else
                board[i][j] = 'X';
        }
    }
}

int[][] direction = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

private void bfs(int i, int j, char[][] board) {
    Queue<Integer> queue = new LinkedList<>();
    //把当前位置变为A
    board[i][j] = 'A';
    //把当前的坐标加入到队列中
    queue.offer(i);
    queue.offer(j);
    while (!queue.isEmpty()) {
        //坐标出队
        int queueI = queue.poll();
        int queueJ = queue.poll();
        //沿着当前位置(queueI,queueJ)的上下左右四个方向查找
        for (int k = 0; k < 4; k++) {
            int x = direction[k][0] + queueI;
            int y = direction[k][1] + queueJ;
            //边界条件判断，首先不能跑到矩阵的外边
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length)
                continue;
            //如果当前位置不是O，就不用再判断了
            if (board[x][y] != 'O')
                continue;
            //否则就把他变为A
            board[x][y] = 'A';
            //然后再把这个位置的坐标存放到队列中
            queue.offer(x);
            queue.offer(y);
        }
    }
}
```

<br>

#### 3，总结

要想找到被X包围的区域，最简单的一种方式就是从四周开始找，因为如果四周有O，那么他们肯定是不能被包围的，如果还有和这个O挨着的，也是不能被包围的，否则剩下的如果有O，那么剩下的这些肯定是能被X包围的，理解这个思路很重要。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

