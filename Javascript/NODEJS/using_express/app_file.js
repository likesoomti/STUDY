var express = require('express');
var app = express();
var bodyParser = require('body-parser')
var fs = require('fs') // 파일 시스템 쓸수 있도록 호출
var multer = require('multer') //파일 업로드 쓸수 있도록 호출 
var _storage = multer.diskStorage({
    destination: function (req, file, cb) {
      cb(null, 'uploads/')
    },
    filename: function (req, file, cb) {
      cb(null, file.originalname)
    }
  })  
var upload = multer({ storage: _storage}) //storage는 객체로 가능 
// var upload = multer({ dest: 'uploads/' }) // 업로드 목적지 .폴더 있어야 한다. 
app.set('views','./view_file');
app.set('view engine', 'jade');


app.use(bodyParser.urlencoded({ extended: false }))
app.use('/static', express.static('uploads'));

app.locals.pretty = true;

app.listen(3000,function(){
    console.log('connected , 3000 port ! ')
})

app.get('/upload',function(req,res){
    res.render('upload')
})
app.post('/upload',upload.single('soomti'),function(req,res){
    console.log(req.file);
    res.send('filename: '+req.file.filename);
})
app.get('/topic/new',function(req,res){


    fs.readdir('data',function(err,files){
        if(err){
            res.status(500).send('Internal Server Error')
        }
        
        res.render('new',{topics:files,title:'Hello Javasrcipt',description:'welcome to js'});  
    })

})
app.post('/topic',function(req,res){
    var title = req.body.title;
    var description = req.body.description;
    //res.send("topic okay "+title+","+description);
    //저장될 파일 경로, 내용 , 콜백함수 
    fs.writeFile('data/'+title,description,function(err){
        if(err){
            console.log(err);
            res.status(500).send('Internal Server Error')
        }
            res.redirect('topic/'+title)
    });
});

app.get(['/topic','/topic/:id'],function(req,res){
    var id = req.params.id

    fs.readdir('data',function(err,files){
        if(err){
            res.status(500).send('Internal Server Error')
        }

        if(id){
            fs.readFile('data/'+id,function(err,data){
                if(err){
                    console.log(err);
                    res.status(500).send('Internal Server Error')
                }
                res.render('view',{topics:files,title:id,description:data});      
            })
        }
        else
            res.render('view',{topics:files,title:'Hello Javasrcipt',description:'welcome to js'});  
    })

});

// 리팩토링 
// app.get('/topic/:id',function(req,res){
//     var id = req.params.id
//     fs.readdir('data',function(err,files){
//         if(err){
//             console.log(err);
//             res.status(500).send('Internal Server Error')
//         }
//         fs.readFile('data/'+id,function(err,data){
//             if(err){
//                 console.log(err);
//                 res.status(500).send('Internal Server Error')
//             }
//             res.render('view',{topics:files,title:id,description:data});      
//         })
//     });
// })


//mysql

//create database o2 CHARACTER SET utf8 collate utf8_general_ci;
// show database;

//  CREATE TABLE `topic` (
// `id` int(11) NOT NULL AUTO_INCREMENT, //자동으로 숫자 하나씩 증가 
// `title` varchar(100) NOT NULL,
// `description` text NOT NULL,
// `author` varchar(30) NOT NULL,
// PRIMARY KEY (id)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

// INSERT INTO topic (title, description, author) VALUES('JavaScript','Computer language for web.', 'egoing');
// INSERT INTO topic (title, description, author) VALUES('NPM','Package manager', 'leezche');
