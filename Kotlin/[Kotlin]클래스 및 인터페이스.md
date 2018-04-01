# [kotlin] 클래스 및 인터페이스

### 클래스  

- 클래스는 `class 클래스이름 헤더 바디` 로 선언합니다. 
- 헤더와 바디는 옵션이므로, 생략 가능합니다
- 단, 기본생성자에 어노테이션이나, 접근 지정자가 있는 경우 constructor 키워드를 써주어야 합니다. 

아래의 것들이 포함됩니다.

1. constrouctors and initializer blocks
2. functions
3. properties
4. nestled and inner class
5. object declations

###### example

```kotlin
// 접근지정자는 default 가 public 
class goo (data: Int) {
  
}
// 이름 바디 
class Baz {
  ...
}
// 클래스 본체 없이 선언 가능하다.
class Foo

// 클래스 인스턴스 선언
// 자바와 다르게 new 를 사용하지 않는다.
val foo: Foo = Foo()

// 생성자 있는 인스턴스
val bar: Bar = Bar(1)

// 추상 클래스 선언
abstract class Foo{
  abstract fun bar()
}
// 추상 클래스 인스턴스 생성
val foo = object: Foo(){
  override fun bar(){
    // 함수 구현 
  }
}
```

### 인터페이스 선언

###### example

```kotlin
// 인터페이스 선언
interface Foo {
  fun baz()
}
// 본체없이 선언 가능
interface Foo

// 인터페이스 todtjd
val bar = object : Bar {
  override fun baz(){
    // func 구현 
  }
}
```

### 프로퍼티

자바에서는 클래스 내 자료 접근을 위해 get/setter 를 생성합니다. 

코틀린은 이런 점을 개선하기 위해 property 를 사용합니다. get/set 메서드를 함께 제공합니다. 

getter/setter 중 getter만 원하는 항목은 `val`로 표현합니다.

###### example

```kotlin
class Person {
  val name : String? = null // getter 만 가능 
  // val 프로퍼티는 항상 선언과 함께 값을 할당해야 합니다.
  var address : String? = null // 프로퍼티에 널 값이 들어갈 수 있다. getter/setter 가능 
  
  lateinit var phone : String? // 나중에 할당하겠다고 표시. 4444444
}
```

코틀린에서 클래스의 멤버로 사용하는 프로퍼티는 초깃값을 명시적으로 지정해야합니다.

생성자에 프로퍼티를 할당한다면 선언시 값을 할당하지 않아도 됩니다.

프로퍼티 선언 시점에서 값을 할당할 수 없는 경우에는 `lateinit` 키워드를 사용하여 나중에 할당 될 것임을 명시해 줍니다.



### 접근 제한자

디폴트는 public 입니다.

자바와 동일하게 private,protected,public 으로 구성이 됩니다. 

자바에서는 default  부분이 동일한 패키지 내에 있는 클래스에서만 접근이 가능했다면, 코틀린에서는 동일한 모듈 내에 있는 클래스들로의 접근을 제한합니다.



###  생성자

자바에서는 생성자를 클래스와 같은 이름의 함수로 정의합니다.

코틀린은 init 블록을 사용하여 생성자를 대체합니다. init 안에서만 사용할 수 있습니다.



##### 기본 생성자

- 클래스 별로 1개만 가질 수 있다.
- 클래스 헤더의 일부 
- 클래스 이름 뒤에 작성 
- 기본생성자는 코드를 가질 수 없습니다. 
  - 초기화는 초기화 블럭에서 작성 되어야 합니다.
  - 초기화 블록 만드는법 =  init {}
- 기본 생성자의 파라미터는 init 블록 안에서 사용 가능합니다. 

##### 보조 생성자

- 클래스 별로 여러개를 가질 수 있습니다. 
- constructor 키워드로 선언합니다. 
- 클래스가 기본 생성자를 가지고 있을 경우, 보조생성자들은 기본 생성자를 직접 / 간접으로 위임해 주어야 합니다. 
- this 키워드를 이용합니다. 

##### 생성된 기본 생성자

클래스에 생성자를 생성하지 않으면, 생성된 기본 생성자가 만들어 집니다. 

- 매개변수가 없습니다.

- public 입니다.

- private 로 선언시에는 다른 가시성이 가진, 빈 기본생성자를 만들어야합니다.

  ```kotlin
  class DontCreatedMe private Constructor{
    
  }
  ```

  ​

###### example

```kotlin
// 생성자
class Foo {
  init {
    //생성자 수행 작업들. 
    // 생성자에서 클래스 내부의 프로퍼티 값을 할당할 수 있다.
  }
}

// 기본 생성자 - 클래스 이름 뒤에 작성
class Person constructor(firstName: String){}
// 어노테이션이나, 접근 생성자가 없을 경우 constructor 생략 가능
class Person (firstName: String){}

// 보조 생성자
class Person(val name: String) {
  constructor(name: String,parent: Person):this(name) {
    
  }
  constructor(): this("홍길동",Person())
  
}
// 인자로 받은 생성자 
class Foo(val a: Int,var b: Char)
// 기본 생성자의 프로퍼티는 init 메서드에서만 사용할 수 있다.

// 주 생성자 외 다른 생성자가 필요한 경우 ( 여러 생성자가 필요한 경우

// a 값만 인자로 받을 경우, 
constructor(a:Int) :this(a,0)

// 두 값 모두 0 으로 지정시 
constructor(): this(0,0)
)
```

생성자의 가시성을 위에 앞에 접근 지정자를 사용할 수 있습니다.



### 상속 및 인터페이스 구현

자바에서는 `extends` 메서드로 implement 를 구분하지만, 코틀린에서는 `:` 뒤에 상속한 클래스나 인터페이스를 표기합니다.

###### example

```kotlin
class MainActivity:    
    AppCompatActivity(),
View.OnClickListener {
  ...
}
```



##### 클래스 상속

클래스 상속 시에는, 부모의 생성자를 호출해야 합니다. 부모의 생성자가 여러 개라면, 별도의 생성자 선언에서 부모 클래스의 생성자를 호출합니다.

부모 클래스 생성자는 `super` 키워드를 사용합니다.

```kotlin
class MyView : View {
  constructor(context: Context) : super(context){
    // view 
  }
  constructor(context: Context, attrs: AttributeSet?)
  :super(context,attrs){
    // view
  }
}
```

생성자가 여러개인 경우 `this` 키워드를 이용하여 생성자를 호출합니다.

###### example

```kotlin
class MyView : View {
  constructor(context: Context) : this(context,null)
  
  constructor(context: Context, attrs: AttributeSet?):super(context,attrs){
    // view 
  }
}
```

##### 오버라이딩

자바에서는 `@Override` 어노테이션을 붙히지만, 선택사항이며, 메서드의 유형을 코드만으로 구분하기 어렵습니다.

코틀린 에서는 이러한 모호함을 대체하기위해 함수 앞에 `override` 키워드를 붙힙니다.

```kotlin
class MyActivity: AppCompatActivity(), View.OnClickListener {
  // 이렇게 앞에 붙힙니다.
  override fun onCreate(saved~: Bundle?){
    
  }
}
```



자바에서는 클래스나 `final` 키워드를 붙혀 상속받지 못하게 하거나, 메서드를 재정의 하지 못하게 합니다.

코틀린에서는 `open` 키워드를 붙혀 상속,재정의가 가능한 함수를 만듭니다.

###### example 

```kotlin
open class OpenClass {
  // 프로퍼티 재정의 가능
  open val open Property = "foo"
  
  // 재정의 불가
  val noOpen = "goo"
  
  // 재정의 가능 함수
  open fun openFunc() 
}
```



##### this

`this` 키워드는 클래스 자신을 지칭합니다. 코틀린에서도 동일한 용도로 사용되빈다. 

```
class MyActivity : AppCompatActivity(),
	View.OnClickListener {
      lateinit var btnHello: Button
      
      override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        
        btn = fid(R.id.btn_hello)
        
        // MyActivity 설정
        btn.setOnClickListener(this)
        }
	}
```

클래스의 인스턴스를 동적으로 생성하여 사용하는 경우, this가 의미하는 클래스가 달라질 수 있습니다.

이런 문제를 해결하기 위해 자바에서는 `ClassName.this` 로 클래스를 명시하지만, 코틀린은 `this@CalssName` 으로 명시합니다.



##### 정적 필드 및 메서드

자바에서는 정적 필드,메서드를 사용하여 인스턴스 생성없이 사용할수 있는 메서드를 만들 수 있습니다.

하지만 코틀린에서는 이를 지언하지 않습니다. 정적필드나 메서드는 패키지 단위로 선언합니다.

###### example

```kotlin
package foo.bar

const val FOO = 123

fun foo(){}

class Foo {
  fun bar(){}
}
```

정적 필드는 패키지에 종속되기 떄문에 **import** 시에도 이을 사용합니다.

###### example

```kotlin
import foo.bar.FOO
import foo.bar.foo

class Bar {
  fun bar(){
    // 인스턴스 생성 없이 바로 호출이 가능합니다.
    val foo = FOO
    
    foo()
  }
}
```



패키지 단위 함수는 특정 클래스에 속해있지 않아, private 로 접근해야 하는 팩토리 메서드는 패키지 단위 함수로 구현할 수 없습니다. 이러한 경우 동반 객체를 사용하면 클래스 내 모든 멤버에 접근하고,인스턴스 생성 없이 함수를 호출할 수 있습니다.

##### 동반객체

###### example

```kotlin
// private 클래스므로, 외부에서 접근할 수 없다. 
class user private constructor(val name: String, val registerTime :long){
  
  companion object {
    // 클래스 내부에 존재하므로, private 로 선언된 생성자에 접근할 수 있다.
    fun create(name: String) : user {
      return User(name, System.currentTimeMills())
    }
  }
}
```



### 싱글톤

단 하나의 인스턴스만 생성되도록 제약을 둔 디자인 패턴입니다. 

코틀린에서는 object 를 이용하여 간편하게 선언할 수 있습니다.

###### example

```kotlin
object Singleton
```



### enum class

###### example

```kotlin
enum blass Direction {
  A,B,C,D
}
// label
enum blass Direction(val label: String){
  A,B,C,d
}
```



### 중첩 클래스

자바에서는 클래스 간 종속관계가 있는 경우, `static` 키워드를 추가합니다.

코틀린은 별도의 키워드를 붙히지 않습니다.



하지만, 비 정적 중첩 클래스인 경우 코틀린에서는 `inner` 키워드를 추가합니다.

###### example

```kotlin
class Outer {
  // inner 가 없으면 중첩클래스
  class StaticNested {
    
  }
  // 비 중첩 클래스 선언 
  inner class NonStaticNested {
    
  }
}
```

