const url = require('url');
const query = require('querystring');

//url string return obj
var parseObj = url.parse('http://www.hanbit.co.kr/store/books/look.php?p_code=1234');
console.log(parseObj.query) //p_code=1234
//parse -> return obj 
console.log(query.parse(parseObj.query))

