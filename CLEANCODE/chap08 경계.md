# chap08 경계

소프트웨어를 개발하면서, 패키지를 사고, 오픈 소스를 이용하는 경우가 있다.

외부 코드를 우리 코드에 깔끔하게 통합 해야만 한다.



### 1. 외부 코드 사용하기

- 인터페이스 제공자: 적용성을 최대한 넓히려고 애쓴다.
- 인터페이스 사용자 : 자신의 요구에 집중하는 인터페이스를 바란다.

이러한 입장 대립으로 인해, 시스템 경계에서는 문제가 생길 소지가 많다.



###### example - java.util.MAP

java.util.Map 은 다양한 메서드를 제공한다. 

```java
- clear() void - map // 누구나 마음만 먹으면 Map 내용을 지울 수 있다..
- containsKey(Object key)boolean - map
- containsValue(Object value) boolean - map
- entrySet() set - map
- equals(Object o) boolean - map
- get(Object key) Object - map
- getClass() Class<? extends Object> - Object
- hashCode() int - map
- isEmpty() boolean - map
- keySet() Set - map
- notify() void - Object
- notifyAll() void - Object
- put(Object key, Object value) Object - map
- putAll(Map t)void - map
- remove(Object key) Object - Map
- Size() int - Map
- toString() String - Object
- values() Collection - Map
- wait() void - Object
- wait(long timeout) void - Object
- wait(long timeout, int nanos)void - Object
```

마음만 먹으면 사용자는 어떤 객체 유형도 추가할 수 있다.

##### Sensor 라는 객체를 담는 MAP

```java
Map sensors = new HashMap();

// Sensor 객체가 필요한 코드
Sensor s = (Sensor)sensors.get(sensorId); // 이런 코드가 여러차례나옴. 깨긋한 코드 X

// 가독성을 높이기
MAP<String,Sensor> sensors = new HashMap<Sensor>(); // but 필요하지 않은 기능까지 제공한다. 
...
Sensor s = sensors.get(sensorId);
```

프로그램에서 `MAP<String,Sensor>`를 여기 저기 넘긴다면, 수정할 코드가 상당히 많아진다.



##### MAP을 좀 더 깔끔하게 사용한 코드

경계 인터페이스인  MAP 을 sensor 안으로 숨긴다

```java
public class Sensors {
    private Map sensors = new Hashmap();
    public Sensor getById(string id) {
        return (sensor) sensors.get(id);
    }
}
```

 MAP 을 sensor 안으로 숨김으로써 , 나머지 프로그램에는 영향을 미치지 않는다. 

유사한 경계 인터페이스를 사용할 경우, 여기저기 넘기지 말아라

경계 인터페이스를 이용할 때는 이를 이용하는 클래스나, 클래스 계열 밖으로 노출시키지 않도록 주의한다.



### 2. 경계 살피고 익히기

외부 라이브러리를 가져왔으나, 사용법이 분명하지 않은 경우  : 문서를 읽으며 사용법을 결정한다.

간단한 테스트 케이스를 작성해 외부 코드를 읽힌다.

###### sample - log4j 익히기

코드를 만들어가면서 에러를 발견하고, 돌아가는 방식을 익힌다

모든 지식을 독자적인 클래스로 캡슐화 하여,  나머지 프로그램이 경계 인터페이스를몰라도 되게 한다.



### 3. 학습 테스트는 공짜 이상이다.

학습 테스트는 이해도를 높여주는 정확한 실험이다.

실제 코드와 동일한 방식으로 인터페이스를 사용하는 테스트 케이스가 필요하다.

경계 테스트가 존재하면, 패키지의 새 버전으로 이전하기 쉬워진다.



### 4. 아직 존재하지 않는 코드를 사용하기

아는 코드와 모르는 코드를 분리하는 경계의 유형이 존재한다.

##### "지정한 주파수를 이용해 이 스트림에서 들어오는 자료를 아날로그 신호로 전송하라"

1. 다른 팀이 구현 방법을 설계하지 않아 방법을 모르는 상황이다.
2. 따라서 자체적으로 인터페이스를 정의한다. (클래스 다이어그램 만들기)

인터페이스를 전적으로 통제할 수 있다.

코드 가독성이 높아지고 코드 의도도 분명해진다.



### 5. 깨끗한 경계

소프트웨어 설계가 우수하다면 변경하는데 많은 투자와 재작업이 필요하지 않다. 

1. 경계에 위치하는 코드는 깔끔히 분리한다.
2. 기대치를 정의하는 테스트 케이스도 작성한다.
3. 이쪽 코드에서 외부 패키지를 세세하게 알 필요가 없다
4. 통제가 불가능한 외부 패키지에 의존하는 대신, 통제 가능한 코드에 의존하는게 훨씬 좋다.
5. 외부 패키지 호출하는 코드를 줄여 경계를 관리하자. 
6. `Adapter` 패턴을 사용하거나, 새로운 클래스로 경계를 감싸자.