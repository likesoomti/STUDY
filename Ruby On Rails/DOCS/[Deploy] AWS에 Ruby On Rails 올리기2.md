#  [Deploy] AWS에 Ruby On Rails 올리기 - Production 모드 

저번에 디플로이 한건 development 를 다 ~ 받아서 문제가 발생한다. 편리한  rails_db 도 설치가 되어버리면 아무나 다 db 상태를 볼수있음.. 멋쟁이사자처럼 5기 마지막강의와 비교해 보면서  production 모드 배포를 정리해보고자 한다.

## 설치

### sudo 설치

##### 이전 development 설치  

```bash
$ sudo apt-get update
```

```bash
$ sudo apt-get install git-core curl zlib1g-dev build-essential libssl-dev libreadline-dev libyaml-dev libsqlite3-dev sqlite3 libxml2-dev libxslt1-dev libcurl4-openssl-dev python-software-properties libffi-dev nodejs
```

##### 추가 설치 파일

```bash
$ sudo apt-get install  mysql-server mysql-client libmysqlclient-dev imagemagick
```

강의에서는 production 모드에서 mysql을 사용하기 위해 설치를 했다. 방대한 데이터를 관리하기에는 sqlite보다는 mysql이 편리하다는 점에서 설치하는데, 사실 복붙하느라 이런게 있는지 몰랐음.. 내가 배포할 서비스는 아주 데이터가 적기때문에 그냥 sqlite 를 사용하려고 한다. 이부분을 적용하고자 한다면 https://github.com/likelion-net/auto-deploy-script 잘 정리되어있으니 참고하면 좋을것 같다. 

 `ImageMagick` 또한 설치가 되었는데, 이미지 용량 조정해주는 라이브러리이다. 업로드가 필요한 배포서비스여서 잘됨 굿!

한줄로 요약하면 mysql 안쓰면 이전 문서랑 그대로 해도됨!



### Ruby on Rails 환경 만들어주기

#### ruby

루비 환경 만들어주는건 `development` 모드던 ` production` 같다. 

```bash
$ git clone https://github.com/rbenv/rbenv.git ~/.rbenv
```

```bash
$ echo 'export PATH="$HOME/.rbenv/bin:$PATH"' >> ~/.bashrc
```

```bash
$ echo 'eval "$(rbenv init -)"' >> ~/.bashrc
```

```bash
$ exec $SHELL
```

```bash
$ git clone https://github.com/rbenv/ruby-build.git ~/.rbenv/plugins/ruby-build
```

```bash
$ rbenv install 2.3.3
```

```bash
$ rbenv global 2.3.3
```

```bash
$ rbenv rehash
```

```bash
$ ruby -v 
```



##### 추가된것!!!!!!!!!!!!!!!

이전 문서에 없는게 추가되었다. gem 파일에 문서 타입의 파일들은 설치하지 말라는 명령어 인것 같다. 

쓸데없는건 설치하지말라는 느낌인듯 

```bash
$ echo "gem: --no-document" > ~/.gemrc
```

```bash
$ gem install bundler
```

#### rails

원래 문서에서는 레일즈를 이렇게 설치해줬다

```bash
$ curl -sL https://deb.nodesource.com/setup_4.x | sudo -E bash -
```

```bash
$ sudo apt-get install -y nodejs
```

```bash
$ gem install rails -v 4.2.5
```

```bash
$ rbenv rehash
```

```bash
$ rails -v 
```

를 확인해보면 레일즈가 잘 설치 된 것을 확인할 수 있습니다.

####  하지만, production Deploy 강의에서는 깔지않는다!!! 왜냐면 내 프로젝트에 rails gem이 깔려있기 때문에 상관이 없다고 한다! 그래서 이거 안해줘도 됨!!!! 

보통 ec2에서 여러 rails 프로젝트를 돌릴때 이렇게 사용할 수 있다고 하지만, 보통 하나만 돌리니까 쓰루하자.

### 프로젝트 불러오기

그대로 해주면 된다 

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

```bash
$ ssh -T git@github.com
$ yes
```

##### 나의 프로젝트 가져오기

```bash
$ git clone 복사한 주소 
```

#### nginx 설정하기

##### development 모드에서는 이렇게했다.

```bash
$ ls
$ vi Gemfile	
```

##### Gemfile.rb

gem 파일에서 3가지 작업을 한다.

1. gem 'therubyracer' 주석 해제


1. gem 'figaro'  추가
2. gem 'passenger' 추가

후

```bash
$ bundle install
```

을 하시면 gem 이 추가된다.

##### production 모드

```bash
$ bundle install --without development test 
$ bundle exec figaro install 
$ echo "production:" >> ./config/application.yml 
$ echo "  SECRET_KEY_BASE: $(bundle exec rake secret RAILS_ENV=production)" >> ./config/application.yml 
$ echo "  DATABASE_PASSWORD: $2" >> ./config/application.yml 
$ cd ..
```

1. bundle install 시 development, test에 있는 부분을 제외한다.
2. echo는 저 파일끝에 한줄 추가해준다.

#### 

```bash
$ sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys 561F9B9CAC40B2F7
```

이게 뭔지 몰라서 검색해봤떠니 저장소를 참조하는 키가 ubuntu keyserver에 등록되지 않았기 때문이다...? 라는 이상한 답변을 받았다. sudo apt-get update 가 안될때 공개키가 없어서 문제라는데, 공개키를 설정해주는것 같다. 언제쓰는지는 자세히 알아봐야할듯. 

```bash
$ sudo apt-get install -y apt-transport-https ca-certificates
```

apt-transport-https: https://로 만 받을 수 있는 데이터를 가져올 수 있는것같음.

ca-certificates: ssl 통신 증명 해주는 거같음

##### gem 으로 설치하던 것과 다르게 url로 가져와 설치하는 것 같다. 뭐가 더 낳은지는 모르겠지만.

하지만 ㅠㅠ실패했다.......쉘에 있는걸 복붙해ㅓㅅ 안되는듯한데 