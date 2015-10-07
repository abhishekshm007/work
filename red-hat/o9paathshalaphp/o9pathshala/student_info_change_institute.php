
<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$email = $_POST['email'];
$institute_id = $_POST['institute_id'];
$password = $_POST['password'];
$sql = mysql_query("select * from o9_login where email = '{$email}' and password = '{$password}' and institute_id = '{$institute_id}'");
$row = mysql_fetch_assoc($sql);
if(count($row)==1)
{ print("false");}

else{
$sql = mysql_query("select * from `o9_{$institute_id}_student_master` where student_email = '{$email}'");
$row = mysql_fetch_assoc($sql);
$output[]=$row;
while($row = mysql_fetch_assoc($sql))
{
$output[]=$row;
}
print(json_encode($output));

}
mysql_close();
?>
