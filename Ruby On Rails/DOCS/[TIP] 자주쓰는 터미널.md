# 자주 쓰는 터미널 명령어 

맨날 코드 검색하는거 귀찮아서 정리

## process 종료 시키기 
##### terminal
```
$ kill -9 $(lsof -i tcp:3000 -t)
```

## precompile

```
$ rake assets:precompile RAILS_ENV=production
```