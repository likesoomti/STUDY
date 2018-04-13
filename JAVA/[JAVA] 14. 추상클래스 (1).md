# [JAVA] 14. 추상클래스 (1).md

```
1. 추상 클래스의 이해
2. 추상 클래스가 필요한 이유
3. 추상 클래스의 문법
4. 추상 클래스 상속과 일반적인 상속 차이
```



### 1. 추상 클래스의 이해

추상적으로 정의를 해놓은 것, 사용자가 꼭 재정의 해야한다.



##### 지난 예제

본사에서 메뉴를 보낸다

지점들은  지점들 마음대로 바꿀 수 있다. 

이럴때, 상속하는 클래스 안 메서드를 명시하지 않고, **abstract** 키워드로 명시를 한다. 

`abstract` 하지 않은 경우, 재정의 하지 않을때 문제가 날 수 있다. 

이런 문제를 사전에 예방하기 위해 강제성을 띄우기 위해 추상 클래스를 사용한다. 

```java
public abstract class bonsa {
  public abstract void bibimbab();
  // 구현부가 없는, 추상적인 메서드 
}

// abstract class 를 상속받으면 무조건 구현해야한다.
public class store extends bonsa {
  @Override 
  public void bibimbab(){
    
  }
}

```

추상클래스를 상속받을 시, 추상 메서드를 무조건 구현해야합니다.

### 3. 추상 클래스의 문법

abstract 문법을 사용해 만듭니다.

```
접근지정자 abstract class classname{}
```



```java
public abstract class SuperClassEx {
    public SuperClassEx(){
        
    }
    public abstract void method1();
    
    public void method2(){
        System.out.println("Super Class 의 method1 입니다");
    }
}

class ChildEX extends SuperClassEx{

    // 안구현하면 빨간줄 
    @Override
    public void method1() {
        
    }
}
```



### 4. 추상클래스 상속과 일반적인 상속 차이

차이는 있지만, 어느 쪽이 더 좋다는 정답은 없다. 

추상클래스로 할 것인지, 일반클래스로 할 것인지 고민합니다.

정답은 없지만, 뭐가 더 좋은지 상황에 따라 만듭니다. 

이전 예제처럼, 자식 클래스에서 꼭 재정의 해야하는 부분이 있다면 추상클래스를 사용하는게 좋습니다.

**디자인 패턴** 에서 보면 상속의 차이를 느낄 수 있습니다.