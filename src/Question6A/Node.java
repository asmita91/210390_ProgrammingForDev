package Question6A;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Implement Huffman encoding and decoding.


//defining a class that creates nodes of the tree
class Node
{
    //storing character in ch variable of type character
    Character ch;
    //storing frequency in freq variable of type int
    Integer freq;
    //initially both child (left and right) are null
    Node left = null;
    Node right = null;
    //creating a constructor of the Question6A.Node class
    Node(Character ch, Integer freq)
    {
        this.ch = ch;
        this.freq = freq;
    }
    //creating a constructor of the Question6A.Node class
    public Node(Character ch, Integer freq, Node left, Node right)
    {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}
