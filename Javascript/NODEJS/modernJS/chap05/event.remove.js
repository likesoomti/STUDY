var onUncaughtException = function(error){
    // print 
    console.log("exception!");

    // event 1번 실행 후 제거됨 remove
    process.removeListener('uncaughtException',onUncaughtException);
}

// event on! 
process.on('uncaughtException',onUncaughtException);

var test = function(){
    setTimeout(test,2000);
    error.error.error();
};

setTimeout(test,2000);