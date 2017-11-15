# MVC 디자인패턴을 이용한 트위터 스타일 애플리케이션 만들기


## MVC 패턴

`MVC`는 소프트웨어에서 사용되는 디자인 패턴이다. 사용자가 보는 `UI` 부분과, 보이지않는 `비즈니스 로직` 을 분리해서 영향없이 어플리케이션을 만들 수 있다. 

MVC에서 
- `모델`은 애플리케이션의 정보(데이터)를 나타내며,
- `뷰`는 체크박스,텍스트 항목 등과 같은 UI,
- `컨트롤러`는 데이터와 비즈니스 로직 사이의 상호동작을 관리한다.

## Express.js

`Node.js`에서 웹 및 모바일 어플리케이션을 만들수 있는 `프레임워크` 

### Node.js 설치하기

#### 1 버전 체크
```
$ node -v
$ npm -v
```

#### 2 yeoman 설치 

##### yeoman
스캐폴딩을 자동으로 만들어주는 툴. CLI 기반이기 떄문에 제너레이터를 함께 사용한다

```
$ npm install -g yo
```

### Express generator 설치

```
$ npm install -g express
$ npm install -g express-generator
```

### Application Server 구축 

익스프레스와 미들웨어를 사용하여 어플리케이션 구축 

#### 미들웨어 (`Middle Ware`)
미들웨어(`middleware`)는 응용 소프트웨어가 운영 체제로부터 제공받는 서비스 이외에 추가적으로 이용할 수 있는 라이브러리! 

##### body-parser
`Node.js` 에서 데이터를 post 요청 데이터를 보낼 수 있는 미들웨어 모듈 
`req.body` property 속성이 부여된다.
##### cookie-parser
`Node.js` 에 쿠키를 사용하기 위한  미들웨어 모듈 
`req.cookie` property 를 사용할 수 있다

#### 1. 폴더 만들기
```
chapter-01 
```
#### 2. 명령 실행 
```ruby
# express모듈로 어플리케이션 만들겠다
# --ejs : 템플릿 엔진 --ejs로 설정 
# --css sass : css는 sass 사용
# --git : .gitigore추가 
$ express --ejs --css sass --git 
```

###### result
`--ejs` 명령어는 `--view=ejs` 로 바뀐듯하다
```
  warning: option `--ejs' has been renamed to `--view=ejs'


   create : .
   create : ./package.json
   create : ./app.js
   create : ./.gitignore
   create : ./public
   create : ./routes
   create : ./routes/index.js
   create : ./routes/users.js
   create : ./views
   create : ./views/index.ejs
   create : ./views/error.ejs
   create : ./bin
   create : ./bin/www
   create : ./public/javascripts
   create : ./public/stylesheets
   create : ./public/stylesheets/style.sass

   install dependencies:
     $ cd . && npm install

   run the app:
     $ DEBUG=chapter-01:* npm start
```

#### 3. 프레임워크에서 사용할수 있는 옵션 확인

```
$ express -h 
```

###### result

```
Usage: express [options] [dir]
  Options:
        --version        output the version number
    -e, --ejs            add ejs engine support
        --pug            add pug engine support
        --hbs            add handlebars engine support
    -H, --hogan          add hogan.js engine support
    -v, --view <engine>  add view <engine> support (dust|ejs|hbs|hjs|jade|pug|twig|vash) (defaults to jade)
    -c, --css <engine>   add stylesheet <engine> support (less|stylus|compass|sass) (defaults to plain css)
        --git            add .gitignore
    -f, --force          force on non-empty directory
    -h, --help           output usage information
```

### Express 가 만들어주는 폴더 구조
Express 모듈을 통해 프로젝트 시작에 필요한 최소한의 구조를 만들어 주었다
#### bin/www
`서버 구동`을 위한 기본적인 코드로 구성되어있다. `app.js` 파일에서 `서버 설정`코드를 짜면, 그 코드를 가져와 HTTP 객체와 연동하는 작업을 해준다.

#### public
UI 설정(html/js/css 관련)에 들어가는 assets들의 폴더와 파일들로 구성되어 있다

#### routes
url 을 연결해서 로직과 연결해주는 파일
 그 중 /routes/index.js 파일을 살펴 봅시다.

#### view
템플릿 `ejs` 파일이 담겨있다. `ejs` 엔진을 통해 html로 변환해 효율적으로 코드를 짤 수 있다

#### .gitignore
`git`에 올릴때 원하지 않는 파일을 설정해준다

#### app.js
`서버 설정` 파일이 담겨있다. bin/www 가 가져가 http 와 연결해준다.


#### package.json

패키지 관리 파일. 프로그램 이름, 버전 등 노드 프로그램의 정보를 이 파일에 기술한다. 사용하는 모듈 목록을 알 수있다.

### package.json 수정
필요한 모듈을 적어놓는 package.json파일에 필요한 파일을 기술한다 

###### sample

```json
{
  "name": "chapter-01",
  "description": "Build a twitter Like app using the MBC design pattern",
  "license": "soomti",
  "author": {
    "name": "soomin Han",
    "url": "https://github.com/likesoomti/STUDY"
  },
  "repository": {
    "type": "git",
    "url": "https://github.com/likesoomti/STUDY"
  },
  "keywords": [
    "MVC",
    "Express App",
    "Expressjs"
  ],
  "version": "0.0.0",
  "private": true,
  "scripts": {
    "start": "node ./bin/www"
  },
  "dependencies": {
    "body-parser": "~1.18.2",
    "cookie-parser": "~1.4.3",
    "debug": "~2.6.9",
    "ejs": "~2.5.7",
    "express": "~4.15.5",
    "morgan": "~1.9.0",
    "node-sass-middleware": "0.9.8",
    "serve-favicon": "~2.4.5"
  }
}
```

### 1. package.json dependency 설치
```
npm install
```

### 2. Application 실행

```
$ DEBUG=chapter-01:* npm start 
```

###### result
```
> chapter-01@0.0.0 start /Users/Soomti/STUDY/NODEJS/BLUEPRINT/chapter-01
> node ./bin/www

  chapter-01:server Listening on port 3000 +0ms
```

### 어플리 케이션 구조 변경
블루프린트의 오타는 여기서부터 시작된다.. 

#### 프로젝트 안에 폴더 & 폴더 만들기
```
chapter-01
ㄴ server
    - config
    - routes
    - views
```
#### 폴더 이동

- `views` 폴더 파일 > `server/views` 이동
- `routes` 폴더 파일 > `server/views` 이동

#### app.js 변경

폴더 파일 경로를 이동함으로써 변한 path를 맞춰주자..

```javascript
var index = require('./server/routes/index');
var users = require('./server/routes/users');

app.set('views', path.join(__dirname, 'server/views'));
```

### 기본 초기화 프로세스 수정해보기

#### 1. app.js .set을 통해 시작할때 콘솔 로그 출력하기 

```javascript

// 서버 포트를 3000으로 설정 
app.set('port',process.env.PORT || 3000);

// 서버 포트 리슨을 받으면 console log 출력 
var server = app.listen(app.get('port'),function(){
  console.log('Express server listening on port'+ server.address().port);
})
```

#### package.json 변경으로 명령어 변경 실행

##### package.json
```json
//before
"scripts": {
    "start": "node ./bin/www"
  }

// after
"scripts": {
    "start": "node app.js"
  }
```
으로 바꿔주면 `DEBUG=chapter-01:* npm start` 를 안쓰고, `node app.js`를 통해 서버를 시작할 수 있다