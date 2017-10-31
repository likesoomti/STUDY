# Link Helper
링크 관련 뷰 헬퍼 정리

## link_to(body,url [,html_opt])
하이퍼 링크 ```<a></a>``` 생성 헬퍼 
* ```body``` : 링크 텍스트 
* ```url``` : 링크 대상 URL(url_for 메서드 참고)
* ```html_opt``` : 동작 옵션

#### options

- ```method ```: 링크 이동할때 사용할 http 방식 
- ```remote``` : Ajax Link
- ```data ``` : Data 속성 
- ``` attr name ```: id, class, style 속성 

###### sample

```html
<%= link_to 'apply', 'https://github.com/likesoomti/STUDY' %>

# => <a href="https://github.com/likesoomti/STUDY">apply</a>

<%= link_to 'Top', {controller: :hello, action: :index }, id: :link, class: :menu %>

# => <a id="link" class="menu" href="/home/index">Top</a>
```

## url_for(opts)
매개 변수로 주어진 정보로 url 문자열 생성 잘 안씀

#### options

- ```controller``` : controller name
- ```action``` : action name
- ``` host ``` : host name
- ```protocol ``` : protocol
- ``` anchor ``` : anchor name  -> 맨 뒤에 # 으로 붙는다
- ```only_path``` : 상대 url return 여부 -> false 시 절대 경로 리턴 
- ```trailing_slash``` : 끝에 '/' 추가 여부 
- ```user ```  : using http auth user name
- ```password``` : using http auth password

###### sample

```html
<%= url_for(action: :new) %> <br/>
# => /home/new 
<%= url_for(controller: :home , action: :index, id: 5, anchor: 'rails', charset: 'utf8' ) %> <br/>
# => /home/index?charset=utf8&id=5#rails
<%= url_for(action: :login, controller: :members, only_path: false, protocol: 'https') %> <br/>
# => https://localhost:3000/members/login 
<%= url_for(:back) %>
# => javascript:history.back()
```

#### setting basic options

##### ```default_url_options```
```controller``` 에 ```default_url_options``` 를 오버라이드 하면 모든 링크에 하위 코드를 지정 가능하다
###### sample

```ruby
def  default_url_options(options= {})
    { charset: 'utf-8' }
end
# 모든 링크 뒤 /view/new?charset=utf8 리턴  
```


## link_to_if(condition,name[,url[,html_opt]],&block), link_to_unless(condition,name[,url[,html_opt]],&block)

##### link_to_if 

- condition이 true 인 경우 ```<a>``` 태그를 출력
- false인 경우에는 ```name```을 기반으로 고정 텍스트를 출력

##### link_to_unless

- condition이 false 인 경우 ```<a>``` 태그를 출력
- true인 경우에는 ```name```을 기반으로 고정 텍스트를 출력

#### options
- ```condition``` : 조건식
- ```name``` : link_text
- ```url``` : link url
- ```html_opt``` : html 동작 option
- ```&block``` : 대체 내용물 

###### sample 
```ruby 
<% @user = true %>

<%= link_to_if @user, 'link_to_if_true', controller: :home, action: :index %>
# => <a href="/home/index">link_to_if_true</a>

<%= link_to_if !@user, 'link_to_if_false', controller: :home, action: :index %>
# => link_to_if_false

<%= link_to_unless @user, 'link_to_unless_true', controller: :home, action: :index %>
# => link_to_unless_true


<%= link_to_unless !@user, 'link_to_unless_false', controller: :home, action: :index %>
# => <a href="/home/index">link_to_unless_false</a>
```

## link_to_unless_current(name,[url[,html_opt]],&block)

링크가 현재 있는 링크일 경우, a 태그를 제외한 문자 출력 

공통 레이아웃 페이지에서 사용한다

- ```name``` : link_text
- ```url``` : link url
- ```html_opt```: html option
- ```&block``` : 대체 내용 

###### sample

```html
<%= link_to_unless_current 'current_page' ,action: :index %>  <br />
# => current_page  <br />

<%= link_to_unless_current 'no_current_page' ,action: :new %>  <br />
# => <a href="/home/new">no_current_page</a>  <br />
```

## mail_to(address,[,name [,opt]])
지정된 메일 주소로 mailto: 링크 생성

- ```address``` : 메일주소
- ```name``` : 링크 텍스트 
- ```opt``` : 동작 옵션 

#### options

- ```subjects``` : 제목
- ```body``` : 내용
- ```cc ``` : 참조
- ```bcc ``` : 숨은 참조 

###### sample
```html 
<%= mail_to 'soomti@haha.com' %> <br />
# => <a href="mailto:soomti@haha.com">soomti@haha.com</a>

<%= mail_to 'soomti@haha.com', 'question here' %> <br />
# => <a href="mailto:soomti@haha.com">question here</a>

<%= mail_to 'soomti@haha.com', nil, subject: 'question', cc: "test@test.com" %> <br />
# => <a href="mailto:soomti@haha.com?cc=test%40test.com&amp;subject=question">soomti@haha.com</a>
```
