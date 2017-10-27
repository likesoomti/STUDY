# mongoDB
nosql
#### 2. 데이터 베이스 컬렉션

#### 3. 몽고db 실행하기
mongoDB 서버를 실행한다.
```
$ mongod
```
다른 터미널을 키고 터미널 실행한다 (하이시에라는 바로할수있당...)
mongo console 실행
```
$ mongo
```
#### 4. example

```javascript
// show mongo database list

> show dbs

admin  0.000GB
local  0.000GB

// 현재 사용하는 데이터베이스를 삭제해준다. 
> db.dropDatabase();
{ "ok" : 1 }

// 현재 사용하는 database 목록을 보여준다.
// 신기한건 db를 삭제했음에도 불구하고, db 명령어를 입력해도 지워진 database값이 반환된다. 
> db
node_study

// 사용하는 데이터 베이스를 선택한다.
> use node_study;
switched to db node_study

// 바뀐 데이터 베이스 값을 확인!
> db
node_study

> use node
switched to db node

// create collection
> db.createCollection('products')
{ "ok" : 1 }

// 컬렉션 확인
> show collections
products
```

#### 몽고 js 설치하기

``` javascript
npm install mongojs 
// mongo - node 연결 추출. 
var mongo = require('mongojs');
var db = mongo('node',['products']);
// mongojs의 모듈의 메소드는 마지막으로 (err,data)인자를 가진 콜백 메소드를 가진다.
db.products.find(
    function(err,data){
        console.log(data);
    });
```