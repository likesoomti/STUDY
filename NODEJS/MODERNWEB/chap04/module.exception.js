const fs = require('fs');


try{
    var data = fs.readFileSync('textfile.txt','utf8');
    console.log(data);
}catch(e){
    console.log(e);
}
// 비동기식 함수에서는 callback args 를 err,data 순서대로 받는다.
// 첫 error 를 호출하는 형식으로 받는다. 
fs.readFile('textfile.txt','utf8',function(err,data){
    //간결화
    //if(err) console.log(err);
    if(err){
        console.log(err);
    }
    else{
        console.log(data);
    }

});

