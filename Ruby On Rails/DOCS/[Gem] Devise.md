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



---



## 디바이스 컬럼 추가하기

##### db/migrate/devise

```ruby
class DeviseCreateUsers < ActiveRecord::Migration[5.1]
  def change
    create_table :users do |t|
      ## Database authenticatable
      t.string :email,              null: false, default: ""
      t.string :encrypted_password, null: false, default: ""
        
      ## Recoverable
      t.string   :reset_password_token
      t.datetime :reset_password_sent_at

      ## Rememberable
      t.datetime :remember_created_at

      ## Trackable
      t.integer  :sign_in_count, default: 0, null: false
      t.datetime :current_sign_in_at
      t.datetime :last_sign_in_at
      t.string   :current_sign_in_ip
      t.string   :last_sign_in_ip

      ## Confirmable
      # t.string   :confirmation_token
      # t.datetime :confirmed_at
      # t.datetime :confirmation_sent_at
      # t.string   :unconfirmed_email # Only if using reconfirmable

      ## Lockable
      # t.integer  :failed_attempts, default: 0, null: false # Only if lock strategy is :failed_attempts
      # t.string   :unlock_token # Only if unlock strategy is :email or :both
      # t.datetime :locked_at


      t.timestamps null: false
    end

    add_index :users, :email,                unique: true
    add_index :users, :reset_password_token, unique: true
    # add_index :users, :confirmation_token,   unique: true
    # add_index :users, :unlock_token,         unique: true
  end
end

```

이렇게 만들어진 코드에 원하는 컬럼을 추가를 해봅시다.

```ruby
class DeviseCreateUsers < ActiveRecord::Migration[5.1]
  def change
    create_table :users do |t|
      ## Database authenticatable
      t.string :email,              null: false, default: ""
      t.string :encrypted_password, null: false, default: ""

      # name
      t.string :name, null: false
      t.string :phone, null: false
      # 생년 월일 
      t.datetime :birthday, null: false
      # true 남자, false 여자, 성별
      t.boolean :gender, null: false
      # 결제 고객 유효 번호 
      t.string :user_code
      # 계좌 번호 
      t.string :back_number
      # 은행 코드 enum
      t.string :back_code
      t.string :address

      ## Recoverable
      t.string   :reset_password_token
      t.datetime :reset_password_sent_at

      ## Rememberable
      t.datetime :remember_created_at

      ## Trackable
      t.integer  :sign_in_count, default: 0, null: false
      t.datetime :current_sign_in_at
      t.datetime :last_sign_in_at
      t.string   :current_sign_in_ip
      t.string   :last_sign_in_ip

      ## Confirmable
      # t.string   :confirmation_token
      # t.datetime :confirmed_at
      # t.datetime :confirmation_sent_at
      # t.string   :unconfirmed_email # Only if using reconfirmable

      ## Lockable
      # t.integer  :failed_attempts, default: 0, null: false # Only if lock strategy is :failed_attempts
      # t.string   :unlock_token # Only if unlock strategy is :email or :both
      # t.datetime :locked_at


      t.timestamps null: false
    end

    add_index :users, :email,                unique: true
    add_index :users, :reset_password_token, unique: true
    # add_index :users, :confirmation_token,   unique: true
    # add_index :users, :unlock_token,         unique: true
  end
end
```

원하는 컬럼을 다 추가 했다면

db 를 저장해 줍니다.

##### terminal

```irb
$ rake db:migrate
```

##### view

디바이스 뷰에 컬럼을 추가해줍시다.

##### app/views/devise/registrations/new.html.erb

```erb
<h2>Sign up</h2>

<%= form_for(resource, as: resource_name, url: registration_path(resource_name)) do |f| %>
  <%= devise_error_messages! %>

  <div class="field">
    <%= f.label :email %><br />
    <%= f.email_field :email, autofocus: true %>
  </div>

  <div class="field">
    <%= f.label :password %>
    <% if @minimum_password_length %>
    <em>(<%= @minimum_password_length %> characters minimum)</em>
    <% end %><br />
    <%= f.password_field :password, autocomplete: "off" %>
  </div>

  <div class="field">
    <%= f.label :password_confirmation %><br />
    <%= f.password_field :password_confirmation, autocomplete: "off" %>
  </div>
  <div class="actions">
    <%= f.submit "Sign up" %>
  </div>
<% end %>

<%= render "devise/shared/links" %>

```

이렇게 되어있는 코드에

```ruby
  <div class="field">
    <%= f.label :phone %><br />
    <%= f.text_field :phone, autocomplete: "off" %>
  </div>
  <div class="field">
    <%= f.label :name %><br />
    <%= f.text_field :name, autocomplete: "off" %>
  </div>
  <div class="field">
    <%= f.label :name %><br />
    <%= f.text_field :birthday, autocomplete: "off" %>
  </div>
  <div class="field">
    <%= f.label :gender %><br />
    <%= f.text_field :gender, autocomplete: "off" %>
  </div>
  <div class="field">
    <%= f.label :user_code %><br />
    <%= f.text_field :user_code, autocomplete: "off" %>
  </div>
  <div class="field">
    <%= f.label :bank_number %><br />
    <%= f.text_field :bank_number, autocomplete: "off" %>
  </div>
  <div class="field">
    <%= f.label :address %><br />
    <%= f.text_field :address, autocomplete: "off" %>
  </div>
```

이 필드를 추가해주었습니다. `edit.html.erb` 또한 추가해줍니다.



##### application_controller.rb

이제 이 컬럼 값을 데이터베이스에 넘길 코드가 필요합니다.

```ruby
class ApplicationController < ActionController::Base
  before_action :configure_permitted_parameters, if: :devise_controller?
  protect_from_forgery with: :exception
  protected

  def configure_permitted_parameters
    devise_parameter_sanitizer.permit(:sign_up, keys: [:phone,:name,:birthday,:gender,:user_code,:bank_number,:address])
    devise_parameter_sanitizer.permit(:account_update, keys: [:phone,:name,:birthday,:gender,:user_code,:bank_number,:address])
  end:sign_up, keys

end
```

##### 뷰 필드 고치기

뷰 필드를 해당하는 필드로 고치면 좋다.

```erb

<%= text_area_tag(:message, "Hi, nice site", size: "24x6") %>
<%= password_field_tag(:password) %>
<%= hidden_field_tag(:parent_id, "5") %>
<%= search_field(:user, :name) %>
<%= telephone_field(:user, :phone) %>
<%= date_field(:user, :born_on) %>
<%= datetime_local_field(:user, :graduation_day) %>
<%= month_field(:user, :birthday_month) %>
<%= week_field(:user, :birthday_week) %>
<%= url_field(:user, :homepage) %>
<%= email_field(:user, :address) %>
<%= color_field(:user, :favorite_color) %>
<%= time_field(:task, :started_at) %>
<%= number_field(:product, :price, in: 1.0..20.0, step: 0.5) %>
<%= range_field(:product, :discount, in: 1..100) %>
```

##### 유효성 검사

```ruby
validates :phone, format: {with:/\A^\d{3}-\d{3,4}-\d{4}$\z/,:message => "번호를 제대로 입력해 주세요"} 
```



 



#### 참고 사이트

- https://github.com/plataformatec/devise
- http://gyuwonable.tistory.com/19?category=939382