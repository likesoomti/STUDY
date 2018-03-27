
# Cookie

HTTP 쿠키는 `서버`가 `브라우저(chrome)`에 사용자의 데이터를 일정한 시간동안 저장해 놓아, 요청이 일어날 경우 브라우저에 있는 데이터를 활용하는 기능입니다.

- 보통 로그인 기능에 사용한다
- 서버,클라이언트 둘 다 저장하고 사용할 수 있다

#### 1. 사용 방법

```json
Name = value
```
##### NAME 
아스키코드로 된 문자는 다 사용할 수 있다. (`space`,`tap`,`CTLs(Control Characters)`, `( ) < > @ , ; : \ " /  [ ] ? = { }. `)는 사용 불가능하다. 

##### VALUE

wjdflfjwjdlf 쿠키 떼서 파일 만들기 
##### 쿠키에서 저장하는 목록 

쿠키에서 저장하는 목록은 여러개이지만, 대표적으로 Name=Value 값을 받습니다. 이에 다른 타입을 추가하고싶다면 세미콜론을 붙히고 설정을 해주면 됩니다.
- `Name`
- `Value`
- `Expires`
- `Max-Age`
- `Domain`
- `Path`
- `Secure`
- `HttpOnly`



