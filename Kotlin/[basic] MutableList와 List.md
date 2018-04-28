# MutableList 와 List 에 대해서 헷갈렸던 점 

코틀린 컬렉션에는 수정이 불가능한 List 와 MutableList 가 있습니다.

MutableList 는 수정이 가능한 리스트고,

List 는 수정이 불가능한 리스트입니다.



커니의 코틀린에 대해서 이런 예시가 나옵니다. 

```kotlin
fun main(args: Array<String>) {
    println("hello world")

    // 수정 불가 리스트
    val immutableList: List<String> = listOf("Lorem","a","b")
   // immutableList.add("amet") //error
   // 수정 가능 리스트
    val mutableList: MutableList<String> = arrayListOf("a","b","c")
    mutableList.add("hi")
    // 재할당 시에도, 지원 불가 
    val immutableList2: List<String> = mutableList
//    immutableList2.add("g") // error
 }
```



수정 가능한 리스트를 List<String> 으로변환 시 수정 불가능한 리스트가 됩니다. 



이부분은 이해가 되었는데, 반대로 List를 MutableList로 바꿨을 경우에러가 나서 왤까? 많은 생각을 했습니다.

```kotlin
/*
* 2.2 컬렉션
* 자료의 가변 / 불변
* */
fun main(args: Array<String>) {

    //자료를 수정할수 없는 리스트
    val immutableList1: List<String> = listOf("hello", "word", "hey")

    val list2: MutableList<String> = (immutableList1 as MutableList<String>);

    // error
    list2.add("num");
   
}
```

다형성의 개념의 부족으로 클래스를 바꿨지만 왜 안될까 고민을 했었는데

원인은 **listOf** 에 있었습니다.

MutableList 는 List 를 상속받습니다.

List 는 Collections 을 상속받고 그 안에 `listOf()`  와 `arrayListOf()` 함수가 존재합니다.

listOf 자체가 수정 불가능하기 때문에, 하위  MutableList 에서 사용한다 해도, 할당이 불가능하므로 에러가 났던거였습니다.

다형성의 개념은 중요한듯..



```kotlin
/*
* 2.2 컬렉션
* 자료의 가변 / 불변
* */
fun main(args: Array<String>) {

    //자료를 수정할수 없는 리스트
    val immutableList1: List<String> = ArrayListOf("hello", "word", "hey")

    val list2: MutableList<String> = (immutableList1 as MutableList<String>);

    // error
    list2.add("num");
   
}
```

ArrayListOf 로 바꾼 결과 잘 실행됩니다.! 삽질끝