# [Kotlin] 기본 자료형

## 기본타입

코틀린을 한마디로 표현하면,  `"모든 것은 객체이다"`  라고 말할 수 있습니다. 자료형에서도 그게 적용되는데요. `JAVA`에서는 자료형이 기본형/참조형 으로 나뉘지만, 코틀린은 이것 조차 모두 객체로 표현이 됩니다. 

자바에서 사용되는 기본 타입이, 코틀린에서는 아래처럼 바뀌게 됩니다.

##### 자료형

| java 원시타입 | java 래퍼클래스        | kotlin 타입      |
| --------- | ----------------- | -------------- |
| byte      | java.lang.Byte    | kotlin.Byte    |
| short     | java.lang.Short   | kotlin.Short   |
| int       | java.lang.Int     | kotlin.Int     |
| long      | java.lang.Long    | kotlin.Long    |
| char      | java.lang.Char    | kotlin.Char    |
| float     | java.lang.Float   | kotlin.Float   |
| double    | java.lang.Double  | kotlin.Double  |
| boolean   | java.lang.Boolean | kotlin.Boolean |

보기에는, 래퍼클래스 처럼 사용되는것 같네요.

- 원시타이블 객체로 처리하면 비효율적이지 않는가요?
  - 작성시에는 객체로 프로그래밍을 하지만, 컴파일 단계를 거칠때 원시타입과 / 랩퍼로 구분이 됩니다. **Representation**  탭의 예시를 확인해 주세요.



#### 원시/랩퍼 이외의 자료형 처리

자바,코틀린 호환시 기본/참조 이외의 자료형도 코틀린의 자료형으로 처리가 됩니다.

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

등이 있습니다. 코틀린 자료형에 `!` 가 붙허있는 이유는, 널 허용 여부에 대한 정보를 포함하고 있지 않음을 나타냅니다. 이러한 타입을 플랫폼 타입이라고 합니다. 



### 숫자형

- 자바와 비슷하게 처리됩니다.
- char 형이 java에서는 숫자형이지만, 코틀린은 숫자형이 지원되지 않습니다.
- java 는 원시 타입이 소문자로 시작하지만,(`int`,` long`) 코틀린은 객체 타입이므로 대문자로(`Int`,`Long`) 시작합니다. 
- 8진수가 지원되지 않습니다.

숫자를 표현하는 자료형은 Number 클래스를 상속합니다 (`Byte`,`Double`,`Float`,`Int`,`Long`,`Short`) . `Number` 클래스에는, 다른 자료형으로 바꿔줄 수 있는 메서드를 제공합니다.

###### example

- `toByte()`
- `toChar()`
- `toDouble()`
- `toFloat()`
- `toInt()`
- `toLong()`
- `toShort()`

###### 

#####  Literal 표기법

리터럴 표기에 대한 예시입니다.

######  example

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

작은 타입이 큰 타입의 하위가 아니기 때문에, 항상 타입 변환을 명시적으로 해주어야 합니다.

###### example

```kotlin
val a: Int = 1 // a Boxed Int
// val b: Long = a // error!
val b: Long = a.toLong() // 이렇게 해주어야 한다.
//println(a===b) // error
```



### Array

- array class로 표현됩니다.
- get/set 연산자 ([] 연산자 오버로딩)
- size 등 여러 유용한 멤버 함수 존재

코틀린은 배열 타입이 별도로 존재하지 않고, Array 클래스로 표현이 됩니다.

##### arrayOf()

코틀린 표준 라이브러리에 포함되어 있는 함수입니다. 입력받은 인자로 구성된 배열을 저장합니다. 

##### 특별한 array type

자바의 배열 원시 타입은, 코틀린 배열의 클래스 타입 인자로 사용되지 않습니다.  왜냐하면 `Array<String>`으로 배열을 선언하기 떄문에, 제네릭이 사용되고, 제네릭 또는 널 포인터가 사용이 되면 박싱이 되어 사용되기 때문에 타입형 아이덴티티가 날라갑니다! 이게 싫다! 할때는 특별한 array type을 사용합니다.

- array에 primitive 타입을 사용하기 위해 사용되는 배열
- `IntArray` 로 타입을 지정하면 됩니다.
- Array 를 상속하는 클래스는 아니나, 같은 메소드 프로퍼티를 가집니다.

```
val x: IntArray = intArrayOf(1,2,3)
x[0] = 7
```

###### example

```kotlin
// java
String[] words = new String[] {
  "kot","test"
}

// kotlin
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

##### 배열을 인자로 받을 경우

java로 짜여진 메서드에 배열을 인자로 받거나,가변 인자를 사용하는 경우 코틀린에서는 ` *` 을 사용해야 코틀린의 배열을 인자로 전달할 수 있습니다. 먼소린지 모르겠으니 예시를 볼게요.

###### example

```kotlin
// java
public void foo(int[] arr){}
public void bar(String...args){}

// kotlin
// 코틀린은,메서드 호출 시 * 연산자를 사용하여 메서드의 인자로 배열을 전달합니다.
// foo() 호출출
val intArr :IntArray = intArrayOf(1,2,3,4,5)
foo(*intArr)
val stringArr : Array<String> = arrayOf("hi","test")
bar(*stringArr)


```

코틀린으로 작성된 함수에서는, **가변인자에 배열을 전달하는 경우**만 `*`을 사용합니다.

###### example

```kotlin
fun foo(arr: Array<Int>){...}
fun bar(vararg args: String){..}

// call foo()
val intArr :IntArray = intArrayOf(1,2,3,4,5)
foo(intArr)
// 가변 인자 호출시 
val stringArr : Array<String> = arrayOf("hi","test")
bar(*stringArr)
```





### String 

- 자바와 같다.

##### 문자열 리터럴

- 기존 방식 : escaped string ("kotlin")

- raw String ("""kotlin""")

  escape 처리 필요 없이, 개행이나 어떤 문자든 포함이 가능합니다.

자바에서는 문자열의 문자에 접근하기위해 `charAt()` 함수를 쓰지만, 코틀린에서는 `get()`함수를 사용합니다.

###### example

```kotlin
val a = "hello world \n"

val new = 
"""
"' 이건 코틀린의
raw String 입니다. '"
"""
```

