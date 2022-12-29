/*
Yahav Corcos
Professor Phillips
CS323 Project 2 - LinkedList Implementation of Hashtable
September 12, 2022
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader inFile = new BufferedReader(new FileReader(args[0]));
		BufferedWriter outFile1 = new BufferedWriter(new FileWriter(args[2]));
		BufferedWriter outFile2 = new BufferedWriter(new FileWriter(args[3]));
		int bucketSize = Integer.parseInt(args[1]);
		
		hashTable hashtable = new hashTable(bucketSize);
		hashtable.informationProcessing(inFile, outFile2);
		hashtable.printHashTable(outFile1);
		inFile.close();
		outFile1.close();
		outFile2.close();
	}

}
