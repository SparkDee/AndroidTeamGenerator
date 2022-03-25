<?php
require_once "db_function.php";

$response= array();



if($_SERVER['REQUEST_METHOD']=='POST'){
    
    $player_id=$_POST['player_id'];
    $team_id=$_POST['team_id'];
    

    
    if(isset($_POST['player_id'])){

        $result=returnAllPlayerConfirmedMatches($player_id,$team_id);


    if(mysqli_num_rows($result)>0){
        
        $response['details']=array();
    
        while($row=mysqli_fetch_array($result)){

            $data=array();
            
            $data["date"]=$row['date'];
            $data["time"]=$row['time'];
            $data["venue"]=$row['venue'];
            $data["player_id"]=$row['player_id'];
            $data["match_id"]=$row['match_id'];
            $data["admin_id"]=$row['admin_id'];
            $data["team_id"]=$row['team_id'];


            array_push($response['details'],$data);

        
        }

    }
}
    //if result is true
    //display allusers 
    if($result){
            
        //user is inserted
        //return response to user
        $response['success']=1;
        $response['message']="Successfully Displayed";

        //echo in json response
        echo json_encode($response);

    }else{
        $response['success']=0;
        $response['message']="No Record Found";
        echo json_encode($response);
    }
    if($db==true){
        mysqli_close($db);
    }
 
    }





?>