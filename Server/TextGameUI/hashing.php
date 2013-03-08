<?php
	function toHash($pass){
		$a = array();
		$salt = mcrypt_create_iv(24, MCRYPT_DEV_RANDOM);
		$a['salt'] = $salt;
		$salted_pass = $salt . $pass;
		$hash = hash("sha256", $salted_pass);
		$a['hash'] = $hash;
		return $a;
	}

	function toHashWithSalt($pass, $salt){
		$a = array();
		$a['salt'] = $salt;
		$salted_pass = $salt . $pass;
		$hash = hash("sha256", $salted_pass);
		$a['hash'] = $hash;
		return $a;
	}

	function randHash(){
		$pass = randomPassword();
		$a = array();
		$a["pass"] = $pass;
		$salt = mcrypt_create_iv(24, MCRYPT_DEV_RANDOM);
		$a['salt'] = $salt;
		$salted_pass = $salt . $pass;
		$hash = hash("sha256", $salted_pass);
		$a['hash'] = $hash;
		return $a;
	}

	function randomPassword() {
	    $alphabet = "abcdefghijklmnopqrstuwxyzABCDEFGHIJKLMNOPQRSTUWXYZ0123456789";
	    $pass = array(); //remember to declare $pass as an array
	    $alphaLength = strlen($alphabet) - 1; //put the length -1 in cache
	    for ($i = 0; $i < 8; $i++) {
	        $n = rand(0, $alphaLength);
	        $pass[] = $alphabet[$n];
	    }
	    return implode($pass); //turn the array into a string
	}
?>