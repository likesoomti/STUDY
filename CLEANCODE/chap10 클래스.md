# chap10 클래스

코드의 표현력과, 함수에 신경을 쓰는것도 중요하지만, 더 차원 높은 단계까지 신경 써야 한다.



### 1. 클래스 체계

JAVA 의 클래스 정의 단계

1. 변수 목록
   1. static public constant
   2. private variable
   3. public variable

*캡슐화*

같은 패키지 안에서 테스트 코드가 함수를 호출하거나 변수를 사용해야 한다면 변수를 `protected` 로 선언하거나 패키지 전체로 공개한다.



### 2. 클래스는 작아야 한다.

클래스는 작아야 한다. 

##### 구린 클래스

```java

public class SuperDashboard extends JFrame implements MetaDataUser {
    public String getCustomizerLanguagePath()
    public void setSystemConfigPath(String systemConfigPath) 
    public String getSystemConfigDocument()
    public void setSystemConfigDocument(String systemConfigDocument) 
    public boolean getGuruState()
    public boolean getNoviceState()
    public boolean getOpenSourceState()
    public void showObject(MetaObject object) 
    public void showProgress(String s)
    public boolean isMetadataDirty()
    public void setIsMetadataDirty(boolean isMetadataDirty)
    public Component getLastFocusedComponent()
    public void setLastFocused(Component lastFocused)
    public void setMouseSelectState(boolean isMouseSelected) 
    public boolean isMouseSelected()
    public LanguageManager getLanguageManager()
    public Project getProject()
    public Project getFirstProject()
    public Project getLastProject()
    public String getNewProjectName()
    public void setComponentSizes(Dimension dim)
    public String getCurrentDir()
    public void setCurrentDir(String newDir)
    public void updateStatus(int dotPos, int markPos)
    public Class[] getDataBaseClasses()
    public MetadataFeeder getMetadataFeeder()
    public void addProject(Project project)
    public boolean setCurrentProject(Project project)
    public boolean removeProject(Project project)
    public MetaProjectHeader getProgramMetadata()
    public void resetDashboard()
    public Project loadProject(String fileName, String projectName)
    public void setCanSaveMetadata(boolean canSave)
    public MetaObject getSelectedObject()
    public void deselectObjects()
    public void setProject(Project project)
    public void editorAction(String actionName, ActionEvent event) 
    public void setMode(int mode)
    public FileManager getFileManager()
    public void setFileManager(FileManager fileManager)
    public ConfigManager getConfigManager()
    public void setConfigManager(ConfigManager configManager) 
    public ClassLoader getClassLoader()
    public void setClassLoader(ClassLoader classLoader)
    public Properties getProps()
    public String getUserHome()
    public String getBaseDir()
    public int getMajorVersionNumber()
    public int getMinorVersionNumber()
    public int getBuildNumber()
    public MetaObject pasting(MetaObject target, MetaObject pasted, MetaProject project)
    public void processMenuItems(MetaObject metaObject)
    public void processMenuSeparators(MetaObject metaObject) 
    public void processTabPages(MetaObject metaObject)
    public void processPlacement(MetaObject object)
    public void processCreateLayout(MetaObject object)
    public void updateDisplayLayer(MetaObject object, int layerIndex) 
    public void propertyEditedRepaint(MetaObject object)
    public void processDeleteObject(MetaObject object)
    public boolean getAttachedToDesigner()
    public void processProjectChangedState(boolean hasProjectChanged) 
    public void processObjectNameChanged(MetaObject object)
    public void runProject()
    public void setAçowDragging(boolean allowDragging) 
    public boolean allowDragging()
    public boolean isCustomizing()
    public void setTitle(String title)
    public IdeMenuBar getIdeMenuBar()
    public void showHelper(MetaObject metaObject, String propertyName) 
}
```

##### 리팩토링을 해보자.

1. 클래스 이름은 해당 클래스 책임을 기술해야 한다.
2. 클래스 이름에 Processor, Manager, Super 등과 같은 모호한 단어가 있다면 클래스에 여러 책임을 떠안고 있다는 증거다.
3. 클래스 설명을 // if, and or but 을 사용하지 않고 25자 이하여야 한다. 

*단일 책임 원칙*

단일 책임 원칙 (Single Responsibility Principle SRP)은 클래스나 모듈을 변경할 이유가 1가지 뿐이어야 한다. 

###### 단일 책임 클래스

```java
public class Version{
  public int getMajorVersionNumber()
  public int getMinorVersionNumber()
  public int getBuildNumber()
}
```

위에 `SuperDashBoard` 버전 정보를 다루는 메서드 3개를 따로 꺼내 독자적인 클래스를 만들었다. 

이렇게 만들면 다른 어플리케이션에서 재사용 하기 아주 쉬운 구조로 변경된다.

규모가 어느정도 있는 시스템은 복잡해진다. 그럴수록 체계적인 정리가 필수다.

큰 클래스 몇개가 아니라, 작은 클래스 여럿으로 이루어진 시스템이 바람직하다. 

##### 작은 클래스는 

1. 각자 맡은 책임이 하나며, 
2. 변경할 이유가 하나며, 
3. 작은 클래스와 협력해 시스템에 필요한 동작을 수행한다.

*응집도*

##### 응집도가 높다 = 클래스에 속한 메서드와 변수가 서로 의존하며 논리적인 단위로 묶인다 

클래스는 인스턴스 변수 수가 작아야한다.

메서드가 변수를 더 많이 사용할수록 메서드와 클래스는 응집도가 더 높다. 



##### Stack.java

```java
// 응집도가 높은 클래스 
public class Stack {
 private int topOfStack = 0;
 List<Integer> elements = new LinkedList<Integer>();
 public int size() {
 return topOfStack;
 }
 public void push(int element) {
 topOfStack++;
 elements.add(element);
 }
 public int pop() throws PoppedWhenEmpty {
 if (topOfStack == 0)
 throw new PoppedWhenEmpty();
 int element = elements.get(--topOfStack);
 elements.remove(topOfStack);
 return element;
 }
}
```



*응집도를 유지하면 작은 클래스 여럿이 나온다.*

클래스가 응집력을 잃는다면 쪼개라! 

큰 함수를작은 함수 여럿으로 쪼개다 보면 종종 작은 클래스 여럿으로 쪼갤 기회가 생긴다.



##### 작은 함수 / 클래스 여럿으로 쪼깨보자

###### PrintPrimes.java

```java
// 엉망진창
// 심한 들여쓰기
// 이상한 변수
// 구조가 빡빡 
package literatePrimes;
public class PrintPrimes {
 public static void main(String[] args) {
 final int M = 1000;
 final int RR = 50;
 final int CC = 4;
 final int WW = 10;
 final int ORDMAX = 30;
 int P[] = new int[M + 1];
 int PAGENUMBER;
 int PAGEOFFSET;
 int ROWOFFSET;
 int C;
 int J;
 int K;
 boolean JPRIME;
 int ORD;
 int SQUARE;
 int N;
 int MULT[] = new int[ORDMAX + 1];
 J = 1;
 K = 1;
 P[1] = 2;
 ORD = 2;
 SQUARE = 9;
 while (K < M) {
	 do {
 		J = J + 2;
 		if (J == SQUARE) {
 			ORD = ORD + 1;
 			SQUARE = P[ORD] * P[ORD];
 			MULT[ORD - 1] = J;
		}
 	N = 2;
 	JPRIME = true;
 	while (N < ORD && JPRIME) {
 		while (MULT[N] < J)
 			MULT[N] = MULT[N] + P[N] + P[N];
 		if (MULT[N] == J)
 				JPRIME = false;
 		N = N + 1;
 	}
 } while (!JPRIME);
 K = K + 1;
 P[K] = J;
 }
 {
   PAGENUMBER = 1;
   PAGEOFFSET = 1;
   while (PAGEOFFSET <= M) {
     System.out.println("The First " + M +
     " Prime Numbers --- Page " + PAGENUMBER);
     System.out.println("");
     for (ROWOFFSET = PAGEOFFSET; ROWOFFSET < PAGEOFFSET + RR; ROWOFFSET++){
     for (C = 0; C < CC;C++)
     if (ROWOFFSET + C * RR <= M)
     System.out.format("%10d", P[ROWOFFSET + C * RR]);
     System.out.println("");
   }
   System.out.println("\f");
   PAGENUMBER = PAGENUMBER + 1;
   PAGEOFFSET = PAGEOFFSET + RR * CC;
   }
  }
 }
}
```

여러 함수로 나눠보자.

##### PrimePrinter.java 리팩토링

###### PrimePrinter

```java
package literatePrimes;
public class PrimePrinter {
 public static void main(String[] args) {
   final int NUMBER_OF_PRIMES = 1000;
   int[] primes = PrimeGenerator.generate(NUMBER_OF_PRIMES);
   
   final int ROWS_PER_PAGE = 50;
   final int COLUMNS_PER_PAGE = 4;
   RowColumnPagePrinter tablePrinter =
     new RowColumnPagePrinter(ROWS_PER_PAGE,
                              COLUMNS_PER_PAGE,
                              "The First " + NUMBER_OF_PRIMES +
                               " Prime Numbers");
   tablePrinter.print(primes);
 }
}
```

###### RowColumnPagePrinter.java

```java
package literatePrimes;
import java.io.PrintStream;
public class RowColumnPagePrinter {
 private int rowsPerPage;
 private int columnsPerPage;
 private int numbersPerPage;
 private String pageHeader;
 private PrintStream printStream;
 public RowColumnPagePrinter(int rowsPerPage,
                             int columnsPerPage,
                             String pageHeader) {
   this.rowsPerPage = rowsPerPage;
   this.columnsPerPage = columnsPerPage;
   this.pageHeader = pageHeader;
   numbersPerPage = rowsPerPage * columnsPerPage;
   printStream = System.out;
 }
   public void print(int data[]) {
 int pageNumber = 1;
 for (int firstIndexOnPage = 0;
 firstIndexOnPage < data.length;
 firstIndexOnPage += numbersPerPage) {
 int lastIndexOnPage =
 Math.min(firstIndexOnPage + numbersPerPage - 1,
 data.length - 1);
 printPageHeader(pageHeader, pageNumber);
 printPage(firstIndexOnPage, lastIndexOnPage, data);
 printStream.println("\f");
 pageNumber++;
 }
 }
 private void printPage(int firstIndexOnPage,
                         int lastIndexOnPage,
                         int[] data) {
 int firstIndexOfLastRowOnPage =
 	firstIndexOnPage + rowsPerPage - 1;
 for (int firstIndexInRow = firstIndexOnPage;
       firstIndexInRow <= firstIndexOfLastRowOnPage;
       firstIndexInRow++) {
   printRow(firstIndexInRow, lastIndexOnPage, data);
   printStream.println("");
  }
 }
 private void printRow(int firstIndexInRow,
                       int lastIndexOnPage,
                       int[] data) {
   for (int column = 0; column < columnsPerPage; column++) {
     int index = firstIndexInRow + column * rowsPerPage;
     if (index <= lastIndexOnPage)
  	   printStream.format("%10d", data[index]);
   }
 }
  
 private void printPageHeader(String pageHeader,
 							  int pageNumber) {
   printStream.println(pageHeader + " --- Page " + pageNumber);
   printStream.println("");
 }
 public void setOutput(PrintStream printStream) {
	 this.printStream = printStream;
 }
}
```

###### PrimeGenerator.java

```java
package literatePrimes;
import java.util.ArrayList;
public class PrimeGenerator {
 private static int[] primes;
 private static ArrayList<Integer> multiplesOfPrimeFactors;
  
 protected static int[] generate(int n) {
   primes = new int[n];
   multiplesOfPrimeFactors = new ArrayList<Integer>();
   set2AsFirstPrime();
   checkOddNumbersForSubsequentPrimes();
   return primes;
 }
 private static void set2AsFirstPrime() {
   primes[0] = 2;
   multiplesOfPrimeFactors.add(2);
 }
 private static void checkOddNumbersForSubsequentPrimes() {
   int primeIndex = 1;
   for (int candidate = 3;
     primeIndex < primes.length;
     candidate += 2) {
     if (isPrime(candidate))
       primes[primeIndex++] = candidate;
   }
 }
 private static boolean isPrime(int candidate) {
   if (isLeastRelevantMultipleOfNextLargerPrimeFactor(candidate)) {
   multiplesOfPrimeFactors.add(candidate);
   return false;
   }
   return isNotMultipleOfAnyPreviousPrimeFactor(candidate);
 }
 private static boolean
 isLeastRelevantMultipleOfNextLargerPrimeFactor(int candidate) {
   int nextLargerPrimeFactor = primes[multiplesOfPrimeFactors.size()];
   int leastRelevantMultiple = nextLargerPrimeFactor * nextLargerPrimeFactor;
   return candidate == leastRelevantMultiple;
 }
 private static boolean
   isNotMultipleOfAnyPreviousPrimeFactor(int candidate) {
   for (int n = 1; n < multiplesOfPrimeFactors.size(); n++) {
     if (isMultipleOfNthPrimeFactor(candidate, n))
   		return false;
    }
    return true;
 }
 private static boolean
   isMultipleOfNthPrimeFactor(int candidate, int n) {
   return
  	 candidate == smallestOddNthMultipleNotLessThanCandidate(candidate, n);
 }
 private static int
   smallestOddNthMultipleNotLessThanCandidate(int candidate, int n) {
   int multiple = multiplesOfPrimeFactors.get(n);
   while (multiple < candidate)
     multiple += 2 * primes[n];
   multiplesOfPrimeFactors.set(n, multiple);
   return multiple;
  }
}
```



##### 분석

- 프로그램이 길어졌다.

1. 리팩터링한 프로그램은 좀 더 길고 서술적인 변수 이름을 사용한다.
2. 코드에 주석을 추가하는 수단으로 함수 선언과 클래스 선언을 활용한다
3. 가독성을 높이고자 공백을 추가하고 형식을 맞추었다. 

- 프로그램은 3가지 책임으로 나눠졌다.
  1. PrintPrimes.java - main 함수를 포함하며, 실행 환경을 책임진다.
  2. RowColumnPagePrinter.java - 숫자 목록을 주어진 행과 열에 맞춰 페이지를 출력한다.
  3. PrimeGenerator.java - 소수 목록을 생성한다. 알고리즘이 바뀔 시 여기를 수정한다.



### 3. 변경하기 쉬운 클래스

대다수의 시스템은 지속적인 변경이 가해진다.

깨끗한 시스템은 클래스를 체계적으로 정리해 변경에 수반하는 위험을 낮춘다.



##### 변경이 필요해 손대야 하는 클래스

```java
public class Sql {
 public Sql(String table, Column[] columns)
 public String create()
 public String insert(Object[] fields)
 public String selectAll()
 public String findByKey(String keyColumn, String keyValue)
 public String select(Column column, String pattern)
 public String select(Criteria criteria)
 public String preparedInsert()
 private String columnList(Column[] columns)
 private String valuesList(Object[] fields, final Column[] columns)
 private String selectWithCriteria(String criteria)
 private String placeholderList(Column[] columns)
}
```

클래스 일부에서만 사용되는 비공개 메서드는 코드를 개선할 잠재적인 여지를 시사한다.

위 코드는 *SRP* 를 위반한다.

새로운 sql 문을 지원하려면 sql 클래스에 손대야 한다. 

또한 기준 SQL 문 하나를 수정할 때도 반드시 Sql 클래스에 손대야한다.



###### 변경 클래스

위 코드에 있던 공개인터페이스를 각각 SQL 클래스에 파생하는 클래스로 만들었다.

```java
abstract public class Sql {
 public Sql(String table, Column[] columns)
 abstract public String generate();
}
public class CreateSql extends Sql {
 public CreateSql(String table, Column[] columns)
 @Override public String generate()
}
public class SelectSql extends Sql {
 public SelectSql(String table, Column[] columns)
 @Override public String generate()
}
public class InsertSql extends Sql {
 public InsertSql(String table, Column[] columns, Object[] fields)
 @Override public String generate()
 private String valuesList(Object[] fields, final Column[] columns)
}
public class SelectWithCriteriaSql extends Sql {
 public SelectWithCriteriaSql(
 String table, Column[] columns, Criteria criteria)
 @Override public String generate()
}
public class SelectWithMatchSql extends Sql {
 public SelectWithMatchSql(
 String table, Column[] columns, Column column, String pattern)
 @Override public String generate()
}
public class FindByKeySql extends Sql
 public FindByKeySql(
 String table, Column[] columns, String keyColumn, String keyValue)
 @Override public String generate()
}
public class PreparedInsertSql extends Sql {
 public PreparedInsertSql(String table, Column[] columns)
 @Override public String generate() {
 private String placeholderList(Column[] columns)
}
public class Where {
 public Where(String criteria)
 public String generate()
}
public class ColumnList {
 public ColumnList(Column[] columns)
 public String generate()
}
```

클래스를 서로 분리하니, 테스트 관점에서 모든 논리를 증명할 수 있고, 순식간에 이해 된다.

OCP 원칙도 지원한다

OCP = 클래스는 확장에 개방적이고 수정에 폐쇄적이여야 한다.

새 기능을 수정하거나, 기존 기능을 변경할 때 건드릴 코드가 최소인 시스템 구조가 바람직하다.

이상적인 시스템이라면 새 기능을 추가할 때 시스템을 확장할 뿐 기존 코드를 변경하지는 않는다.



*변경으로부터 격리*

요구사항은 변한다. 따라서 코드도 변한다.

프로그래밍에는 구체적인 클래스와 추상 클래스가 있다.

상세한 구현에 의존하는 클라이언트 클래스는 구현이 바뀌면 위험에 빠진다.

인터페이스와 추상 클래스를 사용해 구현이 미치는 영향을 격리한다.



시스템의 결합도를 낮추면, 유연성과 재사용성이 높아진다. 

결합도가 낮다는 소리는 각 시스템 요소가 다른 요소로부터, 변경으로 부터 잘 격리되어 있다는 의미이다.

###### example - Portfolio 클래스 만들기

// Portfolio class 

- TokyoStockExchange API 를 사용해 포트폴리오 값을 계산한다.
- 따라서 테스트 코드는 시세 변화에 영향을 받는다 
- 5분마다 값이 달라지는 API 

```java
// Portfolio class에서 API 를 직접 호출하는 대신, Stock Exchange 라는 인터페이스를 생성한다.
public interface StockExchange {
 Money currentPrice(String symbol);
}
```

```java
// StockExchange 인터페이스를 구현하는 ToykyoStockExchange 클래스를 구현한다.
public Portfolio {
 private StockExchange exchange;
 public Portfolio(StockExchange exchange) {
 this.exchange = exchange;
 }
 // ...
}
```

```java
// 테스트용 클래스 
public class PortfolioTest {
 private FixedStockExchangeStub exchange;
 private Portfolio portfolio;
 @Before
 protected void setUp() throws Exception {
 exchange = new FixedStockExchangeStub();
 exchange.fix("MSFT", 100);
 portfolio = new Portfolio(exchange);
 }
 @Test
 public void GivenFiveMSFTTotalShouldBe500() throws Exception {
 portfolio.add(5, "MSFT");
 Assert.assertEquals(500, portfolio.value());
 }
}

```

위와 같이 테스트 가능한 결합도를 낮추면, 유연성과 재 사용성이 더욱 높아진다.

결합도가 낮다는 소리는 각 시스템 요소가 다른 요소로부터, 변경으로 부터 잘 격리되어 있다는 의미이다. 



결합도를 최소로 줄이면 DIP 를 따르는 클래스가 나온다

DIP = 클래스는 상세한 구현이 아니라, 추상화에 의존해야 한다. 