# ReactNativeUI-2

## 컴포넌트 분리하기

#### 여러 스타일이 담긴 뷰를 하나의 컴포넌트로 묶어 가지고 다닐 수 있다

``` javascript

class NavBar2 extends Component{
  render(){
    return(
    <View style={styles.navBar}>
      <Text>NavBar</Text>
    </View>
    )
  }
}
class Body2 extends Component{
  render(){
    return(
    <View style={styles.body}>      
      <View style={styles.left}/>
      <View style={styles.right}>
        <View style={styles.rightTop}/>
        <View style={styles.rightBottom}/>
      </View>
    </View>
  )}
}
```

#### 상위 만든 컴포넌트는 커스텀 태그처럼 사용된다. 

``` javascript
class test2 extends Component {
  render() {
    return (
      <View style={styles.page}>
        <NavBar2/>
        <Body2/>
      </View>
    )
  }
}

```


