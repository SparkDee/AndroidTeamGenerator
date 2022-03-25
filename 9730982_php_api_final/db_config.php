<?php

//constants
define("DB_NAME","mdavidson04");
define("DB_USER","mdavidson04");
define("DB_PASSWORD","");
define("DB_HOST","mdavidson04.lampt.eeecs.qub.ac.uk");

//database connection string
$db=mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_NAME);

//check success
//if($db==false){
  //  echo"Not able to connect";
//}else{
    //echo"Connection successful";
//}

?>