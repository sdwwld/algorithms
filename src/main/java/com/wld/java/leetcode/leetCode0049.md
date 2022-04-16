## [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)（中等）

给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

**示例:**

```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

**说明：**

- 所有输入均为小写字母。
- 不考虑答案输出的顺序。

<br>

### 答案：

#### 1，先排序再判断

字母异位词就是两个字符串中的字母都是一样的，只不过顺序被打乱了，这里要把他们找出来，然后放到一起。既然字母异位词的字母都是一样的，可以对字符串中的字符进行排序，生成一个新的字符串，如果生成新的字符串相同，那么他们就是字母异位词。代码比较简单，来看下

```java
public List<List<String>> groupAnagrams(String[] strs) {
    //边界条件判断
    if (strs == null || strs.length == 0)
        return new ArrayList<>();
    //map中key存储的是字符串中字母排序后新的字符串
    Map<String, List<String>> map = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
        //取出字符串，然后把它转化为字符数组
        char[] c = strs[i].toCharArray();
        //对字符数组进行排序
        Arrays.sort(c);
        //排序之后再把它转化为一个字符串
        String keyStr = String.valueOf(c);
        //判断map中有没有这个字符串，如果没有这个字符串，
        //说明还没有出现和这个字符串一样的字母异位词，
        //要新建一个list，把它存放到map中
        if (!map.containsKey(keyStr))
            map.put(keyStr, new ArrayList<>());
        //把字符串存放到对应的list中
        map.get(keyStr).add(strs[i]);
    }
    //最后返回
    return new ArrayList<>(map.values());
}
```

<br>

#### 2，统计每个字母的个数

题目中说了所有输入均为小写字母，所以还可以只用一个数组，统计字符串中每个字符的个数，最终会生成一个新的字符串，如果生成新的字符串相同，说明他们是字母异位词，画个图看一下

![](https://raw.githubusercontent.com/sdwwld/algorithms/master/img/leetcode/0049/640.png)

可以看到，只要是字母异位词，通过上面的方式转换，他们生成的字符串都是一样的

```java
public List<List<String>> groupAnagrams(String[] strs) {
    //边界条件判断
    if (strs == null || strs.length == 0)
        return new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    for (String s : strs) {
        char[] ca = new char[26];
        //统计字符串中每个字符串出现的次数
        for (char c : s.toCharArray())
            ca[c - 'a']++;
        //统计每个字符出现次数的数组转化为字符串
        String keyStr = String.valueOf(ca);
        if (!map.containsKey(keyStr))
            map.put(keyStr, new ArrayList<>());
        map.get(keyStr).add(s);
    }
    return new ArrayList<>(map.values());
}
```

<br>

#### 3，总结

由相同的字母组成的字符串才是字母异位词，既然有相同的字母，直接把他们排序是最容易想到的，所以第一种方式一般都能想到。第二种方式通过统计每个字符出现的次数，然后再把统计的结果转化为字符串也是可以实现的。



![](https://img-blog.csdnimg.cn/20200807155236311.png)

