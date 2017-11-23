# A server is already running. Check /home/..name/rprojects/railsapp/tmp/pids/server.pid.

맨날 코드 검색하는거 귀찮아서 정리

## solve
##### terminal
```
$ kill -9 $(lsof -i tcp:3000 -t)
```