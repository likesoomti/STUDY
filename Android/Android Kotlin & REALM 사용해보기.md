# Android Kotlin & REALM 사용해보기

## 요구사항

RealM 은 현재 Android 외에서 java 를 지원하지 않는다고한다. 꼭 Android Studio 를 사용해야 합니다.! 

또한, 밑에 조건을 만족해야되는데 왠만하면 될것 같네요.

```
1.5.1 이상 버전의 Android Studio
최신 버전의 Android SDK
7.0 이상 JDK 버전
API Level 9 이상의 모든 Android 버전을 지원합니다. (Android 2.3 진저브레드 버전 이상)
```



이 요구사항을 설치했다면, 처음부터 따라해봅시다.



## 1. 설치하기

Android Project 에서 RealM을 사용하려면, 라이브러리를 설정해 주어야 합니다.

 `build.gradle(Project)` 안에 설치해 주세요

```json
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath "io.realm:realm-gradle-plugin:3.5.0"
    }
}
```

`build.gradle(App)` 안에 플러그인을 설치합니다.

```json
apply plugin: 'realm-android'
apply plugin: 'kotlin-kapt'
```



## 2. 모델 만들기

RealM 데이터베이스를 연결하기 위한 모델을 생성할 수 있습니다.

모델을 만들때는 RealmObject를 상속받아 정의합니다.

```java
package com.soomti.saturdaychillin.MODEL
import io.realm.RealmObject
class User : RealmObject() {
    private val id: String? = null
    private val email: String? = null
    private val password: String? = null
}
```

기본키를 설정해 주기 위해  `@PrimaryKey` 어노테이션을 사용합니다. 

```java
public class Person extends RealmObject {
    @PrimaryKey
    private long id;
    private String name;
    private RealmList<Dog> dogs; // 일 대 다 관계를 정의합니다
}
```

select 를 빠르게 하기 위해 `@Index` 어노테이션을 사용합니다.

```kotlin
@Index private var email: String? = null
```

커스텀 메서드를 사용할 수 있도록 private/public/protected 를 지원합니다.

```kotlin
private fun hasLongName(): Boolean {
        return id.length() > 7
}
override fun equals(o: Any?): Boolean {
  // Custom equals comparison
}
```



## 3. Realm 연결하기  

```kotlin
// Realm을 초기화합니다.
Realm.init(context);
// 인스턴스를 받아옵니다.
Realm realm = Realm.getDefaultInstance();
```

init 은 보통 어플리케이션에서 한번만 시행합니다.기본적으로 init 을 하게 되면 `RealmConfiguration.Builder (). build ()`가 사용됩니다. 

 이때는 `application` 클래스를 상속받아 사용합니다.

## Android Application Class

공통으로 전역 변수를 사용하고 싶을 때 `Application` 클래스를 상속받아 사용합니다.

보통 프로젝트 이름과 같은 파일이름을 만들어 `extends`하여 사용하는 것 같습니다.

#### 사용 메서드

- onCreate() 

  애플리케이션이 생성될 때 호출됩니다.. 엑티비티나 서비스보다 항상 먼저 호출 되므로 진정한 진입점임.

- onTerminate() 

  애플리케이션 객체와 모든 컴포넌트가 종료될 때 호출됩니다.

- onLowMemory() 

  시스템이 메모리 리소스가 부족할 때 호출됩니다.

- onConfigurationChanged() 

  애플리케이션은 구성변경을 위해 재시작하지 않아, 변경이 필요할 경우에는 이곳에서 핸들러를 재정의 합니다.

#### 사용방법

1.  아래처럼 AndroidManifest.xml에서 name 속성으로 등록합니다.
2.  Application을 상속 받은 클래스를 생성합니다.

#### Configuration  커스텀 

만약 , 기본 구성 기능을 커스텀 하고 싶다면, 

밑에와 같은 방식으로 커스텀 할 수 있습니다.

```kotlin
RealmConfiguration config = new RealmConfiguration.Builder()
  .name("myrealm.realm")
  .encryptionKey(getKey())
  .schemaVersion(42)
  .modules(new MySchemaModule())
  .migration(new MyMigration())
  .build();
// Use the config
Realm realm = Realm.getInstance(config);
```



## 모델 추가

```kotlin
val realm = Realm.getDefaultInstance()
val user = realm.createObject(User::class.java, user_id.text.toString())
user.password = user_password.text.toString()
user.email = user_email.text.toString()
```



## 모델 읽기

```kotlin
 Realm.getDefaultInstance().use { realm ->
  realm.where(User::class.java).findAll().forEach {
    Log.d("id", "${it.id}")
    Log.d("name", "${it.password}")
    Log.d("age", "${it.email}")
  }
}
```





## Error

####'Modelname' has a primary key, use 'createObject(Class<E>, Object)' instead.

프라이머리키를 지정 안해서 생겼다..

##### after

```kotlin
// 데이터 베이스 추가
realm.executeTransaction { r ->
  val user = r.createObject(User::class.java, user_id.text.toString())
  user.password = user_password.text.toString()
  user.email = user_email.text.toString()
}
```



#### Third Party...

run  > Edit Configuration 에서 awake 빼고 지워주자.



## 참고자료

https://realm.io/kr/docs/java/latest/#getting-started

