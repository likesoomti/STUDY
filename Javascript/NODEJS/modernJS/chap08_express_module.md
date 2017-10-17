express사용이유

모듈을 세분화해서 분류할 수 있기 때문에.

next -> 다음 모듈로 가기 위해 사용한다.

res.send(data): 

data값을 화면에 출력해준다.

req.status(404).send(data):

404의 status를 가질 경우 data의 값을 화면에 출력해준다.


기본 요청메서드

req.header() = req.get()

같은 메서드. 현재 문서에서는  get()만 나와있다.

http express 차이점ㅋㅋㅋ

use 메서드를 사용한다.

이유 : 
콜백 매개변수 req,res, next를 분리해서
사용할수 있기 때문에 재사용이 가능하다. 