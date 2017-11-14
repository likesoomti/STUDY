# HTML5에 이미지 preview 띄우기
    
`html5` 에서 이미지를 등록하고 `AWS S3`로 전송 하는걸 끝내고, 기본 정보를 수정시에  이미지를 선택하면 미리보기 창으로 띄워주고 싶어서  검색해보다 발견하였다!  

##### html5 

```html
    <!-- html5 -->
    <img id="user_image" src="#" alt="" >
    <!-- 값이 변경되면 올려줌 -->
    <input accept=".jpg" onchange="PreviewImage();" type="file" name="user[profile_img]" id="user_profile_img" />
```
##### Javascript
```javascript
    function PreviewImage() {
        // 파일리더 객체 생성 
        var preview = new FileReader();
        preview.onload = function (e) {
            // 로컬이미지 데이터화 
            document.getElementById("user_image").src = e.target.result;
        };
        // 파일 뿌려줌
        preview.readAsDataURL(document.getElementById("user_profile_img").files[0]);
    };
```