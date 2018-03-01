



# [Deploy] AWS에 Ruby On Rails 올리기

`AWS EC2` 에 항상 deploy 하는데, 

 ubuntu로 생성 후 키 받은 이후 부터 설명합니다.



## 접속 

#### 1. pem key 권한 변경 

```bash
$ chmod 400 "pemkey.pem"
```

ec2에 들어갈 비밀번호인 pem 키를 읽기 모드로 변경해줍니다.

#### 2. 접속

```bash
$ ssh -i ubuntu@인스턴스주소
```

인스턴스를 클릭하면 퍼블릭 인스턴스 DNS 가 있는데 그걸 복사해서 접속합니다.

귀찮다면 우클릭 인스턴스에 연결 에 나오는 부분을 복붙해서 치면 꿀!



## 설치

#### apt-get(Advanced Packaging Tool)

우분투(Ubuntu)에서 사용하는 패키지 도구입니다. `gem` , `npm` 과 비슷하다고 보면 될것 같아요.

우분투에서 쉽게 패키지를 받을수 있는 툴로, 보통 개발 관련한 패키지를 설치할 때 사용합니다. 그럼 개발 관련 패키지를 설치해봅시다!

#### 1. 업데이트하기

```bash
$ sudo apt-get update
```

현재 인스턴스에 설치된 apt-get 를 새로운 버전이 나왓을지도 모르니 업데이트를 해준다. 

#### 2. 필요한 도구 설치

ubuntu 는 아무것도 안 깔려 있는 상태이기 때문에, 필요한 개발 패키지들을 깔아줘야 합니다.

```bash
$ sudo apt-get install git-core curl zlib1g-dev build-essential libssl-dev libreadline-dev libyaml-dev libsqlite3-dev sqlite3 libxml2-dev libxslt1-dev libcurl4-openssl-dev python-software-properties libffi-dev nodejs
```

이렇게 깔면 되는데, 어떤걸 깔았는지 설치한 리스트를 찾아봤습니다.

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

루비와 레일즈를 설치하기 위한 환경을 제공해주는건 크게 `rvm` 과 `rbenv`로 나뉩니다. 

레일즈 사이트에서는 `rbenv`로 깔기를 권장하고 있습니다. rbenv는 앱 별로 다른 루비를 사용 가능하고, 쉽게 업데이트 되는 장점이 있습니다.  `rbenv`로 깔겠습니다. 

##### rbenv git 클론하기

```bash
$ git clone https://github.com/rbenv/rbenv.git ~/.rbenv
```

##### 환경 변수 추가해주기 

보통 프로그래밍 랭기지는, 설치한 곳 파일 내부에서 작동합니다. 따라서 이 소스를 해석하기 위해서는 항상 설치된 경로 full path를 써줘서 사용하거나, 그 내부에서 명령어를 사용해야하는 불편함이 있습니다. 매우 불편하죠. 아무데서나 편리하게 쓰기위해 환경 변수를 추가해서 전역 폴더에서 사용할 수 있도록 만들어줍니다. 

```bash
$ echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bashrc
```

##### rbenv 초기화 시켜주기

rbenv 가 동작하기 위해서는 초기화 작업이 필요한데. 위에 명령어를 통해 쉘을 실행할때마다 rbenv를 초기화 시켜주는 코드를 작성합니다. eval() 함수는 안에 문자열 코드를 실행시킬 수 있는 기능을 가지고 있습니다.

```bash
$ echo 'eval "$(rbenv init -)"' >> ~/.bashrc
```

##### 저장 후 실행

현재 실행하는 쉘을 끄지 않고 재부팅 해줍니다. 위에서 `bahsrc` 에 설정한  rbenv 가 실행되겠죠 ? 

```bash
$ exec $SHELL
```

#####  루비 플러그인 설치

루비를 설치하기 위해서는 플러그인을 설치해야합니다. 설치해 줍시다.

```bash
$ git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
```

##### 루비 설치

루비를 설치합니다! 오래걸려요. 2.3.0 으로 되어있지만, 원하는 버전을 설치해주시면 됩니다.

```bash
$ rbenv install 2.3.0
```

##### 루비 전역 설정

`global` 은 전역설정을 하기위한 명령어입니다. 전체 경로에서 해당 버전의 루비를 사용할 수 있습니다.

``` bash
$ rbenv global 2.3.0
```

##### 루비 환경 재설정

루비 환경을 재 설정해주기 위해 rehash  명령어를 사용합니다.

```bash
$ rbenv rehash
```

#####  루비 버전 확인해보기

설치가 끝났으니 루비 버전을 확인해 봅시다

```bash
$ ruby -v 
```

잘 설정 된 것을 확인할 수 있습니다 :D

#####  gem 설치하기

루비에서 라이브러리를 설치해주는 gem 을 받아봅시다. 

``` bash
$ gem install bundler
```



#### 4. Rails 설치하기

Rails는 offeescript 와 Asset Pipeline 을 사용할 때 JavaScript Runtime 환경을 사용합니다. 이를 위해 node.js 를 깔아야 합니다. 레일즈를 깔기 전에 node.js 먼저 설치하겠습니다.

