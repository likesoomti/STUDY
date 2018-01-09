// 미들웨어 임포트 
var passport = require('passport')
  , LocalStrategy = require('passport-local').Strategy;

// 패스포트 로컬 스트레티지 전략을 사용하겠다!

passport.use(new LocalStrategy(
  function(username, password, done) {
    User.findOne({ username: username }, function (err, user) {
      if (err) { return done(err); }
      if (!user) {
        return done(null, false, { message: 'Incorrect username.' });
      }
      if (!user.validPassword(password)) {
        return done(null, false, { message: 'Incorrect password.' });
      }
      return done(null, user);
    });
  }
));