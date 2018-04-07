# [Kotlin] 기본 문법.md



## 패키지 정의

자바와 비슷하다.

파일 최 상단에 적으면 된다.

```kotlin
package my.demo

import java.util.*
```

- 맨 위 상단에 정의를 한다
- 자바는 이 경로에 이 파일에 있어야 했지만, 코틀린은 경로랑 일치 안해도된다. 좀더 유동성 있음 ㅎㅎ
- `import` 는 자바와 같다.



## 함수 정의

- 반환타입, 접근 지정자 안나옴
- 함수 함수이름 인자,리턴타입 형식
- 리턴타입은 생략이 될수도 있다.
- fun 키워드로 함수를 선언한다.
- 간결한 문법이 특징이기 때문에, 식으로 표시를 하면 return 을 생략 가능하다. 

######  example

```kotlin
fun sum(a:Int, b: Int): Int {
  return a+b
}
// 1. fun 키워드로 함수를 선언한다.
// 2. 함수이름, 인자, 리턴타입이 그 이후에 나온다
// 3. 리턴 타입은 생략 될 수 있다.

// 간결하게 줄여서 사용할 수 있다.

// 위의 함수 간결하게 
fun sum(a:Int,b:Int) = a+b
// a+b 가 자동으로 return 타입으로 추론 된다.
```

- void 가 코틀린에서는 Unit 이라고 한다.
- Unit 생략 가능.
- Singleton object. 아무것도 안쓰면 디폴트로 Unit 으로 적용된다.


코틀린에서는 자바 클래스 내 메서드를 함수로 표현합니다.

###### example

```kotlin
class Foo {
  fun foo(): Unit {
    
  }
  
  private fun bar() : Int {
    return 0
  }
}
```




## 변수 정의

#### val (value)

- 상수. 읽기 전용 변수
- 값 할당이 한번만 가능하다. Java Final 과 유사하다.

```kotlin
val a:Int = 1 // 즉시 할당된다.
val b = 2 // int type으로 추론이 된다.
val c: Int // 읽기 전용 변수는 초기화 시 값 할당을 하지않으면 꼭 타입을 적어줘야함

// example
val a: String = "foo" 
val b = "bar"
var c: String // 자료 할당을 하지 않으면, 타입을 꼭 붙혀야 한다. 
c = "baz"
```



#### var (variable)

- 일반 변수

```Kotlin
var d:Int = 0 // value d declare
d +=1; // value change
```



## 클래스 & 인터페이스

```kotlin
class Foo {
  val foo: String = "foo"
  
  fun foo(){
    
  }
}
interface Bar {
  fun bar()
}
```

- 자바와 크게 다르지 않다.
- 생성자 정의 방법은 다르다. 이후 명시



## 주석

 ```kotlin
// 한줄 주석
/* */ 전체 주석
전체 주석은 자바와 다르게 중첩가능하다.
/*
/* 여로케 */
*/
 ```



## 문자열 템플릿

**String Interpolation **

```kotlin
var a = 1 

val s1 = "a is $a" // java는 "a is" +a 지만 kotlin 은 $ 을 쓴다.

a = 2

val s2 = "${s1.replace("is","was)},but now is $a" // 이렇게도 쓰인다.
```



## 조건문

```kotlin
fun maxOf(a: Int, b: Int): Int {
  // 자바와 같다.
  if(a>b)
  	return a;
  else 
    return
}
// 리턴 타입 자동 추론 되서, 축소할수 있다.
fun maxOf(a: Int, b: Int) = if(a>b) a else b
```

## Nullable

- 가장 인상 깊었던 부분. 
- Nullable 이 뭐냐면. 이 type 에서 null이 발생할수있는지를 컴파일러에 알려준다.

```kotlin
fun parseInt(str: String): Int? {
// 정수가 아닌 경우 null이 리턴된다.
// 미리 컴파일러에게 ? 를 붙혀서 null이 리턴될 수 있다는 것을 알려준다.
}

fun printProduct(arg1:String,arg2:String){
  val x: Int? = parseInt(arg1)
  val y: Int? = parseInt(arg2)
  
  if(x!= null && y!= null){
    println(x*y)
  }
}
```



## Auto Casting

자동으로 타입을 변환시켜 준다.

```kotlin
fun getStringLength(obj: Any): Int? { //  Any = 어떤 타입도 올 수 있다.
  if(obj is String)
      return obj.length
  return null
}
```



## When Expression

- 스위치랑 비슷하나, 완전 다르다
- 스트링, 비교 같은것도 다 지원되어 강력하게 사용된다.

```Kotlin
fun describe(obj: Any): String = 
    when (obj) {
      1 -> "One"
      "Hellp" -> "Gretting"
      is Long -> "long"
      !is String -> "Not a String"
      else -> "Unknown"
    }
```



- 자바로 코드시 엄청 길었겠지만, 강력하고 쉽게 사용할 수 있다



## 반복문

##### for

- 자바에서 for loop 시, `for(i = 0 ; i < 10 ; i ++)`, for each 문을 지원하나,
- 코틀린에서는 for-each 문만 지원한다.

##### while

- 자바와 동일하다.

```kotlin
val items = listOf("foo","bar","baz")
var i = 0;
while(i<items.size){
  println("item:${items[i]})
  i++
}
```

-

## collections

- 컬렉션도 in 으로 loop가 가능하다
- in 키워드로 collection 에 포함되는지 체크가 가능하다.

````kotlin
val items = listOf("apple",banana,kiwi)
items
.filter {it.startsWith("a")}
.sortedBy {it}
.map {it.toUpperCase()}
.forEach{ println(it)}
````



- val items = listOf("apple",banana,kiwi)



### 자료형 / 자료형 확인 및 반환



##### ==

코틀린에서는 객체냐,값이냐를 구분할 필요없이 모두 == 연산자를 사용합니다.

값이 동일한지 여부를 확인합니다.

널 여부를 함께 확인합니다.



##### ===

동일한 객체인지 확인합니다.



##### is

코틀린에서는 자료형을 확인하기 위해 is 연산자를 사용합니다.

###### example

```kotlin
if(obj is Int)
```

##### as

자료형을 변환할 수 있는 연산자 입니다.

###### example

```kotlin
fun processNumber(number :Number){
  val foo : Int = number as Int
}
```

