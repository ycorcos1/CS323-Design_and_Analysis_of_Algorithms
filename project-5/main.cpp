#include<iostream>
#include<fstream>
#include<string>
#include<cmath>
#include<cstdlib>
using namespace std;

class QtNode{
    public:
        int color;
        int upperR;
        int upperC;
        int size;
        QtNode* NWkid;
        QtNode* NEkid;
        QtNode* SWkid;
        QtNode* SEkid;

        QtNode(int color, int upperR, int upperC, int size, QtNode* NWkid, QtNode* NEkid, QtNode* SWkid, QtNode* SEkid){
            this->color = color;
            this->upperR = upperR;
            this->upperC = upperC;
            this->size = size;
            this->NWkid = NWkid;
            this->NEkid = NEkid;
            this->SWkid = SWkid;
            this->SEkid = SEkid;
        }

        void printQtNode(ofstream &outFile){
            // if(NWkid == NULL || NEkid == NULL || SWkid == NULL || SEkid == NULL){
            //     outFile << "[" << color << ", " << upperR << ", " << upperC << ", " << size << ", NULL, NULL, NULL, NULL] ";
            // }
            // outFile << "[" << color << ", " << upperR << ", " << upperC << ", " << size << ", " << NWkid->color << ", " << NEkid->color << ", " << SWkid->color << ", " << SEkid->color << "] ";

            int NW;
            if(NWkid != NULL){
                NW = NWkid->color;
            }else{
                NW = 0;
            }
            int NE;
            if(NWkid != NULL){
                NE = NEkid->color;
            }else{
                NE = 0;
            }
            int SW;
            if(NWkid != NULL){
                SW = SWkid->color;
            }else{
                SW = 0;
            }
            int SE;
            if(NWkid != NULL){
                SE = SEkid->color;
            }else{
                SE = 0;
            }
            outFile << "[" << color << ", " << upperR << ", " << upperC << ", " << size << ", " << NW << ", " << NE << ", " << SW << ", " << SE << "]" << endl;
        }
};

class QuadTree{
    public:
        QtNode* QtRoot;
        int numRows;
        int numCols;
        int minVal;
        int maxVal;
        int power2Size;
        int** imgAry;
        int** newImgAry;

        int computePower2(){
            int size = max(numRows, numCols);
            int power2 = 2;
            while(size > power2){
                if(size > power2){
                    power2 *= 2;
                }
            }
            return power2;
        }

        void loadImage(ifstream &inFile){
            int num;
            for(int i = 0; i < power2Size; i++){
                for(int j = 0; j < power2Size; j++){
                    inFile >> imgAry[i][j];
                }
            }
        }

        QtNode* buildQuadTree(int upR, int upC, int size, ofstream &outFile2){
            QtNode* newQtNode = new QtNode(-1, upR, upC, size, NULL, NULL, NULL, NULL);
            if(size == 1){
                newQtNode->color = imgAry[upR][upC];
            }else{
                int halfSize = size/2;
                newQtNode->NWkid = buildQuadTree(upR, upC, halfSize, outFile2);
                newQtNode->NEkid = buildQuadTree(upR, upC + halfSize, halfSize, outFile2);
                newQtNode->SWkid = buildQuadTree(upR + halfSize, upC, halfSize, outFile2);
                newQtNode->SEkid = buildQuadTree(upR + halfSize, upC + halfSize, halfSize, outFile2);

                int sumColor = addKidsColor(newQtNode);

                if(sumColor == 0){
                    newQtNode->color = 0;
                    newQtNode->NWkid = NULL;
                    newQtNode->NEkid = NULL;
                    newQtNode->SWkid = NULL;
                    newQtNode->SEkid = NULL;
                 }else if(sumColor == 4){
                    newQtNode->color = 1;
                    newQtNode->NWkid = NULL;
                    newQtNode->NEkid = NULL;
                    newQtNode->SWkid = NULL;
                    newQtNode->SEkid = NULL;
                }else{
                    newQtNode->color = 5;
                }
            }
            newQtNode->printQtNode(outFile2);
            return newQtNode;
        }

        int addKidsColor(QtNode* node){
            int count = 0;
            if(!isLeaf(node)){
                count = count + node->NWkid->color;
                count = count + node->NEkid->color;
                count = count + node->SWkid->color;
                count = count + node->SEkid->color;
            }
            return count;
        }

        bool isLeaf(QtNode* node){
            if(node->color == 0 || node->color == 1){
                return true;
            }
            return false;
        }

        void preOrder(QtNode* node, ofstream &outFile1){
            if(isLeaf(node)){
                node->printQtNode(outFile1);
            }else{
                node->printQtNode(outFile1);
                preOrder(node->NWkid, outFile1);
                preOrder(node->NEkid, outFile1);
                preOrder(node->SWkid, outFile1);
                preOrder(node->SEkid, outFile1);
            }
        }

        void postOrder(QtNode* node, ofstream &outFile1){
            if(isLeaf(node)){
                node->printQtNode(outFile1);
            }else{
                preOrder(node->NWkid, outFile1);
                preOrder(node->NEkid, outFile1);
                preOrder(node->SWkid, outFile1);
                preOrder(node->SEkid, outFile1);
                node->printQtNode(outFile1);
            }
        }

        void getLeaf(QtNode* node){
            if(isLeaf(node)){
                fillNewImgAry(node);
            }else{
                getLeaf(node->NWkid);
                getLeaf(node->NEkid);
                getLeaf(node->SWkid);
                getLeaf(node->SEkid);
            }
        }

        void fillNewImgAry(QtNode* node){
            int color, R, C, sz;
            color = node->color;
            R = node->upperR;
            C = node->upperC;
            sz = node->size;
            int i = R;
            while(i < R + sz){
                int j = C;
                while(j < C + sz){
                    newImgAry[i][j] = color;
                    j++;
                }
                i++;
            }
        }

        void zero2DAry(int** arr){
            for(int i = 0; i < power2Size; i++){
                for(int j = 0; j < power2Size; j++){
                    arr[i][j] = 0;
                }
            }
        }
};

int main(int argc, const char *argv[]){
    ifstream inFile(argv[1]);
    ofstream outFile1(argv[2]);
    ofstream outFile2(argv[3]);
    ofstream outFile3(argv[4]);
    QuadTree qt;

    inFile >> qt.numRows >> qt.numCols >> qt.minVal >> qt.maxVal;

    qt.power2Size = qt.computePower2();
    outFile2 << "power2Size: " << qt.power2Size << endl;

    qt.imgAry = new int*[qt.power2Size];
    for(int i = 0; i < qt.power2Size; i++){
        qt.imgAry[i] = new int[qt.power2Size];
    }
    qt.zero2DAry(qt.imgAry);
    
    qt.newImgAry = new int*[qt.power2Size];
    for(int i = 0; i < qt.power2Size; i++){
        qt.newImgAry[i] = new int[qt.power2Size];
    }
    qt.zero2DAry(qt.newImgAry);

    qt.loadImage(inFile);
    
    int size = qt.power2Size;
    qt.QtRoot = qt.buildQuadTree(0, 0, size, outFile2);
    
    QtNode* root = qt.QtRoot;
    outFile1 << "preOrder: " << endl;
    qt.preOrder(root, outFile1);
    outFile1 << "postOrder: " << endl;
    qt.postOrder(qt.QtRoot, outFile1);

    qt.getLeaf(qt.QtRoot);
    
    outFile3 << "imgAry: " << endl;
    for(int i = 0; i < qt.power2Size; i++){
        outFile3 << qt.imgAry[i] << " ";
    }
    outFile3 << endl;

    outFile3 << "newImgAry: " << endl;
    for(int i = 0; i < qt.power2Size; i++){
        outFile3 << qt.newImgAry[i] << " ";
    }
    outFile3 << endl;

    inFile.close();
    outFile1.close();
    outFile2.close();
    outFile3.close();
    return 0;
}