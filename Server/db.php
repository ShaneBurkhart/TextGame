<?php
	$db_name = "text";
	$user = "shane";
	$pass = "Java#97Java";
	$server = "localhost";

	$GLOBALS["db"] = new mysqli($server, $user, $pass, $db_name);

	if($GLOBALS["db"]->errno){
		die("Could not connect");
	}
?>