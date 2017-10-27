# ReactNativeUI-3 final

## 응용. 로그인 뷰 페이지 만들기 결과

```javascript

//만들어보기
class Test3 extends Component{
  render(){
    return(
      <View style={myPage.container}>
        <NavBar/>
        <MyProfile/>
        <ButtonGroup/>
        <Taps/>
      </View>
    )
  }
}
//make mypage view
//tite navbar
class NavBar extends Component{
  render(){
    return(
      <View style={myPage.navBar}>
        <Text style={myPage.text}>
          더보기
        </Text>
      </View>
    )
  }
}
// my profile
class MyProfile extends Component{
  render(){
    return(
      <View style={myPage.myProfile}>
        <View style={myPage.myProfileTop}>
          <View style={myPage.myProfileImage}>
             <Image source={require('./image/avatar.png')} 

             style={{width: 100, height: 100, backgroundColor: 'gray'}} />
          </View>
          <View style={myPage.myProfileInfo}>
            <Text style={myPage.myProfileName}>한수민</Text>
            <Text>숨티/@likesoomti</Text>
            <Text>OPENULL</Text>
          </View>
        </View>


        <View style={myPage.myProfileBottom}>
         
          <View style={myPage.myProfileBtn}>
            <Image source={require('./image/list.png')} style={myPage.buttonIcon}/>
            <Text>내가 쓴 글</Text>
          </View>
          <View style={myPage.myProfileBtn}>
            <Image source={require('./image/chat.png')} style={myPage.buttonIcon}/>
            <Text>댓글 단 글</Text>
          </View>
          <View style={myPage.myProfileBtn}>
            <Image source={require('./image/star.png')} style={myPage.buttonIcon}/>
            <Text>스크랩</Text>
          </View>
        </View>
      </View>
    )
  }
}
class ButtonGroup extends Component{
  render(){
    return(
      <View style={myPage.buttonGroup}>
        <View style={myPage.buttonSquareList} >
          <Button name="내 계정"/>
          <Button name="친구"/>
          <Button name="강의평가"/>
          <Button name="학점계산기"/>
        </View>
        <View style={myPage.buttonSquareList}>
          <Button name="쪽지함"/>
          <Button name="공지사항"/>
          <Button name="도움말"/>
          <Button name="환경설정"/>
        </View>
      </View>
    );
  }
}
class Button extends Component{
  render(){
    return(
      <View style={myPage.miniBtn} >
         <View style={myPage.miniBtnImg} />
         <Text>{this.props.name}</Text>
      </View>
    );
  }
}
class Taps extends Component{
  render(){
    return(
      <View style={myPage.taps}>
        <View style={myPage.buttonSquareList}>
          <Button name="홈"/>
          <Button name="시간표"/>
          <Button name="커뮤니티"/>
          <Button name="모임"/>
          <Button name="더보기"/>
        </View>
      </View>

    )
  }
}
//mypage style container
const myPage = StyleSheet.create({
  container:{
    flex:1,
    flexDirection: 'column',
    justifyContent: 'space-between'
  },
  //navBar
  navBar:{
    //not flex, 고정 위치 사용
    height:60, 
    alignItems: 'center',
    justifyContent:'center',
    backgroundColor:'indianred'
  },
  text:{
    fontSize:20,
    fontWeight: 'bold',
    color:'ghostwhite'
  },
  //myprofile
  myProfile:{
    height:200,
    backgroundColor: 'ghostwhite'
  },
  myProfileTop:{
    height:150,
    flexDirection:'row'
  },
  myProfileBottom:{
    height:50,
    flex:1,
    flexDirection:'row',
    flexWrap:'nowrap',
  },
  myProfileBtn:{
    flex:1,
    //다 해주면 가운데 중복되서 하나하나 border 값 지정
    borderTopWidth:0.5,
    borderBottomWidth:0.7,
    borderRightWidth:0.5,
    flexDirection:'row',
    justifyContent:'center',
    alignItems:'center'
  },
  myProfileImage:{
    flex:1,
    justifyContent:'center',
    alignItems: 'center',
  },
  myProfileInfo:{
    flex:2,
    justifyContent:'center',
    flexDirection: 'column',
  },
  myProfileName:{
    fontSize:20,
    fontWeight: 'bold',
    color:'#000'
  },
  //myProfileButton
  buttonIcon:{
      width: 30,
      height: 30,
      marginRight:5,
  },
  //buttonGroup
  buttonGroup:{
    height: 200,
  },
  buttonSquareList:{
    flex:1,
    flexDirection: 'row',
  },
  miniBtn:{
    flex:1,
    flexDirection:'column',
    height:100,
    borderWidth:0.5,
    justifyContent: 'center',
    alignItems: 'center'
  },
  miniBtnImg:{
    backgroundColor: 'gray',
    width: 50,
    height: 50,
  },
  //List
  taps: {
    height: 100,
  }

});

AppRegistry.registerComponent('gitbookTest', () => Test3);


```

