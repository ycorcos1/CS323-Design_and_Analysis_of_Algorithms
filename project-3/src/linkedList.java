import java.io.BufferedWriter;

public class linkedList {

	treeNode listHead;
	
	public linkedList() {
		listHead = new treeNode("dummy",0,"",null,null,null);
		listHead.next = null;
	}
	
	public void insertNewNode(treeNode newNode) {
		treeNode spot = findSpot(newNode);
		newNode.next = spot.next;
		spot.next = newNode;
	}
	
	public treeNode findSpot(treeNode newNode) {
		treeNode spot = listHead;
		while(spot.next != null && spot.next.frequency < newNode.frequency) {
			spot = spot.next;
		}
		return spot;
	}
	
	public void printList(BufferedWriter outFile) throws Exception {
		treeNode temp = listHead;
		while(temp.next != null) {
			temp.printNode(temp, outFile);
			temp = temp.next;
		}
		if(temp.next == null) {
			temp.printNode(temp, outFile);
		}
	}
}