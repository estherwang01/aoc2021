#include <iostream>
#include <string>
#include <fstream>

using namespace std; 

int isValid(string pass){
    int a = pass.find("-"); 
    int b = pass.find(" "); 
    int c = pass.find(":"); 

    int low = stoi(pass.substr(0, a)); 
    int high = stoi(pass.substr(a+1, b-a-1)); 
    string letter = pass.substr(b+1, c-b-1); 
    string password = pass.substr(c+2); 

    //part 1
    int count = 0; 
    for(int i =0; i<password.length(); i++){
        if(password.substr(i, 1) == letter){
            count++; 
        }
    }
    bool part1 = count >= low && count <= high; 
    bool part2 = (password.substr(low-1, 1) == letter) != (password.substr(high-1, 1) == letter); 

    if(part1 && part2){
        return 3; 
    }
    if(part1){
        return 1; 
    }
    if(part2){
        return 2; 
    }
    return 0; 
}


int main(){
    string inp;

    ifstream input ("..\\inputs\\day2.txt");
    int count1 = 0; 
    int count2 = 0; 
    while (getline (input, inp)) {
        int r = isValid(inp); 
        if(r == 3){
            count1++; 
            count2++; 
        }
        else if(r == 2){
            count2++; 
        }
        else if(r == 1){
            count1++; 
        }
    }
    cout << count1 << endl;
    cout << count2 << endl;  
}