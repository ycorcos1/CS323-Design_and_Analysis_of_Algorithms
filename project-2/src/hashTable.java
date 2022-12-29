import java.io.BufferedReader;
import java.io.BufferedWriter;

public class hashTable {
	
	private char op;
	private String data;
	private int bucketSize;
	private Node[] hashtable;
	  
	
	public hashTable(int bucketSize) {
		this.bucketSize = bucketSize;
		hashtable = new Node[bucketSize];
		for(int i = 0; i<bucketSize; i++) {
			hashtable[i] = new Node("dummy");
			hashtable[i].next = null;
		}
	}
	
	public int doIt(String data) {
		long value = 1;
		int i = 0;
		int length = data.length();
		for(;i<length;i++) {
			char oneCh = data.charAt(i);
			value = value * 32 + (int) oneCh;
		}
		return (int) (value % bucketSize);
	}

	public void informationProcessing(BufferedReader inFile, BufferedWriter outFile2) throws Exception {
		String line;
		while((line = inFile.readLine()) != null) {
			outFile2.write("\n-------Enter informationProcessing method-------");
			String words[] = line.split("\t");
			op = words[0].charAt(0);
			data = words[1];
			outFile2.write("\nOperation: " + op + " Data: " + data);
			int index = doIt(data);
			outFile2.write("\nData is going into bucket: " + index);
			outFile2.write("\nBefore operation execution, printing bucket: " + index);
			printList(index, outFile2);
			if(op=='+') {
				hashInsert(index, data, outFile2);
			}else if(op=='-') {
				hashDelete(index, data, outFile2);
			}else if(op=='?') {
				hashRetrieval(index, data, outFile2);
			}else {
				outFile2.write("\nop is an unrecognized operation!");
			}
			outFile2.write("\n");
		}
		
	}
	
	public Node findSpot(int index, String data) {
		Node spot = hashtable[index];
		while(spot.next != null && spot.next.data.compareTo(data) > 0) {
			spot = spot.next;
		}
		return spot;
	}
	
	public void hashInsert(int index, String data, BufferedWriter outFile2) throws Exception {
		outFile2.write("\n*** enter hashInsert method. Performing hashInsert");
		Node spot = findSpot(index, data);
		if(spot.next != null && spot.next.data.compareTo(data) == 0) {
			outFile2.write("\n*** Warning, data is already in the database!");
		}else {
			Node newNode = new Node(data);
			newNode.next = spot.next;
			spot.next = newNode;
			outFile2.write("\nAfter hashInsert operation ...");
			printList(index, outFile2);
		}
	}
	
	public void hashDelete(int index, String data, BufferedWriter outFile2) throws Exception {
		outFile2.write("\n** Inside hashDelete method. Performing hashDelete");
		Node spot = findSpot(index, data);
		if(spot.next == null || spot.next.data.compareTo(data) != 0) {
			outFile2.write("\n*** Warning: data is *not* in the database!");
		}else {
			Node temp = spot.next;
			spot.next = temp.next;
			temp.next = null;
			outFile2.write("\nAfter hashDelete operation ...");
			printList(index, outFile2);
		}
	}
	
	public void hashRetrieval(int index, String data, BufferedWriter outFile2) throws Exception {
		outFile2.write("\n*** Inside hashRetrieval. Performing hashRetrieval");
		Node spot = findSpot(index, data);
		if(spot.next == null || spot.next.data.compareTo(data) != 0) {
			outFile2.write("\n*** Warning, the record is *not* in the database!");
		}else {
			outFile2.write("\nYes, the record is in the database!");
		}
	}
	
	public void printList(int index, BufferedWriter outFile) throws Exception {
		Node tempNode = hashtable[index];
		String baseStr = "\nHashTable["+index+"]: ";
		outFile.write(baseStr);
		if(tempNode.next == null) {
			outFile.write("(dummy null)");
		}else {
			while(tempNode.next != null) {
				String nodeStr = "(" + tempNode.data + " " + tempNode.next.data + ") -->";
				outFile.write(nodeStr);
				tempNode = tempNode.next;
			}
			if(tempNode.next == null) {
				String lastNodeStr = "(" + tempNode.data + " null)";
				outFile.write(lastNodeStr);
			}
		}
	}
	
	public void printHashTable(BufferedWriter outFile) throws Exception {
		for(int i = 0; i<bucketSize; i++) {
			printList(i, outFile);
		}
	}
	
}
