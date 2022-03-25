<?php

require_once "db_function.php";

//set response as array
$response=array();

if($_SERVER['REQUEST_METHOD']=='POST'){


    //define vars to update
    $admin_id=$_POST['admin_id'];
    $match_id=$_POST['match_id'];
    $team_id=$_POST['team_id'];
    $date=$_POST['date'];
    $time=$_POST['time'];
    $venue=$_POST['venue'];

    if(isset($_POST['date']) && isset($_POST['time'])&& isset($_POST['admin_id'])&& isset($_POST['venue'])&& isset($_POST['match_id'])&& isset($_POST['team_id'])){
            
            //call update function, set to var
            $result=updateAdminMatch($match_id,$admin_id,$team_id,$date,$time,$venue);

            
            
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