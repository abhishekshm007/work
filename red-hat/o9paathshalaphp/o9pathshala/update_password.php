<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$email = $_POST['email'];
$oldPassword = $_POST['oldPassword'];
$newPassword = $_POST['newPassword'];
$institute_id = $_POST['institute_id'];
$sql = mysql_query("select * from `o9_login` where email = '{$email}' and password = '{$oldPassword}' and institute_id = '{$institute_id}'");
$row = mysql_fetch_assoc($sql);
if(count($row)==1)
{ print("false");}
else{
mysql_query("update `o9_login` set password = '{$newPassword}' where email = '{$email}' and institute_id = '{$institute_id}'");
print(mysql_affected_rows());
}
mysql_close();
?>
