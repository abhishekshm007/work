<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$email = $_POST['email'];
$password = $_POST['password'];
$sql = mysql_query("select * from `o9_100000001_student_master` where student_email = '{$email}' and student_password = '{$password}'");
$row = mysql_fetch_assoc($sql);
if(count($row)==1)
{ print("false");}
else
{
$output[]=$row;
while($row = mysql_fetch_assoc($sql))
{
$output[]=$row;
}
print(json_encode($output));
}
mysql_close();
?>
