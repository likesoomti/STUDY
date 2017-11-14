# ArgumentError - comparison of String with 0 failed

integer argument가 들어가는 값에 string 값을 넣어서 발생 하는 오류

## ploblem

View 에서 select 태그로 받은 값을 
Date.new로 저장하려고 하니 에러가 발생

##### Error Code
```ruby
birthday = Date.new(params["date"].values.join(","))

puts params["date"].values.join(",").to_s
# => 2017,11,13
```

왜 안되지? 해서 밑에 코드 실행

##### check 
```ruby        
    puts Date.new(2017,11,13)
    # => 2017-11-13
```

열라잘됨..왜지 하고 독스를 찾아보니 

##### ruby docs
```ruby
require 'date'

Date.new(2001,2,3)
 #=> #<Date: 2001-02-03 ...>
Date.jd(2451944)
 #=> #<Date: 2001-02-03 ...>
Date.ordinal(2001,34)
 #=> #<Date: 2001-02-03 ...>
Date.commercial(2001,5,6)
 #=> #<Date: 2001-02-03 ...>
Date.parse('2001-02-03')
 #=> #<Date: 2001-02-03 ...>
Date.strptime('03-02-2001', '%d-%m-%Y')
 #=> #<Date: 2001-02-03 ...>
Time.new(2001,2,3).to_date
 #=> #<Date: 2001-02-03 ...>
```

..! 엥 맞자나 싶었지만 아니었다.

내가 입력한 값은

`params["date"].values.join(",")`으로 string 값으로 리턴이 된다. 

위에 입력한 
```ruby
puts Date.new(2017,11,13)
```
는 

`( to_i , to_i , to_i )` 로 들어갔던것... 


비슷해서 아왜안되지ㅠㅠ 싶었따 원인을 못찾았다. 

## solve

Date.parse() 메서드는 String 을 통해 보내줄 수 있다. parse를 써서 해결 

```ruby
Date.parse(params["date"].values.join("-"))
```