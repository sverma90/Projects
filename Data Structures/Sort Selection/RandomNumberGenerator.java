/**
 * Sort Comparison
 * RandomNumberGenerator.java
 * @version    1.0     2021-12-07
 * @author     Soumil Verma
 * Class randomly generates inputs from 50 to 10000
 * Stores all inputs in Input folder with project files
 * @return ordered/reversed/random sorted inputs
 */
import java.io.*;
public class RandomNumberGenerator  {
    //variable declarations
    PrintWriter out;
    int number;
    /**
     * constructor for random numbers
     *
     * @return randon numbers
     * @return writes to file (i.e. ordered/reversed/random)
     */
    public RandomNumberGenerator() throws IOException {
        int size[] = new int[] {50, 100, 500, 1000, 2000, 5000, 10000};
        for(int currSize : size){
            int temp[] = currArray(currSize);
            writeToFile(temp, "Ordered");
            reverseArray(temp);
            writeToFile(temp, "Reversed");
            random(temp);
            writeToFile(temp, "Random");
        }
    }
    /**
     * initialize an ascending ordered array
     *
     * @param size integer for input size
     * @return array of numbers
     */
    public int [] currArray(int size){
        int array[] = new int[size];
        for(int i = 0; i < size; i++){
            array[i] = i+1;
        }
        return array;
    }
    /**
     * shuffles array contents
     *
     * @param array accepts array of integers
     * @return shuffled array
     */
    public void random(int [] array){
        for(int i = 0; i < array.length; i++) {
            int swap = i + (int) (Math.random() * (array.length-i));
            int temp = array[swap];
            array[swap] = array[i];
            array[i] = temp;
        }
    }
    /**
     * reverses array contents
     *
     * @param array accepts array of integers
     * @return reversed array
     */
    public void reverseArray(int [] array){
        int j = array.length - 1;
        for(int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            j--;
        }
    }
    /**
     * writes to file
     *
     * @param array accepts array of integers
     * @param fileName string of file name (i.e. ordered/reversed/random)
     * @return reversed array
     */
    public void writeToFile(int [] array, String fileName) throws IOException {
        FileWriter myWriter = new FileWriter("Input/inputTo" + array.length + fileName + ".txt");
        for (int val : array) {
            myWriter.write(val + "\n");
        }
        myWriter.close();
    }
}