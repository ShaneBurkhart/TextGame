<?php

	class MiscFunctions{

		public $db;

		public function __construct(){
			$this->db = $GLOBALS["db"];
		}

		public function getSearchingUser(){
			//Return 0 if none is found
			$query = "	SELECT player_id 
						FROM searching";
			$r = $this->db->query($query);
			$row = $r->fetch_row();
			$r->close();
			if($row)
				return $row[0];
			else
				return 0;
		}

		public function getUsername($userID){
			$query = "	SELECT username 
						FROM players
						WHERE id = ?";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("i", $userID);
			$stmt->execute();
			$stmt->bind_result($username);
			$stmt->fetch();
			$stmt->close();
			return $username;
		}

		public function addUserToSearch($userID){
			$query = "	INSERT INTO searching (player_id) 
						VALUES (?)";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("i", $userID);
			$r = $stmt->execute();
			$stmt->close();
			return $r;
		}

		public function createGame($user1ID, $user2ID){
			$query = "	INSERT INTO games (player1_id, player2_id, player1_score, player2_score) 
						VALUES (?, ?, -1, -1)";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("ii", $user1ID, $user2ID);
			$r = $stmt->execute();
			$stmt->close();
			return $r;
		}

		public function deleteUserFromSearch($userID){
			$query = "	DELETE FROM searching
						WHERE player_id = ?";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("i", $userID);
			$r = $stmt->execute();
			$stmt->close();
			return $r;
		}

		public function isFacebook($userID){
			$query = "	SELECT facebook
						FROM players
						WHERE id = ?";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("i", $userID);
			$stmt->execute();
			$stmt->bind_result($facebook);
			$stmt->fetch();
			$stmt->close();
			return $facebook;
		}

		public function getUserIDFromEmail($email){
			$query = "	SELECT id
						FROM players
						WHERE email = ?
						AND facebook = 0";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("s", $email);
			$stmt->execute();
			$stmt->bind_result($userID);
			$stmt->fetch();
			$stmt->close();
			return $userID;
		}

		public function getUserIDFromUsername($user){
			$query = "	SELECT id
						FROM players
						WHERE username = ?
						AND facebook = 0";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("s", $user);
			$stmt->execute();
			$stmt->bind_result($userID);
			$stmt->fetch();
			$stmt->close();
			return $userID;
		}

		public function getUserIDFromFacebookEmail($email){
			$query = "	SELECT id
						FROM players
						WHERE email = ?
						AND facebook = 1";
			$stmt = $this->db->prepare($query);
			$stmt->bind_param("s", $email);
			$stmt->execute();
			$stmt->bind_result($userID);
			$stmt->fetch();
			$stmt->close();
			return $userID;
		}
	}
?>