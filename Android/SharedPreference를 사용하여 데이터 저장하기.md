# SharedPreference

안드로이드에서 데이타를 저장할때 사용하는 방법 중 하나.



###  안드로이드에서 데이터 저장하는 방법

- 파일 생성
- 안드로이드 내부 DB 사용
- SharedPreference 사용

으로 나눌 수 있는데, 여기서  **SharedPreference** 를  알아보자.



## SharedPreference란

SharedPreference는 ` Key/Value` 형태로 데이타 저장할 수 있다. 

이 클래스를 통해 값을 저장하면,  내부적으로는 XML 파일로 저장이 된다.

사용법이 매우 간단하기 때문에

1. 일반적인 설정값이나 
2. 세션 정보
3. 사용자 정보
4. 간단하고 가벼운 데이터

를 저장하는데 주로 사용한다.

**안드로이드 파일 시스템 내에 XML 파일로 접근이 되기 때문에, 보안적으로 안전하지 않을 수 있으니 암호화가 필요하다.**



## 사용방법

실제로 사용하는 방법은 `Activity`나 `Service`  클래스에서 `Context`를 가지고 온후, 

해당` Context`를 통하여 `SharedPreference`를 생성하여, 데이터를 저장하면 된다.



## Example

```java
public void writeSharedPreference(View view){

        EditText txtValue = (EditText) findViewById(R.id.txtValue);
        String value = txtValue.getText().toString();


        // 1. get Shared Preference
        SharedPreferences sharedPreference
                = this.getSharedPreferences("MYPREFRENCE", Context.MODE_MULTI_PROCESS | Context.MODE_WORLD_READABLE); 

        // 2. get Editor
        SharedPreferences.Editor editor = sharedPreference.edit();

        // 3. set Key values
        editor.putString("MYKEY",value);
        editor.putString("KEY2", "VALUE2");

        // 4. commit the values
        editor.commit();

 }
```

파헤쳐보자.



## 1.getSharePreference() 

```java
SharedPreferences sharedPreference
                = this.getSharedPreferences("MYPREFRENCE", Context.MODE_MULTI_PROCESS | Context.MODE_WORLD_READABLE); 
```

`ApplicationContext` 로 부터 `getSharedPreferences` 를 가져올 수 있다.

매개변수는 *("`set Name`","`Access Mode`")* 가 들어간다. 

###  Access Mode 

이 데이터에 대한 모드를 설정할 수 있다. `MODE_MULTI_PROCESS` 는 여러 프로세스 간 공유가 가능하고, 읽을 수 있는 모드이다. 

**Access Mode** 는 보통 `MODE_PRIVATE`을 사용한다.

- `MODE_PRIVATE`

   다른 애플리케이션 접근 불가하고, 이 SharedPreference를 만든 애플리케이션만 접근이 가능하게 한다. 

- `MODE_WORLD_READABLE`

   다른 애플리케이션도 읽기를 허용한다.

- ` MODE_WORLD_WRITABLE` 

  다른 애플리케이션도 쓰기를 허용한다.



## SharedPreferences.Editor editor = sharedPreference.edit();

`context`에서 받아온 `sharedPreference` 를 가지고 데이터를 기록하기 위해서는  `SharedPreferences.Editor` 인스턴스를 얻어야 한다. 이후 데이터의 `key`,`value` 값을 설정할 수 있다.

##### 3.  editor.putString("MYKEY",value);

데이터를 세팅해준다. 

##### 4. editor.commit();

저장한다. `commit()` 이 되야 xml 에 기록이 된다.



##### xml 경로

안드로이드 스튜디오 우측 아래에보면 `device file explorer` 가 있다.

 `data/data/packagename/sharedPreference` 에 가보면 저장된 xml 파일을 확인할 수 있다.

