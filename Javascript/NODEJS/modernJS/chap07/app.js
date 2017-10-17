require('http').createServer(function(req,res){
    if(req.url == '/'){
        //response
        res.write('<!DOCTYPE html>');
        res.write('<html>');
        res.write('<head>');
        res.write('     <title>forever</title>');
        res.write('</head>');
        res.write('<body>');
        res.write('     <h1>FOREVER</h1>');
        res.write('</body>');
        res.write('</html>');
        res.end();
    }
    else{
        //에러발생시키기
        error.error.error();
    }
}).listen(52273,function(){
    console.log('server run!!');
})

/*
// 파일 시스템 모듈 사용
var fs = require('fs');
// 서버 생성을 위한 http모듈 사용 
var http = require('http');
// ejs 모듈 추출 
var ejs = require('ejs');

// 서버 생성 및 실행
http.createServer(function(req,res){
    fs.readFile('ejsPage.ejs','utf-8',function(err,data){

        // 응답 스트림에 헤더를 작성
        // writeHead 메소드는  헤더를 작성한다.
        // html 파일 형식으로 응답을 보낸다
        res.writeHead(200,{'Content-Type': 'text/html'});

        // ejs 문자열을 html 문자열로 변경해준다.
        res.end(ejs.render(data));

    });
}).listen(52273,function(){
    console.log('server run');
})
*/