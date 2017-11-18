# AWS ec2에서 LAMP 설치 후 Root-Directory 변경  

## Problem 
맥에서는 bitnami에서 `MAMP`를 사용해 php 환경서버 세팅을 해준다. 이때는 `Apache2>conf>bitnami>bitnami.conf `에서 루트 디렉토리를 설정할 수 있었는데, aws에서 제공해주는 LAMP의 경로는 달라서 애를 좀 먹었다.


## Solve

ec2 디렉토리

- `/etc/httpd/conf/httpd.conf`

또한 FTP 설정을 위해 chmod 변경해야한다

- `sudo chown -R apache:apache /var/www/html`



## 참고 
##### AWS 제공 LAMP Guide
- http://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/install-LAMP.html
##### FTP 설정 
- http://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/install-LAMP.html