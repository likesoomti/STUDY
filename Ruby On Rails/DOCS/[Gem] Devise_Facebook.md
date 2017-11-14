# Devise gem 사용하기 2 

**Facebook 연동하기 설명서** 


### 1. Gemfile 설치
```
gem 'omniauth'
gem 'omniauth-facebook'
```

### 2. Gem 적용 하기 

##### terminal
```
$ bundle install
```

### 3. SNS 모델을 저장할 테이블 만들기 

##### terminal
```
$ rails g model sns_user user:references provider:string uid:string
```

###### 결과창

```
Running via Spring preloader in process 20174
      invoke  active_record
      create    db/migrate/20171113045119_create_sns_users.rb
      create    app/models/sns_user.rb
      invoke    test_unit
      create      test/models/sns_user_test.rb
      create      test/fixtures/sns_users.yml
```

### 모델 저장하기
##### terminal
```
$ rails db:migrate
```
### 4. ominauth 설정 

##### config/initializers/devise.rb
```ruby
  config.omniauth :facebook, ENV["Facebook_Key"], ENV["Facebook_Secret"]
```

#### (참고) 키 관리하기

`FACEBOOK_KEY`, `FACEBOOK_SECRET`은 
- https://developers.facebook.com/ 
에서 프로젝트 생성한 뒤 받을 수 있습니다. git에 key,secret을 올리게 되면 다른 사람들이 다 볼 수 있어 보안성이 떨어집니다. 이때 Rails에서 제공하는 `Secret.yml`이나 `Figaro gem` 을 사용하여 키 관리를 할 수 있습니다.

##### Secret.yml reference
- http://guides.rubyonrails.org/4_1_release_notes.html

###### example
##### Secrets.yml
```ruby
development:
  facebook_app_id: 11234325
  facebook_app_secret: 75fbbcdfqewteqwtwte
```
##### use
```
Rails.application.secrets.facebook_app_id
```

##### Figaro gem
- https://github.com/laserlemon/figaro

### 5.routes 설정

##### config/routes.rb
```ruby
# before
# devise_for :users
# after ++ add
devise_for :users, :controllers => { omniauth_callbacks: 'user/omniauth_callbacks'}
```
### 6. Button 만들기 
facebook 버튼 만들기. 뷰는 알아서 shared/_links 에서 넣어줘서 고칠필요 없습니다 확인 차원에 넣어보았습니다
##### app/views/devise/shared/_links.html.erb
```html
<%- if controller_name != 'sessions' %>
  <%= link_to "Log in", new_session_path(resource_name) %><br />
<% end -%>

<%- if devise_mapping.registerable? && controller_name != 'registrations' %>
  <%= link_to "Sign up", new_registration_path(resource_name) %><br />
<% end -%>

<%- if devise_mapping.recoverable? && controller_name != 'passwords' && controller_name != 'registrations' %>
  <%= link_to "Forgot your password?", new_password_path(resource_name) %><br />
<% end -%>

<%- if devise_mapping.confirmable? && controller_name != 'confirmations' %>
  <%= link_to "Didn't receive confirmation instructions?", new_confirmation_path(resource_name) %><br />
<% end -%>

<%- if devise_mapping.lockable? && resource_class.unlock_strategy_enabled?(:email) && controller_name != 'unlocks' %>
  <%= link_to "Didn't receive unlock instructions?", new_unlock_path(resource_name) %><br />
<% end -%>

<%- if devise_mapping.omniauthable? %>
  <%- resource_class.omniauth_providers.each do |provider| %>
    <%= link_to "Sign in with #{OmniAuth::Utils.camelize(provider)}", omniauth_authorize_path(resource_name, provider) %><br />
  <% end -%>
<% end -%>

```

### 6. Controller 설정 

##### app/controllers/user/omniauth_callbacks_controller.rb
```ruby
class User::OmniauthCallbacksController < Devise::OmniauthCallbacksController
  # 프로바이더 값을 가져온다 
  # 클래스를 이름마다 생성
  # user 값과 auth 값을 보내줌 

  # 페이스북 경로 
  def facebook
    # User 모델에 설정된 find_for_oauth 호출
    @user = User.find_for_oauth(request.env["omniauth.auth"], current_user)
    # 유저 값이 존재하면 로그인 
    if @user.persisted?
      sign_in_and_redirect @user, event: :authentication
    else
    # 아니라면 유저 등록 페이지 
      session["devise.facebook_data"] = env["omniauth.auth"]
      redirect_to new_user_registration_url
    end
  end

  # 로그인 이후 경로 설정 
  def after_sign_in_path_for(resource)
    @user = User.find(current_user.id)
    if @user.persisted?
      sign_add_path
    else
      main_path
    end
  end
end

```
### 5. 모델 설정 
##### app/models/user.rb
```ruby
# before
devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable

# after 
devise :database_authenticatable, :registerable,
         :recoverable, :rememberable, :trackable, :validatable, :omniauthable
  
  # self.find_for_oauth(SNS 토큰, current_user)
  def self.find_for_oauth(auth, signed_in_resource = nil)
    sns_user = SnsUser.find_for_oauth(auth)  
    user = signed_in_resource ? signed_in_resource : 
    
    sns_user.user
    # 유저가 없으면 이메일 찾아 유저 값있는지 확인 
    if user.nil? 
      email = auth.info.email
      user = User.where(:email => email).first
      # 유저이메일에 해당하는 아이디가 없으면 하나 db에 만들어놓는다. 
      unless self.where(email: auth.info.email).exists?
      # 유저가 그럼에도 없음 값을 넣어준다
        if user.nil?
          user = User.new
          user.email = auth.info.email
          user.profile_image = auth.info.image
          user.password = Devise.friendly_token[0,20]
          user.save!
        end
      end
    end  
   if sns_user.user != user
     sns_user.user = user
     sns_user.save!
   end
   user
 end
```

#### app/models/sns_user.rb
##### sample 
```ruby
class SnsUser < ApplicationRecord
  belongs_to :user 

  # add
  # 유효성 설정 
  validates_presence_of :uid, :provider
  validates_uniqueness_of :uid, :scope => :provider
  # uid 값 넣어주기
  def self.find_for_oauth(auth)
    find_or_create_by(uid: auth.uid, provider: auth.provider)
  end

end
```

## example github

https://github.com/likesoomti/facebook-devise-rails

# facebook developer

##### URL을 읽어들일 수 없음: 앱 도메인에 포함되어 있지 않은 URL입니다. 이 URL을 읽어들이려면 앱 설정에서 앱 도메인 필드에 앱의 모든 도메인과 서브 도메인을 추가하세요.

##### 페이스북 로그인 설정

- 유효한 OAuth 리디렉션 URI 에 도메인 설정 

- 포함(embed)된 브라우저 OAuth 로그인
OAuth 클라이언트 로그인을 위해 브라우저 제어 리디렉션 URL을 활성화합니다 버튼 설정! 