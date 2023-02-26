import java.util.Arrays;
/*You are given an even length array; divide it in half and return possible minimum product difference of any two subarrays.
Note that the minimal product difference is the smallest absolute difference between any two arrays a and b, which is computed by calculating the difference after multiplying each element of the arrays a and b.
Input: {5,2,4,11}
Output: 2
{5,4} {2,11} result into minimum product difference.
*/



//import java.util.Arrays;
//
//public class Question3A {
//    // function to find the minimum product difference of an even-length array
//    public static int minProductDifference(int[] arr) {
//        int n = arr.length;
//        // loop to sort the array in non-ascending order using bubble sort
//        for (int i = 0; i < n - 1; i++) {
//            for (int j = 0; j < n - i - 1; j++) {
//                // swap arr[j] and arr[j + 1] if arr[j] is smaller than arr[j + 1]
//                if (arr[j] < arr[j + 1]) {
//                    int temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println(Arrays.toString(arr));
//        int half = n / 2;
//        // product of first half of the array
//        int product1 = 1;
//        int product2 = 1;
//        int first_half[]=new int[half];
//        int second_half[]=new int[half];
//        for(int i=0;i< half;i++){
//            first_half[i]=arr[i];
//            second_half[i]=arr[i+half];
//        }
//        System.out.println(Arrays.toString(first_half));
//        System.out.println(Arrays.toString(second_half));
//
//        int first_product=first_half[0]*second_half[second_half.length-1];
//        int second_product=first_half[first_half.length-1]*second_half[0];
//
//        System.out.println(first_product);
//        System.out.println(second_product);
//
//        int min_diff=Math.abs(second_product-first_product);
//        return min_diff;
//    }
//
//    public static void main(String[] args) {
//        int a[]= {5,2,4,11};
//        System.out.println("The minimum product difference is "+  minProductDifference(a));
//
//    }
//}

//creating class
public class Question3A {
    //defining a function that takes arr array as an parameter and returns an int value
    public static int minProductDifference(int[] arr) {
        //sorting the elements of array in ascending oredr
        Arrays.sort(arr);
        //n refers to length of array arr
        int n = arr.length;
        //initializing minDiff variable with the max_value (at first)
        int minDiff = Integer.MAX_VALUE;
        // calculate the difference between two subarrays with the minimum product difference
        //for loop that iterates through the first half of the array.
        for (int i = 0; i < n / 2; i++) {
            /*calculates the product of the i-th element and its corresponding
            element in the second half of the array*/
            int product1 = arr[i] * arr[n - i - 1];
            /*calculates the product of the (n/2+i)-th
             element and its corresponding element in the first half of the array*/
            int product2 = arr[n / 2 + i] * arr[n - n / 2 - i - 1];
//            calculates the absolute difference between product1 and product2.
            int diff = Math.abs(product1 - product2);
            //updates the minimum difference found so far between the two products
            minDiff = Math.min(minDiff, diff);
        }
        //returns minDiff
        return minDiff;
    }


    public static void main(String[] args) {
        //providing the input for the array "arr"
        int[] arr = {5, 2, 4, 11};
        //calling the function and display the value returned by function in the console
        System.out.println(minProductDifference(arr));
    }
}
