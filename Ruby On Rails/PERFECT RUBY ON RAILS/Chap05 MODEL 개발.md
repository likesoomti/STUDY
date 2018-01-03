# Perfect Ruby On Rails - MODEL 개발

1. 액티브 레코드를 통한 데이터 베이스 접근 방법과 유효성 검사 방법을 살펴본다. 

2. migration 파일과 fixture 에 관련된 내용을 다룬다. 

## 들어가기전에

https://github.com/likesoomti/STUDY/blob/master/Ruby%20On%20Rails/DOCS/%5BModel%5D%20Model%20Basic.md

이걸 보면서 간단한 모델을 만들어 주세요. 저는 간단하게 `Book`이라는 컨트롤러를 만들었습니다. 

## 5.1 데이터 추출 기본 

### find()

    primary key로 데이터를 추출한다. 배열로 지정하는 것도 가능하다. 

##### example

```irb
irb(main):001:0> Book.find(1)
  Book Load (0.6ms)  SELECT  "books".* FROM "books" WHERE "books"."id" = ? LIMIT ?  [["id", 1], ["LIMIT", 1]]
=> #<Book id: 1, isbn: "80198", name: "Beneath the Bleeding", price: "91.64", publish: "City Lights Publishers", published: "2017-12-10", cd: false, url: "sallie.beier@upton.org", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">

irb(main):002:0> Book.find([1,2,3])
  Book Load (0.2ms)  SELECT "books".* FROM "books" WHERE "books"."id" IN (1, 2, 3)
=> [#<Book id: 1, isbn: "80198", name: "Beneath the Bleeding", price: "91.64", publish: "City Lights Publishers", published: "2017-12-10", cd: false, url: "sallie.beier@upton.org", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">, #<Book id: 2, isbn: "39673", name: "When the Green Woods Laugh", price: "48.89", publish: "Unfinished Monument Press", published: "2017-12-10", cd: true, url: "kaleigh.heller@wolf.io", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">, #<Book id: 3, isbn: "76009", name: "A Catskill Eagle", price: "19.38", publish: "Carlton Publishing Group", published: "2017-12-11", cd: false, url: "marta.watsica@tromp.net", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">]

```

### find_by( key : value)
    필드값으로 검색. key: 필드 이름, value: 값 이름 
    검색된 값중 가장 처음에 검색되는 1개의 값을 가져온다.
    1개의 값만 가져오는 메서드 
    여러 필드를 한번에 검색할 수 있다.

##### sample

```irb

irb(main):004:0> Book.find_by(publish:"City Lights Publishers")
  Book Load (0.2ms)  SELECT  "books".* FROM "books" WHERE "books"."publish" = ? LIMIT ?  [["publish", "City Lights Publishers"], ["LIMIT", 1]]
=> #<Book id: 1, isbn: "80198", name: "Beneath the Bleeding", price: "91.64", publish: "City Lights Publishers", published: "2017-12-10", cd: false, url: "sallie.beier@upton.org", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">

irb(main):005:0> Book.find_by(publish:"City Lights Publishers",price: "91.64")
  Book Load (0.2ms)  SELECT  "books".* FROM "books" WHERE "books"."publish" = ? AND "books"."price" = ? LIMIT ? [["publish", "City Lights Publishers"], ["price", "91.64"], ["LIMIT", 1]]
=> #<Book id: 1, isbn: "80198", name: "Beneath the Bleeding", price: "91.64", publish: "City Lights Publishers", published: "2017-12-10", cd: false, url: "sallie.beier@upton.org", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">
```

## 쿼리 메서드
모든 값을 불러오는 `all`과 1개의 값만 불러오는 `find` 메소드 외에도 정렬, 그룹화, 범위추출, 결합 등 복잡한 조건식은 쿼리 메서드들을 통해 처리합니다.

쿼리 메서드는 체인 형태로 코드를 작성할 수 있다. 코드 체이닝이 끝난 후 데이터 베이스에 접근한다.

#### where(조건식)
    1. 조건 필터링.find_by()와 비슷하지만 여러개의 값을 리턴합니다.
    2. AND 값을 통해 여러개의 조건을 사용할 수 있습니다
    3. placeholder를 통해 조건의 값을 변수값으로 지정하여 유동적이게 사용할 수 있습니다.

###### sample

1. **Book.where(publish:"City Lights Publishers")**

리턴 결과를 살펴보면 11개 값이 나온 것을 확인할 수 있습니다.
```irb
irb(main):006:0> Book.where(publish:"City Lights Publishers")
  Book Load (2.4ms)  SELECT  "books".* FROM "books" WHERE "books"."publish" = ? LIMIT ?  [["publish", "City Lights Publishers"], ["LIMIT", 11]]
=> #<ActiveRecord::Relation [#<Book id: 1, isbn: "80198", name: "Beneath the Bleeding", price: "91.64", publish: "City Lights Publishers", published: "2017-12-10", cd: false, url: "sallie.beier@upton.org", created_at: "2017-12-11 08:44:27", updated_at: "2017-12-11 08:44:27">]>
```

2. **Book.where(publish:"City Lights Publishers" AND price: "100")**

AND 값을 통해 여러개의 조건을 사용할 수 있습니다

3. **Book.where('publish=? AND price= ?',params[:publish,params[:price]])**

플레이스홀더 기호인 `?`를 통해 설정된 플레이스 홀더의 매개변수 값을 전달합니다. 

#### not()
    부정 조건식

#### order()
    정렬

#### reorder()
    재정렬

#### select()
    레코드 지정

#### distinct()
    중복 제거

#### limit()
    추출하고 싶은 레코드 수를 지정

#### offset()
    추출을 시작하는 위치를 지정. limit 과 같이 사용하다 

#### group()
    특정 키로 결과를 그룹화

#### having()
    Gruop By에 추가 조건 부여

#### joins()
    다른 테이블과 결합

#### includes()
    관련된 모델을 한꺼번에 추출

#### readonly()
    추출한 객체를 읽기 전용으로 변경

#### none()
    아무것도 없는 결과 집합 리턴 




## 5.5 유효성 검사 구현 

사용자가 잘못 입력한 데이터를 데이터베이스에 저장하는 것을 방지해야 됩니다. `Active Model Validation` 사용을 통해 검사를 하면 쉽게 유효성 검사를 구현할 수 있습니다.

### 5.5.1 액티브 모델을 사용한 유효성 검사 



#### acceptance
    체크박스에 체크가 되어있는지 확인한다
    매개변수
    - accept
#### confirmation
    두개의 필드가 같은지 확인한다
#### exclusion
    값이 범위에 포함되어 있지 않은지 확인한다
    매개변수
    - in
#### inclusion
    값이 범위에 포함되어 있는지 확인한다
     매개변수
     - in
#### format
    정규 표현식 패턴에 맞는지 확인한다
    매개변수
    - with
#### length
    문자열의 길이를 확인한다
    - minimum
    - maximum
    - in
    - tokenizer
    - is
    - too_long
    - too_short
    - wrong_length
#### numericality
    숫자의 대/소/ 자료형을 확인한다 
    - only_integer
    - greater_than
    - greater_than_or_equal_to
    - equal_to
    - less_than
    - less_than_or_equal_to
    - odd
    - even
#### precense 
    값이 비어있지 않은지 확인한다
#### absence 
    값이 비어있는지 확인한다
#### uniqueness
    값이 유일한지 확인한다
    - scope
        유일성 제약을 위해 사용할 필드 
    - case_sensitive 
        대소문자 구분 체크 


### 5.5.2 유효성 검사 기본 

##### sample
```ruby
class Book < ActiveRecord::Base
    validates :isbn,
        precense: true,
        uniqueness: true,
        length: {is: 17},
        format: { with: /A[0-9]{3}-[0-9]{1}-[0-9]{3,5}-[0-9]{4}-[0-9X]{1}\z/ }
    validates :title,
        precense: true,
        length: {minimum: 1, maximum: 100}
    validates :price,
        numericality: {only_integer: true, less_than: 10000}
    validates :publish,
        inclusion:{in:['jpub','hanbit','g%shon','insite']}
```

#### 5.5.3 unique 검사 

`uniqueness` 검사는 지정된 필드의 값이 중복되지 않음을 확인해 줍니다. 

##### example

```ruby
validates :isbn,
     precense: true,
     uniqueness: true,
     length: {is: 17},
     format: { with: /A[0-9]{3}-[0-9]{1}-[0-9]{35}-[0-9]{4}-[0-9X]{1}\z/ }
```
우리는 유효성 isbn 유효성 검사를 이런식으로 했는데요. 이걸 sql 문으로 변환하면

```sql
select 1 AS one FROM "books" where "books"."isbn" = '978-4-7741-5878-5 LIMIT 1
```
로 바꿔줍니다. 

여러개의 값이 중복되는 지 확인하고 싶은 경우에는 `scope`필드를 사용하여 검사할 수 있습니다.

```ruby
validates :title, uniqueness: {scope: :publish}
```