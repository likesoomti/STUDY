# 안드로이드 프로젝트 구조

```
http://codeasy.tistory.com/6?category=751343
```

를 보고 정리한 글입니다.



안드로이드 스튜디오 프로젝트를 만들고 키면, 3중 구조로 되어있습니다.

#### 좌측

프로젝트 구조

#### 우측

파일 내용. 작업 영역 (코드)

#### 하단

디버깅, 실행 모드 입니다.



프로젝트 구조를 이해하기 위해 중요한 좌측 메뉴를 살펴보겠습니다.

##### 프로젝트 상위 구조

프로젝트의 상위 구조를 살펴보면 

```
app
GradleScript
```

가 존재합니다.

##### app

우리가 코딩해야 할 파일들이 있습니다.

##### Gradle Scripts

애플리케이션을 빌드하기 위한 설정/옵션/라이브러리 정보들이 들어있습니다.



#### App 살펴보기

```
app
  - manifests
  - java
  - res
```

app 폴더는 크게 manifests, java, res 로 나눠집니다.

##### manifest

`AndroidManifest.xml` 파일이 존재합니다. 

안드로이드 어플을 구동하기 위한 설정 값을 관리해 주는 곳입니다.

##### Java

클래스를 관리하는 폴더입니다.

##### res

Resource 폴더입니다. UI 와 관련한 파일, 디자인 리소스, 문자열 리소스를 담고있는 폴더입니다. 

*안드로이드에서 패키지는 폴더와 같습니다.*



따라서, 안드로이드는 크게

##### Manifest , Java , Resource , Gradle 패키지로 이루어져있습니다.



#### 안드로이드 필수 설정 파일

안드로이드 프로젝트를 설정할 때 꼭 알아야하는 파일입니다.

##### manifests/AndroidManifest.xml 

를 먼저 살펴보면,

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.soomti.firstapp">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

처럼 되어있습니다.

`<> </>` 가 계층 형식으로 되어있는데요. xml 기본 구조입니다.

<> 안에 이름으로 분리를 하자면 

1. manifest
   1. application
      1. activity
         1. intent-filter
            1. action
            2. category

로 되어있네요. 

안드로이드 프로젝트는 "application" 위에서 "activity" 가 실행되는 구조라는 것만 알면 됩니다. 

##### 1. manifest

```
https://developer.android.com/guide/topics/manifest/manifest-intro.html?hl=ko
```

모든 애플리케이션에는 루트 디렉터리에 `AndroidManifest.xml` 파일(정확히 이 이름이어야 함)이 있어야 합니다. 매니페스트 파일에서는 Android 시스템이 앱의 코드를 실행하기 전에 확보해야 하는 앱에 대한 필수 정보를 시스템에 제공합니다.

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.soomti.firstapp">
```

`package="com.soomti.firstapp`  에 있는 프로젝트를 말합니다.

##### 2. application

```xml
    <application
        android:allowBackup="true" // 응용 프로그램이 백업 및 복원 인프라에 참여할 수 있는지 여부. 
        android:icon="@mipmap/ic_launcher" // 응용프로그램 아이콘 설정 
        android:label="@string/app_name" // 응용프로그램 이름 설정 
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
```



##### 과 build.gradle





#### Java 폴더 살펴보기

프로젝트를 만든 패키지 폴더에 들어가면 MainActivity.java가 있습니다.

##### MainActivity.java

모든 프로젝트에 있어야 하는 기본 파일입니다. 

```kotlin
package com.soomti.firstapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

```

` setContentView(R.layout.activity_main)` 

layout 패키지 아래 activity_main 을 view 로 연결합니다.

따라서 activity 와 view 파일은 한쌍이고,

activity 파일에서 xml 파일을 연결해 줌을 알 수 있습니다. 



#### Res 파일 살펴보기

레이아웃과 관련한 파일들이 존재합니다.

##### layout

화면 레이아웃 파일들이 존재합니다. 파일 디자인의 뼈대들이 모여져 있는 곳입니다.

##### Drawable

이미지 저장 패키지 입니다. 

##### mipmap

launcher 이미지는 앱 아이콘을 저장하는 곳입니다.

만약, 파일 명을 바꾼다면 기본 설정 되어있는 `AndroidMenifest` 도 변경해야합니다.

##### values 

1. colors  컬러 값을 저장하는 파일입니다.
2. strings.xml 문자열을 저장하는 파일입니다.
3. styles.xml 스타일을 저장하는 패키지 입니다.



