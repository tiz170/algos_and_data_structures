public static void mergerSortNR(int[] tar) throws InterruptedException {
      int N = tar.length;

      for (int n = 1; n < N; n = 2*n) { //以N = 8为例，n分别为1, 2，4
     // n代表每次divide的步长的一半（即mid = low + n -1)
       // 只循环log2N次 + 1：长度为8的数组，循环4次
       // 先以2为单位进行merge；再以4；再以8（都是从零开始）
       // 与recursion版的顺序不完全一致：
       // recursion版本是：1，1，2，1，1，2，2，2，4（代表每次array的个数）
       // BU版本是：1,1,1,1,2,2,4
          for (int i = 0; i < N-n; i += 2*n) { // n = 1, N = 8, i分别为0, 2, 4, 6 （规定了每轮的小array的个数）
              int lo = i;
              int mid  = i+n-1;
//                  int hi = i+n+n-1;
              int hi = Math.min(i+n+n-1, N-1); //当N为奇数是，n为最大值时，i+n+n-1会大于N-1
              merge(tar, lo, mid, hi);
          }
      }
}

public static void merge (int[] A,int p, int m, int q) throws InterruptedException {
    // merge sections A[p..p+l-1] and A[p+l..min(A.length,p+2*l)-1]
   
    int r = q-m; // length of R
   
    if(q-m>0){
        int i;     // index for A
        int j=0;   // index for L = A[p..m] 
        int k=0;   // index for R = A[m+1..q]
       
        int[] L = new int[m - p + 1];
        int[] R = new int[r];
        for(i=p;i<=m;i++){ L[i-p]  =A[i];}
        for(   ;i<=q;i++){ R[i-m-1]=A[i];} // q已经减了1
        for(i=p;i<=q;i++){
            if ( !(k<r) || (j<=m-p && L[j]<=R[k])) {              
                A[i]=L[j];
                j++;
            }else{ 
                A[i]=R[k];
                k++;
            }
        } 
    }
}