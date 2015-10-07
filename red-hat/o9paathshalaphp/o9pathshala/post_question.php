<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala",$con);
$sql = $_POST['query'];
$tagsql = $_POST['tag_query'];
$result = mysql_query($sql);
$id = mysql_insert_id();
$sql = str_replace("POST_ID",$id,$tagsql);
$result = mysql_query($sql);
print(json_encode($result));
mysql_close();
?>
