

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