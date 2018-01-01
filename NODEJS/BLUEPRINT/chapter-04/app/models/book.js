var Schema = require('jugglingdb').Schema;
var schema = new Schema('mongodb', {url: 'mongodb://localhost/myapp'});
console.log(schema);

/*
Schema {
  name: 'mongodb',
  settings:
   { url: 'mongodb://localhost/myapp',
     host: 'localhost',
     port: 27017,
     database: 'myapp',
     username: null,
     password: null,
     safe: false,
     w: 0,
     j: false },
  connected: false,
  connecting: true,
  models: {},
  definitions: {},
  adapter: MongoDB { name: 'mongodb', _models: {}, collections: {} },
  ObjectID:
   { [Function: ObjectID]
     index: 5461557,
     createPk: [Function: createPk],
     createFromTime: [Function: createFromTime],
     createFromHexString: [Function: createFromHexString],
     isValid: [Function: isValid],
     ObjectID: [Circular],
     ObjectId: [Circular] },
  connect: [Function],
  callAdapter: [Function] }
  */

console.log(schema.name);
console.log(schema.connect);
console.log(schema.connect());

var Picture = schema.define('Picture', {
  title : { type: String, length: 255 },
  description: {type: Schema.Text},
  category: {type: String, length: 255 },
  image : { type: JSON}
});

module.exports = schema;