# 멀티미디어 애플리케이션 구축하기 

`mongoDB`와 `mongoose`를 이용해 사용자 인증을 지원하는 이미지와 비디오 업로드 어플리케이션을 만들기 

### 텍스트 및 파일을 로딩하고 저장하는 법 

1. 바이너리 포맷으로 데이터베이스에 직접 저장

2. 파일 서버 또는 클라우드로 파일 시스템 저장

### 목표 

- 하드디스크에 여러 종류 파일 업로드
- 스트림 API 이요하여 파일 읽고 쓰기
- 멀티파트 폼 업로드 처리
- 로컬 머신 파일 저장할 수 있도록 멀터(multer) 모듈 구성
- 파일 형식 알아내고 유효성 검사 적용하기
- 동적 아바타 생성기 사용 

## 기본 세팅 파일 추가하기

#### 0. 폴더만들기
```
chapter-03
```

#### 1. package.json 추가
`package.json`은 패키지 관리 파일로, 프로그램 이름, 버전 등 노드 프로그램의 정보를 이 파일에 기술한다. 사용하는 모듈 목록을 알 수있다.
```json
 "dependencies": {
    "bcrypt-nodejs": "0.0.3",
    "body-parser": "~1.13.2",
    "connect-flash": "^0.1.1",
    "connect-mongo": "^1.1.0",
    "cookie-parser": "~1.3.5",
    "debug": "~2.2.0",
    "ejs": "~2.3.3",
    "express": "~4.13.1",
    "express-session": "^1.13.0",
    "gravatar": "^1.4.0",
    "mongoose": "^4.4.5",
    "morgan": "~1.6.1",
    "multer": "^1.1.0",
    "node-sass-middleware": "0.8.0",
    "passport": "^0.3.2",
    "passport-local": "^1.0.0",
    "serve-favicon": "~2.3.0"
  },
  "devDependencies": {
    "nodemon": "^1.9.1"
  }
```

#### 2. `.EditorConfig` 추가 

`.EditorConfig는` 개발자가 다른 편집기와 IDE간에 일관된 코딩 스타일을 정의하고 유지할 수 있도록 설정해주는 파일이다

##### .editorconfig 
```
# http://editorconfig.org
root = true

[*]
indent_style = tab
indent_size = 4
charset = utf-8
trim_trailing_whitespace = true
insert_final_newline = true

[*.md]
trim_trailing_whitespace = false
```

#### 3. `.gitignore`파일 추가 
git 에 프로젝트를 올려놓을때, 중요한 키값이 들어있거나 올리고 싶지 않은 파일들을 설정할 수 있다. 

#### 4. 서버 폴더 추가하기
```
public
    ㄴ /images
    ㄴ /javascripts
    ㄴ /stylesheets
    ㄴ /uploads
    ㄴ /videos
server
    ㄴ /config
    ㄴ /controllers
    ㄴ /models
    ㄴ /views
```


#### 5. 모듈 설치하기

```
$ npm install
```

필요한 dependency들이 설치된다


###### 노드 폴더 구조

- `./node_module` : 이 express앱에 필요한 module을 저장한다.
- ` ./public` : html, image, javascript,css와 같은 정적 파일들을 저장한다.
-  `./routes` : URL 별로 수행되는 로직을 저장한다.
- `./views `: HTML view 템플릿을 저장한다. (여기서는 ejs로 지정하였기 때문에, ejs 템플릿들이 여기 저장된다.)
- `app.js` : 이 웹프로젝트의 메인 소스 코드
- `package.json` : module의 package.json 파일로, 의존성 및 npm script 명령어를 정의한다.


## app.js 설정하기 

#### 1. 모듈 추가

```javascript

var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var bodyParser = require('body-parser');
var cookieParser = require('cookie-parser');

```
##### morgan 
웹에서 요청이 들어왔을 때 로그를 출력해주는 미들 웨어
#### path
파일의 경로를 다루는 코어 모듈로, 파일명 추출 ,디렉토리 추출, 경로생성 , 확장자 추출 등의 기능을 제공해준다.
#### multer
이미지 등 바이너리 파일 전송 모듈. multipart/form-data 방식을 지원해 준다.

#### 2. 멀터 불러오기 

```javascript
var multer = require('multer');
var upload = multer(
    // dest: 올라갈 목적지
    // limits: 파일 옵션
    { dest: './public/uploads', 
      limits:{
        fileSize:1000000,
        files:1}
    });
```

#### 3. 컨트롤러 불러오기 

```javascript
var index = require('./server/controllers/index');
var auth = require('./server/controllers/auth');
var comments = require('./server/controllers/comments');
var videos = require('./server/controllers/videos');
var images = require('./server/controllers/images');
```

#### 4. database 모듈 추가

```javascript
var mongoose = require('mongoose');
var session = require('express-session');
var MongoStore = require('connect-mongo')(session);
var passport = require('passport');
var flash = require('connect-flash');

```

#### 5. 익스프레스 app 시작

```javascript
var app = express();
```

#### 6. view 관련 엔진, 데이터베이스 연결 추가

```javascript
//뷰 엔진 설정 
app.set('views', path.join(__dirname, 'server/views/pages') );
app.set('view engine', 'ejs');

// 경로 설정 
var config = require('./server/config/config.js');
// config.url에 몽고 db url 있음 이걸 mongoose  로 연결 
mongoose.connect(config.url);
// 몽고 db 실행중인지 확인
mongoose.connection.on('error',function(){
    console.error('Mongo error!')
});
// 사용자 인증 세팅 
// passport settting
require('./server/config/passport')(passport);
```

#### 7. 기본 미들웨어 설정 및 passport-local 초기화 

```javascript
app.use(logger('dev'));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));
app.use(cookieParser());
app.use(require('node-sass-middleware')({
    src: path.join(__dirname, 'public'),
    dest: path.join(__dirname, 'public'),
    indentedSyntax: true,
    sourceMap: true
}));
app.use(express.static(path.join(__dirname, 'public')));

// passport 
// 세션 비밀키 
app.use(session({
    secret: 'sometextgohere',
    saveUninitialized: true,
    resave: true,
    store: new MongoStore({
        url: config.url,
        collection : 'sessions'
    })
}));
// 패스포트 인증 초기화 
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());
```

#### 8. 라우트 추가 

```javascript
app.get('/', index.show);
app.get('/login', auth.signin);
app.post('/login', passport.authenticate('local-login', {
    successRedirect : '/profile',
    failureRedirect : '/login',
    failureFlash : true
}));
app.get('/signup', auth.signup);
app.post('/signup', passport.authenticate('local-signup', {
    successRedirect : '/profile',
    failureRedirect : '/signup',
    failureFlash : true
}));

app.get('/profile', auth.isLoggedIn, auth.profile);

app.get('/logout', function(req, res) {
    req.logout();
    res.redirect('/');
});

app.get('/comments', comments.hasAuthorization, comments.list);
app.post('/comments', comments.hasAuthorization, comments.create);

app.get('/videos', videos.hasAuthorization, videos.show);
app.post('/videos', videos.hasAuthorization, upload.single('video'), videos.uploadVideo);

app.post('/images', images.hasAuthorization, upload.single('image'), images.uploadImage);
app.get('/images-gallery', images.hasAuthorization, images.show);
```

#### 9. error 함수 추가

```javascript

app.use(function(err, req, res, next) {
    res.status(err.status || 500);
    res.render('error', {
        message: err.message,
        error: {}
    });
});
module.exports = app;
app.set('port', process.env.PORT || 3000);

var server = app.listen(app.get('port'), function() {
    console.log('Express server listening on port ' + server.address().port);
});

```

## config.js 파일 생성 
##### server/config/config.js
```javascript
module.exports = {
    'url' : 'mongodb://localhost/mvc-app-multimedia'
}
```

## controller 생성
##### server/controllers/auth.js
```javascript

var gravatar = require('gravatar');
var passport = require('passport');

exports.signin = function(req, res) {
    res.render('login', { title: 'Login Page', message: req.flash('loginMessage') });
};
// Signup GET
exports.signup = function(req, res) {
    res.render('signup', { title: 'Signup Page', message: req.flash('signupMessage') });

};
exports.profile = function(req, res) {
    res.render('profile', { title: 'Profile Page', user : req.user, avatar: gravatar.url(req.user.email ,  {s: '100', r: 'x', d: 'retro'}, true) });
};
exports.logout = function () {
    req.logout();
    res.redirect('/');
};
exports.isLoggedIn = function(req, res, next) {
    if (req.isAuthenticated())
        return next();
    res.redirect('/login');
};
```

##### server/controllers/comments.js
```javascript
var gravatar = require('gravatar');
var Comments = require('../models/comments');

exports.list = function(req, res) {
    Comments.find().sort('-created').populate('user', 'local.email').exec(function(error, comments) {
        if (error) {
            return res.send(400, {
                message: error
            });
        }
        res.render('comments', {
            title: 'Comments Page',
            comments: comments,
            gravatar: gravatar.url(comments.email ,  {s: '80', r: 'x', d: 'retro'}, true)
        });
    });
};
// Create Comments
exports.create = function(req, res) {
    var comments = new Comments(req.body);
    comments.user = req.user;
    comments.save(function(error) {
        if (error) {
            return res.send(400, {
                message: error
            });
        }
        res.redirect('/comments');
    });
};
exports.hasAuthorization = function(req, res, next) {
	if (req.isAuthenticated())
        return next();
    res.redirect('/login');
};
```

##### server/controllers/index.js
```javascript
exports.show = function(req, res) {
	res.render('index', {
		title: 'Multimedia Application',
		callToAction: 'An easy way to upload and manipulate files with Node.js'
	});
};
```

##### server/controllers/images.js
```javascript
// Import modules
var fs = require('fs');
var mime = require('mime');
// get gravatar icon from email
var gravatar = require('gravatar');

var Images = require('../models/images');
// set image file types
var IMAGE_TYPES = ['image/jpeg','image/jpg', 'image/png'];

// Show images gallery
exports.show = function (req, res) {

    Images.find().sort('-created').populate('user', 'local.email').exec(function(error, images) {
        if (error) {
            return res.status(400).send({
                message: error
            });
        }
        // REnder galley
        res.render('images-gallery', {
            title: 'Images Gallery',
            images: images,
            gravatar: gravatar.url(images.email ,  {s: '80', r: 'x', d: 'retro'}, true)
        });
    });
};

// Image upload
exports.uploadImage = function(req, res) {
    var src;
    var dest;
    var targetPath;
    var targetName;
    var tempPath = req.file.path;
    console.log(req.file);
    //get the mime type of the file
    var type = mime.lookup(req.file.mimetype);
    // get file extension
    var extension = req.file.path.split(/[. ]+/).pop();
    if (IMAGE_TYPES.indexOf(type) == -1) {
        return res.status(415).send('Supported image formats: jpeg, jpg, jpe, png.');
    }
    targetPath = './public/images/' + req.file.originalname;
    src = fs.createReadStream(tempPath);
    dest = fs.createWriteStream(targetPath);
    src.pipe(dest);

    src.on('error', function(err) {
        if (err) {
            return res.status(500).send({
                message: error
            });
        }
    });
    src.on('end', function() {
        var image = new Images(req.body);
        image.imageName = req.file.originalname;
        image.user = req.user;
        image.save(function(error) {
            if (error) {
                return res.status(400).send({
                    message: error
                });
            }
        });
        fs.unlink(tempPath, function(err) {
            if (err) {
                return res.status(500).send('Woh, something bad happened here');
            }
            res.redirect('images-gallery');

        });
    });
};
exports.hasAuthorization = function(req, res, next) {
    if (req.isAuthenticated())
    return next();
    res.redirect('/login');
};
```
##### server/controllers/videos.js

```javascript
var fs = require('fs');
var mime = require('mime');
var gravatar = require('gravatar');

var Videos = require('../models/videos');
var VIDEO_TYPES = ['video/mp4', 'video/webm', 'video/ogg', 'video/ogv'];

exports.show = function(req, res) {

    Videos.find().sort('-created').populate('user', 'local.email').exec(function(error, videos) {
        if (error) {
            return res.status(400).send({
                message: error
            });
        }
        console.log(videos);
        res.render('videos', {
            title: 'Videos Page',
            videos: videos,
            gravatar: gravatar.url(videos.email ,  {s: '80', r: 'x', d: 'retro'}, true)
        });
    });
};
exports.uploadVideo = function(req, res) {
    var src;
    var dest;
    var targetPath;
    var targetName;
    console.log(req);
    var tempPath = req.file.path;
    var type = mime.lookup(req.file.mimetype);
    var extension = req.file.path.split(/[. ]+/).pop();
    if (VIDEO_TYPES.indexOf(type) == -1) {
        return res.status(415).send('Supported video formats: mp4, webm, ogg, ogv');
    }
    targetPath = './public/videos/' + req.file.originalname;
    src = fs.createReadStream(tempPath);
    dest = fs.createWriteStream(targetPath);
    src.pipe(dest);
    src.on('error', function(error) {
        if (error) {
            return res.status(500).send({
                message: error
            });
        }
    });

    src.on('end', function() {
        var video = new Videos(req.body);
        video.videoName = req.file.originalname;
        video.user = req.user;
        video.save(function(error) {
            if (error) {
                return res.status(400).send({
                    message: error
                });
            }
        });
        fs.unlink(tempPath, function(err) {
            if (err) {
                return res.status(500).send({
                    message: error
                });
            }
            res.redirect('videos');

        });
    });
};
exports.hasAuthorization = function(req, res, next) {
    if (req.isAuthenticated())
        return next();
    res.redirect('/login');
};
```

## 모델 파일 생성하기 
##### server/model/comment.js
```javascript
// load the things we need
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var commentSchema = mongoose.Schema({
    created: {
        type: Date,
        default: Date.now
    },
    title: {
        type: String,
        default: '',
        trim: true,
        required: 'Title cannot be blank'
    },
    content: {
        type: String,
        default: '',
        trim: true
    },
    user: {
        type: Schema.ObjectId,
        ref: 'User'
    }
});

module.exports = mongoose.model('Comments', commentSchema);
```

##### server/models/users.js

```javascript
var mongoose = require('mongoose');
var bcrypt   = require('bcrypt-nodejs');
var userSchema = mongoose.Schema({
    local: {
        name: String,
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

##### server/models/images.js
```javascript
// load the things we need
var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var imagesSchema = mongoose.Schema({
    created: {
        type: Date,
        default: Date.now
    },
    title: {
        type: String,
        default: '',
        trim: true,
        required: 'Title cannot be blank'
    },
    imageName: {
        type: String
    },
    user: {
        type: Schema.ObjectId,
        ref: 'User'
    }
});

module.exports = mongoose.model('Images', imagesSchema);
```

## view 파일 생성하기
chapter-01과 같은 파일을 생성한다. chapter-01 참조하기 
## 참고자료 

- http://editorconfig.org/
- http://cpdev.tistory.com/48
- http://bcho.tistory.com/887 
- http://avilos.codes/server/nodejs/node-js-path%EB%AA%A8%EB%93%88/
- http://blog.jeonghwan.net/%EC%9D%B4%EB%AF%B8%EC%A7%80-%EC%97%85%EB%A1%9C%EB%93%9C-1-multer-%EB%AA%A8%EB%93%88%EB%A1%9C-%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C/