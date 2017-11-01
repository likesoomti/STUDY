// server 객체의 이벤트 
var http = require('http');
var server = http.createServer();

// 클라이언트가 요청 시 발생
server.on('request',function(code){
    console.log('request On: 클라이언트가 요청 시 발생');
})
// 클라이언트 접속 시 발생
server.on('connection',function(code){
    console.log('Connection On: 클라이언트 접속 시 발생')
})
// 클라이언트 종료 시 발생
server.on('close',function(code){
    console.log('Close On : 클라이언트 종료 시 발생')
})

server.listen(22222);