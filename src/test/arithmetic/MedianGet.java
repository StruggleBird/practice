package test.arithmetic;

public class MedianGet {
    public MedianGet() {}

    public static void main(String[] args) {
        int[] a = new int[] {2, 4, 6};
        int[] b = new int[] {1, 3, 5};


        System.out.println("\r\n" + findMedianSortedArrays(a, a.length, b, b.length));
    }


    public static double findMedianSortedArrays(int[] a, int m, int[] b, int n) {

        int[] sumArr = new int[m + n];
        int posA = 0, posB = 0;
        for (int i = 0; i < sumArr.length; i++) {
            if (posA < m && posB < n) {
                if (a[posA] < b[posB]) {
                    sumArr[i] = a[posA++];
                } else {
                    sumArr[i] = b[posB++];
                }
            } else {
                sumArr[i] = (m >= n && posA < posB) ? a[posA++] : b[posB++];
            }

        }
        
        double median = (m + n) % 2 == 0 ? ((double)(sumArr[(m + n) / 2 - 1] + sumArr[(m + n) / 2]) / 2) : sumArr[(m + n) / 2];

        for (int i : sumArr) {
            System.out.print(i + "  ");
        }


        return median;
    }
}
