var express = require('express');
var router = express.Router();
var passport = require('passport');


/* GET home page. */
router.get('/', function(req, res, next) {
    res.render('index', { title: 'Express from server folder' });
});

/* GET login page. */
router.get('/login', function(req, res, next) {
    res.render('login', { title: 'Login Page', message: "로그인 페이지" });
});
/* POST login */
router.post('/login', passport.authenticate('local-login', {
    successRedirect : '/profile',
    failureRedirect : '/login'
}));

/* GET Signup */
router.get('/signup', function(req, res) {
    res.render('signup', { title: 'Signup Page', message: "가입 페이지" });
});
/* POST Signup */
router.post('/signup', passport.authenticate('local-signup', {
    //Success go to Profile Page / Fail go to Signup page
    successRedirect : '/profile',
    failureRedirect : '/signup'
}));

/* check if user is logged in */
function isLoggedIn(req, res, next) {
    if (req.isAuthenticated())
        return next();
    res.redirect('/login', { message: 'success' });
}
/* GET Logout Page */
router.get('/logout', function(req, res) {
    req.logout();
    res.redirect('/');
});

module.exports = router;