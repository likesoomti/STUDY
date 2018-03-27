# [kotlin]package,return,jump.md

### 패키지

소스 파일을 만들고 위에 패키지 선언을 한다

###### example

```kotlin
// a.kt
package foo.bar
fun printOther2(){
  println("hi")
}

// b.kt
fun printOther(){
  println("hi")
}
// main.kt
fun main(args: Array<String>){
  // error not import package
  // main 위에 printOther2를 선언 해야한다.
  printOther2();
  
  
  // b.kt 는 패키지를 선언하지 않았다. 이럴때는 디폴트 패키지에 포함이 되어 따로 package 를 import 하지 않아도된다.
  printOther();
}
```

모든 콘텐츠는 패키지에 포함이 된다.

패키지를 선언 안하면 디폴트 패키지에 포함이 된다.



#### 기본 패키지

기본으로 import 되는 패키지가 존재한다

- kotlin.*

- kotlin.collections

- kotlin.annotaions

  등등

그래서

```kotlin
fun main(){
  var a = listOf(1,2,3)
}
```

의 경우에도 `listOf` import 를 시키지 않았지만 사용이가능하다. 기본으로 import 된 패키지 이기 때문이다.

#### import 하기

```kotlin
// Bar 1개만 가능
import foo.Bar

// foo 패키지 모든것을 임포트하기
import foo.*

// foo.Bar
// bar.Bar 

// 이름 충돌시, as 키워드로 로컬 리네임을 시켜야한다.

import bar.Bar as bBar
```



### jump

3가지 jump 표현식 

- return : 함수/익명 함수에서 반환
- break: 루프를 종료시킴
- continue: 루프 다음 단계로 진행

##### label 사용하기

```kotlin
// 중첩된 for loop시에는 둘 다 종료 시켜줘야함.
// 코틀린에서는 label 을 통해 조정할 수 있다.
for(i in 1..10){
  for(j in 1..10){
    
  }
}

// label

loop@ for(i in 1..10){
  
  for(j in 1..10){
    // @loop 부분으로 넘어간다.
    // 그럼 포문 전이니 둘다 빠져나감.
    break @loop
  }
}
```



##### label 로 return 하기

코틀린 중첩 요소들

- 함수 리터럴
- 지역 함수
- 객체 표현식
- 함수

```kotlin
fun foo(){
  var ints = listOf(1,2,3)
  // 충첩 함수
  int.forEach(fun(value: Int){
    // return 시 가까운 함수 1개 반환 return 
    if(value ==1) return
    print(value)
  })
  print("END")
}
// -> 023END
```

함수가 중첩되어있을때 return 은 해당하는 함수만 반환한다

##### 람다의 경우

```kotlin
fun foo(){
  var ints = listOf(1,2,3)
  // 충첩 함수
  int.forEach(
    {
      // return 시 가까운 함수 1개 반환 return 
      if(value ==1) return
      print(value)
    })
  print("END")
}
// -> 0
```

람다에서는 0만 출력된다.

왜냐면 **람다는 함수가 아니다**

따라서 제일 가까운 함수인 foo() 가 종료되어버린다.

람다 식을 return 하고 싶은 경우 label을 사용하면 된다

###### example

```kotlin
fun foo(){
  var ints = listOf(1,2,3)
  // 충첩 함수
  int.forEach @label(
    {
      // return 시 가까운 함수 1개 반환 return 
      if(value ==1) return @label
      print(value)
    })
  print("END")
}
// -> 023END
```



##### 암시적 레이블

직접 label 을 사용하기 보다, 암시적 레이블을 사용하는게 좋다

직접 변수@ 보다 사용한 함수 이름을 레이블로 사용할 수 있다.

###### example

```kotlin
fun foo(){
  var ints = listOf(1,2,3)
  // 충첩 함수
  int.forEach (
    {
      // return 시 가까운 함수 1개 반환 return 
      if(value ==1) return @forEach
      print(value)
    })
  print("END")
}
// -> 023END
```



##### 값을 반환시 레이블도사용하고, 값도 사용하고 싶을때

###### return label value 순서로 사용한다.

```kotlin
fun foo(){
  var ints = listOf(1,2,3)
  // 충첩 함수
  int.forEach (
    {
      // return 시 가까운 함수 1개 반환 return 
      if(value ==1) return @forEach "zero"
      print(value)
    })
  print("END")
}
// -> 023END
```