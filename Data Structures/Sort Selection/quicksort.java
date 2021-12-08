/**
 * Sort Comparison
 * quicksort.java
 * @version    1.0     2021-12-07
 * @author     Soumil Verma
 * Quick Sort class that includes 4 different methods and 2 separate partition methods
 * Program also includes insertion sort method below
 * @return ordered/reversed/random sorted inputs for quick sort algo
 */
public class quicksort {
    //Global variable counters
    public static int numComparison;
    public static int numExchange;
    /**
     * Recursive quick sort logic
     * Only sorts 100 integers using insertion sort
     *
     * @param array input array
     * @param startIndex start index of the array
     * @param endIndex end index of the array
     */
    public static void recursiveQuickSortto100(int array[], int startIndex, int endIndex) {
        int index = partition(array, startIndex, endIndex);
        if (startIndex < index - 1) {                               // Recursively call quicksort with left part of the partitioned array
            if(index - 1 - startIndex <= 100){
                insertionSort(array, startIndex, index - 1);
            }else{
                recursiveQuickSortto100(array, startIndex, index - 1);
            }
        }
        if (endIndex > index) {                                     // Recursively call quick sort with right part of the partitioned array
            if(endIndex - index <= 100){
                insertionSort(array, index, endIndex);
            }else{
                recursiveQuickSortto100(array, index, endIndex);
            }
        }
    }
    /**
     * Recursive quick sort logic
     * Only sorts 50 integers using insertion sort
     *
     * @param array input array
     * @param startIndex start index of the array
     * @param endIndex end index of the array
     */
    public static void recursiveQuickSortto50(int array[], int startIndex, int endIndex) {
        int index = partition(array, startIndex, endIndex);
        if (startIndex < index - 1) {                               // Recursively call quicksort with left part of the partitioned array
            if(index - 1 - startIndex <= 50){
                insertionSort(array, startIndex, index - 1);
            }else{
                recursiveQuickSortto50(array, startIndex, index - 1);
            }
        }
        if (endIndex > index) {                                     // Recursively call quick sort with right part of the partitioned array
            if(endIndex - index <= 50){
                insertionSort(array, index, endIndex);
            }else{
                recursiveQuickSortto50(array, index, endIndex);
            }
        }
    }
    /**
     * Recursive quick sort logic
     * Only sorts all regular integers
     *
     * @param array input array
     * @param startIndex start index of the array
     * @param endIndex end index of the array
     */
    public static void recursiveQuickSorttoRegular(int array[], int startIndex, int endIndex) {
        int index = partition(array, startIndex, endIndex);
        if (startIndex < index - 1) {                               // Recursively call quicksort with left part of the partitioned array
                recursiveQuickSorttoRegular(array, startIndex, index - 1);
        }
        if (endIndex > index) {                                     // Recursively call quick sort with right part of the partitioned array
                recursiveQuickSorttoRegular(array, index, endIndex);
        }
    }
    /**
     * Recursive quick sort logic Median of 3
     *
     * @param array input array
     * @param startIndex start index of the array
     * @param endIndex end index of the array
     */
    public static void recursiveQuickSorttoMedianOf3(int array[], int startIndex, int endIndex) {
        int index = partitionMedianOf3(array, startIndex, endIndex);
        if (startIndex < index - 1) {                               // Recursively call quicksort with left part of the partitioned array
            recursiveQuickSorttoMedianOf3(array, startIndex, index - 1);
        }
        if (endIndex > index) {                                     // Recursively call quick sort with right part of the partitioned array
            recursiveQuickSorttoMedianOf3(array, index, endIndex);
        }
    }
    /**
     * Separate partition method ONLY for median of 3 logic
     * Sorts array using insertion sort to determine the pivot
     *
     * @param array input array
     * @param left start index of the array
     * @param right end index of the array
     */
    public static int partitionMedianOf3(int array[], int left, int right) {
        int arrayMedian[] = {array[(left + right) / 2], array[left], array[right]};
        insertionSort(arrayMedian, 0, 2);

        int pivot = arrayMedian[1];                 // taking median of 3 element as pivot
        while (left <= right) {
            while (array[left] < pivot) {           //searching number which is greater than pivot, bottom up
                left++;
                numComparison++;
            }
            while (array[right] > pivot) {          //searching number which is less than pivot, top down
                right--;
                numComparison++;
            }
            if (left <= right) {                    // swap the values
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;                             //increment left index and decrement right index
                right--;
                numExchange++;
            }
        }
        return left;
    }
    /**
     * Partition logic divides an array from pivot point, to the left side,
     * which contains elements less than Pivot,
     * while right side contains elements greater than pivot.
     *
     * @param array array to partitioned
     * @param left lower bound of the array
     * @param right upper bound of the array
     * @return the partition index
     */
    public static int partition(int array[], int left, int right) {
        int pivot = array[left];                    // taking first element as pivot
        while (left <= right) {
            while (array[left] < pivot) {           //searching number which is greater than pivot, bottom up
                left++;
                numComparison++;
            }
            while (array[right] > pivot) {          //searching number which is less than pivot, top down
                right--;
                numComparison++;
            }
            if (left <= right) {                    // swap the values
                int tmp = array[left];
                array[left] = array[right];
                array[right] = tmp;
                left++;                             //increment left index and decrement right index
                right--;
                numExchange++;
            }
        }
        return left;
    }
    /**
     * insertion sort logic
     *
     * @param array array to partitioned
     * @param left lower bound of the array
     * @param right upper bound of the array
     * @return the partition index
     */
    public static void insertionSort(int array[], int left, int right){
        int n = array.length;
        for (int j = left+1; j < right+1; j++) {
            int key = array[j];
            int i = j-1;
            while ( (i > -1) && ( array [i] > key ) ) {
                array [i+1] = array [i];
                i--;
                numComparison++;
                numExchange++;
            }
            array[i+1] = key;
            numExchange++;
        }
    }
}