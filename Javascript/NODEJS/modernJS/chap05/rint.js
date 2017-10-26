//exports.timer = new.process.EventEmitter();
//process.EventEmitter is deprecated. Use require('events') instead.
var event = require('events');
exports.timer = new event.EventEmitter();

// setInterval은 Timers의 모듈
// 전역 모듈이어서 그냥 사용가능한듯 ?
setInterval(function(){
    exports.timer.emit('tick');
},1000);