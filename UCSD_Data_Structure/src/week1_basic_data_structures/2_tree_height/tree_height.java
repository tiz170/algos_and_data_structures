import java.util.*;
import java.io.*;

public class tree_height {
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

	public class TreeNode {
		int val;
		//        ArrayList<TreeNode> is a type, children 是我们取的变量名
		ArrayList<TreeNode> children;
	}

	public class TreeHeight {
		int n;
		int parent[];

		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
			TreeNode[] nodes = new TreeNode[n];
			for (int i = 0; i < n; i++) {
//                46行：初始化一个实例，否则array里就是null
				nodes[i] = new TreeNode();
//                这题里index和值都是i
				nodes[i].val = i;
				nodes[i].children = new ArrayList<>();
			}

			TreeNode root = null;
			for (int i = 0; i < n; i++) {
				TreeNode childNode = nodes[i];
				int parentNodeIndex = parent[i];
				if (parentNodeIndex == -1) {
					root = childNode;
				}
				else {
					TreeNode parentNode = nodes[parentNodeIndex];
					parentNode.children.add(childNode);
				}

			}
			return getHeight(root);
		}

		public int getHeight(TreeNode root) {
			if (root == null) {
				return 0;
			} else {
//                for(TreeNode curNode : root.children) {
//                }
				int maxHeight = 0;
//                for loop for ArrayList or LinkedList, i < ArrayList.size(); array就用.length
				for (int i = 0;i < root.children.size();i++) {
					TreeNode curNode = root.children.get(i);
					int height = getHeight(curNode);
					if (height > maxHeight) {
						maxHeight = height;
					}
				}
				return maxHeight+1;
			}
		}

		int computeHeightBruteForce() {
			// Replace this code with a faster implementation
			int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
		}

	}

	static public void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				try {
					new tree_height().run();
				} catch (IOException e) {
				}
			}
		}, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
//        System.out.println(tree.computeHeightBruteForce());
		System.out.println(tree.computeHeight());
	}
}
