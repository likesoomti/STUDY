## chap07 외부 모듈


### 외부모듈이란 

일반 개발자가 만들어 배포한 모듈
npm을 기반으로 모듈을 공유한다

### npm

Node.js의 패키지를 모아둔 유틸리티
Node.js 패키지 설치, 버전 및 호환성 관리할 수 있다

### package.json

node.js의 프로젝트 설정정보를 담은 파일

### 외부 모듈의 사용

#### 1. 외부 모듈의 설치

```javascript
    // 외부 모듈 ejs를 설치한다
    npm install ejs
    
    // 외부 모듈 jade를 설치한다.
    // jade는 2016년 pug로 이름이 개정되었다.
    // node.js 4버전에서는 jade 모듈을 사용한다.
    npm install jade 
```

#### 2. 외부 모듈의 사용

``` javascript
    // 외부 모듈을 추출한다. 
    var ejs = require('ejs');
    var jade = require('jade');
```


### 7-1 ejs 모듈


##### 템플릿모듈. 특정 형식의 문자열을 HTML 형식의 문자열로 반환한다.
구글에 쿠키랑 고양이랑 검색을 해볼 경우,
보여주는 검색 결과는 다르지만 ui 가 같은 걸 알 수 있다. 정적인 html으 경우 각각 하나의 html 파일로 응답해야 하지만, html을  템플릿 모듈을 사용하여 정적인 html 파일을 동적이게 만들 수 있다.



#### 1. 설치

```
$ npm install ejs 
```

#### 2. 추출

``` javascript 
// ejs 모듈을 추출한다.
var ejs = require('ejs') 
```

#### 3. 변환

```javascript 
// ejs 문자열을 html 문자열로 변경해준다.
ejs.render(data);
```

### ejs 모듈 sample code

ejs 모듈을 사용한 웹페이지 제공 

* app.js

```javascript
// 파일 시스템 모듈 사용
var fs = require('fs');
// 서버 생성을 위한 http모듈 사용 
var http = require('http');
// ejs 모듈 추출 
var ejs = require('ejs');

// 서버 생성 및 실행
http.createServer(function(req,res){
    fs.readFile('ejsPage.ejs','utf-8',function(err,data){

        // 응답 스트림에 헤더를 작성
        // writeHead 메소드는  헤더를 작성한다.
        // html 파일 형식으로 응답을 보낸다
        res.writeHead(200,{'Content-Type': 'text/html'});

        // ejs 문자열을 html 문자열로 변경해준다.
        res.end(ejs.render(data));

    });
}).listen(52273,function(){
    console.log('server run');
})

```
* ejsPage.ejs

``` javascript
<% var name = "soomti" %>
<h1><%=name %></h1>
<p><%= 52 * 273 %></p>
<hr />
<% for(var i = 0 ; i < 10 ; i ++){ %>
    <h2>the square of <%= i %> is <%= i * i %></h2>
<% } %>
```

* result

```
soomti
14196
the square of 0 is 0
the square of 1 is 1
the square of 2 is 4
the square of 3 is 9
the square of 4 is 16
the square of 5 is 25
the square of 6 is 36
the square of 7 is 49
the square of 8 is 64
the square of 9 is 81
```

#### ejs  파일의 특수 태그

밑에 특수 코드들은 render 메소드를 통해 html의 문자열로 변경된다.

```
<% code %> // 자바스크립트 코드를 입력한다.
<%= value %> // 데이터를 출력한다
<%% // <%fmf 출력 하고 싶을때 사용 
<%# // comment tag
```


### 7-3 서버 실행 모듈

* 지역 모듈 : 자바스크립트 파일 내부에서 사용하는 모듈
* 전역 모듈 : 터미널에서 사용할 수 있는 모듈. 전역모듈 설치 시 -g 라는 옵션을 사용한다. 시스템에 깔겠다는 명령어

```
$ npm install -g supervisor
$ npm install -g forever
```

#### supervisor 모듈

``` $ node app.js ``` 의 경우, 서버를 키고 파일 내용을 변경 시에 인식을 못한다. 

 ```supervisor``` 모듈은 파일 내용 변경을 인식하는 모듈로, 자동으로 인식하여 실행을 종료시킨후에 다시 실행시킨다.

 ##### 1.설치

 ```
 $ sudo npm install -g supervisor
 [sudo] password for USER : 비밀번호 입력
 ```

##### 2. 설치 확인

```
$ supervisor

---------- 실행 결과 ----------

Node Supervisor is used to restart programs when they crash.
It can also be used to restart programs when a *.js file changes.

Usage:
  supervisor [options] <program>
  supervisor [options] -- <program> [args ...]

Required:
  <program>
    The program to run.

Options:
  -w|--watch <watchItems>
    A comma-delimited list of folders or js files to watch for changes.
    When a change to a js file occurs, reload the program
    Default is '.'

  -i|--ignore <ignoreItems>
    A comma-delimited list of folders to ignore for changes.
    No default

  --ignore-symlinks
    Enable symbolic links ignoring when looking for files to watch.

  -p|--poll-interval <milliseconds>
    How often to poll watched files for changes.
    Defaults to Node default.

  -e|--extensions <extensions>
    Specific file extensions to watch in addition to defaults.
    Used when --watch option includes folders
    Default is 'node,js'

  -x|--exec <executable>
    The executable that runs the specified program.
    Default is 'node'

  --debug[=port]
    Start node with --debug flag.

  --debug-brk[=port]
    Start node with --debug-brk[=port] flag.

  --harmony
    Start node with --harmony flag.
  --inspect
    Start node with --inspect flag.

  --harmony_default_parameters
    Start node with --harmony_default_parameters flag.

  -n|--no-restart-on error|exit
    Don't automatically restart the supervised program if it ends.
    Supervisor will wait for a change in the source files.
    If "error", an exit code of 0 will still restart.
    If "exit", no restart regardless of exit code.
    If "success", no restart only if exit code is 0.

  -t|--non-interactive
    Disable interactive capacity.
    With this option, supervisor won't listen to stdin.

  -k|--instant-kill
    use SIGKILL (-9) to terminate child instead of the more gentle SIGTERM.

  --force-watch
    Use fs.watch instead of fs.watchFile.
    This may be useful if you see a high cpu load on a windows machine.

  -s|--timestamp
    Log timestamp after each run.
    Make it easy to tell when the task last ran.

  -h|--help|-?
    Display these usage instructions.

  -q|--quiet
    Suppress DEBUG messages

  -V|--verbose
    Show extra DEBUG messages

Options available after start:
rs - restart process.
     Useful for restarting supervisor eaven if no file has changed.

Examples:
  supervisor myapp.js
  supervisor myapp.coffee
  supervisor -w scripts -e myext -x myrunner myapp
  supervisor -- server.js -h host -p port
```

##### example

``` javascript
// 터미널 실행 
$ supervisor test.server.js

Running node-supervisor with
  program 'test.server.js'
  --watch '.'
  --extensions 'node,js'
  --exec 'node'

Starting child process with 'node test.server.js'
Watching directory '/Users/Soomti/STUDY/Javascript/NODEJS/modernJS/chap07' for changes.
Press rs for restarting the process.
test!
// 파일 변경후 저장 버튼 누른 후 
crashing child
Starting child process with 'node test.server.js'
test changed!

```


#### forever 모듈

단일 스레드 기반인 node.js 웹 서비스는 예외 하나로 웹 서비스가 크게 죽어버리는데, 이런 상황을 대비하고자 만든 모듈 

 ##### 1.설치

 ```
 $ sudo npm install -g forever
 [sudo] password for USER : 비밀번호 입력
 ```

##### 2. 실행

```
$ forever

// 실행 결과 

help:    usage: forever [action] [options] SCRIPT [script-options]
help:
help:    Monitors the script specified in the current process or as a daemon
help:
help:    actions:
help:      start               Start SCRIPT as a daemon
help:      stop                Stop the daemon SCRIPT by Id|Uid|Pid|Index|Script
help:      stopall             Stop all running forever scripts
help:      restart             Restart the daemon SCRIPT
help:      restartall          Restart all running forever scripts
help:      list                List all running forever scripts
help:      config              Lists all forever user configuration
help:      set <key> <val>     Sets the specified forever config <key>
help:      clear <key>         Clears the specified forever config <key>
help:      logs                Lists log files for all forever processes
help:      logs <script|index> Tails the logs for <script|index>
help:      columns add <col>   Adds the specified column to the output in `forever list`
help:      columns rm <col>    Removed the specified column from the output in `forever list`
help:      columns set <cols>  Set all columns for the output in `forever list`
help:      columns reset       Resets all columns to defaults for the output in `forever list`
help:      cleanlogs           [CAREFUL] Deletes all historical forever log files
help:
help:    options:
help:      -m  MAX          Only run the specified script MAX times
help:      -l  LOGFILE      Logs the forever output to LOGFILE
help:      -o  OUTFILE      Logs stdout from child script to OUTFILE
help:      -e  ERRFILE      Logs stderr from child script to ERRFILE
help:      -p  PATH         Base path for all forever related files (pid files, etc.)
help:      -c  COMMAND      COMMAND to execute (defaults to node)
help:      -a, --append     Append logs
help:      -f, --fifo       Stream logs to stdout
help:      -n, --number     Number of log lines to print
help:      --pidFile        The pid file
help:      --uid            Process uid, useful as a namespace for processes (must wrap in a string)
help:                       e.g. forever start --uid "production" app.js
help:                           forever stop production
help:      --sourceDir      The source directory for which SCRIPT is relative to
help:      --workingDir     The working directory in which SCRIPT will execute
help:      --minUptime      Minimum uptime (millis) for a script to not be considered "spinning"
help:      --spinSleepTime  Time to wait (millis) between launches of a spinning script.
help:      --colors         --no-colors will disable output coloring
help:      --plain          alias of --no-colors
help:      -d, --debug      Forces forever to log debug output
help:      -v, --verbose    Turns on the verbose messages from Forever
help:      -s, --silent     Run the child script silencing stdout and stderr
help:      -w, --watch      Watch for file changes
help:      --watchDirectory Top-level directory to watch from
help:      --watchIgnore    To ignore pattern when watch is enabled (multiple option is allowed)
help:      -t, --killTree   Kills the entire child process tree on `stop`
help:      --killSignal     Support exit signal customization (default is SIGKILL)
help:                       used for restarting script gracefully e.g. --killSignal=SIGTERM
help:      -h, --help       You're staring at it
help:
help:    [Long Running Process]
help:      The forever process will continue to run outputting log messages to the console.
help:      ex. forever -o out.log -e err.log my-script.js
help:
help:    [Daemon]
help:      The forever process will run as a daemon which will make the target process start
help:      in the background. This is extremely useful for remote starting simple node.js scripts
help:      without using nohup. It is recommended to run start with -o -l, & -e.
help:      ex. forever start -l forever.log -o out.log -e err.log my-daemon.js
help:          forever stop my-daemon.js

```

##### sample code

app.js

``` javascript
require('http').createServer(function(req,res){
    if(req.url == '/'){
        //response
        res.write('<!DOCTYPE html>');
        res.write('<html>');
        res.write('<head>');
        res.write('     <title>forever</title>');
        res.write('</head>');
        res.write('<body>');
        res.write('     <h1>FOREVER</h1>');
        res.write('</body>');
        res.write('</html>');
        res.end();
    }
    else{
        //에러발생
        error.error.error();
    }
}).listen(52273,function(){
    console.log('server run!!');
})
```

