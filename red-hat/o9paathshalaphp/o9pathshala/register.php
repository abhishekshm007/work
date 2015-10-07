<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala",$con);
$name = $_POST['name'];
$password = $_POST['password'];
$email = $_POST['email'];
$result = mysql_query("insert into `o9_100000001_student_master`(student_name,student_email,student_password, student_batch_id) values ('{$name}','{$email}','{$password}','46')");
print(json_encode($result));
mysql_close();
?>
