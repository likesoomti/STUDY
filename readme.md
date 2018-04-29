# 숨티위키

##### rule

공부하다가 궁금한 용어/단어들을 정리할것이다

ㄱ~ㅎ A~Z 순서대로 정리를 한다.

### ㄷ

#### 단위 테스트(= unit test)  

단위 테스트, 유닛 테스트 는 소스코드 모듈이 정확히 작동하는지 확인하는 테스트입니다. 모든 함수/메소드에 대한 테스트 케이스를 작성하는 절차를 말합니다. 

코드 변경으로 인해 문제가 발생 시, 단시간 내 바로 파악하고 잡을 수 있습니다.

단일 함수 정도에 해당하는 크기의 코드 단위에 집중합니다.

테스트 크기가 작기 때문에 빠르게 수행할 수 있고, 많은 버그를 발견해서 잡아낼 수 있습니다.

##### 특징

1. 테스트 코드들은 서로 분리되어있습니다
2. 실행은 자동화되며, 어플리케이션의 같은 부분 테스트하는 여러 테스트는 그룹화되어 한번에 처리됩니다.



###### 비슷한 용어

TDD, BDD



### ㅅ

#### 스트림과 스트림 API

스트림과, 스트림 api는 완전히 다른 개념이다.



#### 스트림

자바에서는 데이터 입력, 출력 시 스트림이라는 패키지를 통해 데이터를 입력 받는다. 

단방향 이동이기 대문에, 크게 입력 / 출력 스트림으로 구분이 된다.

#### 스트림 API

저장된 데이터에 공통적인 접근 방식을 제공해주는 인터페이스

컬렉션,배열 등에 저장된 많은 데이터들에 접근하기 위해서는 iterator 나 loop 문으로 새로운 코드를 작성했는데,

가독성이 떨어지며 데이터마다 다른 방법으로 접근해야 하는 불편함이 있었다.

이걸 자바se8 부터 보안해, 스트림 api 가 나왔고, 컬렉션 뿐만 아니라 저장된 데이터도 같은 방법으로 다룰 수 있게 된다.

##### 스트림 API의 특징

- 내부 반복을 통해 작업을 수행한다.
- 한번만 사용할 수 있다
- 원본 데이터를 변경하지 않는다.
- 필터 맵 기반의 api 를 이용하여 지연 연산을 통해 성능을 최적화 한다.
- parallelStream 메소드를 통해 손쉬운 병렬 처리를 지원한다.

-----

 

### D

#### DI, Dependency Injection , 의존성 주입

객체의 의존성을 외부에서 주입하는 개념입니다.

크게 세가지가 있습니다.

1. 생성자 사용
2. setter 메서드를 사용
3. 초기화 인터페이스 사용 

일단, 의존성 주입을 예시로 쉽게 이해해봐요!

```java
public interface Unit {
  public void sayHello();
}
class Archur implements Unit {
    public void sayHello(){
        System.out.println("나는 안녕 나는 궁수");
    }
}
class Warrior implements Unit{
    public void sayHello(){
        System.out.println("나는 안녕 나는 전사");
    }
}

public class ioctest {
    public static void main(String[] args){
        Unit unit = new Warrior(); // 여기 부분 바뀜..
        unit.sayHello();
    }
}
```

이렇게 main 안에 있는 `Archur` 클래스를 `Warrior` 클래스로 바꿔주게 되는데, 이런 상황을

`ioctest`가 `Warrior`,`Archur`에 의존하고 있다고 합니다. 기능을 변경할 때 마다 바꿔줘야 하기 때문이죠. 

컴파일시 매우 비효율 적입니다. 

따라서 외부에서 객체를 설정하기 위해, Spring 에서는 외부 `beans.xml`  파일을 사용해 객체를 생성합니다.

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://spring~"
       xmlns:xsi="http://www.w3.org"
       xsi:schemaLocation="~">
	<bean id="UnitBean" class="packagename.Archur"/>
</beans>
       
```

```java
// import java bean 관련 팩토리

public interface Unit {
  public void sayHello();
}
class Archur implements Unit {
    public void sayHello(){
        System.out.println("나는 안녕 나는 궁수");
    }
}
class Warrior {
    public void sayHello(){
        System.out.println("나는 안녕 나는 전사");
    }
}

public class ioctest {
    public static void main(String[] args){
        BeanFactory factry = new XmlBeanFactory(new FileSystemResource("resource/beans.xml"));
        Unit unit = factory.getBean("Unit",UnitBean.class) 
        unit.sayHello();
    }
}
```

Spring 에서는 BeansFactory 를 이용해 외부 xml 파일에서 객체 생성 주입을 해주는데요.

그러면 프로그래밍 코드 변화 없이, xml에서

```xml
<bean id="UnitBean" class="packagename.Archur"/>
```

한줄로 바꿈으로 외부환경에서 관리함으로서, 

1. 소스코드 변경없이 

2. 모듈간의 결합도를 낮춰 유연한 변경을 가능하게 합니다.

   ​

---

### E

#### Exception

##### 예외처리

 프로그래밍 실행중에 프로그램 흐름에 어긋나는 이벤트를 "예외"라고 합니다.

1. 메모리가 부족하거나
2. 입출력 연산 중 에러가 발생하거나

이런 프로그래밍 에러가 발생시, 이 에러를 잡아주는 코드를 설정하는것을,  **예외처리** 라고합니다.

자바에서는 try-catch-finally 문으로 이 예외를 잡습니다.

```java
try{
  //
}catch(Exception e){
  
}finally{
  
}
```

##### 특징

1. 에러가 나는 코드만 관리할 수 있다.
2. 에러를 그룹별로 관리할 수 있다.
3. Call Stack으로 에러발생을 전파할 수 있다.

##### 예외처리 계층 

예외처리 계층을 보면 object 는 `Throwable` 이라는 최상위 인터페이스를 구현하여 예외처리를 던집니다.





![Alt text](https://lh5.googleusercontent.com/WqqNoyFEkZXfmZBBQjgIutY72_BUV6_By_BAe7Ih9u36HfelS3nTWQEYtdRUkQS32Tuhg9P9CUXo-jgvOpkO84vLm2viI4Od0BNustwONdMm7DKZnKC6kyVHyRJbsESLIPV4uBU)



예외는 크게 

1. error
2. Exception

으로 나뉩니다.

##### error

JVM 에서 생기는 에러를 말합니다.

 애플리케이션 구동 범위를 벗어나기 때문에 코드,로직,흐름에서 에러를 처리할 수 없습니다.

##### exception

프로그래머들이 예상할 수 있는 에러입니다.

1. 체크 할수 있는 예외와,
2.  없는 예외로 나뉩니다.

##### 체크되는 예외

컴파일 타임에 체크되어야하고, 

프로그래머에게 `throws`  메서드를 선언할 것을 요구합니다.

코틀린에서는 이런 체크되는 예외를 따로 검사하지 않습니다.

##### 체크되지 않는 예외

프로그래머에게 예외 처리를 강조하지 않는 예외입니다. 



### I

#### Inversion of Control, IOC

**제어 역전**

한마디로 설명하면 프로그램의 흐름을 프레임워크에서 주도하는 것을 말합니다. 

큰 시스템에서는 제어할 객체가 수백개 이상된다. 비슷한 객체를 단위로 묶고 관리해도 많기 때문에 

더 효율적인 방법을 사용하기 위해 제어 역전을 사용합니다.

프레임워크에 제어권이 넘어가면서 **DI** (의존성 주입) ,**AOP** (관점 지향 프로그래밍) 이 가능해 집니다.

큰 프로그램에서는 컴파일 하는데 시간적 비용이 어마어마한데요

소프트웨어에서는 기능 변경(코드 변경) 이 불가피한데, 기능을 추가/보수 할때마다 매번 컴파일을 한다는것은 비효율적 입니다.

따라서 **소스의 변경을 최소화** 하기 위해 사용합니다

##### DI 의존성 주입 알아보기 :  `ctrl+f5 DI 검색 `





### T

#### TDD

TDD는 매우 짧은 개발 사이클을 반복하는 개발 프로세스입니다.  '언제' 테스트를 작성하고 수행하는지에 대한 절차를 정합니다. 

TDD 를 사용하면 테스트 커버리지를 크게 높일 수 있습니다. 

##### 절차

1. 테스트 코드를 작성합니다.
2. 작성한 테스트 코드를 기존에 작성된 다른 테스트와 수행합니다. 새로 추가된 테스트 코드는 실패할 것입니다.
3. 실패한 테스트를 바로잡기 위해 최소한의 코드를 구현부에 작성합니다.
4. 실패한 테스트가 성공하는지 확인하기 위해 테스트를 재수행합니다.
5. 필요에 따라 작성한 코드를 리펙토링한합니다.
6. 1번 부터 다시 반복한다.

##### 단위 테스트와의 차이

단위테스트는 테스트 단위가 '무엇인지'를, TDD는 작성 '시점' 을 알려줍니다.