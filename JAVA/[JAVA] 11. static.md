# [JAVA] 11. static

```
1. static 의 이해
2. static 과 객체와의 관계
3. static 과 장점과 단점
```



### 2. static 의 이해

필요에 의해 클래스를 만들고, 사용할 때 객체로 만들어 사용합니다.

항상 클래스를 만들어 객체를 만들면 문제가 생길 수 있습니다.



#### 문제점

아빠 지갑에 200원이 있읍니다 세명의 아이가 100원을 달라고 요청했습니다.

1. 첫째 - 아빠 지갑 클래스 객체 생성해서 100원을 받는다
2. 둘째 - 아빠 지갑 클래스 객체 생성해서 100원을 받는다
3. 셋째 - 아빠 지갑 클래스 객체 생성해서 100원을 받는다

**버그** : 지갑에는 200원밖에 없지만, 지갑을 생성해서 받아 300원을 받았습니다.

#### 해결

세명의 아이들이  **아빠 지갑을 공유**하면 됩니다.

첫째가 100원을 받고, 둘째가 100원을 받으면 아이는 못받음 ㅠㅠ



이럴때 **static** 키워드를 사용합니다!

##### 아빠 지갑

```java
public class papaPouch{
  public static int MONEY = 300;
  
  public papaPouch(){
    
  }
  
}
```

##### 첫째아이

```java
public class firstChild{
  public static int MONEY = 200;
  public firstChild(){
    
  }
  public void takeMoney(int money){
    papaPouch.MONEY -= money;
    if(PapaPouch.MONEY<0){
      sout("돈이 없어 못받아요 ㅠㅠ ")
    }
  }
}
```

##### 둘째아이

```java
public class secondChild{
  public static int MONEY = 200;
  public secondChild(){
    
  }
  public void takeMoney(int money){
    papaPouch.MONEY -= money;
    if(PapaPouch.MONEY<0){
      sout("돈이 없어 못받아요 ㅠㅠ ")
    }
  }
}
```

##### 셋째아이 

```java
public class thirdChild{
  public static int MONEY = 200;
  public thirdChild(){
    
  }
  public void takeMoney(int money){
    papaPouch.MONEY -= money;
    if(PapaPouch.MONEY<0){
      sout("돈이 없어 못받아요 ㅠㅠ ")
    }
  }
}
```

객체를 생성하지 않고, `CLASS이름.스태틱 변수`로 사용할 수 있습니다.

```java
main(){
  firstChild fc = new firstChild();
  fc.takeMoney(100);
  secondChild sc = new secondChild();
  sc.takeMoney(100);
}
```



## 2.  static 과 객체와의 관계

static 은 클래스 변수입니다.

객체는 클래스에서 생성됩니다.

하지만, static 이 붙은 변수는 클래스 변수로서, 객체가 생성되기 전에 존재합니다.

#### 메모리 영역

##### 데이터 영역

클래스들이 모여있는 곳

##### Heap 영역

new 생성자로 만들어진 영역. 객체들이 여기있다.

JAVA 는 메모리 관리를 가비지 컬렉터가 대신합니다. 

가비지 컬렉터는 heap 영역의 메모리 부분을 관리합니다. 



### 3. Static 장점과 단점

잘 사용하면 좋고, 아니면 큰 재앙을 불러옵니다.

#### 특징

1. 클래스 영역에 존재합니다.
2. 즉, 객체를 생성하지 않아도, 클래스 영역에 메모리를 가지고 있습니다.
3. 따라서 힙 영역에 메모리를 사용하지 않아도 됩니다.

#### 장점

1. 객체 생성을 하지 않고, 사용할 수 있습니다.

#### 단점

1. 가비지 컬렉터 관리 범위 밖이므로, 시스템이 오랜시간 돌아가게 되면, 시스템이 느려지다, 재앙이 올 수 있습니다.

#### 상수

대표적으로 변하지 않는 **상수**로 사용됩니다.

##### final

한번 설정하면 값이 바뀌지 않는다.

###### 원주율 같은

```java
public class PICLASS{
  public static final double PI =3.14; // final :  더이상 변경 되지 않는다. 
  
  sout(PICLASS.pI)
}
main(){
  // 악의 변경
  PICLASS.PI = 4.6; // error 
}
```

