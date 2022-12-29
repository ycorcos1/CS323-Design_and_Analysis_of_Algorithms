//
//  main.cpp
//  CorcosY_Project1_CPP
//
//  Created by Yahav Corcos on 9/2/22.
//

#include <iostream>
#include <fstream>
#include <string>
using namespace std;

int main(int argc, const char * argv[]) {
    ifstream myFile;
    myFile.open(argv[1]);
    
    if(!myFile.is_open()){
        cout<<"Unable to open file"<<endl;
        exit(1);
    }
    string word;
    while(myFile >> word){
        cout<<"Word: "<<word<<endl;
    }
    
    return 0;
}
