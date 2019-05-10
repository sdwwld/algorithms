package com.wld.java.leetcode;

public class leet427 {

    public static void main(String args[]) {
        System.out.println();

    }

    private class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

//    public Node construct(int[][] g) {
//        return build(0, 0, g.length - 1, g.length - 1, g);
//    }
//
//    Node build(int r1, int c1, int r2, int c2, int[][] g) {
//        if (r1 > r2 || c1 > c2) return null;
//        boolean isLeaf = true;
//        int val = g[r1][c1];
//        for (int i = r1; i <= r2; i++)
//            for (int j = c1; j <= c2; j++)
//                if (g[i][j] != val) {
//                    isLeaf = false;
//                    break;
//                }
//        if (isLeaf)
//            return new Node(val == 1, true, null, null, null, null);
//        int rowMid = (r1 + r2) / 2, colMid = (c1 + c2) / 2;
//        return new Node(false, false,
//                build(r1, c1, rowMid, colMid, g),//top left
//                build(r1, colMid + 1, rowMid, c2, g),//top right
//                build(rowMid + 1, c1, r2, colMid, g),//bottom left
//                build(rowMid + 1, colMid + 1, r2, c2, g));//bottom right
//    }


//    public Node construct(int[][] grid) {
//        return helper(grid, 0, 0, grid.length);
//    }
//
//    private Node helper(int[][] grid, int x, int y, int len) {
//        if (len == 1) {
//            return new Node(grid[x][y] != 0, true, null, null, null, null);
//        }
//        Node result = new Node();
//        Node topLeft = helper(grid, x, y, len / 2);
//        Node topRight = helper(grid, x, y + len / 2, len / 2);
//        Node bottomLeft = helper(grid, x + len / 2, y, len / 2);
//        Node bottomRight = helper(grid, x + len / 2, y + len / 2, len / 2);
//        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
//                && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
//            result.isLeaf = true;
//            result.val = topLeft.val;
//        } else {
//            result.topLeft = topLeft;
//            result.topRight = topRight;
//            result.bottomLeft = bottomLeft;
//            result.bottomRight = bottomRight;
//        }
//        return result;
//    }


//    public Node construct(int[][] grid) {
//        return helper(grid, 0, 0, grid.length);
//    }
//    private Node helper(int[][] grid, int x, int y, int len) {
//        if(len == 1) return new Node(grid[x][y] == 1, true, null, null, null, null);
//
//        Node nodeTL = helper(grid, x, y, len/2);
//        Node nodeTR = helper(grid, x, y+len/2, len/2);
//        Node nodeBL = helper(grid, x+len/2, y, len/2);
//        Node nodeBR = helper(grid, x+len/2, y+len/2, len/2);
//        // Merge all child nodes
//        if(nodeTL.isLeaf && nodeTR.isLeaf && nodeBL.isLeaf && nodeBR.isLeaf) {
//            if(nodeTL.val && nodeTR.val && nodeBL.val && nodeBR.val) return new Node(true, true, null, null, null, null);
//            if(!nodeTL.val && !nodeTR.val && !nodeBL.val && !nodeBR.val)  return new Node(false, true, null, null, null, null);
//        }
//        return new Node(true, false, nodeTL, nodeTR, nodeBL, nodeBR);
//    }


    public Node construct(int[][] grid) {
        return recursiveConstruct(grid, 0, grid.length, 0, grid[0].length);
    }

    public Node recursiveConstruct(int[][] grid, int xStart, int xEnd, int yStart, int yEnd) {
        if (xStart == xEnd - 1 || yStart == yEnd - 1)
            return new Node(grid[xStart][yStart] == 1, true, null, null, null, null);

        int xMiddle = (xStart + xEnd) / 2;
        int yMiddle = (yStart + yEnd) / 2;

        Node topLeft = recursiveConstruct(grid, xStart, xMiddle, yStart, yMiddle);
        Node bottomLeft = recursiveConstruct(grid, xMiddle, xEnd, yStart, yMiddle);
        Node topRight = recursiveConstruct(grid, xStart, xMiddle, yMiddle, yEnd);
        Node bottomRight = recursiveConstruct(grid, xMiddle, xEnd, yMiddle, yEnd);

        if (topLeft.isLeaf && bottomLeft.isLeaf && topRight.isLeaf && bottomRight.isLeaf &&
                topLeft.val == bottomLeft.val && topRight.val == bottomRight.val && topLeft.val == topRight.val) {
            return new Node(grid[xStart][yStart] == 1, true, null, null, null, null);
        }
        return new Node(grid[xStart][yStart] == 1, false, topLeft, topRight, bottomLeft, bottomRight);
    }


}