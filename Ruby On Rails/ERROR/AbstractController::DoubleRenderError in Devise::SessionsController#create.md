# AbstractController::DoubleRenderError in Devise::SessionsController#create

## Problem

세션을 통해 특정 페이지 이동 설정 중 에러 발생

```ruby
  def after_sign_in_path_for(resource)
    puts "after sign in path for"
    puts resource
    if(session[:show_url].present?)
      redirect_to session[:show_url]
    else
      puts " no session"
    end
    home_index_path
  end
```



## Solve

`after_sign_in_path_for` 에서는 redirect_to 가 아닌, 

return 을 통해 url 을 보내준다.

```ruby
  def after_sign_in_path_for(resource)
    puts "after sign in path for"
    puts resource
    if(session[:show_url].present?)
      return session[:show_url]
    else
      puts " no session"
    end
    home_index_path
  end
```

