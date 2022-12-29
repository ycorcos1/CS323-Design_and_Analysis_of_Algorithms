import java.io.BufferedWriter;

public class Corcos_Yahav_BinaryTree {
	
	Corcos_Yahav_treeNode root;
	
	public Corcos_Yahav_BinaryTree(Corcos_Yahav_treeNode root) {
		this.root = root;
	}
	
	public boolean isLeaf(Corcos_Yahav_treeNode node) {
		if(node.right == null && node.left == null) {
			return true;
		}
		return false;
	}
	
	public void preOrderTraversal(Corcos_Yahav_treeNode T, BufferedWriter outFile) throws Exception {
		if(isLeaf(T)) {
			T.printNode(T, outFile);	
		}else {
			T.printNode(T, outFile);
			outFile.write("\n");
			preOrderTraversal(T.left, outFile);
			preOrderTraversal(T.right, outFile);
		}
	}
	
	public void inOrderTraversal(Corcos_Yahav_treeNode T, BufferedWriter outFile) throws Exception {
		if(isLeaf(T)) {
			T.printNode(T, outFile);
		}else {
			inOrderTraversal(T.left, outFile);
			T.printNode(T, outFile);
			outFile.write("\n");
			inOrderTraversal(T.right, outFile);
		}
	}
	
	public void postOrderTraversal(Corcos_Yahav_treeNode T, BufferedWriter outFile) throws Exception {
		if(isLeaf(T)) {
			T.printNode(T, outFile);
		}else {
			postOrderTraversal(T.left, outFile);
			postOrderTraversal(T.right, outFile);
			T.printNode(T, outFile);
			outFile.write("\n");
		}
	}

}