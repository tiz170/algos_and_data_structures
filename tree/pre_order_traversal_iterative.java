/* 
二叉树的非递归前序遍历，前序遍历思想：先让根进栈，只要栈不为空，就可以做弹出操作， 
每次弹出一个结点，记得把它的左右结点都进栈，记得右子树先进栈，这样可以保证右子树在栈中总处于左子树的下面。 
*/  
public List<Integer> preorderTraversal(TreeNode root) {
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    List<Integer> res = new LinkedList<>();
    // sanity check
    if (root == null) return res;
    while (!stack.isEmpty()) {
        TreeNode cur = stack.pop();
        res.add(cur.val);
        if (cur.right != null)
            stack.push(cur.right);
        if (cur.left != null)
            stack.push(cur.left);
  }   
    return res;
}