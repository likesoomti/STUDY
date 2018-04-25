# chap02 기본 자료형.md

```
2.1 기본 자료형
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

---

커니의 코틀린 정리 

---



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

