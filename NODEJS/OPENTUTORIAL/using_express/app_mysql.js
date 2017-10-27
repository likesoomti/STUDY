var express = require('express');
var app = express();
var bodyParser = require('body-parser')
var fs = require('fs') // 파일 시스템 쓸수 있도록 호출
//db 연결
var mysql      = require('mysql');
var conn = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'qwer1234',
  database : 'o2'
});
conn.connect();


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
app.set('views','./view_mysql');
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
app.get('/topic/add',function(req,res){

    fs.readdir('data',function(err,files){
  
        
        res.render('add',{topics:files,title:'Hello Javasrcipt',description:'welcome to js'});  
    })

})
app.post('/topic/add',function(req,res){
    var title = req.body.title;
    var description = req.body.description;
    var author = req.body.author;

    var sql = 'insert into topic (title,description,author) values(?,?,?)'
    var params = [title,description,author];

    conn.query(sql,params,function(err,results,fields){
        if(err){
            console.log(err);
            res.status(500).send('Internal server error')
        }
        res.redirect('/topic/'+results.insertId);

    });
});
app.get('/topic/:id/edit',function(req,res){
    
        var sql = 'select id,title from topic'
        conn.query(sql,function(err,topics,fields){
            var id = req.params.id;
            if(id){
                var sql = 'select * from topic where id =?'
                conn.query(sql,[id],function(err,rows,fields){
                    if(err){
                        console.log(err);
                        res.status(500).send('Internal Server Error');
                    }
                    else{
                        res.render('edit',{topics:topics,topic:rows[0]});
                    }
                })
            }
            else{
                res.status(500).send('server error. there is no id ')
            }
           });
    });

app.post('/topic/:id/delete',function(req,res){
    
        var sql = 'delete from topic where id=?'
        conn.query(sql,[id],function(err,topics,fields){
            if(err){
                res.status(500).send('Internal server error');
                console.log(err);
            }
            else{
                res.redirect('/topic');
            }
        });
});
app.post('/topic/:id/edit',function(req,res){
    var title = req.body.title;
    var description = req.body.description;
    var author = req.body.author;
    var id = req.params.id;
    
    var sql = 'update topic set title=?,description=?,author=? where id=?'
    conn.query(sql,[title,description,author,id],function(err,topics,fields){
        if(err){
            res.status(500).send('Internal server error');
            console.log(err);
        }
        else{
            res.redirect('/topic/'+id);
        }
    });
});
    
app.get(['/topic','/topic/:id'],function(req,res){

    var sql = 'select id,title from topic'
    conn.query(sql,function(err,topics,fields){
        var id = req.params.id;
        if(id){
            var sql = 'select * from topic where id =?'
            conn.query(sql,[id],function(err,rows,fields){
                if(err){
                    console.log(err);
                    res.status(500).send('Internal Server Error');
                }
                else{
                    res.render('view',{topics:topics,topic:rows[0]});
                }
            })
        }
        else{
            res.render('view',{topics: topics})
        }
       });
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
