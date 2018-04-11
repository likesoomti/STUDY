# Scanner와 BufferedReader 차이

##### Scanner

콘솔 입출력을 쉽게 처리할 수 있는 클래스

```java
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    }
```

#### BufferedReader

InputStreamReader에 버퍼링 기능을 추가한 것.

데이터를 일정량사이즈를 읽어와 버퍼에 보관한 후 그리고 사용자가 요구할 때 버퍼에서 읽어온다.

`사용할때는 IO Exception 을 필수적으로 사용해야 한다.`

속도를 향상시키고 시간의 부하를 줄일수 있게 된다.

```java
public class Main {
    public static void main(String[] args) {
        BufferedReader n = new BufferedReader(new FileReader("파일명.확장자")); // 파일 입력
        BufferedReader n2 = new BufferedReader(new InputStreamReader(System.in)) // 키보드 입력

    }
}
```

##### bufferedreader int 공백 입력시

StringTokenizer 의 nextToken을 사용하면 공백 다음 값을 불러올 수 있다.

```java
BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
StringTokenizer st = new StringTokenizer( br.readLine() );

int T = Integer.parseInt( st.nextToken() );
int N = Integer.parseInt( st.nextToken() )
```



##### 성능비교

`Java.Util.Scanner` : 6.068초

`Java.io.BufferReader` : 0.934초 

버퍼 리더가 훨씬 우세하다.

