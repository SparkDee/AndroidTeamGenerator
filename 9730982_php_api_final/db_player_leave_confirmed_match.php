<?php

require_once "db_function.php";


//initialise response and set to array
$response=array();

//define vars
if($_SERVER['REQUEST_METHOD']=='POST'){
    
    
    $player_id=$_POST['player_id'];
    $match_id=$_POST['match_id'];
    

    



    //check all vars set
    if (isset($_POST['player_id'])&& isset($_POST['match_id'])){
        
        //call insertFunction from <db_function class="php"></db_function>

        $result=deletePlayerConfirmedMatch($player_id,$match_id);
        

        //if result var returns true the insert to db
        if($result){
            
            //user is inserted
            //return response to user
            $response['success']=1;
            $response['message']="user data deleted";

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

//check section 4 !    




?>