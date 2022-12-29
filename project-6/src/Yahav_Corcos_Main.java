import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Yahav_Corcos_Main {

	public static void main(String[] args) throws Exception {
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		BufferedWriter SSSfile = new BufferedWriter(new FileWriter(args[1]));
		BufferedWriter debugFile = new BufferedWriter(new FileWriter(args[2]));
		
		int num = inFile.read();
		System.out.println(num);
//		int numNodes = Integer.parseInt(inFile.readLine());
//		Yahav_Corcos_DijktraSSS dijktraSSS = new Yahav_Corcos_DijktraSSS(numNodes);
//		dijktraSSS.loadCostMatrix(inFile);
//		dijktraSSS.sourceNode = 1;
//		
//		SSSfile.write("There are " + dijktraSSS.numNodes + " nodes in the input graph. Below are all the pairs of shortest paths:\n\n");
//		while(dijktraSSS.sourceNode <= dijktraSSS.numNodes) {
//			dijktraSSS.setBest(dijktraSSS.sourceNode);
//			dijktraSSS.setFather(dijktraSSS.sourceNode);
//			dijktraSSS.setToDo(dijktraSSS.sourceNode);
//			while(dijktraSSS.doneToDo() == false) {
//				dijktraSSS.minNode = dijktraSSS.findMinNode();
//				dijktraSSS.toDo[dijktraSSS.minNode]= 0; 
//				dijktraSSS.debugPrint(debugFile, dijktraSSS.sourceNode);
//				dijktraSSS.currentNode = 1;
//				while(dijktraSSS.currentNode <= dijktraSSS.numNodes) {
//					if(dijktraSSS.toDo[dijktraSSS.currentNode] > 0) {
//						dijktraSSS.newCost = dijktraSSS.computeCost(dijktraSSS.minNode,  dijktraSSS.currentNode);
//						if(dijktraSSS.newCost < dijktraSSS.best[dijktraSSS.currentNode]) {
//							dijktraSSS.best[dijktraSSS.currentNode] = dijktraSSS.newCost;
//							dijktraSSS.father[dijktraSSS.currentNode] = dijktraSSS.minNode;
//							dijktraSSS.debugPrint(debugFile, dijktraSSS.sourceNode);
//						}
//					}
//					dijktraSSS.currentNode++;
//				}
//			}
//			dijktraSSS.currentNode = 1;
//			SSSfile.write("The source node = " + dijktraSSS.sourceNode + "\n");
//			while(dijktraSSS.currentNode <= dijktraSSS.numNodes) {
//				dijktraSSS.printShortestPath(dijktraSSS.currentNode, dijktraSSS.sourceNode, SSSfile);;
//				SSSfile.write("\n");
//				dijktraSSS.currentNode++;
//			}
//			SSSfile.write("\n");
//			dijktraSSS.sourceNode++;
//		}
//		inFile.close();
//		SSSfile.close();
//		debugFile.close();
	}

}