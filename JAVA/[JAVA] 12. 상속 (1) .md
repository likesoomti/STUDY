# 상속 (1)

```
1. 상속! 어렵지 않아요. 일상에서 알고 있는 상속입니다.
2. 상속이 필요한 이유
3. 상속 문법의 이해
4. 상속 예제 - 대대로 이어오고 있는 식당 메뉴 
```



### 1. 상속 어렵지 않아요! 

객체지향 의 대표적인 부분 : 상속과 추상화

중요한 부분을 정확하게 알아야지 안그러면 개발 찌질이..



#### 상속

어떤 객체가 다른 객체로부터 데이터와 메서드를 상속 받을 수 있습니다.

상속 받은 객체는 받은 데이터와 메서드를 이용하고, 변경시킬 수 있습니다.

#### 예시

자전거 = 핸들,바퀴,브레이크,페달,프레임

네발자전거 = 자전거(상속) + 바퀴 2개 

처음부터 자전거를 만드는 것 보다 쉽다! 



### 2. 상속이 필요한 이유

항상, 처음부터 개발하기는 어렵다 = 고됨 ㅠㅠ

1. 처음부터 하얀 도화지에 하는것보다, 기존에 있는 프로그램을 사용하여 빠른 시간 내에 만든다.
2. 다양한 객체를 상속을 통해 하나의 객체로 묶을 수 있습니다.



```java
public class S {
  
}
// s 상속 받기
public class A extends S{
  
}
public class B extends S{
  
}
public class C extends S{
  
}
// 상속을 받는 순간, 각자의 클래느는 S 클래스라고 부를 수 있다. 
// 따라서 하나의 객체로 묶을 수 있다.
```



### 3. 상속 문법의 이해

상속을 해주는 클래스 : parent

상속을 받는 클래스 : child

```java
public class ParentClass{
   String pStr = "부모 클래스";
  public ParentClass(){
    
  }
  public void getPapa(){
    
  }
  public void getMama(){
    
  }
  
}
public class ChildClass extends parentClass {
  String cStr = "sonclass";
}
main(){
  ChildClass c = new ChildClass();
  c.getPapa(); // 상속 받아서 가능하다
  c.getMama(); // 상속 받아서 가능하다
}
```

##### 자바는 상속이 1개만 가능합니다. 



##### 상속 받은 값 수정하기

`@override` 를 통해 변경할 수 있습니다.

```java
public class CookingClass {
    public static void main(String[] args) {
        ChildMenu c = new ChildMenu();
        c.makeChunggukjang();
    }
}
class ParentMenu {
    public ParentMenu(){

    }
    public void makeChunggukjang(){
        System.out.println("ㅊㅓㅇ국장");
    }
    public void danjanguk(){
        System.out.println("된장국");
    }
    public void galbi(){
        System.out.println("갈비찜");
    }
}
class ChildMenu extends ParentMenu{
    // 아빠의 음식을 개편하자
    // 업그레이드 하기 위해 재편한다.
    @Override
    public void makeChunggukjang() {
        System.out.println("냄새 없는 청국장");
    }
}
```

