
/*b)	You are given an array of different words and target words.
Each character of a word represents a different digit ranging from 0 to 9, and no two character are linked to same digit.
If the sum of the numbers represented by each word on the array equals the sum the number represented by the targeted word, return true;
otherwise, return false. Note: Provided list of words and targeted word is in the form of equation
Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
Output: true
Explanation:
 s=6,I=5,X=0,E=8,V=7,N=2,T=1,W=3,Y=4
SIX +SEVEN + SEVEN = TWENTY
650 + 68782 + 68782 = 138214
*/


public class Question6B {
    // Method to convert character to number based on the given rule
    static int characterToNum(char c) {
        if (c == 'S') return 6;
        if (c == 'I') return 5;
        if (c == 'X') return 0;
        if (c == 'E') return 8;
        if (c == 'V') return 7;
        if (c == 'N') return 2;
        if (c == 'T') return 1;
        if (c == 'W') return 3;
        if (c == 'Y') return 4;
        return -1;
    }

    // This method takes a string "word" as input and converts it into an integer array "nums"
    static int[] wordToNumber(String word) {
        // Initialize an integer array "nums" with the length of the input word
        int[] nums = new int[word.length()];
        // Loop through each character in the input word
        for (int i = 0; i < word.length(); i++) {
            // Convert the current character to a number using the characterToNum() method and add it to the nums array
            nums[i] = characterToNum(word.charAt(i));
        }
        return nums; // Return the integer array "nums"
    }
//initializing the function
    static int toNum(int[] nums) {
        // initialize the integer representation to 0
        int num = 0;
        // iterate through each digit
        for (int i = 0; i < nums.length; i++) {
            // if there's an invalid digit (-1), return -1
            if (nums[i] == -1) {
                return -1;
            }
            // update the integer
            num = num * 10 + nums[i];
        }
        // return the final integer
        return num;
    }

    // Method to check if the given words and result form a valid equation
    public static boolean isValid(String[] words, String result) {
// Count the number of non-numerical characters in the words
        int cnt = 0;
        //iterate until j reaches to 1 less than words.length
        for (int j = 0; j < words.length; j++) {

            // Get the current word
            String word = words[j];

// Iterate through the characters in the current word
            for (int i = 0; i < word.length(); i++) {
                // Get the current character
                char c = word.charAt(i);

                // Check if the character is non-numerical, if so, increment the count
                if (characterToNum(c) == -1) {
                    cnt++;
                }
            }

        }
        System.out.println("cnt: " + cnt);
// Convert the result string to an array of integers using the wordToNumber function
        int[] wordNums = wordToNumber(result);

// Convert the array of integers to a single integer using the toNum function
        int target = toNum(wordNums);

// Print the target integer for debugging purposes
        System.out.println("target: " + target);
// Calculate the sum of the numbers obtained by converting each word to an integer
        int sum = 0;
        for (int j = 0; j < words.length; j++) {
            String word = words[j];
            int[] nums = wordToNumber(word);
            int wordSum = toNum(nums);
            if (wordSum == -1) {
                return false;
            }
            sum += wordSum;
        }
        System.out.println("sum: " + sum);

// Check if the sum is equal to the target integer
        return sum == target;
    }

    public static void main(String[] args) {
        // Example input values
        String[] words = {"SIX", "SEVEN", "SEVEN"};
        String result = "TWENTY";
        // Call the isValid method with the input values
        System.out.println("isValid: " + isValid(words, result));
    }
}
