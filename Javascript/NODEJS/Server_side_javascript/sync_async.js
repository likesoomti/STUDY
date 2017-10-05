var fs = require('fs');


//sync 
console.log("1");
var data = fs.readFileSync('data.txt',{encoding: 'utf-8'});

console.log(data);
//
//async
console.log("2");
var data = fs.readFile('data.txt',{encoding: 'utf-8'},function(error,data){
    console.log(data);
    console.log("3")
});

console.log("4");