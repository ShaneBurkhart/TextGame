<?php
	function retrieve(){
		$db = $GLOBALS["db"];

		if(!isset($_GET["u"]))
			error(0);
		$user = $_GET["u"];
		$query = "	SELECT players.id, games.id, games.player1_id, games.player2_id, games.player1_score, games.player2_score
					FROM games
					INNER JOIN players
					ON players.username = ? AND (games.player1_id = players.id OR games.player2_id = players.id)";
		$stmt = $db->prepare($query);
		$stmt->bind_param("s", $user);
		$stmt->execute();
		$stmt->bind_result($pid, $gid, $p1id, $p2id, $p1score, $p2score);
		$data = "";
		while($stmt->fetch())
			$data = $data . "{" . $pid . SEPERATOR . $user . SEPERATOR . $gid . SEPERATOR . $p1id . SEPERATOR . $p2id . SEPERATOR . $p1score . SEPERATOR . $p2score . "}" . CRLF;
		echo $data;
	}

	function search(){
		$db = $GLOBALS["db"];

		if(!isset($_GET["uid"]))
			error(0);

		$userId = $_GET["uid"];

		$query = "	SELECT COUNT(*) , player_id FROM searching";
		$r = $db->query($query);
		$row = $r->fetch_row();
		$r->close();
		if($row[1] == $userId)
			error(SEARCHING);
		if($row[0] < 1){
			$query = "	INSERT INTO searching (player_id) 
						VALUES (?)";
			$stmt = $db->prepare($query);
			$stmt->bind_param("i", $userId);
			if($stmt->execute())
				error(SEARCHING);
			else
				error(INTERNAL_SERVER_ERROR);
		}else{
			$oid = $row[1];
			$query = "	INSERT INTO games (player1_id, player2_id, player1_score, player2_score) 
						VALUES (?, ?, -1, -1)";
			$stmt = $db->prepare($query);
			$stmt->bind_param("ii", $userId, $oid);
			if($stmt->execute()){
				$stmt->close();
				$query = "	DELETE FROM searching
							WHERE player_id = ?";
				$stmt = $db->prepare($query);
				$stmt->bind_param("i", $oid);
				if($stmt->execute())
					$stmt->close();
				else
					error(INTERNAL_SERVER_ERROR);
				echo $oid;
			} else
				error(INTERNAL_SERVER_ERROR. $stmt->error);
		}
	}

	function login(){
		include(SITE_ROOT . "/hashing.php");

		if(!isset($_GET["u"]) or !isset($_GET["p"]))
			error(0);

		$user = $_GET["u"];
		$pass = $_GET["p"];
		
		$db = $GLOBALS["db"];

		$query = "	SELECT id, hash, salt
					FROM players
					WHERE username = ?";
		$stmt = $db->prepare($query);
		$stmt->bind_param("s", $user);
		$stmt->execute();
		$stmt->bind_result($id, $hash, $salt);
		$stmt->fetch();
		$creds = toHashWithSalt($pass, $salt);
		if($creds["hash"] == $hash)
			echo $id;
		else
			error(LOGIN_FAILED);
	}	

	function email(){
		include(SITE_ROOT . "/hashing.php");

		$db = $GLOBALS["db"];

		if(!isset($_GET["e"]))
			error(FAILED_TO_CREATE_USER);
		$email = $_GET["e"];

		if (!filter_var($email, FILTER_VALIDATE_EMAIL))
			error(INVALID_EMAIL);

		$query = "	SELECT id
					FROM players
					WHERE email = ?";
		$stmt = $db->prepare($query);
		$stmt->bind_param("s", $email);
		$stmt->execute();
		$stmt->bind_result($id);
		if($stmt->fetch())
			error(USER_ALREADY_EXISTS);
		$stmt->close();

		$passCreds = randHash();
		echo $passCreds["pass"];
		try {
			@mail($email, "Welcome To Text Game!", "We are glad you joined. " . CRLF . CRLF . " Your password is:" . CRLF . $passCreds["pass"] . CRLF . CRLF . "Thanks for downloading and enjoy!");
		} catch (Exception $e) {
			error(INTERNAL_SERVER_ERROR);
		}

		$query = "	INSERT INTO players (username, email, hash, salt)
					VALUES (? ,?, ? ,?)";
		$stmt = $db->prepare($query);
		$stmt->bind_param("ssss", $email, $email, $passCreds["hash"], $passCreds["salt"]);
		if(!$stmt->execute())
			error(INTERNAL_SERVER_ERROR);
		$stmt->close();
	}
?>