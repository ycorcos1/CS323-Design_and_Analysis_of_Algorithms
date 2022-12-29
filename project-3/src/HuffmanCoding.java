import java.io.BufferedReader;
import java.io.BufferedWriter;

public class HuffmanCoding {
	
	int[] charCountAry = new int[256];
	//String[] charCode = new String[256];
	linkedList LL = new linkedList();
	BinaryTree tree;
	
	public void computeCharCounts(BufferedReader inFile) throws Exception {
		int ch;
		while((ch = inFile.read()) != -1) {
			charCountAry[ch]++;
		}
//		for(int i=0; i<256; i++) {
//			char chr = (char) i;
//			charCode[i] = String.valueOf(chr);
//		}
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
				treeNode newNode = new treeNode(chStr, frequency, "", null, null, null);
				//treeNode spot = LL.findSpot(newNode);
				LL.insertNewNode(newNode);
				LL.printList(DebugFile);
				DebugFile.write("\n");
			}	
		}
	}
	
	public void constructHuffmanBinTree(BufferedWriter outFile) throws Exception {
		treeNode listHead = LL.listHead;
		tree = new BinaryTree(listHead);
		while(listHead.next.next != null) {
			int frequency = listHead.next.frequency + listHead.next.next.frequency;
			
			String chStr1 = fixString(listHead.next.chStr);
			String chStr2 = fixString(listHead.next.next.chStr);
			String chStr = chStr1.concat(chStr2);
			
			treeNode left = listHead.next;
			left.chStr = chStr1;
			treeNode right = listHead.next.next;
			right.chStr = chStr2;
			
			treeNode newNode = new treeNode(chStr, frequency, "", left, right, null);
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
}