# 안드로이드 웹뷰 만들기

생각보다 간단하고 쉽다!

#### 1. New project > empty project

![Alt text](img/webview0.png)

프로젝트를 생성합니다.

#### 2. App > res > layout > activity_main.xml

1. design 탭을 누르고, container -> webView 클릭

   ![Alt text](img/webview1.png)


2. text 부분은 아래로 바꿔주세요

   ```xml
   <?xml version="1.0" encoding="utf-8"?>
   <LinearLayout
       xmlns:android="http://schemas.android.com/apk/res/android"
       android:layout_width="match_parent"
       android:layout_height="match_parent"
       android:orientation="vertical"
       >
       <WebView
           android:id="@+id/webView"
           android:layout_width="match_parent"
           android:layout_height="match_parent">
       </WebView>
   </LinearLayout>

   ```

   ​

#### 3. manifests/AndroidMenifest.xml

인터넷 사용 퍼미션을 추가해 줍니다.

```xml
<uses-permission android:name="android.permission.INTERNET"/>
```

만약에 액션바를 없애고 싶다면  theme 부분을 밑처럼 변경해 주세요

```xml
     android:theme="@style/AppTheme">
```

#### 4. styles.xml

```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
```

#### 5. Main Activity 채우기

```java

    private WebView webView; // 웹뷰
    private WebSettings webSetting; //웹뷰 세팅
    private static final String url = "https://www.url.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webView = (WebView) findViewById(R.id.webView);
      
        webView.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게 막아준다
        webSetting = webView.getSettings(); // 세부 세팅을 등록한다
        webSetting.setJavaScriptEnabled(true); // 자바스크립트 사용을 허용한다

        webView.loadUrl(url); // 연결할 웹뷰 url

    }

    // 뒤로 가기 버튼 설정
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode,event);
    }
}

```



하고 run 하면 실행 됩니다 :)

#### 6. 테마 변경

셀렉트 폼이 까맣게 나오길래 확인했더니, 테마가 dark 였다.

```xml
 android:theme="@style/AppTheme"
```

