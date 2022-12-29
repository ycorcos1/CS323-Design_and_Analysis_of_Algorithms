import java.io.BufferedWriter;

public class Corcos_Yahav_linkedList {

	Corcos_Yahav_treeNode listHead;
	
	public Corcos_Yahav_linkedList() {
		listHead = new Corcos_Yahav_treeNode("dummy",0,"",null,null,null);
		listHead.next = null;
	}
	
	public void insertNewNode(Corcos_Yahav_treeNode newNode) {
		Corcos_Yahav_treeNode spot = findSpot(newNode);
		newNode.next = spot.next;
		spot.next = newNode;
	}
	
	public Corcos_Yahav_treeNode findSpot(Corcos_Yahav_treeNode newNode) {
		Corcos_Yahav_treeNode spot = listHead;
		while(spot.next != null && spot.next.frequency < newNode.frequency) {
			spot = spot.next;
		}
		return spot;
	}
	
	public void printList(BufferedWriter outFile) throws Exception {
		Corcos_Yahav_treeNode temp = listHead;
		while(temp.next != null) {
			temp.printNode(temp, outFile);
			temp = temp.next;
		}
		if(temp.next == null) {
			temp.printNode(temp, outFile);
		}
	}
}