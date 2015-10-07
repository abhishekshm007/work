<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala",$con);
$sql = $_POST['query'];
$result = mysql_query($sql);
print(json_encode($result));
mysql_close();
?>
