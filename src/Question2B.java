import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*You are given an array of binary trees that represent different cities where a certain corporation has its branch office and the organization wishes
to provide service by constructing a service center. Building service centers at any node, i.e., a city can give service to its directly connected cities where it can
provide service to its parents, itself, and its immediate children. Returns the smallest number of service centers required by the corporation to provide service to all connected cities.
Note that: the root node represents the head office and other connected nodes represent the branch office connected to the head office maintaining some kind of hierarchy.

Input: tree= {0,0, null, 0, null, 0, null, null, 0}
Output: 2
Explanation: construction of two service centers denoted by black markk will be enough to provide service to all cities.
*/

public class Question2B {
    private int[] parents; // array to store the parent of each node
    private int[] depth; // array to store the depth of each node in the tree
    private List<Integer>[] children; // array of lists to store the children of each node
    private int[] subtreeSize; // array to store the size of the subtree rooted at each node
    private int[] serviceCenters; // array to store the minimum number of service centers needed for each node
    private int n; // number of nodes in the tree

    public int minimumServiceCenters(int n, List<Integer>[] children) {
        this.n = n;
        this.children = children;
        parents = new int[n];
        depth = new int[n];
        subtreeSize = new int[n];
        serviceCenters = new int[n];
        Arrays.fill(parents, -1); // initialize parents array to -1
        Arrays.fill(serviceCenters, -1); // initialize serviceCenters array to -1
        dfs1(0, -1); // call dfs1 to compute depth, parents, and subtreeSize arrays
        return dfs2(0, -1); // call dfs2 to compute minimum number of service centers needed for root node (0)
    }

    // dfs1 computes depth, parents, and subtreeSize arrays
    private void dfs1(int node, int parent) {
        parents[node] = parent; // set parent of current node
        depth[node] = parent == -1 ? 0 : depth[parent] + 1; // set depth of current node
        subtreeSize[node] = 1; // set size of subtree rooted at current node to 1
        for (int child : children[node]) {
            dfs1(child, node); // recursively call dfs1 on each child of current node
            subtreeSize[node] += subtreeSize[child]; // update size of subtree rooted at current node
        }
    }

    // dfs2 computes minimum number of service centers needed for each node
    private int dfs2(int node, int parent) {
        if (serviceCenters[node] != -1) return serviceCenters[node]; // if minimum number of service centers has already been computed for current node, return it
        int count = 0; // initialize count of service centers needed for current node
        for (int child : children[node]) {
            count += dfs2(child, node); // recursively call dfs2 on each child of current node and add their minimum number of service centers to count
        }
        int option1 = count; // option1 is to place a service center at each child of current node
        int option2 = n - subtreeSize[node]; // option2 is to place a service center at the current node itself and cover the entire subtree rooted at it
        serviceCenters[node] = Math.min(option1, option2) + 1; // choose the minimum of option1 and option2, add 1 for the service center at the current node, and store the result in the serviceCenters array
        return serviceCenters[node]; // return the minimum number of service centers needed for current node
    }

    public static void main(String[] args) {
        // create a sample tree with 5 nodes
        List<Integer>[] children = new List[5];
        for (int i = 0; i < 5; i++) {
            children[i] = new ArrayList<>();
        }
        children[0].add(1);
        children[0].add(2);
        children[0].add(3);
        Question2B sc = new Question2B();
        System.out.println(sc.minimumServiceCenters(5, children));
    }
}