# [Kotlin] 기본 자료형



## 기본타입

- 코틀린은 모든 것은 객체이다.

- 자바는 primitive, reference 타입이 분리되는데, 코틀린은 객체로 처리된다

- 자바의 char 문자타입에 숫자,문자 둘다 들어가지만, 코틀린에서는 문자만 처리된다.

  ​

   

### 숫자형

- 자바와 비슷하게 처리된다.
- char 형이 java에서는 숫자형이지만, 코틀린은 숫자형이 아니다.
- java 는 원시 타입이 소문자로 시작하지만,(`int`,` long`) 코틀린은 객체 타입이므로 대문자로(`Int`,`Long`) 시작한다.
- 8진수가 지원되지 않는다.

###### example

#####  Literal

- 10진수 : 123 (`Int`,`Short`)
- Long : 123L
- Double: 123.5, 123.5e10
- Float: 123.5f
- 2진수: 0b0001011
- 16진수: 0X0F
- 8진수 미지원

##### underScores in numberlic literal

언더바를 사용하여 긴 숫자를 헷갈리게 사용하는걸 막아줄 수 있다. 

컴파일 에러안남!

###### example

```kotlin
val a = 1_000_000_000
```

##### Characters

###### example

```kotlin
func check(c :Char){
  if( c ==1){} // error
}
fun check(c:Char){
  if( c =='1') // okay
}
// 유니코드. c.toInt() 는 적용 된다.
```

### 

### Representation

- Java 로 변환할때, 어떤 경우는 primitive 타입으로 저장이 되지만, Nullable을 사용하거나 generic 의 경우는 박싱 되어 저장한다.
- 박싱 되어 저장이 되면 identity 를 유지 하지 않는다.

##### example

```kotlin
/* case 1 */
fun main(args: Array<String>) {

    var a:Int = 10000
    var b:Int = 10000

    println("a === b: ${a===b}") // equal 3개는 객체 타입이 같은지 체크해준다.
    println("a == b: ${a==b}")
}
// decomplie
public final class HelloworldKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      int a = true; // int 로 변환된다
      int b = true; // int 로 변환된다
      String var3 = "a === b: " + true;
      System.out.println(var3);
      var3 = "a == b: " + true;
      System.out.println(var3);
   }
}
// true, true
/* case 2 : nullable 사용 경우 */
fun main(args: Array<String>) {

    var a:Int? = 10000
    var b:Int = 10000

    println("a === b: ${a===b}") // equal 3개는 객체 타입이 같은지 체크해준다.
    println("a == b: ${a==b}")
}
// decompile
public final class HelloworldKt {
   public static final void main(@NotNull String[] args) {
      Intrinsics.checkParameterIsNotNull(args, "args");
      Integer a = 10000; // boxed
      int b = 10000;
      String var3 = "a === b: " + (a == Integer.valueOf(b));
      System.out.println(var3);
      var3 = "a == b: " + (a == b);
      System.out.println(var3);
   }
}
// false,true
```

### Explicit Conversions

작은 타입이 큰 타입의 하위가 아니기 때문에, 항상 타입 변환을 명시적으로 해주어야 한다.

###### example

```kotlin
val a: Int = 1 // a Boxed Int
// val b: Long = a // error!
val b: Long = a.toLong() // 이렇게 해주어야 한다.
//println(a===b) // error
```



### Array

- array class로 표현된다
- get/set 연산자 ([] 연산자 오버로딩)
- size 등 여러 유용한 멤버 함수 존재

###### example

```kotlin
var array: Array<String> = arrayOf("kot","test")
println(array.get(0))
println(array[0])
println(array.size)

// array 생성

// 1. factory func 사용
val b = Array(5,{i -> {i.toString()})
                 
// 2. library func 사용
val c = arrayOf("0","1","2","3","4")
```



##### 특별한 array type

- array에 primitive 타입을 사용하기 위해 사용되는 배열
- `IntArray` 로 타입을 지정하면 된다.
- Array 를 상속하는 클래스는 아니나, 같은 메소드 프로퍼티를 가진다.

```
val x: IntArray = intArrayOf(1,2,3)
x[0] = 7
```



### String 

- 자바와 같다.

##### 문자열 리터럴

- 기존 방식 : escaped string ("kotlin")

- raw String ("""kotlin""")

  escape 처리 필요 없이, 개행이나 어떤 문자든 포함이 가능하다

###### example

```kotlin
val a = "hello world \n"

val new = 
"""
"' 이건 코틀린의
raw String 입니다. '"
"""
```

