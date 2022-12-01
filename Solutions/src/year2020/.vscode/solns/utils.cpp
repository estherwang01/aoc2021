#include <iostream>
#include <string>
#include <fstream>
#include <vector>

using namespace std; 

vector<string> string_split(string text, string delimiter){
    vector<string> words;
    int start = 0;
    int end = text.find(delimiter); 
    while(end != -1){
        string cur = text.substr(start, end-start); 
        words.push_back(cur); 
        start = end + delimiter.size(); 
        end = text.find(delimiter, start); 
    }
    string last = text.substr(start, text.size() - start); 
    words.push_back(last); 
    return words; 
}

template < typename T>
int vector_find(const vector<T> & v, const T & elem){
    int current = 0; 
    for(auto x: v){
        if(x == elem){
            return current; 
        }
        current++; 
    }
    return -1; 
}