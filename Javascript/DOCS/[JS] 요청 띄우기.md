# HTML Javascript Notify

```javascript
function notifyMe() {
  // Let's check if the browser supports notifications
  if (!("Notification" in window)) {
    alert("This browser does not support desktop notification");
  }

  // Let's check whether notification permissions have already been granted
  else if (Notification.permission === "granted") {
    // If it's okay let's create a notification
    var msg={
        body: "예양 ㄱ요청이 들어왓습니다"
    }
    var notification = new Notification("예약 요청이 들어왔습니다", msg);
  }

  // Otherwise, we need to ask the user for permission
  else if (Notification.permission !== 'denied') {
    Notification.requestPermission(function (permission) {
      // If the user accepts, let's create a notification
      if (permission === "granted") {
        var notification = new Notification("Hi there!");
      }
    });
  }

  // At last, if the user has denied notifications, and you 
  // want to be respectful there is no need to bother them any more.
} Notification.requestPermission().then(function(result) {
  console.log(result,"hihi");
});

function spawnNotification(theBody,theIcon,theTitle) {
  var options = {
      body: theBody,
      icon: theIcon
  }
  console.log(theBody,theIcon,theTitle);
  var n = new Notification(theTitle,options);
}

```