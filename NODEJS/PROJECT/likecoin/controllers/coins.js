// 모델 불러오기 
var Coins = require('../models/coin');

// console.log(Coins);
// 코인 리스트 가져오기
exports.list = function(req, res) {
	// 코인 모든 리스트를 가져온다. 유저 설정 아직 안함.
    Coins.find(function(error, coins) {
        if (error) {
            return res.send(400, {
                message: error
            });
        }
        // Render result
        console.log("get list");
        console.log(coins)
        res.render('coins', {
            title: 'Comments Page',
            coins: coins,
        });
    });
};
// 코인등록하기
exports.create = function(req, res) {
    // 새로운 코인 생성
    console.log("req check");
    console.log(req.body);
    var coins = new Coins(req.body);

    console.log(coins);
    // set category
    coins.category = req.body.category;
    // set name 
    coins.coinname = req.body.coinname;
    // set price
    coins.price = req.body.price;
    // set deposit
    coins.deposit = req.body.deposit;


    // 코인 저장
    coins.save(function(error) {
        if (error) {
            return res.send(400, {
                message: error
            });
        }
        // 리다이렉트 해주기.
        res.redirect('/coins');
    });
};