

const express = require('express');
const config = require('./config/config');

const app = express();

module.exports = require('./config/express')(app, config);
// cloudinary 환경 가져오기
require('./config/env')(app);
app.listen(config.port, () => {
  console.log(config.port)
  console.log('Express server listening on port ' + config.port);
  console.log(config.port);
});

