<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$query = $_POST['query'];
$sql = mysql_query($query);
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

