



# [Deploy] AWS에 Ruby On Rails 올리기

멋쟁이 사자처럼 3기 갓 유재욱 님이 Deploy를 쉽게하는 파일을 공유해주셨는데,  항상 쓰는 컴퓨터도 다르고, pdf  가 복붙이 힘든 부분이있어서 정리를 했다. 개인적인 복붙 내용이기 때문에 불친절할 수 있는데.

 ubuntu로 생성 후 키 받고, 보안그룹 잘 설정한 이후이고, 한번 deploy를 해본 경험이 있다면 편리하게 쓰일것이다.



## 접속 

#### 1. pem key 권한 변경 

```bash
$ chmod 400 "pemkey.pem"
```

ec2에 들어갈 비밀번호인 pem 키를 읽기 모드로 변경

#### 2. 접속

```bash
$ ssh -i ubuntu@인스턴스주소
```

인스턴스를 클릭하면 퍼블릭 인스턴스 DNS 가 있는데 그걸 복사해서 접속

귀찮다면 우클릭 인스턴스에 연결 에 나오는 부분을 복붙해서 치면 꿀!



## 설치

#### apt-get(Advanced Packaging Tool)

우분투(Ubuntu)에서 사용하는 패키지 도구입니다. `gem` , `npm` 과 비슷하다.

우분투에서 쉽게 패키지를 받을수 있는 툴로, 보통 개발 관련한 패키지를 설치할 때 사용

#### 1. 업데이트하기

```bash
$ sudo apt-get update
```

현재 인스턴스에 설치된 apt-get 를 새로운 버전이 나왓을지도 모르니 업데이트를 해준다. 

#### 2. 필요한 도구 설치

ubuntu 는 아무것도 안 깔려 있는 상태이기 때문에, 필요한 개발 패키지들을 깔아줘야 함.

```bash
$ sudo apt-get install git-core curl zlib1g-dev build-essential libssl-dev libreadline-dev libyaml-dev libsqlite3-dev sqlite3 libxml2-dev libxslt1-dev libcurl4-openssl-dev python-software-properties libffi-dev nodejs
```

이렇게 깔면 되는데, 어떤걸 깔았는지 설치한 리스트를 알아보았다.



#### 설치 리스트 

`git-core` : 깃을 사용하기 위한 패키지

`curl` : command line 데이터 전송 툴. command line을 통해 데이터를 다운/업로드를 할 수 있다. http/https/ftp/telnet 등 여러 프로토콜에서 사용 가능한 툴 

`zlibig-dev` : 검색해보니 임베디드 GNU C 라이브러리를 가지고 있는 패키지인듯 하다 

`build-essential` :gcc, make 한번에 설치 해주는 패키지

`libssl-dev` :OpenSSL 통신을위한 SSL 및 TLS 암호화 프로토콜 구현이 들어간 패키지

`libreadline-dev` : GNU readline 및 history 라이브러리, 개발 파일 이라는데 맞는지 모르게씀. 

`libyaml-dev` :  YAML 파서 라이브러리. YAML은 데이터 직렬화 양식 언어. 마크업이랑 비슷하지만 마크업이 아니라고 한다. 마크업 보다는 데이터 중심.

`libsqlite3-dev` : sqlite3 library 패키지. `sqlite3 `를 사용하려면 설치를 해줘야한다. 

`sqlite3`: sqlite3 을 커맨드라인에서 사용할수 있게 해주는 패키지. 

`libxml2-dev` : xml 라이브러리 개발 관련 패키지 

`libxslt1-dev` :xml 파일을 html, text 등으로 변환시켜주는 패키지 

`libcurl4-openssl-dev` :  데이터 통신에 쓰이는 프로토콜인 TLS와 SSL의 오픈 소스 라이브러리. SSL 인증서, HTTP POST, HTTP PUT, FTP 업로드, HTTP 폼 기반 업로드 등 

`python-software-properties`:  설치한 패키지들을 관리해주는 소프트웨어 

`libffi-dev` : Foreign function interface. 다른 언어의 함수를 사용할 수 있게 만들어주는 인터페이스.

` nodejs` : 다들 아는 node.js 또한 설치한다.  레일즈 프레임워크는 JavaScript Runtime Environment가 사용된다.  Asset Pipeline을 관리하는 데실행되는 환경이기 때문에 설치를 해주어야한다.



#### 3. 루비 설치

루비와 레일즈를 설치하기 위한 환경을 제공해주는건 크게 `rvm` 과 `rbenv`로 나뉘는데,

레일즈 사이트에서는 `rbenv`로 깔기를 권장하고 있습니다. rbenv는 앱 별로 다른 루비를 사용 가능하고, 쉽게 업데이트 되는 장점이 있다.  

##### rbenv git 클론하기

```bash
$ git clone https://github.com/rbenv/rbenv.git ~/.rbenv
```

##### 환경 변수 추가해주기 

보통 프로그래밍 랭기지는, 설치한 곳 파일 내부에서 작동한다. 따라서 이 소스를 해석하기 위해서는 항상 설치된 경로 full path를 써줘서 사용하거나, 그 내부에서 명령어를 사용해야하는 불편함이 있다. 아무데서나 편리하게 쓰기위해 환경 변수를 추가해서 전역 폴더에서 사용할 수 있도록 만들어준다. 

```bash
$ echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bashrc
```

##### rbenv 초기화 시켜주기

rbenv 가 동작하기 위해서는 초기화 작업이 필요한데. 위에 명령어를 통해 쉘을 실행할때마다 rbenv를 초기화 시켜주는 코드를 작성한다. eval() 함수는 안에 문자열 코드를 실행시킬 수 있는 기능을 가지고 있다.

```bash
$ echo 'eval "$(rbenv init -)"' >> ~/.bashrc
```

##### 저장 후 실행

현재 실행하는 쉘을 끄지 않고 재부팅. 위에서 `bahsrc` 에 설정한  rbenv 가 실행된다. 

```bash
$ exec $SHELL
```

#####  루비 플러그인 설치

루비를 설치하기 위해서는 플러그인을 설치해야한다.

```bash
$ git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
```

##### 루비 설치

루비를 설치. 원하는 버전을 입력하면 된다.

```bash
$ rbenv install 2.3.0
```

##### 루비 전역 설정

`global` 은 전역설정을 하기위한 명령어. 전체 경로에서 해당 버전의 루비를 사용할 수 있습니다.

``` bash
$ rbenv global 2.3.0
```

##### 루비 환경 재설정

루비 환경을 재 설정해주기 위해 rehash  명령어.

```bash
$ rbenv rehash
```

#####  루비 버전 확인해보기

```bash
$ ruby -v 
```

#####  gem 설치하기

루비에서 라이브러리를 도구인 gem 설치 

``` bash
$ gem install bundler
```



#### 4. Rails 설치하기

Rails는 `coffeescript` 와 `Asset Pipeline` 을 사용할 때 JavaScript Runtime 환경을 사용한다. 

따라서 레일즈를 깔기 전에 node.js 먼저 설치해야한다. 

##### 셋업 파일 설치하기

```bash
$ curl -sL https://deb.nodesource.com/setup_4.x | sudo -E bash -
```

##### 노드 설치

```bash
$ sudo apt-get install -y nodejs
```

##### rails 설치하기 

```bash
$ gem install rails -v 4.2.5
```

##### 레일즈 적용하기

 ```bash
$ rbenv rehash
 ```

##### 레일즈 버전 확인해보기

```bash
$ rails -v 
```

를 확인해보면 레일즈가 잘 설치 된 것을 확인할 수 있습니다.



#### 프로젝트 불러오기

##### ssh를 통한 깃 연결

```bash
$ git config --global color.ui true
$ git config --global user.name "깃헙 이름을 입력하세요"
$ git config --global user.email "깃헙 이메일 주소를 입력하세요"
$ ssh-keygen -t rsa -b 4096 -C "github 이메일"
```

을 하고 https://github.com/settings/keys 로 이동해보면

SSH KEY 라는 탭이 있다. 버튼을 누르면 title 과 key 라는 탭이 보이는데 title 에는 원하는 이름을 쓰고 key 에는

```bash
$ cat ~/.ssh/id_rsa.pub 
```

라는 명령어를 입력하고 나온 내용을 복붙해준다.

```bash
$ ssh -T git@github.com
$ yes
```

를 입력했을때 성공적이라는 메세지가 입력되면 잘 연결 된거!



##### 나의 프로젝트 가져오기

``` bash
$ git clone 복사한 주소 
```



#### 6. nginx 설치하기 

```bash
$ ls
$ vi Gemfile	
```

##### Gemfile.rb

gem 파일에서 3가지 작업을 한다.

1. gem 'therubyracer' 주석 해제


2. gem 'figaro'  추가
3. gem 'passenger' 추가

후

```bash
$ bundle install
```

을 하시면 gem 이 추가된다.

##### 오류

이부분 그냥 지나치면 오류가 남 ㅠㅠ  passenger gem 이 잘 안깔리는것 같다. 이 부분에 에러를 방지하고자

```bash
$ gem install passenger
```

명령어를 한번 입력해야한다.. 원인은 모르겠음.

##### rbenv-sudo 설치

`passenger-install-nginx-module`  을 설치해야 하는데, rbenv는 sudo 권한에 문제가 있어 `rbenv-sudo` 플러그인을 깔아야 한다.

```bash
$ git clone git://github.com/dcarley/rbenv-sudo.git ~/.rbenv/plugins/rbenv-sudo
```

##### 가상 메모리 설정

```bash
$ sudo dd if=/dev/zero of=/swap bs=1M count=1024
$ sudo mkswap /swap
$ sudo swapon /swap
```

##### passenger-nginx 모듈 설치

```bash
$ rbenv sudo passenger-install-nginx-module
$ ruby
$ 1
```



##### 경로 수정

오래 걸리는 설치 후 몇가지 경로를 바꾼다.

```bash
$ sudo vi /opt/nginx/conf/nginx.conf
```

##### server

```bash
  server {
    listen 80;
    passenger_enabled on;
    rails_env development;
    root /home/ubuntu/ ‘내프로젝트폴더이름’/public; }
```

##### 서버 시작

````bash
$ sudo fuser -k 80/tcp
$ sudo /opt/nginx/sbin/nginx
````





이렇게 배포를 하면 사실 development 모드이기 때문에 배포하면 큰일난다. 막 rails/db 다 들어가지고 난리남.

production 배포 모드는 차후에 올리겠음.