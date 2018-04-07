# [Kotlin] 컬렉션

코틀린 컬렉션은, 기본형 타입과 다르게 자바에서 제공하는 클래스들을 그대로 사용합니다. 

 별칭을 사용하여 컬랙션 내 다른 클래스와의 일관성을 유지합니다.



코틀린에서는 컬렉션 내 자료 수정 가능 여부에 따라 컬렉션 종류를 구분합니다. 새로운 타입을 선언하는게 아닌 인터페이스를 통해 사용 가능한 함수를 제한하는 방식으로 구현됩니다.

- 코틀린은 수정 가능 / 불가능 여부에 따라 컬렉션의 종류를 구분합니다.
- 자바는 `list`,`collection`,`iterable`  에 각각 자료를 수정/조회 할 수 있는 메서드가 포함되어있습니다.
- 코틀린 에서는 자료를 조회하는 함수만 포함이 되어있고, 수정을 하고 싶다면 `Mutable~ ` interface 를 상속하여 수정 가능하게 변경합니다.
- `Set`,`Map`도 동일한 규칙이 제공됩니다. 
- 코틀린은 수정 가능 여부를 제한할 수 있지만, 자바로 작성한 코드에서 코틀린 함수를 호출 시, 코틀린에서 제공하는 인터페이스는 자바에서 제공되는 인터페이스로 변환되기 떄문에, 제한이 풀어짐으로 리스트 내 자료를 모두 수정할 수 있게 됩니다.



코틀린은 컬렉션을 쉽게 생성하는 함수를 제공합니다. 

| 함수명           | 자료수정여부 | 반환타입(실제타입)                               |
| ------------- | ------ | ---------------------------------------- |
| listOf()      | x      | kotlin.collections.List                  |
| arrayListOf() | O      | kotlin.collections.ArrayList(java.util.ArrayList) |
| setOf()       | x      | kotlin.collections.Set                   |
| hashSetOf()   | O      | kotlin.collections.HashSet(java.util.HashSet) |
| linkedSetOf() | O      | kotlin.collections.LinkedHashSet(java.util.LinkedHashSet) |
| sortedSetOf() | O      | kotlin.collections.TreeSet(java.util.TreeSet) |

##### listOf() : 데이터를 일렬로 할당한다.

##### arrayListOf() : listOf 와 동일하나, 수정 가능하게 할당한다. 

###### example

```kotlin
fun main(args: Array<String>) {
    println("hello world")

    // 수정 불가 리스트
    val immutableList: List<String> = listOf("Lorem","a","b")
//    immutableList.add("amet") //error
   // 수정 가능 리스트
    val mutableList: MutableList<String> = arrayListOf("a","b","c")
    mutableList.add("hi")
    // 재할당 시에도, 지원 불가 
    val immutableList2: List<String> = mutableList
//    immutableList2.add("g") // error
 }
```

### 컬렉션에서 자료 접근하기

자바에서는 컬렉션 특정 항목에 접근시 get/set 메서드를 사용합니다.

코틀린에서는 배열에 접근하는 방법과 동일하게 항목에 접근할 수 있습니다.

맵의 경우, 숫자 인덱스 대신 키 값을 넣어 접근할 수 있습니다.

###### example

