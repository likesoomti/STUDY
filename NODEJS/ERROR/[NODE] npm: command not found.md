# npm: command not found


## problem
node를 최신 버전을 다시 설치했더니 command not found가 발생하였다. 

export PATH 를 잘못 설정해줘서 아예 ```cd``` ```ls``` 등 모든 bash 명령어가 안되었다..

## solve

```
export PATH="/usr/bin:/bin:/usr/sbin: 
```

명령어를 입력해 주었더니 되었다.