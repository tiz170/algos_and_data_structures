int binarySearchIter(int[] arr, int x) {
    int start = 0;
    int end = arr.length - 1;
    int mid;
    while(start + 1 < end) {
        mid = start + (end - start) / 2;
        if(arr[mid] < x) {
            start = mid;
        }
        else if(arr[mid] > x) {
            end = mid;
        }
        else {
			start = mid; // it can directly return if we only want any position of the target
        }
    }

	if (arr[start] == x) return start;
	if (arr[end] == x) return end;
	return -1;
}