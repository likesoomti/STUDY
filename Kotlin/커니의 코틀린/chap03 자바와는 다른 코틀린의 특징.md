# 3. 자바와는 다른 코틀린의 특징

## 3.1 클래스

코틀린에서 제공하는 특수한 클래스와, 코틀린 클래스에서만 제공하는 기능을 살펴본다.

### 데이터 클래스

자바에서는 자료들의 값만 관리하는 클래스가 있습니다.

이럴때는, 필드를 생성하고 생성자와, 각 필드를 통해 getter/setter 클래스를 작성합니다.

```java
public class Person {
    private String name;
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

```

자바에서는 해당 필드를 담는 용도를 사용할때 getter/setter 를 해줘야하고,

`equals()` 및 `hashCode()` 메서드를 추가로 구현해주어야 합니다.

이런 경우, 차후 클래스를 변경 / 유지보수 시 `equals()` , `hashCode()` 메서드의 수동적인 변경이 발생합니다.



이런 불편함을 코틀린에서는 특별한 **데이터 클래스** 를 통해 제공해줍니다.

##### 코틀린의 데이터 클래스

코틀린의 데이터 클래스는 자료를 구성하는 프로퍼티만 선언하면,  `equals()` , `hashCode()` ,`toString()` 메서드를 자동으로 만들어 줍니다.

```kotlin
data class Person(val name:String, val address: String)
```



### 한정 클래스

한정 클래스는 enum 클래스를 확장한 개념을 가진 클래스입니다.

각 종류별 인스턴스만 생성되어있는 enum 클래스와 달리, 인스턴스를 여러개 생성할 수 있습니다.

`sealed` 키워드를 사용합니다.



```kotlin
sealed class MobileApp(val os:String){
    class Android(os: String, val packageName: String) :MobileApp(os)

    class IOS(os:String,val bundleId: String) :MobileApp(os)
}

fun whoami(app: MobileApp) = when(app){
    is MobileApp.Android -> println("${app.os}/${app.packageName}")
    is MobileApp.IOS -> println("${app.os}/${app.bundleId}")
}
```

만약 이넘 클래스 상수의 인스턴스들이 다를경우, 유용하게 사용됩니다. 



이럴 경우, 다른 클래스가 추가되어있는 경우 굉장히 유용합니다. 

한정클래스를 선언하고, whoami 같은 함수를 호출시, Android,Ios 외에 다른 windowMobile 이라는 클래스가 추가되었을 시  whoami 에 else 문으로 걸어주어야하지만 이에 대해 컴파일 에러가 발생 안하고 넘어갈경우 버그가 발생합니다.

코틀린에서는 새로운 이넘 클래스 추가시 when 문에 해당 함수를 호출하지 않으면 에러를 발생시켜 이러한 버그를 최소화 하게 만들어 줍니다. 

`whoami` 

```kotlin

sealed class MobileApp(val os:String){
    class Android(os: String, val packageName: String) :MobileApp(os)

    class IOS(os:String,val bundleId: String) :MobileApp(os)

    class WindowMobile(os:String,val packageWindow:String): MobileApp(os)
}

fun whoami(app: MobileApp) = when(app){ //when 에러발생. windowMobile 누락 알림 
    is MobileApp.Android -> println("${app.os}/${app.packageName}")
    is MobileApp.IOS -> println("${app.os}/${app.bundleId}")

}
```

### 프로퍼티 사용자 지정 Getter/Setter

클래스 안에 프로퍼티는 getter/setter 가 내부적으로 구현되어있습니다.

단순히 필드 값을 반환/설정되도록 구현이 되어 있습니다. 

커스터마이징을 하기 위해서 `Getter/Setter` 프로퍼티 선언과 함께 `get()`,`set(value)` 을 사용하여 커스텀 할 수 있습니다.



##### 간단한 클래스 구현

```kotlin
class Person(val name:String,val address:String)
```

이 클래스에 age getter 를 성인 여부로 표시하는 커스터 마이징을 할 경우에는 

```kotlin
class Person2(val age:Int,val name:String) {
    val adult: Boolean
        get() = age >= 19
}
```

주소 프로퍼티에 앞 10자만 저장하도록 구현하는 코드는 

```java
class Person2(val age:Int,val name:String) {
    val adult: Boolean
        get() = age >= 19
    
    var address: String =""
        set(value) {
            field = value.substring(0..9)
        }
}
```



## 3.2 함수

코틀린의 함수는 자바와 동일한 기능을 수행하지만, 표현이 더 자유롭고 유용한 기능을 갖추고 있습니다.



### 명명된 인자 

코틀린에서는 명명된인자를 사용함으로써, 함수를 호출할 때 매개변수의 순서와 상관없이 인자를 전달할 수 있습니다.

매개변수의 수가 많아지더라도, 인자에 어떤 값이 전달되는지 쉽게 구분할 수 있습니다.

```kotlin
fun drawCircle(x:Int,y:Int,radius:Int){...}
```

자바의 경우 순서대로 인자를 대입해야합니다.

매개변수의 정보를 알고있지 않다면, 대입된 값의 의미를 파악하기 어렵습니다.



하지만, 코틀린에서는 명명된 인자를 제공하므로 매개변수의 이름과 함께 대입할 수 있습니다.

필수는 아니고 선택인듯!

```kotlin
fun main(args: Array<String>) {
    var test: User = User.create("test")
    drawCircle(10,5,25)
    drawCircle(x=10,y=5,radius = 25)
    drawCircle(x=10,5,25) // 이거 책에는 된다고 했는데 에러남.
}
```



### 기본 매개변수

코틀린에서는 기본 매개변수를 지정해, 인자 변수에 기본값을 제공해 만들 수 있습니다.

이때 지정되는 값을 **기본 매개변수** 라고 합니다.

##### radius를 기본 매개변수로 가지는 값 

```kotlin
fun drawCircle(x:Int,y:Int,radius:Int =25){
}
```



### 단일 표현식 표기

코틀린에서는 메서드를 항상 `{}` 로 감싸야 하지 않습니다.

단일표현식을 사용하여 정의할 수 있습니다.

```kotlin
// 일반적인 함수 정의
fun test() : Int {
    return 21*1
}
// 축약 1
fun test2() : Int = 21*1
// 반환값 추론 메서드 
fun test3() = 21*1
```



### 확장 함수

자바에서는 기존에 만든 클래스에 새로운 메서드를 추가 시, 클래스를 상속하는 새로운 클래스를 작성해야 합니다.

코틀린에서는 **확장함수** 를 사용하여 상속 없이 기존 클래스에 새로운 함수를 추가할 수 있습니다.

확장함수를 사용하면

1. 기존 클래스의 내용 수정 없이 함수를 만들 수 있습니다.

##### 확장 함수를 추가하는 대상 클래스

를 리시버 타입이라고 합니다.

`.` 을 찍고 그 뒤 원하는 함수의 형태를 적는 방식으로 정의합니다. 

##### 함수의 구현부

함수의 구현부에서는 `this` 를 사용하여 클래스의 인스턴스에 접근할 수 있습니다.

이를 `리시버 객체` 라고 부릅니다. 

```kotlin
class Car {
    fun getPrice():Int {
        return 10000
    }
}
```

이 자동차에 이름을 출력하는 함수를 확장함수를 통해 만든다면, 

```kotlin
fun Car.brandName(name: String): String{
    return name
}
```

이렇게 `클래스이름`.`함수이름` 으로 추가할 수 있습니다.

확장 함수는 클래스를 수정하는 것이 아닌, 클래스 타입 변수에 함수를 추가하는 것 입니다.

이 확장함수는 클래스에 "정적으로" 표현되기 때문에 호출되는 표현식의 "type"에 결정됩니다.

```kotlin
open class Car {
    open fun getPrice():Int {
        return 10000
    }
}
class Bus : Car(){
    override fun getPrice(): Int {
        return 20000
    }
}
fun Car.getFuel(): String{
    return "gasolin"
}
fun Bus.getFuel(): String {
    return "LPG"
}
fun printPrice(fuel: Car){
    println(fuel.getPrice())
}
fun printFuel(fuel:Car){
    println(fuel.getFuel())
}
fun main(args: Array<String>) {
    var test: User = User.create("test")
    drawCircle(10,5,25)
    drawCircle(x=10,y=5,radius = 25)

    var bus = Bus()
    printPrice(bus) // 20000
    printFuel(bus) // gasolin
}
```

##### 호출되는 표현식의 타입?

예를들어 같은 이름으로 하나는 상속 오버라이드를, 하나는 확장 함수로 구현해 보겠습니다.

상속된 메서드는 자신의 값을 출력하지만,

확장 함수는 매개변수에 있는 값인 "gasolin" 으로 출력됩니다.

이렇게 정의된 확장 함수는 `{정의된 파일 이름}`KT 클래스 내 정적 함수로 변환됩니다.



### 연산자 오버로딩

코틀린은 사용자 정의 타입에 한해 연산자 오버로딩을 지원합니다. 

사전 정의된 함수를 재정의 하는 방식으로 오버 로딩을 사용할 수 있스빈다.

오버로딩을 위한 함수는 함수 정의에 `operator` 키워드가 추가됩니다. 

기존의 연산자를 재정의하는 것만 허용합니다. 

##### 단항 연산자 

- +
- 0
- !
- ++
- --

##### 단항 연산자 재정의하기

```kotlin

class Volume(var left:Int,var right: Int) {
    operator fun unaryMinus() : Volume {
        this.left = -this.left
        this.right= -this.right
        return this
    }
}
// 확장 함수를 통한 재정의 
operator fun Volume.inc() : Volume {
    this.left +=1
    this.right +=1
    return this
}
```



### 중위 표기법 지원

코틀린에서는 자용자가 정의한 함수를 중위 표기법을 사용하여 호출할 수 있습니다.

해당 함수는 다음 조건을 만족해야 합니다.

- 함수 선어네 infix 키워드 표기
- 확장 함수 혹은 멤버 함수이면서, 매개 변수가 하나일 것 

중위 표기법을 사용하면

```kotlin
class Volume(var left,var right){
  infix fun inreaseBy(amount:Int){
    this.left += amount
    this.right += amount
  }
}
// 확장함수 사용해서
infix fun Volumn.decreaseBy(amount:Int){
  this.left -= amount
  this.right -= amount
}
val currentVolume = Volume(50,50)
// 중위 미사용
currentVolume.increateBy(30)
// 중위 사용 
currentVolume decreaseBy 30 
```

처럼 문장으로 표현할 수 있어집니다.



## 3.3 람다 표현식 

람다 표현식은 하나의 함수를 표현할 수 있습니다.

특히 익명 클래스를 간결하게 표현할 때 사용할 수 있으므로 매우 유용합니다. 

##### java

익명 클래스를 사용하면, 문법상 한계로 인해 리스너의 주요 구현부 코드보다, 

익명 클래스를 생성하는 코드가 길었습니다.

```java
Button button = hiih;// button instance
button.setOnClickListener(new View.OnClickListener(){
  @override
  public void onClick(View v){
    dosomething()
  }
})
 
```

##### 람다를 사용한 java 

람다를 사용하여, 실제 메서드 구현에 필요한 요소만 기술할 수 있습니다.

```java
Button button = hiih;// button instance
button.setOnClickListener((View v) -> doSomething());
// 타입 생략 가능
button.setOnClickListener(v -> doSomething())
```



코틀린에서도 람다를 사용할 수 있습니다. 

코틀린의 람다 표현식은 자바의 람다와 비슷하지만, 중괄호를 사용하여 앞 뒤를 묶어준다는 점이 다릅니다. 

```kotlin
{x:Int,y:Int -> x + y}
```

##### 코틀린을 사용한 람다표현식

```kotlin
val button: Button =...
button.setOnClickListener({v:View -> dosomething()})
// 인자 생략 가능 
button.setOnClickListener({v -> dosomething()})
```

##### 멤버 참조

객체에서 하나의 메서드만 호출한다는 뜻입니다.

자바에서는 메서드참조라고 불립니다.

```kotlin
fun doSomethingWithView(view: View){...}

val button :Button = //

// doSomethingWithView 호출
button.setOnClickListener({v -> doSomethingWithView(v)})

// 멤버참조 
button.setOnClickListener(::doSomethingWithView)
```

코틀린에서는 프로퍼티 또한 멤버 참조를 지원합니다.

```kotlin
class Person(val age:Int,val name:String) {
    val adult = age> 19

}

fun printAdults(people: List<Person>){
    people.filter( {person -> person.adult} ).forEach(println("Name= ${it.name}"))
    people.filter(Person::adult).forEach{ println()"Name=${it.name}"}
}
```



### 코틀린 람다 표현식의 유용한 기능

함수를 호출할 때 대입하는 인자 중 마지막 인자가 함수 타입이고, 인자에 함수를 대입할 때 람다 표현식을 사용한다면 

람다표현식은 함수의 인자를 대입하는 괄호 외부에 선언할 수 있습니다.

```kotlin
fun doSomethingWithView(view: View){...}

val button :Button = //

// doSomethingWithView 호출
button.setOnClickListener({v -> doSomethingWithView(v)})
// 하나만 보낼 경우, 괄호 생략 가능
button.setOnClickListener {v -> doSomethingWithView(v)}
// 매개변수가 1개일 경우 it 으로 변환 가능
button.setOnClickListener{doSomethingWithView(it)}
```



## 3.4 코틀린의 여타 특징

코틀린의 특별한 기능에 대해 알아봅니다.



### 타입 별칭

제네릭 타입은, 다소 복잡한 형태의 타입을 사용하는 경우가 있습니다.

이럴 경우 타입 별칭을 사용할 수 있습니다.

`typealias`를 사용하여 정의합니다.

```kotlin
typealias PeopleList = List<Person>
typealias PeopleInTags = Map<String,Person>

fun SendMessage(people: List<Person>)

==

fun SendMessage(people: PeopleList)
```

클래스나 함수와 마찬가지로 타입을 인자로 받을 수 있습니다.

``` kotlin
typealias ItemsInTags<T> = Map<String,T>
```

함수형 타입에도 타입 별칭을 지정할 수 있습니다.

다음은 함수형 타입을 매개변수로 받는 간단한 함수입니다.

```kotlin
fun sednMsg(people:List<Person>,filterFunc: (Person) -> Boolean){
  people.filter(filterFunc).forEach{
    
  }
}

// 타입 선언
typealias PersonFilter = (Person) -> Boolean
fun sednMsg(people:List<Person>,filterFunc: PersonFilter){
  people.filter(filterFunc).forEach{
    
  }
}
```

타입 별칭은 새로운 클래스가 생성되는 것이 아닙니다.

타입 별칭으로 선언된 타입은 컴파일 시점에 모두 원래 타입으로 변환됩니다.

실행 시점의 부하가 없습니다.

### 분해 선언 

복잡한 자료구조를 사용하다보면, 필드 중 일부 항목만 사용하거나, 별도로 변수를 뽑아 사용하는 경우가 있습니다.

코틀린에서는 각 프로퍼티가 가진 자료의 값을 한번에 변수에 할당할 수 있습니다.

```kotlin
data class Person(val age: Int, val name:String)

val person : Person
// 여러개의 값을 한번에 할당 
val (ageOfPerson , nameOfPerson) = person 
```

분해선언을 사용하면 내부적으로 값에 `component1()`,`component2()` 의 값이 할당됩니다.

클래스에 프로퍼티 수 만큼 `componentN()`함수가 있어야하며, 이 함수를 포함하고 있는 클래스에서만 분해 선언을 사용할 수 있습니다.

분해 선언을 기본적으로 제공하는 클래스는 다읍과 같습니다.

- data class
- kotlin.Pair
- kotlin.Triple
- kotlin.collections.Map.Entry

분해 선언은 반복문에도 사용할 수 있으며, 맵 자료구조를 사용할 때 유용합니다. 

```kotlin
val cities:Map<String,String> = ...

for ((citycode,name) in cities){
  println("$citycode=$name")
}

// 람다 
cities.forEach { citycode,name -> println("$citycode=$name)"}
```



만약, `ComponentN()` 를 제공하지 않는 클래스에서 선언하고 싶을 시, 선언순서 / 타입에 알맞게 추가해 주면 됩니다.

일종의 규칙처럼 선언되기 때문에, 앞에 `operator` 를 선언해 주어야 합니다. 