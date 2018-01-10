var mongoose = require('mongoose');
var Schema = mongoose.Schema;

var coinSchema = new Schema({
    category: String,
    coinname: String,
    price: Number,
    deposit: Number,

    category: {
        type: String,
        default: ' ',
        trim: true
    },
    coinname: {
        type: String,
        trim: true,
        default: ' '
    },
    price: {
        type: Number,
        trim: true,
        default: 0
    },
    deposit: {
        type: Number,
        trim: true,
        default: 0
    }

});

// 코인 모델 등록 ~!!
module.exports = mongoose.model('coin', coinSchema);

