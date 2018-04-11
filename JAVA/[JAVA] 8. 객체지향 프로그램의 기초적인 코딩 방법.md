# [JAVA] 8 객체지향 프로그램의 기초적인 코딩 방법

````
1. 클래스 제작
2. 메서드와 인스턴스 변수 만들기
3. 생성자의 이해
4. this 키워드의 이해 
````



### 1. 클래스 제작

```java
// 패키지 명 써줘야한다.
package com.soomti.makeClass;

// 클래스 명. 함수와 달리 () 가 없다.
public class ManClass {
  // 클래스와 같은 이름을 가진 함수 
  public ManClass(){
    //
  }
}
int main(){
  ManClass m = new ManClass();
  ManClass m2 = new ManClass();
  
  mc.equals(m2) // 같은 객체인지 비교
}
```



### 2. 메서드와 인스턴스 변수 만들기

```java
package com.soomti.makeClass;

// 클래스 명. 함수와 달리 () 가 없다.
public class ManClass {
  
  private int age; // 인스턴스 변수 : 클래스 안에 있는 변수 
  private int height;
  private int weight;
  
  // 모든 곳에서 쓰고싶다 : public 접근지정자 사용
  // 나는 클래스 내부에서만 쓸거야 : private 사용!
  // private 가 된다면 바깥에서 사용할 수 없기 때문에, getter,setter 를 만들어야 한다.
  
  // 생성자는 여러개가 될 수 있다.
  public ManClass(){
    //
  }
  public ManClass(int age,int height,int weight){
  }
  
  public int getAge(){
    return age;
  }
  public void getAge(int age){
    // this 내 자신. 이 객체가 가지는 age 값을 나타낸다.
    // 클래스 인스턴스 변수를 듯한다. 
    this.age = age;
  }
  // 클래스 내부 변수. 접근 지정자로 외부/내부 설정할 수 있다.
  public double calculateBMI(){
    return result;
  }
}

int main(){
  
  // 생성자의 종류에 따라 객체를 다르게 생성할 수 있다.
  ManClass m = new ManClass();
  ManClass m2 = new ManClass(10,163,50);
  
}
```

##### equals()

 객체를 비교하는 함수

##### this

나 자신.객체의 age 클래스 인스턴스 변수 

##### private, public

private: 클래스 내부에서만 접근 가능하다

변수를 private 로 설정하고, 외부에서 참조하고 싶을 경우 `getter`,`setter` 로 만든다

##### 생성자

클래스 이름과 동일한 메서드

매개변수,타입을 다르게 여러개 선언할 수 있다. 

