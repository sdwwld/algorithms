## [剑指 Offer 5. 替换空格](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/)（简单）

请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

<br/>

**示例 1：**

```
输入：s = "We are happy."
输出："We%20are%20happy."
```

<br/>

**限制：**

0 <= s 的长度 <= 10000

<br/>

### 答案：

#### 1，先把字符串转换为单个字符

这里让求的是把字符串中的空格替换成%20，其中一种实现方式就是申请一个临时数组，然后再遍历这个字符串的每个字符，如果不是空格就把遍历的字符添加到临时数组中，如果是空格就添加3个字符'%'，'2'，'0'分别到临时数组中，最后再把临时数组转化为字符串即可。

```java
    public String replaceSpace(String s) {
        int length = s.length();
        char[] array = new char[length * 3];
        int index = 0;
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                array[index++] = '%';
                array[index++] = '2';
                array[index++] = '0';
            } else {
                array[index++] = c;
            }
        }
        String newStr = new String(array, 0, index);
        return newStr;
    }
```

看一下运行结果

![image.png](https://pic.leetcode-cn.com/546fe254e065d2a62d2a0df0f87957221c7790b5e63e6f44e305240889007122-image.png)

<br/>

#### 2，使用StringBuilder

还有一种方式和上面差不多，就是把字符串中的每个字符一个个添加到StringBuilder中，如果遇到空格就把他换成%20。

```java
    public String replaceSpace(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ')
                stringBuilder.append("%20");
            else
                stringBuilder.append(s.charAt(i));
        }
        return stringBuilder.toString();
    }
```

再来看下运行结果

![image.png](https://pic.leetcode-cn.com/7467d3998c3bedf3247009d90ca559423c70a4ca6614354858ed78643aafb14f-image.png)

<br/>

#### 3，直接调用API

如果还想要更简单的，直接调用API，一行代码搞定

```java
    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }
```

运行结果如下

![image.png](https://pic.leetcode-cn.com/8691d2c76224e3ff0ad132dc15aced1d687087245cc148055843cf41867a825b-image.png)

![](https://img-blog.csdnimg.cn/20200807155236311.png)

#### [上一题（4. 二维数组中的查找）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer04.md)

#### [下一题（6. 从尾到头打印链表）(简单)](https://github.com/sdwwld/leetCode/blob/master/src/main/java/com/wld/java/offer/剑指Offer06.md)