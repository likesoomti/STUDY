# [JAVA] 18. 인터페이스 (2).md

```
1. 인터페이스와 다중 상속
2. 인터페이스를 통한 로봇 장난감 만드릭 프로그래밍
3. 인터페이스와 추상 클래스 
```



### 1. 인터페이스와 다중 상속

JAVA 는 단일 상속 프로그래밍 언어입니다.

```java
public class ChildClass extends ParentClass1{} (o)
public class ChildClass extends ParentClass1,2,3..(X)
```

C++ 에서 여러 문제점이 있었기 때문에, 다중 상속을 많았습니다.

하지만, java 에서도 interface 를 통해 여러가지 타입으로 객체를 선언할 수 있습니다. 하지만 여러가지 객체 타입으로 선만 할 수 있기 때문에 

다중 상속이 아닌 **다중 구현** 이 가능하다고 표현합니다.



##### 인터페이스가 인터페이스를 상속할 시에는 `extends` 를 사용합니다.

```java
interface b{}
interface a extends b{}
```





### 2. 인터페이스를 통한 로봇 장난감 만들기

```
장난감 미사일발사 불빛발사 팔다리움직임
곰돌이 x x o
마징가 o x o
비행기 o o x
```

저번과 달리, 기능을 인터페이스로 구현합니다.

```java
package chap18;

public class ToyEx {
    public static void main(String[] args) {
        Toy bear = new ToyBear();
        Toy robot = new ToyMachine();
        Toy airplane = new ToyAirplane();
    }
}

package chap18;

public class ToyMachine implements Missile,MoveArms {
    public ToyMachine() {
        launch();
        moveArms();
    }

    @Override
    public void launch() {
        System.out.println("로봇 미사일 발사");
    }

    @Override
    public void moveArms() {
        System.out.println("로봇 팔다리 움직이기");
    }
}
package chap18;

public class ToyBear implements MoveArms{
    public ToyBear() {
        moveArms();
    }

    @Override
    public void moveArms() {
        System.out.println("팔다리 움직이기");
    }
}
package chap18;

public class ToyAirplane implements Missile,Fire {
    public ToyAirplane() {
        launch();
        launchFire();
    }

    @Override
    public void launchFire() {
        System.out.println("비행기 불꽃 발사");
    }

    @Override
    public void launch() {
        System.out.println("비행기 미사일 발사");
    }
}
package chap18;

public interface Fire extends Toy{
    public void launchFire();
}
package chap18;

public interface Missile extends Toy{
  // 이름을 너무 추상적으로 해서 별로임. 나중에 참고 
    public void launch();
}
package chap18;

public interface MoveArms extends Toy{

    public void moveArms();

}
package chap18;
// 객체 타입 공통적으로 생성하기 위한 인터페이스 
public interface Toy {
}


```



### 3. 인터페이스와 추상클래스

##### 공통점

1. 추상 메서드를 가집니다. 추상 메서드를 가지고 하위 클래스를 구현합니다.
2. 데이터 타입이 목적입니다. 객체 생성이 목적이 아니라, 데이터 타입을 정의하는 것이 목적입니다.
3. 객체 생성은 'anonymous' 를 이용해야 합니다.

##### 차이점

1. 상속, 구현 - 추상클래스는 "상속"을 통해 사용하고, 인터페이스는 "구현"을 통해 사용합니다.
2. 추상클래스는 변수,메서드 등 모든 기능을 사용할 수 있지만, 인터페이스는 상수와 추상메서드만 존재합니다.
3. 단일 상속 다중 구현 - 추상 클래스는 상속이므로, 단일 상속만 지원하고, 인터페이스는 다중 구현이 가능합니다.