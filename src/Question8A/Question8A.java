package Question8A;

import java.util.Stack;
/*Given 2D matrix of 1 and 0s. Using stack, find maximum area of square made by 0s.
INPUT: 1 0 1 0 0
0 1 1 1 1
0 0 0 0 1
0 0 0 1 1
0 1 0 1 1
OUTPUT: 4
*/
public class Question8A {
    public static int getMaxSquareArea(int[][] matrix) {
        // number of rows in matrix
        int m = matrix.length;
        // number of columns in matrix
        int n = matrix[0].length;
        // variable to store the maximum area of square made by 0s
        int maxArea = 0;
        // array to store the height of the histogram for each column in the matrix
        int[] height = new int[n];

        for (int i = 0; i < m; i++) { // iterate over each row in the matrix
            for (int j = 0; j < n; j++) { // iterate over each column in the matrix
                if (matrix[i][j] == 1) { // if the current element is 1, set the height of the histogram to 0
                    height[j] = 0;
                } else { // if the current element is 0, increment the height of the histogram for the current column
                    height[j]++;
                }
            }
            // calculate the maximum area of the square for the current row using the largestSquareArea method
            int area = largestSquareArea(height);
            // update the maxArea variable with the maximum area of the square for the current row, if it is larger than the previous maximum
            maxArea = Math.max(maxArea, area);
        }

        // return the maximum area of square made by 0s in the matrix
        return maxArea;
    }

    // implementation of the largest square in a histogram algorithm using a stack
    private static int largestSquareArea(int[] heights) {
        int n = heights.length; // number of columns in the matrix (i.e., the length of the heights array)
        Stacks stack = new Stacks(n); // create a new stack to store the indices of the histogram bars
        int maxArea = 0; // variable to store the maximum area of the square in the histogram
        int i = 0; // variable to keep track of the current index in the heights array

        while (i <= n) { // loop through the heights array
            // get the height of the current bar in the histogram (i.e., the current height in the heights array)
            int h = (i == n) ? 0 : heights[i];
            if (stack.isEmpty() || h >= heights[stack.peek()]) { // if the stack is empty or the current bar is higher than or equal to the bar at the top of the stack
                stack.push(i); // push the current index onto the stack
                i++; // increment the index variable
            } else { // if the current bar is lower than the bar at the top of the stack
                // calculate the area of the square for the bar at the top of the stack
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int size = Math.min(height, width);
                int area = size * size;
                // update the maxArea variable with the area of the square for the bar at the top of the stack, if it is larger than the previous maximum
                maxArea = Math.max(maxArea, area);
            }
        }

        // return the maximum area of the square in the histogram
        return maxArea;
    }

    public static void main(String[] args) {
        //providing input
        int[][] matrix = {{1, 0, 1, 0, 0},
                {0, 1, 1, 1, 1},
                {0, 0, 0, 0, 1},
                {0, 0, 0, 1, 1},
                {0, 1, 0, 1, 1}};

        // Find the maximum area of square made by 0s in the matrix
        int maxArea = Question8A.getMaxSquareArea(matrix);
        System.out.println("Maximum area of square made by 0s: " + maxArea);
    }
}
//}
//class Question8A.Question8A {
//    // This is a static method that takes a 2D integer array (matrix) as input and returns an integer value (maxArea).
//    public static int getMaxSquareArea(int[][] matrix) {
//
//        // Get the number of rows
//        int rows = matrix.length;
//        // Get the number of rows
//        int cols = matrix[0].length;
//
//        // Create a new 2D integer array named "dp" to store intermediate results.
//        int[][] dp = new int[rows][cols];
//
//        // Initialize the "maxArea" variable to zero.
//        int maxArea = 0;
//
//        // Initialize the first row of the "dp" matrix.
//        for (int i = 0; i < rows; i++) {
//            //iterate i for less than rows
//            dp[i][0] = matrix[i][0];
//            //add value of maxArea to Max between maxArea and dp[i][0]
//            maxArea = Math.max(maxArea, dp[i][0]);
//        }
//
//        // Initialize the first column of the "dp" matrix.
//        for (int j = 0; j < cols; j++) {
//            //iterate j for less than rows
//            dp[0][j] = matrix[0][j];
//            //add value of maxArea to Max between maxArea and dp[0][j]
//            maxArea = Math.max(maxArea, dp[0][j]);
//        }
//
//        // Fill the remaining cells of the "dp" matrix.
//        //iterate i until it reaches less than rows value
//        for (int i = 1; i < rows; i++) {
//            //iterate j until it reaches less than cols value
//            for (int j = 1; j < cols; j++) {
//                // If the current cell contains a 1, set the corresponding cell in the "dp" matrix to 0.
//                if (matrix[i][j] == 1) {
//                    dp[i][j] = 0;
//                    // Otherwise, set the corresponding cell in the "dp" matrix to the minimum of the values in the three adjacent cells
//                } else {
//                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
//                    maxArea = Math.max(maxArea, dp[i][j]);
//                }
//            }
//        }
//
//        // Return the maximum area of the square made by 0s.
//        return maxArea * maxArea;
//    }
//
//    // This is the main method that calls the "getMaxSquareArea" method and prints the result.
//    public static void main(String[] args) {
//
//        // Create a 2D integer array named "matrix" to represent the input matrix.
//        int[][] matrix = {{1, 0, 1, 0, 0},
//                {0, 1, 1, 1, 1},
//                {0, 0, 0, 0, 1},
//                {0, 0, 0, 1, 1},
//                {0, 1, 0, 1, 1}};
//
//        // Call the "getMaxSquareArea" method to get the maximum area of the square made by 0s.
//        int maxSquareArea = getMaxSquareArea(matrix);
//
//        // Print the maximum area of the square made by 0s.
//        System.out.println("Maximum area of square made by 0s: " + maxSquareArea);
//    }
//}