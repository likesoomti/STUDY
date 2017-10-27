var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var sassMiddleware = require('node-sass-middleware');


//add soomin

var mongoose = require('mongoose');
var session = require('express-session');
var MongoStore = require('connect-mongo')(session);

var comments = require('./server/controllers/comments');

//패스포트 경고 플래시 메세지 모듈 가져오기. (인증 미들웨어, 유저 패스워드 인증)
var passport = require('passport');
var flash = require('connect-flash');


//add soomin end 
var index = require('./server/routes/index');
var users = require('./server/routes/users');

var app = express();

//soomin add 
// view engine setup
app.set('views', path.join(__dirname, 'server/views/pages'));
app.set('view engine', 'ejs');

//db setting 
var config = require('./server/config/config.js');
mongoose.connect(config.url);
//mongo db check
mongoose.connection.on('error',function(){
  console.error('mongo error . make sure mongo is running')
});

//passport set
require('./server/config/passport')(passport);

//soomin add end 

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

//passport 용 
//세션용 비밀 키 
app.use(session({
  secret: 'soomin',
  saveUninitialized: true,
  resave: true,
  //몽고디비에저장하는거
  store: new MongoStore({
    url: config.url,
    collection: 'sessions'
  })
}));
//passport 인증 초기화
app.use(passport.initialize());
app.use(passport.session());
app.use(flash());

app.use('/', index);
app.use('/users', users);

app.get('/comments',comments.hasAuthorization,comments.list);
app.post('/comments',comments.hasAuthorization,comments.create);

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

app.set('port',process.env.PORT || 3000);

var server = app.listen(app.get('port'),function(){
  console.log('Express server listening on port '+server.address().port);
});