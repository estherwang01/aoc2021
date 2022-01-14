#include <iostream>
#include <fstream>
#include <string>
#include <unordered_set>
#include <vector>
using namespace std; 

int main()
{
    string inp;
    unordered_set <int> m; 

    ifstream input ("..\\inputs\\day1.txt");
    while (getline (input, inp)) {
        int i = stoi(inp); 

        if(m.find(2020-i) != m.end()){
            //part 1
            cout << (2020-i)*i;
            cout << endl; 
        }
        m.insert(i); 
    }

    //ok nvm that. part 2: 
    vector<int> nums (m.begin(), m.end()); 

    for(int i = 0; i< nums.size(); ++i){
        for(int j =0; j<nums.size(); ++j){
            for(int k =0; k<nums.size(); ++k){
                if(i != j && i !=k && j != k){
                    if(nums[i] + nums[j] + nums[k] == 2020){
                        unsigned long x = nums[i] * nums[j] * nums[k]; 
                        cout << x; 
                        cout << endl; 
                        return 0; 
                    }
                }

            }
        }
    }

    input.close();
}