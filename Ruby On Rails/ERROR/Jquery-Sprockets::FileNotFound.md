# jquery-Sprockets::FileNotFound.md


## Problem 
jquery 를 rails 에 깔려고 했으나 오류가 났다.

내가 했던건 

##### app/assets/javascripts/application.rb
```javascript
//= require jquery
//= require jquery_ujs
```
를 추가했지만 뿜는 에러는

### couldn't find file 'jquery' with type 'application/javascript'


## Solve 

gem file에 밑에 부분을 추가 안해서였음 ㅠㅠ
```
gem jquery-rails 
```

### 건드린파일 

##### app/assets/javascripts/application.rb

```javascript
//= require jquery
//= require jquery_ujs
//= require bootstrap-sprockets
//= require rails-ujs
//= require turbolinks
//= require_tree .
```

##### gemfile

```ruby 
# Bootstrap
gem 'bootstrap-sass', '~> 3.3.7'

# Jquery
gem 'jquery-rails'
```