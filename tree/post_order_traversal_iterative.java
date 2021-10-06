public List<Integer> postorderTraversal(TreeNode root) {
    List<Integer> res = new LinkedList<>();
    // sanity check
    if (root == null) return res;
    TreeNode pre = null;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pop();
        if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
            res.add(cur.val);
            pre = cur;
        }
        else {
            stack.push(cur); // push back
            // right gets pushed first so that left will be visited first
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
    }
    return res;
}