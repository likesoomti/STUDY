# AWS Deploy template

하루동안 개고생.. 정리하기 왜냐면 까먹으면또 지옥의 시작이니까 

## 1.aws marketplace

`aws marketplace` 에서 하나하나 다운받는게 아닌 툴킷 다 설치된 인스턴스를 생성해준다. 클릭한번에 apache2 mysql php !
굿잡!

```
https://aws.amazon.com/marketplace/pp/B00NNZUY96?qid=1510928900378&sr=0-2&ref_=srh_res_product_title
```
### 2.pem key permission

pem key 읽기모드 생성해주기
```
$ chmod 400 test.pem
```

### 3. AWS 접속하기
```
$ ssh -i test.pem ubuntu@ec2주소
```

## 잠깐
 `ec2 인스턴스 오른쪽클릭 > 인스턴스설정 > 시스템로그 가져오기` 확인해서 mysql 비밀번호를 캡쳐해두자 ! 

나중에 가면 넘어가서 볼수가없다..

### 4. 템플릿 압축파일 다운로드
```
$ wget "템플릿 압축해놓은 주소"
```

### 5. 템플릿 압축파일 풀기

#### 압축파일 넣을 폴더 생성
```
$ mkdir template
```

### 6. 압축파일 풀기
```
$ unzip flex_2.7_template_only.zip -d template/
```

### 7. 경로 설정

bitnami = 빛나미 
빛나미가 제공해주는 게 아닌 다른 lamp는 이상하리만큼 경로가 제대로 설정되지 않았다. 하지만 갓 빛나미.. 가능하다 

##### /opt/bitnami/apache2/conf/bitnami/bitnami.conf 
Document root 를 변경 해준다
```
<VirtualHost _default_:80>
  DocumentRoot "/home/bitnami/template"
  <Directory "/home/bitnami/template">
```

### 8. 재부팅 
아쉽게도, 이유는 모르겠지만
`sudo service apache2`가 작동이 안된다. (service is not recoginzed)

그래서 apache2를 reinstall을 하자 bitnami.conf 가 와장창 사라져 망했다...

재부팅 명령어는 찾아서 다해봐도 안됨...
무식하게 인스턴트를 재부팅 해줫따...ㅎㅎ..해결방법 아시는분들은 꼭 알려주세요.. 

무식하게 인스턴스를 재부팅하면 joomla 가입 페이지가 나온다. 스무스하게 따라가면 된다.


### 9. 줌라가입 설정

##### database password

mysql은 빛나미가 생성해줬기 때문에 아마 root 아님 user로 아이디를 생성해준다. 비밀번호는 위에 잠깐! 에서 설명했을때 복사했던 비밀번호를 적으면 된다

##### ftp
왠만하면 칸을 비우고 설정하자

##### installation 삭제

설치가 끝나고나면, `configuration` 설정과 `installaion을` 삭제하라고하는데. 로컬 컴퓨터에서는 자동으로 되었지만 `linux`에서는 왜인진 몰라도 안된다 

#### configuation.php 만들기

템플릿을 다운받은 경로에

```
$ nano configuation.php
```

를 만들고 복사해서 붙혀넣기. 
`esc` > `:` > `wq` 누르고 저장하고 나오면 된다.
#### installation 삭제

```
$ rm -r installation
```

하고 루트 페이지로 들어가면 완성된다 ! 
