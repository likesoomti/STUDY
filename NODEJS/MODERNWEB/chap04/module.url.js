const url = require('url');

var url_obj = url.parse('https://www.naver.com/');
console.log(url_obj);

console.log(url.format(url_obj));