var mysql      = require('mysql');
var conn = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'qwer1234',
  database : 'o2'
});

conn.connect();

var sql = 'select * from topic;'
var sql2 = 'INSERT INTO topic (title, description, author) VALUES("nodejs","serverside javascript", "egoing");'
var sql3 = 'INSERT INTO topic (title, description, author) VALUES("?","?", "?");'
var params = ['1','2','3'];
/*
// cr 
conn.query(sql3, params , function (err, rows, fields) {
 
  if(err){
      console.log(err);
  }
  else{
//      for(var i = 0 ; i < rows.length ; i ++){
//          console.log(rows[i].author);
//          console.log(rows[i].description);
//});
        console.log(rows.insertId);}

  });
*/
/*
//update
var sql4 = 'update topic set title=?,description=? where id=?'
var params4=['title','des',1];
conn.query(sql4, params4, function (err, rows, fields) {
    
     if(err){
         console.log(err);
     }
     else{
        console.log(rows);
    }

});
*/
var sql5 = 'delete from topic where id=?'
var params5=[1];
conn.query(sql5, params5, function (err, rows, fields) {
    
     if(err){
         console.log(err);
     }
     else{
        console.log(rows);
    }

});

conn.end();