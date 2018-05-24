# Local 에서 Https 설정하기

 ```bash
$ cd ~/.ssh

$ openssl genrsa -des3 -out server.orig.key 2048

$ openssl rsa -in server.orig.key -out server.key

$ openssl req -new -key server.key -out server.csr

$ openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt

$ echo "127.0.0.1 localhost.ssl" | sudo tee -a /private/etc/hosts

$ puma -b 'ssl://127.0.0.1:3000?key=/Users/유저이름/.ssh/server.key&cert=/Users/유저이름/.ssh/server.crt'
 ```



