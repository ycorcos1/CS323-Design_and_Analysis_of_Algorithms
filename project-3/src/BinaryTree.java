import java.io.BufferedWriter;

public class BinaryTree {
	
	treeNode root;
	
	public BinaryTree(treeNode root) {
		this.root = root;
	}
	
	public boolean isLeaf(treeNode node) {
		if(node.right == null && node.left == null) {
			return true;
		}
		return false;
	}
	
	public void preOrderTraversal(treeNode T, BufferedWriter outFile) throws Exception {
		if(isLeaf(T)) {
			T.printNode(T, outFile);	
		}else {
			T.printNode(T, outFile);
			outFile.write("\n");
			preOrderTraversal(T.left, outFile);
			preOrderTraversal(T.right, outFile);
		}
	}
	
	public void inOrderTraversal(treeNode T, BufferedWriter outFile) throws Exception {
		if(isLeaf(T)) {
			T.printNode(T, outFile);
		}else {
			inOrderTraversal(T.left, outFile);
			T.printNode(T, outFile);
			outFile.write("\n");
			inOrderTraversal(T.right, outFile);
		}
	}
	
	public void postOrderTraversal(treeNode T, BufferedWriter outFile) throws Exception {
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