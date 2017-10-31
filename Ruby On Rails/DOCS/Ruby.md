
```
irb(main):001:0> name = 'eric'
=> "eric"
irb(main):002:0> puts name
eric
=> nil
irb(main):003:0> puts "name login "
name login
=> nil
irb(main):004:0> puts "#{name} login "
eric login
=> nil
```

#### 입력

입력받는 명령어 : ```gets```

받으면 여분의 줄이 생김 

여분 제거 옵션 : .chomp

#### 출력

Puts “”
따옴표 안의 내용출력과 함께 엔터까지 한 번에 출력된다
Print ””
엔터없이 따옴표 안의 내용만 출력된다



#### 연산 

## 루비의 흐름 제어

#### = 

할당연산자. ```x = y ```는 x의 변수에 y 의 값을 할당한다. 

보통 수학에서는 = 가 같다인데, 프로그래밍에서는 == 를 사용한다.

#### 루비의 흐름 제어

~ 미만 : <

~ 이하 : <= 

~ 이상 : >=

~ 초과 : 