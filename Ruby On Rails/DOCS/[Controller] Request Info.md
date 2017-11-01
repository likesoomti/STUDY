
# Request INFO

요청 정보를 어떤식으로 가져오는지 알아보자 

## Header Method

#### 헤더 정보, 요청 헤더 

`browser` 에서 `server` 로 요청시 눈에 보이지 않는 정보를 말한다

- 클라이언트 언어 정보 (`eng`,`kor`)
- `browser` 정보
- 이전 페이지 링크 

와 같은 정보를 전달한다

##### 사용 방법
`headers`는 hash 형식으로 전달되기 때문에 request.headers[:params] 를 통해 사용 가능하다 

#### main header
| header name | description |
| ----------- | :---------- |
| Accept    |   클라이언트 지원 콘텐츠 종류 |
| Accept-Language | 클라이언트 대응 언어 (우선순위 대로 ) |
| Authorization |   인증 정보 |
| Host  |   요청 대상 호스트 이름 | 
| Referer | 클릭 링크 이전 문서 URL |
| User-Agent | 클라이언트 종류 |

###### sample 

```ruby
# params 전달 시 - 가 붙어있는 헤더 네임들은 심볼이 아닌, 스트링 값으로 전달해야한다.
# 안그럼 에러남

request.headers # 헤더값 
# => <ActionDispatch::Http::Headers:0x007fc5733c6870>

request.header[:Accept] # 클라이언트 지원 콘텐츠 
# => text/html, application/xhtml+xml

request.headers[:Accept-Language] # 대응되는 언어 순위
# =>

request.headers[:Authorization] # 인증 정보
# =>

request.headers[:Host] # 요청 대상 호스트 이름
# => localhost:3000

request.headers[:Referer] # 클릭한 링크 이전 문서 url 
# => http://localhost:3000/

request.headers[:User-Agent] # 클라이언트 종류 
# =>

headers["Content-Type"] 
# =>
```


## Request Method
ActionDispatch::Request 를 포함한다 

#### method

| method      | description               | example        |    
| ----------- | :------------------------ | :-------------:|
| accepts     | 클라이언트가 지원하는 콘텐츠 종류 | image/jpeg,application/x-ms-application,image/git,---*/*        |    
| authorization| 인증 정보                  |                |      
| body       |	포스트 데이터(StringIO 객체)   | -              |     
| content_length| 콘텐츠 크기                |    0         |
| fullpath	  | 요청 URL                    | /ctrl/req_head|
| get?,post?,put?,patch?,delete?,head?| http 통신 여부 | true |
| host | 호스트 이름 | localhost |
| host_with_port | 포트 번호 붙은 호스트 이름 | localhost:3000|
| method | http method | GET |
| port | 포트번호 | 3000 |
| port_string | 포트 번호 (문자열) | localhost:3000 |
| protocol | 프로토콜 | http:// |
| remote_ip | 클라이언트 IP 주소 | 127.0.0.1 |
| request_method | http 메서드 (rails 내부에서 사용하는) | patch |
| request_uri | 요청 uri | /ctrl/req_head |
| scheme | 스키마 이름 | http |
| server_software | 사용되는 소프트 웨어 | webbrick |
| ssl? | 암호화 통신인지 여부 | false |
| standard_port? | WELL-Known 포트인지 여부 | false |
| url | 완전한 요청 URL | http://localhost:3000/ctrl/req_head| 
| xml_http_request? | XMLHttpRequest 객체로 전달된 요청인지 여부 | false |



###### sample

```ruby on rails


request.accpets # 클라이언트가 지원하는 콘텐츠 종류 

# => [#<Mime::Type:0x007fc56e4bbd48 @synonyms=["application/xhtml+xml"], @symbol=:html, @string="text/html", @hash=-3512688345871750341>]

request.authorization # 인증 정보 

# =>

request.body # 포스트 데이터 객체

# => #<Puma::NullIO:0x007fc56ae954f8>

request.content_length # 콘텐츠 크기 

# => 0

request.fullpath # 요청 URL

# => /ctrl/c_request

request.get? or post?,put?,patch?,delete?,head? # => 이 통신이 맞는지 (return type: bool) 

# => true

request.host # 호스트 이름 

# => localhost

request.host_with_port # 호스트 이름 +포트번호

# => localhost:3000

request.local? # 로컬 통신인지 여부 

# => 0 (책에서는 true이나 여기서는 0 출력..?)

request.method

# => GET

port # 포트번호 

# => 3000

port_string # 포트번호 문자열 

# => :3000

request.protocol # 프로토콜

# => http://

request.remote_ip # 클라이언트의 IP 주소

# => 127.0.0.1

request_method # http 메서드 

# => GET

scheme # 스키마 이름

# => http

server_software # 사용되는 서버 소프트웨어

# => puma

ssl? # 암호화 통신 여부 

# => false

standard_port? # well-known 포트 여부

# => false

url # 완전한 요청 url 

# => http://localhost:3000/ctrl/c_request

xml_http_request? # xmlhttpreq 객체로 전달된 요청인지 여부

# => 
```