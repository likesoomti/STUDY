# Devise gem 사용하기
**Devise GEM** 사용 설명서 

### 1. Gemfile 설치
```
gem 'devise'
```

### 2. Gem 적용 하기 

##### terminal
```
$ bundle install
```

###### 결과창

```ruby
Bundle complete! 19 Gemfile dependencies, 78 gems now installed.
Use `bundle show [gemname]` to see where a bundled gem is installed.
```

### 3. devise 설치하기
##### terminal
```
$ rails generate devise:install
```

###### 결과창

```ruby
Running via Spring preloader in process 7177
      create  config/initializers/devise.rb
      create  config/locales/devise.en.yml
===============================================================================

Some setup you must do manually if you haven't yet:

  1. Ensure you have defined default url options in your environments files. Here
     is an example of default_url_options appropriate for a development environment
     in config/environments/development.rb:

       config.action_mailer.default_url_options = { host: 'localhost', port: 3000 }

     In production, :host should be set to the actual host of your application.

  2. Ensure you have defined root_url to *something* in your config/routes.rb.
     For example:

       root to: "home#index"

  3. Ensure you have flash messages in app/views/layouts/application.html.erb.
     For example:

       <p class="notice"><%= notice %></p>
       <p class="alert"><%= alert %></p>

  4. You can copy Devise views (for customization) to your app by running:

       rails g devise:views

===============================================================================
```

### 4. 모델 만들기

##### terminal
User는 아무 이름으로 대체 가능하다!
```
$ rails generate devise User
```

###### 결과창
```
Running via Spring preloader in process 7292
      invoke  active_record
      create    db/migrate/20171109035003_devise_create_users.rb
      create    app/models/user.rb
      invoke    test_unit
      create      test/models/user_test.rb
      create      test/fixtures/users.yml
      insert    app/models/user.rb
       route  devise_for :users
```
### 5. 모델 설정하기 

`db/migrate/~devise_create_users.rb` path 에 필요한 컬럼을 넣는다. 

###### sample
```ruby
## Database authenticatable
  t.string :email,              null: false, default: ""
  t.string :encrypted_password, null: false, default: ""
```

### 6. 모델 설정 저장
##### terminal
```
$ rake db:migrate
```
###### sample
```
== 20171109035003 DeviseCreateUsers: migrating ================================
-- create_table(:users)
   -> 0.0052s
-- add_index(:users, :email, {:unique=>true})
   -> 0.0016s
-- add_index(:users, :reset_password_token, {:unique=>true})
   -> 0.0013s
== DeviseCreateUsers: migrated (0.0085s) =======================
```

### 7. Devise View 설정
##### terminal
```
$ rails generate devise:views

```
###### result
```ruby
Running via Spring preloader in process 9445
      invoke  Devise::Generators::SharedViewsGenerator
      create    app/views/devise/shared......
```

### 8. Devise Controller 설정
디바이스에서 제공하는 여러 controller들을 user라는 폴더에 담겠다
##### terminal
```
$ rails generate devise:controllers user
```
###### result
```ruby
Running via Spring preloader in process 9465
      create  app/controllers/user/confirmations_controller.rb
      create  app/controllers/user/passwords_controller.rb
      create.......
```


끝!

#### 참고 사이트
- https://github.com/plataformatec/devise