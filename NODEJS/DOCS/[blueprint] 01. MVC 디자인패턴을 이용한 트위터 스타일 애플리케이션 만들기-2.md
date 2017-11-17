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
