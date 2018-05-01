# 422 (Unprocessable Entity)

## Problem

ajax 데이터로 보낼 시 csrf 토큰 에러 발생에 나는 이유 



## solve 

ajax 에 추가하면 된다

```js
 beforeSend: function(xhr) {xhr.setRequestHeader('X-CSRF-Token', $('meta[name="csrf-token"]').attr('content'))},
```

##### sample

```js
$(".itemConfirm").click(function(){      
       $.ajax({
       url: "/additem",
       type:"post",
       beforeSend: function(xhr) {xhr.setRequestHeader('X-CSRF-Token', $('meta[name="csrf-token"]').attr('content'))},
       data: {
         item : item
       },
       success: function(data){
         console.log("success");
       }
     })
   })
```

