# chap03 Node.js의 전역 객체

## 3.1 전역 변수

웹 브라우저에서의 javascript 최상위 객체는 ``` window ``` 이지만, node.js는 서버 환경에서 작동하기 때문에 사용할 수 없다.

### node.js 에 관련한 전역 변수

#### 문자열 자료형의 전역변수

``` __filename ``` : 현재 실행중인 파일 경로를 나타낸다.

``` __dirname ``` : 현재 실행중인 코드의 폴더 경로를 나타낸다. 

##### sample
``` javascript
// 현재 실행중인 파일의 경로를 나타낸다.
console.log('filename:',__filename);
// 현재 실행중인 폴더의 경로를 나타낸다.
console.log('dirname:',__dirname);
```
##### result
```
filename: /Users/Soomti/STUDY/Javascript/NODEJS/modernJS/chap03/test.js
dirname: /Users/Soomti/STUDY/Javascript/NODEJS/modernJS/chap03
```

#### 전역 객체

``` console ``` : 콘솔 화면과 관련된 기능을 다루는 객체

``` exports ``` : 모듈과 관련된 기능을 다루는 객체

``` process ``` : 프로그램과 관련된 가능을 다루는 객체

## 3.2 console 객체

콘솔 화면과 관련된 기능을 다룬다.

### console 객체의 메서드

``` log() ```: 원하는 값을 출력한다

``` time() ```: 시간 측정을 시작한다

```timeEnd()```: 시작 측정을 종료한다


#### console.log()

특수 문자를 이용해 문자열을 출력할 수 있다.

##### 특수 문자
``` %d ``` :  숫자

``` %s ``` :  문자열

``` %j ``` :  json

#####  sample

```
> console.log('output: %d', 273); // %d를 입력하면 그 뒤에 숫자를 찾아 그 안에 출력해준다.
// 순서대로 출력된다.
> console.log('%d + %d = %d', 273, 52, 273+52);
// 매개변수의 갯수가 다른 경우 
> console.log('%d + %d = %d', 273, 52, 273+52,52273);
> console.log('%d + %d = %d & %d', 273, 52, 273+52);
```

#####  result
```
output: 273
273 + 52 = 325
// 매개변수의 갯수가 다른 경우 
273 + 52 = 325 52273 //매개변수가 넘칠경우 뒤에 문자열로 출력  
273 + 52 = 325 & %d // 매개변수가 모자랄경우 문자열로 출력 
```

##### sample2
```
console.log('문자열: %s', 'Hello World..!','특수 기호와 상관 없음');
console.log('JSON: %j',{name:'RintIanTta'});
```

##### result2
```
문자열: Hello World..! 특수 기호와 상관 없음
JSON: {"name":"RintIanTta"}
````

##### console.time()
프로그램 실행 시간을 출력할 수 있다.

##### sample
``` javascript
// 시간 측정을 시작한다.
console.time('alpha');
var output = 1;
for(var i = 1 ; i <= 10; i ++)
{
    output *= 1;
}
console.log('Result:',output);
//시간 측정을 종료한다.
console.timeEnd('alpha');
```
##### result
```
Result: 1
alpha: 0.078ms
```

## 3.3 process 객체

서버사이드 언어인 Node.js 만이 가진 객체 

###  process 객체의 속성 

```argv```: 실행 매개변수

```env```: 컴퓨터 환경과 관련된 정보

```version```: Node.js 버전 반환

```versions```: Node.js 종속된 프로그램 버전 반환

```arch```: 프로세스 아키텍처 반환

```platform```: 플랫폼 반환

###  process 객체의 속성 

```exit([exitcode = 0])``` :  프로그램 종료
```memoryUsage()``` : 메모리 사용정보 객체 리턴
```uptime()```: 현재 프로그램이 실행된 시간 리턴 

#### process.argv,process.exit() sample

```javascript
//process.argv,process.exit() sample
//process.argv
process.argv.forEach(function(item,index) {
    //print
    console.log(index + ' : ' + typeof(item) + ' : ', item);

    // 실행 매개변수에 --exit 있을 때
    if (item == '--exit'){
        // 다음 실행 매개변수를 얻습니다.
        var exitTime = Number(process.argv[index +1]);

        // 일정 시간 후 프로그램을 종료한다.
        setTimeout(function(){
            process.exit();

        },exitTime);
    }
});
```
##### bash
4개의 매개변수를 넣는다.
```
$ node node.process.js --exit 10000
```

#### process.argv,process.exit() result

```
0 : string :  /usr/local/bin/node //node 의 경로를 출력 
1 : string :  /Users/Soomti/STUDY/Javascript/NODEJS/modernJS/chap03/node.global.js

// 파일 명의 경로를 출력 
2 : string :  --exit // 들어온 매개변수 문자열을 출력
3 : string :  100000 // 들어온 매개변수 문자열을 출력
```

## 3.4 exports 객체와 모듈 