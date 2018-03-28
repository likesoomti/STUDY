# [AWS] Ruby on Rails에서 HTTPs 설정하기

페이스북 로그인을 하려고하는데, https 가 아니라 할수도없고, 그 기능을 끌 수도 없는 상황이 발행했다 ㅠ.ㅠ

로컬에서 할 수 있는 방법이 있을테지만...난 허접이라 일단 https 설정을 해보기로했다.



HTTPS는 HTTP 프로토콜의 안전하고 암호화 된 버전이다. SSL 인증서를 통해 보안을 강력하게 한다. 이를 위해 3가지를 해야한다.



### 1. SSL 인증서 얻기

도메인을 사고, 도메인에 대한 인증서를 얻어야한다. 나는 route 53 에서 산 도메인 하나를 

https://aws.amazon.com/ko/certificate-manager/

를 통해 인증서를 얻었다. 도메인 확인 후 `DNS`로 인증을 받았더니, 이메일이 왔고 (30분 이내로 온다.)

approve 하니 인증서 승인을 얻었다.

### 2. AWS 로드 밸런싱 설치

AWS EC2 에서 HTTPS 를 관리해주려면 로드 밸런싱을 설치해야한다.

1. ec2 대시보드 가기
2. 사이드에 로드 밸런서 클릭 
3. 로드밸런서 만들기 클릭( http/https)
4. 이름 짓기 (무슨 이름이 나은지 몰라, 프로젝트 이름과 동일하게 했다)
5. **리스너**  https 를 추가.
6. 가용 영역은 두개가 있었는데, 둘다 선택하래서 선택
7. 보안설정 구성창은 aws 가 알아서 잘해줫겠지..싶어 다음 누름!
8. 보안그룹 구성 = 프로젝트와 같은 이름을 가진 친구 선택 
9. 라우팅구성 - 프로젝트와 같은 이름을 쓰고 다음 





## HTTPS를 지원하도록 웹 서버 구성

이 섹션에서는 가장 일반적인 Ruby on Rails 웹 서버를 구성하여 HTTPS 하에서 애플리케이션을 제공하는 방법을 배우게 될 것이다. 이전 단계에서 얻은 공개 SSL 인증서, 개인 키 및 중간 SSL 체인을 사용합니다.

예제의 목적을 위해 다음 파일 이름을 사용합니다.

- `certificate.crt` - 공용 SSL 인증서
- `private.key` - 비공개 키

웹 서버에 따라 공개 SSL 인증서와 함께 단일 파일에 SSL 중간 체인을 제공하거나 두 개의 개별 파일을 사용해야 할 수도 있습니다.

- `chain.pem` - 중간 SSL 인증서 번들
- `certificate_and_chain.pem` - SSL 인증서 및 중간 SSL 인증서 번들

Read more at https://www.pluralsight.com/guides/ruby-ruby-on-rails/using-https-with-ruby-on-rails#dbeERfG4KFltiRTM.99