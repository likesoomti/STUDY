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