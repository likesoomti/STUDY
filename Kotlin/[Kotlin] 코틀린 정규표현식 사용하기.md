# 정규표현식 사용하기

```
String 체크 
```

```kotlin
fun emailCheck(email:String):Boolean {
    val regex = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$/i;".toRegex()
        return !regex.containsMatchIn(email)
}
```

