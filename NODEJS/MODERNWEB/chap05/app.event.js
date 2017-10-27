// module 추출

var rint = require('./rint');

// event on
rint.timer.on('tick',function(code){
    console.log("event on!");
})