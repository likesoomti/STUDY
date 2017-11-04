require('http').createServer(function(req,res){
    res.writeHead(200,{'Content-Type': 'text/html'});
    res.end('<h1>hello node! </h1>')
}).listen(22222,function(){
    console.log('server start!');
})