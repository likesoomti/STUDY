# chap02 기본 자료형.md

```
# 관련 kotlin docs 정리 
2.1 기본 자료형
2.2 컬렉션
```

#### Kotlin DOCS

코틀린은 모든것이 객체입니다. 

어떤 변수에서든지 멤버함수와 프로퍼티를 호출할 수 있습니다.

자료형은

- 숫자
- 문자 
- 불린

으로 나타냅니다. 각각의 자료현은 특수한 내부 표현을 가질 수 있습니다.

예를들어, 숫자, 문자, 불린, 배열 등은 런타임시 원시 값으로 나타낼 수 있지만 일반 클래스 처럼 보입니다.

### 숫자

#### Kotlin DOCS

Kotlin은 Java와 비슷하게 숫자를 처리하지만 정확하게 동일하지는 않습니다. 

##### 차이점

- 숫자에 대한 암시적인 변환 변환은 없습니다.
- 경우에 따라 리터럴이 약간 다릅니다.

Kotlin은 숫자를 나타내는 다음과 같은 내장 유형을 제공합니다 (Java에 가깝습니다).

| 유형     | 비트 폭 |
| ------ | ---- |
| Double | 64   |
| Float  | 32   |
| Long   | 64   |
| Int    | 32   |
| Short  | 16   |
| Byte   | 8    |

`Char` 는 Kotlin의 숫자가 아닙니다.

#####  리터럴 상수

- 십진법 : 

  ```
  123
  ```

  - `Long` 타입은 뒤에 `L`을 붙혀줍니다.  `L`.`123L`

- 16 진수 : `0x0F`

- 2 진수: `0b00001011`

참고 : 8 진수 리터럴은 지원되지 않습니다.

Kotlin은 부동 소수점 숫자에 대한 기존 표기법도 지원합니다.

- `Double` 기본적으로 : `123.5`,`123.5e10`
- `Float` 자료형은  `f`또는에 의해 태그됩니다 `F`:`123.5f`



##### 숫자 리터럴의 밑줄 (1.1 이후)

밑줄을 사용하여 숫자 상수를 더 읽기 쉽게 만들 수 있습니다. 이렇게 해도 알아서 숫자 형으로 저장됩니다.

```kotlin
val oneMillion = 1_000_000
val creditCardNumber = 1234_5678_9012_3456L
val socialSecurityNumber = 999_99_9999L
val hexBytes = 0xFF_EC_DE_5E
val bytes = 0b11010010_01101001_10010100_10010010
```



##### 표현

Java 플랫폼에서 숫자는 기본 타입으로 저장되지만, 

nullable 일 경우 (예 `Int?`:) , generic일 경우 는 박싱되어 래퍼 클래스로 저장됩니다.

Number 클래스가 박싱되면, 이 전의 객체는 다른 객체가 됩니다.

```kotlin
val a: Int = 10000
print(a === a) // Prints 'true' // === 는 같은 객체인지 비교
val boxedA: Int? = a
val anotherBoxedA: Int? = a
print(boxedA === anotherBoxedA) // !!!Prints 'false'!!!
// decompile java 래핑되면서 다른 객체가 된다. 
Integer boxedA = Integer.valueOf(a);
Integer anotherBoxedA = Integer.valueOf(a);
```

같은 값을 유지합니다.

```kotlin
val a: Int = 10000
print(a == a) // Prints 'true'
val boxedA: Int? = a
val anotherBoxedA: Int? = a
print(boxedA == anotherBoxedA) // Prints 'true'

```



##### 명시적 전환

코틀린에서는 꼭 명시적으로 자료형을 변환합니다.

```kotlin
// Hypothetical code, does not actually compile:
val a: Int? = 1 // A boxed Int (java.lang.Integer)
val b: Long? = a // implicit conversion yields a boxed Long (java.lang.Long)
print(a == b) // Surprise! This prints "false" as Long's equals() check for other part to be Long as well

```

결과적으로 더 작은 유형은 내재적으로 더 큰 유형으로 변환되지 않습니다. 이것은 우리가 유형의 값을 할당 할 수 있음을 의미 `Byte`에 `Int`명시 적 변환없이 변수를

```kotlin
val b: Byte = 1 // OK, literals are checked statically
val i: Int = b // ERROR
```

숫자를 넓히기 위해 명시 적 변환을 사용할 수 있습니다.

```kotlin
val i: Int = b.toInt() // OK: explicitly widened
```

모든 숫자 유형은 다음과 같은 전환을 지원합니다.

- `toByte(): Byte`
- `toShort(): Short`
- `toInt(): Int`
- `toLong(): Long`
- `toFloat(): Float`
- `toDouble(): Double`
- `toChar(): Char`

코틀린은 자료형을 유추해주기 때문에, 다른 자료형 사용 시 적절한 연산으로 자료형이 오버로드 됩니다.

```kotlin
val l = 1L + 3 // Long + Int => Long
```



##### 연산

Kotlin은 `Number` 자료형에 대한 기본적인 산술 연산의 을 지원합니다. 

비트 연산과 관련하여 특수 문자는 없지만 중위 형태로 호출 할 수있는 명명 된 함수입니다 (예 :

```kotlin
val x = (1 shl 2) and 0x000FF000
```

여기 (에 해당하는 비트 연산의 전체 목록 `Int`및 `Long`전용) :

- `shl(bits)`- 부호있는 시프트 레프트 (Java 's `<<`)

- `shr(bits)`- 부호있는 시프트 권한 (Java 's `>>`)

- `ushr(bits)`- 부호없는 시프트 권한 (Java 's `>>>`)

- `and(bits)` - 비트와

- `or(bits)` - 비트 또는

- `xor(bits)` - 비트 xor

- `inv()` - 비트 반전

  ​

##### 부동 소수점 숫자 비교

이 절에서 설명하는 부동 소수점 숫자에 대한 연산은 다음과 같습니다.

- 평등 검사 : `a == b`및`a != b`
- 비교 연산자 : `a < b`, `a > b`, `a <= b`,`a >= b`
- 범위 인스턴스와 범위를 확인 : `a..b`, `x in a..b`,`x !in a..b`

때 피연산자 `a`와 `b`일하기 위해 정적으로 알려져있다 `Float`거나 `Double`또는 널 (NULL) 대응 (유형 선언 또는 유추하거나의 결과입니다 [스마트 캐스트](https://kotlinlang.org/docs/reference/typecasts.html#smart-casts) 가 부동의 IEEE 754 표준에 따라 형성), 숫자의 운영 및 범위 포인트 산술.

그러나, 일반적인 사용 사례를 지원하고 피연산자가 될 때, 전체 순서를 제공 **하지** 정적으로 (예를 들어, 부동 소수점 숫자로 입력 `Any`, `Comparable<...>`, 유형 매개 변수), 작업은 사용 `equals`과 `compareTo`에 대한 구현 `Float`및 `Double`표준에 동의 있도록 :

- `NaN` 똑같은 것으로 간주된다
- `NaN` 다음을 포함하는 다른 요소보다 더 큰 것으로 간주됩니다. `POSITIVE_INFINITY`
- `-0.0` 보다 적게 고려된다 `0.0`

### 배열 

코틀린은 Array 클래스를 통해 표현됩니다. 

 `get`및 `set`

 `[]` 

`size` 등 다른 유용한 기능이 존재합니다.

##### ArrayClass

```kotlin
class Array<T> private constructor() {
    val size: Int
    operator fun get(index: Int): T
    operator fun set(index: Int, value: T): Unit

    operator fun iterator(): Iterator<T>
    // ...
}
```

##### 배열 만들기

1. 라이브러리 함수를 사용 `arrayOf()`하여 `arrayOf(1, 2, 3)`배열 [1, 2, 3] 을 만들 수 있습니다.
2. `arrayOfNulls()`라이브러리 함수를 사용하여 null 요소로 채워진 주어진 크기의 배열을 만들 수 있습니다.
3. 다른 옵션은 `Array`배열 크기를 취하는 생성자를 사용, 인덱스를 사용하여 각 배열 요소의 초기 값을 반환 할 수있는 함수를 사용하는 것입니다.

```kotlin
// Creates an Array<String> with values ["0", "1", "4", "9", "16"]
val asc = Array(5, { i -> (i * i).toString() })
```

##### `[]`

 `[]`작업은 멤버 함수 `get()`및 함수 호출을 나타냅니다 `set()`.

##### 참고

 Java와 달리 Kotlin의 배열은 변하지 않습니다. 

`Array<String>` 을 할당하는 것을 허용하지 않습니다.

`Array<Any>`, 이는 가능한 런타임 오류를 방지합니다 

코틀린은 기본 형의 배열을 표현하는 클래스가 존재합니다.

-  `ByteArray`, 


- `ShortArray`,
- `IntArray`

이러한 클래스는 `Array`클래스와 상속 관계가 없지만 동일한 메서드 및 속성 집합을가집니다. 

각각은 또한 해당 공장 기능을 가지고 있습니다 :

```kotlin
val x: IntArray = intArrayOf(1, 2, 3)
x[0] = x[1] + x[2]
```

---

#  커니의 코틀린 정리 

## 2.1 기본 자료형

자바에서는 

- 원시타입
- 래퍼 클래스 

로 나뉘지만, 코틀린은 원시타입/래퍼 타입을 구분해서 사용하지 않습니다.

비효율적일 수 있지만, 컴파일 단계를 거치면서 가장 효율적인 타입으로 변환됩니다.

#####  컴파일 단계를 거치면서 가장 효율적인 타입으로 변환됩니다.

```
- 값이나 변수 타입으로 사용되는 경우 : 원시 타입으로 변환
- 컬렉션이나 타입 인자로 사용되는 경우 : 래퍼로 변환

다음과 같은 코틀린 코드가 있을 때, 값 foo 와 bar 에서 모두
Int 자료형을 사용할 수 있습니다.

-- kotlin --
val foo: Int
val bar: List<Int>
------------

compile 

---- java ----
int foo
List<Integer> bar
--------------
```

원시 / 래퍼 자료형 외에도, 일부 자바 자료형은 다음과 같이 코틀린의 자료형으로 처리됩니다.

| java                     | kotlin                 |
| :----------------------- | ---------------------- |
| `java.lang.Annotation`   | `kotlin.Annotation!`   |
| `java.lang.CharSequence` | `kotlin.CharSequence!` |
| `java.lang.Cloneable`    | `kotlin.Cloneable!`    |
| `java.lang.Comparable`   | `kotlin.Comparable!`   |
| `java.lang.Deprecated`   | `kotlin.Deprecated!`   |
| `java.lang.Enum`         | `kotlin.Enum!`         |
| `java.lang.Number`       | `kotlin.Number!`       |
| `java.lang.Object`       | `kotlin.Any!`          |
| `java.lang.String`       | `kotlin.String!`       |

등이 있습니다. 코틀린 자료형에 `!` 가 붙허있는 이유는, 널 허용 여부에 대한 정보를 포함하고 있지 않음을 나타냅니다. 

이러한 타입을 **플랫폼 타입**이라고 합니다. (2.8에서!)



### 숫자

숫자를 표현하는 모든 자료형은 다음과 같이 Number 클래스를 상속합니다.

##### Number 를 상속하는 자료형

- Byte
- Double
- Float
- Int
- Long
- Short

자료형을 다른 자료형으로 바꿔주는 함수가 존재합니다.

- `toByte(): Byte`
- `toShort(): Short`
- `toInt(): Int`
- `toLong(): Long`
- `toFloat(): Float`
- `toDouble(): Double`
- `toChar(): Char`



#####  리터럴 상수

- 십진법 : 

  ```
  123
  ```

  - `Long` 타입은 뒤에 `L`을 붙혀줍니다.  `L`.`123L`

- 16 진수 : `0x0F`

- 2 진수: `0b00001011`

참고 : 8 진수 리터럴은 지원되지 않습니다.

Kotlin은 부동 소수점 숫자에 대한 기존 표기법도 지원합니다.

- `Double` 기본적으로 : `123.5`,`123.5e10`
- `Float` 자료형은  `f`또는에 의해 태그됩니다 `F`:`123.5f

###### example

```kotlin
val decValue: Int = 100;

val hexValue: Int = 0x100;

val binaryValue: Int = 0b100

val longValue: Long = 100L

val doubleValue: Double = 100.1

val floatValue: Float = 100.0f
```



##### 숫자 연산에 대한 연산자

사칙 연산은 java와 동일합니다.

하지만, 비트 연산자는 자바에 비해 직관적입니다.

여기 (에 해당하는 비트 연산의 전체 목록 `Int`및 `Long`전용) :

- `and` - 비트 연산 AND
- `or` - 비트 연산 OR
- `xor(bits)` - 비트 연산 XOR
- `inv()` - 비트 연산 NOT
- `shl`- 부호있는 시프트 레프트 (Java 's `<<`)
- `shr`- 부호있는 시프트 권한 (Java 's `>>`)
- `ushr`- 부호없는 시프트 권한 (Java 's `>>>`)

###### example

```kotlin
//java
int foo = (2|4) <<1;

//kotlin
val foo: Int = (2 or 4) shl 1
```



### 문자

하나의 문자를 표현하는 자료형입니다.

JAVA 에서는 아스키 코드를 문자 자료형에 숫자 형태로 대입할 수 있지만, 코틀린에서는 문자만 대입할 수 있습니다.

```kotlin
val c : Char = 65 // error
val ch: Char = 'A' // okay
```

문자 자료형에 아스키 코드를 넣는게 불가피한 경우, `toChar()`를 사용하면 됩니다.

```kotlin
// 문자 'A'의 아스키 코드 값
val code: Int = 65
// code 에 해당하는 문자를 할당
val ch: Char = code.toChar()
```



### 논리

참과 거짓을 표현하기 위해 사용하는 자료형입니다.

```kotlin
val foo : Boolean = true
val bar : Boolean = false
```



사용 가능한 연산자 

- `||` - 논리 연산 OR
- `&&` - 논리 연산 AND
- `!` - 논리 연산 NOT



### 문자열 

```kotlin
val foo: String = "Lorem ipsum"
```



##### `get()` or` []`

 특정 위치의 문자에 접근하기 위해 사용되는 메서드 입니다. (Java 's `CharAt()`)

```kotlin
val foo: String = "Lorem ipsum"
val ch1 : Char = foo.get(4) // 'm'
val ch2 : Char = foo[6] // 'i'
```



##### 규격화된 문자열

규격화된 문자열을 사용하고 싶을 때는 `String.format()` 함수를 사용합니다.

```kotlin
val length : Int = 3000

val lengthText : String = String.format("Length: %d meters",length)
```

##### 문자열 템플릿

문자열 템플릿 기능을 사용할 수도 있습니다. 

```kotlin
val length : Int = 3000

val lengthText: String = "Length $length meters"
```

문자열에 포함할 인자는 `$` 를 붙혀 사용합니다



문자열 템플릿을 사용하게되면, 변수 대신 표현식을 사용해도 됩니다.

달러 표시를 사용할 경우, 두개의 템플릿을 사용해야 합니다.

```kotlin
val text: String = "Lorem impsum"

//"TextLength: 4"
val lengthText : String = "TextLength: ${text.length}"

//달러 표시를 사용할 경우
val lengthText : String = "TextLength: Price ${'$'}$price"
```



##### 차이? 뭐가 더 좋은지

```
스트링 포맷에 넣을 변수들이 많아지면 가독성에 대한 불편함이 생긴다. 
String.format("%d + %d is %d", a, b, a + b)
이럴때는 스트링 템플릿을 사용하는게 편함
```



### 배열

코틀린은 타입 인자를 갖는 Array 클래스로 표기됩니다.

```kotlin
val words :Array<String> = arrayOf("a","b","c","d")
```

##### arrayOf

입력받은 인자로 구성된 배열을 생성합니다.

자바의 기본형 타입 배열 코틀린 타입 인자로 사용할 수 없습니다.

따라서, 자바 타입의 기본형 인자를 갖는 걸 사용하기 위해 특수한 클래스를 제공합니다.

| java     | kotlin               |
| -------- | -------------------- |
| byte[]   | `kotlin.ByteArray`   |
| double[] | `kotlin.DoubleArray` |
| float[]  | `kotlin.FloatArray`  |
| int[]    | `kotlin.IntArray`    |
| long[]   | `kotlin.LongArray`   |
| short[]  | `kotlin.ShortArray`  |



##### int type 배열 선언

```kotlin
val intArr : IntArray = intArrayOf(1,2,3,4,5)
```

##### wrapper type 배열 선언하기

```kotlin
val intArr : Array<Int> = arrayOf(1,2,3,4,5)
```

##### 가변 인자 호출

스프레드 연산자를 사용하여 메서드의 인자로 배열을 전달합니다.

```kotlin
// foo method call
val intArr : IntArray = intArrayOf(1,2,3,4,5)
foo(*intArr)
```

```java

```

자바의 경우에는 타입뒤에 `...`을 사용해서 가변인자로 만들 수 있지만,

코틀린은 `vararg` 키워드를 사용해서 만들수 있습니다.

```kotlin
//java
public static <T> void varargsParameters(T... ts){
  for(T t: ts){
    System.out.println(t);
  }
//kotlin
fun <T> varargsParametersFun(vararg ts: T) {
    for (t in ts){
        println(t)
    }
}
```



## 2.2 컬렉션

컬렉션은 자바의 컬렉션을 그대로 사용합니다.

코틀린에서는 컬렉션 내 자료 수정 가능 여부에 따라 컬렉션 종류를 구분합니다.

##### mutable

자료를 수정할 수 있는 타입입니다. 메모리에 대한 구분이있다. 

##### immutable

자료를 수정할 수 없는 타입입니다.

`Set`,`Map` 도 이와 동일한 규칙이 지정됩니다.

```
jvm 에서 java 로 decompile 하게 되는 경우,
java 는 단일 인터페이스로 구성되어있기 때문에
모든 함수가 자료를 모두 수정할 수 있게 됩니다.
```

코틀린은 컬렉션을 쉽게 생성하는 함수를 제공합니다. 

| 수정여부 |      함수명      | 반환타입(실제타입)                               |
| :--: | :-----------: | ---------------------------------------- |
|  x   |   listOf()    | kotlin.collections.List                  |
|  O   | arrayListOf() | kotlin.collections.ArrayList(java.util.ArrayList) |
|  x   |    setOf()    | kotlin.collections.Set                   |
|  O   |  hashSetOf()  | kotlin.collections.HashSet(java.util.HashSet) |
|  O   | linkedSetOf() | kotlin.collections.LinkedHashSet(java.util.LinkedHashSet) |
|  O   | sortedSetOf() | kotlin.collections.TreeSet(java.util.TreeSet) |

##### listOf() : 데이터를 일렬로 할당한다.

##### arrayListOf() : listOf 와 동일하나, 수정 가능하게 할당한다.



배열에 접근하는 방식과 동일하게 컬렉션 내 항목에 접근할 수 있습니다.

```kotlin
val immutableList: List<String> = listOf("a","b","c","d")
val firstItem: String = immutableList[0]

immutableList[0] = "lo" // error. immutablelist

val mutableList: MutableList<String> = listOf("a","b","c","d")
mutableList[0] = "lo" // okay 
```



##### 맵을 생성하는 함수

맵을 생성하는 함수는 Key,Value 값을 인자로 받기 위해 Pair 클래스를 사용합니다.

```kotlin
val mutableMap: HashMap<String,Int> = hashMapOf(Pair("A",65),Pair("B",66))
```

to 함수를 사용하여 Pair 형태의 값을 더욱 편리하게 생성할 수 있습니다.

```kotlin
val map :Map<String,Int> = mapOf("A" to 65, "B" to 66)
```



## 2.3 클래스 인터페이스

##### 클래스 선언

코틀린의 deault 접근 지정자는`public` 입니다.

```kotlin
class Baz {
  ...
}

// 본체 없이 이름으로 선언 가능합니다.
class Foo
```

##### 인터페이스 선언

 ```kotlin
interface Foo{
  
}
// 본체없이 선언 가능합니다.
interface Foo
 ```



##### 클래스 선언

java 에서는 `new` 키워드를 사용하지만, 코틀린에서는 사용하지 않습니다.

```kotlin
val foo : Foo()

// 인자 1개 받는 생성자
val goo : Bar(1)
```



##### 추상 클래스 선언 및 인스턴스 생성

```kotlin
abstract class Foo{
  abstract fun bar()
}
val foo = object: Foo(){
  override fun bar(){
    //함수 구현 
  }
}
```

##### 인터페이스 선언 및 인스턴스 생성

 ```kotlin
interface Bar{
  fun baz()
}
// 인터페이스 인스턴스 생성
// object: [인터페이스이름] 형태 로 선언 
val bar = object : Bar{
  override fun baz(){
    
  }
}
 ```

추상클래스는 인스턴스 생성시, 생성자를 사용하지만

생성자가 없는 인스턴스는 인스턴스 이름만 사용합니다. 



### 프로퍼티

클래스는 필드와 메서드를 제공하는데,

코틀린에서는 자료를 저장할 수 있는 필드를 프로퍼티라고 합니다.

자바와 달리 프로퍼티는 필드의 Getter/Setter 를 함께 제공합니다.

```kotlin
class Person {
  var name: String? = null
  var address:String? = null
}
```

코틀린의 프로퍼티는 val 또는 var 중 하나로 선언하는데, val 의 경우읽기 전용 이므로 getter 만 존재하게 됩니다.



- 코틀린의 프로퍼티는 초깃값을 명시적으로 지정해야합니다.
- 하지만, 생성자에서 프로퍼티 값을 지정하는 경우 선언시 값을 할당하지 않아도 됩니다. 
- 프로퍼티 값을 선언 시점에서 초기화 할 수 없는 경우, `lateinit` 키워드를 사용하여 나중에 할당함을 명시합니다.
- `lateinit` 키워드는 `var` 프로퍼티에만 사용 가능합니다.



```kotlin
class Person{
  val name : String? = null 
  
  // 선언 시점에 값을 할당하지 않아도, 컴파일 에러가 발생하지 않습니다.
  lateinit var address : String? 
  
  // 타입 추론이 가능한 경우, 타입 선언 생략 가능합니다.
  val test = "no name" //String 으로 추론
}
```



##### Uninitialized PropertyAccessException

`lateinit` 키워드를 사용한 프로퍼티를 초기화 없이 사용하는 경우 



### 접근 제한자

코틀린에서는 접근제한자를 사용하여 클래스,함수,프로퍼티의 가시성을 제어합니다.

```kotlin
class Foo {
  // default 는 public
  val a = 1
  
  protected val b = 2
  
  private val c = 3
  
  // internal 을 대신 사용
  internal val d = 4
}
```



##### public

디폴트 제한자입니다. 

java 에서는 default 단위가 패키지 단위이지만, 이는 클래스가 포함한 모듈이 아닐지라도 패키지를 동일하게 맞추면 접근할 수 있다는 단점이 있습니다.

##### internal

동일한 모듈 내에 있는 클래스들로의 접근을 제한합니다.

외부 모듈에서는 이 접근 체한자로 선언된 요소에 접근할 수 없습니다.

모듈의 범위는

- intellJ IDEA 모듈
- MAVEN/ Gradle 프로젝트
- 하나의 Ant 태스크 내에서 함께 컴파일 되는 파일들 



### 생성자

코틀린에서는 `init` 블록을 사용하여 기본 생성자를 만듭니다.

##### 생성자 선언

```kotlin
class Foo {
  init {
    
  }
}
```

##### 생성자에 인자가 필요한 경우

생성자를 받은 값을 init 블록에서 사용할 수 있습니다.

```kotlin
class Foo(a: Int){
  init {
    Log.d("Foo","Number: $a")
  }
}
```



코틀린은 생성자의 인자를 통해, 클래스 내부 프로퍼티에 값을 바로 할당합니다.

따라서 java this.a = a 와 같이 할당을 하지 않아도 됩닌다.

```kotlin
class Foo(val a: Int, val b: Char)
```



주 생성자 외에 다른 형태의 생성자가 필요한경우 `constructor` 키워드를 사용합니다.

```kotlin
class Foo(val a:Int,val b: Char){

  constructor(a: Int) : this(a,0)
  constructor() :this(0,0)
}
```



코틀린에서는 추가 생성자를 정의하는 경우 주 생성자를 반드시 호출해야 합니다. 

생성자의 가시성을 변경하기 위해서는, constructor 키워드를 추가하고 앞에 접근 지정자를 붙혀줍니다.

```kotlin
class Foo internal constructor(val a:Int , val b : String){
  private constructor(a: Int): this(a,0)
  
  constructor():this(0,0)
}
```



##### 함수

코틀린에서는 메서드를 함수 라고 표현합니다.

특별한 값을 반환하지 않는 경우,  Unit  타입을 반환하며, 반환 타입을 생략할 수 있습니다.

```kotlin
class Foo {
  fun foo():Unit{
    
  }
  private fun bar(): Int {
    return 0;
  }
}
```



### 상속 및 인터페이스 구현

코틀린에서는 `:` 뒤에 상속한 클래스나,인터페이스를 표기합니다.

```kotlin
class MainActivity :AppCompatActivity(),
					View.OnclickListener{
  ...
}
```



클래스 상속시에는, 무조건 부모 생성자를 호출합니다.

위의 예시에서도 `AppCompatActivity()` 로 부모 생성자를 호출하는 것을 확인할 수 있습니다.

부모 생성자가 여러 형태일경우, 별도의 생성자 선언으로 부모 클래스의 생성자를 호출하도록 구현할 수 있습니다.

부모 클래스의 생성자는 super 키워드를 사용하여 호출합니다.

this 키워드를 사용하여 자신의 생성자를 호출할 수 있습니다.

 ```kotlin
class MyView : View {
  constructor(context: Context) :this(context,null)
  
  constructor(context: Context) : super(context){
    
  }
  constructor(context: Context, attrs: AttributeSet?) : super(context,attrs){
    
  }
}
 ```



##### 재정의

코틀린에서는 상속받거나, 구현한 함수 앞에 무조건 `override` 키워드를 붙이도록 강제합니다.

```kotlin
class MyActivity : AppCompatActivity(),View.onClickListener {
  // appcompat
  override fun onCreate(savedInstanceState: Bundle?){
    super.onCreate(savedInstanceState)
  }
  // view.onclicklistener interface
  override fun OnClick(v: View){
    
  }
}
```



##### 더이상 상속 받지 못하게 할때

코틀린에서는 open 키워드를 붙힌 함수,클래스가 아니면 클래스를 상속하거나 재정의 할 수 없습니다.

```kotlin
open class OpenClass {
  open val openProperty = "foo" // 상속 가능
  val finalProperty = "goo" // 상속 불가
  
}
```



### this

클래스 자신을 지칭할 때 사용하는 키워드입니다.

```kotlin
class MyActivity : AppCompatActivity(), View.OnClickListener {
  lateinit var btnhello : Button
  
  override fun onCreate(~){
    btnhello = fid(R.id.button)
    btnhello.setOnClickListener(this)
  }
}
```

this 는 가장 가까운 범위의 클래스를 의미합니다.

클래스 내에 다른 클래스나, 인터페이스의 인스턴스를 생성하는 경우 위치에 따라 this 의 의미가 달라질 수 있습니다.

이러한 모호성을 없애기 위해 코틀린에서는 `this@{클래스 이름}`형태로 표기합니다.



### 정적 필드 및 메서드 

코틀리은 클래스 내에 정적 필드/메서드를 선언할 수 없습니다.

코틀린은 패키지 단위로 정적 필드/메서드를 선언할 수 있습니다.

```kotlin
package foo.bar

const val FOO = 123

fun foo() {}

class Foo {
  // 인스턴스를 만들어야 사용할 수 있습니다. 
  fun bar() {}
}
```

이렇게 패키지 단위로 선언된 값/메서드는 패키지에 종속됩니다.

 `import` 시에도, `{패키지이름}.{값 or 메서드}`  를 사용합니다.

```kotlin
import foo.bar.foo
import foo.bar.FOO

class Bar {
  fun bar(){
    //foo.bar 의 FOO 변수 값을 참조
    val foo = FOO
    
    // foo.bar 패키지의 foo() 호출 
     foo()
  }
}
```



패키지 단위 함수는 클래스에 소속되어있지않아, 클래스가 private 일 경우에는 그 안에 private 멤버에 접근할 수 없습니다.

이에 대해 `companion object`를 사용해서 클래스 내 모든 멤버에 접근할 수 있도록 해줍니다.

java로 디컴파일 시 static 으로 변환됩니다.

```kotlin
class User private constructor(val name: String, val regester: Long){
    companion object {
        // 동반 객체 안의 함수를 통해
        // private 로 선언한 생성자에 접근할 수 있습니다.
        fun create(name:String):User {
            return User(name,10L)
        }
    }
}

fun main(args: Array<String>) {
    var test: User = User.create("test")
}
```

동반 객체로 선언한 함수는 자바의 정적 메서드와 사용이 동일합니다.



### 싱글톤

