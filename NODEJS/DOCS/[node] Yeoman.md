# Yeoman

### yeoman 이란?
`Yeoman` 은 프로젝트에 필요한 디렉토리 및 파일을 만들어주는 커맨드라인 인터페이스입니다. `Yeoman`을 사용하면 한번에 필요한 폴더와 파일들을 만들어 줍니다. `Yeoman은` `Yeoman-generator`를 통해 진행됩니다.

### yeoman vs express-generator/

`Yeoman`은 프레임워크가 아니라, 프로젝트 구조 생성을 위한 하나의 워크플로우(도구) 입니다.`Yeoman`은 Node 웹 프레임워크인 Express.js의 구조를 만들 뿐만 아니라, Wordpress 구조 또한 제공하고, 이에 대한 확장이 가능합니다. 

`express-generator`는 Express 웹 프레임워크의 구조를 만들어주는 Express Application Generator Tool입니다. 


#### 설치
```
$ npm install -g yo
```

#### 실행

```
$ yo

? 'Allo soom! What would you like to do? (Use arrow keys)
  Run a generator
  ──────────────
❯ Install a generator
  Find some help
  Get me out of here!
  ──────────────

  ```
  ##### 검색 결과
  검색 결과 angular-mongodb-login같은 다른 스택을 사용하는 프로젝트의 툴을 제공합니다
  ```
  ? Search npm for generators: mon
? Here's what I found. Official generator → ෴
  Install one? (Use arrow keys)
❯ commonjs ෴  Generate a CommonJS module
  alfresco-common Common code for Alfresco yeoman generator
  almond Yeoman generator that scaffolds out a front-end web app with AMD capabilities.
  angular-mongodb-login Extends Yeoman generator for AngularJS and includes a sub-generator to scaffold user l
ogin page backed by MongoDB. NOTE: this is just an idea for now; if you like/need it star the project and I'll
 be happy to put more hours into it.
  angular-nmi Yeoman generator for various AngularJS resources to encourage a common style within a project.
  api-express-docker A minimalist express template. Babel / ESLint / nodemon, all of this inside a Docker imag
e
  aurelia-auth-go A go backend (using common DDD code) for the aurelia-auth-ui generator
(Move up and down to reveal more choices)
```