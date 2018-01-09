# Rbenv Ruby version Change 

## problem 

맥에 루비를 설치. 기본으로 깔려있는 `2.3.3` 버전이 아닌 새로운 `2.4.3` 버전을 설치해서 사용하려고 했지만 안되었다.

## solve

`~/.bash_profile` 에 밑에 값 넣어줌 

```
export PATH="$HOME/.rbenv/bin:$PATH"
eval "$(rbenv init -)"
```
