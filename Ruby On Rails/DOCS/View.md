# View 
```ROR```의 MVC 요소 중  **V** 에 해당하는 역할로, 사용자 인터페이스를 담당한다

## View Basic 

#### ```.erb(Embed RuBy)```

```Rails``` 에서는 **VIEW** 부분에 사용하는 템플릿
```HTML```에 ```Ruby``` 스크립트를 삽입한 구조

- ```HTML```기반이기 때문에 조건,분기문을 사용하여 정적인 ```HTML```의 파일을 동적으로 사용

- ``` View Helper ``` 를 이용해 입력 양식 요소 등을 간단하게 생성가능

#### 1. 템플릿 변수 
```(.erb)``` 에서 사용하는 변수 
- 액션 메서드(Controller)의 데이터를 뷰
- 템플릿 변수를 통해 뷰와 컨트롤러가 연결된다

###### sample

```ruby on rails 
# Controller .rb
@msg = "hello world..!"
# Template  .erb
<%= @msg %>
```

#### 1. 템플릿 파일 작성 (.erb)

#### 위치
``` /app/views/controller/action_name.erb```
 
액션메서드가 실행 시 대응되는 템플릿 파일을 찾아 실행

#### 템플릿 파일 지정
기본 템플릿 지정 : ```controller/action_name```이나, **render method** 를 통해 자유롭게 지정할 수 있다.

###### sample

```ruby on rails 
  def view
    @msg = "hello World"

    # app/views 폴더에서의 경로를 지정한다 확장자 안붙혀도됨
    render 'hello/special'
  end
```
####  .erb 파일에 ruby code를 사용

```.erb```는 궁극적으로 ```HTML``` 기반이기때문에, ```.erb``` 파일에 ruby 사용 시 블록을 사용한다. 

###### sample
```ruby on rails 
# 임의의 루비 코드 작성
<% price = 1000 %>
# 출력 코드 작성 
<%= price %>
```

#### 템플릿 변수 지정

변수 이름앞에 @ 를 붙힌다. 붙히지 않으면 action으로 안들어간다
```ruby on rails
@msg = "hello world"
```

#### 뷰 자동생성
컨트롤러를 만들 시 컨트롤러 이름 다음 템플릿 파일 이름을 넣으면 자동으로 생성해준다

###### sample
```ruby
# hello controller 에 show 와 index erb 파일이 생성 
$ rails g controller hello show index 
```


#### 레이아웃 템플릿
공통 디자인을 한꺼번에 적용할 수 있다

- 사이트 구조 일관성 부여 
- 개별 템플릿에는 페이지 자체 내용만 작성 
- 개발 생산성, 보수성, 사용자의 편리성 

#####  경로
```app/views/layouts/application.html```
###### sample
```html
<!DOCTYPE html>
<html>
    <head>
        <title>Rails Book </title>

        <%= stylesheet_link_tag "application",media: "all", "data-turbolinks-track" => true %>
        <%= javascript_include_tag "application","data-turbolinks-track" => true %>
        <% csrf_meta_tags %>
    </head>
    <body>
        <!-- 이 부분에 .erb 파일이 들어간다 -->
        <%= yield %>
    </body>
</html>
```

##### erb 표준 주석 구문
```<%# %>```
##### ruby 주석 구문
``` # ```
##### HTML 주석 구문
``` <!-- -->```


## View Helper
 긴 html양식을 간단한 헬퍼를 통해 지정할 수 있다.
 

### ```form_tag([url_opts[,opts]]) ```

#### Definition
- 범용적인 입력 양식 헬퍼

- 검색 키워드 또는 조건을 입력받는 양식들에 사용

- csrf 방지 구성

##### options
- ```method ``` : 입력 양식 전송
- ```multipart``` : enctype 속성에 file upload 여부 사용
- ```etc..``` : id, class 등등 기본 옵션  
###### sample
```html
<!-- before -->
<%= form_tag({controller: :home, action: :search},
id: :fm, class: :search) do %>
 검색 키워드 : 
 <%= text_field_tag (:keywd), '' , size: 30 %>
 <%= submit_tag 'search' %>
 <% end %>

<!-- after -->
<form id="fm" class="search" action="/home/search" accept-charset="UTF-8" method="post">
    <input name="utf8" type="hidden" value="&#x2713;" />
    <input type="hidden" name="authenticity_token" value="IOxtFpzqHPF8qibeVOAQpXoGHDxkefykuQLKCXs3DVIpFAjm6hMw3gUXVM2eEouLeozwRop9pxhAYin8YxFVhQ==" />
    검색 키워드 : 
    <input type="text" name="keywd" id="keywd" value="" size="30"/>
    <input type="submit" name="commit" value="search" data-disable-with="search"/>
</form>
```
#### 모델을 사용한 ```form_tag ```

 모델을 사용하는 경우, ```text_field_tag```가 아닌 ```text_field``` 태그를 사용한다.
#### form _tag 순서 
 - object name : ```book```
 - object value : ```isbn ```
 - other info : ```size : 25 ```

###### sample 

```html
<!-- before -->
<input id ="book_isbn" name = book[:isbn] size = "25"/>

<!-- after -->
<%= form_tag(action: :create) do %>
    <div class="field">
        <%= label :book , :isbn %> <br />
        <%= text_field :book, :isbn, size: 25 %>
    </div>
    <div>
        <%= label :book, :title %> <br />
        <%= text_field :book, :title, size: 25 %>
    </div>
<% end %>
```