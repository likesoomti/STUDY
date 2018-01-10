var passport = require('passport')
  , LocalStrategy = require('passport-local').Strategy;
var User = require('../model/user');

module.exports = function(passport){

    passport.serializeUser(function(user, done) {
        done(null, user);
      });
      
    passport.deserializeUser(function(user, done) {
      done(null, user);
    });
   // 회원가입
   // passport.auth 이름을 signup 으로 지정
   // new Locals~ 객체 생셩
   // new Locals~ 매개변수 1 가져온 params
   // new Locals~ 매개변수 2 params 에 대한 콜백함수
   passport.use('signup', new LocalStrategy({
    usernameField : 'email',
    passwordField : 'password',
    passReqToCallback : true
    },function(req, email, password, done) {
    if (email)
      email = email.toLowerCase();
    // exceptions
    if (!req.user) {
        User.findOne({ 'local.email' :  email }, function(err, user) {
          // errors
          if (err)
           return done(err) 
          // check email
          if (user) {
              return done(null, false);
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
            console.log(newUser);
          }
      });
    } else {
        return done(null, req.user);
    }
}));
}