# chap07 오류처리

오류처리는 프로그램에 반드시 필요한 요소이다.

오류를 잡아야 깨끗한 코드가 된다.

오류코드가 흩어지게 되면, 코드가 하는 일을 파악하기 어렵다



### 1. 오류 코드보다 예외를 사용해라

######  DeviceController - bad

```java
// bad
// 밑과 같이 짜면, 함수를 호출한 즉시 오류 코드를 확인해야 한다. 
// 오류가 발생하면 예외를 던지는 편이 호출자 코드가 더 깔끔해진다.

public class DeviceController {
    public void sendShutDown(){
        DeviceHandle handle = getHandle(DEV1);
        // 디바이스 상태를 점검한다.
        if (handle != DeviceHandle.INVALID) {
            // 레코드 필드에 디바이스 상태를 저장한다.
            retrieveDeviceRecord(handle);
            // 디바이스가 일시정지 상태가 아니라면 종료한다.
            if( record.getStatus() != DEVICE_SUSPENDED) {
                pauseDevice(handle);
                clearDeviceWorkQueue(handle);
                closeDevice(handle);
            } else {
                logger.log("Device suspended. Unable to shut down");
            }
        } else {
            logger.log("Invalid handle for: " + DEV1.toString());
        }
    }
}
```



###### DeviceController - good

```java
// good
// 디바이스 종료 알고리즘과 오류 처리 알고리즘이 분리 되었다.
// 각 개념을 독립적으로 살펴보고 이해할 수 있다.
public class DeviceContoller {
    public void sendShutDown(){
        try{
            tryToShutDown();
        }catch (DeviceShutDownError e){
            logger.log(e);
        }
    }
    private void tryToShutDown() throws DeviceShutDownError {
        DeviceHandle handle = getHandle(DEV1);
        DeviceRecord record = retrieveDeviceRecord(handle);

        pauseDevice(handle);
        clearDeviceWorkQueue(handle);
        closeDevice(handle);
    }
    private DeviceHandle getHandle(DeviceID id){
        throw new DeviceShutDownError("Invalid handle for: " + id.toString());
    }
}
```



### 2. Try-Catch-Finally 문부터 작성해라

`try-catch-finally` 문에서 `try` 블록에 들어가는 코드를 실행하면, 

어느 시점에서든 실행이 중단된 후 `catch` 블록으로 넘어갈 수 있다.

`catch` 블록은 프로그램 상태를 일관성 있게 유지하게 한다. 

###### sample

```java
// 파일이 없으면 예외를 던진다.
@Test(expected = StorageException.class)
public void retrieveSectionShouldThrowOnInvalidFileName(){
    sectionStore.retrieveSection("invalid - file");
}
```

```java
// 단위 테스트에 맞춰 다음 코드를 구현했다.
// 단위테스트 : 모듈이나 애플리케이션 안에 있는 개별적인 코드 단위가 예상대로 작동하는지 확인하는 반복적인 행위.

public List<RecordedGrip> retrieveSection(String sectionName){
    // 실제로 구현할 때까지 비어 있는 더미를 반환한다.
    return new ArrayList<RecordedGrip>();
}

// result: 코드가 예외를 던지지 않음. 단위 테스트 실패 
```

```java
// 잘못된 파일에 접근하는 코드 
public List<RecordedGrip> retrieveSection(String sectionName){
    try{
        FileInputStream stream = new FileInputStrean(sectionName)
    } catch (Exception e){
        throw new StorageException("retrieval error", e);
    }
    return new ArrayList<RecordedGrip>();
}
// file not exception 을 던지는 코드. 
```

```java
// 리팩토링
public List<RecordedGrip> retrieveSection(String sectionName){
    try{
        FileInputStream stream = new FileInputStream(sectionName);
        stream.close();
      // exception -> file not found Exception 으로 변경 
    } catch (FileNotFoundException e){
        throw new StorageException ( "retrieval error", e);
    }
    return new ArrayList<RecordedGrip>();
}
```



### 3. 미확인 예외를 사용해라

하위 단계에서 코드를 변경하면 상위 단계 메서드 선언부를 전부 고쳐야 하낟.

위 단계에서 최상위 단계까지 연쇄적인 수정이 일어난다.



### 4. 예외에 의미를 제공해라

예외를 던질 때는 전후 상황을 충분히 덧붙혀야 한다. 

오류가 발생과 위치를 찾기 쉬워진다.

실패한 연산 이름과 실패 유형을 언급해라



### 5. 호출자를 고려해 예외 클래스를 정의해라

오류를 분류하는 방법은 수없이 많다.

개발자는 효율적으로 오류를 잡아내는 방법을 고안해야한다.

```java
ACMEPort port = new ACMEPort(12);

try {
    port.open();
} catch (DeviceResponseException e) {
    reportPortError(e);
    logger.log("Device response exception", e);
} catch (ATM1212UnlockedException e) {
    reportPortError(e);
    logger.log("Unlock exception", e);
} catch (GMXError e) {
    reportPortError(e);
    logger.log("Device response exception");
} finally {
    ...
}

// 이 코드는 예외에 대응하는 방식이 예외유형과 무관하게 동일하다.
// 호출하는 라이브러리 API를 감싸면서 예외 유형 하나를 반환하면 된다.
```

###### api 감싸기

```java

LocalPort port = new LocalPort(12);
try {
    port.open();
} catch (PortDeviceFailure e) {
    reportError(e);
    logger.log(e.getMessage(), e);
} finally {
}
```

```java
// ACME 클래스가 던지는 예외를 잡아 반환하는 클래스. 
public class LocalPort {
    private ACMEPort innerPort;

    public LocalPort(int portNumber) {
        innerPort = new ACMEPort(portNumber);
    }

    public void open() {
        try {
            innerPort.open();
        } catch (DeviceResponseException e) {
            throw new PortDeviceFailure(e);
        } catch (ATM1212UnlockedException e) {
            throw new PortDeviceFailure(e);
        } catch (GMXError e) {
            throw new PortDeviceFailure(e);
        }
    }
}
```

외부 API 를 감싸면 

- 의존성이 크게 준다
- 다른 라이브러리로 갈아타는 비용이 적다
- 프로그램을 테스트 하기 쉬워진다
- 특정 업체가 API를 설계한 방식에 발목 잡히지 않는다. 



### 6. 정상 흐름을 정의해라

외부 API 를 감싼 분리된 코드가 멋진 바식이지만, 때론 적합하지 않다.

###### example

```java
// 총계를 계산하는 허술한 코드 
try {

    MealExpenses expenses = expenseReportDAO.getMeals(employee.getID());
    m_total += expenses.getTotal();
} catch(MealExpencesNotFound e) {
    m_total += getMealPerDiem();
}
// 직원이 청구한 식비를 총계에 더한다다다다
// 청구하지 않았다면 일일 기본 식비를 총계에 더한다.

// 위 코드는 이해하기가 어렵다. 
```

###### example - better

```java
// 간결한 코드 
MealExpenses expenses = expenseReportDAO.getMeals(employee.getID());
m_total += expenses.getTotal();

public class PerDiemMealExpenses implements MealExpenses {
    public int getTotal() {
        // 기본값으로 일일 기본 식비를 반환한다.
        // (예외가 아닌)
    }
}
// 예외가 아닌 기본값으로 식비를 반환한다. 
```

이를 특수 사례 패턴이라고 한다. 

= 클래스를 만들거나 객체를 조작해 특수 사례를 처리하는 방식 



### 7. Null 을 반환하지 마라

흔한 실수 

1. null 을 반환하는 습관

   ```java
   public void registerItem(Item item) {
       if (item != null) {
           ItemRegistry registry = peristentStore.getItemRegistry();
           if (registry != null) {
               Item existing = registry.getItem(item.getID());
               if (existing.getBillingPeriod().hasRetailOwner()) {
                   existing.register(item);
               }
           }
       }
   }

   // peristentStore 가 null 이면 난리남. 
   ```

   ###### 안좋은이유

   1. 일거리를 넘긴다
   2. 호출자에게 문제를 넘긴다
   3. 통제 불능에 빠진다.

null 을 반환해야 하는 경우, 또는 외부 API 가 null 을 반환한다면, 감싸기 메서드를 구현해 예외를 던지거나 특수 사례 객체를 반환하는 방식을 고려한다. 



```java
// bad
List<Employee> employees = getEmployees();
if(employees != null) {
    for(Employee e : employees) {
        totalPay += e.getPay();
    }
}
// getEmployees(); 는 null 도 반환. 

// good - 빈 리스트를 반환해서 코드를 깔끔하게 
List<Employee> employees = getEmployees();
for(Employee e : employees) {
    totalPay += e.getPay();
}

// 자바에는 Collections.emptyList(); 가 있어 읽기 전용 리스트를 반환한다. 
public List<Employee> getEmployees() {
    if (..직원이 없다면..)
        return Collections.emptyList();
}
```



### 8. Null 을 전달하지 마라

정상적인 인수로 null 을 기대하는 API 가 아니라면, 메서드로 null 을 반환하는 코드는 최대한 피한다.

대다수 프로그래밍 언어는 null 을 적절히 처리하는 방법이 없다. 

애초에 null 이 넘어오면 코드에 문제가있다. 그만큼 부주의한 실수를 저지를 확률도 작아진다.



#### 결론

깨끗한 코드는 안정성도 높아야한다.

오류 처리를 프로그램 논리와 분리해 독자적인 사안으로 고려하면 튼튼하고 깨끗한 코드를 작성할 수 있다.

### 