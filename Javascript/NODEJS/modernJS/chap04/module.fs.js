//file system은 다른 모듈과 다르게 줄임말 사용
var fs = require('fs');
//fs.readFileSync(path,encoding)
var text = fs.readFileSync('textfile.txt','utf8');
console.log(text);
/*
//fs.readFile(path,encoding,callback)
fs.readFile('textfile.txt','utf8',function(error,data){
    console.log(data)
})
*/

var data = 'hello world.. soomin jang!';

// 비동기 파일 읽기
// 동기식과는 다르게 콜백이 붙음 
// fs.writeFile(path,data,encoding,callback)
fs.writeFile('writeFile.txt',data,'utf8',function(err){
    console.log('write is complete')
})


// 동기 파일 읽기 
//fs.writeFileSync(path,data,encoding)
fs.writeFileSync('writeFileSync.txt',data,'utf8')
console.log('write sync is complete');