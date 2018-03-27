

# [IaaS] rails nginx deploy - production (복붙용)

나중에 편하게 하려고 정리. (많이 썻는데 최고다. pdf 파일로 정리하면 복붙 구려지는데 깃헙짱!)

#### 만약에

development모드인데 production으로 변경하고싶어요! 하는 사람들은

`[Deploy] Ruby On Rails 배포하기 (명령어 파헤치기)` 문서 하단을 참고하세요



##### setting

```bash
$ sudo apt-get update
```

```bash
$ sudo apt-get install git-core curl zlib1g-dev build-essential libssl-dev libreadline-dev libyaml-dev libsqlite3-dev sqlite3 libxml2-dev libxslt1-dev libcurl4-openssl-dev python-software-properties libffi-dev nodejs
```

##### ruby

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

```bash
$ echo "gem: --no-document" > ~/.gemrc
```

```bash
$ gem install bundler
```

##### project

```bash
$ git config --global color.ui true
$ git config --global user.name "깃헙 이름을 입력하세요"
$ git config --global user.email "깃헙 이메일 주소를 입력하세요"
$ ssh-keygen -t rsa -b 4096 -C "github 이메일"
```

```bash
$ cat ~/.ssh/id_rsa.pub 
```

```bash
$ ssh -T git@github.com
$ yes
```

```bash
$ git clone 복사한 주소 
```



##### nginx

```bash
$ ls
$ vi Gemfile	
1. gem 'therubyracer' 주석 해제
1. gem 'figaro'  추가
2. gem 'passenger' 추가

```

```bash
$ gem install passenger
$ git clone git://github.com/dcarley/rbenv-sudo.git ~/.rbenv/plugins/rbenv-sudo
$ sudo dd if=/dev/zero of=/swap bs=1M count=1024
$ sudo mkswap /swap
$ sudo swapon /swap

$ rbenv sudo passenger-install-nginx-module
$ ruby
$ 1

$ bundle install --without development test 
$ bundle exec figaro install 
$ echo "production:" >> ./config/application.yml 
$ echo "  SECRET_KEY_BASE: $(bundle exec rake secret RAILS_ENV=production)" >> ./config/application.yml 
$ cd ..
```

```bash
$ bundle exec rake db:create RAILS_ENV=production
$ bundle exec rake db:migrate RAILS_ENV=production
$ bundle exec rake assets:precompile RAILS_ENV=production
```

```bash
$ sudo vi /opt/nginx/conf/nginx.conf
```

```bash
  server {
    listen 80;
    passenger_enabled on;
    rails_env production;
    root /home/ubuntu/ ‘내프로젝트폴더이름’/public; }
```

##### 서버 시작

```bash
$ sudo fuser -k 80/tcp
$ sudo /opt/nginx/sbin/nginx
```

##### 