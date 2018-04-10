# 클래스 실전

```
1. 학사 관리 프로그램 만들기
2. 프로그래밍이란 결국 데이터를 효율적으로 관리하기 위한 공구
```

이전에 살펴본 문법으로 간단한 프로그래밍을 해보자.



### 1. 학사 관리 프로그램 만들기

필요한 클래스?

1. 학사 관리 시스템 클래스
   1. ​
2. 학생 정보 클래스
   1. 학생 한명한명마다 클래스가 필요할 듯 
   2. 이름, 나이, 학번, 전공, 성별 ( 인스턴스 변수 )
   3. 데이터를 언제든지 업데이트 할 수 있어야 함 (메서드)
3. 재적 학생 클래스



##### 1. 학사 관리 시스템 클래스

```java
import java.util.ArrayList;

public class StudentManager {
    ArrayList<Student> students = new ArrayList<>();
    ExpelStudent expelStudent = new ExpelStudent();

    public static void main(String[] args) {

        StudentManager manager = new StudentManager();
        manager.addStudent(26,"soomti",201301308,"computer");

        manager.addStudent(27,"DK",20125788,"computer");

        manager.addStudent(27,"hunsung",201201234,"computer");

        System.out.println(manager.students.get(0).getAge());
        System.out.println(manager.students.get(1).getName());
    }
    public void addStudent(int age, String name, int studentID, String major){
        students.add(new Student(age,name,studentID,major));
        System.out.println("학생 정보 입력 성공!");

    }
}

```

##### 2. 학생 정보 클래스

```java
public class Student {
    private int age;
    private String name;
    private int studentID;
    private String major;

    public Student(int age,String name, int studentID,String major){
        this.age = age;
        this.name = name;
        this.studentID = studentID;
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return major;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void updateInfo(int i,String info){
        switch (i){
            case 1:
                setName(info);
                break;
            case 2:
                setAge(Integer.parseInt(info));
                break;
            case 3:
                setStudentID(Integer.parseInt(info));
                break;
            case 4:
                setMajor(info);
                break;
        }
    }

}

```



##### 3. 재적 학생 클래스

```java
import java.util.ArrayList;
public class ExpelStudent {
    ArrayList<Student> expelStudent;
    public void ExpelStudent(Student student){
       expelStudent = new ArrayList<>();
    }
    public void addExpelStudent(int age,String name,int studentID,String major){
        expelStudent.add(new Student(age,name,studentID,major));
        System.out.println(" 재적 학생 정보 등록 ");
    }
}
```



### 2. 프로그래밍이란 결국 효율적으로 관리하기 위한 공구

프로그래밍의 핵심은 효율적이다. 

프로그래밍은 버그가 없이 돌아가야한다. 어떠한 로직으로 돌아갈 지 꾸준히 고민해야 합니다.

프로그래밍은 누군가가 **효율적**으로 활용할 수 있게 사용합니다.

기능 구현은 다 되었을 지라도, 속도가 느리면 쓰지 않습니다.

코딩보다 프로그래밍 구조에 대해 깊이 생각해야 하며,

리팩토링 서적을 보세요