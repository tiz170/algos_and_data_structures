import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BuildHeap {
    private Comparable[] data;
    private List<Swap> swaps;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new BuildHeap().solve();
    }

    private void readData() throws IOException {
        int n = in.nextInt();
        data = new Comparable[n];
        for (int i = 0; i < n; ++i) {
            data[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        out.println(swaps.size());
        for (Swap swap : swaps) {
            out.println(swap.index1 + " " + swap.index2);
        }
    }

    private void generateSwaps() {
        swaps = new ArrayList<Swap>();
        int n = data.length;
        for (int i = (n-1-1)/2; i >= 0; i--) {
            shiftDown (data, n, i);
        }
//        // The following naive implementation just sorts
//        // the given sequence using selection sort algorithm
//        // and saves the resulting sequence of swaps.
//        // This turns the given array into a heap,
//        // but in the worst case gives a quadratic number of swaps.
//        //
//        // TODO: replace by a more efficient implementation
//        for (int i = 0; i < data.length; ++i) {
//            for (int j = i + 1; j < data.length; ++j) {
//                if (data[i] > data[j]) {
//                    swaps.add(new Swap(i, j));
//                    int tmp = data[i];
//                    data[i] = data[j];
//                    data[j] = tmp;
//                }
//            }
//        }
    }

    private void swap(int i, int j) {
        swaps.add(new Swap(i, j));
        Comparable t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    private void shiftDown(Comparable[] data, int n, int k) {
        while (2 * k + 1 < n) {
            //左孩子节点
            int j = 2 * k + 1;
            //右孩子节点比左孩子节点大
            if (j + 1 < n && data[j + 1].compareTo(data[j]) < 0)
                j += 1;
            //比两孩子节点都大
            if (data[k].compareTo(data[j]) <= 0) break;
            //交换原节点和孩子节点的值
            swap(k, j);
            k = j;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        generateSwaps();
        writeResponse();
        out.close();
    }

    static class Swap {
        int index1;
        int index2;

        public Swap(int index1, int index2) {
            this.index1 = index1;
            this.index2 = index2;
        }
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

