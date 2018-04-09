# 알아두면 좋은 명령어



1. production db 삭제하고 싶을 때

   ```bash
   $ RAILS_ENV=production rake db:drop DISABLE_DATABASE_ENVIRONMENT_CHECK=1 
   ```

2. production create

   ```bash
   $ bundle exec rake db:create RAILS_ENV=production
   $ bundle exec rake db:migrate RAILS_ENV=production
   $ bundle exec rake assets:precompile RAILS_ENV=production
   $ bundle exec rake db:seed RAILS_ENV=production
   ```

   ​

3. install

   ```bash
   $ bundle install --without development test 
   ```

   ​

