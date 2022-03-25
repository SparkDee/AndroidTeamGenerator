<?php

require_once "db_function.php";


//initialise response and set to array
$response=array();

//define vars
if($_SERVER['REQUEST_METHOD']=='POST'){
    
    
    $player_id=$_POST['player_id'];
    $match_id=$_POST['match_id'];
    
    
/* $check_duplicate="select * from final_app_confirmed_player where player_id='$player_id' and match_id='$match_id'";
$res_check_duplicate=mysqli_query($db,$check_duplicate);

if(mysqli_num_rows($res_check_duplicate)>0){
    $response['success']=0;
    $response['message']="player already inserted";

    echo json_encode($response);

} */



    //check all vars set
    if (isset($_POST['player_id'])&& isset($_POST['match_id'])){

        //check if match is full
        $check_capacity="SELECT * FROM final_app_confirmed_player WHERE match_id='$match_id' ";
        $res_check_capacity=mysqli_query($db,$check_capacity);

        if(mysqli_num_rows($res_check_capacity)>9){
            $response['success']=0;
            $response['message']="match full";

            echo json_encode($response);

        }else{
            
        //call insertFunction from db_function class

        $result=insertConfirmedPlayer($player_id,$match_id);
        

        //if result var returns true the insert to db
        if($result){
            
            //user is inserted
            //return response to user
            $response['success']=1;
            $response['message']="player inserted";

            //echo in json response
            echo json_encode($response);
        }else{
            $response['success']=0;
            $response['message']="error";

            //echo in json response
            echo json_encode($response);
        }
        
    }

}

}





?>