# Active Record

## Active Record 란 
```MVC``` 패턴 중 ```MODEL``` 부분에서 사용하는 라이브러리

 **O/R(Object/Relational)** 맵퍼를 사용하여 관계형(```RDBMS```) 데이터 베이스를 객체로 조작하는 방법을 제공한다


### MODEL ??
```MVC``` 의 ```Model``` 부분으로, 데이터에 관련된 부분이다

- 데이터 가공
- 외부 서비스에서 데이터에 접근
 
 위와 같은 처리를 담당한다

 ### O/R Mapper ?? (Object/Relational)

- 관계형 데이터베이스```(RDBMS)``` 를 App에서 사용하는 객체처럼 만들어주는 라이브러리
- 하나의 테이블을 하나의 객체로 생성한다

#### impedance mismatch (객체 관계 불일치)
: ```Database```와 ```Application```의 구조적인 차이 

**App**  에서 데이터를 담는 ```Object``` 와, **RDBMS** 의 데이터를 담는 ```Table``` 은 구조적으로 다르다 ( 개미집과 두꺼비집!? ) 

따라서 수동적으로 ```RDBMS```의 데이터를 뽑아서 App에 가져온 뒤 ```Object```의 구조로 가공하여 사용하는 불편함이 존재함이 있다

```O/R Mapper```를 통해 이러한 불편함을 제거한다. (짱편함)

## ActiveRecord 의 규칙 

### 1. 명명규칙

Active Record는 Database의 ```TABLE```과 App의 ```Object```를  자동으로 매핑해 주기 때문에 편리하지만 몇몇의 규칙을 지켜야한다. 

1. ```App```의 Model Class 단수형으로, ```데이터베이스``` Table  Name은 복수형을 사용한다.
2.  Name의 복수형이 불규칙 변형일 경우에도 복수형으로 변환해준다
3.  Name이 복합어(2단어 이상)인 경우, Ruby의 CamelCase 명명규칙을 따른다 
4.  테이블의 이름은 소문자에 밑줄을 사용한다
        
###### sample   

| Model Class | Database Table | ETC        |    
| ----------- | :------------  | :---------:|
| Book        | Books          |            |
| LineItem	  | line_items     | 복합어 사용   |   
| Mouse       |	mice           | 불규칙 복수형  |
| Person	  | people         |             |


### 2. 스키마 명명규칙

#### Schema???

```Schema```는 ```Database``` 대한 구조, 표현 방법, 관계를 형식 언어로 정의한 구조이다. ```Active Record```에서는 ```Table```을 ```Object```로 바꿔주기 때문에 사용 목적에 따른 규칙이 필요하다.

1. **Foreign keys** : 테이블 명의 단수형 id (ex: item_id) Active Record가 모델간의 관계를 설정할때 사용 

2. **Primary Keys**  :  Integer 형 id 이름을 기본키로 가진다 `Active Record Migrations` 시 자동으로 생성해준다.

#### Active Record instance Options
* 밑에 컬럼명은 예약어이기 때문에 사용을 피해하는 것이 좋다.

| Options     | Description                    |
| ----------- | :-----------------------------:|
| `created_at`  | 레코드가 생성된 시점의 시각을 자동으로 저장| 
| `updated_at`  | 레코드가 갱신된 시점의 시각을 자동으로 저장|
| `lock_version`| 모델에 optimistic locking을 추가    |
| `type`        | 모델에 Single Table Inheritance를 |사용하는 경우 추가
| `관계명_type`   | Polymorphic Associations의 종류를 저장|
| `테이블명_count`| 관계 설정에 있어서 속해있는 객체 숫자를 캐싱하기 위해서 사용 |


