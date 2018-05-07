

# 로그인 화면 만들기

로그인 화면을 만들면서, 코틀린으로 만드는 안드로이드에 적응을 해보자.

## 요구사항

```
* 간단한 로그인 화면을 구성한다.
1) 회원가입 :
  - 아이디, 비밀번호, 비밀번호 확인, 이메일로 구성
  - 회원 가입 시 데이터가 다 들어갔는지 확인
  - 회원 가입 시 이메일에 @ 있는지 확인
  - 회원 가입 시 패스워드와 패스워드 확인 문자가 동일한지 확인
  - SharedPreference을 사용하요 캐쉬에 아이디, 비밀번호 1개만 저장
2) 메인화면 :
  - 아이디 , 비밀번호가 입력 되었는지 확인
  - 저장된 아이디, 비밀번호가 일치 했을 경우만 로그인 성공
    (SharedPreference에 저장된 값과 사용자가 입력한 값과의 비교)
  - 로그인 성공화면에 입력된 ID 전송 (Intent.putString(key, value)
3) 로그인 성공화면 :
 - 메인화면에서 사용자가 입력한 ID 값을 전달 받아 보여줌 (Intent.getStringExtra 사용)
 - (선택) 메인화면에서 초기화버튼이 있어 저장된 아이디, 비밀번호를 초기화 하고 회원가입 페이지로 이동한다.
4) 공통
 - Kotlin으로 작성할 것
 - 아이디, 비밀번호, 비밀번호확인, 이메일은 무조건 1줄로만 나오게 할 것
 - 비밀번호, 비밀번호 확인은 inputType 변경할 것(layout)
 - 이메일의 inputType 은 Email로 변경할 것 (textEmailAddress)
```



### 1. 액티비티 만들기  

- 회원가입 (SignUpActivity)
- 메인화면 (MainActivity) = LoginActivity
- 로그인 성공화면 (IndexActivity)

#### 회원가입 액티비티 Layout 만들기

```
  - 아이디, 비밀번호, 비밀번호 확인, 이메일로 구성
  - 회원 가입 시 데이터가 다 들어갔는지 확인
  - 회원 가입 시 이메일에 @ 있는지 확인
  - 회원 가입 시 패스워드와 패스워드 확인 문자가 동일한지 확인
  - SharedPreference을 사용하요 캐쉬에 아이디, 비밀번호 1개만 저장
```

##### SignUpActivity.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SignupActivity">

    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginStart="8dp"
        android:layout_marginTop="16dp"
        android:gravity="center_vertical|center_horizontal"
        android:text="회원가입 하기"
        android:textSize="20dp"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.487"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/email"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="이메일 입력" />

    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="비밀번호 입력" />

    <EditText
        android:id="@+id/passwordMore"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="비밀번호 다시 입력" />

    <Button
        android:id="@+id/createButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="회원가입버튼" />
</LinearLayout>
```

##### SignUpActivity.java

```kotlin
package com.soomti.loginproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email by lazy {
            findViewById(R.id.email) as EditText
        }
        val password by lazy {
            findViewById(R.id.password) as EditText
        }
        val password2 by lazy {
            findViewById(R.id.passwordMore) as EditText
        }
        val createButton by lazy {
            findViewById(R.id.createButton) as Button
        }

        createButton.setOnClickListener{
            view -> Toast.makeText(this,"test",Toast.LENGTH_LONG).show()
        }

    }
}
```



## 코틀린과 자바 비교해보기

### 1. 바인딩(?)

처음부터 굉장히 버벅였다. 자바에서는

```java
public class SingleItemView extends LinearLayout {
    TextView singername;
    TextView singerphone;
    ...
    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.singeritem,this,true);
        singername = (TextView)findViewById(R.id.singername);
        singerphone = (TextView)findViewById(R.id.singerphone);
    }
}
```

대충 이런 모양으로 클래스 인스턴스에 선언을 하고 , 안에서 `findViewById`를 통해 이어주지만,

코틀린에서는 `Nullable `의 제한으로 그렇게 선언이 안된다. 

```kotlin
val email by lazy {
            findViewById(R.id.email) as EditText
}
```

이런식으로 선언을 하는데, 구글링을 해보니 보통 익스텐션을 깔아 바인딩 처리를 해줌으로써,

`ButterKnife` 또한 안쓴다고한다. 일단 프로젝트를 진행해보고 알아봐야겠다.

### 2. 온 클릭 리스너

 람다를 사용하였다

`java`  에서는 `TextView` 에대한 데이터를 가져올때 `getText()`를 쓰지만, 코틀린에서는 프로퍼티를 제공해주기 때문에

`text` 로 가져오는 것 같다.

```kotlin 
       createButton.setOnClickListener{
            var email = email.text.toString()
            var password = password.text.toString()
            var password2 = password2.text.toString()
            //1. 이메일 정규표현식이 적합하지 않으면
            if (emailCheck(email)) {
                Toast.makeText(this,"이메일 표현이 맞지 않습니다.",Toast.LENGTH_LONG).show()
                flag = false

            }else if (nullCheck(email) || nullCheck(password) || nullCheck(password2)) {
                Toast.makeText(this,"공란이 있습니다.",Toast.LENGTH_LONG).show()
                flag = false
            }else if (passwordCheck(password,password2)) {
                Toast.makeText(this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show()
            }

        }
```

### 3. 함수

비슷하다

```kotlin
// 이메일 정규표현식
fun emailCheck(email:String):Boolean {
    //val regex = "/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}\$/i;".toRegex()
    //return !regex.containsMatchIn(email)

    return !(email.contains("@"))
}
fun nullCheck(str:String) :Boolean {
    return (str=="")
}
// 비밀번호 같은지 체크
fun passwordCheck(password1:String,password2:String):Boolean{
    return !(password1 == password2)
}
```



### 2. 로그인 화면 만들기

```
회원가입 버튼을 누르면 회원가입 페이지로 보낸다.
로그인을 하면 맞는지 유효성 검사를 한다.

```

#####  레이아웃

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:orientation="vertical"
    android:layout_height="match_parent"
    tools:context=".MainActivity">
    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="로그인 화면"/>

    <EditText
        android:id="@+id/email"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="이메일 "
        android:inputType="textEmailAddress" />

    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="비밀번호"
        android:inputType="textPassword" />
    <Button
        android:id="@+id/createButton"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="로그인버튼" />

</LinearLayout>
```



#### 익스텐션 플러그인 적용

`findViewByID()` 를 사용하지 않고 사용하는 방법에는 

```
apply plugin: 'com.android.application'
apply plugin: 'kotlin-android'
apply plugin: 'kotlin-android-extensions' // 익스텐션 플러그인 적용
```

이하의 플러그인을 적용하면 되는데, 알고보니 안드로이드 3.0 버전 이상이어서 그런지 적용되어있다.

알아서 바인딩을 사용하면된다... 우왕 그냥 xml 아이디를 사용하면 된다.

```kotlin
  // 로그인 링크 -> 로그인 성공 화면
        movesignup.setOnClickListener{
            Toast.makeText(this,"페이지 이동시킬거임",Toast.LENGTH_LONG);
        }
```



### 3. 인텐트 사용하기

인텐트 사용에 익숙치않아..(까먹음) 검색을 해봤다.

##### java

```java
/* MainActivity.java */
Intent myIntent = new Intent(this, SecondActivity.class);
myIntent.putExtra("번호", 12345);
myIntent.putExtra("메시지", "이것이 메시지의 vaule입니다.");
/* 하나의 인텐트에 여러 형태, 여러 개의 extra 값을 담을 수 있다 */
```

##### kotlin

```java

val nextIntent = Intent(this, SecondActivity::class.java)
nextIntent.putExtra("nameKey", "nachoi")
startActivity(nextIntent)
```

`putExtra` 한 후, 받은 액티비티에서는  에서 `getStringExtra`, `getBooleanExtra` 등 자료형에 맞는 메소드를 사용한다.

코틀린은 널 체크가 필수이기 때문에  `if hasExtra`로 체크하는것이 좋다고 하는 참고 자료를 보았다.

```kotlin
package com.soomti.loginproject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // 검사 먼저
        if(intent.hasExtra("email")) {
            homeemail.text = intent.getStringExtra("email")
        }
    }
}
```



### TO DO

```
* 간단한 로그인 화면을 구성한다.
1) 회원가입 :
  - SharedPreference을 사용하요 캐쉬에 아이디, 비밀번호 1개만 저장
2) 메인화면 :
  - 아이디 , 비밀번호가 입력 되었는지 확인
  - 저장된 아이디, 비밀번호가 일치 했을 경우만 로그인 성공
    (SharedPreference에 저장된 값과 사용자가 입력한 값과의 비교)
3) 로그인 성공화면 :
 - (선택) 메인화면에서 초기화버튼이 있어 저장된 아이디, 비밀번호를 초기화 하고 회원가입 페이지로 이동한다.
4) 공통
 - Kotlin으로 작성할 것
 - 아이디, 비밀번호, 비밀번호확인, 이메일은 무조건 1줄로만 나오게 할 것
 - 비밀번호, 비밀번호 확인은 inputType 변경할 것(layout)
 - 이메일의 inputType 은 Email로 변경할 것 (textEmailAddress)
```



## error

```
java.lang.NoClassDefFoundError: Failed resolution of: Lcom/soomti/loginproject/LoginOkActivity;
```

액티비티를 찾을 수없다는 에러 발생.만들어줬고 동일한 코드로 SignUpActivity 는 움직인다. 

클래스 로딩 단계에서 못찾는다는 건데, 내가 같은 이름으로 삭제하고 만들어서 그런가? 

#### 참고자료

https://nachoi.github.io/studynote/2017/11/28/Android-Kotlin-putExtra.html