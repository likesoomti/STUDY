const crypto = require('crypto');

var hash = crypto.createHash('sha256');
hash.update('crypto_hash');

var output = hash.digest('hex');

console.log('crypto_hash',output)

// 다른 문자열이더라도, 같은 같게 나올 수 있다.
// 해시화 된 암호를 또 암호화시켜 서버에 저장.
// 변환된 해시를 사용하여 사용자의 정보를 보호한다.

// hash example

// declare var
var key = 'my secret key'
var input = 'PASSWORD'

// crypto

// createCipher(algorithm, passsword)
var cipher = crypto.createCipher('aes192',key);
// update(data,input,output)
cipher.update(input,'utf8','base64');
var cipherOutput = cipher.final('base64');

// decrypto
var decipher = crypto.createDecipher('aes192',key);
decipher.update(cipherOutput,'base64','utf8');
var decipherOutput = decipher.final('utf8');

console.log( '원래 문자열 ' + input);
console.log( '암호화 :' + cipherOutput);
console.log( '암호화 해제: '+ decipherOutput);