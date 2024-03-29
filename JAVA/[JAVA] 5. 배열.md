# 배열

```
1. 배열이란
2. 배열의 문법
3. 배열의 메모리 구조의 이해
4. 레퍼런스의 이해
5. 다차원 배열 
```



### 1. 배열이란

변수는 값을 1개만 가질 수 있지만

배열은 여러개의 변수를 가지기 때문에, 여러개의 값을 담을 수 있습니다.



### 2. 배열의 문법

배열을 선언할때, 크기를 정합니다.

최초에 한번 설정이 되면 변경이 불가능 합니다.

```Java
자료형[] name = {value,value,value}
자료형[] 이름 = new 자료형[사이즈] // 배열의 사이즈를 초기화 하지 않고 선언 만 할 수 있습니다.
  
// 배열 선언 
// 1. 
int[] arr = {10,20,30,40,50};
// 2.
int[] arr2 = new Int[5];
arr2[0] = 10;
arr2[1] = 20;
arr2[2] = 30;
arr2[3] = 40;
arr2[4] = 50;
```

##### []

이 표시가 배열을 만들겠다는 표시입니다.

##### {}

괄호 안에 `,`를 써서 여러 값을 차곡차곡 넣습니다.

##### 크기

배열은 값 만큼의 크기를 가집니다.

##### 인덱스

배열이 여러가지 값을 가지니, 그 개개인의 값을 구분할 수 있는 방법이 필요합니다.

이때는 첫번째 0 부터 ~ N-1 까지 인텍스로 구분을 해줍니다.



### 3. 배열의 메모리 구조의 이해

배열은 주소값을 가집니다. 

데이터의 값이 들어있지 않습니다.

따라서 주소값을 통해 값을 찾아갈 수 있습니다.

동일한 주소 값을 가지고 있다면 같은 데이터를 가지고 있습니다.



### 4. 레퍼런스의 이해

같은 주소를 가리키고 있는 경우, 한쪽을 바꾸면 다른 쪽도 바꿔집니다.

```java
int[] iArr = {10,20,30} // iArr 배열 생성 주소 1234 를 가진다고 가정합시다.

int[] iArr2 = iArr; // iArr2 에 iArr 를 할당하면, iArr은 배열생성 주소 1234 를 가지고 있으므로,같은 주소가 넣어집니다.

iArr2[0] = 100;
iArr2[1] = 200;
iArr2[2] = 300; 
// 주소에 있는 값 0,1,2 의 값을 변경시켰습니다. 따라서 iArr도 동일하게 가지므로, 
// iArr 의 값도 바뀝니다.
```



### 5. 다차원 배열

배열안에 배열이 들어있는 경우

주소 값이 또 주소값을 가리키는 경우를 말합니다.

쉽게 말하면,

푸르지오 아파트에는 10개의 건물이 있습니다. 공통적으로 "푸르지오아파트"이죠.

건물에는 고유의 주소가있어요. 101동 102 동

그안에 또 10층이상 방을 가지고 있습니다.

이때 10개의 동 또한 배열이고,

각 동의 10층 이상에 존재하는 집은 배열에 속하는 배열입니다. 



다차원 배열이 복잡해지면 어려워지므로, 데이터 베이스를 사용합니다.