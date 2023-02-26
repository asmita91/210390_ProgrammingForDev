import java.util.ArrayList;
import java.util.List;

/*Given an array of even numbers sorted in ascending order and an integer k,
Find the k^th missing even number from provided array
Input a[] ={0, 2, 6, 18, 22} k=6
Output: 16 examples:
Explanation: Missing even numbers on the list are 4, 8, 10, 12, 14, 16, 20 and so on and kth
missing number is on 6th place of the list i.e. 16
*/// Importing required classes for the program
import java.util.ArrayList;
import java.util.List;

// Creating a class named Question8B
public class Question8B {

    // Defining a method to find the kth missing even number
    public static int findKthMissingEvenNumber(int[] a, int k) {

        // Creating a List to store the missing even numbers
        List<Integer> missingEvens = new ArrayList<>();

        // Initializing a variable j to 0
        int j = 0;

        // Looping through the even numbers between the first and last element of the given array
        for (int i = a[0]; i < a[a.length - 1]; i += 2) {

            // Checking if the current even number is present in the given array
            if (j < a.length && a[j] == i) {
                j++;
                continue;
            }

            // Adding the missing even number to the List
            missingEvens.add(i);

            // Checking if the kth missing even number is found
            if (missingEvens.size() == k) {
                return i;
            }
        }

        // If the kth missing even number is not found in the above loop, return the last element of the array plus k multiplied by 2
        return a[a.length - 1] + 2 * k;
    }

    // Defining the main method to execute the program
    public static void main(String[] args) {

        // Initializing an array of even numbers in ascending order
        int[] a = {0, 2, 6, 18, 22};

        // Initializing the value of k
        int k = 6;

        // Calling the findKthMissingEvenNumber method to find the kth missing even number and printing it
        System.out.println(findKthMissingEvenNumber(a, k));
    }
}