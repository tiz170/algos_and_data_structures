import java.util.*;
import java.io.*;

public class is_bst {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }

        boolean isValidBST() {
          // sanity check
          if (nodes == 0 )
            return true;

          Stack<Integer> stack = new Stack<>();
          Integer pre = null;
          int nextIdx = 0;
          while(nextIdx != -1 || !stack.empty()){
            while(nextIdx != -1){
              stack.push(nextIdx);
              nextIdx = tree[nextIdx].left;
            }

            nextIdx = stack.pop();
            if(pre != null) {
              if(tree[nextIdx].key <= pre)
                return false;
            }

            pre = tree[nextIdx].key;
            nextIdx = tree[nextIdx].right;
          }

          return true;
        }

        boolean isBinarySearchTree() {
          // Implement correct algorithm here
          return this.isValidBST();
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
}
