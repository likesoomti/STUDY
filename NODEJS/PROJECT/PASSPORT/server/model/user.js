// 몽고 디비 연결 
var mongoose = require('mongoose');
// 암호화 모듈 생성 
var bcrypt   = require('bcrypt-nodejs');
// 유저 스키마 만들기 
var userSchema = mongoose.Schema({
    local: {
        email: String,
        password: String
    }

});
// 유저 암호화 모듈 메소드 
userSchema.methods.generateHash = function(password) {
    return bcrypt.hashSync(password, bcrypt.genSaltSync(8), null);
};
userSchema.methods.validPassword = function(password) {
    return bcrypt.compareSync(password, this.local.password);
};
module.exports = mongoose.model('User', userSchema);