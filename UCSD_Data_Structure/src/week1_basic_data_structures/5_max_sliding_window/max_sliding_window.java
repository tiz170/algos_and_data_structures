import java.util.ArrayDeque;
import java.util.Deque;

class max_sliding_window {
//     // 思路1：Brute Force
//     // time: O(nm)
//     public int[] maxSlidingWindow(int[] nums, int m) {
//         int n = nums.length;
//         int[] res = new int[n - m + 1];

//         for (int i = 0; i <= n - m; i++) {
//             //min integer
//             int max = Integer.MIN_VALUE;
//             for (int j = i; j < i + m; j++) {
//                 if (max < nums[j]) {
//                     max = nums[j];
//                 }
//             }

//             res[i] = max;
//         }
//         return res;
//     }

    // Brute-force解法：每次向右移动window一次，遍历当前window找到最大值 => Time complexity: (n-k+1) * T(k) => O(n*k) （对于n个数字，window宽度为k，一共要移动 n-k+1 次）
    // 优化目标： time complexity O(n)
    // 既然优化目标是O(n)，且显而易见该题目 time complexity 的lower bound（最优）是 O(n-k+1)（因为光window move就要遍历这么多次），那么每个window里找最大值这个操作必须是O(1)
    //
    // 思路：空间换时间，找到一种数据结构存max value candidates
    //
    // Background knowledge：解法中用到的deque全称为double-ended queue，双端队列，是一种具有队列和栈的性质的数据结构。
    // 既可以在队列头部插入（deque.addFirst(x)），头部删除（deque.removeFirst()），也可以尾部插入（deque.addLast(x)），尾部删除（deque.removeLast()）
    // 具体可查看API doc：https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
    //
    //
    // - 使用deque维护当前window中的candidate index（Q1：为什么这里存index，而不直接存value？），且保证：
    // 1. 队列中存的index从头至尾按照小到大排列
    // 2. store only relevant elements 且队首总是 best candidate index (index of the max value)
    //
    // 如何保证1：遍历的输入数组的时候，总是将当前符合条件的index candidate 加在队列的末尾
    // 如何保证2：before adding a new element drop all smaller elements
    // 因为存在特性：如果index i > j, 且a[i] > a[j]，那么即可舍弃a[j]，因为它永远不可能是best candidate（举几个例子就能intuitively理解）
    // 所以每次遇到比队尾大的数，就从尾至首，drop deque里所有比当前这个数小的candidate，然后将当前的index
    public int[] maxSlidingWindow(int[] a, int k) {
        // sanity check
        if (a == null || k <= 0) {
            return new int[0];
        }

        int n = a.length;
        int[] result = new int[n-k+1]; // 对于n个数字，window宽度为k，一共可以移动 n-k+1 次，所以result长度为n-k+1
        int resultIndex = 0;
        Deque<Integer> candidates = new ArrayDeque<>(); // used to store candidate index

        // 遍历数组
        for (int i = 0; i < a.length; i++) {
            // as candidates only store the candidates in current window so remove candidates out of current window
            while (!candidates.isEmpty() && candidates.peek() < i - k + 1) { // Q2: why i-k+1?
                candidates.removeFirst();
            }

            // remove smaller numbers in k range as they are useless
            while (!candidates.isEmpty() && a[candidates.peekLast()] < a[i]) {
                candidates.removeLast();
            }

            candidates.addLast(i); // 加在末尾
            if (i >= k - 1) {
                result[resultIndex] = a[candidates.peek()];
                resultIndex++;
            }
        }
        return result;
    }
}