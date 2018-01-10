var express = require('express');
var router = express.Router();
// define model
var Coin = require('../models/coin');

router.post('/create',function(req,res){
    var coin = new Coin();
    coin.category = req.body.category;
    coin.coinname = req.body.coinname;
    coin.price = req.body.price;
    coin.deposit = req.body.deposit;
    coin.save(function(error){
        if(error){
            console.error(error);
            res.json({result:0});
            return;
        }
        res.json({result:1})
    })
});

router.get('/',function(req,res){
    res.render('coins');
})
router.get('/test',function(req,res){
    Coin.find(function(err,coins){
        if(err) return res.status(500).send({error: 'database failure'});
        res.json(coins);
    })
    res.render('coininput');
});

module.exports = router;