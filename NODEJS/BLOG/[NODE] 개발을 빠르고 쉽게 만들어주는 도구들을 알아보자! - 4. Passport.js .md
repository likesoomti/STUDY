# [NODE] 개발을 빠르고 쉽게 만들어주는 도구들을 알아보자! - 4. Passport.js 사용해보기

Passport는 node.js 에서 사용하는 인증 모듈입니다. 기본 개념은 

https://github.com/likesoomti/STUDY/blob/master/NODEJS/DOCS/Passport.md

를 확인해 주세요. 기본 개념을 숙지하고 Express 에 제대로 사용해 봅시다. 

## Passprort 설치

기본적으로 로그인 관련 패키지를 설치해야 합니다.



```
$ npm install passport
$ npm install passport-local
$ npm install express-session
```



## 폴더 구조 생성

`express` 가 기본적으로 만들어 준 폴더를 모듈화 시키기 위해 구조를 조금 바꿔보겠습니다.

```Bash
server
  - config 
  - public
  - routes
  - views
  
```

Server 라는 폴더를 만든 후,  원래 제공해주는 `public` ,`routes` 폴더를 안으로 넣어주고, 새로 `config`  폴더를 만들어 주었습니다. 



## Passport.js 기본 구성 

`config` 폴더 안에 passport 모듈을 사용하기 위한 기본적인 코드를 짜보겠습니다.

 `passport` document 를 보면 패스포트에 대한 기본적인 설명이 나오는데요. 패스포트는 기본적으로 2가지 필수 구성을 해주어야 합니다.

1. Strategy  인증
2. Application Middleware

*Strategy* 란 로그인을 시도하는 사용자가 계정에 주인인지를 체크할 지에 대해 정해줍니다. 예를들어 기본 로그인 / 비밀번호를 넣을것인지 아니면 페이스북 / 카카오톡 과 같은 SNS로 인증할 것인지를 말합니다. 

사용 전략을 선택했다면 이제 그에 해당하는 미들웨어를 사용해 패스포트의 구성을 만들어 주어야 겠죠?

 가장 기본적으로 데이터베이스에 사용자가 가입할 때 넣었던 이메일 / 비밀번호를 통해 사용자를 인증하는 passport-local 전략을 사용해 보겠습니다. 위에 npm을 깔았고 밑에 코드를 넣어주겠습니다. 



#### config/passport.js

##### passport.js

```Javascript
var passport = require('passport')
  , LocalStrategy = require('passport-local').Strategy;

passport.use(new LocalStrategy(
  function(username, password, done) {
    User.findOne({ username: username }, function (err, user) {
      if (err) { return done(err); }
      if (!user) {
        return done(null, false, { message: 'Incorrect username.' });
      }
      if (!user.validPassword(password)) {
        return done(null, false, { message: 'Incorrect password.' });
      }
      return done(null, user);
    });
  }
));
```



일단 기본적인 구성 코드를 저장해놓고. 사용자 패스워드를 저장해 놓을 데이터 베이스를 만들어 봅시다. 



## 데이터 베이스 연결 및 생성  

#### 몽고 디비 연결

```bash
$ npm install mongoose 
$ npm install connect-mongo
$ npm install bcrypt-nodejs
```

`bcypt-nodejs` 는 사용자가 적은 패스워드를 암호화 해주는 모듈입니다. 패스워드를 암호화 해주기 위해 사용하겠습니다. 

이제 몽고 디비를 연결해주는 파일을 만들어 봅시다. passportAPP 라는 이름의 데이터베이스를 만들어 주겠습니다.

#### config/mongo.js

```javascript
// Database URL
module.exports = {
    'url' : 'mongodb://localhost/passportAPP'
};
```



####  유저 모델 생성

passport.js 에서 제공해주는 인증 샘플입니다. 일단 유저의 id 와 passport 를 받을 수 있는 디비가 필요하겠네요. 몽고 DB를 사용할 건데, 필요한 모듈을 설치하겠습니다.


##### user.js

```javascript

// 몽고 디비 연결 
var mongoose = require('mongoose');
// 암호화 모듈 생성 
var bcrypt   = require('bcrypt-nodejs');
// 유저 스키마 만들기 
var userSchema = mongoose.Schema({
    local: {
        email: String,
        password: String
    }

});
// 유저 암호화 모듈 메소드 
userSchema.methods.generateHash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(8), null);
};
userSchema.methods.validPassword = function(password) {
    return bcrypt.compareSync(password, this.local.password);
};
module.exports = mongoose.model('User', userSchema);var mongoose = require('mongoose');
var bcrypt   = require('bcrypt-nodejs');
var userSchema = mongoose.Schema({
    local: {
        email: String,
        password: String,
    }

});
userSchema.methods.generateHash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(8), null);
};
userSchema.methods.validPassword = function(password) {
    return bcrypt.compareSync(password, this.local.password);
};
module.exports = mongoose.model('User', userSchema);

```



자 이제 구성한 모듈들을 `app.js` 에 추가해 봅시다. 

#### app.js 

```javascript
var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var sassMiddleware = require('node-sass-middleware');

var index = require('./server/routes/index');
var users = require('./server/routes/users');

// 몽고 디비 연결
var mongoose = require('mongoose');
var session = require('express-session');
var MongoStore = require('connect-mongo')(session);
// 패스포트 연결 
var passport = require('passport');

var app = express();

// view engine setup
app.set('views', path.join(__dirname, './server/views'));
app.set('view engine', 'ejs');

// 데이터 베이스 구성한 것 연결 
var config = require('./server/model/mongo.js');

mongoose.connect(config.url);
mongoose.connection.on('error',function(){
  console.error('MongoDB Connection Error. Make sure MongoDB is running');
});

// uncomment after placing your favicon in /public
//app.use(favicon(path.join(__dirname, 'public', 'favicon.ico')));
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

module.exports = app;

```



추가한 부분을 알아봅시다



##### 모듈 설치

##### app.js

 설치한 모듈의 미들웨어를 사용하기 위해 `require` 를 해준 코드입니다.

```javascript
// 몽고 디비 연결
var mongoose = require('mongoose');
var session = require('express-session');
var MongoStore = require('connect-mongo')(session);
// 패스포트 연결 
var passport = require('passport');
```

 데이터베이스 연결 

```javascript
// 데이터 베이스 구성한 것 연결 
var config = require('./server/model/mongo.js');
mongoose.connect(config.url);
mongoose.connection.on('error',function(){
  console.error('MongoDB Connection Error. Make sure MongoDB is running');
});
```



간단하게 실행 해보면 돌아가는 것을 알 수 있습니다

만약 오류가 날 시에는 , 맨 처음에 구조를 바꾼 폴더의 경로를 확인해 보시면 됩니다.



