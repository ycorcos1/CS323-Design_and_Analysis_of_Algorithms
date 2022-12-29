import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Yahav_Corcos_DijktraSSS {
	
	int numNodes;
	int sourceNode;
	int minNode;
	int currentNode;
	int newCost;
	int costMatrix[][];
	int father[];
	int toDo[];
	int best[];
	
	public Yahav_Corcos_DijktraSSS(int numNodes) throws Exception {
		this.numNodes = numNodes;
		minNode = 0;
		sourceNode = 0;
		currentNode = 0;
		newCost = 0;
		costMatrix = new int[numNodes+1][numNodes+1];
		for(int i = 0; i<numNodes+1; i++) {
			for(int j = 0; j<numNodes+1; j++) {
				if(i==j) {
					costMatrix[i][j] = 0;
				}else {
					costMatrix[i][j] = 9999;
				}
			}
		}
		father = new int[numNodes+1];
		toDo = new int[numNodes+1];
		best = new int[numNodes+1];
	}
	
	public void loadCostMatrix(BufferedReader inFile) throws Exception {
		String line;
		while((line = inFile.readLine()) != null) {
			String nums[] = line.split(" ");
			if(nums.length == 3) {
				int i = Integer.parseInt(nums[0]);
				int j = Integer.parseInt(nums[1]);
				int cost = Integer.parseInt(nums[2]);
				costMatrix[i][j] = cost;
			}
		}
	}
	
	public void setBest(int sourceNode) {
		for(int i = 0; i<costMatrix[sourceNode].length; i++) {
			best[i] = costMatrix[sourceNode][i];
		}
	}
	
	public void setFather(int sourceNode) {
		for(int i = 0; i<father.length; i++) {
			father[i] = sourceNode;
		}
	}
	
	public void setToDo(int sourceNode) {
		for(int i = 0; i<toDo.length; i++) {
			if(i==sourceNode) {
				toDo[i] = 0;
			}else {
				toDo[i] = 1;
			}
		}
	}
	
	public int findMinNode() {
		int minCost = 9999;
		int minNode = 0;
		int index = 1;
		while(index <= numNodes) {
			if(toDo[index] > 0) {
				if(best[index] < minCost) {
					minCost = best[index];
					minNode = index;
				}
			}
			index++;
		}
		return minNode;
	}
	
	public int computeCost(int minNode, int currentNode) {
		int computedCost = best[minNode] + costMatrix[minNode][currentNode];
		return computedCost;
	}
	
	public void debugPrint(BufferedWriter debugFile, int sourceNode) throws Exception {
		debugFile.write("the sourceNode is: " + sourceNode + "\n");
		printFather(debugFile);
		printBest(debugFile);
		printToDo(debugFile);
		debugFile.write("\n");
	}
	
	public boolean doneToDo() {
		int count = 0;
		for(int i = 0; i<toDo.length; i++) {
			if(toDo[i] != 0) {
				count++;
			}
		}
		if(count==0) {
			return true;
		}else
			return false;
	}
	
	public void printShortestPath(int currentNode, int sourceNode, BufferedWriter SSSfile) throws Exception {
		SSSfile.write("The path from " + sourceNode + " to " + currentNode + ": " + currentNode);
		int temp = currentNode;
		while(father[temp] != sourceNode) {
			SSSfile.write("<-" + father[temp]);
			temp = father[temp];
		}
		if(father[temp] == sourceNode) {
			SSSfile.write("<-" + sourceNode);
		}
		SSSfile.write(" :cost = " + best[currentNode]);
	}
	
	private void printToDo(BufferedWriter debugFile) throws Exception {
		debugFile.write("the toDo array is: ");
		for(int i = 0; i<toDo.length; i++) {
			debugFile.write(toDo[i] + " ");
		}
		debugFile.write("\n");
	}
	
	private void printFather(BufferedWriter debugFile) throws Exception {
		debugFile.write("the father array is: ");
		for(int i = 0; i<father.length; i++) {
			debugFile.write(father[i] + " ");
		}
		debugFile.write("\n");
	}
	
	private void printBest(BufferedWriter debugFile) throws Exception {
		debugFile.write("the best array is: ");
		for(int i = 0; i<best.length; i++) {
			debugFile.write(best[i] + " ");
		}
		debugFile.write("\n");
	}
}
