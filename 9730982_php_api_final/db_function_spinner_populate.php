<?php

header('Content-Type: application/json');

//require_once "db_config.php";
include "db_config.php";

global $db;

if($_SERVER['REQUEST_METHOD']=='POST'){

    $admin_id=$_POST['admin_id'];
    

$sql="SELECT * FROM final_app_team  WHERE admin_id='$admin_id' ";

if(!$db->query($sql)){
    echo "error in connect";
}else{
    $result=$db->query($sql);
    if($result->num_rows>0){
        $return_arr['spinner_teams']=array();
        while($row=$result->fetch_array()){
            array_push($return_arr['spinner_teams'],array(
                'admin_id'=>$row['admin_id'],
                'team_name'=>$row['team_name'],
                'team_id'=>$row['team_id']
            ));
        }

echo json_encode($return_arr);

    }

}
}


?>