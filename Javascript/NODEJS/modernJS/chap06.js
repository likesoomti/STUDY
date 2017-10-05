var http = require('http');

var server = http.createServer();

server.on('request',function(code){
    console.log('Request on');
})
server.on('connection',function(code){
    console.log('connection on');
})
server.on('close',function(code){
    console.log('close on');
})

server.listen(52273);