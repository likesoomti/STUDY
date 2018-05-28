# build.gradle project module 차이 .md

## Problem

빌드 그래들은 라이브러리를 저장하는 파일로 알고있는데, 프로젝트와 모듈의 차이가 뭘까 궁금해서 검색해봤다

## Gradle

라이브러리를  자동으로 설치해주거나, 관리해준다.  

각 빌드 구성에서는 자체적인 코드 및 리소스 세트를 정의할 수 있으며, 모든 앱 버전에 공통되는 부분을 재사용하기도 합니다. `Gradle`용 `Android` 플러그인과 빌드 툴킷을 함께 사용하면, `Android` 애플리케이션을 빌드하고 테스트하는 데 필요한 특정 프로세스와 구성 가능 설정을 제공할 수 있습니다.



## build.gradle project 파일 

`build.gradle` (Project : MyApplication) 파일은 프로젝트의 루트 폴더에 있으며 

해당 구성 설정은 프로젝트의 모든 모듈에 적용됩니다.

모듈은 더 큰 프로젝트의 고립 된 부분입니다. 다중 모듈 프로젝트에서 이러한 모듈은 자체 작업을 가지고 있지만 함께 작업하여 전체 프로젝트를 구성합니다. 대부분의 Android 프로젝트에는 하나의 모듈 인 app 모듈 만 있습니다.

## build.gradle project 파일 

여기에 `build.gradle` (Module : app) 파일이 `app` 폴더에 있습니다. 빌드 설정은 앱 모듈에만 적용됩니다. 다른 모듈이 있다면 자체 `build.gradle` 파일도 `build.gradle` 습니다. 예를 [들어](https://github.com/suragch/mongol-library) 라이브러리 모듈, 데모 응용 프로그램 모듈 및 테스트 용으로 사용할 다른 응용 프로그램 모듈의 세 가지 모듈로 라이브러리 프로젝트를 만들었습니다. 각각의 파일에는 내가 수정할 수있는 `build.gradle` 파일이 있습니다.
