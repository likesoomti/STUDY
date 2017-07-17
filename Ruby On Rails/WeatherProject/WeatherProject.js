import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  TextInput,
  View
} from 'react-native';

//export default 
class WeatherProject extends Component {
 //생성자를 통해 state를 설정한다. 
  constructor(props){
      super(props);
      this.state = { 
        zip: '',
        forecast: 
        {
          main: 'Clouds',
          description: 'few clouds',
          temp: 45.7
        }
      };
  }
  _handleTextChange = (event) => {
      console.log(event.nativeEvent.text);
      this.setState({
          zip: event.nativeEvent.text
      });
  }
  render(){
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          You input {this.state.zip} .
        </Text>
        <TextInput
            style={styles.input}
            returnKeyType='go'
            onSubmitEditing={(event) => this._handleTextChange(event)} />
      </View>
    );
  
  };
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
  input: {
      fontSize: 20,
      borderWidth: 2,
      height: 40
  }
});

export default WeatherProject;
