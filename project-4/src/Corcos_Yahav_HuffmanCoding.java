import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Corcos_Yahav_HuffmanCoding {
	
	int[] charCountAry = new int[256];
	String[] charCode = new String[256];
	Corcos_Yahav_linkedList LL = new Corcos_Yahav_linkedList();
	Corcos_Yahav_BinaryTree tree;
	
	public void computeCharCounts(BufferedReader inFile) throws Exception {
		int ch;
		while((ch = inFile.read()) != -1) {
			charCountAry[ch]++;
		}
	}
	
	public void printCountAry(BufferedWriter DebugFile) throws Exception {
		for(int i = 0; i<256; i++) {
			if(charCountAry[i] == 0) {
				DebugFile.write("");
			}else {
				DebugFile.write("char" + i + ": " + fixString(String.valueOf((char)i)) + " ");
				DebugFile.write("count" + i + ": " + charCountAry[i] + "\n");
			}
		}
	}
	
	public void constructHuffmanLList(BufferedWriter DebugFile) throws Exception {
		//treeNode listHead = LL.listHead;
		for(int index = 0; index < 256; index++) {
			if(charCountAry[index] > 0) {
				char chr = (char) index;
				String chStr = String.valueOf(chr);
				int frequency = charCountAry[index];
				Corcos_Yahav_treeNode newNode = new Corcos_Yahav_treeNode(chStr, frequency, "", null, null, null);
				//treeNode spot = LL.findSpot(newNode);
				LL.insertNewNode(newNode);
				LL.printList(DebugFile);
				DebugFile.write("\n");
			}	
		}
	}
	
	public void constructHuffmanBinTree(BufferedWriter outFile) throws Exception {
		Corcos_Yahav_treeNode listHead = LL.listHead;
		tree = new Corcos_Yahav_BinaryTree(listHead);
		while(listHead.next.next != null) {
			int frequency = listHead.next.frequency + listHead.next.next.frequency;
			
			String chStr1 = fixString(listHead.next.chStr);
			String chStr2 = fixString(listHead.next.next.chStr);
			String chStr = chStr1.concat(chStr2);
			
			Corcos_Yahav_treeNode left = listHead.next;
			left.chStr = chStr1;
			Corcos_Yahav_treeNode right = listHead.next.next;
			right.chStr = chStr2;
			
			Corcos_Yahav_treeNode newNode = new Corcos_Yahav_treeNode(chStr, frequency, "", left, right, null);
			LL.insertNewNode(newNode);
			listHead.next = listHead.next.next.next;
			LL.printList(outFile);
			outFile.write("\n");
		}
		tree.root = listHead.next;
	}
	
	private String fixString(String string) {
		if(string.equals(String.valueOf((char)10))) {
			return "[ENTER]";
		}else if(string.equals(String.valueOf((char)13))){
			return "[RETURN]";
		}else if(string.equals(String.valueOf((char)32))){
			return "[SPACE]";
		}else
			return string;
	}
	
	public void constructCharCode(Corcos_Yahav_treeNode T, String code) {
		if(tree.isLeaf(T)) {
			T.code = code;
			char ch = convertToChar(T.chStr);
			int index = ch;
			charCode[index] = code;
		}else {
			constructCharCode(T.left, code + "0");
			constructCharCode(T.right, code + "1");
		}
	}
	
	private char convertToChar(String chStr) {
		if(chStr.equals("[ENTER]")) {
			return (char) 10;
		}else if(chStr.equals("[RETURN]")) {
			return (char) 13;
		}else if(chStr.equals("[SPACE]")) {
			return (char) 32;
		}else
			return chStr.charAt(0);
	}
	
	public void Encode(BufferedReader orgFile, BufferedWriter compFile, BufferedWriter outFile) throws Exception {
		char charIn;
		while((charIn = (char) orgFile.read()) != (char) -1) {
			int index = charIn;
			if(charCode[index] != null) {
				String code = charCode[index];
				outFile.write(String.valueOf(index) + " " + code);
				compFile.write(code);
			}
		}
	}
	
	public void Decode(BufferedReader compFile, BufferedWriter deCompFile, Corcos_Yahav_treeNode root) throws Exception {
		Corcos_Yahav_treeNode spot = root;
		int oneBit;
		while((oneBit = compFile.read()) != -1) {
			if(tree.isLeaf(spot)) {
				deCompFile.write(String.valueOf(convertToChar(spot.chStr)));
				spot = tree.root;
			}
			if(oneBit == '0') {
				spot = spot.left;
			}else if(oneBit == '1') {
				spot = spot.right;
			}else {
				System.out.println("Error! The compress file contains invalid character!");
				System.exit(1);
			}
		}
		if(compFile.read() == -1 && !tree.isLeaf(spot)) {
			System.out.println("Error: The compress file is corrupted!");
		}
	}
	
	public void userInterface(Corcos_Yahav_treeNode root, BufferedWriter outFile) throws Exception {
		String nameOrg;
		String nameCompress;
		String nameDeCompress;
		char yesNo = 'Y';
		Scanner scanner = new Scanner(System.in);
		
		while(yesNo != 'N') {
			System.out.println("Do you want to encode a file? (Y/N): ");
			String input = scanner.next().toUpperCase();
			yesNo = input.charAt(0);
			if(yesNo == 'N') {
				System.exit(1);
			}else 
				System.out.println("What is the name of the file you want to compress? (exclude .txt)");
				nameOrg = scanner.next();
		
				nameCompress = nameOrg + "_Compressed.txt";
				nameDeCompress = nameOrg + "_DeCompress.txt";
				nameOrg = nameOrg + ".txt";
				
				BufferedReader orgFile = new BufferedReader(new FileReader(nameOrg));
				BufferedWriter compFile = new BufferedWriter(new FileWriter(nameCompress));
				BufferedWriter deCompFile = new BufferedWriter(new FileWriter(nameDeCompress));
				
				
				Encode(orgFile, compFile, outFile);
				compFile.close();
			
				BufferedReader compFile2 = new BufferedReader(new FileReader(nameCompress));
				
				Decode(compFile2, deCompFile, root);
				orgFile.close();
				compFile2.close();
				deCompFile.close();
			
		}
		scanner.close();
	}
}