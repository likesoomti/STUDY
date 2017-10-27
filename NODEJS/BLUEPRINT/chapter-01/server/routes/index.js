var express = require('express');
var router = express.Router();
var passport = require('passport');
// get gravatar icon from email
var gravatar = require('gravatar');


/* GET home page. */
router.get('/login',function(req,res,next){
  res.render('login',{ title:'Login page',message: req.flash('loginMessage')});
});
router.get('/signup',function(req,res){
  res.render('signup',{ title:'SignUp page',message: req.flash('signupMessage')});
});
router.get('/profile',function(req,res,next){
  res.render('profile',{ title:'profile page',user: req.user, avatar: gravatar.url(req.user.email,{s:'100',r:'x',d:'retro'},true)});
});
 
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Expres from server folder' });
});

// login post render시 passport.js의 local-login에 설정한 인증으로 넘어감  
router.post('/login',passport.authenticate('local-login',{
  successRedirect: '/profile',
  failureRedirect: '/login',
  failureFlash: true
}));

router.post('/signup',passport.authenticate('local-signup',{
  successRedirect: '/profile',
  failureRedirect: '/signup',
  failureFlash: true
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

