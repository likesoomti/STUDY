# chap03 함수

함수는 어떤 프로그램이든 가장 기본적인 단위입니다.



### 함수의 문제 

- 긴 길이
- 중복된 코드
- 괴상한 문자열
- 모호한 자료유형 
- 많은 API
- 다양한 추상화 수준
- 긴 코드
- 중첩된 if 문

등의 문제로 코드의 이해가 힘들어진다.

메서드를 추출하고, 이름을 변경하고, 구조를 조금만 변경해도 코드의 이해는 쉬워진다.



##### HtmlUtil.java

```java
public static String testableHtml(
     PageData pageData,
     boolean includeSuiteSetup
   ) throws Exception {
     WikiPage wikiPage = pageData.getWikiPage();
     StringBuffer buffer = new StringBuffer();
     if (pageData.hasAttribute("Test")) {
       if (includeSuiteSetup) {
         WikiPage suiteSetup =
           PageCrawlerImpl.getInheritedPage(
               SuiteResponder.SUITE_SETUP_NAME, wikiPage
           );
         if (suiteSetup != null) {
           WikiPagePath pagePath =
             suiteSetup.getPageCrawler().getFullPath(suiteSetup);
           String pagePathName = PathParser.render(pagePath);
           buffer.append("!include -setup .")
                 .append(pagePathName)
                 .append("\n");
         }
       }
       WikiPage setup =
         PageCrawlerImpl.getInheritedPage("SetUp", wikiPage);
       if (setup != null) {
         WikiPagePath setupPath =
           wikiPage.getPageCrawler().getFullPath(setup);
         String setupPathName = PathParser.render(setupPath);
         buffer.append("!include -setup .")
               .append(setupPathName)
               .append("\n");
       }
     }
     buffer.append(pageData.getContent());
     if (pageData.hasAttribute("Test")) {
       WikiPage teardown =
         PageCrawlerImpl.getInheritedPage("TearDown", wikiPage);
       if (teardown != null) {
         WikiPagePath tearDownPath =
           wikiPage.getPageCrawler().getFullPath(teardown);
         String tearDownPathName = PathParser.render(tearDownPath);
         buffer.append("\n")
               .append("!include -teardown .")
               .append(tearDownPathName)
               .append("\n");
       }
       if (includeSuiteSetup) {
         WikiPage suiteTeardown =
           PageCrawlerImpl.getInheritedPage(
                   SuiteResponder.SUITE_TEARDOWN_NAME,
                   wikiPage
           );
         if (suiteTeardown != null) {
           WikiPagePath pagePath =
             suiteTeardown.getPageCrawler().getFullPath (suiteTeardown);
           String pagePathName = PathParser.render(pagePath);
           buffer.append("!include -teardown .")
                 .append(pagePathName)
                 .append("\n");
         }
      }
    }
    pageData.setContent(buffer.toString());
    return pageData.getHtml();
   }
}
```

밑에 코드를 보면 3분만에 코드를 이해하기는 어렵다.

이에 메서드를 추출/이름변경/구조 변경을 한 코드이다

##### HtmlUtil.java 리팩토링

```java
public static String renderPageWithSetupsAndTearDowns (
  PageData pageData, boolean isSuite
  ) throws Exception {
  	boolean isTestPage = pageData.hasAttribute("Test");
    if(isTestPage) {
    	WikiPage testPage = pageData.hasAttribute("Test");
        if(isTestPage){
        	WikiPage testPage = pageData.getWikiPage();
        	StringBuffer newPageContent = new StringBuffer();
        	includeSetupPages(testPage, newPageContent, isSuite);
        	newPageContent.append(pageData.getContent());
        	includeTeardownPages(testPage, newPageContent, isSuite);
        	pageData.setContent(newPageContent.toString());
        }
        return pageData.getHtml()
    }
  }
```

위 코드 보다는 함수가 설정/해제 페이지를 넣은 후 테스트 페이지를 html 로 렌더링 한다 라는 사실은 짐작이 된다.

어떻게 하면 함수를 직관적으로 파악할 수 있을지 알아보자.



### 1. 작게 만들어라

함수의 절대적인 규칙은 작게! 만드는 것이다.

##### *블록 들여쓰기*

- `if/else`,`while` 문 등 들어가는 블록은 한줄이어야 한다.
- 중첩 구조가 생길만큼 함수가 커져서는 안된다

### 2. 한가지만 해라!

- 맨 위 목록은 하나의 함수에서 여러가지를 처리한다


- 검색하고, 렌더링, 문자열 , html 생성 등 


- 함수를 만드는 이유는 큰 개념을 다음 추상화 수준에서 여러 단계로 나눠서 처리하기 위함이다.

### 3. 함수 당 추상화 수준은 하나로!

- 한 함수 내에 추상화 수준을 섞으면 코드 읽는 사람이 헷갈린다. 

##### *위에서 아래로 코드 읽기: 내려가기 규칙*

코드는 위에서 아래로 이야기처럼 읽혀야 좋다.

위에서 아래로 프로그램을 읽으면 추상화 수준이 한번에 한단계씩 낮아진다.

###### example

```
설정/해제 페이지를 포함하려면, 1.설정 페이지를 포함 하고, 테스트 페이지 내용을 포함하고, 해제 페이지 내용을 포함한다.
2.설정 페이지를 포함하려면, 3.슈트이면 슈트 설정 페이지를 포함한 후 일반 설정 페이지를 포함한다.
3.슈트 설정 페이지를 포함하려면 4.부모 계층에서 suiteSetUp 페이지를 찾아 include문과 경로를 추가한다
4.부모 계층을 검색하면...
```

아래로 내려가듯이 코드를 구현하면 추상화 수준을 일관되게 유지하기가 쉬워진다.

### 4. `Switch` 문

`Switch` 문은 작게 만들기 어렵다.

 하지만 완전 피할 방법은 없다. 따라서 다형성을 이용한다.

##### PayRoll.java

```java
public Money calculatePay(Employee e)
  throws InvalidEmployeeType{
  switch(e.type){
    case COMMISSIONED:
      return calculateComissionedPay(e);
    case HOURLY:
      return calculateHourlyPay(e);
    case SALARIED:
      return calculateSalariedPay(e);
    default:
      throw new InvalidEmployeeType(e.type);
  }
}
```

 위 코드의 문제는

1. 함수가 길다
2. 새 직원 유형을 추가하면 더 길어진다
3. 한가지 작업을 수행하지 않고
4. SRP 를 위반한다.
5. OCP 를 위반한다.

##### srp single responsibility principle (wikipedia)

[객체 지향 프로그래밍](https://ko.wikipedia.org/wiki/%EA%B0%9D%EC%B2%B4_%EC%A7%80%ED%96%A5_%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D)에서 [단일 책임 원칙]()(single responsibility principle)이란 모든 클래스는 하나의 책임만 가지며, 클래스는 그 책임을 완전히 [캡슐화](https://ko.wikipedia.org/wiki/%EC%BA%A1%EC%8A%90%ED%99%94)해야 함을 일컫는다. 클래스가 제공하는 모든 기능은 이 책임과 주의 깊게 부합해야 한다.

 어떤 클래스나 모듈은 변경하려는 단 하나 이유만을 가져야 한다고 결론 짓는다. 예를 들어서 보고서를 편집하고 출력하는 모듈을 생각해 보자. 이 모듈은 두 가지 이유로 변경될 수 있다. 첫 번째로 보고서의 내용 때문에 변경될 수 있다. 두 번째로 보고서의 형식 때문에 변경될 수 있다. 이 두 가지 변경은 하나는 실질적이고 다른 하나는 꾸미기 위한 매우 다른 원인에 기인한다. 단일 책임 원칙에 의하면 이 문제의 두 측면이 실제로 분리된 두 책임 때문이며, 따라서 분리된 클래스나 모듈로 나누어야 한다. 다른 시기에 다른 이유로 변경되어야 하는 두 가지를 묶는 것은 나쁜 설계일 수 있다.

한 클래스를 한 관심사에 집중하도록 유지하는 것이 중요한 이유는, 이것이 클래스를 더욱 튼튼하게 만들기 때문이다. 앞서 든 예를 계속 살펴보면, 편집 과정에 변경이 일어나면, 같은 클래스의 일부로 있는 출력 코드가 망가질 위험이 대단히 높다.

#####  OCP open closed principle

**개방-폐쇄 원칙**(OCP, Open-Closed Principle)은 '소프트웨어 개체(클래스, 모듈, 함수 등등)는 확장에 대해 열려 있어야 하고, 수정에 대해서는 닫혀 있어야 한다.'는 프로그래밍 원칙이다.



쉽게 말하면 , 직원이 늘수록 생겨야 하는 함수가 무한정 존재한다. 

이걸 해결하기 위해 `switch` 문을 추상 팩토리에 숨긴다.

팩토리는  `switch`를 이용해 적절한 `Employee`파생 클래스의 인스턴스를 생성한다. 그러면, Employee 클래스가 호출되고, 다형성으로 인해 실제 파생 클래스의 함수가 실행된다.



### 5. 서술적인 이름을 사용해라!

- 함수가 하는 일을 좀 더 잘 표현하는 것이 좋다.
- 코드를 읽으면서 짐작했던 기능을 그대로 수행하면 깨끗한 코드이다.
- 길고 서술적인 이름이 긴 주석보다 훨 낫다.
- 이름을 붙일 때는 일관성이 있어야 한다.
- 모듈 내 함수 이름은 같은 문구/명사/동사를 사용한다.



### 6. 함수 인수

- 함수의 인수는 적을수록 좋다 
- 3,4개의 인수는 피하는 것이 좋다 ( 차라리 클래스를 만들어라 )
- 인수가 많으면 개념을 이해하기 어려어진다.
- 코드를 읽을때는 `includeSetupPageIntoo(new PageContent)` 보다 `includeSetupPage()`가 쉽다.



##### 많이 쓰는 단항 형식

*함수 인수 1개를 넘기는 이유*

1. 질문을 던지는 경우 (`boolean fileExists("myFile")`)
2. 인수를 뭔가로 변환해 반환하는 경우 
3. 이벤트 함수

이름을 지을때는 두 경우를 분명히 구분해라.

또한 명령과 조회를 분리해라!

이벤트 함수는 이름과 문맥을 신중히 선택해야한다.

한가지만 해라! (변환 함수에서 출력 하지말고, 변환만 할 것)



*플래그 인수*

- 개추함 쓰지마욧 
- 함수가 한꺼번에 처리한다는 뜻임. 분리해라



*이항 함수*

이항함수가 적절한 경우 - 좌표코드 

###### example

```java
Point p = new Point(0,0) // 근데 두개가 한 값을 표현하는 경우임!
```

이항 함수를 쓰는 것 보다, 클래스 구성원으로 만들어 함수로 호출하는 것이 낫다.

###### example

```java
void writeField(outputStream,name) // x
void outputStrea.writeField(name) // o

```

*삼항 함수*

- 신중히 고려해라. 함수 볼때마다 순서를 기억하는게 주춤하게 되고 구리다.

*인수 객체*

- 인수가 2~3개가 필요하면 독자적인 클래스 변수로 선언하여, 객체를 생성해 인수를 줄이자.

*인수 목록*

- 가변 인수는 동등하게 취급하자.

###### example

```java
 void dyad(String name, Integer… args) //인수 2개 
```

*동사와 키워드*

- 순서와 의도를 제대로 표현하기 위해 동사/명사 쌍을 이루게 만들자
- 키워드를 붙혀 순서를 기억하기 쉽게 만들자

###### example

```java
String write(name) // 이해 쉬움
String writeField(name) //better 
String assertExpectedEqualsActual(expected,actual) // 키워드를 붙혀 인수 순서 기억할 필요없음!
```



*부수 효과를 일으키지마라*

- 함수에서는 한가지만 해라
- 여러가지를 해결하려 하면, 시간적인 결합이나 순서 종속성을 초래한다.

*출력 인수*

- 우리는 인수를 함수 입력으로 생각하는데, 출력 인수로 사용할 경우 또한 있다.
- 객체 지향 언어에서는 출력 인수로 사용하려고 `this` 를 설계했다.
- 일반적으로 출력 인수는 피하자.

###### example

```java
public void appendFooter(StringBuffer report)
appendFooter(s) //x
report.appendFooter() // o
```



### 7. 명령과 조회를 분리하라!

- 함수는 하나만 해야한다.
- 객체상태를 변경하거나 , 반환하거나 둘중 하나만 하자.
- 명령과 조회를 분리해 만들자.



### 8. 오류 코드보다 예외를 사용해라.

- 오류 코드 반환 방식은 ocp 규칙위반.
- 오류 코드 대신 예외를 사용하면 오류 처리 코드가 분리되므로, 깔끔해 진다.

###### example

```java
// worst
if(delegePage(page) == E_OK){
  if(registry.deleteReference(page.name) == E_OK){
    if(configKeys.deleteKey(page.name.makeKey()) == E_OK){
      logger.log("page delete!")
    }
  }
}

// best
try {
  deletePage(page);
  registry.deleteReference(page.name);
  configKeys.deleteKey(page.name.makeKey()); 
}catch(Exception e){
  logger.log(e.getMessage())
}
```



*Try/catch 블록 뽑아내기*

별도의 함수로 뽑아내는 편이 좋다.

정상 동작과 오류 처리 동작을 분리하면 코드이해가 쉬워진다.

###### example

```java
public void delete(Page page){
  try{
    deletePageAndAllReferences(page);
  }
  catch(Exception e){
    logError(e);
  }
}
```

*오류 처리도 한 가지 작업이다.*

- 오류 처리도 한가지 작업만 해야한다.



*Error.java 의존성 분석*

- 오류 코드를 반환한다 = 오류 코드를 정의한다. 
-  Error Enum 이 존재하면, `import` 해서 사용해야 한다.
- 그렇다면 클래스 전부를 다시 컴파일 하고 배치해야 한다. (새 오류 코드를 추가해야함. OCP 위반) 
- 예외를 사용하면 Exception 클래스에서 파생하므로, 재컴파일 / 재배치를 하지 않아도 된다. 예외를 사용하자!



### 9 .반복하지 마라!

- 소스 중복 제거는 진짜 중요하다. 중복을 제거하자



### 10. 구조적 프로그래밍

- goto 쓰지마라 무조건 
- 함수를 작게 만들어라.
- 작게만들면 return,break,continue 를 여러 차례 사용해도 의도를 표현하기 쉬워진다.



### 11. 함수를 어떻게 짜죠?

1. 걍 막 짠다.
2. 코드를 다듬는다
3. 함수를 만든다
4. 이름을 바꾼다
5. 중복을 제거한다
6. 메서들를 줄인다
7. 순서를 바꾼다.

#### **처음부터 탁 짜내는 사람은 존재하지 않는다.**



### 결론

함수는 길이가 짧고, 이름이 좋고, 체계잡힌 함수가 좋다.

함수가 분명하고 정확한 언어로 깔끔하게 떨어져야 코드를 풀어가기가 쉬워진다.



