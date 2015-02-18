Xiaohui Chen
xc2388
xhchen0328@utexas.edu

Jiawei Guo
jg44347
tomwei1992@gmail.com

Huff.java
This java file takes a filename as an argument and output the length of the Huffman code and the minimum expected length of the compression. There is a HashSet that stores the nodes of Huffman tree. 
The main function takes the argument, opens the file, and calls io.giveArray() to generate a char Array. Then it indexes the charaters in the array with their probabilities and store them as a binary tree node. The node is sorted by probability of the character it represents. Then the main function calls buildTree which builds the Huffman tree. Next, it find the depth of the tree by traversing it.


buildTree(HashSet<HuffmanNode> nodeBag)
This method recursively merge the two smallest nodes into one and build the tree from the bottom to the top. It is similar to the algorithm described in the book.

HuffmanNode Class
This class implements tree nodes. Each node has its the character it represents, the associated probability, the parent and children. It is comparable by the probability.


Comp.java
This class implements LZ coding. It takes 2 arguments. The 1st argument is used for deciding whether the file is used for compression or decompression. The 2nd argument is the fileName. Then either compressFile or decompressFile is called according to the flag.

compressFile(String inputFile)
This method takes the file name as the argument. Then it uses methods in IO.java to open this file and generates a char array. Them it calls indexString in LZCompressor.java (discuss later) to compress the string.

decompressFile(String inputFile)
This method takes the file name as the argument. Then it creates an ArrayList and uses it as a dictionary. When a pair is fetched using io.decode(), a string in the ArrayList is fetched using the index number in the pair, and then the extention is attached to the string. At last, this string is written to the decompressed file using io.append method.


LZCompressor.java
This java file is used for compression. The class constructor generates the root of the trie tree. indexString is called by compressFile method in Comp.java

indexString(char[] carray, IO.Compressor io)
This method takes the char array and the IO.Compressor instance as arguments. Then it generates a trie and uses io.encode to create the corresponding pair and write it to the corresponding file. After writing all pairs, the method returns to compressFile method in Comp.java

private class LZDictTreeNode
This is a private class which implements the nodes of trie. It contains information about the node's children in a HashMap (use the character that the child represents as key). It also contains the index of the node.



Why the size of small ".myZ" file is larger than the original file?
--In LZ coding, the compressed file contains pairs of (index,String). In small files, the probability of string repetition is so low that the dictionary is long. Since index is added to the compressed file, the compressed file is larger.





java Comp c xxx.txt
java Comp d xxx.txt.myZ

java Huff xxx.txt

