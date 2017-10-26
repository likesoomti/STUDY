# Chap05 이벤트
```Node.js```는 이벤트 기반 비동기 입출력 플랫폼이다. 이벤트를 연결하고 및 생성하는 방법을 알아본다.

#### 필수 개념 
* ```on ``` : 이벤트 연결 메서드
* ``` emit ```: 이벤트 실행 메서드
* ```EventEmitter``` :이벤트 연결 객체 부모 



## 1. 이벤트 연결

#### 웹에서 동작하는 자바스크립트로 이벤트 연결하기
웹에서는 window 객체에 load 이벤트를 사용한다

###### sample
```javascript
// window 객체에 load 이벤트 연결 
// 'load' : 이벤트 이름 or 타입
// callback : 이벤트 리스너 or 핸들러 
window.addEventListener('load',function(){

});
```
#### Node.js의 이벤트 연결 메서드 

```on``` : 이벤트를 연결 

###### sample

```javascript
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
```

###### result 
```
exception!
exception!
exception!
good bye...^__^
```


## 2. 이벤트 연결 개수 제한

Node.js는 하나의 이벤트에 10개가 넘는 이벤트 리스너 연결시, 개발자의 실수로 간주, 경고를 보낸다.

###### sample

```javascript
// 10개가 넘는 이벤트 연결

process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});

process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});

```
###### result
```
(node:12429) Warning: Possible EventEmitter memory leak detected. 11 exit listeners added. Use emitter.setMaxListeners() to increase limit
```

#### 경고제거 메서드

```setMaxListeners(limit)``` : 이벤트 리스너 연결 개수를 조절

###### sample
```javascript
// 연결 갯수를 15개로 변경
process.setMaxListeners(15);

// 10개가 넘는 이벤트 연결
process.on('exit',function(){});
process.on('exit',function(){});
process.on('exit',function(){});
...
```

## 3. 이벤트 제거

#### 이벤트 제거 메서드
```removeListener(eventName,handler)``` : 특정 이벤트 리스너 제거
```removeAllListeners([EventName])``` : 모든 이벤트 리스너 제거 

###### sample

```javascript
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
```

###### result 
```javascript
// no removeListener
exception!
exception!
exception!
exception!
exception!
exception!
exception!

// use removeListener

exception!
event.remove.js:14
    error.error.error();

ReferenceError: error is not defined
    at Timeout.test [as _onTimeout] (/Users/Soomti/STUDY/Javascript/NODEJS/modernJS/chap05/event.remove.js:14:5)
    at ontimeout (timers.js:386:14)
    at tryOnTimeout (timers.js:250:5)
```

#### 이벤트 연결 메서드
위에 처럼 한번만 달고 지울경우는 once 메서드가 유용하다

```once(eventName,eventHandler)``` : 이벤트 리스너를 한번만 연결 

###### sample 
```

// not on, use once
process.once('uncaughtException',function(error){

    console.log('error');
});

var test = function(){
    setTimeout(test,2000);
    error.error.error();
};

setTimeout(test,2000);
```

## 4. 이벤트 강제 발생
이벤트를 강제로 발생시킬 수 있다. 이벤트 리스너 만 실행된다.

#### 이벤트 강제 발생 메서드

```emit``` : 이벤트를 강제로 실행

###### sample
``` javascript
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

// 결과는 위 샘플 (use removeListener)result 와 동일하다 . 
```

###### result 

```javascript
// exit 이벤트가 실행되었으나 종료되진 않았다.
goodbye
goodbye
goodbye
goodbye
goodbye
program exe 
goodbye
```
## 5. 이벤트 강제 발생
```EventEmitter```: Node.js에서 이벤트를 연결할수 있는 객체의 부모 .
상속 받은 객체는 EventEmitter를 생성자 함수로 생성할 수 있다.

#### EventEmitter의 메서드

```addListener(eventName,eventHandler)``` : 이벤트를 연결한다

```on(eventName,eventHandler)``` : 이벤트를 연결한다

```setMaxListeners(limit)``` : 이벤트 리스너 연결 개수를 조절

```removeListener(eventName,handler)``` : 특정 이벤트 리스너 제거

```removeAllListeners([EventName])``` : 모든 이벤트 리스너 제거 

```once(eventName,eventHandler)``` : 이벤트 리스너를 한번만 연결 

###### sample