// module 추출 
var http = require('http');
var fs = require('fs');
// read html file
http.createServer(function(req,res){
    // html 파일을 불러옴 
    // fs.readFile('path|number|buffer|url|options',callback(err,data)')
    fs.readFile('htmlPage.html',function(err,data){
        res.writeHead(200,{'Content-Type':'text/html'});
        res.end(data);
    })
}).listen(22222,function(){
    console.log("server start!");
});
// read jpeg file
http.createServer(function(req,res){
    fs.readFile('pooh.png',function(err,data){
        res.writeHead(200,{'Content-Type': 'image/jpeg'});
        res.end(data);
    })
}).listen(22223,function(){
    console.log("hello world");
})

// read audio file
http.createServer(function(req,res){
    fs.readFile('hi.mp3',function(err,data){
        res.writeHead(200,{'Content-Type': 'audio/mp3'});
        res.end(data);
    })
}).listen(22224,function(){
    console.log("22224 start")
})