# cannot symbol R

탭으로 만든 프래그먼트를 하고있는데 갑자기 저게떴다!



## 해결방법1

내가아는 방법은 

```
clean Project > rebuild Project
```

**근데 안되네!**

검색해봤다

## 해결방법 2

```
file> invalid Caches/Restart.. 클릭
```



**근데 안되네!2**



이후 로그 확인 

```
AAPT2 error: check logs for details
```

를 검색해보니 



## 해결방법 3

찾아보니

gradle.property에

```
android.enableAapt2=false
```

를 넣으면 해결된다고한다!





**근데 안되네!3**





그래서 단톡방에 물어봣다. 어느 고수님이 답변해주시길



## 해결방법 4

```
업그레이드 하세요 
```

그래서 업그레이드를 했다..



**근데 안되네!4**

너무 밤이 늦어 다음날 또 시작을햇따...

그래서 에러를 보니 



#### error code

```
Process 'command '/Users/Soomti/Library/Android/sdk/build-tools/27.0.3/aapt'' finished with non-zero exit value 1

```

검색했더니 타깃을 변환하라고 해서 했는데 안된다..



후 다시 마음 크게 숨쉬고 찾아보니



```
Android Studio에서 컴파일 했을 때 Gradle 오류로 
Android java.exe finished with non-zero exit value 1
메시지가 보인다면 drawable resources가 라이브러리나 모듈에서의 종속관계에서 충돌이 원인이다.
해결 방법은 Gradle 메시지에서 충될되는 drawable resource를 찾아서 지우면 해결 됨.

출처: http://kworks.tistory.com/382 [K works..]
```

이라는 고수님의 말이있었다.



내 코드는 `menu.xml` 을지우니 build gradle 성공..

하지만 아직 안잡혔따...미치겠따...