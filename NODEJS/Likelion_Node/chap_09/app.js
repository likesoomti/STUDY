var fs = require('fs');
var ejs = require('ejs');
var mysql = require('mysql');
var express = require('express');
var bodyParser = require('body-parser');

// DB 연결
var client = mysql.createConnection({
  user:'root',
  password:'ajyabr2368',
  database:'o2'
});

// 서버 생성
var app = express();
app.use(bodyParser.urlencoded({
  extended: false
}));

// 서버 실행
app.listen(52273, function() {
  console.log('server running at http://127.0.0.1:52273');
});

// routes 수행
app.get('/', function(request, response) {
  // read file
  fs.readFile('list.html', 'utf8', function(error, data) {
    // DB query 실행
    client.query('SELECT * FROM products', function(error, results) {
      // 응답
      response.send(ejs.render(data, {
        data: results
      }));
    });
  });
});

// Delete
app.get('/delete/:id', function(request, response) {
  // DB query 실행
  client.query('DELETE FROM products WHERE id = ?', [request.params.id], function() {
    // 응답
    response.redirect('/');
  });
});

// Create
app.get('/insert', function(request, response) {
  // read file
  fs.readFile('insert.html', 'utf8', function(error, data) {
    // 응답
    response.send(data);
  });
});
// Create
app.post('/insert', function(request, response) {
  // 변수 선언
  var body = request.body;

  // DB query 실행
  client.query('INSERT INTO products (name, modelnumber, series) VALUES (?,?,?)', [body.name, body.modelnumber, body.series], function() {
    // 응답
    response.redirect('/');
  });
});

// Update
app.get('/edit/:id', function(request, response) {
  // 파일 읽기
  fs.readFile('edit.html', 'utf8', function(error, data) {
    // DB query 실행
    client.query('SELECT * FROM products WHERE id = ?', [request.params.id], function(error, result) {
      // 응답
      response.send(ejs.render(data, { data : result[0]}));
    });
  });
});
// Update
app.post('/edit/:id', function(request, response) {
  // 변수 선언
  var body = request.body;

  // DB query 실행
  client.query('UPDATE products SET name=?, modelnumber=?, series=? WHERE id=?',[body.name, body.modelnumber, body.series, request.params.id], function() {
    response.redirect('/');
  });
});
