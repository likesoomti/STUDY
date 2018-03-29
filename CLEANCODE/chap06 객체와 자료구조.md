# chap06 객체와 자료구조



##### 변수를 비공개로 정의하는 이유 = 의존하지 않게 만들고 싶어서

그런데 왜.. 많은 프로그래머들은 조회/설정 함수를 공개해 변수를 외부에 노출시킬까?



### 1. 자료 추상화

2개의 점을 표현하는 클래스를 비교해보자

###### sample1

```java
public class Point {
  public double x;
  public double y;
}
```

- 직교 좌표계를 사용하는 클래스
- 개별적으로 읽고 설정을 강제한다.
- 구현을 노출한다
- private 로 변경되더라도, get/set 함수를 제공하면 구현을 외부로 노출시킬 수 있다.

###### sample2

```java
public interface Point {
  double getX();
  double getY();
  void setCartesian(double x, double y);
  double getR();
  double getTheta();
  void setPolar(double r, double theta);
}
```

- 어떤걸 사용하는지 알 수가 없다. 
- 자료구조를 명백하게 표현한다. 



변수 사이에 함수라는 계층을 넣는다고, 구현이 저절로 감춰지지는 않는다.

##### 추상화 인터페이스를 제공해, 사용자가 구현을 모른체 자료의 핵심을 조작해야한다.



###### sample3

```java
public interface Vehicle {
  double getFuelTankCapacityInGallons();
  double getGallonsOfGasoline();
}
```

- 자동차 연료 상태를 구체적인 숫자 값으로 알려준다.
- 두 함수가 변수값을 읽어 반환하는게 보인다

개의 점을 표현하는 클래스를 비교해보자

###### sample4

```java
public interface Vehicle {
  double getPercentFuelRemaining();
}
```

- 자동차 연료 상태를 백분율이라는 추상적인 개념으로 알려준다.
- 어떤식으로 구현되는지 정보가 드러나지 않는다.



sample1,2를 비교했을때는 sample2 가, sample3,4를 비교했을때는 sample4 가 훨씬 추상적인 표현이다.



**자료를 세세하게 공개하기 보다는, 추상적인 개념으로 표현하는 것이 좋다.**



### 2. 자료/객체 비대칭

**객체**는 추상화 뒤로 자료를 숨긴 채 자료를 다루는 함수만 공개한다.

**자료구조**는 자료를 그대로 공개하며, 별다른 함수는 제공하지 않는다.

두 정의는 본질적으로 상반된다. 

**절차적인 코드**는 기본 자료 구조를 변경하지 않으면서 새 함수를 추가하기 쉽다. 

그러나, 새로운 자료구조를 추가하기 어렵다. 모든 함수를 고쳐야한다



**객체 지향 코드**는 기존 함수를 변경하지 않으면서 새 클래스를 추가하기 쉽다.

반면, 새로운 함수를 추가하기 어렵다. 그러면 모든 크래스를 고쳐야한다.



객체지향 코드에서 어려운 변경은, 절차적인 코드에서쉽고, 절차지향 코드에서 어려운 변경은 객체 지향 코드에서 쉽다.



### 3. 디미터 법칙

모듈은 자신이 조작하는 객체의 속사정을 몰라야 한다. 



**객체**

객체는 자료를 숨기고 함수를 공개한다.

###### sample1

```java
final String outputDir = ctxt.getOptions().getScratchDir().getAbsolutePath(); //1. worst
final String outputDir = ctxt.options.scratchDir.absolutePath; // 2.  better
```

*기차 충돌 코드*

여러 객차가 한줄로 이어진 기차처럼 보이기때문엥. 조잡하게 여겨지는 방법이다.

###### sample2

```java
Options opts = ctxt.getOptions();
File scratchDir = opts.getScratchDir();
final String outputDir = scratchDir.getAbsolutePath();
```

디미터의 법칙은 구조가 자료구조인지 , 객체인지에 따라 적용 상태가 달라진다.

**객체** 는 내부 구조를 숨겨야 함으로 디미터의 법칙을 위반한다.

**자료구조** 는 당연히 내부구조를 노출함으로 적용이 되지 않는다.

sample1 에 2처럼 구현하면 디미터 법칙을 위반하지 않는다.



*잡종 구조*

절반은 객체/ 절반은 자료구조인 잡종 구조가 나오는 경우도 있다.



*구조체 감추기*

위의 샘플 코드가 객체인 경우, 줄줄이 사탕으로 엮이면 안된다.

객체는 내부 구조를 감춰야 한다. 

###### sample

```java
ctxt.getAbsolutePathOfScratchDirectoryOption(); // 1. 
ctxt.getScratchDirectoryOption().getAbsolutePath(); // 2. 
```

1. 번의 경우 객체에 공개해야 하는 메서드가 ㅁ낳아진다
2. 객체가 아니라 자료구조를 반환한다고 가정한다. 

**객체는 무언가 하라고 말해야지 속을 드러내라고 하면 안된다 **

모듈에서 해당 함수는 자신이 몰라야 하는 여러 객체를 탐색할 필요가 없다. 



### 4. 자료 전달 객체

자료 구조체의 전형적인 형태는 공개 변수만 있고 함수가 없는 클래스이다. 

##### DTO 

 가공되지 않은 DB 정보를 애플리케이션 코드에서 사용할 객체로 변환하는 일련의 단계에서 사용하는 구조체 

```java
public class Address {
    private String street;
    private String streetExtra;
    private String city;
    private String state;
    private String zip;

    public Address(String street, String streetExtra, String city, String state, String zip) {
        this.street = street;
        this.streetExtra = streetExtra;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet(){
        return street;
    }

    public String getStreetExtra(){
        return streetExtra;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public String getZip(){
        return zip;
    }
}
```

*활성 레코드*

DTO의 특수한 형태 . DTO + save,find 같은 탐색 함수를 저장하는 형태.

잡종 구조가 되기 때문에 바람직하지 않다. 

활성 레코드를 자료구조로 취급하면 해결된다.



##### 결론

**객체**는 동작을 공개하고 자료를 숨긴다. 따라서 동작을 변경하지 않으면, 객체타입을 추가하긴 쉽지만 기존 객체에서 새 동작을 추가하기는 어렵다. 

**자료구조**는 별 동작없이 자료를 노출한다. 따라서 동작을 추가하기 쉽지만, 기존 함수에 새 자료구조를 추가하기는 어렵다.



새로운 자료 타입을 추가하는 유연성이 필요하면 객체가 적합하다. 

새로운 동작을 추가하는 유연성이 필요하면 자료구조와 절차적인 코드가 더 적합하다.