# Chap06 Http Module
Node.js 에서 사용하는 기본적인 웹 모듈로, 웹 서버와 클라이언트 생성에 관한 기능을 담당한다

## HTTP (HyperText Transefer Protocol) 
### BASIC
##### HyperText
참조`Hyper Link`를 통해 독자가 한 문서에서 다른 문서로 즉시 접근할 수 있는 텍스트
##### Protocol
외교 쪽에서 사용되는 용어. 국가간에 약속을 의미한다.
통신에서는 어떤 시스템이 다른 시스템과 통신을 원활하게 해주는 약속으로 정의된다
#### HTTP? 
`TCP/IP(80)` 를 이용하여 컴퓨터와 컴퓨터 사이에 데이터를 주고받는 프로토콜.
 가장 기본적인 `web module`로 사용된다

#### HTTP 특징 
##### 방식
 `웹 브라우저`를  통해 `클라이언트의 요청`으로 `서버`에 접속하여  `클라이언트 요청`에 대한 `응답`을 전송 후 연결을 종료한다
 
##### 장점
 - 연결 상태를 유지하지 않기 때문에 전산 자원이 적게 든다

##### 단점
-  연결이 지속적이지 않기에 추가적인 `클라이언트 요청`시 어떤 클라이언트 이 요청인지 모른다

- 다수의 `클라이언트 요청` 각각의 요청을 구분 할 수 없어 제대로 된 응답인 데이터를 전송 할 수 없다

##### 단점 솔루션 

- Cookie
- Session
- URL Rewriting
- Hidden Form Field
   
##### 참고사항

연결 상태 유지는 프로토콜: `FTP`, `Telnet`



## 1. 요청과 응답 

#### web server 
요청을 받고 그에 대한 응답을 전송하는 역할

`사용자 요청` : url 을 이용해 요청 

`서버 응답` : 해당 url을 받고 그에 대한 응답 제공 

##### 클라이언트 : 요청하는 대상 `url` 로 요청한다
##### 서버 : 응답하는 대상 `html`파일을 제공한다



###### sample
###### `option`+`command`+`I` > `Network` 탭에서 요청, 응답 메세지를 확인할 수 있다

Request message
```html
- Request URL : http://127.0.0.1:52273/
- Request Method : GET

- Request Headers
Accept : text/html , application/xhtml#xml,application/xml; q = 0.9,*/*; q = 0.8
...
```
Response message
```html
- Status Code : 200 OK

- Request Header 
Connectioon : Keep -alive

- Response Data
<!DOCTYPE html>
...
```

## 2. server 객체

`http` 에서 가장 중요한 객체 

###### sample code

```javascript
// http module 추출
const http = require('http');

// web server를 생성
var server = http.createServer();

// web server 실행
server.listen(22222);
```

#### server method

`listen(port[,callback]` : start server
`close([callback])` : close server

###### sample
```javascript
// web server 실행
// server 실행 시, 터미널에서 확인 후 강제종료(command+c)를 누르고 다시키면 안되는 경우가 발생. 이럴때는 터미널을 아예 종료하고 키면 해결된다. 
server.listen(22222,function(){
    console.log('server running at http://127.0.1:22222')
});

//10초 후 함수 실행
var test = function(){
    //close server
    server.close();
};
// 1초 뒤 test 실행 
setTimeout(test,1000);
```
#### server event
`request` : 클라이언트가 요청 시 발생
`connection` : 클라이언트 접속 시 발생
`close` : 서버 종료 시 발생
`checkContinue` : 클라이언트가 계속 연결 할 때 발생
`upgrade` : 클라이언트가 HTTP 업그레이드 요청 시 발생
`clientError` : 클라이언트 오류 시 발생 

###### sample
```javascript
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
```
###### result
```
Connection On: 클라이언트 접속 시 발생
request On: 클라이언트가 요청 시 발생
Connection On: 클라이언트 접속 시 발생
request On: 클라이언트가 요청 시 발생
Connection On: 클라이언트 접속 시 발생
request On: 클라이언트가 요청 시 발생
Connection On: 클라이언트 접속 시 발생
request On: 클라이언트가 요청 시 발생
```