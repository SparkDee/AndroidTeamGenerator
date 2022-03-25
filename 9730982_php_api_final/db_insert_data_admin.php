<?php



require_once "db_function.php";


//initialise response and set to array
$response=array();

//define vars
if($_SERVER['REQUEST_METHOD']=='POST'){
    $name=$_POST['name'];
    $email=$_POST['email'];
    $password=$_POST['password'];
    $password=password_hash($password,PASSWORD_DEFAULT);



    //check all vars set
    if (isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])){
        
        
        //check if admin already exists
        $check_email="SELECT * FROM final_app_admin WHERE email='$email' ";
        $res_check_email=mysqli_query($db,$check_email);

        if(mysqli_num_rows($res_check_email)>0){
            $response['success']=0;
            $response['message']="exists";

            echo json_encode($response);

        }else{
        //call insertFunction from <db_function class="php"></db_function>

        $result=insertDataAdmin($name,$email,$password);
        

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

//check section 4 !    




?>