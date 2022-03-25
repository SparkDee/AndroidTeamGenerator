<?php

require_once "db_function.php";

//set response as array
$response=array();

if($_SERVER['REQUEST_METHOD']=='POST'){


    //define vars to update
    $admin_id=$_POST['admin_id'];
    $name=$_POST['name'];
    $email=$_POST['email'];

    if(isset($_POST['name']) && isset($_POST['email'])&& isset($_POST['admin_id'])){
            
            //call update function, set to var
            $result=updateAdminData($admin_id,$name,$email);

            
            
            //if result is true update data
            if($result){
                $response['success']=1;
                $response['message']="Updated";
                echo json_encode($response);
            }
            else{
                $response['success']=0;
                $response['message']="Problem with Update";
                echo json_encode($response);
            }


    }

}

?>