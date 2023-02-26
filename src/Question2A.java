import java.util.Arrays;
/*You are given a 2D array containing hierarchical information about certain species,
with edge[i]=[xi,yi], where node xi is connected to xj.
 You are also provided an array of values associated with each species, such that value[i] reflects the ith nodes value.
 If the greatest common divisor of two values is 1, they are "relatively prime."
 Any other node on the shortest path from that node to the absolute parent node is an ancestor of certain species i.
 Return a list of nearest ancestors, where result[i] is the node i's nearest ancestor such that values[i] and value[result[i]] are both relative primes otherwise -1.
 */

public class Question2A {
    // Define a public static method to find the greatest common divisor (GCD) of two numbers using Euclid's algorithm
    public static int gcd(int a, int b) {
        if (b == 0) { // Check if the second number is zero
            return a; // Return the first number as GCD
        } else {
            return gcd(b, a % b); // Recursively call the method with swapped parameters
        }
    }

    // Define a public static method to find the nearest ancestor with relative prime value
    public static int nearestAncestor(int[] values, int[][] edges, int node) {
        // Base case: If the current node is the root node (i.e., has no parent)
        if (node == 0) {
            return -1; // Return -1 as there is no ancestor for the root node
        }

        int parent = -1; // Define a variable to store the parent node of the current node
        int gcdValue = 0; // Define a variable to store the GCD value of the current node and its parent

        // Traverse the path from the current node to the root node
        while (node != 0 && gcdValue != 1) {
            // Find the parent of the current node
            for (int i = 0; i < edges.length; i++) {
                if (edges[i][1] == node) { // Check if the second element of the edge is the current node
                    parent = edges[i][0]; // Assign the first element of the edge as the parent node
                    break;
                }
            }
            gcdValue = gcd(values[node], values[parent]); // Find the GCD of the current node and its parent
            node = parent; // Set the parent node as the current node for the next iteration
        }

        if (gcdValue == 1) { // Check if the GCD value is 1, indicating that the parent node has a relative prime value with the current node
            return parent; // Return the parent node as the nearest ancestor with relative prime value
        } else {
            return -1; // Return -1 as there is no ancestor with relative prime value for the current node
        }
    }

    // Define a public static method to find the nearest ancestors for all nodes
    public static int[] nearestAncestors(int[] values, int[][] edges) {
        int n = values.length; // Get the number of nodes in the tree
        int[] result = new int[n]; // Define an integer array to store the nearest ancestor of each node
        Arrays.fill(result, -1); // Initialize the array with -1, indicating that there is no nearest ancestor for each node

        // Iterate over all nodes and find their nearest ancestor with relative prime value
        for (int i = 0; i < n; i++) {
            result[i] = nearestAncestor(values, edges, i); // Call the nearestAncestor method for each node and store the result in the array
        }

        return result; // Return the array containing the nearest ancestor of each node
    }
    // Define a public static method to test the solution
    public static void main(String[] args) {
        int[] values = {3, 2, 6, 6, 4, 7, 12}; // Define an integer array to store the values of each node in the tree
        int[][] edges = {{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}}; // Define a 2D integer array to store the edges between the nodes
        int[] result = nearestAncestors(values, edges); // Call the nearestAncestors method to find the nearest ancestor for each node

// Print the result
        System.out.println(Arrays.toString(result)); // Print the result as a string using the Arrays.toString() method
    }
}