# Unpermitted parameters_스캐폴드로 만든 게시판 컬럼 추가했지만 DB에 저장이 안될 때

## Problem

Rails `Scaffold` gem library Review 게시판을 만들었는데 하나의 컬럼을 빠트려 추가하는 경우가 발생했다. 컬럼을 추가했지만, 값이 저장이 안되는 부분이 발생했다. 로그를 확인하는 결과 

##### log

```ruby
 Parameters: {"review"=>{"store_id"=>"3", "user_id"=>"1", "service_score"=>"1", "store_score"=>"2", "food_score"=>"3", "content"=>"ddd", "image"=>"pooh.jpg"}, "store_id"=>"3"}

  User Load (1.2ms)  SELECT  "users".* FROM "users" WHERE "users"."id" = ? ORDER BY "users"."id" ASC LIMIT ?  [["id", 1], ["LIMIT", 1]]

Unpermitted parameters: :food_score, :image
   (0.2ms)  begin transaction
  ```

` Unpermitted parameters: :food_score, :image` 의 문제가 발생하였다

## Solve

해당 Controller 를 찾아가보면

```ruby
    def ScaffoldName_params
      params.require(:review).permit(:store_id, :user_id, :content, :service_score, :store_score, :food_score)
    end
```
이런 부분이 있다. 여기에 unpermitted가 뜬 column 값을 넣어주면 된다. 괜히 sql문 찾고있었음..