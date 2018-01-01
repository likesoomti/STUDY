// get Env variable / cloudinary
module.exports = function(app, configEnv) {
    
        var dotenv = require('dotenv');
        dotenv.load();
        var cloudinary = require('cloudinary').v2;

        cloudinary.config({ 
          cloud_name: process.env.cloud_name, 
          api_key: process.env.api_key, 
          api_secret: process.env.api_secret
        });

        console.log(process.env.cloud_name);
        console.log(process.env.api_key);
        console.log(process.env.api_secret)

        if ( typeof(process.env.CLOUDINARY_URL) == 'undefined' ){
          console.log('Cloudinary config file is not defined');
          console.log('Setup CLOUDINARY_URL or use dotenv mdule file')
        } else {
          console.log('Cloudinary config, successfully used:');
          console.log(cloudinary.config());
        }
    }