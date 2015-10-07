<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$id = $_POST['id'];
$name = $_POST['name'];
$email = $_POST['email'];
$institue_id = $_POST['institue_id'];
mysql_query("update o9_{$institue_id}_student_master set student_name = '{$name}' where student_id = '{$id}'");
mysql_query("update o9_login set name = '{$name}' where email = '{$email}'");
print(mysql_affected_rows());
mysql_close();
?>
