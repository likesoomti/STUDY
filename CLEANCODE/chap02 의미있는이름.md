# Chap02. 의미있는 이름

**이름**이라..

`func`,`args`,`class`,`pkg`,`srcfile`,`dir`,`jar`,`war`,`ear`.....

엄청나게 이름을 붙힘! 

여기저기 사용되니 잘 지으면 여러모로 편하다.

이름을 잘 짓는 간단한 규칙을 알아보자!



## 1. 의도를 분명히 밝히자

변수/함수/클래스 의 이름을 봤을때 이에 대한 사용 의도가 보이게 지어야한다.

주석처리로 존재이유를 나타낸다는건 의도를 분명히 드러내지 못한것이다.

```java
// worst
int d; // 경과 시간

// best
int daysSinceCreation;
int fileAgeInDays;
```



### 의도가 들어나면 좋은 이유

아무 변수 이름이나 짓게 되면, 코드를 처음 본 사람은 어떤 일을 하는지 짐작하기 어렵다. 

코드는 보통 함축되기 때문에, 코드 맥락이 명시적으로 들어나지 않는다. 따라서 의도를 담은 이름을 사용함으로 써 함축성을 높인다.

###### example

```java
// worst
public List<int[]> getThem(){
  List<int[]> list1 = new ArrayList<int[]>();
  
  for(int[] x : theList)
   if(x[0]==4)
   	list1.add(x);
  return list1;
}

//best
public List<int[]> getFlaggedCells() {
  List<int[]> flaggedCells = new ArrayList<int[]>();
  for(int[] cell : gameBoard)
   if(cell[STATUS_VALUE]== FLAGGED)
   	flaggedCells.add(x);
  return flaggedCells;
}

// more best -> int 배열 대신 '칸'을 간단한 클래스로 나타내기
public List<Cell> getFlaggedCells() {
  List<Cell> flaggedCells = new ArrayList<int[]>();
  for(Cell cell : gameBoard)
   if(cell[STATUS_VALUE]== FLAGGED)
   	flaggedCells.add(x);
  return flaggedCells;
}


```



## 2. 그릇된 정보를 피하자

- 헷갈리는 변수 이름을 사용하지 말자!

  `hypotenuse` 의 약어로 `hp`를 사용할 수 있지만, `unix` 플랫폼을 나타내는 예약어 이기 떄문에 헷갈릴 수 있다.

- 여러 그룹을 계정으로 묶을 때 List 가 아닌 이상 List 를 사용하지 말자. 

  동적배열에 쓰는 명칭이므로 헷갈릴 수 있다. Accounts 로 대체하자.

- 흡사한 이름을 사용하지 말자

  한 모듈에서 `XYZControllerFor`을 사용하고, 다른 모듈에서 `XYZControllerForEff` 를 쓰면 비슷하므로 헷갈린다. 나중에 까먹어서 모르다. 

- 이름 몇자 입력 후 핫키 조합을 누르면 개념 차이가 들어나는데, 십중팔구 개발자들은 상세히 안보고 이름만 보고 객체를 선택한다..! 그러므로 헷갈리지 않게 짓자.



### 3. 의미있게 구분해라

- 불용어를 쓰지 말자 (a1,a2...)

  ​

### 4.  발음하기 쉬운 이름을 사용해라.

- 괜히 줄여서 발음하기 힘든 `genymdhms`를  사용하지 말자. 의미 파악도 힘들고 코드리뷰할때 부르기도 힘들고 헷갈린다. 

### 5. 검색하기 쉬운 이름을 사용해라.

- 문자를 하나 사용하면 검색하기 짱 힘들다. 이름 길이는 범위 크기에 비례해야 한다.
- 1000개이상의 파일이 있는곳에서 `a`라고 짓는게 쉬울까? 아님 `WORK_DAYS_PER_WEEK`가 쉬울까?ㅎㅎ

### 6. 인코딩을 피해라

- 이름에 인코딩할 유형,범위까지 넣으면 해독하기 어렵다

#### 헝가리식 표기법

- 옛날에는 컴파일러가 타입을 점검하지 않았기 때문에 프로그래머가 타입을 기억했어야했다(웩). 
- 요즘은 컴파일러가 타입을 기억하고 강제한다. 따라서 타입을 굳이 쓰지 않아도 된다.

###### example

```java
PhoneNumber phoneString // worse
```

#### 멤버 변수 접두어 

- `m_` 하는 접두어 필요 . 구닥다리 코드다

#### 인터페이스 클래스와 구현 클래스

- ShapeFactory 클래스와 인터페이스를 만들경우? 구분하기 위해 만들지만, 인코딩 할시 자유다.
- 하지만 IShpaeFactory 는 구닥다리. ShapeFactoryImp 식으로 만들어라 



### 7. 자신의 기억력을 자랑하지 마라

- 문자 하나만 사용하는 변수는 문제가 있다. 
- 프로그래머는 똑똑하지만, 전문가 프로그래머는 **명료** 하다.

### 8.클래스이름 

- 명사,명사구가 적합하다.



### 9.메서드이름

- 동사,동사구가 적합하다.
- 앞에 접근자,변경자,조건자에 따라 get/set/is 를 붙힌다.



### 10. 기발한 이름은 피해라 

- 특정 문화에서만 사용하는건 재밌지만, 피해라. 의도를 분명히 하고 솔직하게 표현하는게 좋다.

### 11. 한 개념에 한 단어를 사용하라

- 추상적인 개념에 단어 하나를 선택해 하나를 고수하라. 
- 메서드 이름은 독자적이고,일관적이여야 주석을 보지 않고도 올바른 메서드를 선택할 수 있다.
- 동일 코드에 controller,manager,driver 을 쓰면 혼란스럽다. 일관성 있는 어휘코드를 쓰자

### 12. 말장난을 하지 마라

- 위와 모순적일수 있지만, 비슷한 기능이지만, 같은 맥락이 아니면 다른 단어를 사용하자
- add 라 불리우는 메서드는 항상 2개를 더하거나 새로운 값을 만든다고 가정
- 새로 만드는 메서드는 집합에 값 하나를 추가하는 메서드.
- add 의 의미는 통하지만, 이전 add 메서드와 맥락이다름. 이런 메서드는 다른 유의어를 사용하자 

### 13. 해법 영역에서 가져온 이름을 사용해라.

- 코드를 읽는 사람도 프로그래머이다. 
- 모든 이름을 문제 영역에서 가져오는것은 현명하지 못하다.
- 기술 개념에는 기술 이름이 가장 적합하다. 

### 14. 문제 영역에서 가져온 이름을 사용해라

- 적절한 용어가 없을시에는, 문제 영역에서 이름을 가져오면 전문가에게 의미를 물어 파악할 수 있다.
- 해법 영역과 문제 영역을 구분할 줄 알아야한다.
- 문제영역 : 클라이언트가 원하는 문제인듯..? (마켓컬리 닭가슴살 내역) 해법:(모델에 데이터를 빼오는 방법)

### 15. 의미있는 맥락을 추가해라.

- 스스로 의미가 분명한 이름은 별로 없다

- 따라서 클래스,함수,이름에 공간을 넣어 맥락을 부여한다

- 모두 별로면 접두어를 붙힌다.

  ###### example

  - `firstName` 만 있으면 뭔지모름
  - `firstName` `lastName` `street` `houseNumber` `city` `state` `zipcode` 이 있으면 맥락 오케이

- 접두어를 추가해  `addFirstName` 이라고 지칭되면 구조적 부분도 알려줄 수 있다.

##### 맥락이 불분명한 변수

###### example

함수 이름이 일부 맥락만 제공하고, 알고리즘이 나머지 맥락을 제공하는데, 메서드만 훑어서는 세 변수의 의미가 불분명하다.

```java
private void printGuessStatistics(char candidate, int count){
 String number;
 String verb;
 String pluralModifier;
  
  if(count==0){
    number="no";
    verb = "are";
    pluralModifier ="s"
  }else if( count ==1){
    number = "1";
    verb ="is";
    pluralModifier = "";
  }else{
    number = Integer.toString(count);
    verb = "are";
    pluralModifier = "s";
  }
  
  String guessMessage = String.format(
  " There is %s %s %s",verb,number,candidate,pluralModifier
  );
  
  print(guessMessage)
}
```

함수가 기니, 작은 조각으로 쪼갰다.

세 변수를 클래스에 넣어, 만들면 변수의 맥락이 분명해진다.



##### 맥락이 분명해진 변수

```java
public class GuessStaticsMessage {
  // 클래스에 변수를 속하게 함으로서 확실하게 
  String number;
  String verb;
  String pluralModifier;
  
  // 함수를 쪼개 잘 보이게 한다.
  public String make(char candidate, int count){
  	createPluralDependentMesssageParts(count);
    return String.format(
    " There is %s %s %s",verb,number,candidate,pluralModifier
    );
  }
  private void createPluralDependentMesssageParts(int count){
    if(count==0){
      number="no";
      verb = "are";
      pluralModifier ="s"
    }else if( count ==1){
      number = "1";
      verb ="is";
      pluralModifier = "";
    }else{
      number = Integer.toString(count);
      verb = "are";
      pluralModifier = "s";
    }
  }
}

```

### 16. 불필요한 맥락을 없애라

- 맥락을 분명하게 한다고 해서 다 열거하는건 불필요하다
- 고급 휘발유 충전소라는 어플을 만든다고해서 , 모든 클래스 이름을 GSD로 하면 안된다.
- 검색했을때 다나오쟈나.
- 의미가 분명한 경우 짧은 이름이 훨씬 좋다
- ​