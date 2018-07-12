# [Role] cancancan - rolify gem 을 이용한 권한 부여 



## 설정

1. devise 설치

   ```ruby
   $ rails generate devise:install
   ```

   ```ruby
   $ rails generate devise User
   ```

2. cancancan 설정

   ```ruby
   $ rails generate cancan:ability
   ```

3. rolify 권한 설정

   ```ruby
   $ rails generate rolify Role User
   ```

4. ability.rb 생성자에 추가

   ```ruby
   class Ability
     include CanCan::Ability
   
     def initialize(user)
       if user.has_role? :admin
         can :manage, :all
       else
         can :read, :all
       end
     end
   end
   
   ```



## 실행하기

회원 정보 하나 저장

```ruby
> user = User.new
> user.email = "anyemail@address.com"
> user.password = "test1234"
> user.save

###
irb(main):007:0> user.save
(0.1ms)  begin transaction
  User Exists (0.4ms)  SELECT  1 AS one FROM "users" WHERE "users"."email" = ? LIMIT ?  [["email", "soomti@likelion.org"], ["LIMIT", 1]]
  User Create (1.9ms)  INSERT INTO "users" ("email", "encrypted_password", "created_at", "updated_at") VALUES (?, ?, ?, ?)  [["email", "soomti@likelion.org"], ["encrypted_password", "$2a$11$z1SXixXZKGnSTN1R88da2eAzzM6uRPfqXUPVkcCvzeWNDeJSh29OS"], ["created_at", "2018-07-11 05:23:05.427377"], ["updated_at", "2018-07-11 05:23:05.427377"]]
```

회원 권한 부여

```ruby
irb(main):010:0> user.add_role "admin"
  Role Load (0.4ms)  SELECT  "roles".* FROM "roles" WHERE "roles"."name" = ? AND "roles"."resource_type" IS NULL AND "roles"."resource_id" IS NULL ORDER BY "roles"."id" ASC LIMIT ?  [["name", "admin"], ["LIMIT", 1]]
   (0.1ms)  begin transaction
  Role Create (1.3ms)  INSERT INTO "roles" ("name", "created_at", "updated_at") VALUES (?, ?, ?)  [["name", "admin"], ["created_at", "2018-07-11 05:23:25.816478"], ["updated_at", "2018-07-11 05:23:25.816478"]]
   (1.3ms)  commit transaction
  Role Exists (0.5ms)  SELECT  1 AS one FROM "roles" INNER JOIN "users_roles" ON "roles"."id" = "users_roles"."role_id" WHERE "users_roles"."user_id" = ? AND "roles"."id" = ? LIMIT ?  [["user_id", 1], ["id", 1], ["LIMIT", 1]]
   (0.2ms)  SELECT "roles"."id" FROM "roles" INNER JOIN "users_roles" ON "roles"."id" = "users_roles"."role_id" WHERE "users_roles"."user_id" = ?  [["user_id", 1]]
  Role Load (0.2ms)  SELECT "roles".* FROM "roles" WHERE "roles"."id" = ?  [["id", 1]]
  Role Load (0.4ms)  SELECT "roles".* FROM "roles" INNER JOIN "users_roles" ON "roles"."id" = "users_roles"."role_id" WHERE "users_roles"."user_id" = ?  [["user_id", 1]]
   (0.1ms)  begin transaction
  User::HABTM_Roles Create (0.9ms)  INSERT INTO "users_roles" ("user_id", "role_id") VALUES (?, ?)  [["user_id", 1], ["role_id", 1]]
   (0.8ms)  commit transaction
=> #<Role id: 1, name: "admin", resource_type: nil, resource_id: nil, created_at: "2018-07-11 05:23:25", updated_at: "2018-07-11 05:23:25">
```





## 권한 설정

모델에 권한설정하기

 `resourcify` 를 붙히면 된다.

```ruby
class Post < ApplicationRecord
    resourcify
    ...
end
```



```ruby
load_and_authorize_resource
```

이 코드는 모델을 로드할때 권한을 부여하는 코드이다.  우리는 현재 모델이없기때문에 사용 x



가져오는 모델에대한 특정 메서드에만  권한을 적용하고 싶을 경우에는, 밑에처럼 사용한다.

```ruby
def show
  @article = Article.find(params[:id])
  authorize! :read, @article
end
```



권한 설정 

ability.rb

```ruby
class Ability
  include CanCan::Ability
 
  def initialize(user)
    #user -> current_user 
       user ||= User.new # guest user (not logged in)
       # 어드민 권한
       if user.has_role? :admin
         can :manage, :all #create, edit 등
       # 일반 유저 권한
       else
         
       end
  end
end

###
 :manage - 전체 관리
 :index - 게시판 글 목록
 :show - 게시글 보기
 :new - 게시글 작성 페이지 이동
 :create - 작성자가 작성한 게시글을 게시판에 upload
 :edit - 게시글 편집
 :destroy - 게시글 삭제
```

권한 설정하기 예시

반대로 하지않겠다 설정하고싶으면 `cannot` 을 설정하면 된다.

```ruby
def initialize(user)
    #user -> current_user 
    user ||= User.new # guest user (not logged in)
    if user.has_role? :admin #어드민 권한
        can :manage, :all #create, edit 
    else   
    #일반 회원일 경우 
    can [:index, :show, :new, :create], Post 
    # Post 모델의 resource 는 index,show,new,create 적용 
    can [:edit, :update, :destroy], Post, user_id: user.id
    # edit, update, destroy 부분은 user_id 가 같은 경우만 적용 
    can :destroy, Comment, user_id: user.id
  end
end
```



회원가입한 유저 회원가입이후 권한 부여 코드

```ruby
class User < ApplicationRecord
  rolify
  after_create :assign_default_role
  def assign_default_role
    add_role(:user)
  end
  # Include default devise modules. Others available are:
  # :confirmable, :lockable, :timeoutable and :omniauthable
  devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable
end
```

