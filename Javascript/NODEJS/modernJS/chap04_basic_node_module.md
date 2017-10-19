## chap04 기본 내장 모듈

Node.js 는 다양한 모듈 지원을 해주기 때문에 관련문서 사용법을 익히는 것이 좋다

##### Node.js DOC
https://nodejs.org/dist/latest-v6.x/docs/api/

--------- 

### 4.1 os 모듈 
##### nodejs 문서 사용법
 원하는 모듈 이름을 클릭하면 관련된 설명을 볼 수 있다.

#### sample

# OS(모듈이름)
###### mark
```
Stability: 2 - Stable
```
###### description

The ```os``` module provides a number of operating system-related utility methods. It can be accessed using:
###### sample
```
const os = require('os');
```
--------- 

### os 모듈의 메서드
간략한 설명 밑에 주요 메서드 들을 확인할 수 있다.

#### os.hostname() : 운영체제 호스트 이름 리턴
##### Added in: v0.3.3
- Returns: String

The os.hostname() method returns the hostname of the operating system as a string.

###### sample
```javascript
console.log(os.hostname());
```
###### result
```
hansumin-ui-MacBook-Pro.local
```

#### os.type() : 운영체제 이름 리턴
##### Added in: v0.3.3
- Returns: string

The os.type() method returns a string identifying the operating system name as returned by uname.

###### sample
```javascript
console.log(os.type());
```
###### result
```
Darwin
```

#### os.platform() : 운영체제 플랫폼 리턴 
##### Added in: v0.5.0
- Returns: string

The os.platform() method returns a string identifying the operating system platform as set during compile time of Node.js.

Currently possible values are:

'aix'
'darwin'
'freebsd'
'linux'
'openbsd'
'sunos'
'win32'
Equivalent to process.platform.

###### sample
```javascript
console.log(os.platform());
```
###### result
```javascript
darwin
```

#### os.arch(): 운영체제의 아키텍처 리턴 
##### Added in: v0.5.0
- Returns: string

The os.arch() method returns a string identifying the operating system CPU architecture for which the Node.js binary was compiled.
The current possible values are: 'arm', 'arm64', 'ia32', 'mips', 'mipsel', 'ppc', 'ppc64', 's390', 's390x', 'x32', 'x64', and 'x86'.

Equivalent to process.arch.

###### sample
```javascript
console.log(os.arch());
```
###### result
```javascript
x64
```

#### os.release() : 운영체제 버전 리턴 
##### Added in: v0.3.3
- Returns: string

The os.release() method returns a string identifying the operating system release.

Note: On POSIX systems, the operating system release is determined by calling uname(3). On Windows, GetVersionExW() is used.
###### sample
```javascript
console.log(os.release());
```
###### result
```javascript
15.6.0
```

#### os.uptime() : 운영체제 실행된 시간 리턴
##### Added in: v0.3.3
- Returns: integer

The os.uptime() method returns the system uptime in number of seconds.

Note: On Windows the returned value includes fractions of a second. Use Math.floor() to get whole seconds.

###### sample
```javascript
console.log(os.uptime());
```
###### result
```javascript
// 초로 반환
223999
```

#### os.loadavg() : 평균 로드시간 리턴
##### Added in: v0.3.3
- Returns: Array

The os.loadavg() method returns an array containing the 1, 5, and 15 minute load averages.

The load average is a measure of system activity, calculated by the operating system and expressed as a fractional number. As a rule of thumb, the load average should ideally be less than the number of logical CPUs in the system.

The load average is a UNIX-specific concept with no real equivalent on Windows platforms. On Windows, the return value is always [0, 0, 0].
###### sample
```javascript
console.log(os.loadavg());
```
###### result
```javascript
// 초로 반환
[ 2.2705078125, 2.49755859375, 2.48779296875 ]
```

#### os.totalmem() : 총 메모리 리턴
##### Added in: v0.3.3
- Returns: integer

The os.totalmem() method returns the total amount of system memory in bytes as an integer.

###### sample
```javascript
console.log(os.totalmem());
```
###### result
```javascript
// 초로 반환
8589934592
```

#### os.freemem() : 시스템 가용 메모리 리턴
##### Added in: v0.3.3
- Returns: integer

The os.freemem() method returns the amount of free system memory in bytes as an integer.

###### sample
```javascript
console.log(os.freemem());
```
###### result
```javascript
// 초로 반환
81633280
```
#### os.cpus(): cpu 정보 객체 리턴 
##### Added in: v0.3.3
- Returns: Array

The os.cpus() method returns an array of objects containing information about each CPU/core installed.

The properties included on each object include:

- model :string
- speed :number (in MHz)
- times :Object
- user :number
- nice :number
등등등..
```javascript
console.log(os.cpus());
```
###### result
```javascript
[ { model: 'Intel(R) Core(TM) i5-5257U CPU @ 2.70GHz',
    speed: 2700,
    times: { user: 10158440, nice: 0, sys: 7051720, idle: 51757490, irq: 0 } },~
     ]
```


#### os.networkInterfaces(): 네트워크 정보 배열 리턴
##### Added in: v0.6.0
- Returns: Object

The os.networkInterfaces() method returns an object containing only network interfaces that have been assigned a network address.

Each key on the returned object identifies a network interface. The associated value is an array of objects that each describe an assigned network address.

The properties available on the assigned network address object include:

- address : string 
- netmask : string 
- family : string>
- mac : string
- internal : boolean    
- scopeid : number

###### sample
```javascript
console.log(os.networkInterfaces());
```
###### result
```javascript
{ lo0:
   [ { address: '::1',
       netmask: 'ffff:ffff ~',
       family: 'IPv6',
       mac: '00:00:00:00:00:00',
       scopeid: 0,
       internal: true },
      ],
}
```

### 4.2 url 모듈 

# url 
url 파싱 관련 모듈을 제공한다.
###### mark
```
Stability: 2 - Stable
```
###### description
The url module provides utilities for URL resolution and parsing. It can be accessed using:

###### sample
```javascript
const url = require('url');
```

##### url 모듈의 메서드

```parse``` :   url string을 url 객체로 반환

```format``` : url 객체를 문자열로 반환 

```resolve``` : 매개변수를 조합해 완전한 url 문자열을 생성해 반환

--- 
### parse 
url string을 url 객체로 반환한다

###### sample 
```javascript
const url = require('url');

var url_obj = url.parse('https://www.naver.com/');
console.log(url_obj);
```
###### result
```json
Url {
  protocol: 'https:',
  slashes: true,
  auth: null,
  host: 'www.naver.com',
  port: null,
  hostname: 'www.naver.com',
  hash: null,
  search: null,
  query: null,
  pathname: '/',
  path: '/',
  href: 'https://www.naver.com/' }
```

### format
url 객체를 문자열로 반환한다.
```javascript
const url = require('url');

var url_obj = url.parse('https://www.naver.com/');
console.log(url.format(url_obj));
```

###### result
```json
https://www.naver.com/
```

---
### 4.3 Query String 모듈 

# Query String
url 객체의 쿼리와 관련된 모듈 
######  mark
```
Stability: 2 - Stable
```
###### description
The querystring module provides utilities for parsing and formatting URL query strings. It can be accessed using:

```javascript
const querystring = require('querystring');
```
---

##### query string 모듈의 메서드

```stringfy``` : 쿼리 객체를 쿼리 문자열로 반환 

```parse``` : 쿼리 문자열을 객체로 반환


###### sample
``` javascript
const url = require('url');
const query = require('querystring');

//url string return obj
var parseObj = url.parse('http://www.hanbit.co.kr/store/books/look.php?p_code=1234');
console.log(parseObj.query); //p_code=1234
//parse -> return obj 
console.log(query.parse(parseObj.query));
```
###### result
```
p_code=1234
{ p_code: '1234' }
```

---
### 4.4 util 모듈 

# util
node.js 의 보조적인 기능을 모아둔 모듈.
######  mark
```
Stability: 2 - Stable
```
###### description
The util module is primarily designed to support the needs of Node.js' own internal APIs. However, many of the utilities are useful for application and module developers as well. It can be accessed using:

```javascript
const util = require('util');
````


##### util 모듈의 메서드

```format``` :   매개변수로 입력한 문자열을 조합해 반환. console.log() 와 비슷하나 출력을 하지 않는다.

###### sample

```javascript
const util = require('util');
// not print
var data = util.format('%d + %d = %d ',52,273,52+273);
console.log(data);
```

###### result
```
52 + 273 = 325
```
