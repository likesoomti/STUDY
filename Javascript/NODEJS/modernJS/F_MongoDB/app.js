var mongo = require('mongojs');
var db = mongo('node',['products']); //(database,[collections,|])

// db.collections.의 값을 검색한다.
// console.log 를 띄우고싶다!! 
// 하지만 어떤걸 띄우길 정하지 않음
db.products.find(
    function(err,data){
        console.log(data);
    });