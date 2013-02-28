<?php

	define('USER_ALREADY_EXISTS', -1);
	define('LOGIN_FAILED', -2);
	define('FAILED_TO_CREATE_USER', -3);
	define('INTERNAL_SERVER_ERROR', -4);
	define('INVALID_EMAIL', -5);
	define('SEARCHING', -6);

	function error($num){
		die($num . "");
	}
?>