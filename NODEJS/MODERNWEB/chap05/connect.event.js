// process 객체에 exit 이벤트 연결
process.on('exit',function(code){
    console.log("good bye...^__^");
});

// process 객체에 uncaughtException 이벤트 연결

process.on('uncaughtException',function(error){
    console.log("exception!")
});

// 2초 간격으로 3번 예외를 발생시킨다.
var count = 0;

var test = function(){
    // break code
    count = count+1;

    if(count > 3) return;

    // 강제 예외 발생
    setTimeout(test,2000);
    error.error.error();
};
// 2초마다 uncaught error event callback
setTimeout(test,2000);
// logic이 끝나면 exit event callback 