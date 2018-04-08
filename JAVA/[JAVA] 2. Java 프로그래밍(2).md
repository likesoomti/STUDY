# [JAVA] JAVA 프로그래밍 -2

```
https://www.inflearn.com/course-status-2/ 의 강의 2. Java 프로그래밍 정리한 글입니다.
```

### 변수 = 변하는 수 

![Alt text](img/java2_1.jpg)

​		변수는 요술주머니입니다 ! 모든 데이터를 담을 수 있는 것입니다. 보통 크기와 타입으로 분류됩니다.



### 변수 선언하기

```java
타입 name; // 변수를 선언합니다
or
타입 name = 넣을 값; // =는 오른쪽에 있는 값을 왼쪽 선언한 변수에 넣어줄 수 있습니다.
```

##### 타입

변수는 보통 크기/종류로 분류되는데요. 

1. 숫자
2. 문자
3. 객체(= 여러 값을 한번에 가집니다.)

로 나눠집니다

##### name

변수를 부를 이름을 말합니다. 

##### = 

값을 변수에 넣어줍니다. 오른쪽에 값을 왼쪽의 변수 이름에 넣어줍니다.

###### example

```java
int a = 1; // int(정수를 저장하는 자료형)을 a로 이름을 짓고 거기 1을 할당해라
char b = 'a'; // char(문자를 저장하는 자료형)을 b로 이름을 짓고'a'값을 넣어라
```



##### 왜 이름을 짓죠?

갖다 써야되니까요! 

```java
public class programming {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        char c = 'a';
        char d = 'b';

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

    }
}

// run 
10
20
a
b
```

#####  System.out.println();

() 안에 있는 값을 출력해주는 치트키(?) 입니다.

저 안에 변수를 넣어 저렇게 출력할 수 있습니다!



#### 주의할 점

![Alt text](img/java2_2.jpeg)

세미콜론..꼭 잊지마세요



## 기본 자료형

같은 자료형임에도, 크기로 나눕니다.

1 을 저장하는애랑 1,000,000,000,000,000 을 저장하는 애랑 같이 사용하면 공간 낭비라고 생각이 되어 

알맞게 저장하기 위해서 자료형 크기를 나눴습니다. 

보통 종류중에 가장 기본적으로 사용되는 자료형이 있는데요. default 를 붙혔습니다.

실수형

1. float (4byte)
2. double (default)

정수형

1. byte (1byte)
2. short (2byte)
3. int (default) (4byte)
4. long (8byte)

문자형

1. char (2byte)

논리형

1. boolean (1bit)

### 참조 자료형

1. Array
2. String
3. ArrayList



### 형변환

##### 명시적 형변환

큰 크기를 가진 자료형을 같은 종류의 작은 크기의 자료형에 옮길 경우에 사용합니다.

코드에 명시해 주어야 합니다.

```java
double d2 = 10.9;
int i1 =(int)d2; // 실수 자료형이 정수 자료형으로. 이때 소수점은 잃어버리게 됩니다.
```

##### 묵시적 형변환

작은 크기를 가진 자료형을 큰 크기의 자료형으로 옮길때는 굳이, 명시를 안해줘도 가능합니다.

```java
int i1 = 10;
double d3 = i1; // 문제 없음
```

