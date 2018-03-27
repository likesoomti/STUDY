# nginx.service is not active, cannot reload.

## ploblem

```bash
sudo systemctl reload nginx
nginx.service is not active, cannot reload.
```

서비스 배포 후,  nginx 을 재설치하는데 저게 계~속 뜬다 다시 설치해도 계~속뜬다. 넘화나..머야



## solve

```bash
$ sudo fuser -k 80/tcp

$ sudo service nginx start
$ sudo systemctl reload nginx

```

80 포트를 죽이고 하니 바로 실행된다 ^^ 이틀의 삽질~