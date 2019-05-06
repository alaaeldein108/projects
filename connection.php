<?php
class connection
{
	var $host="localhost";
	var $db="asa school";
	var $username="root";
	var $password="123456789";
	
	var $connect;
	
	function connect()
	{	
		$this->connect=mysql_connect($this->host,$this->username,$this->password);
		mysql_select_db($this->db);		
	}
	function disconnect()
	{
		if (isset($this->connect))
		{
			mysql_close($this->connect);
		}
	}
	function get()
	{
		return $this->connect;
	}
	
	
}
?>