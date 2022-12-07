const fs = require('fs');
const allContents = fs.readFileSync('day4input.txt', 'utf-8');
var total = 0 
allContents.split(/\r?\n/).forEach((line) => {
    var ranges = line.split(',')
    var elf1 = ranges[0].split('-')
    var elf2 = ranges[1].split('-')
    if(parseInt(elf1[0]) <= parseInt(elf2[1]) && parseInt(elf2[0]) <= parseInt(elf1[1])) {total++ }

});
console.log(total)