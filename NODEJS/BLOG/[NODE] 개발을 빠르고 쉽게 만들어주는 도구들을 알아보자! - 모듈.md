
## 모듈 

`모듈`은 Node.js에서 사용하는 라이브러리를 말합니다. Node의 모듈은 크게  기본적으로 제공하는 `내장모듈`과 일반 개발자가 만들어 배포한 `외부모듈`로 나뉩니다.
모듈 = 라이브러리
모듈의 메서드 = 모듈이 가지고 있는 모든 함수 
미들웨어 = 모듈이 가지고 있는 함수 중 개발자들이 커스텀 한 것..........

## 미들웨어

`미들웨어`는 여러가지 동작 함수를 말하는데요. 이 여러 `미들웨어`들이 모여 하나의 `모듈`을 구성합니다. 내장모듈을 살펴보면서 모듈과 미들웨어의 차이점을 알아봅시다.

## 내장 모듈 살펴보기 
Node.js DOC 홈페이지에서 기본 내장 모듈인 `OS` 모듈을 살펴봅시다.

### OS(모듈이름)
###### mark
```
Stability: 2 - Stable
```
###### description

The ```os``` module provides a number of operating system-related utility methods. It can be accessed using:
###### sample
```
const os = require('os');
```
--------- 

### os 모듈의 메서드
간략한 설명 밑에 주요 메서드 들을 확인할 수 있습니다.

#### os.hostname() : 운영체제 호스트 이름 리턴
#### os.type() : 운영체제 이름 리턴
#### os.platform() : 운영체제 플랫폼 리턴 
#### os.arch(): 운영체제의 아키텍처 리턴 
#### os.release() : 운영체제 버전 리턴 
#### os.uptime() : 운영체제 실행된 시간 리턴
#### os.loadavg() : 평균 로드시간 리턴
#### os.freemem() : 시스템 가용 메모리 리턴
#### os.cpus(): cpu 정보 객체 리턴 
##### Added in: v0.3.3
- Returns: Array

The os.cpus() method returns an array of objects containing information about each CPU/core installed.

The properties included on each object include:

- model :string
- speed :number (in MHz)
- times :Object
- user :number
- nice :number
등등등..
```javascript
console.log(os.cpus());
```
###### result
```javascript
[ { model: 'Intel(R) Core(TM) i5-5257U CPU @ 2.70GHz',
    speed: 2700,
    times: { user: 10158440, nice: 0, sys: 7051720, idle: 51757490, irq: 0 } },~
     ]
```


#### os.networkInterfaces(): 네트워크 정보 배열 리턴
##### Added in: v0.6.0
- Returns: Object

The os.networkInterfaces() method returns an object containing only network interfaces that have been assigned a network address.

Each key on the returned object identifies a network interface. The associated value is an array of objects that each describe an assigned network address.

The properties available on the assigned network address object include:

- address : string 
- netmask : string 
- family : string>
- mac : string
- internal : boolean    
- scopeid : number

###### sample
```javascript
console.log(os.networkInterfaces());
```
###### result
```javascript
{ lo0:
   [ { address: '::1',
       netmask: 'ffff:ffff ~',
       family: 'IPv6',
       mac: '00:00:00:00:00:00',
       scopeid: 0,
       internal: true },
      ],
}
```

### 외부모듈? 
외부 모듈은 Node.js에서 기본적으로 제공하는 모듈이 아닌, 일반 개발자가 만들어 배포한 모듈입니다. npm을 통해 모듈을 설치할 수 있습니다

### npm
Npm은 Node.js의 외부 모듈들을 설치할 수 있는 유틸리티를 말합니다. 휴대폰에 어플을 설치할 때 앱스토어 / 또는 구글 플레이 스토어를 이용하듯이 Node.js에서 라이브러리를 설치할 경우에 사용하는 유틸리티입니다. npm에서 패키지 설치 및 버전 및 호환성 관리할 수 있다

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
## 미들웨어

미들웨어(`middleware`)는 응용 소프트웨어가 운영 체제로부터 제공받는 서비스 이외에 추가적으로 이용할 수 있는 라이브러리를 말합니다.

여러가지 미들웨어가 존재하는데, 하나하나 자세하게 알아봅시다. 

## body-parser
`Node.js` 에서 데이터를 post 요청 데이터를 보낼 수 있는 미들웨어 모듈 
`req.body` property 속성이 부여된다.

## cookie-parser
`Node.js` 에 쿠키를 사용하기 위한  미들웨어 모듈 
`req.cookie` property 를 사용할 수 있다

## gravator

Global+Recoginze+Avator
프로필을 업로드하면, 그라바타를 사용하는 사이트에 이미지가 자동으로 업로드되는 모듈 

- https://www.npmjs.com/package/gravatar


#### connect-flash

일회성 메세지. 한번 보내고 나면 사라진다.

- https://m.blog.naver.com/PostView.nhn?blogId=rwans0397&logNo=220680181786&proxyReferer=https:%2F%2Fwww.google.co.kr%2F


## mongoose

MongoDB 기반 ODM(Object Data Mapping). Rails의 ActiveRecord 인듯하다.MongoDB의 데이터를 Javascript 객체 기반으로 활용할 수 있다

- https://velopert.com/594

## express-session

 세션 데이터를 서버에 저장한다. 쿠키에는 데이터가 아닌 ID를 저장하여 Id를 가지고 사용한다.develop 모드에서 사용하는 듯..?

 - http://expressjs.com/ko/advanced/best-practice-security.html

## connect-mongo

몽고 DB - Node.js 연결해주는 드라이버 
- https://www.npmjs.com/package/connect-mongodb

## bcypt-nodejs
패스워드를 암호화 해주는 모듈 
- https://www.npmjs.com/package/bcrypt-nodejs

## passport 

node.js용 범용 인증 모듈로, 다양한 인증 프로토콜을 지원한다.(HTTP Basic Auth, HTTP digest authentication, OAuth,OpenID)
로그인 쉽게 가능! 

passport는 Strategy(전략)이라는 것을 사용하여 인증을 실행한다.
- http://bcho.tistory.com/920 

## passport-local

passport 인증 모듈에서 로컬 유저/패스워드를 인증해주는 모듈 

## morgan 

웹에서 요청이 들어왔을 때 로그를 출력해주는 미들 웨어

## path

파일의 경로를 다루는 코어 모듈로, 파일명 추출 ,디렉토리 추출, 경로생성 , 확장자 추출 등의 기능을 제공해준다.