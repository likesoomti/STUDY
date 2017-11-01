# Form_for example 
t.index 부분의 값이 넣어지지 않아, 이에 대한 문제를 발견하기 위해 연습중. 

## 1. scaffold 로 post user 생성

###### post

```bash
$ rails g scaffold Post title:string content:text
```

###### user

```bash
% rails g scaffold User name:string age:integer
```

## 2. Association 설정 

##### `User : Post` => `1:N`

- 사용자는 많은 게시글을 쓸 수 있다
- 게시글은 하나의 사용자를 가진다 

따라서 이에 대한 부분을 `app/models`

###### Post.rb

```ruby
class Post < ApplicationRecord
    belongs_to :user
end
```
###### User.rb

```ruby
class User < ApplicationRecord
    has_many :posts
end
```
