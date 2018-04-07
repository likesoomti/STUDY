# [Kotlin] 11. Object Expression and Declation.md



### object 키워드

객체를 생성한다. 클래스랑은 다름

어떤 클래스를 작성하고, 그 클래스에 객체를 생성하는게 아니라,

어떤 클래스에서 바로 객체를 사용하고 싶을 때 사용하는 키워드.



상속받은 클래스를 명시적으로 선언하지 않고 `object`  키워드를  바로 쓸 수 있습니다. 



##### 자바 익명객체

자바에서 콜백함수를 사용할때 익명객체를 이용했습니다. 코드 내부에서 익명 객체를 이용해 listener를 전달했는데요.

코틀린에서는 `object` 로 제공합니다.

##### 싱글톤

코틀린에서는 object 를 통해 싱글톤을 손쉽게 만들 수 있습니다.

##### companion object

어떤 클래스에 관계된 싱글톤 객체가 필요할때 사용할 수 있습니다.

코틀린은 *static* 메서드가 존재하지 않습니다.

스태틱한 방법을 사용하기 위해서 companion object 를 사용합니다.

##### 자바 코틀린 비교

```java
 button.setOnClickListener(new OnClickListener() {
    public void onClick(View v) {
    }
 });
```

```kotlin
button.setOnClickListener { view ->
    println("clicked")
}
```

