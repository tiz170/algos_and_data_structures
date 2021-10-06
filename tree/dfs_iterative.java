/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        if (root == null) return 0;
        
        return DFS(root, 1);
    }
    
    public int DFS(Node root, int curHeight) {
        if (root.children == null || root.children.isEmpty()) return curHeight;
        
        int maxHeight = 0;
        for (Node child : root.children) {
            int thisHeight = DFS(child, curHeight+1);
            maxHeight = thisHeight > maxHeight ? thisHeight : maxHeight;
        }
        
        return maxHeight;
    }
}