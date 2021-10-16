import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) {
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();

			inOrderHelper(result, 0);

			return result;
		}

		void inOrderHelper(List<Integer> res, int curIdx) {
			if (left[curIdx] != -1)
				inOrderHelper(res, left[curIdx]);

			res.add(key[curIdx]);

			if (right[curIdx] != -1)
				inOrderHelper(res, right[curIdx]);
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();

			preOrderHelper(result, 0);

			return result;
		}

		void preOrderHelper(List<Integer> res, int curIdx) {
			res.add(key[curIdx]);

			if (left[curIdx] != -1)
				preOrderHelper(res, left[curIdx]);

			if (right[curIdx] != -1)
				preOrderHelper(res, right[curIdx]);
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();

			postOrderHelper(result, 0);

			return result;
		}

		void postOrderHelper(List<Integer> res, int curIdx) {
			if (left[curIdx] != -1)
				postOrderHelper(res, left[curIdx]);

			if (right[curIdx] != -1)
				postOrderHelper(res, right[curIdx]);

			res.add(key[curIdx]);
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
