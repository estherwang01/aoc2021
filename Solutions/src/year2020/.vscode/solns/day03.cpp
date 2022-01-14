#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std; 

int calc(int right, int down, int len, vector<vector<string>> m){
    int i = 0; 
    int j = 0; 
    int count = 0; 
    while(i < m.size()){
        if(m[i][j] == "#"){
            count++; 
        }
        i+= down; 
        j = (j+right) % len; 
    }
    return count; 
}

int main(){
    string inp;
    ifstream input ("..\\inputs\\day3.txt");
    vector<vector<string>> m; 

    vector<vector<int>> slopes = {{1,1}, {3,1}, {5,1}, {7,1}, {1,2}}; // part 2
    // vector<vector<int>> slopes = {{3,1}}; part 1 

    int len = -1; 
    while (getline (input, inp)) {
        vector<string> cur; 
        cur.clear(); 
        for(int i =0; i<inp.length(); i++){
            cur.push_back(inp.substr(i, 1)); 
        }
        len = cur.size(); 
        m.push_back(cur); 
    }

    unsigned long long prod = 1; 
    for(vector<int> v: slopes){
        prod *= calc(v[0], v[1], len, m); 
    }

    cout << prod; 

}