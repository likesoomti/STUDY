process.on('exit',function(){
    console.log("goodbye");
})

// force event
process.emit('exit');
process.emit('exit');
process.emit('exit');
process.emit('exit');
process.emit('exit');

// program exe
console.log("program exe");