public int BFS(Node root) {
  if(root == null) return 0;

  Queue<Node> queue = new LinkedList<>();
  queue.offer(root);

  int depth = 0;

  while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
          Node current = queue.poll();
          for(Node child: current.children) queue.offer(child);
      }

      depth++;
  }

  return depth;
}