
// not on, use once
process.once('uncaughtException',function(error){

    console.log('error');
});

var test = function(){
    setTimeout(test,2000);
    error.error.error();
};

setTimeout(test,2000);