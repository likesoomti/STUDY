# [JAVA] 4. 컴퓨터의 기본 소통수단(2)

```
https://www.inflearn.com/course-status-2/ 의 강의 4. Java 컴퓨터의 기본 수단 2을 정리한 글입니다.
```



### 제어문

어떤 상황을 true / false 로 반환합니다.



##### 감기에 걸렸다!

결근을 한다 (yes)

결근하지 않는다 (NO) 로나뉜다.



### 루프문

일상적으로 반복해서 하는게 많다.

구구단 1~9에서 곱하는것 처럼

프로그래밍에도 반복이 많다. 이걸 편하게 하기 위한 문법



#### if 문

제어문의 기초

```java
// 1. if
if(expression){
  // expression이 true 이면 실행합니다.
}
// 2. if-else
if(expression){
  // expression 이 true 이면 실행합니다.
}else{
  // expression 이 false 이면 실행합니다.
}

// 3. if-else if
if(expression){
  
}else if(expression2..){
  // 조건이 여러개인 경우 사용합니다. 1일경우2일경우3일경우 등
  // 무한개로 조건을 나눌 수 있습니다.
}else{
  
}
```



### switch 문

if-else 보다 가독성이 좋아 인기가 높습니다.

스위치를 많이 쓰다보면 개발을했다! 고 인정합니다.

```java
switch(variable){
  case 'a':
    // variable 이 a 와 일치하면 이 부분에 내용이 실행됩니다.
    break;
  case 'b':
    // variable 이 b 와 일치하면 이 부분에 내용이 실행됩니다.
    break;
  default:
    // 일치하지 않으면 default 가 실행됩니다.
    break;
}
```



### 루프문

### for

```java
for(초기값;조건;증감){
  // 조건이 만족할 때 까지 반복합니다.
  // 증감을 통해 조건을 제외하는 기점이 있어야합니다.(안그러면 무한 루프)
}
// 10이상 값이 커지면 for 문을 빠져나갑니다.
for(int i=0;i<10;i++){
  sout(i)
    if(i==0){
      continue; //continue는 밑의 코드로 가지않고 바로 증감연산자로 가서 반복합니다.
    }
    if(i>=3){
      break;// break 는 포문을 아예 빠져나가게 하는 문장입니다.
    }
}
```



##### 구구단 만들기

```java
for(i=2;i<=9;i++){
  for(j=1;j<=9;j++){
    sout(i*j)
  }
}
```



### while 

반복문

```java
while(expression){
  // 조건을 만족할 때 가지 반복합니다.
  // 조건을 만족하지 않는 구문이 필요합니다.
}
```

