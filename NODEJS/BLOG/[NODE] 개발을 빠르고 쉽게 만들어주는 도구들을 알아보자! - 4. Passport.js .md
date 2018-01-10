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


##### model/user.js

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

이제 user 로그인을 할수 있는 코드를 짜봅시다.



## Passport.js 구성

이제 위에 복사했던 기본적인 코드를 만들 수 있게 바꿔보겠습니다.



```javascript
var passport = require('passport'), LocalStrategy = require('passport-local').Strategy;

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



이건 이전에 복사했던 기본적인 코드입니다. 

우리는 회원가입 기능을 구성하기 위해 밑에 두가지 기능을 해야합니다.

- 우리는 passport.js 를 사용한 회원가입 / 로그인을 하기 때문에 `User` 모델에 회원을 등록시키고, 
- `User`  가 등록이 되었을 경우 성공! 페이지를 보여주게 할 것입니다.



##### 1. 유저 모델 임포트

유저 모델을 등록하고 / 조회 하기 위해 user를 import 해주는 코드를 작성합니다.

```javascript
var User = require('./model/user');
```

##### 2. passport export

passport 에서 제공해주는 Strategy 을 외부에서 사용하기 위해 `module.exports` 를 해줍시다.

```javascript
// 패스포드 익스폴트. 초기설정 및 회원가입/로그인을 해줍니다.
module.exports = function(passport) {
  passport.serializeUser(function(user, done) {
    done(null, user);
  });  
  passport.deserializeUser(function(user, done) {
    done(null, user);
  });
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
}
```

이렇게 `module.export` callback 함수에 기본적인 코드를 넣어주면 외부에서 모듈을 사용할 수 있습니다. :) 

##### 3. 회원가입 코드 작성하기

이제 회원가입 코드를 작성해 보겠습니다.  `module.export` 에 있는 내용을 완전히 바꿔보도록 하겠습니다. 

```javascript
 // Signup local strategy
    passport.use('local-signup', new LocalStrategy({
        // change default username and password, to email and password
        usernameField : 'email',
        passwordField : 'password',
        passReqToCallback : true
    },
    function(req, email, password, done) {
        if (email)
        // format to lower-case
        email = email.toLowerCase();
        // asynchronous
        process.nextTick(function() {
            // if the user is not already logged in:
            if (!req.user) {
                User.findOne({ 'local.email' :  email }, function(err, user) {
                    // if errors
                    if (err)
                    return done(err);
                    // check email
                    if (user) {
                        return done(null, false, req.flash('signupMessage', 'Wohh! the email is already taken.'));
                    } else {
                        // create the user
                        var newUser = new User();
                        // Get user name from req.body
                        newUser.local.name = req.body.name;
                        newUser.local.email = email;
                        newUser.local.password = newUser.generateHash(password);
                        // save data
                        newUser.save(function(err) {
                            if (err)
                            throw err;
                            return done(null, newUser);
                        });
                    }
                });
            } else {
                return done(null, req.user);
            }
        });
    }));
```

이제 뷰를 만들어 보겠습니다. 

##### users/index.js

저는 view 폴더에 users 라는 폴더를 파 index.ejs 폴더를 만들어 주었습니다.

```ejs
<!DOCTYPE html>
<html lang="ko">
    <head>
        <title>ExpressCOIN</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body> 
        <div class="cointainer">
            <h1> 회원가입</h1>
            <form action="users/signup" method="post">
                <div class="box">
                    <label for="email">이메일</label><br>
                    <input type="text" name="email" />                   
                </div>
                <div class="box">
                    <label for="password">비밀번호</label><br>
                    <input type="password" name="password" />
                </div>
               
                <button type="submit">회원가입</button>
            </form>
        </div>
    </body>
</html>
```



`form` 태그를 통해 `post` 방식으로 `users/signup` 에 보내줬는데요. 이제 저 라우트를 받아 Passport에 넘겨주는 코드를 작성해 보겠습니다.



##### routes/users.js

```javascript
var express = require('express');
var router = express.Router();

// passport 연동을 위한 
var passport = require('passport');

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.render('users/index', { title: 'User Page' });
});



// signup 
router.post('/signup', passport.authenticate('signup', {
  successRedirect : '/users/success', 
  failureRedirect : '/', //로그인 실패시 redirect할 url주소
  failureFlash : false,
  session: true 
}));

// signup success
/* GET users listing. */
router.get('/success', function(req, res, next) {
  res.render('users/success', { title: 'User Page' });
});

module.exports = router;

```

`Express`가 기본적으로 만들어 준 코드 외에 회원가입을 가능하게 해주는 코드를 작성하였습니다. 이제 성공시 보여질 페이지 users/success 뷰를 만들어야겠네요.



##### users/success.ejs

```ejs
<h1>성공</h1>
```

간단하게 확인하기 위한 파일을 만들어 주었습니다.

간단하게  패스포트 모듈을 사용한 회원가입 기능을  만들어 보았습니다!

