/**
 * Sort Comparison
 * Main.java
 * @version    1.0     2021-12-07
 * @author     Soumil Verma
 * Main class to run the program
 * includes calls to write the output to file
 * includes logic for insertion, quick, heap, and merge sort algorithms
 * includes Exchange Comparison processing code (This is for ENHANCEMENT work)
 * @return ordered/reversed/random sorted inputs
 */
import java.io.*;
import java.util.Scanner;
/**
 * Begins main class
 */
public class Main {
    public static void main(String[] args) throws IOException {
        RandomNumberGenerator numbers = new RandomNumberGenerator();
        int size[] = new int[] {50, 100, 500, 1000, 2000, 5000, 10000};
        String inputType[] = new String[] {"Ordered", "Reversed", "Random"};
        for(int currSize : size){
            for(String currType : inputType) {
                processInputs(currSize, currType);
            }
        }
        System.out.print("Program runtime complete.\n");
    }
    /**
     * Method that loads file, scanner, file writer and writes output to file
     *
     * @param size size of input (i.e. 50, 10000)
     * @param type ordered/reversed/random
     * @return Array of integers
     */
    public static int[] writeFunction(int size, String type) throws FileNotFoundException {
        int i = 0;
        int[] intArray = new int[size];
        //load file, scanner, file writer
        File file = new File("Input/inputTo" + size + type + ".txt");
        Scanner scan = new Scanner(file);
        //if file is empty, print message and end program.
        if(!scan.hasNextLine()){
            System.out.println("File is empty. Restart the program and try again.");
            System.exit(1);
        }
        //add int values to array
        while(scan.hasNextInt()){
            intArray[i] = scan.nextInt();
            i++;
        }
        scan.close();
        return intArray;
    }
    /**
     * processes inputs
     * calls recursive quick and heap sort algorithms
     * writes to file
     *
     * @param size size of input (i.e. 50, 10000)
     * @param type ordered/reversed/random
     * @return Array of integers
     */
    public static void processInputs(int size, String type) throws IOException {
        int intArray[];
        /**
         * Begins File Writer calls
         */
        FileWriter myWriterQuickSorttoRegular = new FileWriter("Output/Sort/Quick/Regular/outputTo" + size + type + ".txt", false);
        FileWriter myWriterQuickSortto50 = new FileWriter("Output/Sort/Quick/50_Integers/outputTo" + size + type + ".txt", false);
        FileWriter myWriterQuickSortto100 = new FileWriter("Output/Sort/Quick/100_Integers/outputTo" + size + type + ".txt", false);
        FileWriter myWriterQuickSorttoMedianOf3 = new FileWriter("Output/Sort/Quick/MedianOf3/outputTo" + size + type + ".txt", false);

        //file writer for heap and merge sort
        FileWriter myWriterHeapSort = new FileWriter("Output/Sort/Heap/outputTo" + size + type + ".txt", false);
        FileWriter myWriterMergeSort = new FileWriter("Output/Sort/Merge/outputTo" + size + type + ".txt", false);

        /**
         * Begins Exchange Comparison Output Code
         * This is for ENHANCEMENT work
         */

        //output for merge sort
        FileWriter myWriterMergeSortExchangeComparison = new FileWriter("Output/Exchange_Comparison/Merge/outputTo" + size + type + ".txt");

        //output for heap sort
        FileWriter myWriterHeapSortExchangeComparison = new FileWriter("Output/Exchange_Comparison/Heap/outputTo" + size + type + ".txt");

        //output for quick Sort
        FileWriter myWriterQuickSortRegularExchangeComparison = new FileWriter("Output/Exchange_Comparison/Quick/Regular/outputTo" + size + type + ".txt");
        FileWriter myWriterQuickSort50ExchangeComparison = new FileWriter("Output/Exchange_Comparison/Quick/50_Integers/outputTo" + size + type + ".txt");
        FileWriter myWriterQuickSort100ExchangeComparison = new FileWriter("Output/Exchange_Comparison/Quick/100_Integers/outputTo" + size + type + ".txt");
        FileWriter myWriterQuickSortMediumOf3ExchangeComparison = new FileWriter("Output/Exchange_Comparison/Quick/MedianOf3/outputTo" + size + type + ".txt");

        /**
         * Begins the following below:
         * - write to function Output Code with write to file
         * - write exchange comparison (ENHANCEMENT)
         * - reset counters (ENHANCEMENT)
         */

        //recursive quick sort regular
        intArray = writeFunction(size, type);
        quicksort.recursiveQuickSorttoRegular(intArray, 0, intArray.length - 1);
        writeToFile(intArray, myWriterQuickSorttoRegular);
        writemyWriterExchangeComparisonToFile(quicksort.numComparison, quicksort.numExchange, size, type, "Regular", myWriterQuickSortRegularExchangeComparison);
        quicksort.numComparison = 0;
        quicksort.numExchange = 0;

        //recursive quick sort to 50
        intArray = writeFunction(size, type);
        quicksort.recursiveQuickSortto50(intArray, 0, intArray.length - 1);
        writeToFile(intArray, myWriterQuickSortto50);
        writemyWriterExchangeComparisonToFile(quicksort.numComparison, quicksort.numExchange, size, type, "Sort to 50", myWriterQuickSort50ExchangeComparison);
        quicksort.numComparison = 0;
        quicksort.numExchange = 0;

        //recursive quick sort to 100
        intArray = writeFunction(size, type);
        quicksort.recursiveQuickSortto100(intArray, 0, intArray.length - 1);
        writeToFile(intArray, myWriterQuickSortto100);
        writemyWriterExchangeComparisonToFile(quicksort.numComparison, quicksort.numExchange, size, type, "Sort to 100", myWriterQuickSort100ExchangeComparison);
        quicksort.numComparison = 0;
        quicksort.numExchange = 0;

        //recursive quick sort to Median of 3
        intArray = writeFunction(size, type);
        quicksort.recursiveQuickSorttoMedianOf3(intArray, 0, intArray.length - 1);
        writeToFile(intArray, myWriterQuickSorttoMedianOf3);
        writemyWriterExchangeComparisonToFile(quicksort.numComparison, quicksort.numExchange, size, type, "Median of 3 Quick", myWriterQuickSortMediumOf3ExchangeComparison);
        quicksort.numComparison = 0;
        quicksort.numExchange = 0;

        //Exchange Comparison for Heap Sort
        intArray = writeFunction(size, type);
        heapsort.recursiveHeapSort(intArray);
        writeToFile(intArray, myWriterHeapSort);
        writemyWriterExchangeComparisonToFile(heapsort.numComparison, heapsort.numExchange, size, type, "Heap", myWriterHeapSortExchangeComparison);
        heapsort.numComparison = 0;
        heapsort.numExchange = 0;

        //Exchange Comparison for Merge Sort
        intArray = writeFunction(size, type);
        //mergesort.recursiveMergeSort(intArray, 0, intArray.length - 1);
        mergesort.mergeSort(intArray, intArray.length);
        writeToFile(intArray, myWriterMergeSort);
        writemyWriterExchangeComparisonToFile(mergesort.numComparison, mergesort.numExchange, size, type, "Merge", myWriterMergeSortExchangeComparison);
        heapsort.numComparison = 0;
        heapsort.numExchange = 0;

        //close file writer calls
        myWriterQuickSorttoRegular.close();
        myWriterQuickSortto50.close();
        myWriterQuickSortto100.close();
        myWriterQuickSorttoMedianOf3.close();
        myWriterHeapSort.close();
        myWriterMergeSort.close();

        //close file writer calls (Exchange Comparison)
        myWriterHeapSortExchangeComparison.close();
        myWriterMergeSortExchangeComparison.close();
        myWriterQuickSortRegularExchangeComparison.close();
        myWriterQuickSort50ExchangeComparison.close();
        myWriterQuickSort100ExchangeComparison.close();
        myWriterQuickSortMediumOf3ExchangeComparison.close();
    }
    /**
     * method that writes all sorted output to file
     *
     * @param intArray accepts integer (sorted)
     * @param file accepts file path
     * @return output written to file
     */
    public static void writeToFile(int intArray[], FileWriter file) throws IOException {
        //loop to write to file
        for (int k = 0; k < intArray.length; k++) {
            file.write(intArray[k]+"\n");
        }
        file.close();
    }
    /**
     * method that writes all exchanges and comparisons to file
     *
     * @param comparison accepts integer (sorted)
     * @param exchange file path
     * @param size accepts integer size (50, 100, 500, 1000, etc.)
     * @param type accepts string whether file is ordered, reversed, random
     * @param sort accepts which type of string (heap/merge/quick sort), and this written to file output
     * @param file accepts file path
     * @return output written to file
     */
    public static void writemyWriterExchangeComparisonToFile(int comparison, int exchange, int size, String type, String sort, FileWriter file) throws IOException{
        file.write(size + "\n");
        file.write(type + "\n");
        file.write("Comparison for " + sort + " Sort: "+ comparison + "\n");
        file.write("Exchange for " + sort +  " Sort: " + exchange + "\n");
    }
}