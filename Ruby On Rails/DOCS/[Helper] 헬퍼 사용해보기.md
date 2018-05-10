# 헬퍼 사용해보기

레일즈에는 Helper 라는 폴더가 존재하는데,

보통 컨트롤러를 만들 고 나면 같이 만들어 지게 된다.

해당 컨트롤러에서 계속 사용하게 되는 모듈을 만들 경우, 여기에 넣고 갔다 쓰게 된다. 

## Example

```
사용자가 가지는 고유의 게시글이 있다.
이 때, 사용자가 잘못된 url 을 들어오게되면 board 의 user_id 를 서치해 
그 해당 board_id 값의 url 로 리다이렉트를 해주고 싶을 경우!에

-> 빈 값일 경우 error 발생 
```

이에 대한 모듈을 만들어보았다. 이 모듈 이름은 `purchases` 다.

```ruby
module PurchasesHelper 
    extend self
    def isPurchase
    end
end
```

이렇게, 해당 글이 존재하는지에 대한 메서드를 모듈 안에 만들어 주었다. 

##### extend self

이 모듈 안에 메서드를 다 *static* 으로 사용할 수 있다. 만약 일부분만 붙히고 싶다면

```ruby
module PurchasesHelper 
    def self.isPurchase
    end
end
```

로 사용해도 된다.

#### 호출하기

> `helper이름.메서드이름`

```ruby
if (data =PurchasesHelper.isPurchase(@user))
    redirect_to purchase_path(data)
  else
    redirect_to '/errors'
end
```

이렇게하면

```ruby
# b controller
  def index
    unless(@user.email == "admin@openull.com" )
      PurchasesHelper.movePurchase(@user)
    end
  end 
  
# a controller
  def after_sign_in_path_for(resource)
      ...
      else
        PurchasesHelper.movePurchase(@user)
      end
    end
  end

# c controller

    # show 페이지 이용자 검사 
    def user_admin_check
      # 유저아이디, 어드민이 아닐 경우 error page 를 띄운다. 또는 로그아웃 화면과 메세지를 보낸다.
      unless(@user.id == @purchase.user_id || @user.email == "admin@openull.com" )
        PurchasesHelper.movePurchase(@user)
      end
    end
```

이런 중복 코드를 한줄에 줄일 수 있다!



