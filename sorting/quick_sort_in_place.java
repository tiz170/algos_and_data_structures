public static void quickSort(int[] arr) {
    qsort(arr, 0, arr.length-1);
}

private static void qsort(int[] arr, int left, int right) {
    if (left >= right) return;
    
    int pivot = partition(arr, left, right); //将数组分为两部分
    // 这里不用包括pivot因为partition后，pivot所处的位置就是最终排序后它的位置
    qsort(arr, left, pivot-1); //递归排序左子数组
    qsort(arr, pivot+1, right); //递归排序右子数组
}

// 对于right，如果其所指的元素的值大于或者等于基准值，那么right往左移一位，如果小于基准值，则和基准值交换；
// 同理，对于left，如果left所指元素的值小于或者等于基准值，那么left往右移一位，如果大于基准值，则和基准值交换。
// 从right开始执行，重复这两步骤，直至left == right为止。
// 对于基准的选取会影响算法的性能，这里取第一个元素为pivot。 
// 返回pivot的坐标
private static int partition(int[] arr, int left, int right) {
    int pivot = arr[left]; // 取最左端为pivot
    while (left < right) {
        while (left < right && arr[right] >= pivot) {
            right--;
        }
        // here: left == right || arr[right] < pivot
        // move element in the right part that's smaller to the left
        arr[left] = arr[right];
        while (left < right && arr[left] <= pivot) {
            left++;
        }
        // here: left >= right || arr[left] > pivot
        // move element in the left part that's larger to the right
        arr[right] = arr[left];
    }
    
    arr[left] = pivot; // 第一次low与high互换时，low（pivot）没有存下，这里归位
    //返回的是pivot的位置
    return left;
}