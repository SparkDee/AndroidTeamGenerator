<?php

header('Content-Type: application/json');

//require_once "db_config.php";
include "db_config.php";

global $db;

//get email and password from android xml form
if($_SERVER['REQUEST_METHOD']=='POST'){

$email=$_POST['email'];
$password=$_POST['password'];


//sql query
$sql="SELECT * FROM final_app_player  WHERE email='$email'  ";

//query the db using connection file and sql query
$response=mysqli_query($db,$sql);


//return an array name login
$result=array();
$result['login']=array();


//if number of rows is equal to one return all info as a JSON array
if(mysqli_num_rows($response)===1){
    
    $row=mysqli_fetch_assoc($response);

    
        if(password_verify($password,$row['password'])){


        $index['name']=$row['name'];
        $index['email']=$row['email'];
        //$index['team_id']=$row['team_id'];  
        $index['player_id']=$row['player_id'];
        
        
       

        array_push($result['login'],$index);

        

        $result['success']="1";
        $result['message']="success";
        echo json_encode($result);

        mysqli_close($db);



    }else{
        $result['success']="0";
        $result['message']="error";
        echo json_encode($result);

        mysqli_close($db);

    }

}
}




?>