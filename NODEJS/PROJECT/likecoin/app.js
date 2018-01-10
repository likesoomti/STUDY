var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var sassMiddleware = require('node-sass-middleware');



var index = require('./routes/index');
var users = require('./models/users');

var coins = require('./controllers/coins');

// var coins = require('./routes/coins');

// passports add //

var passport = require('passport');
var passport = require('passport')
    // 사용자 이름, 비밀번호로 인증 하는 모듈 
    , LocalStrategy = require('passport-local').Strategy;

var app = express();

// passports end //

// mongo connect
var mongoose = require('mongoose');
var session = require('express-session')
var MongoStore = require('connect-mongo')(session);

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// mongo setting
var config = require('./config/config.js');
// connect mongo
mongoose.connect(config.url);
// mongo db execute check
mongoose.connection.on('error',function(){
  console.error("몽고 디비 에러. 실행 중인지 확인해 주세요");
})
mongoose.connection.once('open', function(){
  // CONNECTED TO MONGODB SERVER
  console.log("Connected to mongod server");
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
app.get('/coins', coins.list);
app.post('/coins',coins.create);

// passport route use

require('./config/passport')(passport);
// Localstrategy 사용 . use() 메서드를 사용한다
app.use(session({

  secret: 'sometexthere',
  saveUninitialized: true,
  resave: true,

  // session connect-mongo 사용 
  store: new MongoStore({
    url: config.url,
    collection: 'sessions'
  })

  }));

app.use(passport.initialize());
app.use(passport.session());

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
