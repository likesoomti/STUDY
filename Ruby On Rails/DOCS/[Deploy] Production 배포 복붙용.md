

# [Deploy] production 복붙용

나중에 편하게 하려고 정리.

#### 만약에

development모드인데 production으로 변경하고싶어요! 하는 사람들은

`[Deploy] AWS에 Ruby On Rails 올리기` 문서 하단을 참고하세요



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



##### mysql쓰거나 imagemagick 쓸때

```bash
$ sudo apt-get install  mysql-server mysql-client libmysqlclient-dev imagemagick
```

