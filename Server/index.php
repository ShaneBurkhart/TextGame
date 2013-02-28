<?php
	define('SITE_ROOT' , $_SERVER['DOCUMENT_ROOT']);

	define('SEPERATOR' , ";");
	define('CRLF' , "\r\n");

	include(SITE_ROOT . "/db.php");
	include(SITE_ROOT . "/functions.php");
	include(SITE_ROOT . "/error.php");

	if(isset($_GET['m']))
		$method = $_GET['m'];
	else
		die("Not Valid Parameters");

	if(function_exists($method)){
		$method();
	}else{
		die("Function " . $method . " Does Not Exist");
	}
?>