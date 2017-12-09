
# [NODE] 개발을 빠르고 쉽게 만들어주는 도구들을 알아보자! - 3. Express 구조를 뜯어보자! 


저번시간에 Express-generator을 통해 한번에 만들었던 폴더들을 하나하나 순서대로 뜯어보겠습니다. 그전에 기본적인 Express 폴더에 대해 짚고 넘어가는게 좋겠죠? 


## Express의 기본 구조 

### bin/www
`서버 구동`을 위한 기본적인 코드로 구성되어있습니다. `app.js` 파일에을 가져와 HTTP 객체와 연동하는 작업을 해줍니다.

### public
UI 설정(html/js/css 관련)에 들어가는 `assets` 의 폴더와 파일들로 구성되어 있습니다.

### routes
url 을 연결해서 로직과 연결해주는 폴더입니다.

### view
템플릿 `jade` 파일이 담겨있는 공간입니다. `jade` 엔진을 통해 html로 변환해 효율적으로 코드를 짤 수 있습니다.

#### app.js
`서버 설정` 파일이 담겨있습니다. bin/www 가 이 파일을 읽고 http 와 연결해줍니다.

#### package.json

패키지 관리 파일. 프로그램 이름, 버전 등 노드 프로그램의 정보를 이 파일에 담아놓습니다. 사용하는 모듈 목록을 알 수 있습니다.


### bin/www
`서버 구동`을 위한 기본적인 코드입니다. `node app.js`를 시작하면 컴파일러는 서버를 키기 위해 가장 먼저 이 파일을 해석하죠. 하나하나 찬찬히 뜯어보겠습니다. 

```javascript
#!/usr/bin/env node


//1. http 통신을 위한 모듈을 추출합니다.
var app = require('../app');
var debug = require('debug')('meanapp:server');
var http = require('http');

//2. 통신할 포트를 설정합니다. 3000으로 설정해 주네요.
var port = normalizePort(process.env.PORT || '3000');
app.set('port', port);


// 3. 추출한 http 모듈의 미들웨어로 서버를 생성합니다. 
var server = http.createServer(app);

// 4.통신을 위해 3000 번 포트로 응답을 듣습니다. 
server.listen(port);
server.on('error', onError);
server.on('listening', onListening);

// 5. 파이프 이름 또는 포트 번호를 반환합니다. 
function normalizePort(val) {
  var port = parseInt(val, 10);
  if (isNaN(port)) 
    return val;

  if (port >= 0) 
    return port;
  return false;
}
// 6. 에러시 로그를 반환합니다.
function onError(error) {
  if (error.syscall !== 'listen') {
    throw error;
  }

  var bind = typeof port === 'string'
    ? 'Pipe ' + port
    : 'Port ' + port;

  switch (error.code) {
    case 'EACCES':
      console.error(bind + ' requires elevated privileges');
      process.exit(1);
      break;
    case 'EADDRINUSE':
      console.error(bind + ' is already in use');
      process.exit(1);
      break;
    default:
      throw error;
  }
}
// 7. 성공시 값을 반환합니다.
function onListening() {
  var addr = server.address();
  var bind = typeof addr === 'string'
    ? 'pipe ' + addr
    : 'port ' + addr.port;
  debug('Listening on ' + bind);
}
```

이 파일에서 처음 추출했던 `var app = require('../app');`에 해당하는 app.js를 살펴볼까요?

### app.js

#### 1. var ~ = require('~')

```javascript
var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser')
var bodyParser = require('body-parser');
var sassMiddleware = require('node-sass-middleware');

var index = require('./routes/index');
var users = require('./routes/users');
```
**require('~')** 함수는 모듈을 로딩하는 함수입니다. c와 비교하자면 `import java.util.~`이런것과 비슷하려나요?

 express-generator가 `package.json`에 명시한 외부 모듈(=라이브러리)와, 사용자가 직접 만든 내부 모듈을 app.js에서 사용할 수 있도록 만들어 줍니다. 

##### index.js
```javascript
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

module.exports = router;

```
모듈은 라이브러리와 같다고 보면 됩니다. node.js에서는 라이브러리를 모듈이라고 지칭하죠. 외부에서 개발자들이 만든 모듈 외에,Express-generator은 기본적으로 내장 모듈을 만들어줍니다. 대표적인 샘플이 `./routes/index.js`의 내용인데요. 마지막에 `module.exports = router;`을 명시하여 이 파일을 모듈로 사용할 수 있습니다. 


##### 1. var app = express();
``` javascript
var app = express();
```
express 라이브러리를 해당 파일에 임폴트 (require)했으니, 실행시켜야겠죠? 실행시킨 express(); 를 app 에 넣어줍니다.

##### 2. app.set()

```javascript
// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');
```

생성한 express에서 제공하는 .set() 메서드는 `템플릿 엔진`을 렌더링 하는 함수입니다.

- 'views' : 템플리트가 있는 디렉토리 경로를 설정해 줍니다.
- 'view engine': 사용할 템플리트 엔진을 설정해 줍니다.

##### app.use()
```javascript
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(sassMiddleware({
  src: path.join(__dirname, 'public'),
  dest: path.join(__dirname, 'public'),
  indentedSyntax: true, // true = .sass and false = .scss
  sourceMap: true
}));
app.use(express.static(path.join(__dirname, 'public')));

// 라우팅을  로드합니다. 
app.use('/', index);
app.use('/users', users);

// catch 404 and forward to error handler
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// error handler
app.use(function(err, req, res, next) {
  // set locals, only providing error in development
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};

  // render the error page
  res.status(err.status || 500);
  res.render('error');
});
```

위에 require 로 import한 모듈을 express() 함수 안에서 사용하겠다고 선언하는 내용입니다. 이 모듈들의 각각의 내용은 따로 정리하여 올리겠습니다. 자 이제 app.js의 순서대로 살펴보겠습니다. ```app.set()```에서 설정한 View 엔진 부터 둘러보겠습니다.

## app/views/index.js

```html
<!DOCTYPE html>
<html>
  <head>
    <title><%= title %></title>
    <link rel='stylesheet' href='/stylesheets/style.css' />
  </head>
  <body>
    <h1><%= title %></h1>
    <p>Welcome to <%= title %></p>
  </body>
</html>
```
 ejs파일입니다. ejs는 `embed javascript`의 약자로 `<%= %>`의 표현식 안에 `javascript` 문법을 넣어 정적인 html 문서를 동적으로 사용할 수 있습니다.

 ### Routing

```javascript
app.use('/', index);
app.use('/users', users);
```
app.js에서는 `app.use()`를 통해  라우팅을  로드합니다. `app.use('/',index)`에서 `/` 는 라우트의 경로를 의미합니다. `/` 의경로로부터 시작하는 라우팅인데, 한번 파일을 보겠습니다.

#### app/routes/index.js

```javascript
var express = require('express');
var router = express.Router();
// 1. 
router.get('/', function(req, res, next) {
  // 2. 
  res.render('index', { title: 'Express' });
});
module.exports = router;
```
##### router.get('/', func) 

index.js 파일 `.get()` 함수로 라우팅 경로를 지정해 줍니다. 예를들어 `use('/')`의 `.get('/')` 경로이므로 `localhost:3000`의 경로에 나오는 페이지를 보여줍니다. 

##### res.render('index', { title: 'Express' });
res.render()는 해당 라우팅 요청이 들어왔을때 보여줄 뷰 페이지를 말해줍니다. `index.ejs`의 파일에 있는 html 문서가 보여집니다. `{}` 안에 있는 내용은 html에 전달해줄 값들을 명시해 둔 내용입니다.


크게 express-generator로 만든 모듈을 살펴보았습니다. 수고하셨습니다! 

#### 참고자료

-  http://rinow.tistory.com/3 
