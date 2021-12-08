/**
 * Sort Comparison
 * mergesort.java
 * @version    1.0     2021-12-07
 * @author     Soumil Verma
 * Merge Sort class that includes all methods to process inputs
 * This is for ENHANCEMENT work
 * @return ordered/reversed/random sorted inputs for merge sort algo
 */
public class mergesort {
    //Global variable counters
    public static int numComparison;
    public static int numExchange;
    /**
     * Merge sort method to merge to sub-arrays
     * first array is from left to middle point
     * second array is form middle point + 1 to right
     *
     * @param array input array
     * @param left left index of the array
     * @param right right index of the array
     * @param leftIndex left element on the left side of the array
     * @param rightIndex right element on the right side of the array
     */
    public static void merge(int[] array, int[] left, int[] right, int leftIndex, int rightIndex) {

        int i = 0, j = 0, k = 0;
        while (i < leftIndex && j < rightIndex) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
                numComparison++;
            }
            else {
                array[k++] = right[j++];
                numComparison++;
            }
            numComparison++;
            numExchange++;
        }
        while (i < leftIndex) {
            array[k++] = left[i++];
            numComparison++;
            numExchange++;
        }
        while (j < rightIndex) {
            array[k++] = right[j++];
            numComparison++;
            numExchange++;
        }
    }
    /**
     * Merge sort method performs sort
     *
     * @param array input array
     * @param var left index of the array
     */
    public static void mergeSort(int[] array, int var) {
        if (var < 2) { return; }
        int mid = var / 2;
        int[] l = new int[mid];
        int[] r = new int[var - mid];
        for (int i = 0; i < mid; i++) {
            l[i] = array[i];
            numExchange++;
        }
        for (int i = mid; i < var; i++) {
            r[i - mid] = array[i];
            numExchange++;
        }
        mergeSort(l, mid);
        mergeSort(r, var - mid);
        merge(array, l, r, mid, var - mid);
    }
}