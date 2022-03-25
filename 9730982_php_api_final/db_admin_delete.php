<?php

require_once "db_function.php";

$response=array();

if($_SERVER['REQUEST_METHOD']=='POST'){

    $admin_id=$_POST['admin_id'];

    if(isset($_POST['admin_id'])){

        $result=deleteAdminData($admin_id);

        //if $result var is true

        if($result){
            $response['success']=1;
            $response['message']="Data deleted successfully";
            //echo in json
            echo json_encode($response);
        }else{
            $response['success']=0;
            $response['message']="Problem deleting";
            echo json_encode($response);
        }
        
    
    }

}








?>