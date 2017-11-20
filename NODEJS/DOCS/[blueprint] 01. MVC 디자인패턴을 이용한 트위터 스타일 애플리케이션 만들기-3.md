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