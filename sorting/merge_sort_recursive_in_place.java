public void mergeSort(int[] A) {
    // to maintain consistent indexing, I make helper array have the same length of input array A
    // to avoid wasting space, I pass in helper array to the function rather than initializing it each time
    int[] helper = new int[A.length];
    mergeSortHelper(A, helper, 0, A.length-1); // A is sorted
}

public void mergeSortHelper (int[] A, int[] helper, int start, int end) {
    if (start >= end) return;

    int mid = start + (end - start) / 2;
    mergeSortHelper(A, helper, start, mid);
    // A[start, mid] is sorted
    mergeSortHelper(A, helper, mid + 1, end);
    // A[mid+1, end] is sorted
    merge(A, helper, start, end);
}

public void merge (int[] A, int[] helper, int start, int end) {
    int mid = start + (end - start) / 2;
    int leftIndex = start;
    int rightIndex = mid + 1;
    int cur = start;
    // A[start, mid] is sorted, A[mid+1, end] is sorted
    // merge two sorted subarray to A[start, end]
    while(leftIndex <= mid && rightIndex <= end) {
        if(A[leftIndex] <= A[rightIndex]) {
            helper[cur] = A[leftIndex];
            leftIndex++;
        }
        else {
            helper[cur] = A[rightIndex];
            rightIndex++;
        }
        cur++;
    } 
    
    while (leftIndex <= mid) {
        helper[cur] = A[leftIndex];
        cur++;
        leftIndex++;
    }
    
    while (rightIndex <= end) {
        helper[cur] = A[rightIndex];
        cur++;
        rightIndex++;
    }
    
    // copy back to A
    for (int i = start;i <= end;i++) {
        A[i] = helper[i];
    }
}