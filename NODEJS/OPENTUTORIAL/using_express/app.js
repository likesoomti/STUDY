//약속이니까 이렇게 쓰자. 
var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.set('view engine', 'jade');
app.set('views', './view');
/*

app.use(bodyParser.json()); // application/json 파싱하기 위해 설정
app.use(bodyParser.urlencoded({ extended: false })); // application/x-www-form-urlencoded 파싱 설정
위에서 extended의 값을 true 일 경우는 언제일가 생각해 봤습니다.
false는 한번의 파싱으로 값을 가져오는데 true 중첩된 객체까지 파싱해 값을 가져온다

*/
// url로 접속하기 위한 get방식 메소드 호출. 
// '/' 방식으로 들어왔을때 요청한 정보에 대한 데이터를 req
// res에는 요청에 대한 답을 데이터에 넣는다. 
app.use(express.static('public'));
app.use(bodyParser.urlencoded({ extended: false}));
app.get('/topic',function(req,res){

    var topic = [
        'javascipt is ..',
        'nodejs is...',
        'express is ...'
    ]

    var output = 
    `
        <a href="/topic?id=0">javascipt</a><br>
        <a href="/topic?id=1">nodejs</a><br>
        <a href="/topic?id=2">express</a><br>
        ${topic[req.query.id]}
    `
    res.send(output);
});

app.get('/topic/:id',function(req,res){

    var topic = [
        'javascipt is ..',
        'nodejs is...',
        'express is ...'
    ]

    var output = 
    `
        <a href="/topic/0">javascipt</a><br>
        <a href="/topic/1">nodejs</a><br>
        <a href="/topic/2">express</a><br>
        ${topic[req.params.id]}
    `
    res.send(output);

});


app.get('/form', function(req,res){
    res.render('form');
});
app.get('/template',function(req,res){

    res.render('template',{ _title : 'soomin title', 
                             time: Date()
                           });
});

app.get('/',function(req,res){

    //send에 인자로 보내준다. 
    res.send('soomin worlds');

});
app.get('/form_receiver',function(req,res){
    var title = req.query.title;
    var description = req.query.description;
    res.send(title +","+description+",helloget");
    res.send('hello get')
});
app.post('/form_receiver',function(req,res){
    // post 는 body로 가져온다 => req.body.title error 
    // 그때 이걸 사용할때는 body-parser나 multer를 사용해야한다.
    // body paser :확장기능. 정확한 용어는 middle ware 
    // body parser 설치해야함 
    // npm install body-parser --save

    var title = req.body.title
    var description = req.body.description
    res.send(title+","+description+", hellopost");
});
app.get('/dynamic',function(req,res){
    var list = '';
    for ( var i = 0 ; i < 5 ; i ++)
        list += '<li>hello coding</li>'
    var html = 
    ` 
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Document</title>
    </head>
    <body>
        <h1>hello dynamic</h1>
    
        <ul>
            ${list}
        </ul>
        
    </body>
    </html>
    `;

    res.send(html);
});
app.get('/route',function(req,res){
    res.send('Hello Router, <img src="/soomti2.jpg">')
});

app.get('/login', function(req,res){
    res.send('login plz');
    
});
//우리 app이 3000번 port를 listen할 수 있다. 
app.listen(3000,function(){
    console.log('connected시 보여준다.')
}); 