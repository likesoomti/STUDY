// http module 추출
const http = require('http');

// web server를 생성
var server = http.createServer();

// web server 실행
server.listen(22222,function(){
    console.log('server running at http://127.0.1:22222')
});

//10초 후 함수 실행
var test = function(){
    //close server
    server.close();
};
setTimeout(test,1000);

