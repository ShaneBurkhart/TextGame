<?php
	function findRandomOpponent(){
		$db = $GLOBALS["db"];

		if(!isset($_GET["uid"]))
			result(ERROR);

		$userID = $_GET["uid"];
		$miscFunctions = new miscFunctions();
		$searchingUserID = $miscFunctions->getSearchingUser();
		//This user is already searching
		if($searchingUserID == $userID)
			result(SUCCESS);

		if($searchingUserID){
			//There is a user so match them up and make a game
			if(!$miscFunctions->createGame($userID, $searchingUserID))
				result(ERROR);
			if(!$miscFunctions->deleteUserFromSearch($searchingUserID))
				result(ERROR);
			echo $searchingUserID;
		}else{
			//No user so add to search table
			if(!$miscFunctions->addUserToSearch($userID))
				result(ERROR);
			result(SUCCESS);
		}
	}

	function getGames(){
		$db = $GLOBALS["db"];

		if(!isset($_GET["uid"]))
			result(ERROR);

		$userID = $_GET["uid"];
		$miscFunctions = new miscFunctions();
		$query = "	SELECT players.username, games.id, games.player1_id, games.player2_id, games.player1_score, games.player2_score
					FROM games
					INNER JOIN players
					ON players.id = ? AND (games.player1_id = ? OR games.player2_id = ?)";
		$stmt = $db->prepare($query);
		$stmt->bind_param("iii", $userID, $userID, $userID);
		$stmt->execute();
		$stmt->store_result();
		$stmt->bind_result($user, $gid, $p1id, $p2id, $p1score, $p2score);
		$data = "";
		while($stmt->fetch())
			$data = $data . "{" . $gid . SEPERATOR . $p1id . SEPERATOR . $p2id . SEPERATOR . $miscFunctions->getUsername($p1id) . SEPERATOR . $miscFunctions->getUsername($p2id) . SEPERATOR . $p1score . SEPERATOR . $p2score . "}" . CRLF;
		echo $data;
	}

	function loginWithFacebook(){
		$db = $GLOBALS["db"];
		$miscFunctions = new miscFunctions();

		if(!isset($_GET["e"]) or !isset($_GET["u"]))
			result(ERROR);

		$email = $_GET["e"];
		$user = $_GET["u"];
		if(!$miscFunctions->getUserIDFromFacebookEmail($email)){
			$query = "	INSERT INTO players (username, email, facebook, hash, salt)
						VALUES (? ,?, 1, 0, 0)";
			$stmt = $db->prepare($query);
			$stmt->bind_param("ss", $user, $email);
			$r = $stmt->execute();
			$stmt->close();
			if(!$r)
				error(ERROR);
		}
		$query = "	SELECT id
					FROM players
					WHERE email = ?
					AND facebook = 1";
		$stmt = $db->prepare($query);
		$stmt->bind_param("s", $email);
		$stmt->execute();
		$stmt->bind_result($userID);
		$stmt->fetch();
		$stmt->close();
		result($userID + "");
	}

	function loginWithEmailAndPassword(){
		$db = $GLOBALS["db"];
		$miscFunctions = new miscFunctions();

		if(!isset($_GET["e"]) or !isset($_GET["p"]))
			result(ERROR);

		$email = $_GET["e"];
		$pass = $_GET["p"];
		
		if(!$miscFunctions->getUserIDFromEmail($email))
			result(FALSE);

		$query = "	SELECT id, hash, salt
					FROM players
					WHERE email = ?
					AND facebook = 0";
		$stmt = $db->prepare($query);
		$stmt->bind_param("s", $email);
		$stmt->execute();
		$stmt->bind_result($userID, $hash, $salt);
		$stmt->fetch();
		$creds = toHashWithSalt($pass, $salt);
		if($creds["hash"] == $hash)
			echo $userID;
		else
			error(FALSE);
	}

	function doesEmailExist(){
		$db = $GLOBALS["db"];
		$miscFunctions = new miscFunctions();

		if(!isset($_GET["e"]))
			result(ERROR);

		$email = $_GET["e"];
		
		if(!$miscFunctions->getUserIDFromEmail($email))
			result(FALSE);
		else
			result(SUCCESS);
	}

	function doesUsernameExist(){
		$db = $GLOBALS["db"];
		$miscFunctions = new miscFunctions();

		if(!isset($_GET["u"]))
			result(ERROR);

		$user = $_GET["u"];
		
		if(!$miscFunctions->getUserIDFromUsername($user))
			result(FALSE);
		else
			result(SUCCESS);
	}

	function createUserWithEmail(){
		$db = $GLOBALS["db"];
		$miscFunctions = new miscFunctions();

		if(!isset($_GET["e"]) or !isset($_GET["u"]))
			result(ERROR);

		$email = $_GET["e"];
		$user = $_GET["u"];

		if (!filter_var($email, FILTER_VALIDATE_EMAIL))
			result(ERROR);

		if($miscFunctions->getUserIDFromEmail($email))
			result(FALSE);

		$passCreds = randHash();

		$query = "	INSERT INTO players (username, email, hash, salt)
					VALUES (? ,?, ? ,?)";
		$stmt = $db->prepare($query);
		$stmt->bind_param("ssss", $user, $email, $passCreds["hash"], $passCreds["salt"]);
		$r = $stmt->execute();
		$stmt->close();
		if(!$r)
			result(ERROR);
		try {
			mail($email, "Welcome To Text Game!", "We are glad you joined. " . CRLF . CRLF . " Your password is:" . CRLF . $passCreds["pass"] . CRLF . CRLF . "Thanks for downloading and enjoy!");
		} catch (Exception $e) {
			result(ERROR);
		}
		result($passCreds["pass"]);
	}
?>