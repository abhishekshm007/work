<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$email = $_POST['email'];
$sql = mysql_query("select o9_login.institute_id, o9_institute_master.institute_name from `o9_login` left join `o9_institute_master` on o9_login.institute_id = o9_institute_master.institute_id where email = '{$email}'");
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
