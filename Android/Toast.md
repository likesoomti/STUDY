# Toast

토스트는 메세지를 간단히 띄웠다 사라지게 하는 방법입니다.

`Toast(Context context)`

##### makeText()

토스트는 `.makeText()` 메서드를 통해 글자를 보여주는데요, 

이 `makeText` 는 매개변수 3개를 받습니다.

```
Toast makeText (Context context, 
                CharSequence text, 
                int duration)
```

###### example

```
Toast.makeText(context, text, duration).show();
```



##### context

컨텍스트는 어플리케이션 환경 정보에 대한 인터페이스 입니다. 안드로이드 시스템에서 구현된 추상클래스입니다.

컨텍스트는 특별한 어플리케이션 리소스에 접근을 허용합니다. 

또한  활동 시작, 방송 및 수신 의도와 같은 애플리케이션 수준 작업에 대한 호출을 허용합니다.

##### text

띄울 글씨를 리턴해 줍니다.

##### duration

유지 시간을 설정합니다.

- LENGTH_SHORT
- LENGTH_LONG

 이 있습니다.