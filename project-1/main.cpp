/*
Yahav Corcos
Professor Phillips
CS323 Project 1 - LinkedList Implementation of Stack & Queue
September 9, 2022
*/
#include<iostream>
#include<cstdlib>
#include<fstream>
#include<string>
using namespace std;

class listNode{
    public:
        string data;
        listNode* next;

        listNode(string data){
            this->data = data;
            this->next = NULL;
        }

        void printNode(ofstream &outFile){
            if(next == NULL){
                outFile << "(" << data << ", NULL)";
            }
            outFile << "(" << data << ", " << next->data << ") -->";
        }
};

class LLStack{
    public:
        listNode* top;
        
        LLStack(){
            top = new listNode("dummy");
        }

        void push(listNode* newNode){
            newNode->next = top->next;
            top->next = newNode;
        }

        bool isEmpty(){
            if(top->next == NULL){
                return true;
            }
            return false;
        }

        listNode* pop(){
            listNode* temp = top->next;
            top->next = temp->next;
            temp->next = NULL;
            return temp;
        }

        void buildStack(ifstream &inFile, ofstream &outFile1){
            string op;
            string data;
            while(inFile >> op >> data){
                if(op == "+"){
                    outFile1 << data << " is being pushed into stack" << endl;
                    listNode* newNode = new listNode(data);
                    push(newNode);
                }else if(op == "-"){
                    outFile1 << top->next->data << " is being popped from Stack" << endl;
                    listNode* junk = pop();
                    if(junk != NULL){
                        junk = NULL;
                    }else{
                        outFile1 << "Stack is empty" << endl;
                    }
                }
                printStack(outFile1);
                outFile1 << endl;
            }
        }

        void printStack(ofstream &outFile1){
            listNode* temp = top;
            outFile1 << "Top: ";
            while(temp->next != NULL){
                temp->printNode(outFile1);
                temp = temp->next;
            }
            outFile1 << endl;
        }
};

class LLQueue{
    public:
        listNode* head;
        listNode* tail;
        
        LLQueue(){
            listNode* dummy = new listNode("dummy");
            head = tail = dummy;
        }

        void insertQ(listNode* newNode){
            newNode->next = tail->next;
            tail->next = newNode;
            tail = newNode;
        }

        listNode* deleteQ(){
            listNode* temp = head->next;
            head->next = temp->next;
            temp->next = NULL;
            return temp;
        }

        bool isEmpty(){
            if(head == tail){
                return true;
            }
            return false;
        }

        void buildQueue(ifstream &inFile, ofstream &outFile2){
            char op;
            string data;
            while(inFile >> op >> data){
                if(op == '+'){
                    outFile2 << data << " is being added to the queue" << endl;
                    listNode* newNode = new listNode(data);
                    insertQ(newNode);
                }else if(op == '-'){
                    outFile2 << head->next->data << " is being deleted" << endl;
                    listNode* junk = deleteQ();
                    if(junk != NULL){
                        junk = NULL;
                    }else{
                        outFile2 << "Queue is empty" << endl;
                    }
                }
                printQueue(outFile2);
                outFile2 << endl;
            }
        }

        void printQueue(ofstream &outFile2){
            listNode* temp = head;
            while(temp->next != NULL){
                temp->printNode(outFile2);
                temp = temp->next;
            }
            outFile2 << endl;
        }
};

int main(int argc, const char *argv[]){
    ifstream inFile(argv[1]);
    ofstream outFile1(argv[2]);
    ofstream outFile2(argv[3]);
    LLStack S;
    S.buildStack(inFile, outFile1);
    inFile.close();
    inFile.open(argv[1]);
    LLQueue Q;
    Q.buildQueue(inFile, outFile2);
    inFile.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
