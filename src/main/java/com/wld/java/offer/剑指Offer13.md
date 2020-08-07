## [剑指 Offer 13. 机器人的运动范围](https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/)（中等）

地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？

<br/>

**示例 1：**

```
输入：m = 2, n = 3, k = 1
输出：3
```

**示例 2：**

```
输入：m = 3, n = 1, k = 0
输出：1
```

**提示：**

- `1 <= n,m <= 100`
- `0 <= k <= 20`

<br/>

### 答案：

#### 1，DFS（深度优先搜索）

这道题说的是一个机器人从左上角开始，他可以沿着上下左右四个方向走，并且走到的每个格子坐标的数字和不大于k，问可以走多少个格子。我们先来画个图看一下

![image.png](https://pic.leetcode-cn.com/9f8192e4ceb4bfb40c5ed22c1a036d65138456e8f44c1b7dd95bcc91457b6483-image.png)

这里统计的是能走多少个格子，所以统计肯定是不能有重复的，题中说了，机器人是可以沿着上下左右四个方向走的。但你想一下，任何一个格子你从任何一个方向进来（比如从上面进来），那么他只能往其他3个方向走，因为如果在往回走就重复了。但实际上我们只要沿着两个方向走就可以了，一个是右边，一个是下边，也就是上面图中红色的箭头。我们来看下代码

```java
public int movingCount(int m, int n, int k) {
    //临时变量visited记录格子是否被访问过
    boolean[][] visited = new boolean[m][n];
    return dfs(0, 0, m, n, k, visited);
}

public int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
    //i >= m || j >= n是边界条件的判断，k < sum(i, j)判断当前格子坐标是否
    // 满足条件，visited[i][j]判断这个格子是否被访问过
    if (i >= m || j >= n || k < sum(i, j) || visited[i][j])
        return 0;
    //标注这个格子被访问过
    visited[i][j] = true;
    //沿着当前格子的右边和下边继续访问
    return 1 + dfs(i + 1, j, m, n, k, visited) + dfs(i, j + 1, m, n, k, visited);
}

//计算两个坐标数字的和
private int sum(int i, int j) {
    int sum = 0;
    while (i != 0) {
        sum += i % 10;
        i /= 10;
    }
    while (j != 0) {
        sum += j % 10;
        j /= 10;
    }
    return sum;
}
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/16be6264bd5095017cb49f7d28a0e9b3463691bd41ff5f937439cb32ab636a31-image.png)



#### 2，BFS（广度优先搜索）

DFS是沿着一个方向一直往下走，有一种不撞南墙不回头的感觉，直到不满足条件才会回头。而BFS就显得有点博爱了，他不是一条道走下去，他会把离他最近的都访问一遍，访问完之后才开始访问第二近的……，一直这样下去，所以最好的一种数据结构就是使用队列，因为队列是先进先出，离他最近的访问完之后加入到队列中，最先入队的也是最先出队的。如下图，DFS就是沿着一条道走下去，然后再走其他的道……。BFS就是图中先访问圈内的部分，然后再把圈放大继续访问……。

![image.png](https://pic.leetcode-cn.com/370a2d1208674c5aa084250b6cf0142913ddeade52f9e35fed4f69d7d363ee6b-image.png)

代码和上面有很多相似的地方，基本上没什么难度，来看下

```java
public int movingCount(int m, int n, int k) {
    //临时变量visited记录格子是否被访问过
    boolean[][] visited = new boolean[m][n];
    int res = 0;
    //创建一个队列，保存的是访问到的格子坐标，是个二维数组
    Queue<int[]> queue = new LinkedList<>();
    //从左上角坐标[0,0]点开始访问，add方法表示把坐标
    // 点加入到队列的队尾
    queue.add(new int[]{0, 0});
    while (queue.size() > 0) {
        //这里的poll()函数表示的是移除队列头部元素，因为队列
        // 是先进先出，从尾部添加，从头部移除
        int[] x = queue.poll();
        int i = x[0], j = x[1];
        //i >= m || j >= n是边界条件的判断，k < sum(i, j)判断当前格子坐标是否
        // 满足条件，visited[i][j]判断这个格子是否被访问过
        if (i >= m || j >= n || k < sum(i, j) || visited[i][j])
            continue;
        //标注这个格子被访问过
        visited[i][j] = true;
        res++;
        //把当前格子右边格子的坐标加入到队列中
        queue.add(new int[]{i + 1, j});
        //把当前格子下边格子的坐标加入到队列中
        queue.add(new int[]{i, j + 1});
    }
    return res;
}

//计算两个坐标数字的和
private int sum(int i, int j) {
    int sum = 0;
    while (i != 0) {
        sum += i % 10;
        i /= 10;
    }
    while (j != 0) {
        sum += j % 10;
        j /= 10;
    }
    return sum;
}
```

![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（12. 矩阵中的路径）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer12.md)

#### [下一题（14. 剪绳子-I）(中等)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer14-I.md)