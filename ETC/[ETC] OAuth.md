# OAuth
사용자 표준화한 인증방식
### 개념 
1. 여러 `Application`은 구매라던지, 회원가입 등의 이유로 사용자 인증이 필요함

2. 회사마다 각 제각각으로 만들다 보니 복잡해짐

3. 복잡하니까 같이 쓸수 있게 룰을 정해보자! 

4. OAuth 인증방식 탄생 !

### 특징

1 .OAuth를 이용하면 이 인증을 공유하는 애플리케이션끼리는 별도의 인증이 필요없다. 
2. 따라서 여러 애플리케이션을 통합하여 사용하는 것이 가능하게 된다.


### OAuth 1.0 (보안결함으로 지금 사용 XX)

#### 트위터 개발자 

1. API 인증, 권한부여 동시 제공 프로토콜을 찾음 
2. 딱히 맘에드는게 없음!
3. 그래서 만듬 -> `OAuth 1.0` 탄생!(2007.10)

### OAuth 1.0a

1. `OAuth1.0`의 보안 결함 발표 ...!(2009.06)
    ```
    세션 고정 공격(session fixation attack) 
    ```
2. 따라서 보완한 OAuth 1.0a가 발표됨

3. OAuth 1.0a는 `The OAuth 1.0 Protocol` 이라는 이름으로 RFC5849 문서 번호를 부여 받으며 IETF 표준 지정! 는 (2010.04)

3. `OAuth 1.0` 로 지정받았지만, 결함있는 1.0이 아닌 보안 개선한 OAuth 1.0a의 규격을 말한다!!

#### OAuth 1.0a 장점 

- API를 인증 시 어플리케이션에게 사용자의 비밀번호를 노출하지 않는다.
- 둘째, 인증(`authentication`)과 API권한 부여(`authorization`)를 동시에 할 수 있다

### OAuth 2.0 

Oauth 1.0.a의 개선안

#### 특징
##### 1. 간단해 졌다

    **OAuth 1.0a**:  

        - API를 호출시 signature를 생성..해서 호출.. 
        - API를 테스트 시 별도의 API 콘솔 활용..

    **OAuth 2.0**:

        - signature 생성 필요없음!
        - curl등 직관적인 방법을 사용!
    
##### 2. 더 많은 인증 방법을 지원한다

    `OAuth 1.0a`:  한가지 인증 방식을 제공
    `OAuth 2.0`: 2.0은 시나리오별로 여러가지 인증 방식을 제공


## 사용방식 

### 용어
- 사용자(`user`): `service provider`와 `consumer` 사용하는 계정을 가지고 있는 개인
- 소비자(`consumer`): Open API를 이용하여 개발된 OAuth를 사용하여 서비스 제공자에게 접근하는 웹사이트 또는 애플리케이션
- 서비스 제공자(`service provider`): OAuth를 통해 접근을 지원하는 웹 애플리케이션(Open API를 제공하는 서비스)
- 소비자 키(`consumer secret`) : 서비스 제공자에서 소비자가 자신임을 인증하기 위한 키
- 요청 토큰(`request token`) : 소비자가 사용자에게 접근권한을 인증받기 위해 필요한 정보가 담겨있으며 후에 접근 토큰으로 변환된다.
- 접근 토큰(`access token`) : 인증 후에 사용자가 서비스 제공자가 아닌 소비자를 통해서 보호된 자원에 접근하기 위한 키를 포함한 값.

###### Example
1. `user`:`트위터 사용자`,
2. `consumer` : `트위터로 가입하기 기능을 넣고싶은 어플리케이션`
3. `service provider` : `트위터 API 서비스`

4. `consumer secret` : `consumer`가 내가 사용하는거라고 `트위터 API 서비스` 에 알려주는 키

5. `request token` : `user` 로그인 할때 `consumer`가 `트위터 API 서비스` 에 보내서, 사용자를 대신 인증해 달라고 하는 토큰 

6. `access token` : `request token`을 받은 `트위터 API 서비스`가 `user`의 인증을 완료하고 `consumer`에게 보내는 토큰 

#### 플로우 

1. `user` 는 `consumer` 앱을 신뢰하지 않기 떄문에, `twitter`를 통해 가입을 하고 싶어하는 경우가 있다. 이럴때 **트위터로 가입하기** 버튼을 눌렀다고 가정하자. 

2. `consumer`는 트위터 로그인 화면을 보여준다.

3. `user`는 트위터로 로그인 한다.

        `user`가 로그인 할 때, `consumer`가 `twitter API 서비스`를 통해 요청하는 정보 항목을 확인할 수 있다.

4. 허용 시, `twitter API 서비스는`는 `OAuth 1.0a` 의 방식으로 사용자를 인증하여, `인증토큰(access token)` 을 전달받는다.

#### 인증토큰 특징

1. `consumer`는 사용자 아이디/패스워드를 저장하는 게 아닌, 인증 토큰을 받아서 사용한다 
2. 따라서 `user`가 원하는 정보만 허용할 수 있고,

3. `user`가 `consumer`가 맘에 안들 시 관리 페이지에서 권한을 취소할 수 있다.

4. `id/password`를 저장하는 것이 아닌, 로그인 할 때 인증토큰을 보내주기 때문에 패스워드 변경시에도 인증 토큰은 유효하다. 


#### 참고 사이트

http://earlybird.kr/1584
https://ko.wikipedia.org/wiki/OAuth