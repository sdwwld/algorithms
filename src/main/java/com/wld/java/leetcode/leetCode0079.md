## [79. 单词搜索](https://leetcode-cn.com/problems/word-search/)（中等）

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

<br/>

**示例:**

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
```

**提示：**

- board 和 word 中只包含大写和小写英文字母。
- 1 <= board.length <= 200
- 1 <= board[i].length <= 200
- 1 <= word.length <= 10^3

<br/>

### 答案：

#### 1，回溯算法求解

回溯算法实际上一个类似枚举的搜索尝试过程，也就是一个个去试，我们解这道题也是通过一个个去试，下面就用示例1来画个图看一下

![image.png](https://pic.leetcode-cn.com/b13c34a26060e7eea8ba5001928bcf6972abc65df05eca3b5a29e5fc483b9a94-image.png)

我们看到他是从矩形中的一个点开始往他的上下左右四个方向查找，这个点可以是矩形中的任何一个点，所以代码的大致轮廓我们应该能写出来，就是遍历矩形所有的点，然后从这个点开始往他的4个方向走，因为是二维数组，所以有两个for循环，代码如下

```java
public boolean exist(char[][] board, String word) {
    char[] words = word.toCharArray();
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            //从[i,j]这个坐标开始查找
            if (dfs(board, words, i, j, 0))
                return true;
        }
    }
    return false;
}
```

这里关键代码是dfs这个函数，因为每一个点我们都可以往他的4个方向查找，所以我们可以把它想象为一棵4叉树，就是每个节点有4个子节点，而树的遍历我们最容易想到的就是递归，我们来大概看一下

```java
boolean dfs(char[][] board, char[] word, int i, int j, int index) {
    if (边界条件的判断) {
        return;
    }

    一些逻辑处理

    boolean res;
    //往右
    res = dfs(board, word, i + 1, j, index + 1)
    //往左
    res |= dfs(board, word, i - 1, j, index + 1)
    //往下
    res |= dfs(board, word, i, j + 1, index + 1)
    //往上
    res |= dfs(board, word, i, j - 1, index + 1)
    //上面4个方向，只要有一个能查找到，就返回true；
    return res;
}
```

最终的完整代码如下

```java
public boolean exist(char[][] board, String word) {
    char[] words = word.toCharArray();
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            //从[i,j]这个坐标开始查找
            if (dfs(board, words, i, j, 0))
                return true;
        }
    }
    return false;
}

boolean dfs(char[][] board, char[] word, int i, int j, int index) {
    //边界的判断，如果越界直接返回false。index表示的是查找到字符串word的第几个字符，
    //如果这个字符不等于board[i][j]，说明验证这个坐标路径是走不通的，直接返回false
    if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[index])
        return false;
    //如果word的每个字符都查找完了，直接返回true
    if (index == word.length - 1)
        return true;
    //为了防止分支污染，把board数组复制一份
    char[][] newArra = copyArray(board);
    //把newArra[i][j]置为特殊符号，表示已经被使用过了(注意：word中不能包含'.')
    newArra[i][j] = '.';
    //从当前坐标的上下左右四个方向查找
    boolean res = dfs(newArra, word, i + 1, j, index + 1) || dfs(newArra, word, i - 1, j, index + 1) ||
            dfs(newArra, word, i, j + 1, index + 1) || dfs(newArra, word, i, j - 1, index + 1);
    return res;
}

//复制一份新的数组
private char[][] copyArray(char[][] word) {
    char[][] newArray = new char[word.length][word[0].length];
    for (int i = 0; i < word.length; i++) {
        for (int j = 0; j < word[0].length; j++) {
            newArray[i][j] = word[i][j];
        }
    }
    return newArray;
}
```

这里在递归之前新建了一个数组，因为一般来说数组都是引用传递，当我们在一个分支修改了数组之后，其他分支上的数据也会改变，这也就造成了分支污染。所以在递归往下传递的时候我们都会新建一个数组，这样在当前分支的修改并不会影响到其他的分支，也就不会出错。

<br/>

这样虽然也能解决问题，但每次递归传递的时候都要创建一个新的数组，会造成大量的空间浪费，并且每次都创建也非常耗时，所以一般我们都不会使用上面的方式。我们会使用另外一个方法，也就是**回溯**。那么回溯又是如何解决这个问题的呢，要想弄懂回溯我们首先要搞懂递归，递归分为两步，先是递，然后才是归。当我们沿着当前坐标往下传递的时候，我们可以把当前坐标的值修改，然后回归到当前坐标的时候再把当前坐标的值复原，这就是回溯的过程。我们来看下代码，比上面简洁了好多，运行效率也会有很大的提升。

```java
public boolean exist(char[][] board, String word) {
    char[] words = word.toCharArray();
    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[0].length; j++) {
            //从[i,j]这个坐标开始查找
            if (dfs(board, words, i, j, 0))
                return true;
        }
    }
    return false;
}

boolean dfs(char[][] board, char[] word, int i, int j, int index) {
    //边界的判断，如果越界直接返回false。index表示的是查找到字符串word的第几个字符，
    //如果这个字符不等于board[i][j]，说明验证这个坐标路径是走不通的，直接返回false
    if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[index])
        return false;
    //如果word的每个字符都查找完了，直接返回true
    if (index == word.length - 1)
        return true;
    //把当前坐标的值保存下来，为了在最后复原
    char tmp = board[i][j];
    //然后修改当前坐标的值
    board[i][j] = '.';
    //走递归，沿着当前坐标的上下左右4个方向查找
    boolean res = dfs(board, word, i + 1, j, index + 1) || dfs(board, word, i - 1, j, index + 1) ||
            dfs(board, word, i, j + 1, index + 1) || dfs(board, word, i, j - 1, index + 1);
    //递归之后再把当前的坐标复原
    board[i][j] = tmp;
    return res;
}
```

参照：

[剑指 Offer 12. 矩阵中的路径](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer12.md)



![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### 