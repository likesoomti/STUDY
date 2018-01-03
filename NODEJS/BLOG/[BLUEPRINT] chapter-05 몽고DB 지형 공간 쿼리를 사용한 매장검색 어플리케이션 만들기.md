# chapter-05 몽고DB 지형 공간 쿼리를 사용한 매장검색 어플리케이션 만들기

## 목표

- geolocation 데이터 저장
- express 프레임워크 
- google map API 
- javascript

를 통한 데이터 지도 표시 어플 만들 것이다.

javascript library 가 아닌 backend javascript만 사용해서 MVC 어플리케이션을 만든다. 

### 5.2 기초 어플리케이션 생성

express-generator  사용해서 만들기 

#### 1. 폴더 만들기 

```maps-api``` 폴더를 만든다

#### 2. yo express 사용하기

```
$ yo express
질문에 대한 답 입력 
ㄴ N
ㄴ MVC
ㄴ Swig
ㄴ None
ㄴ MongoDB
ㄴ Gulp
```

### 5.3 기본 구조 리팩터링
확장성을 높이기 위해 몇가지 폴더를 추가한다. 

1. app/views 폴더에 pages 폴더 추가
2. app/views 폴더에 partials 폴더 추가
3. views 폴더 루트 파일 pages 폴더로 이동
4. pages 폴더에 있는 확장자 .html로 변경 

#### 5.3.1 푸터 / 헤더 만들기

##### app/view/partials 에 footer.html 추가 
```html
<footer class="page-footer teal darken-1">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">Some Text Example</h5>
                <p class="grey-text text-lighten-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do
                    eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud
                    exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in
                    reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Sample Links</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Sample Links</h5>
                <ul>
                    <li><a class="white-text" href="#!">Link 1</a></li>
                    <li><a class="white-text" href="#!">Link 2</a></li>
                    <li><a class="white-text" href="#!">Link 3</a></li>
                    <li><a class="white-text" href="#!">Link 4</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            MVC Express App for: <a class="white-text text-darken-2" href="#">Node.js 6 Blueprints Book</a>
        </div>
    </div>
</footer>

<!-- Live reload for development -->
{% if ENV_DEVELOPMENT %}
<script src="http://localhost:35729/livereload.js"></script>
{% endif %}


<!-- Init Rsponsive Sidenav Menu  -->
<script>
    (function ($) {
        $(function () {
            $('.button-collapse').sideNav();
        });
    })(jQuery);
</script>
```

##### app/view/partials 에 head.html 추가 

```html
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width">
    <title>{{ title }}</title>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- Import Google Material font and icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/css/materialize.min.css">
    <link rel="stylesheet" href="/css/style.css">

    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.6/js/materialize.min.js"></script>
    <!-- Google Maps API to track location  -->
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDM9YKBj-iKDcarbsZJ5t0MsJgSLG1QpZc"></script>
</head>
```

#### 5.3.2 html 확장자로 swig 템플릿 설정

app.engine 에서 html 확장자를 인식할 수 있도록 설정해야 한다. 

##### app/coinfig

express.js 
```javascript
  app.engine('html', swig.renderFile);
  if (env == 'development') {
    app.set('view cache', false);
    swig.setDefaults({ cache: false });
  }
  app.set('views', config.root + '/app/views/pages');
  app.set('view engine', 'html');

```

#### 5.3.4 app template 만들기

##### app/views/pages

index.html 
```html 

{% extends 'layout.html' %}

{% block content %}
<div id="map" style="height: 300px"></div>
<div class="section">
    <div class="container">
        <br>
        <h1 class="header center teal-text">{{ title }}</h1>
        <div class="row center">
            <h5 class="header col s12 light">Welcome to {{ title }}</h5>
        </div>
        <div class="row center">
            <a href="locations/add" id="download-button" class="btn-large waves-effect waves-light teal">Add your location</a>
        </div>
        <br><br>
    </div>
</div>
<!-- Tracking current user position -->
<script src="/js/getCurrentPosition.js"></script>
{% endblock %}
```

layout.html
```html
<!doctype html>
<html lang="en">
{% include "../partials/head.html" %}
<body>
<nav class="teal" role="navigation">
    <div class="nav-wrapper container"><a id="logo-container" href="/" class="brand-logo">Logo</a>
        <ul class="right hide-on-med-and-down">
            <li><a href="/locations">Locations</a></li>
            <li><a href="/locations/add">Add Location</a></li>
            <li><a href="/stores">Stores</a></li>
        </ul>

        <ul id="nav-mobile" class="side-nav" style="transform: translateX(-100%);">
            <li><a href="/locations">Locations</a></li>
            <li><a href="/locations/add">Add Location</a></li>
            <li><a href="/">Stores</a></li>
        </ul>
        <a href="#" data-activates="nav-mobile" class="button-collapse"><i class="material-icons">menu</i></a>
    </div>
</nav>
{% block content %}{% endblock %}
<!-- Footer -->
{% include "../partials/footer.html" %}
</body>
</html>
```

error.html
```html
{% extends 'layout.html' %}

{% block content %}
<div class="section">
    <div class="container">
        <br>
        <h1 class="header center teal-text">{{ message }}</h1>
        <div class="row center">
            <h3 class="header col s12 light">{{ error.status }}</h3>
        </div>
        <div class="row center">
            <pre>{{ error.stack }}</pre>
        </div>
        <br><br>
    </div>
</div>
{% endblock %}
```

#### 5.4 geolocation html5 api 사용하기

html5 api를 사용해 사용자의 위치를 파악할 수 있다.

##### 1. public/js

getCurrentPosition.js

```javascript
function getCurrentPosition() {

    // Check boreswer/navigator support
    if (navigator.geolocation) {
        var options = {
            enableHighAccuracy : true,
            timeout : Infinity,
            maximumAge : 0
        };

        navigator.geolocation.watchPosition(getUserPosition, trackError, options);

    } else {
        alert('Ops; Geolocation is not supported');
    }
	// Get user position and place a icon on map
    function getUserPosition(position) {
		// Check longitude and latitude
		console.log(position.coords.latitude);
		console.log(position.coords.longitude);

		// Create the user' coordinates
        var googlePos = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
        var mapOptions = {
            zoom : 12,
            center : googlePos,
            mapTypeId : google.maps.MapTypeId.ROADMAP
        };

        // Set a variable to get the HTML div
        var mapObj = document.getElementById('map');
        // Create the map and passing: map div and map options
        var googleMap = new google.maps.Map(mapObj, mapOptions);
        // Setup a marker on map with user' location
        var markerOption = {
            map : googleMap,
            position : googlePos,
            animation : google.maps.Animation.DROP
        };

		// Create a instance with marker on map
        var googleMarker = new google.maps.Marker(markerOption);
        // Get the user's complete address information using the Geocoder Google API
		var geocoder = new google.maps.Geocoder();
        geocoder.geocode({
            'latLng' : googlePos
        }, function(results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                if (results[1]) {
                    var popOpts = {
                        content : results[1].formatted_address,
                        position : googlePos
                    };
                    // Setup an info window with user information
                    var popup = new google.maps.InfoWindow(popOpts);
                    google.maps.event.addListener(googleMarker, 'click', function() {
                        popup.open(googleMap);
                    });
                } else {
                    alert('No results found');
                }
            } else {
                alert('Uhh, failed: ' + status);
            }
        });
    }
    // Setup a error function
    function trackError(error) {
        var err = document.getElementById('map');
        switch(error.code) {
            case error.PERMISSION_DENIED:
            err.innerHTML = "User denied Geolocation.";
            break;
            case error.POSITION_UNAVAILABLE:
            err.innerHTML = "Information is unavailable.";
            break;
            case error.TIMEOUT:
            err.innerHTML = "Location timed out.";
            break;
            case error.UNKNOWN_ERROR:
            err.innerHTML = "An unknown error.";
            break;
        }
    }
}

getCurrentPosition();
```

하고 `$ node app.js ` 명령어를 친 결과 백지 상태가 나와, console 을 확인해보니. chap04에서 나왔던 liveload connect error 메세지가 나온다. 그래서 gulp 를 설치해 주었다


```
sudo npm install gulp
```

```
npm install gulp-watch
```

### 5.5 애플리케이션 컨트롤러 만들기

##### app/controllers
locations.js
```javascript
var express = require('express'),
router = express.Router(),
mongoose = require('mongoose'),
Location = mongoose.model('Location');

module.exports = function (app) {
    app.use('/', router);
};

router.get('/locations', function (req, res, next) {

    Location.find(function (err, item) {
        if (err) return next(err);
        res.render('locations', {
            title: 'Locations',
            location: item,
            lat: -23.54312,
            long: -46.642748
        });

        //res.status(200).json(stores);
    });
});

router.get('/locations/add', function (req, res, next) {

    res.render('add-location', {
        title: 'Insert Locations'
    });
});

router.post('/locations', function (req, res, next) {
    // Fill loc object with request body
    var loc = {
        title: req.body.title,
        coordinates: [req.body.long, req.body.lat]
    };

    var locations = new Location(loc);

    // save the data received
    locations.save(function(error, item) {
        if (error) {
            return res.status(400).send({
                message: error
            });
        }

        //res.json({message: 'Success', obj: item});
        res.render('add-location', {
            message: 'Upload with Success',
            obj: item
        });

    });

});

router.post('/nearme', function (req, res, next) {

    // Setup limit
    var limit = req.body.limit || 10;

    // Default max distance to 10 kilometers
    var maxDistance = req.body.distance || 10;

    // Setup coords Object = [ <longitude> , <latitude> ]
    var coords = [];
    // Create the array
    coords[0] = req.body.longitude;
    coords[1] = req.body.latitude;

    // find a location
    Location.find({
        'coordinates': {
            $near: {
                $geometry: {
                    type: "Point",
                    coordinates: coords
                },
                // distance to radians
                $maxDistance: maxDistance * 1609.34, spherical: true
            }
        }
    }).limit(limit).exec(function (err, stores) {
        if (err) {
            return res.status(500).json(err);
        }

        //res.status(200).json(stores);

        res.render('locations', {
            title: 'Locations',
            location: stores,
            lat: -23.54312,
            long: -46.642748
        });
    });
});
```

### 5.6 모델 생성하기

##### app/model
location.js

```javascript
// Example model
var mongoose = require('mongoose'),
  Schema = mongoose.Schema;

var LocationSchema = new Schema({
    title: String,
    coordinates: {
      type: [Number],
      index: '2dsphere'
    },
    created: {
        type: Date,
        default: Date.now
    }
});

mongoose.model('Location', LocationSchema);
```

### 5.7 뷰 템플릿 만들기 

##### app/views/pages

locations.html

```html
{% extends 'layout.html' %}

{% block content %}
<div class="section">
    <div class="container">
        <br><br>
        <h1 class="header center teal-text">{{ title }}</h1>
        <div class="row center">
            <h5 class="header col s12 light">Welcome to {{ title }}</h5>
        </div>
        <div class="row">
            <div class="col s3">
                <div id="store-list" class="collection">
                    {% for item in location %}
                    <a href="#" class="pan-to-marker collection-item" data-marker-index="{{loop.index }}">{{item.title}}</a>
                    {% endfor %}
                </div>
            </div>
            <div class="col s9">
                <form action="/nearme" method="POST">
                    <div class="row">
                        <!-- Map -->
                        <div class="col s12" id="map" style="height: 600px; width: 100%; margin-bottom: 20px"></div>
                        <br>
                        <h5 class="grey-text center">
                            Find a store near by you
                        </h5>
                        <br>
                        <div class="input-field col s5">
                            <input placeholder="Insert Longitude" name="longitude" id="longitude" type="text" class="validate" value="{{long}}">
                            <label for="longitude">Longitude</label>
                        </div>
                        <div class="input-field col s5">
                            <input placeholder="Insert latitude" name="latitude" id="latitude" type="text" class="validate" value="{{lat}}">
                            <label for="latitude">Latitude</label>
                        </div>
                        <div class="input-field col s2">
                              <select class="browser-default" name="distance" id="distance">
                                <option value="" disabled selected>Distance</option>
                                <option value="2">2 Km</option>
                                <option value="3">3 km</option>
                                <option value="9">9 km</option>
                              </select>
                        </div>
                    </div>
                    <div class="row">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit</button>
                    </div>
                </form>
                <br>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var loadMap = function() {
        // Center map with current lat and long (Simulated with fixed point for this example)
        var googlePos = new google.maps.LatLng({{ lat }} , {{ long }});
        // Setup map options
        var mapOptions = {
            zoom : 12,
            center : googlePos,
            mapTypeId : google.maps.MapTypeId.ROADMAP
        };
        // Set a variable to get the HTML div
        var mapObj = document.getElementById('map');
        var googleMap = new google.maps.Map(mapObj, mapOptions);
        // Create markers array to hold all markers on map
        var markers = [];
        // Using the Swig loop to get all data from location variable
        {% for item in location %}
            // Setup a lat long object
            var latLng = new google.maps.LatLng({{ item.coordinates[1] }}, {{ item.coordinates[0] }});
            // Create a marker
            var marker = new google.maps.Marker({
                map : googleMap,
                position: latLng,
                animation : google.maps.Animation.DROP
            });
            markers.push(marker);
            // Setup the info window
            var infowindow = new google.maps.InfoWindow();
            // Add an event listener to click on each marker and show an info window
            google.maps.event.addListener(marker, 'click', function () {
                // using the tittle from the Swig looping
                infowindow.setContent('<p>' + " {{ item.title }} " + '</p>');
                infowindow.open(googleMap, this);
            });
        {% endfor %}
        // get all the pan-to-marker class
        var els = document.querySelectorAll(".pan-to-marker");
        // looping over all list elements
        for (var i = 0, len = els.length; i < len; i++) {
            els[i].addEventListener("click", function(e){
                e.preventDefault();
                // Use -1 for index because loop.index from swig starts on 1
                var attr = this.getAttribute('data-marker-index') -1;
                // get longitude and latitude of the marker
                var latitude = markers[attr].getPosition().lat();
                var longitude = markers[attr].getPosition().lng();
                console.log(latitude, longitude );
                // Center map and apply zoom
                googleMap.setCenter({lat: latitude, lng: longitude});
                googleMap.setZoom(18);
            });
        }
    };
    // load the map function
    window.onload = loadMap;
</script>

{% endblock %}
```

add-location.html
```html

{% extends 'layout.html' %}

{% block content %}
<div class="section">
    <div class="container">
        <br><br>
        <h1 class="header center teal-text">{{ title }}</h1>
        <div class="row center">
            <h5 class="header col s12 light">Welcome to {{ title }}</h5>
        </div>
        <div class="row">
            <div class="col s3">
                <div id="store-list" class="collection">
                    {% for item in location %}
                    <a href="#" class="pan-to-marker collection-item" data-marker-index="{{loop.index }}">{{item.title}}</a>
                    {% endfor %}
                </div>
            </div>
            <div class="col s9">
                <form action="/nearme" method="POST">
                    <div class="row">
                        <!-- Map -->
                        <div class="col s12" id="map" style="height: 600px; width: 100%; margin-bottom: 20px"></div>
                        <br>
                        <h5 class="grey-text center">
                            Find a store near by you
                        </h5>
                        <br>
                        <div class="input-field col s5">
                            <input placeholder="Insert Longitude" name="longitude" id="longitude" type="text" class="validate" value="{{long}}">
                            <label for="longitude">Longitude</label>
                        </div>
                        <div class="input-field col s5">
                            <input placeholder="Insert latitude" name="latitude" id="latitude" type="text" class="validate" value="{{lat}}">
                            <label for="latitude">Latitude</label>
                        </div>
                        <div class="input-field col s2">
                              <select class="browser-default" name="distance" id="distance">
                                <option value="" disabled selected>Distance</option>
                                <option value="2">2 Km</option>
                                <option value="3">3 km</option>
                                <option value="9">9 km</option>
                              </select>
                        </div>
                    </div>
                    <div class="row">
                        <button class="btn waves-effect waves-light" type="submit" name="action">Submit</button>
                    </div>
                </form>
                <br>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    var loadMap = function() {
        // Center map with current lat and long (Simulated with fixed point for this example)
        var googlePos = new google.maps.LatLng({{ lat }} , {{ long }});
        // Setup map options
        var mapOptions = {
            zoom : 12,
            center : googlePos,
            mapTypeId : google.maps.MapTypeId.ROADMAP
        };
        // Set a variable to get the HTML div
        var mapObj = document.getElementById('map');
        var googleMap = new google.maps.Map(mapObj, mapOptions);
        // Create markers array to hold all markers on map
        var markers = [];
        // Using the Swig loop to get all data from location variable
        {% for item in location %}
            // Setup a lat long object
            var latLng = new google.maps.LatLng({{ item.coordinates[1] }}, {{ item.coordinates[0] }});
            // Create a marker
            var marker = new google.maps.Marker({
                map : googleMap,
                position: latLng,
                animation : google.maps.Animation.DROP
            });
            markers.push(marker);
            // Setup the info window
            var infowindow = new google.maps.InfoWindow();
            // Add an event listener to click on each marker and show an info window
            google.maps.event.addListener(marker, 'click', function () {
                // using the tittle from the Swig looping
                infowindow.setContent('<p>' + " {{ item.title }} " + '</p>');
                infowindow.open(googleMap, this);
            });
        {% endfor %}
        // get all the pan-to-marker class
        var els = document.querySelectorAll(".pan-to-marker");
        // looping over all list elements
        for (var i = 0, len = els.length; i < len; i++) {
            els[i].addEventListener("click", function(e){
                e.preventDefault();
                // Use -1 for index because loop.index from swig starts on 1
                var attr = this.getAttribute('data-marker-index') -1;
                // get longitude and latitude of the marker
                var latitude = markers[attr].getPosition().lat();
                var longitude = markers[attr].getPosition().lng();
                console.log(latitude, longitude );
                // Center map and apply zoom
                googleMap.setCenter({lat: latitude, lng: longitude});
                googleMap.setZoom(18);
            });
        }
    };
    // load the map function
    window.onload = loadMap;
</script>

{% endblock %}
```

### 5.8 몽고 DB에 위치 추가하기 

```

$ gulp

```

#### error
지도가 로드되지 않음. 

콘솔 확인 하자 키가 만료되었다는 오류가 뜬다.
```irb
Google Maps API error: ExpiredKeyMapError 
https://developers.google.com/maps/documentation/javascript/error-messages#expired-key-map-error
_.Pb @ js?key=AIzaSyDM9YKBj-iKDcarbsZJ5t0MsJgSLG1QpZc:38
```

##### solve

https://console.developers.google.com

에서 프로젝트 생성 후 키 교체 