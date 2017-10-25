<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ADMIN CHAT</title>
<link rel="manifest" href="/manifest.json">

</head>
<body>

<div id="chat" class="chat">

</div>
<h1 id ="plz"></h1>
<script src="https://www.gstatic.com/firebasejs/4.0.0/firebase.js" ></script>
<script src="https://www.gstatic.com/firebasejs/3.6.2/firebase-app.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.6.2/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.6.2/firebase-database.js"></script>
<script src="https://www.gstatic.com/firebasejs/3.6.2/firebase-messaging.js"></script>

<script>
  //nitialize Firebase
  var config = {
    apiKey: "AIzaSyDl20iNI4JpWYIP7__FoXrwktzoFyuTaxY",
    authDomain: "hanplace-164407.firebaseapp.com",
    databaseURL: "https://hanplace-164407.firebaseio.com",
    projectId: "hanplace-164407",
    storageBucket: "hanplace-164407.appspot.com",
    messagingSenderId: "187624337374"
    
  };
  firebase.initializeApp(config);
  var userName = document.getElementById('plz');
  var dbref = firebase.database().ref('message');
//  dbref.on('value',snap => userName.innerText = snap.val());
  dbref.on('value',(snap) => {
	    snap.forEach((childSnap) => {
	//        console.log(childSnap.key);
			 console.log(childSnap.val().userName);
 			 console.log(childSnap.val().message);
			  //childSnap.key
		      $("#chat").append(childSnap.val().userName);
			  $("#chat").append('<br>');
			  $("#chat").append(childSnap.val().message);
			  $("#chat").append('<br>');
			  $("#chat").append('<hr>');
			  
	    });
	    
	});
</script>

</body>
</html>
