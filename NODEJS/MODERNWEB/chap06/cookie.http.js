var http = require('http');

http.createServer(function(req,res){

    //date 추가
    var date = new Date();
    date.setDate(date.getDate()+7);

    res.writeHead(200,{
        'Content-Type' :'text/html',
        // 쿠키 생성
        //'Set-Cookie':['breakfast = toast', 'dinner = chicken']

        // 별도 쿠키 추가 

     
        'Set-Cookie':[
            'breakfast = toast;Expires ='+date.toUTCString(),
            'dinner = chicken'
        ]
    });

    res.end('<h1>'+req.headers.cookie+'</h1>');
    
}).listen(22222,function(){
    console.log("Server Running start 22222 ")
})