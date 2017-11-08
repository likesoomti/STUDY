
# Form Helper

### form_tag([url_opts[,opts]]) 
- 범용적인 입력 양식 헬퍼
- 검색 키워드 또는 조건을 입력받는 양식들에 사용
- csrf 방지 구성

#### options
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
### 모델을 사용한 form_tag 

 모델을 사용하는 경우, ```text_field_tag```가 아닌 ```text_field``` 태그를 사용
 
#### form_tag 순서 
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

### form_for([url_opts[,opts]])  
 특정 모델을 편집할 수 있는 입력 양식 생성 

 ### 장점
 - 모델 객체가 신규인지, 저장인지 판별하여
 - url 옵션 또는 html 옵션을 내부적으로 붙혀준다
 #### method
 ```ruby
    form_for(var [,opts]) do |f|
    ...body...
    end
```
- `var` : 모델 객체
- `opts` : 동작 옵션
- `f` : 모델 객체 전달 속성
- `body` : 입력 양식 자체 

##### `opts` option
- `url` : 입력 양식 전송 대상
- `html` : 이외의 form 태그 속성 정보 


###### view_controller
 ```ruby
def form_for
    @book = Book.new
end
```
###### form_for.html.erb
 ```html
<%= form_for(@post) do |form| %>
 <div class="field">
    <!-- form_tag 처럼 호출한다. -->
    <%= form.label :title %>
    <%= form.text_field :title, 
    id: :post_title %>
  </div>
  <div class="field">
    <%= form.label :content %>
    <%= form.text_area :content, id: :post_content %>
  </div>
  <div class="actions">
    <%= form.submit %>
  </div>
  <% end %>
 ```

 ## Input Field

 #### 1. XXXXX_field(obj,prop[,opts])

 ##### 속성
 - `color_field` : 색 선택
 - `datetime_field` : 날짜와 시간 입력 
 - `email_field` : 메일 주소 입력
 - `number_field` : 숫자 입력
 - `search_field` : 검색
 - `time_field` : 시간 입력
 - `week_field` : 주 입력
 - `date_field` : 날짜 입력
 - `datetime_local_field` : 날짜와 시간 입력
 - `month_field` : 월 입력
 - `range_field` : 슬라이더
 - `telephone_field` : 전화번호 입력
 - `url_field` : URL 입력
 - `input_field` : html5 추가 
 
 ####  2. text_area(obj,prop[,opts])
 ####  3. radio_button(obj, prop, value [,opts])
 #### 4. check_box(obj, prop [,opts [,checked ="1 [,unchecked = "0"]]])
 - `obj` : 객체 이름
 - `prop` : 속성 이름
 - `opts` : `<input>` 과 `<textarea>` 옵션
 - `value` : value 속성 값
 - `checked / unchecked` : 체크 상태와 체크되지 않은 상태 value 값 

 #### 5.select(obj, prop, choices [,opts[,html_opts]])

 ##### fields_for
 데이터 베이스를 여러개의 모델을 복합적으로 수정할 수 있는 입력 양식

 ```html 
 <%= field_set_tag '저자정보' do %>
    <%= fields_for @user.author do |>
 ```
 ### fields_for(var)
 
 - 다른 객체 입력 양식 요소를 그룹화 한다 

 #### field_set_tag([legend[, opts]]) 

 - legend : 입력 양식의 제목
 - opts : `<fieldset>` 태그 옵션
 - content : `<fieldset>` 태그 내부의 내용물
