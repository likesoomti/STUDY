



#  [Deploy] Ruby On Rails  let's encrypt 를 이용한 HTTPS 설정하기(파헤치기 & 삽질).md

페이스북 로그인을 연동하기 위해서 평소대로 설정을 했는데..

`https`가 필수로 체크가 되어있었다.

로컬호스트에서는 어떻게 돌리려고..ㅠㅠ? 사실 방법이있는데 못찾은거일수도있다..(확률99%)



http와 https 에 대해 알고있는건 프로토콜! https 가 보안이 더 쩌는거! (시험 끝나면 다날라가는 나란인간..) 의 개념밖에 없어서.. 간단히 알아보고!  쉽게쉽게 정리를 해보았다!

##### http 와 https

http 자체는 통신 프로토콜로 쉽게 말하면 나와 컴퓨터간에 약속이다!

https://github.com/likesoomti/ 이라고 웹 브라우저에 입력한다면, 이건 브라우저에게

```
http 통신 방법으로 github.com/likesoomti/  에있는 데이터를 가져와줘!  
```

라는 명령이 된다. 

그럼 웹브라우저는 http 통신으로 내가 서버에 올린 `데이터(쉽게 html이라고 생각하자)` 를 가져와 사용자가 볼수있게 해석해서 예쁜 사이트를 보여준다!

우리가 쓰는 웹은 기본 프로토콜이 http 이당. 그래서 어느 사이트를 가든 http가 알아서 붙지만,

**암호화가 되지않아 데이터를 중간에서 뽑아갈수 있다는 취약성이 있다. 그럼 비밀번호 다뚤리구 돈잃구 슬프구....?**

이를 보완하기 위해

`https` 프로토콜이 등장했다. 그래서 https 로 설정을 하면  

1. 데이터를 암호화
2. 상대방 신원 확인

을 통해 **안전한 통신**을 할 수 있게 만든다. 

https 는 ssl 층을 감싸서 더 안전하게 만든 방식인데 , ssl 인증은 인증서를 발급해주는 전문 기관들이 있다. 여기서 사이트를 인증받으면 https 접속이 되고,  사이트에 들어가면 초록색 글씨와 자물쇠 아이콘을 가진 안전함! 이 뜨게 된다

Verisign에서 부터 lets encrypt,comodo, amazon certificate manager  등… 다양한 인증기관이 있지만 **유료**.

가난뱅이 개발자를 위해 무료로 ssl 을 발급해주는 ```let's encrypt``` 를 사용할거다!

이제 우리가 배포한 도메인을 https 설정을 해보자!!



------



ssl 인증을 받으려고 엄청 삽질을 했다ㅠㅠ



첫번째 방법은

1. ACM 에서 SSL 받기
2. 로드밸런싱을 통해 자동으로해주기

였는데 적용이 되지 않았다.. 그 원인을 해쳐보고자 했지만, 원인을 해치다..서버 3개를 생각없이 돌리다가 과금!

을 발견 로드밸런싱을 이용하면 추가 과금이 될 것 같아 포기해따. 로드밸런싱까지 설정되었지만 왜 안되는지 잘 모르겠움 ..ㅠㅅㅠ 나중에 깊게 파보겠다..



그래서 여기저기 알아보다 

**LIKELION X Korea Univ. Author : 채희재(갓 오브 갓 갓 갓..얼굴도 모르지만 정말 감사합니다…)** 

님께서 작성해주신. HTTPS 설정하기를 토대로 작업을 하게 되었다!



저 문서는 ruby 설치부터 되어있는데 , 나는 예전에 정리한 밑의 부분으로 하기 때문에, 



**[IaaS] rails nginx deploy - production (복붙용)**

이후에 시작한 내용이다.

설치 부터 시작하는 사람들은 

https://github.com/likesoomti/STUDY/tree/master/Ruby%20On%20Rails/DOCS

에서 저 제목의 문서를 참고하면 좋을것 같다 :-)



##### nginx 설치

위에 문서를 배포하고 참고한 문서에는 nginx 를 gem 이 아닌 다른 방식으로 깔아주었다. 

그래서 다시 설치!

일단, 마음의 안정을 위해  80번 포트를 죽이고 시작하자.

안그러면 원래 켰던 `sudo /opt/nginx/sbin/nginx` 와 겹쳐서 

```
nginx.service is not active, cannot reload.
```

라는 에러가 엄청엄청떠서 몇시간동안 고생했는데 

```bash
$ sudo fuser -k 80/tcp
```

하니 바로 명령어 사용가능..ㅎㅎ 삽질했던 원인 하나. 멍청했따..

```bash
$ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 561F9B9CAC40B2F7
```

```bash
$ sudo apt-get install -y apt-transport-https ca-certificates
```

```bash
$ sudo sh -c 'echo deb https://oss-binaries.phusionpassenger.com/apt/passenger xenial main > /etc/apt/sources.list.d/passenger.list'
# 이거 제가 ubuntu xenial 환경이여서 이렇게 나오는겁니다. 아니신 분들 밑에서 오류날수있어요 참고해주세요!
```

```bash
$ sudo apt-get update
```

```bash
$ sudo apt-get install -y nginx-extras passenger
```

이렇게 설치를 하면 **sudo service nginx start(stop)(restart)** 의 명령어가 사용 가능해진다!



##### Certbot 설치!

ssh 를 인증 받기 위해서 **무료로 발급을 해주는 ** Let `s Encrypt 를 사용해보겠다!

lets encrypt 에서는 cerbot으로 만들어주는데 내 환경은 밑에와 같다

https://certbot.eff.org/lets-encrypt/ubuntuxenial-nginx

여기서 다른환경인 사람들은 다른 환경 체크해서 하면 좋을듯!

```bash
 $ sudo add-apt-repository ppa:certbot/certbot$ sudo add-apt-repository ppa:certbot/certbot
 # 엔터 한번 눌러줘야해여!
```

```bash
 $ sudo apt-get update
```

```bash
$ sudo apt-get update
```

```bash
$ sudo apt-get install python-certbot-nginx -y
```



##### nginx 설정 해주기

```bash
$ sudo vim /etc/nginx/sites-available/default
# 41번 행의 server_name _; 의 _ 부분을
# 자신의 도메인을 넣어준다!
# server_name example.com www.example.com;
# ESC+:wq 로 저장 
```

```bash
$ sudo nginx -t
# nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
# nginx: configuration file /etc/nginx/nginx.conf test is successful
# 라는 메세지가 정상적으로 뜨는 지 확인한다.

```

```bash
$ sudo systemctl reload nginx # NGINX 리로드
```

##### 방화벽 업데이트

```bash
 $ sudo service nginx start
```

```bash
 $ sudo ufw status
 # Status: inactive 라고 뜰 것이다. active로 바꾸자.
```

```bash
 $ sudo ufw enable
 # y 라고 입력
```

```bash
 $  sudo ufw allow 'Nginx Full'
```

```bash
 $ sudo ufw allow OpenSSH
```



##### 인증서 받기

```bash
# AWS 인스턴스의 IP 주소를 example.com / www.example.com 에 연결해두고 시작. 
$ sudo certbot --nginx -d example.com -d www.example.com
# email 입력
# Agree / Yes 입력
# 2 (Redirect) 입력
# 접속해보면 자동으로 https로 연결됩니다. 적용되기까지 조금 시간이 걸립니다.
# 성공적으로 인증서가 설치되면 아래와 같은 메세지가 뜹니다.
# ---------------------------------------------------------------------------
Congratulations! You have successfully enabled https://heejae.site and https://www.heejae.site~~~~~
```

야 되는데…!!! 나는 redirect 따위 나오지 않고 

```bash
Failed authorization procedure. www.dailyquietime.com (http-01): urn:acme:error:connection :: The server could not connect to the client to verify the domain :: DNS problem: NXDOMAIN looking up A for www.dailyquietime.com

IMPORTANT NOTES:
 - The following errors were reported by the server:

   Domain: www.dailyquietime.com
   Type:   connection
   Detail: DNS problem: NXDOMAIN looking up A for
   www.dailyquietime.com

   To fix these errors, please make sure that your domain name was
   entered correctly and the DNS A/AAAA record(s) for that domain
   contain(s) the right IP address. Additionally, please check that
   your computer has a publicly routable IP address and that no
   firewalls are preventing the server from communicating with the
   client. If you're using the webroot plugin, you should also verify
   that you are serving files from the webroot path you provided.
 - Your account credentials have been saved in your Certbot
   configuration directory at /etc/letsencrypt. You should make a
   secure backup of this folder now. This configuration directory will
   also contain certificates and private keys obtained by Certbot so
   making regular backups of this folder is ideal.
```

에러 대뿜뿜!

route53 에서 도메인을 샀는데, 

name 부분에  www. 를 붙힌 채 ip를 연결했어야 했는데, 그냥 등록해서 발생한 오류였다.

이 에러가 난다면 해당 사이트에

```www.도메인이름.com``` 으로 들어갔을때 들어가지는지 확인해보자!

안되면 www 를 붙혀서 연결을 시켜주자! 나는 확인해보니 안되서 route53 에 name 부분에 www 를 붙힌 후 

붙힌 도메인이 들어가지는지 확인 한 다음 다시 명령어를 쳤다!

```bash

Please choose whether or not to redirect HTTP traffic to HTTPS, removing HTTP access.
-------------------------------------------------------------------------------
1: No redirect - Make no further changes to the webserver configuration.
2: Redirect - Make all requests redirect to secure HTTPS access. Choose this for
new sites, or if you're confident your site works on HTTPS. You can undo this
change by editing your web server's configuration.
-------------------------------------------------------------------------------
Select the appropriate number [1-2] then [enter] (press 'c' to cancel):
# 2(redirect) 입력 

```

을 치니

```
-------------------------------------------------------------------------------
Congratulations! You have successfully enabled https://dailyquietime.com and
https://www.dailyquietime.com

You should test your configuration at:
https://www.ssllabs.com/ssltest/analyze.html?d=dailyquietime.com
https://www.ssllabs.com/ssltest/analyze.html?d=www.dailyquietime.com
-------------------------------------------------------------------------------
```

하고 나도 성공 멘트를 확인할 수 있었다..ㅜㅠㅜㅜㅜㅜ감동적인 현장.. 이틀걸림..



##### 디프-헬만 그룹 추가하기

```bash
$ sudo openssl dhparam -out /etc/ssl/certs/dhparam.pem 2048
# *….++이  도배되며 오래 걸립니다.
```

이게 완성되면 

/etc/ssl/certs/ 에 dhparam.pem파일이 생성된다!

`nginx: [emerg] "ssl_dhparam" directive is duplicate in /etc/nginx/sites-enabled/default:70`

서버 블록에 추가되어야된다는 글을 봤는데, 자동으로 추가가 되는지 에러가났다. 그래서 지워주고 바로 밑에 명령어를 실행하였다.

```bash
$ sudo nginx -t
# nginx: the configuration file /etc/nginx/nginx.conf syntax is ok
# nginx: configuration file /etc/nginx/nginx.conf test is successful
```

##### 인증서 자동 갱신 설정

인증서는 90일만 유효해서 매번 갱신해줘야되는데, 귀찮으니 설정을 해주자

```bash
$ sudo crontab -e
# 텍스트 에디터를 자유롭게 선택합니다. 저는 3의 vim을 선택합니다.
# 마지막 줄에 다음을 추가하고 저장합니다.
15 3 * * * /usr/bin/certbot renew --quiet
# 15 3 * * * : 매일 3:15 AP에 갱신 명령을 수행한다.
# 이는 인증서가 만료 30일 이내에 자동 갱신되고 다시 로드되게 합니다
```



##### nginx 추가로 설정하기

```bash
sudo vim /etc/nginx/nginx.conf
# 1번째 줄을 다음으로 수정합니다. user www-data => ubuntu
user ubuntu;
#63번째 줄의에 추가하기
# include /etc/nginx/passenger.conf;
```

```bash
# passenger 설정 파일을 엽니다.
sudo vim /etc/nginx/passenger.conf
# 모든 내용을 지우고 다음 내용으로 대체합니다.
passenger_root /usr/lib/ruby/vendor_ruby/phusion_passenger/locations.ini; passenger_ruby /home/ubuntu/.rbenv/shims/ruby;
# 서버를 재시작합니다.
$ sudo service nginx restart
```



##### 프로젝트 연결해주기

```bash
$ sudo vim /etc/nginx/sites-enabled/default 

# 37번째 줄 근처에  있는 root를 수정
root /home/ubuntu/[프로젝트폴더명]/public;

# 41~42번째 줄쯤에 있는 server_name 아래에 다음을 추가합니다. 
passenger_enabled on;
rails_env production;

# 바로 아래의 location / { ... } 부분을 지우고 다음을 추가하고 저장합니다. error_page 500 502 503 504 /50x.html;
# 이렇게 하면 nginx 에러 페이지가 아닌, 내 에러 페이지를 띄워준다.
location = /50x.html {
root html; }

 
```



여기서 잘못된 참고할 사항은, nginx 를 깔아줄때 `rbenv sudo passenger-install-nginx-module` 을 이용해설치해서, 경로를 /opt/nginx~/nginx.conf 에 깔아주는데, 사실 참고한 페이지에서는 다른 방식으로 nginx 를 설치하기때문에, 굉장히 비효율적으로 https 를 설정한 것 같다. 

디플로이를 다시 할 일이 곧 생길건데 (이틀 정도뒤?) 이 참고사항들을 종합한 디플로이 / https 설정 문서를 정리해서 올리겠다.



##### 참고 사이트

- https://www.digitalocean.com/community/tutorials/how-to-secure-nginx-with-let-s-encrypt-on-ubuntu-14-04

- http://www.bsidesoft.com/?p=3340#main
- LIKELION X Korea Univ. Author - https 설정하기
- https://certbot.eff.org/lets-encrypt/