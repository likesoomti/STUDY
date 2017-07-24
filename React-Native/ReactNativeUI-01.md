# ReactNativeUI-1.md

https://g6ling.gitbooks.io/react-native-tutorial-korean/
를 보고 공부한 파일 정리하였다.

## 기본 개념 정리 

#### react 와 reactnative 라이브러리를 가져와서 사용한다. 


``` javascript

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  Image,
  View
} from 'react-native';

```

#### react 컴포넌트를 상속한 class를 만들어준다. 밑에는 기본 모형 

``` javascript 
export default class gitbookTest extends Component {
  render() {
    return ();
  }
}

```


#### 실제로 화면에는 return 괄호 안 값만 보낸다. RN의 리턴값은 1개만 보내지기 때문에 하나의 컴포넌트를 가져야하기 때문에 큰 태그로 묶어 하나로 감싸주어야 한다. 

``` javascript 

export default class gitbookTest extends Component {
  render() {
    return (
      // textbox여러개를 하나의 큰 View로 감싸주었다.
      <View style={styles.container}>
            <Text style={styles.welcome}>
              Welcome to React Native!
            </Text>
            <Text style={styles.instructions}>
              To get started, edit index.ios.js
            </Text>
            <Text style={styles.instructions}>
              Press Cmd+R to reload,{'\n'}
              Cmd+D or shake for dev menu
            </Text>
      </View>
    );
  }
}

```
## style
css 랑 비슷하게 설정해준다.

```javascript 

const styles = StyleSheet.create({
  page: {
    flex: 1,
    backgroundColor: '#F5FCFF',
  },
  navBar:{
    flex:1,
    alignItems: 'center',
    backgroundColor:'#c8c8c8'
  },
  body: {
    flex: 10,
    flexDirection: 'row',
    backgroundColor: '#F5FCFF',
  },
  left:{
    flex:1,
    backgroundColor:'#f00'
  },
  right:{
    flex:2,
  },
  rightTop:{
    flex:1,
    backgroundColor:'#00f'
  },
  rightBottom:{
    flex:1,
    backgroundColor:'#ff0'
  }
  
});


```

