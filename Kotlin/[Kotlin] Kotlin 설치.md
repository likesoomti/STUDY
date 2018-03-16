# [Kotlin] Kotlin 설치.md

 

꺄 시작이 반이다. 설치를 해보자!

#####  사이트 살펴보기 👀

https://kotlinlang.org/

드러가보자!

##### 레퍼런스

https://kotlinlang.org/docs/reference/

##### 실제로 실행해 볼수 있는 코드

https://try.kotlinlang.org/

등 구경하면 도움될 것들이 많다!



##### intellij idea

설치는 코틀린 만든 intellij idea 를 설치해보자!

https://www.jetbrains.com/idea/download/#section=mac

에 가서 다운로드!



맥은 디폴트로 `/usr/local/bin/idea` 에 설치가 된다. 



##### new create project

설치가 되면 자바가 없다... jdk 필요. 일단 Kotlin/JVM 으로 설치를 해보자!

프로젝트 생성 후

`src 우클릭 -> 코틀린 파일 생성`

but `jdk` 가 없어서 설치해야한다.

http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html

9 버전을 설치했다!

코틀린은 ` JVM`이 필요한데, 지금 컴퓨터에 없기 때문에 `JVM` 을 설치하기 위해 `JDK`를 설치한다.

맥의 설치 경로는 `/Library/Java/JavaVirtualMachines` 이다.



file/project constructure/sdks 에 + 버튼을 누르고 jdk 를 추가, set up jdks 버튼을 누르면 jdk가 셋팅이 된다.

이부분은 나중에 자세히 정리를 하겠다.

