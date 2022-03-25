<?php

header('Content-Type: application/json');

//require_once "db_config.php";
include "db_config.php";

global $db;

if($_SERVER['REQUEST_METHOD']=='POST'){

$email=$_POST['email'];
$password=$_POST['password'];



$sql="SELECT * FROM final_app_admin  WHERE email='$email'  ";

$response=mysqli_query($db,$sql);

$result=array();

$result['login']=array();

if(mysqli_num_rows($response)===1){
    
    $row=mysqli_fetch_assoc($response);

    
        if(password_verify($password,$row['password'])){

        
        $index['name']=$row['name'];
        $index['email']=$row['email'];
        $index['admin_id']=$row['admin_id'];
        //$index['team_id']=$row['team_id'];  
        
        
        
       

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