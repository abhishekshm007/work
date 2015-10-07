<?php
mysql_connect("550ab7ce4382ec88cf000168-o9.rhcloud.com:46321","admina5QjsUr","3XxPLZvlFjA8") or die(mysql_error);
mysql_select_db("o9paathshala");
$id = $_POST['id'];
$gender = $_POST['gender'];
$institue_id = $_POST['institue_id'];
mysql_query("update o9_{$institue_id}_student_master set student_gender = '{$gender}' where student_id = '{$id}'");
print(mysql_affected_rows());
mysql_close();
?>
