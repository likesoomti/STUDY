# ActiveRecord::NotNullViolation in Devise::RegistrationsController#create

## problem 
Not null error..

처음 가입할때는 email,password 만 받고, 
나머지는 다른 페이지에서 추가적으로 받아야 하지만 비어있으면 안되서 다른 값들은 default 값을 설정해 줬는데 ㅠㅠ 에러빵빵 

## solve 

default 설정시에 "" 라고 해주기만했는데 그럼 default 값이 설정이 안된다 몰랐당

##### before
```ruby
     ## 사용자 이름
      t.string :name, null: false, default: ""
      t.integer :gender,null: false, default: ""
```
##### after
default 설정안에 대시를 넣어주니 돌아감! 
```ruby
     ## 사용자 이름
      t.string :name, null: false, default: "-"

      t.integer :gender,null: false, default: "-"
```