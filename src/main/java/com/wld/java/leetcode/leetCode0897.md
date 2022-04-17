## [897. 递增顺序搜索树](https://leetcode-cn.com/problems/convert-bst-to-greater-tree/)（简单）



**中序遍历的递归解决**

```java
    private TreeNode preNode;

    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode(0);
        preNode = dummyNode;
        inorder(root);
        return dummyNode.right;
    }

    public void inorder(TreeNode node) {
        if (node == null)
            return;
        inorder(node.left);

        //
        preNode.right = node;
        node.left = null;
        preNode = node;

        inorder(node.right);
    }
```

**中序遍历的非递归解决**

```java
    public TreeNode increasingBST(TreeNode root) {
        TreeNode dummyNode = new TreeNode();
        TreeNode preNode = dummyNode;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();

                preNode.right = root;
                preNode = root;
                preNode.left = null;
                
                root = root.right;
            }
        }
        return dummyNode.right;
    }
```

**Morris遍历**

```java
    public TreeNode increasingBST(TreeNode root) {
        TreeNode pre;
        TreeNode dummyNode = new TreeNode();
        TreeNode preNode = dummyNode;
        while (root != null) {
            pre = root.left;
            if (pre != null) {
                while (pre.right != null && pre.right != root) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = root;
                    root = root.left;
                    continue;
                } else {
                    root.left = null;
                }
            }

            preNode.right = root;
            preNode = root;
            root = root.right;
        }
        return dummyNode.right;
    }
```

**先通过中序遍历的方式获取所有节点的值，然后再创建树**

```java
    public TreeNode increasingBST(TreeNode root) {
        List<Integer> subList = new ArrayList<>();
        inorder(root, subList);

        TreeNode dummyNode = new TreeNode();
        TreeNode currNode = dummyNode;
        for (int val : subList) {
            currNode.right = new TreeNode(val);
            currNode = currNode.right;
        }
        return dummyNode.right;
    }

    public void inorder(TreeNode node, List<Integer> subList) {
        if (node == null)
            return;
        inorder(node.left, subList);
        subList.add(node.val);
        inorder(node.right, subList);
    }
```

