# [Kotlin] Property, Field

### Property 란

클래스 내부에 선언된 변수를 프로퍼티 라고 합니다.

1. `var`  , `val` 키워드를 사용하여 `mutable` /  `read-only` 속성을 지정합니다.

2. 변수와 비슷하게 사용됩니다 (`속성 이름 :타입` )

3. Type 을 추론할 수 있다면 자동으로 추론해 줍니다.

4. getter/setter 또한 자동으로 만들어줍니다. 

5. 만약 Type  을 추론할 수 없다면 (`Int? String? 같은 경우`) getter/setter를 명시해 주어야 합니다.

   ​

##### 1. 프로퍼티 full 문법

```kotlin
var <propertyName>[: <PropertyType>] [= <property_initializer>]
    [<getter>]
    [<setter>]
```

##### `속성(var/val) 이름 : 타입 = 프로퍼티초기화 getter setter` 

생략 가능한 옵션

1. <PropertyType>
2. <property_initaillizer>
3. <getter>
4. <setter>

##### 2. 프로퍼티 사용하기

1. 선언 형식은 변수와 비슷합니다. 변수처럼 선언하면 알아서 getter/setter 선언이 자동으로 지정됩니다. 

프로퍼티를 사용하는 형식은 변수와 비슷합니다 `.` 연산자를 사용해 해당 객체의 프로퍼티를 호출합니다.

```kotlin
class Address {
    // 프로퍼티 선언 
    var name: String = ...
    var street: String = ...
    var city: String = ...
    var state: String? = ...
    var zip: String = ...
}
fun copyAddress(address: Address): Address {
    val result = Address() // there's no 'new' keyword in Kotlin
    result.name = address.name // accessors are called
    result.street = address.street
    // ...
    return result
}
```

##### 3. getter 와  setter

```kotlin
var allByDefault: Int? // error: explicit initializer required, default getter and setter implied
var initialized = 1 // has type Int, default getter and setter
```

해당 타입을 추론할 수 있다면 자동으로 선언이 되지만, 

해당 타입을 추론할 수 없는 경우 (`Int?`)에는 getter/와 setter 를 명시해 주어야 합니다. 



##### 4. val 속성 프로퍼티

val 은 읽기 전용 속성을 나타냅니다. 따라서 setter 를 허용하지 않습니다.

```kotlin
val simple: Int? // has type Int, default getter, must be initialized in constructor
val inferredType = 1 // has type Int and a default getter
```



##### 5. getter setter 재정의하기

getter 또는 setter 를 재정의 할 경우, 오른쪽 안쪽에 재정의를 해줄 수 있습니다.

```kotlin
// getter
val isEmpty: Boolean
    get() = this.size == 0

// setter
var stringRepresentation: String
    get() = this.toString()
    set(value) {
        setDataFromString(value) // parses the string and assigns values to other properties
    }
```

매개 변수 이름은 기본적으로 value 를 사용하지만, 원하는 경우 다른 이름을 선택할 수 있습니다.



만약, getter/setter 의 기본 접근자는 public 입니다. 

1. 이 것을 private 또는  다른 접근자로 변경하고 싶을 경우 ,
2. annotion 을 사용하고 

싶을 경우 오른쪽 안쪽에 재정의를 할 수 있습니다.

```kotlin
var setterVisibility: String = "abc"
    private set // the setter is private and has the default implementation

var setterWithAnnotation: Any? = null
    @Inject set // annotate the setter with Inject
```



#### Baking Field(= 지원 필드)

코틀린 클래스는 `필드`를 가질 수 없습니다. 

하지만, 프로퍼티의 값의 유효성 검사를 하는 경우, 필드가 필요합니다.

이럴 경우 코틀린은 `baking field` 를 지원해 줍니다. 

1. default implementaition 을 사용하는 경우
2. getter/setter overriding 시  `field`식별자를 사용하는 경우

코틀린은 baking field 를 자동으로 생성해 줍니다.

###### default implementaition ?

_table 이 null 일 경우에 객체 생성을 하는 코드를 짜고싶은데. 

배킹 필드에서는 그런 동작을 할 수 없습니다.

이럴 경우 배킹 프로퍼티를 사용할 수 있습니다.

```kotlin
private var _table: Map<String, Int>? = null

public val table: Map<String, Int>
    get() {
        if (_table == null) {
            _table = HashMap() // Type parameters are inferred
        }
        return _table ?: throw AssertionError("Set to null by another thread")
}
```



###### getter/setter overriding 시  `field`식별자를 사용하는 경우 예시 

```kotlin
// 필드 식별자 사용. 자동으로 backing 필드를 만들어 줍니다.
var counter = 0
    set(value) {
        if (value >= 0) field = value
}
// 필드 식별자 사용 x . backing field 를 지원해 주지 않습니다.
val isEmpty: Boolean
    get() = this.size == 0
```



##### Compile-Time Constants

프로퍼티에서는 컴파일 타임 상수를 사용할 수 있습니다.

컴파일 할 때 사용하는 함수인데, 이것을 사용하고 싶은 경우 `constant` 라는 식별자를 사용합니다.

또한 3가지 조건을 만족해야 합니다.

1. 객체의 top-level 이어야 합니다.
2. getter 로 초기화 하면 안됩니다.
3. string 또는 원시 타입으로 초기화 되어야 합니다.

```kotlin
// 1.  top-level 로 선언 되어야 합니다.
// 2.  String 또는 원시 타입으로 초기화 되어야 합니다.
// 3.  getter 로 초기화 되면 안됩니다.
const val SUBSYSTEM_DEPRECATED: String = "This subsystem is deprecated"

@Deprecated(SUBSYSTEM_DEPRECATED) fun foo() { ... }
```



##### later initialized property

프로퍼티를 non-null 로 사용하고싶은데, 바로 초기화 할 수 없는 경우 사용되는 프로퍼티입니다.

##### 버터나이프 가 이걸 사용

```kotlin
public class MyTest {
    lateinit var subject: TestSubject

    @SetUp fun setup() {
        subject = TestSubject()
    }

    @Test fun test() {
        subject.method()  // dereference directly
    }
}
```

