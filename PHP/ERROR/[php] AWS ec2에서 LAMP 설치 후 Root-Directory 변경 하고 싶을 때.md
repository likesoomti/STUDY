# AWS ec2에서 LAMP 설치 후 Root-Directory 변경 하고 싶을 때 


## Problem 
맥에서는 bitnami에서 `MAMP`를 사용해 php 환경서버 세팅을 해준다. 이때는 `Apache2>conf>bitnami>bitnami.conf `에서 루트 디렉토리를 설정할 수 있었는데, aws에서는 bitnami로 깔수가 없어서 경로가 다르다. 

## Solve

ec2 디렉토리

- `/etc/httpd/conf/httpd.conf`

또한 FTP 설정을 위해 chmod 변경해야한다

- `sudo chown -R apache:apache /var/www/html`