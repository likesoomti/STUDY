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