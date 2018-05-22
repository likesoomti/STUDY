# Kotlin - Anko Library 적용해보기 

toast,intent 를 사용하기 위해 anko 라이브러리를 적용해 보았다

## Toast Anko

##### app gradle

```json
 compile "org.jetbrains.anko:anko-commons:$anko_version"
```

##### project gradle

```json
ext.anko_version='0.10.5'
```

를 넣으면 `Anko` 를 사용할 수 있다. 

##### sample

```java
toast("Hi there!")
toast(R.string.message)
longToast("Wow, such duration")
```

## Before

```kotlin
if (select_user == null) {
  Toast.makeText(this,"아이디가 존재하지 않습니다.",Toast.LENGTH_LONG).show()
}
else if(!(select_user.password == user_password.text.toString())){
  Toast.makeText(this,"비밀번호가 일치하지 않습니다.",Toast.LENGTH_LONG).show()
}
```

## After

```java
if (select_user == null) {
  toast("아이디가 존재하지 않습니다.")
}
else if(!(select_user.password == user_password.text.toString())){
  toast("비밀번호가 일치하지 않습니다.")
}
```



## Intent

설치하는 법은 Toast 와 동일하다 

```kotlin
val intent = Intent(this, SomeOtherActivity::class.java)
intent.putExtra("id", 5)
intent.setFlag(Intent.FLAG_ACTIVITY_SINGLE_TOP)
startActivity(intent)
// use anko 
startActivity(intentFor<SomeOtherActivity>("id" to 5).singleTop())
// dont' need flag
startActivity<SomeOtherActivity>("id" to 5)
```

## Before

```kotlin
// sample 1
if (id == null || id == "") {
  intent = Intent(applicationContext,MainActivity::class.java)
}
else {
  intent = Intent(applicationContext,IndexActivity::class.java)
  intent.putExtra("id",id)
  intent.putExtra("email",email)
}
startActivity(intent)

// sample 2
Toast.makeText(applicationContext,".",Toast.LENGTH_LONG).show()
intent = Intent(applicationContext,MainActivity::class.java)
startActivity(intent)
finish()           

// sample 3
intent = Intent(this,IndexActivity::class.java)
intent.putExtra("id",select_user.id)
intent.putExtra("email",select_user.email)
startActivity(intent)
finish()
```

## After

```kotlin
// sample 1
if (id == null || id == "") 
  startActivity<MainActivity>()
else 
  startActivity<IndexActivity>("id" to id, "email" to email)

// sample 2
toast("회원가입이 완료 되었습니다")
startActivity<MainActivity>()
finish()

// sample3
startActivity<IndexActivity>("id" to select_user.id, "email" to select_user.email)
finish()
```



## Useful `Intent` callers

Anko has call wrappers for some widely used `Intents`:

| Goal            | Solution                                 |
| --------------- | ---------------------------------------- |
| Make a call     | `makeCall(number)` without **tel:**      |
| Send a text     | `sendSMS(number, [text])` without **sms:** |
| Browse the web  | `browse(url)`                            |
| Share some text | `share(text, [subject])`                 |
| Send a email    | `email(email, [subject], [text])`        |