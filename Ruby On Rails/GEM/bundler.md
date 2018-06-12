# bundler

> 어플리케이션의 의존성 (dependency) 를 구해주는 젬 

## 설치

```
$ gem install bundler
```

```
Fetching: bundler-1.16.2.gem (100%)
Successfully installed bundler-1.16.2
Parsing documentation for bundler-1.16.2
Installing ri documentation for bundler-1.16.2
Done installing documentation for bundler after 7 seconds
1 gem installed
```



## Gemfile.rb 만들기

```
$ touch gemfile 
```

gemfile.rb

```ruby
source 'https://rubygems.org'

gem 'sinatra'
gem 'sinatra-reloader'
gem 'datamapper'
gem 'dm-sqlite-adapter'
gem 'json', '~> 1.6'

```



루비에서는 번들러를 통해 gem 을관리한다.

프로젝트마다 버전이 다 다를 수 있기 때문에, 프로젝트 내에서 버전을 맞춰주는 버전관리 툴이 있는데, 이것을 루비에서는 bundler 라이브러리를 통해 사용한다. 



## 실행

```
$ bundle install 
```

을 입력하면, Gemfile.rb 안에 있는 gem 들을 설치해준다.

이명령을 실행하면 `Gemfile.rb` 가 생긴다. 

```
GEM
  remote: https://rubygems.org/
  specs:
    activesupport (5.2.0)
      concurrent-ruby (~> 1.0, >= 1.0.2)
      i18n (>= 0.7, < 2)
      minitest (~> 5.1)
      tzinfo (~> 1.1)
    backports (3.11.3)
    concurrent-ruby (1.0.5)
    i18n (1.0.1)
      concurrent-ruby (~> 1.0)
    json (1.8.6)
    minitest (5.11.3)
    multi_json (1.13.1)
    mustermann (1.0.2)
    rack (2.0.5)
    rack-protection (2.0.3)
      rack
    sinatra (2.0.3)
      mustermann (~> 1.0)
      rack (~> 2.0)
      rack-protection (= 2.0.3)
      tilt (~> 2.0)
    sinatra-contrib (2.0.3)
      activesupport (>= 4.0.0)
      backports (>= 2.8.2)
      multi_json
      mustermann (~> 1.0)
      rack-protection (= 2.0.3)
      sinatra (= 2.0.3)
      tilt (>= 1.3, < 3)
    sinatra-reloader (1.0)
      sinatra-contrib
    thread_safe (0.3.6)
    tilt (2.0.8)
    tzinfo (1.2.5)
      thread_safe (~> 0.1)
PLATFORMS
  ruby
DEPENDENCIES
  json (~> 1.6)
  sinatra
  sinatra-reloader
BUNDLED WITH
   1.16.2
```

이에 대해 다른환경에서 실행하면 설치가 된다. 



## Heroku

1. gem file 설정

   heroku 는 pg 를 쓰기 때문에, 고쳐줘야한다. 다행이 orm 을 쓰기 때문에 크게 고칠필요엇ㅂ고, gem 만 바꿔서 쓴다. 

   - gem file 그룹 나누기

   ```ruby
   source 'https://rubygems.org'
   
   gem 'sinatra'
   gem 'sinatra-reloader'
   gem 'datamapper'
   gem 'json', '~> 1.6'
   
   group :production do 
     gem 'pg'
     gem 'dm-postgres-adapter'
   end
   
   group :development,:test do
     gem 'dm-sqlite-adapter'
   end
   ```

- - configure 변경

  ```ruby
  configure :development do
      DataMapper::setup(:default,"sqlite3://#{Dir.pwd}/blog.db") 
  end
  
  configure :production do 
      DataMapper::setup(:default,ENV["DATABASE_URL"]) 
  end
  ```

  - bundle install

  ```
  $ bundle install --without production
  ```

  ```
  $ heroku login 
  $ 가입했을때 이메일
  $ 비밀번호 
  ```

- - config.ru 만들기		

  ```
  # 서버 돌때 알아서 돌게 실행해주게 만드는 것 
  require './app'
  run Sinatra::Application
  ```

  - git add commit push

  ```
  $ git push heroku master
  ```

- ## error

- commit x

- bundler install (x) => bundle install

- ctrl+s 안눌렀을 경우 