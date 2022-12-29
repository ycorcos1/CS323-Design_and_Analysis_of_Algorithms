import java.io.BufferedWriter;

public class Corcos_Yahav_treeNode {
	
	String chStr;
	int frequency;
	String code;
	Corcos_Yahav_treeNode left;
	Corcos_Yahav_treeNode right;
	Corcos_Yahav_treeNode next;
	
	public Corcos_Yahav_treeNode(
			String chStr, int frequency, 
			String code, Corcos_Yahav_treeNode left, 
			Corcos_Yahav_treeNode right, Corcos_Yahav_treeNode next) {
		
		this.frequency = frequency;
		this.code = code;
		this.left = left;
		this.right = right;
		this.next = next;
		this.chStr = chStr;
	}
	
	public void printNode(Corcos_Yahav_treeNode T, BufferedWriter outFile) throws Exception{
		String nodeStr = "(" + fixString(T) + ", " + 
				T.frequency + ", " + fixString(T.next) + 
				", " + fixString(T.left) + ", " + fixString(T.right) + ") -->";
		outFile.write(nodeStr);
		//outFile.write("\n");
	}
	
	private String fixString(Corcos_Yahav_treeNode node) {
		if(node == null) {
			return "null";
		}else if(node.chStr.equals(String.valueOf((char)10))) {
			return "[ENTER]"; 
		}else if(node.chStr.equals(String.valueOf((char)13))){
			return "[RETURN]";
		}else if(node.chStr.equals(String.valueOf((char)32))){
			return "[SPACE]";
		}else
			return node.chStr;
	}

}