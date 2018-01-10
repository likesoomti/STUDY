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
