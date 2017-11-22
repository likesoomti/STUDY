# [Jquery] HTML 무한 스크롤 만들기

## Problem 
html 로 무한스크롤을 만드는데 , 전체 페이지를 스크롤 시 고정되는게 아닌 고정된 footer와 header 중간이 움직이는 페이지를 만드는중 . jquery는 다 짰고 안에 Database를 어떤식으로 가져올지 고민중이다. 사실 고정이던 고정이아니던 만들때마다 찾는 것 같아 정리해두었당


## Image
분홍색이 고정되어있는 layout 이고 그 중간에 페이지가 스크롤링 되는 정도 
![Alt text](../IMG/html_Infinite_scroll.png)
## Solve

```javascript
 // 무한 스크롤 
  $(".store_info").scroll(function() {
    // store content scrolltop 0 부터 시작한다
    store_scroll_top = $(".store_info").scrollTop();
    // store div value 고정 값 
    store_info_height = $(".store_info").height();    
    // store content overflow height
    store_real_height = $(".store_info")[0].scrollHeight ;

    // 고정값+ 스크롤 끝값 = real_height
    if(store_real_height == store_scroll_top + store_info_height){
      console.log("end!")

      // 리뷰를 5개씩 가져온다
    }
``` 