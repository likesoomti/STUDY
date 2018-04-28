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

##### 확장 함수를 추가하는 대상 클래스

를 리시버 타입이라고 합니다.

`.` 을 찍고 그 뒤 원하는 함수의 형태를 적는 방식으로 정의합니다. 

##### 함수의 구현부

함수의 구현부에서는 `this` 를 사용하여 클래스의 인스턴스에 접근할 수 있습니다.

이를 `리시버 객체` 라고 부릅니다. 