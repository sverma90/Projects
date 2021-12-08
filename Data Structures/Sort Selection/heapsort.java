/**
 * Sort Comparison
 * heapsort.java
 * @version    1.0     2021-12-07
 * @author     Soumil Verma
 * Heap Sort class that includes all methods to process inputs
 * @return ordered/reversed/random sorted inputs for heap sort algo
 */
public class heapsort {
    //Global variable counters
    public static int numComparison;
    public static int numExchange;
    /**
     * Recursive heap sort logic
     *
     * @param array input array
     */
    public static void recursiveHeapSort(int array[]) {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {                  // Build heap (rearrange array)
            heapify(array, n, i);
            numComparison++;
        }
        for (int i = n - 1; i > 0; i--) {                     // One by one extract an element from heap
            int temp = array[0];                              // Move current root to end
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);                           // call max heapify on the reduced heap
            numExchange++;
        }
    }
    /**
     * Heap sort logic, heapify
     * To heapify a subtree rooted with node i,
     * which is an index in arr[]. n is size of heap
     *
     * @param array input array
     */
    public static void heapify(int array[], int n, int i) {
        int largest = i;                                        // Initialize largest as root
        int l = 2 * i + 1;                                      // left index = 2*i + 1
        int r = 2 * i + 2;                                      // right index = 2*i + 2
        if (l < n && array[l] > array[largest]) {               // If left child is larger than root
            largest = l;
            numComparison++;
        }
        if (r < n && array[r] > array[largest]) {                // If right child is larger than largest so far
            largest = r;
            numComparison++;
        }
        if (largest != i) {                                     // If largest is not root
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(array, n, largest);                          // Recursively heapify the affected sub-tree
            numExchange++;
        }
    }
}