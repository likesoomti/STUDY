# [JAVA] 17. 인터페이스 (1).md

```
1. 인터페이스의 이해
2. 인터페이스의 문법
3. 다형성이란?
```



### 1. 인터페이스의 이해

짱어려움 짱 중요!

인터페이스는 어렵습니다. 문법은 간단하지만, 개발 중인 프로그래밍에 어떤식으로 접목 시켜야 할지 고민이 많이 됩니다.

이번 강의를 통해 인터페이스를 완전히 이해하고, 실제 프로젝트를 적용해 봅시다.

디자인 패턴 강의 때 다시 한번 접목해 봅시다. 

실무에서 규모가 어느정도 큰 프로젝트를 한다면, 인터페이스의 필요성에 대한 완전한 이해를 하게 될 것입니다.



#### 인터페이스란

객체와 객체의 **소통수단** 입니다. 

##### 작업 명세서 입니다. 

앞으로 이런 이런 이런 명령어를 포함해야합니다!

친구야 이거만들때 이거 이거 이거하자!

##### 자바의 다형성을 만들 수 있습니다.(중요중요 )

상속에서 배운 개념입니다.

어떤 객체 A 가 있습니다. 

이 객체는 S라는 객체를 상속 받았습니다.

이 경우,

```java
A a = new A();
```

로 만들 수 있고, S 를 상속을 받았을 경우

```java
S a = new A();
```

로도 만들 수 있습니다.

하지만 상속은 단일 상속이기 때문에 하나의 타입밖에 추가가 되지않습니다.

인터페이스를 사용하면,하나의 객체에 많은 타입을 추가할 수있습니다!!



##### 인터페이스를 어케 잘 공부할까요?

내 코드에 인터페이스를 정확히 습득하는 것은 

인터페이스를 잘 습득하는 것은, 디자인 패턴을 공부하는 것이 좋습니다.



##### 객체를 부속품화 (:: 다형성과 연결됩니다.)

다양한 객체를 제품의 부속품처럼 개발자 마음대로 변경할 수 있습니다.

객체를 부속품 화 해서 이걸 사용했다, 저걸 사용했다 변경 할 수 있습니다.



### 2. 인터페이스의 문법



```java
// 첫 인터페이스 글자는 대문자를 사용합니다.
// 상수와, 추상 메서드만 가질 수 있습니다.
// 접근 지정자는 public 으로 만듭니다. 
// 인터페이스는 객체 타입으로만 존재합니다. 
// 구현은 실행되는 객체의 메서드에서 합니다. 
public interface InterfaceEX{
  public static final int CONSTANT = 10;
  public abstract void inMethod();
}
```

1. 인터페이스는 실제 기능은 없어요! ( 추상 메서드와 상수만 존재합니다.)
2. 상수와 추상 메서드만 가질 수 있습니다. 
3. 접근 지정자는 public 으로만 만들어 집니다.
4. 인터페이스는 객체 타입으로만 존재합니다.
5. 구현은 실행되는 객체 메서드에서 구현합니다. 



```java
public interface InterfaceEx {
    public static final int CONSTANT_NAME = 10;
    public int method1();
}
public interface InterfaceEx2 {
    public static final int CONSTANT_NAME = 10;
    public int method2();
}

// , 를 사용해서 인터페이스를 계속 사용할 수 있습니다.
// , 를 사용하는대신, 추상 클래스를 다 구현해 넣어야 합니다.
public class InterfaceClass implements InterfaceEX,InterfaceEx2 {
    @Override
    public int method1() {
        return 0;
    }

    @Override
    public int method2() {
        return 0;
    }
}

```

인터페이스는 추상 메서드를 가지고있고,

추상 메서드는 인터페이스를 포함하는 클래스에서 구현합니다.

팀원한테 " 어떤 클래스가있어! 만들어봐! "

이런것보다,

인터페이스를 만들어 놧으니까 어떤거어떤거 만들어라! 하는게 작업 지시 하는게 좋다. 



#### 다형성에 대해서

인터페이스 클래스 (타입) 이라는 것은, 인터페이스를 구현한 클래스 타입을 인터페이스로 지정할 수 있습니다. 

```java
InterfaceEX c2 = new ClassEx();
InterfaceEx2 c3 = new ClassEx();
```

하지만 인터페이스 타입으로 구현을 하면, 해당 인터페이스 타입으로 구현된 메서드만 사용할 수 있습니다. 

즉 

c2에서는 `method3`을 사용할 수 없습니다.

c3에서는 `method2` 를 사용할 수 없습니다.



#### 스마트폰 제작을 통한 interface 를 이해해보자

```
스마트폰에 많은 기능이 있습니다.
제조사에서 출시할 때 기본적으로 설치되어있는 어플 외에도 구글 스토어를 통해서 다양한 어플을 출시할 수 있습니다.

// 시나리오
제품 전화 송/수신  G TV리모컨 가능
  a    가능     3   기본 미 탑재
  b    가능     4   기본 탑재
  c    가능     4   기본 미탑재
```

```java
package chap17;
// 강의에서는 Iphone 으로 나왔찌만, 클린코드에서 구닥다리라구 했다.
// Imp 로 이름 명세
public interface PhoneImp {

    public void isCallSendandRecive();

    public void GType();

    public void isTVRemoteControll();
}
package chap17;

public class APhone implements PhoneImp {

    @Override
    public void isCallSendandRecive() {
        System.out.println("전화 송수신 가능합니다.");
    }

    @Override
    public void GType() {
        System.out.println("3G 입니다.");
    }

    @Override
    public void isTVRemoteControll() {
        System.out.println("리모콘 미탑재 입니다.");
    }
}
package chap17;

public class BPhone implements PhoneImp {

    @Override
    public void isCallSendandRecive() {
        System.out.println("전화 송수신 가능합니다.");
    }

    @Override
    public void GType() {
        System.out.println("4G 입니다.");
    }

    @Override
    public void isTVRemoteControll() {
        System.out.println("리모콘 탑재 입니다.");
    }
}
package chap17;

public class CPhone implements PhoneImp {


    @Override
    public void isCallSendandRecive() {
        System.out.println("전화 송수신 가능합니다.");
    }

    @Override
    public void GType() {
        System.out.println("4G 입니다.");
    }

    @Override
    public void isTVRemoteControll() {
        System.out.println("리모콘 미탑재 입니다.");
    }
}
package chap17;

public class PhoneEX {
    public static void main(String[] args) {

        PhoneImp aphone = new APhone();
        PhoneImp bphone = new BPhone();
        PhoneImp cphone = new CPhone();

        PhoneImp[] phoneImps = {aphone,bphone,cphone};

        for(int i = 0 ; i < phoneImps.length;i++){

            phoneImps[i].isCallSendandRecive();
            phoneImps[i].GType();
            phoneImps[i].isTVRemoteControll();
                            System.out.println("====================");
        }
    }
}
```



### 3. 다형성이란

말 그대로 객체가 다양하게 변할 수 있다.

#### 영화 - 돌연변이 인터페이스 만들기 

원하는 사람으로 변하는 캐릭터

- 경찰관 : 범인을 찾을 수 있다 / 물건을 찾을 수 있다.
- 소방관 : 불끄기 / 사람구하기
- 요리사 : 피자만들기 / 스파게티 만들기 

```java
package chap17;

public class Actress implements CookerImp,FireFighterImp,PoliceImp {
    @Override
    public void makePizza() {
        System.out.println("피자를 만듭니다");
    }
    @Override
    public void makePasta() {
        System.out.println(" 파스타를 만듭니다.");
    }
    @Override
    public void offFire() {
        System.out.println("불을 끕니다.");
    }
    @Override
    public void saveMan() {
        System.out.println("사람을 구합니다.");
    }
    @Override
    public void findCriminal() {
        System.out.println("범인을 구합니다.");
    }
    @Override
    public void findStuff() {
        System.out.println("물건을 찾습니다");
    }
}
// 강의에서는 Iphone 으로 나왔찌만, 클린코드에서 구닥다리라구 했다.
// Imp 로 이름 명세
public interface PhoneImp {

    public void isCallSendandRecive();

    public void GType();

    public void isTVRemoteControll();
}
package chap17;

public interface FireFighterImp {
    public void offFire();
    public void saveMan();
}
public interface CookerImp {

    public void makePizza();
    public void makePasta();

}
```

