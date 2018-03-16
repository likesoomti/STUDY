# [kotlin]Kotlin 개요

## 코틀린이란

JETBrains 에서 만든 언어. 2011년 만들고, 2016년 공식 언어로 지원 되었다.



### 쓰는 이유

- 안드로이드 3.0 부터 코틀린 사용 가능
- 16년도 부터 오라클 소송 진행하면서, 스위프트를 쓴다, 고랭을 쓴다 여러 얘기 많았음
- 16년 7월 코틀린 얘기 솔솔솔 나옴  
- 2017 5월 공식 안드로이드 언어 지정됨!



### 특징 

1. 간결한 문법

2. 안전한 처리 (`NULL POINT EXCEPTION`)

3. 자바 호환

   ​

####  간결한 문법

코드의 양,문법이 간결하다

- ; 안써도 된다.
- 객체 생성 시 `new`사용 안해도 된다.
- 타입 추론형으로, 타입을 적지 않아도 된다.
- getter/setter 만들어 줄 필요 없이, data class 선언을 하면 자동으로 만들어 준다. 
- 자바의 람다보다 짧음!
- 가독성 있는 레벨에서만 코드를 줄이는게 좋다 ! 



####  안전성

`Null Pointer Exception` 

- 널 허용여부를 명확히 구분해 컴파일러 단계에서 검사한다. 
- 따라서 런타임 에러를 대폭 줄일 수 있다.

`auto type casting`

- 변수 및 면수 내 할당 된 값의 불변 여부를 알려준다. (컬렉션도 가능) 
- 상수는 `val`을 사용한다.(value)
- 변수는 `var`을 사용한다. (variable)

#### 람다

- 람다식을 지원한다.
- 자바로 작성된 Single Abstract Method 변환을 지운하여 람다식으로 표현할 수 있다.

###### java

```java
View view = ...;

View.setOnClickListener(new View.onClicklistener(){
  @override
  public void onClick(View view){
    Toast.makeText(view.getContext(),
    "click",
    Toast.LENGTH_SHORT).show();
  }
})
```

###### kotlin

```
val view = ;
view.setOnClickListener {
  Toast.makeText(it.context,"click",Toast.length_short).show();
}
```



#### 스트림 API 지원

자바 8은 안드로이드 버전 6.0 이상 사용하는 플랫폼에서만 `API` 를 사용할 수 있다는 제약이 있지만, 코틀린은 유사한 역할 함수들을 코틀린 표준 라이브러리를 통해 제공해서 버전에 상관없이 사용할 수 있다.

##### 스트림 api 가 뭐지 ? -> https://github.com/likesoomti/STUDY/blob/master/Kotlin/%5Bbasic%5DStream%20%EA%B3%BC%20Stream%20API.md



#### 자바 호환성

자바랑 100% 호환성이 가능하다.

코드를 바꿀때, 조금씩 바꿀 수 있다.

RxJava 또한 변경 가능하다. 
