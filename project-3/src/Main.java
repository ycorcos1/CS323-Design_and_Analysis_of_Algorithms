import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		BufferedWriter outFile = new BufferedWriter(new FileWriter(args[1]));
		
		HuffmanCoding huffmanCoding = new HuffmanCoding();
		
		huffmanCoding.computeCharCounts(inFile);
		outFile.write("PRINT CHAR COUNT ARY:\n");
		huffmanCoding.printCountAry(outFile);
		outFile.write("\n\nHUFFMAN LList\n");
		huffmanCoding.constructHuffmanLList(outFile);
		outFile.write("\n\nHUFFMAN BINARY TREE:\n");
		huffmanCoding.constructHuffmanBinTree(outFile);
		
		BinaryTree tree = huffmanCoding.tree;
		outFile.write("\n\nPRE ORDER TRAVERSAL:\n");
		tree.preOrderTraversal(tree.root, outFile);
		outFile.write("\n\nIN ORDER TRAVERSAL:\n");
		tree.inOrderTraversal(tree.root, outFile);
		outFile.write("\n\nPOST ORDER TRAVERSAL:\n");
		tree.postOrderTraversal(tree.root, outFile);
		
		inFile.close();
		outFile.close();
	}

}