import java.util.*;
import java.io.*;

public class is_bst_hard {
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

        class ResultType {
          public boolean isBST;
          public Integer maxNodeIdx, minNodeIdx;
          public ResultType(boolean isBST) {
            this.isBST = isBST;
            this.maxNodeIdx = null;
            this.minNodeIdx = null;
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

        private ResultType divideConquer(Integer rootIdx) {
          if (rootIdx == null || rootIdx == -1) {
            return new ResultType(true);
          }

          ResultType left = divideConquer(tree[rootIdx].left);
          ResultType right = divideConquer(tree[rootIdx].right);
          if (!left.isBST || !right.isBST) {
            return new ResultType(false);
          }

          if (left.maxNodeIdx != null && tree[left.maxNodeIdx].key >= tree[rootIdx].key) {
            return new ResultType(false);
          }

          if (right.minNodeIdx != null && tree[right.minNodeIdx].key < tree[rootIdx].key) {
            return new ResultType(false);
          }

          // is bst
          ResultType result = new ResultType(true);
          result.minNodeIdx = left.minNodeIdx != null ? left.minNodeIdx : rootIdx;
          result.maxNodeIdx = right.maxNodeIdx != null ? right.maxNodeIdx : rootIdx;

          return result;
        }

        boolean isBinarySearchTree() {
          // sanity check
          if (nodes == 0 )
            return true;

          // Implement correct algorithm here
          return divideConquer(0).isBST;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst_hard().run();
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
