<?php

require_once "db_function.php";


//initialise response and set to array
$response=array();

//define vars
if($_SERVER['REQUEST_METHOD']=='POST'){
    
    
    $player_id=$_POST['player_id'];
    $team_id=$_POST['team_id'];
    

    



    //check all vars set
    if (isset($_POST['player_id'])&& isset($_POST['team_id'])){


        $check="SELECT * FROM final_app_player_team WHERE player_id='$player_id' AND team_id='$team_id' ";
        $res_check=mysqli_query($db,$check);

        if(mysqli_num_rows($res_check)>0){
            $response['success']=0;
            $response['message']="exists";

            echo json_encode($response);

        
        }else{


        //call insertFunction from <db_function class="php"></db_function>

        $result=insertDataPlayerTeam($player_id,$team_id);
        

        //if result var returns true the insert to db
        if($result){
            
            //user is inserted
            //return response to user
            $response['success']=1;
            $response['message']="user data inserted";

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