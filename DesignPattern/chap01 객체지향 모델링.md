# chap01 객체지향 모델링

```
1. 모델링
2. UML
3. 클래스 다이어그램
```

책 자체가 너무 딱딱하다. 쉽고 내마음에 맞게 설명하기 

## 1. 모델링

일반적으로 모델 하면 패션 모델/ 자동차 모델 등 여러 모델이 이 떠오르는데요. 패션 모델의 역할을 분석해보면

1. 패션 모델은, 패션 디자이너가 지시하는 바 (= 무대라던지,연출)에 대해 이해하고, 그에 대해 응용하여 옷을 표현합니다.
2. 자신의 패션에 이해를 통해 자신의 옷의 특징과 장점을 소비자에게 부각시킬 수 있어야 합니다.



소프트웨어에도 **모델** 이 존재하는데요.

##### 소프트 웨어 모델을 통해

1. **서로의 해석을 공유해 합의를 이룰 수 있습니다.**
2. **앞으로 개발할 소프트웨어의 원하는 모습을 가시화 하는데 큰 도움을 줍니다.**
3. **소프트웨어 시스템의 구조와 행위를 명세할 수 있습니다**
4. **시스템의 틀과 구축된 소프트웨어의 문서화 기능을 제공합니다.**



#### 소프트웨어 모델 만들기

소프트웨어의 모델은  **추상화** 가 중요합니다.

소프트웨어 모델은 그 해당 모델에서 필요한 "특정한 관점에 관련있는 점" 만 부각시킵니다.

학생 을 예로 들어봅시다.



##### 학사 정보 시스템을 만들 경우 

학생의 머리카락 수 , 나이, 학사 지원과는 전혀 관련이 없으므로 모델링 대상에서 제외됩니다.

"학사 정보 시스템과 관련이 있는" 학생의 **학번, 수강과목, 지도교수, 전공** 만을 부각 시킵니다.



### 2. UML (= Unified Model Language)

소프트웨어에서 모델링을 할 때 사용하는 언어입니다. 예전에는 굉장히 여러개의 모델 언어가 있었는데, 모델 언어별로 명세가 달라 언어 마다 해석해야되는 점이 달라 의사소통을 원활히 하자! 해서 이루어진 표준 통합 모델링 언어입니다.

UML 을 사용하여 프로그램 모델을 표현하여 더욱 쉽게 모델링을 제작하고, 이해할 수 있게 되었습니다. 

UML 을사용해 여러 도면을 그리는데요, 이 도면을 **UML 다이어그램**이라고 합니다. 다이어그램 종류는 여러가지가 있습니다.

##### UML 다이어그램 종류

```
구조 다이어그램
- 클래스 다이어그램
- 객체 다이어그램
- 복합체 구조 다이어그램
- 배치 다이어그램
- 컴포넌트 다이어그램
- 패키지 다이어그램
행위 다이어그램
- 활동 다이어그램
- 상태 머신 다이어그램
- 유즈 케이스 다이어그램
- 상호작용 다이어그램	
	= 순차 다이어그램
	= 상호작용 개요 다이어그램
	= 통신 다이어그램
	= 타이밍 다이어그램
```



### 3. 클래스 다이어그램

객체지향의 모델링의 꽃 클래스 다이어그램을 알아보겠습니다.

클래스 다이어그램은 

1. 시스템의 정적인 면을 나타냅니다.
2. 시스템을 구성하는 클래스들과, 
3. 그들 사이의 관계를 표시해줍니다. 

#### 1. 클래스

클래스를 보는 두가지 관점이 있습니다.

1. **동일한 속성과 행위를 수행하는 객체의 집합** 입니다. 
2. 객체를 생성하는 **설계도**입니다.

간단하게, 지식인이라던지, 게시판에 글을 쓴다고 생각합시다. 

보통의 게시판에는 **제목, 내용, 첨부파일** 과 같은 속성과 **등록/수정** 이라는 행위가 있습니다. 

내가 원하는 글제목,내용을 쓰고 **등록** 을 하게되면, 글이 생성됩니다. 

다시 하나의 글을 쓴다고 해서 글이 덮어씌워지지않고, 고유의 한 값으로 구분이 됩니다. 

하나의 등록된 게시글은 **객체** 이고, 게시판의 속성과 행위는 **설계도** 라고 생각할 수 있습니다. 



대충 게시판 코드를 짜보면

```java
public class Board {
    private String title;
    private String content;


    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void readContent(){
        System.out.println(content);
    }
}

```

에 게시글 2개가 등록되었다고 가정합시다.



```java
public static void main(String[] args) {
        Board b1 = new Board("에어팟 사고싶다","왤케비싸 29개월할부하고싶다");
        Board b2 = new Board("꿀호떡 맛있게 먹는법","호떡을 호떡호떡먹는다");
  
  		b1.content(); // 왤케비싸 29개월할부하고싶다
        b2.content(); // 호떡을 호떡 호떡 먹는다.

}
```

같은 속성과 기능을 가지지만, 다른 내용과 글이 탄생한 걸 볼 수 있습니다. 



#### 클래스를 UMl 다이어그램으로 만들기

![Alt text](http://cfile5.uf.tistory.com/image/191CFB234B8F2F930A338A)]



![Alt text](https://image.slidesharecdn.com/uml-090717134723-phpapp02/95/uml-18-728.jpg?cb=1305236286)

크게 세 부분으로 나누어 클래스를 표현하는데요

1. 상단에는 클래스 이름을 적어줍니다
2. 중간에는 클래스 데이터 (변수) 를 나타내줍니다.
3. 마지막에는 기능(메서드)를 나타내 줍니다.

##### 클래스의 속성 :이름,타입,다중성 정보,초기값 등을 나타냅니다 

##### 클래스 연산: 이름,인자,인자타입,반환 타입 등을 나타냅니다.

사진을 보면, 중간 데이터와 마지막 기능 부분 왼쪽에 **+** 로 되어있는 것을 볼 수 있습니다. 이것은 

데이터와 기능이 외부에 어느정도 공개되는지를 표시하는건데요. 자바를 아시는분이라면 어떤 **접근제어자** 를 사용하는지를 나타낸다고 하면 알 수 있을 것 같습니다.

클래스의 속성은 크게 4가지가 있습니다.

1. public : 모든 객체에서 접근 가능
   - **+** 로 표시합니다.
2. private : 이 클래스에서 생성된 객체만 접근 가능
   - **-** 로 표시합니다.
3. protected : 이 클래스와 동일한 패키지나,상속 관계에 있는 하위 클래스의 객체들만 접근 가능
   - **#** 로 표시합니다.
4. Package : 동일 패키지에 있는 클래스의 객체들만 접근 가능 
   - **~** 로 표시합니다.



이런 속성, 연산에 대한 정보를 항상 표시하는건 아닙니다.

클래스 다이어그램은 "단계" 에 따라 강조하는 부분이 다릅니다. 

1. **분석** 단계에서는 어떤것을 속성으로 하는지가 중요합니다. 따라서 이때는 반환타입,인자타입,목록 등이 생략 될 수 있습니다.
2. **설계** 단계에서는 구체적인 정보, 가시화 정보를 기술하는 것이 일반적입니다.

첫번째 사진이 분석단계 클래스, 두번째 사진이 설계 단계 클래스 입니다.



#### 1. 관계 

시스템은, 클래스와 클래스가 서로 긴밀한 **관계**를 맺어 수행합니다. 따라서 관계 또한, 클래스의 정보 만큼 자세히 표현 되어야 합니다.

##### 관계의 종류

```
1. 연관 관계   
2. 일반화 관계 
3. 집합 관계  
4. 의존 관계  
5. 실체화 관계  
```

1. 연관 관계

   클래스들이 개념상 서로 연결되어있을때 **연관 관계** 라고 합니다.

   실선이나 화살표로 표시하고, 제공하는 기능을 명시합니다.

   서로 연관이 있을 경우는, 화살표를 사용하지 않습니다.

   ##### 관계 다중성

   관계 화살표 사이에 숫자를 써서 표현할 수 있는데, 연관 관계에 연결된 수를 표현합니다. 

   ##### 단방향 연관관계

   모든 관계가 서로 존재를 알 필요는 없습니다. "`학생`이 `과목`을 수강한다"의 경우 

   `학생` 은 자신이 수강하는 과목을 알지만,

    `과목` 은 자신이 수강하는 학생의 존재를 모릅니다.

   한쪽으로만 방향성이 있는 관계를 **단방향 연관 관계** 라고합니다.

   ##### 다대다 연관 관계

   양쪽의 클래스가 여러명이 수강하는 경우, 다대다 연관관계라고 한다.

   다대다 연관관계는 자연스럽게 양방향 연관 관계가 된다. 이걸 구현하는 것은 복잡하므로, 연관 클래스를 하나 더 만들어 다대 다 연관 관계로 구현하고, 이런 연관 클래스는 점선으로 구현한다. 이 연관클래스 안에는 두 클래스의 객체가 존재해야지 가치가 있을 경우, 사용한다.

   * 방향과 다대다는 다름! 헷갈리지 말것! 

   ##### 재귀적인 연관일 경우

   직원의 경우, 관리자이지만 사원인 경우에는 관리자,사원 둘다에 속한다. 관리자,사원에 속하는 객체는 두 클래스에 속하는 모순적인 상황이 생긴다. 이럴때 재귀적 연관관계를 사용할 수 있다. 

   클래스를 만들지 않고, 관계를 자신에 연결한다. 

   관계가 자신에 연결되어, 관계의 루프가 생김을 방지하기 위해 연관 관계에 **계층으로** 제약을 설정해야한다.

   ###### 계층이란

   객체 사이에 상하 관계가 존재하고, 사이클ㄹ이 존재하지 않는다는 의미. `{}` 로 표시한다. 

   ​

2. 일반화 관계

   (IS-A)의 상속 관계를 표현한다.

   한 클래스가, 다른 클래스를 포함하는 상위 개념일 때, 일반화 관계가 존재한다.

   일반화 관게가 존재할때, 부모 클래스로부터 속성과 연산을 물려받을 수 있따. 

   `is a` 관계라고 한다. 

   ##### 일반화 관계 표현

   두 클래스를 연결하고, 부모의 부분에 삼각형 빈 화살표로 표현한다.

3. 집합 관계

   클래스 사이의 전체-부분 관계를 나타냅니다. 집약 관계와 합성 관계가 존재한다.

   전체와 부분의 관계를 명확하게 명시하고자 할 때 사용한다.

   집약과 합성 두 종류의 집합 관계가 존재한다. 

   ##### 집약 관계

   한 객체가 다른 객체를 포함한다. 전체와 부분의 관꼐로, 전체가 가리키는 클래스 방향에 빈 마름모로 표시한다.

   전체 객체와 부분 객체의 라이프 타임은 독립적이다.

   ```java
   public class Computer {
       private MainBoard mb;
       private CPU c;
       private Memory m;
       private PowerSupply ps;

       public Computer(MainBoard mb, CPU c, Memory m, PowerSupply ps) {
           this.mb = mb;
           this.c = c;
           this.m = m;
           this.ps = ps;
       }
   }
   ```

   집약 관계의 코드이다. Computer 객체가 생성되더라도, 외부에 new 를 한 객체가 살아있기 때문에 라이프 사이클은 독립적이다. 

   따라서 집약관계의 형태로 모델링 한다. 

   ##### 합성 관계

   부분 객체가 전체 객체에 속하는관계

   전체를 가리키는 방향에 채워진 마름모로 부분 객체를 표시한다. 

   전체 객체가 사라지면 부분 객체도 사라진다. 

   따라서 라이프 타임은 전체 객체의 라이프 타임에 의존한다.

   ```java
   public class Computer {
       private MainBoard mb;
       private CPU c;
       private Memory m;
       private PowerSupply ps;

       public Computer() {
           this.mb = new MainBoard();
           this.c = new CPU();
           this.m = new Memory();
           this.ps = new PowerSupply();
       }
   }
   ```

   합성 관계시의 코드이다. 

   컴퓨터에 관한 c1 객체가 생성되면, 내부의 클래스들이 다 생성된다. 하지만, c1 이 사라지면 모든 부품 객체들 또한 가비지 컬렉터로 사라진다. 이때 부분 객체는 라이프 타임이 전체 객체와 동일하기 떄문에, 합성 관계로 모델링을 한다. 

   ​

4. 의존 관계

   클래스가 다른 클래스에서 제공하는 기능을 사용하는 경우 사용한다. 

   ##### 의존관계의 세가지 경우

   1. 클래스의 속성에서 참조할 때
   2. 연산의 인자로 사용될 때
   3. 메서드 내부의 지역 객체로 참조될 때

   자동차를 소유한 사람이 자동차를 이용해 출근할 경우, 출근한 후 다음날에도 어제 사용한 자동차를 타고 출군한다. 

   매번 출근할 때, 다른 자동차를 사용하는 경우는 없다. 이런 경우 사람과 자동차의 관계는 연관 관계며, 

   Person 클래스의 속성으로 Car 객체를 참조한다. 

   ##### 자동차와 주유기의 관게

   자동차에 주유할 때마다, 주유기는 매번 달라질 수 있다. 

   이럴 경우 주유기를 인자나 지역 객체로 생성해 구현할 것이다.

5. 실체화 관계

   인터페이스와 이 책임을 실제로 실현한 클래스들 사이의 관계를 나타낸다.

   **책임**: 객체가 해야하는일. 해석할 수 있고, 객체가 할 수 있는 일 . 

   객체가 제공하는 외부 서비스 / 기능

   인터페이스는 이런 객체의 능력을 그룹화 할 수 있다. 

   << >> 표시를 통해 인터페이스 키워드를 명세한다 . 





## check point

##### 25

```java
public class Course {
    private String id;
    private String name;
    private Integer numOfStudents = 0;

    public void addStudent(){
        System.out.println("add Student");
    }
    public void deleteStudent(){
        System.out.println("delete Student");
    }
}

```



##### 26

```java
class Person{
    private Phone[] phones;

    public Person(){
        phones = new Phone[2];
    }
}
class Phone {

}

```

##### 27

```java
class Person{
 private Phone homePhones;
    private Phone officePhones;
}
class Phone {
}
```

##### 27 -2

다대다 연관관계 어려웡!

```java
class Professor{
    Student student;

    public void setStudent(Student student){
        this.student = student;
        student.setadvisor(this);
    }
    public void advise(){
        student.advise("상담와라....");
    }
}
class Student{
    Professor advisor;

    public void setadvisor(Professor professor){
        this.advisor = professor;
    }

    public void advise(String msg){
        System.out.println(msg);
    }

}
```

##### 28

```java

class Student {
    Course[] course;

    public Student(Course[] course) {
        this.course = course;
    }

    public void registerCourse(Course course){

    }
    public void dropCourse(Course course){

    }

}
class Course{

}
```



##### 29

```java
class Student {
    Vector<Course> course;

    public Student(Vector<Course> course) {
        this.course = course;
    }

    public void registerCourse(Course course){

    }
    public void dropCourse(Course course){

    }

}
class Course{
    Vector<Student> students;

    public Course(Vector<Student> students) {
        this.students = students;
    }
}

import java.util.Vector;

class Person{
 private Phone homePhones;
    private Phone officePhones;


}
class Phone {

    public static void main(String[] args) {
        int N = 5;
        Course[] course = new Course[N];
        Student student = new Student(course);
    }
}



class Student {
    Vector<Transscript> transscripts;
}
class Course{

    Vector<Transscript> transscripts;                       
}
class Transscript{

    Student student;
    Course course;
}
```

