    const mysql = require('mysql');
    
    const con = mysql.createConnection({
        host: "localhost",
        user:"root",
        password:"",
        database:"glbank"
    });

    con.connect(function(err){
        if(err) throw err;
        console.log("Database connected!");
    });

    module.exports = {
        getLogin(username,password,queryResult)
        {
            const query = "select * from loginclient where login =\'" + username + "\' and password = MD5(\'" + password + "'\);";
        }
    }