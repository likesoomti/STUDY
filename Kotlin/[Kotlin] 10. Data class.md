# Data Class

##### Data class

데이터만 가지고 있고, 아무 동작도 없는 클래스

자바에서는 class 에 하지만, 코틀린에서는 Data class 를 제공합니다.

 ```kotlin
data class User(val name: String,val age: Int)
 ```

Data class 는 기본 생성자에 선언된 속성을 통해  아래의 함수를 제공합니다.

```kotlin
equals()
hashCode()
copy()
toString()
componentN()
```

명시적으로 함수를 선언한 경우, 자동으로 생성해 주지 않습니다.



##### Data class 의 조건

1. 기본 생성자에 1개 이상의 파라미터
2. 파라미터는 var,val로 선언되어야 한다.
3. Data 클래스는 `abstract`,`open`,`sealed`,`inner` 가 되지 않는다.

Data 클래스 interface 구현 가능

Sealed Class 상속 가능



##### 파라미터 없는 생성자를 사용하고 싶은 경우

매개변수 프로퍼티에 값을 지정해주면 된다.

바디 안에 프로퍼티의 값은 무관

```kotlin
data class User(val name = "", val age = 3)

val exam0 = User() // okay
val exam1 = User("kotlin") // name= kotlin age = 3
val exam2 = User("kotlin",1) // name = kotlin age = 1
val exam3 = User(age = 113) // age = 113
val exam4 = user(name="kotlin",age=4)
```



##### 복사 

data 클래스의 일부 값만 고쳐서 사용하고 싶은 경우, 

컴파일러에서 제공하는 `copy()` 메서드를 사용하면 된다.



##### Destructing Class

data class는 Destructing class 가 사용 가능하다.

```kotlin
val jane = User("kotlin","3")
var (name,age) = jane
println("$name,$age")
```



##### Standard Data Classes

스탠다드 라이브러리에서 제공하는 Data Class

- pair
- Triple

```kotlin
val jane = Pair("kotlin","3")
```





##### Nested Class

#####  중첩 클래스

```kotlin
class Outer {
  class Nested {
    fun foo() = 2
  }
}
var goo = Outer.Nested().foo()
```

##### 내부 클래스

inner 를 선언하면 바깥쪽 내부의 멤버에 접근할 수 있다.

```kotlin
class Outer {
  inner class inner {
    fun foo() = 2
  }
}
var goo = Outer().Nested().foo()
```



##### 익명 내부 클래스

객체 표현식을 사용해서 익명 내부 클래스를 사용할 수 있다.

```kotlin
// 전체 코드의 결과가 object 객체가 된다
window.addMouseListener(object: MouseAdapter() {
    override fun mouseClicked(e: MouseEvent) {
        // ...
    }                                                                                   
    override fun mouseEntered(e: MouseEvent) {
        // ...
    }
})
val listener = ActionListener { println("clicked") }
```

