# chap07 외부 모듈


## 외부모듈이란 

일반 개발자가 만들어 배포한 모듈
npm을 기반으로 모듈을 공유한다

## npm

Node.js의 패키지를 모아둔 유틸리티
Node.js 패키지 설치, 버전 및 호환성 관리할 수 있다

### package.json

node.js의 프로젝트 설정정보를 담은 파일

### 외부 모듈의 사용

#### 1. 외부 모듈의 설치

```javascript
    // 외부 모듈 ejs를 설치한다
    npm install ejs
    
    // 외부 모듈 jade를 설치한다.
    // jade는 2016년 pug로 이름이 개정되었다.
    // node.js 4버전에서는 jade 모듈을 사용한다.
    npm install jade 
```

#### 2. 외부 모듈의 사용

``` javascript
    // 외부 모듈을 추출한다. 
    var ejs = require('ejs');
    var jade = require('jade');
```


### 7-1 ejs 모듈

특정 형식의 문자열을 HTML 형식의 문자열로 반환한다.

#### 1. 설치

```
npm install ejs 
```

#### 2. 추출

``` javascript 
    // ejs 모듈을 추출한다.
    var ejs = require('ejs') 
```

### ejs 모듈 sample code

ejs 모듈을 사용한 웹페이지 제공 

* app.js

```javascript
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

```
* ejsPage.ejs

``` javascript
<% var name = "soomti" %>
<h1><%=name %></h1>
<p><%= 52 * 273 %></p>
<hr />
<% for(var i = 0 ; i < 10 ; i ++){ %>
    <h2>the square of <%= i %> is <%= i * i %></h2>
<% } %>
```

* result

```
soomti
14196
the square of 0 is 0
the square of 1 is 1
the square of 2 is 4
the square of 3 is 9
the square of 4 is 16
the square of 5 is 25
the square of 6 is 36
the square of 7 is 49
the square of 8 is 64
the square of 9 is 81
```

#### ejs  파일의 특수 태그

밑에 2가지 특수 코드들은 render 메소드를 통해 html의 문자열로 변경된다.

```
<% code %> // 자바스크립트 코드를 입력한다.
<%= value %> // 데이터를 출력한다
```


