# 로컬컴퓨터에 설정한 git 계정 변경하기.md

맥북에 설정한 계정을 변경해야 할 경우가 있다. 

이럴 경우에는 `git config ` 설정 변경을 통해서 고칠 수 있다.



## git config --global

```console
$ git config --global user.name "soomti"
$ git config --global user.email soom93@gmail.com
```

이렇게 변경한 설정파일을 확인하고 싶다면

`git config --list` 를 통해 확인할 수 있다.



## git config --list

```
...
user.name=soomti
user.email=soom93@gmail.com
```

