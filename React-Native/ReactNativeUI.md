# ReactNativeUI.md

https://g6ling.gitbooks.io/react-native-tutorial-korean/
를 보고 공부한 파일 정리하였다.

## 기본 개념 정리 

```javascript

//react 와 reactnative 라이브러리를 가져와서 사용한다. 

import React, { Component } from 'react';


import {
  AppRegistry,
  StyleSheet,
  Text,
  Image,
  View
} from 'react-native';

//react 컴포넌트를 상속한 class를 만들어준다. 
export default class gitbookTest extends Component {
  render() {
    //실제로 화면에는 return값만 보낸다.RN의 리턴값은 1개만 보내지기 때문에 하나의 컴포넌트를 가져야한다.
    //그래서 view로 감싸준다.
    return (
      //view 안 style 프로퍼티
      // style 부분 입니다. {styles.welcome} 이라는 값을 가지고 있습니다.

      // 리턴 안에는  ex) {()}
      // 컴포넌트 값이기 떄문에,js 값을 넣을 경우에는 {} 안에 넣어 인식 시켜준다. 
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

//gitbookTest프로젝트의 첫 시작을 Test3로 부착한다는 뜻 
AppRegistry.registerComponent('gitbookTest', () => Test3);

```


##
