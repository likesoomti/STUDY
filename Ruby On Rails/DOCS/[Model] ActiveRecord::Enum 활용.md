# ActiveRecord::Enum 활용해보기

point 적립에 대한 데이터베이스 생성을 하면서 Enum형 타입을 사용해야 되는 일이 발생하였습니다. 야매로 할까 하다가 생각보다 자료가많아 공부한 내용을 정리해 보았습니다.

## 어플리케이션 구축 

ENUM 을 실행해보기전에 기본적인 테스트 어플리케이션을 생성해 보겠습니다.
##### 1. rails web framework 생성하기

```bash
$ rails new enumTest
```

##### 2. ENUM을 생성할 Model 만들기

point 적립을 위한 model을 구성하고 있었으니, 그에 대한 Scaffold를 생성해 봅시다
```
$ Rails g scaffold point score:integer description
```

그럼 해당하는 Point 의 MVC 모델이 생성됩니다.

##### 3. Enum 설정해주기

**app/models/point.rb** 에 가서 enum을 설정해 줍니다. 

###### app/models/point.rb
```ruby
class Point < ApplicationRecord
    enum description: { :point_accept :0,:point_reject,:point_wait }
end
```
##### 4. Database 저장하기

```
$ rake db:migrate
```

##### 5. 확인해보기

```irb
irb(main):025:0> p = Point.new
=> #<Point id: nil, score: nil, description: nil, created_at: nil, updated_at: nil>
irb(main):026:0> p.description = 4
=> 4
irb(main):027:0> p.description
=> "fail"
```

4의 값을 저장했지만, fail 값이 나오는 것을 알 수 있습니다. 이런식으로 enum 값을 활용할 수 있습니다.


##### 숫자 출력하고싶은경우
지정된 숫자를 출력하고 싶은 경우, 모델 이름을 통해 숫자를 출력할 수 있습니다.

###### 모델명.컬럼복수형[:enum value]
```irb
irb(main):028:0> Point.descriptions[:accept]
=> 0
irb(main):029:0> Point.descriptions[:fail]
=> 4
```