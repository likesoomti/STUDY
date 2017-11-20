# MVC 디자인패턴을 이용한 트위터 스타일 애플리케이션 만들기 -1

### MVC 패턴

`MVC`는 소프트웨어에서 사용되는 디자인 패턴이다. 사용자가 보는 `UI` 부분과, 보이지않는 `비즈니스 로직` 을 분리해서 영향없이 어플리케이션을 만들 수 있다. 

MVC에서 
- `모델`은 애플리케이션의 정보(데이터)를 나타내며,
- `뷰`는 체크박스,텍스트 항목 등과 같은 UI,
- `컨트롤러`는 데이터와 비즈니스 로직 사이의 상호동작을 관리한다.

### Express.js

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


### 미들웨어 (`Middle Ware`)
미들웨어(`middleware`)는 응용 소프트웨어가 운영 체제로부터 제공받는 서비스 이외에 추가적으로 이용할 수 있는 라이브러리를 말한다.

#### body-parser
`Node.js` 에서 데이터를 post 요청 데이터를 보낼 수 있는 미들웨어 모듈 
`req.body` property 속성이 부여된다.
#### cookie-parser
`Node.js` 에 쿠키를 사용하기 위한  미들웨어 모듈 
`req.cookie` property 를 사용할 수 있다

#### gravator

Global+Recoginze+Avator
프로필을 업로드하면, 그라바타를 사용하는 사이트에 이미지가 자동으로 업로드되는 모듈 
- https://www.npmjs.com/package/gravatar


#### connect-flash

일회성 메세지. 한번 보내고 나면 사라진다.

- https://m.blog.naver.com/PostView.nhn?blogId=rwans0397&logNo=220680181786&proxyReferer=https:%2F%2Fwww.google.co.kr%2F


#### mongoose

MongoDB 기반 ODM(Object Data Mapping). Rails의 ActiveRecord 인듯하다.MongoDB의 데이터를 Javascript 객체 기반으로 활용할 수 있다

- https://velopert.com/594

#### express-session

 세션 데이터를 서버에 저장한다. 쿠키에는 데이터가 아닌 ID를 저장하여 Id를 가지고 사용한다.develop 모드에서 사용하는 듯..?
 - http://expressjs.com/ko/advanced/best-practice-security.html

#### connect-mongo

몽고 DB - Node.js 연결해주는 드라이버 
- https://www.npmjs.com/package/connect-mongodb
#### bcypt-nodejs
패스워드를 암호화 해주는 모듈 
- https://www.npmjs.com/package/bcrypt-nodejs

#### passport 

node.js용 범용 인증 모듈로, 다양한 인증 프로토콜을 지원한다.(HTTP Basic Auth, HTTP digest authentication, OAuth,OpenID)
로그인 쉽게 가능! 

passport는 Strategy(전략)이라는 것을 사용하여 인증을 실행한다.
- http://bcho.tistory.com/920 

#### passport-local

passport 인증 모듈에서 로컬 유저/패스워드를 인증해주는 모듈 

- https://www.zerocho.com/category/NodeJS/post/57b7101ecfbef617003bf457

### 모듈 설치

##### terminal

```
$ npm install connect-flash connect-mongo mongoose express-session gravatar bcrypt-nodejs passport passport-local --save
```
### 서버 구축하기 
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
`--ejs` 명령어는 `--view=ejs` 로 바뀐듯..!?
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
`서버 구동`을 위한 기본적인 코드로 구성되어있다. `app.js` 파일에을 가져와 HTTP 객체와 연동하는 작업을 해준다.

#### public
UI 설정(html/js/css 관련)에 들어가는 assets들의 폴더와 파일들로 구성되어 있다.

#### routes
url 을 연결해서 로직과 연결해주는 파일

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

#### app.js 


```javascript
// 라우트 경로 설정시  
var index = require('./server/routes/index');
var users = require('./server/routes/users');

// 뷰 엔진 설정 
app.set('views', path.join(__dirname, 'server/views'));
```

#### 서버 콘솔 출력  

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

# MVC 디자인패턴을 이용한 트위터 스타일 애플리케이션 만들기 - 2

## EJS?

템플릿 엔진 모듈

특정 형식의 문자열을 HTML 형식의 문자열로 변환하여 
`클라이언트 요청`에 따라 `웹문서 결과`를 다르게 전송한다.
정적인 부분과 동적인 부분을 따로 하기위해 사용

### 재사용 가능한 템플릿 만들기

다른 페이지에서 고정으로 사용하는 리소스를 `partial` 파일이라고 한다. `<% include %>` 태그를 사용하여 고정해준다.

#### Example
##### stylesheet.ejs

```html
<!-- CSS Files -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha/css/bootstrap.min.css'>
<link rel='stylesheet' href='/stylesheets/style.css' />
```
##### javascript.ejs
```html
<!-- JS Scripts -->
<!-- JS Scripts -->
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.1/jquery.min.js'></script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0-alpha/js/bootstrap.min.js'></script>
```
##### header.ejs

```html
<!-- Fixed navbar -->
<div class="pos-f-t">
    <div class="collapse" id="navbar-header">
        <div class="container bg-inverse p-a-1">
            <h3>Collapsed content</h3>
            <p>Toggleable via the navbar brand.</p>
        </div>
    </div>
</div>......etc

// footer.ejs
<footer class="footer">
    <div class="container">
        <span>&copy 2016. Node-Express-MVC-App</span>
    </div>
</footer>
```

### app.js 템플릿 경로 조정하기

app.js 에서 서버가 뷰 파일을 읽을 경로를 설정 해야 한다. 경로를 설정 시 해당 경로의 index.ejs 파일을 읽어온다.

##### app.js
```javascript
//before
app.set('views', path.join(__dirname, 

// after 
// 서버가 렌더링 할 때 뷰를 받아오는 설정을 한다
app.set('views', path.join(__dirname, 'server/views/pages'));
// 뷰 엔진을 ejs로 설정합니다
app.set('view engine', 'ejs');
```

##### index.ejs 재사용 템플릿 추가
 `<% include '경로' %>` 
```html
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <% include ../partials/stylesheet %>
</head>
<body>
    <% include ../partials/header %>
    <div class="container">
      <div class="page-header m-t-1">
        <h1><%= title %></h1>
      </div>
      <p class="lead">Welcome to <%= title %></p>
    </div>
    <% include ../partials/footer %>
    <% include ../partials/javascript %>
</body>
</html>
```

### 라우트 추가

추가한 뷰의 라우트를 지정하고싶다면 

```routes/index.js```에 설정한다

##### routes/index.js

```javascript

// get 방식으로 '/login'을 받는다
// title, message를 params로 보낸다
router.get('/login', function(req, res, next) {
    res.render('login', { title: 'Login Page', message: req.flash('loginMessage') });
});
// post 방식으로 로그인지정,
// 다음은 콜백함수 . 콜백함수에 passport 호출 
router.post('/login', passport.authenticate('local-login', {
    //Success go to Profile Page / Fail go to login page
    successRedirect : '/profile',
    failureRedirect : '/login',
    failureFlash : true
}));

// 로그인 권한 설정 
router.get('/profile', isLoggedIn, function(req, res, next) {
    res.render('profile', { title: 'Profile Page', user : req.user, avatar: gravatar.url(req.user.email ,  {s: '100', r: 'x', d: 'retro'}, true) });
});

// 로그인체크 함수 true면 다음 콜백으로 보내주는거 맞나요? 
function isLoggedIn(req, res, next) {
    if (req.isAuthenticated())
        return next();
    res.redirect('/login');
}
```


# MVC 디자인패턴을 이용한 트위터 스타일 애플리케이션 만들기 - 3

### 1. 설치했던 미들웨어 코드 추가

#### app.js

###### var app = express(); 앞에 설치 
```javascript
// mongoose setting
var mongoose = require('mongoose');
// session setting
var session = require('express-session');
// mongo connect
var MongoStore = require('connect-mongo')(session);
// passport 
var passport = require('passport');
var flash = require('connect-flash');
```
###### app.set('view engine' , 'ejs') 뒤에 추가 
```javascript
// database setting
var config = require('./server/config/config.js');
// database setting
mongoose.connect(config.url);
// check moongose on
mongoose.connection.on('error',function(){
  console.error('Mongo db error. not running mongoDB');
});
//setting passport
require('./server/config/passport')(passport);

```

###### app.use(express.static(path.join(__dirname, 'public')));
```javascript
// add 
app.use(session({
  secret: 'soomti',
  saveUninitialized: true,
  resave: true,
  // express-session , connect-mongo 이용 mongo DB 저장
  store: new MongoStore({
    url: config.url,
    collection: 'sessions'
  })
}));
// passport 인증 초기화
app.use(passport.initialize());
// 영구적 로그인 세션 
app.use(passport.session());
// 플래시 메세지
app.use(flash());
```

### 2. server/config/config.js 추가

```javascript
// Database URL
module.exports = {
    // Uncomment to connect with MongoDB on Cloud
    //'url' : 'mongodb://mvc-user-connect:opklnm@ds019028.mlab.com:19028/mvc-app'
    'url' : 'mongodb://localhost/mvc-app'
};
```

### 3. server/config/passport.js 추가
```javascript
// load passport module
var LocalStrategy    = require('passport-local').Strategy;
// load up the user model
var User = require('../models/users');

module.exports = function(passport) {
    // passport init setup
    // serialize the user for the session
    passport.serializeUser(function(user, done) {
        done(null, user.id);
    });
    // deserialize the user
    passport.deserializeUser(function(id, done) {
        User.findById(id, function(err, user) {
            done(err, user);
        });
    });
    // using local strategy
    passport.use('local-login', new LocalStrategy({
        // change default username and password, to email and password
        usernameField : 'email',
        passwordField : 'password',
        passReqToCallback : true
    },
    function(req, email, password, done) {
        if (email)
        // format to lower-case
        email = email.toLowerCase();
        // process asynchronous
        process.nextTick(function() {
            User.findOne({ 'local.email' :  email }, function(err, user) {
                // if errors
                if (err)
                return done(err);
                // check errors and bring the messages
                if (!user)
                return done(null, false, req.flash('loginMessage', 'No user found.'));
                if (!user.validPassword(password))
                return done(null, false, req.flash('loginMessage', 'Wohh! Wrong password.'));
                // everything ok, get user
                else
                return done(null, user);
            });
        });
    }));
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
};
```


### 4. Model 폴더 User 스키마 
#####  server/model/user.js 파일 추가
```javascript
var express = require('express');
var router = express.Router();

/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

module.exports = router;
```

### 5. 라우트 post 추가  

##### server/routes/index.js

```javascript
var express = require('express');
var router = express.Router();
var passport = require('passport');
// get gravatar icon from email
var gravatar = require('gravatar');

/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express from server folder' });
});

/* GET login page. */
router.get('/login', function(req, res, next) {
    res.render('login', { title: 'Login Page', message: req.flash('loginMessage') });
});
/* POST login */
router.post('/login', passport.authenticate('local-login', {
    //Success go to Profile Page / Fail go to login page
    successRedirect : '/profile',
    failureRedirect : '/login',
    failureFlash : true
}));

/* GET Signup */
router.get('/signup', function(req, res) {
    res.render('signup', { title: 'Signup Page', message: req.flash('signupMessage') });
});
/* POST Signup */
router.post('/signup', passport.authenticate('local-signup', {
    //Success go to Profile Page / Fail go to Signup page
    successRedirect : '/profile',
    failureRedirect : '/signup',
    failureFlash : true
}));

/* GET Profile page. */
router.get('/profile', isLoggedIn, function(req, res, next) {
    res.render('profile', { title: 'Profile Page', user : req.user, avatar: gravatar.url(req.user.email ,  {s: '100', r: 'x', d: 'retro'}, true) });
});

/* check if user is logged in */
function isLoggedIn(req, res, next) {
    if (req.isAuthenticated())
        return next();
    res.redirect('/login');
}
/* GET Logout Page */
router.get('/logout', function(req, res) {
    req.logout();
    res.redirect('/');
});

module.exports = router;

```

### 5. Controller 만들기 

##### chapter-01/controller 폴더 생성 , comment.js 추가

```javascript
//모듈 추가 
var gravatar = require('gravatar');

// 모델 추가 
var Comments = require('../models/comments');

// 코멘트 리스트 만들기 
exports.list = function(req, res) {
    Comments.find().sort('-created').populate('user', 'local.email').exec(function(error, comments) {
        if (error) {
            return res.send(400, {
                message: error
            });
        }
        //렌더링 결과 
        res.render('comments', {
            title: 'Comments Page',
            comments: comments,
            gravatar: gravatar.url(comments.email ,  {s: '80', r: 'x', d: 'retro'}, true)
        });
    });
};
// 커멘트 생성 
exports.create = function(req, res) {
    // 인스턴스 생성
    var comments = new Comments(req.body);

    comments.user = req.user;
    comments.save(function(error) {
        if (error) {
            return res.send(400, {
                message: error
            });
        }
        // Redirect to comments
        res.redirect('/comments');
    });
};
exports.hasAuthorization = function(req, res, next) {
	if (req.isAuthenticated())
        return next();
    res.redirect('/login');
};
``` 

##### app.js
require 인증 해주는 부분에 추가해준다
```javascript
//코멘트 추가
var comments = require('./server/controllers/comments');
```
app.use('/users',users) 뒷 부분에 추가

```javascript
app.get('/comments',comments.hasAuthorization, comments.list)
app.post('/comments',comments.hasAuthorization,comments.create)
```

##### server/views/pages에 comment.ejs 파일 만들기 

```javascript
<!DOCTYPE html>
<html>
<head>
    <title><%= title %></title>
    <% include ../partials/stylesheet %>
</head>
<body>
    <% include ../partials/header %>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <h4 class="text-muted">Comments</h4>
            </div>
            <div class="col-lg-6">
                <button type="button" class="btn btn-secondary pull-right" data-toggle="modal" data-target="#createPost">
                    Create Comment
                </button>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="createPost" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form action="/comments" method="post">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <h4 class="modal-title" id="myModalLabel">Create Comment</h4>
                        </div>

                        <div class="modal-body">

                            <fieldset class="form-group">
                                <label  for="inputitle">Title</label>
                                <input type="text" id="inputitle" name="title" class="form-control" placeholder="Comment Title" required="">
                            </fieldset>
                            <fieldset class="form-group">
                                <label  for="inputContent">Content</label>
                                <textarea id="inputContent" name="content" rows="8" cols="40" class="form-control" placeholder="Comment Description" required=""></textarea>
                            </fieldset>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <hr>
        <div class="lead">
            <div class="list-group">
            <% comments.forEach(function(comments){ %>
                    <a href="#" class="list-group-item">
                        <img src="<%= gravatar %>" alt="" style="float: left; margin-right: 10px">
                        <div class="comments">
                            <h4 class="list-group-item-heading"><%= comments.title %></h4>
                            <p class="list-group-item-text"><%= comments.content %></p>
                            <small class="text-muted">By: <%= comments.user.local.email %></small>
                        </div>
                    </a>
            <% }); %>
            </div>
        </div>
        </div>
        <% include ../partials/footer %>
        <% include ../partials/javascript %>
    </body>
    </html>
```

### 실행하기

```
npm start
```