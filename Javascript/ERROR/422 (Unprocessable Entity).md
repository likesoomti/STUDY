# 422 (Unprocessable Entity)

## Problem

AJAX 로 데이터를 보내는데, rails csrf 토큰이 안보내져서 나는 오류 

```javascript
 $.ajax({
             method: "post",
             url: "/works/nextpage?="+n_page,
             data:{
               nxt_page: n_page
             },
             dataType: "html"   
           })
```

## Solve

`before_send` 에 token을 넣어주는 코드를 추가해준다.

```javascript
 $.ajax({
             method: "post",
             beforeSend: function(xhr) {xhr.setRequestHeader('X-CSRF-Token', $('meta[name="csrf-token"]').attr('content'))},
             url: "/works/nxtpage?="+n_page,
             data:{
               next_page: n_page
             },
             dataType: "html"   
           })
```

