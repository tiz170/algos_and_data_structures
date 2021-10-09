import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 1; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }
    }

    class Thread {
        int id;
        long nextStartTime;
    }

    class ThreadComparator implements Comparator<Thread> {
        @Override
        public int compare(Thread o1, Thread o2) {
            if (o1.nextStartTime < o2.nextStartTime) {
                // if o1 < o2 then return -1 this is following the natural order
                // for priority queue, its natural order is min heap
                // so this means a min heap
                return -1;
            }
            else if (o1.nextStartTime == o2.nextStartTime) {
                if (o1.id < o2.id)
                    return -1;
                else
                    return 1;
            } else {
                return 1;
            }
        }
    }

    private void assignJobsFast() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length]; // for integer array all elements will be zeros initially
        startTime = new long[jobs.length];
        PriorityQueue<Thread> pq = new PriorityQueue<Thread>(numWorkers, new ThreadComparator());

        for (int i = 0;i < numWorkers;i++) {
            Thread thread = new Thread();
            thread.id = i;
            thread.nextStartTime = 0;
            pq.add(thread);
        }

        // O(m*logn)
        for (int i = 0; i < jobs.length; i++) { // m
            int duration = jobs[i]; // O(1)
            Thread earliestCompleteThread = pq.poll(); // O(logn)

            assignedWorker[i] = earliestCompleteThread.id; // O(1)
            startTime[i] = earliestCompleteThread.nextStartTime; // O(1)
            earliestCompleteThread.nextStartTime += duration; // O(1)

            pq.add(earliestCompleteThread); // O(logn)
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
//        assignJobs();
        assignJobsFast();
        writeResponse();
        out.close();
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
