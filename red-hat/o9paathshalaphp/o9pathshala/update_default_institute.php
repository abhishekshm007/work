<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$email = $_POST['email'];
$name = $_POST['name'];
$institute_id = $_POST['institute_id'];
$sql = mysql_query("update `o9_login` set default_institute_id ='{$institute_id}' where email = '{$email}' and name = '{$name}'");
print(mysql_affected_rows());
mysql_close();
?>
