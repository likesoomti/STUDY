# express.Router
express.Router 는 express에서 제공하는 라우팅 시스템, 모듈식으로 핸들러를 작성할 수 있다.
다음의 내용이 입력된 birds.js라는 이름의 라우터 파일을 앱 디렉토리에 작성하십시오.

##### bird.js
```js
var express = require('express');
//라우터 추출
var router = express.Router();

// 라우터를 사용하겠다 표시 
router.use(function timeLog(req, res, next) {
  console.log('Time: ', Date.now());
  next();
});
// '/'이 들어올 경우 콜백
router.get('/', function(req, res) {
  res.send('Birds home page');
});
// '/about'이 들어올 경우 콜백
router.get('/about', function(req, res) {
  res.send('About birds');
});
// 모듈을 사용하겠다 router 이름으로 
module.exports = router;
```

##### app.js
```js
// bird.js 추출
var birds = require('./birds');
// 라우터로 birds 사용 
app.use('/birds', birds);
```

이런식으로 명시해서 사용한다.

# jugglingdb
```node
module.js:471
    throw err;
    ^
Error: Cannot find module 'jugglingdb'
    at Function.Module._resolveFilename (module.js:469:15)
    at Function.Module._load (module.js:417:25)
    at Module.require (module.js:497:17)
    at require (internal/module.js:20:19)
    at Object.<anonymous> (/Users/Soomti/STUDY/NODEJS/BLUEPRINT/chapter-04/app/models/book.js:1:97)
    at Module._compile (module.js:570:32)
    at Object.Module._extensions..js (module.js:579:10)
    at Module.load (module.js:487:32)
    at tryModuleLoad (module.js:446:12)
    at Function.Module._load (module.js:438:3)
```
## solve

not npm install....

`npm install `


# { Error: Cannot find module '../build/Release/bson'}
```
{ Error: Cannot find module '../build/Release/bson'
    at Function.Module._resolveFilename (module.js:469:15)
    at Function.Module._load (module.js:417:25)
    at Module.require (module.js:497:17)
    at require (internal/module.js:20:19)
    at Object.<anonymous> (/Users/Soomti/STUDY/NODEJS/BLUEPRINT/chapter-04/node_modules/bson/ext/index.js:15:10)
    at Module._compile (module.js:570:32)
    at Object.Module._extensions..js (module.js:579:10)
    at Module.load (module.js:487:32)
    at tryModuleLoad (module.js:446:12)
    at Function.Module._load (module.js:438:3) code: 'MODULE_NOT_FOUND' }
js-bson: Failed to load c++ bson extension, using pure JS version
Express server listening on port 3000
GET / 200 32.212 ms - -

```
## solve 

- window10 환경이라그런가 ? 이 해결방법에 대한 경로는 나에게 없었다.

##### node_modules\mongoose\node_modules\mongodb\node_modules\bson\ext\index.js 
출처: http://jlblog.me/71 [JLBlog]


- 해결

chapter-04/node_modules/bson/ext/index.js
```js
Change path to js version in catch block:

bson = require('../build/Release/bson');
To:

bson = require('../browser_build/bson');
Or copy file in:
```
값 2개를 바꿔주면 된다.

## ‘Robo 3T’은(는) 확인되지 않은 개발자가 배포했기 때문에 열 수 없습니다.

https://m.blog.naver.com/PostView.nhn?blogId=moonattic&logNo=220793237821&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F


http://jlblog.me/71

## livereload-js-net-err-connection-refused-problem

###solve

### 1. gulp 설치 

```
sudo npm install gulp
```

```
npm install gulp-watch
```

http://hochulshin.com/gulp-livereload-sample/

## clodinary.config() = {}

## solve 
cloudinary_url = ~ 형태가 헤로쿠에서 사용하는 형태. 
config.url({

})
안에 값을 설정해줘야한다.