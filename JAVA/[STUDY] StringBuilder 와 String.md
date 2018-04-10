# [STUDY] StringBuilder 와 String 

## Problem

java 알고리즘을 풀다가, 시간 초과가 떴다.



이때 내가 접근한 방법은



1. scanner 를 사용하지 않고, BufferedReader 를 이용한 글 입력

이었는데, 이럼에도 속도 초과가 나왔다. 이래서 검색하다 찾은것디



**StringBuilder** 를 사용하세요. 라는 대답이었다



## String

문자열을 대표한다. 문자열을 조작할 때 유용하게 사용한다.

문자열을 +를 활용해 합치는 경우, String 인스턴스를 생성하는 방식이였어요. 이런 성능 이슈를 개선하기 위해 JDK 1.5 버전 이후에는 컴파일 단계에서 StringBuilder로 컴파일 된다.

## StringBuilder,StringBuffer

StringBuffer, StringBuilder의 차이는  동기화 지원 여부이다.

두 클래스가 지원하는 메서드는 같지만, StringBuffer는 각 메소드 별로 synchronized keyword가 존재하죠. 즉, 멀티 쓰레드 상태에서 동기화를 지원한다.

연산이 많은 경우(?) String < StringBuffer < StringBuilder

1. **String** 객체는 불변이기 때문에 변하지 않는 문자열은 String을 사용한다.
2. **StringBuilder**는 비동기방식이기 때문에 Single Thread 환경하에서, 변화되는 문자열의 사용한다.
3. **StringBuffer **동기방식으로 저장되기 때문에 멀티쓰레드로 접근하거나 문자열이 변경될 경우에 사용한다.