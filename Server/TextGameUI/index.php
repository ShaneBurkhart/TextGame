<?php
	////Result codes////
	define('ERROR', -1);
	define('SUCCESS', 1);
	define('FALSE', "0");

	function result($num){
		die($num . "");
	}

	//Site Constants
	define('SITE_ROOT' , $_SERVER['DOCUMENT_ROOT'] . "/TextGameUI");
	define('SEPERATOR' , ";");
	define('CRLF' , "\r\n");

	//Include the functions and database
	include(SITE_ROOT . "/db.php");
	include(SITE_ROOT . "/functions.php");
	include(SITE_ROOT . "/miscFunctionsObject.php");
	include(SITE_ROOT . "/hashing.php");

	//Get called function and distribute work
	if(isset($_GET['f']))
		$function = $_GET['f'];
	else
		result(ERROR);

	if(function_exists($function)){
		$function();
	}else{
		result(ERROR);
	}

	$GLOBALS['db']->close();
?>