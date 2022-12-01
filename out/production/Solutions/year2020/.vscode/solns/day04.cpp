#include "common.h"
#include "utils.cpp"

bool is_hex_notation(string const& s){
  return s.compare(0, 1, "#") == 0
      && s.size() == 7
      && s.find_first_not_of("0123456789abcdef", 1) == string::npos;
}

bool is_valid_number(string const& s){
    return s.size() == 9 
    && s.find_first_not_of("0123456789", 0) == string::npos; 
}

bool validNum(string const& x){
    return x.size() > 0 && x.find_first_not_of("0123456789", 0) == string::npos; 
}

bool validInfo(string tag, string info){
    if(tag == "byr"){
        if(!validNum(info)){
            return false; 
        }
        int x = stoi(info); 
        return x>= 1920 && x <=2002; 
    }
    else if(tag == "iyr"){
        if(!validNum(info)){
            return false; 
        }
        int x = stoi(info); 
        return x>= 2010 && x <=2020; 
    }
    else if(tag =="eyr"){
        if(!validNum(info)){
            return false; 
        }
        int x = stoi(info); 
        return x>= 2020 && x <=2030; 
    }
    else if(tag == "hgt"){
        string units = info.substr(info.size() - 2, 2); 
        string num = info.substr(0, info.size()-2); 
        if(!validNum(num)){
            return false; 
        }
        int x = stoi(num); 

        if(units == "cm"){
            return x >= 150 && x <= 193; 
        }
        else if(units == "in"){
            return x >= 59 && x <= 76; 
        }

    }
    else if(tag == "hcl"){
        return is_hex_notation(info); 
    }
    else if(tag == "ecl"){
        vector<string> valid = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"}; 
        return vector_find(valid, info) != -1; 

    }
    else if(tag == "pid"){
        return is_valid_number(info); 
    }
    return false; 

}

bool isValid(vector<string> inp){
    vector<string> req = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"}; 
    if(inp.size() < 7){
        return false; 
    }

    for(string x: inp){
        string tag = x.substr(0, x.find(":")); 
        string info = x.substr(x.find(":")+1, x.size() - x.find(":") - 1); 

        //if(vector_find(req, tag) != -1){ <- use for part 1
        if(validInfo(tag, info)){            
            req.erase(req.begin() + vector_find(req, tag)); 
        }
    }
    return req.size() == 0; 
}

int main(){
    string inp;
    ifstream input ("..\\inputs\\day4.txt");
    vector<string> cur; 
    int count = 0; 

    while (getline (input, inp)) {
        if(inp == ""){
            if(isValid(cur)){
                count++; 
            }
            cur.clear(); 
        }
        else{
            vector<string> words = string_split(inp, " "); 
            for(string word: words){
                cur.push_back(word); 
            }
        }
    }
    if(isValid(cur)){
        count++; 
    }

    cout << count; 

}