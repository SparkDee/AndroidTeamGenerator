
<?php

require_once "db_function.php";


//initialise response and set to array
$response=array();

//define vars
if($_SERVER['REQUEST_METHOD']=='POST'){
    
    
    $admin_id=$_POST['admin_id'];
    $team_id=$_POST['team_id'];
    $date=$_POST['date'];
    $time=$_POST['time'];
    $venue=$_POST['venue'];

    



    //check all vars set
    if (isset($_POST['admin_id'])&& isset($_POST['team_id']) && isset($_POST['date']) && isset($_POST['time'])&& isset($_POST['venue'])){
        
        //call insertFunction from <db_function class="php"></db_function>

        $result=insertAdminMatch($admin_id,$team_id,$date,$time,$venue);
        

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

//check section 4 !    




?>