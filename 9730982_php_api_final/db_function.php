<?php

//require_once "db_config.php";
include "db_config.php";



//PLAYER FUNCTIONS


//return player confirmed matches
function returnAllPlayerConfirmedMatches($player_id,$team_id){
    global $db;
    
    //SQL select string
    $sql="SELECT
    final_app_match.date,final_app_match.time,final_app_match.venue,final_app_confirmed_player.player_id,final_app_match.match_id,final_app_match.admin_id,final_app_match.team_id
    FROM
    final_app_match
    INNER JOIN
    final_app_confirmed_player
    ON
    final_app_match.match_id=final_app_confirmed_player.match_id
    WHERE player_id='$player_id' AND team_id='$team_id'";
    
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}


//Return all details player

function returnAllPlayerDetails($player_id){

    global $db;
    
    //SQL select string
    $sql="SELECT* FROM final_app_player WHERE player_id='$player_id'";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;

}


//Insert function new player data

function insertDataPlayer($name,$email,$password){
    //global variable
    global $db;

    //sql insertion query
    $sql= "INSERT INTO final_app_player(name,email,password) VALUES ('$name','$email','$password')";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;


}

//Update function player data

function updatePlayerData($player_id,$name,$email){
    //global conn var
    global $db;

    //sql update query
    
    $sql="UPDATE final_app_player SET name='$name',email='$email' WHERE player_id='$player_id'";

    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result from db
   return $result;

}


//Delete function player

function deletePlayerData($player_id){
    global $db;
    
    //delete sql query
    $sql="DELETE FROM final_app_player where player_id='$player_id'";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}


//Delete function player confirmed match

function deletePlayerConfirmedMatch($player_id,$match_id){
    global $db;
    
    //delete sql query
    $sql="DELETE FROM final_app_confirmed_player where player_id='$player_id' and match_id='$match_id'";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}



function insertDataPlayerTeam($player_id,$team_id){
    //global variable
    global $db;

    //sql insertion query
    $sql= "INSERT INTO final_app_player_team(player_id,team_id) VALUES ('$player_id','$team_id')";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;


}

function returnAllPlayerTeams($player_id){

    global $db;
    
    //SQL select string
    $sql="SELECT
    final_app_team.team_name,final_app_player_team.player_id,final_app_team.team_id
    FROM
    final_app_player_team
    INNER JOIN
    final_app_team
    ON
    final_app_player_team.team_id=final_app_team.team_id
    WHERE player_id='$player_id'";
    
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;

}

function insertConfirmedPlayer($player_id,$match_id){
    global $db;
    
    //delete sql query
    $sql="INSERT INTO final_app_confirmed_player(player_id,match_id) VALUES ('$player_id','$match_id')";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}

function displayAllPlayerConfirmedMatchesDate($player_id){
    global $db;
    
    //delete sql query
    $sql="SELECT
    final_app_match.date,final_app_match.time,final_app_match.venue,final_app_confirmed_player.player_id
    FROM
    final_app_match
    INNER JOIN
    final_app_confirmed_player
    ON
    final_app_match.match_id=final_app_confirmed_player.match_id
    WHERE player_id='$player_id'";
    
    

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}












//ADMIN/CREATOR FUNCTIONS


//Insert function new admin data

function insertDataAdmin($name,$email,$password){
    //global variable
    global $db;

    //sql insertion query
    $sql= "INSERT INTO final_app_admin(name,email,password) VALUES ('$name','$email','$password')";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;


}



//Insert function admin create match

function insertAdminMatch($admin_id,$team_id,$date,$time,$venue){

    //global var
    global $db;

    //sql insert query
    $sql="INSERT INTO final_app_match(admin_id,team_id,date,time,venue) VALUES ('$admin_id','$team_id','$date','$time','$venue')";

    //query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return result
    return $result;
}

//Delete function admin

function deleteAdminTeam($admin_id){
    global $db;
    
    //delete sql query
    $sql="DELETE FROM final_app_team where admin_id='$admin_id'";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}


//Insert function admin create new team

function insertAdminTeam($admin_id,$team_name){

    //global var
    global $db;

    //sql insert query
    $sql="INSERT INTO final_app_team(admin_id,team_name) VALUES ('$admin_id','$team_name')";

    //query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return result
    return $result;
}

//Update function admin data

function updateAdminData($admin_id,$name,$email){
    //global conn var
    global $db;

    //sql update query
    
    $sql="UPDATE final_app_admin SET name='$name',email='$email' WHERE admin_id='$admin_id'";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result from db
   return $result;

}


//Delete function admin

function deleteAdminData($admin_id){
    global $db;
    
    //delete sql query
    $sql="DELETE FROM final_app_admin where admin_id='$admin_id'";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}

//Return all details admin

function returnAllAdminDetails($admin_id){

    global $db;
    
    //SQL select string
    $sql="SELECT* FROM final_app_admin WHERE admin_id='$admin_id'";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;

}

function returnAllAdminTeams($admin_id){

    global $db;
    
    //SQL select string
    $sql="SELECT* FROM final_app_team WHERE admin_id='$admin_id'";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;

}

//Delete function admin match

function deleteAdminMatch($match_id){
    global $db;
    
    //delete sql query
    $sql="DELETE FROM final_app_match where match_id='$match_id'";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}


//Update function admin match

function updateAdminMatch($match_id,$admin_id,$team_id,$date,$time,$venue){
    global $db;
    
    //delete sql query
    $sql="UPDATE final_app_match SET admin_id='$admin_id',team_id='$team_id',date='$date',time='$time',venue='$venue' WHERE match_id='$match_id'";

    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;
}


//Return Players Confirmed For Match

function returnAllPlayersConfirmedForMatch($match_id){

    global $db;
    
    //SQL select string
    $sql="SELECT final_app_player.name
    FROM
    final_app_player
    INNER JOIN
    final_app_confirmed_player
    ON
    final_app_confirmed_player.player_id=final_app_player.player_id
    WHERE
    final_app_confirmed_player.match_id='$match_id'";
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;

}







//Player and Admin Functions

function returnTeamMatches($team_id){

    global $db;
    
    //SQL select string
    $sql="SELECT* FROM final_app_match WHERE team_id='$team_id'";
   
    
    //allows to query db
    $result=mysqli_query($db,$sql) or die(mysqli_error($db));

    //return the result
   return $result;

}





?>