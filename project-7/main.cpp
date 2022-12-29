/*
Yahav Corcos
Professor Phillips
CS323 Project 7 - Prim's MST
November 14, 2022
*/
#include<fstream>
#include<cstdlib>
#include<iostream>
#include<string>
using namespace std;

class uEdge{
    public:
        int Ni;
        int Nj;
        int cost;
        uEdge *next;
        uEdge(int Ni, int Nj, int cost, uEdge* next){
            this->Ni = Ni;
            this->Nj = Nj;
            this->cost = cost;
            this->next = next;
        }
        void printEdge(ofstream &debugFile){
            if(next == NULL){
                debugFile << "<" << Ni << ", " << Nj << ", " << cost << ", NULL> --> NULL";
            }else
            debugFile << "<" << Ni << ", " << Nj << ", " << cost << ", " << next->Ni << "> --> ";
        }
};

class PrimMST{
    public:
        int numNodes;
        int nodeInSetA;
        char* whichSet;
        uEdge *edgelistHead;
        uEdge *MSTlisthead;
        int totalMSTCost;

        void listInsert(uEdge* edge){
            uEdge* temp = edgelistHead;
            while(temp->next != NULL && edge->cost > temp->next->cost){
                temp = temp->next;
            }
            edge->next = temp->next;
            temp->next = edge;
        }

        uEdge* removeEdge(){
            uEdge* temp = edgelistHead;
            while(temp->next != NULL && !(whichSet[temp->next->Ni] != whichSet[temp->next->Nj] && (whichSet[temp->next->Ni] == 'A' || whichSet[temp->next->Nj] == 'A'))){
                temp = temp->next;
            }
            uEdge* edge = temp->next;
            temp->next = edge->next;
            edge->next = NULL;
            return edge;
        }

        void addEdgeToMST(uEdge* edge){
            edge->next = MSTlisthead->next;
            MSTlisthead->next = edge;
        }

        void printSet(ofstream &debugFile){
            debugFile << "whichSet[]: ";
            for(int i = 0; i<numNodes+1; i++){
                debugFile << whichSet[i] << " ";
            }
        }

        void printEdgeList(ofstream &debugFile){
            uEdge* temp = edgelistHead;
            debugFile << "edgelistHead --> ";
            while(temp->next != NULL){
                temp->printEdge(debugFile);
                temp = temp->next;
            }
            temp->printEdge(debugFile);
        }

        void printMSTList(ofstream &debugFile){
            uEdge* temp = MSTlisthead;
            debugFile << "MSTlisthead --> ";
            while(temp->next != NULL){
                temp->printEdge(debugFile);
                temp = temp->next;
            }
            temp->printEdge(debugFile);
        }

        bool isEmpty(){
            for(int i = 0; i<numNodes + 1; i++){
                if(whichSet[i] != 'A'){
                    return false;
                }
            }
            return true;
        }

        void updateMST(uEdge* edge){
            addEdgeToMST(edge);
            totalMSTCost += edge->cost;
            if(whichSet[edge->Ni] == 'A'){
                whichSet[edge->Nj] = 'A';
            }else{
                whichSet[edge->Ni] = 'A';
            }
        }
};

int main(int argc, char *argv[]){
    PrimMST prim;
    ifstream inFile(argv[1]);
    ofstream MSTfile(argv[3]);
    ofstream debugFile(argv[4]);
    inFile >> prim.numNodes;
    string nodeInSetAStr(argv[2]);
    prim.nodeInSetA = stoi(nodeInSetAStr);
    prim.whichSet = new char[prim.numNodes+1];
    for(int i =0; i < prim.numNodes+1; i++){
        prim.whichSet[i] = 'B';
    }
    prim.whichSet[0] = 'A';
    prim.whichSet[prim.nodeInSetA] = 'A';
    prim.printSet(debugFile);
    debugFile << "\n\n";
    prim.edgelistHead = new uEdge(0, 0, 0, NULL);
    prim.MSTlisthead = new uEdge(0, 0, 0, NULL);
    prim.totalMSTCost = 0;
    
    int Ni, Nj, cost;
    while(inFile >> Ni >> Nj >> cost){
        uEdge* newEdge = new uEdge(Ni, Nj, cost, NULL);
        prim.listInsert(newEdge);

        prim.printEdgeList(debugFile);
        debugFile << endl;
    }
    debugFile << endl;

    while(!prim.isEmpty()){
        uEdge* nEdge = prim.removeEdge();
        nEdge->printEdge(debugFile);
        debugFile<<endl;
        prim.updateMST(nEdge);
        prim.printSet(debugFile);
        debugFile << endl;
        prim.printEdgeList(debugFile);
        debugFile << endl;
        prim.printMSTList(debugFile); 
        debugFile << "\n\n";  
    }

    MSTfile << "*** Prim's MST of the input graph, G is: ***"<<endl;
    MSTfile << "numNodes: " << prim.numNodes << endl;
    MSTfile << "MSTlist: ";
    prim.printMSTList(MSTfile);
    MSTfile << endl;
    MSTfile << "*** MST total code = " << prim.totalMSTCost;

    MSTfile.close();
    debugFile.close();
    inFile.close();
    return 0;
}
