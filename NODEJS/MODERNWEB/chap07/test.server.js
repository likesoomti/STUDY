//모듈 추출
var http = require('http');

//서버 실행
http.createServer(function(req,res){
    //헤더 설정
    res.writeHead(200,{'Content-Type': 'text/html'});
    res.end('<h1>test - file - 2</h1>')
}).listen(52273,function(){
    console.log('test changed!');
})