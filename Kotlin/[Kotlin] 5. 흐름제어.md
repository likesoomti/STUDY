# [Kotlin] 흐름제어

### If-Else 문

자바문과 비슷합니다.

코틀린의 `if-else` 문은 값을 반환할 수 있습니다.

###### example

```kotlin
// 1. 일반 if 문
var max = a 
if(a<b) max = b

var max: Int
if(a>b){
  max = a
}else {
  max = b
}

// 2. 식으로 사용되는 if 문

val max = if(a>b)a else b

// 조건문 내부에 블록을 가질 수 있습니다.
// 마지막 리턴 값이 변수에 들어가 출력이 됩니다.
val max = if (a>b){
  print("Choose a")
  a
  // 리턴 값이 없지만 할당이 된다.
}else {
  print("Choose b")
  b
}
// 3. 삼항 연산자가 존재하지 않습니다.
//    식으로 쓰면 되잖아여?!
```



### When 문

switch case 문을 대체하는 조건문입니다.

`break`로 구분하는 자바와 달리, 코틀린은 중괄호로 구분합니다. 한줄로 작성하면 중괄호를 생략할 수 있습니다.

숫자,문자만 들어가는 switch 와 달리, 모든 값이 들어갑니다. else는 필수이나, boolean같은 경우는 생략이 가능합니다.

조건에 맞는 값을 바로 반환합니다. 

여러개의 조건을 사용하는 경우 `,` 를 사용하면 됩니다.

range 나  collection 에 in 이나 !in 으로 범위 등을 검사할 수 있습니다.

타입 도 검사할 수 있습니다. 스마트 캐스트가 적용됩니다. 

인자가 없으면 논리 연산으로 처리됩니다. (`if-else` 와 동일 됨)

###### example

``` kotlin
// 인자가 맞는지 체크한다. 
when(x) {
  1 -> print("x == 1")
  2 -> print("x == 2")
  else -> {
    print("no1 no2")
  }
}
// 식으로 사용되는 when 
var res = when (x) {
  100 -> "A"
  90 -> "B"
  80 -> "C"
  else -> "F"  
}

// 
when(x:Any){
  0,1 -> print("x ==0 x ==1")
  // 다양하게 식도 쓸수있고, 함수도 쓸수 있음
  parseint(x) -> print("gg")
  1+2 -> print("hihi")
  // 스트링과 비교하는 동시에 맞다면, 스마트 캐스트가 되어 적용 됩니다.
  is String -> x.startsWith("prefix")
  
  else -> print("otherwise")
}
```



### For Loops

코틀린에서는 `for-each` 형태만 지원합니다.

iterator를 제공하는 모든것을 반복할 수 있습니다.

###### example

```kotlin
for(item in collection)
  print(item)
```

index 를 사용하고 싶으면 `.indices`,`withIndex()`  를 사용합니다.

###### example

```kotlin
val array = ArrayOf("a","b","c")
// just 출력할때 
for(i in array.indices){
  println("$i ${array[i]}")
}

// withIndex() 를 이용할 경우 
// index 를 사용할 경우 withIndex()를 사용하는게 더 좋습니다.
for((index,value) in array.withIndex()){
  println("$index ${value}")
}

```

### Range

`range`는 코틀린에만 있는 독특한 구조입니다.

해당 범위 내에 특정 항목이 포함되어 있는지 확인할 때 사용합니다.

 `..` 연산자를 사용하여 정의합니다.

인덱스 순환을 위한 범위를 생성하는 경우에는 until 함수를 사용하면 마지막 값을 포함하지 않은 범위를 생성합니다.

범위 내에 특정 항목이 있는지 알고싶다면 in 을 사용합니다.

항목들 순서가 반대로 정렬된 범위를 생성하려면 `downTo()` 함수를 사용합니다.

감소폭의 default 는 1이지만, `step()` 함수를 사용하면 감소폭을 조정할 수 있습니다.

###### example

```kotlin
val myRange : IntRange = 0..3 // (0,1,2,3)
val untilRange : IntRange = 0 until 4 (0,1,2,3)

// in 사용하기
// 5가 myRange 에 있는지 확인한다.
val foo : Boolean = 5 in myRange

// downTo() -> 54321 순으로 간다.
for (i in 5 downTo 1){}

// 531 출력
for (i in 5 downTo 1 step 2){}
```



### while Loops

`while`, `do - while` 은 자바와 같습니다.

###### example

```kotlin
while(x>0){
  x--
}do{
// do 에서 변수를 선언할 수 있습니다.
  val y = retriveData()
}while(y != null)
```

