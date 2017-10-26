// EventEmitter 객체 생성
// DeprecationWarning: process.EventEmitter is deprecated. Use require('events') instead.
var custom = new process.EventEmitter();


// event on

custom.on('tick',function(code){
    console.log('event exe');
});

// force event

custom.emit('tick');