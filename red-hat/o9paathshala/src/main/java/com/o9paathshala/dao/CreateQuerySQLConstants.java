package com.o9paathshala.dao;

public interface CreateQuerySQLConstants {

	String BATCH_TABLE="CREATE TABLE if not exists`o9_instituteid_batch` ("+
			"`batch_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`batch_name` varchar(200) NOT NULL,"+
			"PRIMARY KEY (`batch_id`),"+
			"UNIQUE KEY `batch_name_UNIQUE` (`batch_name`)"+
			")";

	String TEST_NOTIFICATION_TABLE="CREATE TABLE `o9_instituteid_test_notification` ("+
			"`id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`test_id` int(11) DEFAULT NULL,"+
			"`batch_id` int(11) DEFAULT NULL,"+
			"`create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,"+
			"`update_time` timestamp NULL DEFAULT NULL,"+
			"PRIMARY KEY (`id`)"+
			")";
	String FACULTY_TABLE="CREATE TABLE if not exists`o9_instituteid_faculty_master` ("+
			"`faculty_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`faculty_name` varchar(200) NOT NULL,"+
			"`faculty_email` varchar(50) NOT NULL,"+
			"`faculty_dob` date DEFAULT NULL,"+
			"`faculty_gender` char(1) DEFAULT NULL,"+
			"`faculty_contact` varchar(15) DEFAULT '-',"+
			"`faculty_password` varchar(550) NOT NULL,"+
			"`faculty_address` varchar(200) DEFAULT NULL,"+
			" PRIMARY KEY (`faculty_id`),"+
			"UNIQUE KEY `faculty_id_UNIQUE` (`faculty_id`),"+
			"UNIQUE KEY `faculty_email_UNIQUE` (`faculty_email`)"+
			") ENGINE=InnoDB AUTO_INCREMENT=200000001";

	String FACULTY_SET_AUTOINCREMENT = "ALTER TABLE `o9_instituteid_faculty_master` AUTO_INCREMENT = 200000001";
	
	String FACULTY_TABLE_INSERT_TRIGGER="CREATE  TRIGGER `o9paathshala`.`o9_instituteid_faculty_master_AFTER_INSERT` AFTER INSERT ON `o9_instituteid_faculty_master` FOR EACH ROW insert into o9_login(id,name,email,password,institute_id) values(new.faculty_id,new.faculty_name,new.faculty_email,new.faculty_password,instituteid)";

	String FACULTY_TABLE_UPDATE_TRIGGER="CREATE  TRIGGER `o9paathshala`.`o9_instituteid_faculty_master_AFTER_UPDATE` AFTER UPDATE ON `o9_instituteid_faculty_master` FOR EACH ROW update o9_login set  o9_login.email=new.faculty_email,o9_login.name=new.faculty_name"+ 
			" where o9_login.id=new.faculty_id and o9_login.institute_id=instituteid";

	String FACULTY_TABLE_DELETE_TRIGGER="CREATE  TRIGGER `o9paathshala`.`o9_instituteid_faculty_master_BEFORE_DELETE` BEFORE DELETE ON `o9_instituteid_faculty_master` FOR EACH ROW delete from o9_login where o9_login.id=OLD.faculty_id and o9_login.institute_id=instituteid";
                                       
	String STUDENT_TABLE="CREATE TABLE if not exists`o9_instituteid_student_master` ("+
			"`student_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`student_name` varchar(200) NOT NULL,"+
			"`student_dob` date DEFAULT NULL,"+
			"`student_gender` char(1) DEFAULT NULL,"+
			"`student_contact` varchar(15) DEFAULT '-',"+
			" `student_batch_id` int(11) NOT NULL,"+
			"`student_email` varchar(50) NOT NULL,"+
			"`student_password` varchar(550) NOT NULL,"+
			"`student_address` varchar(200) DEFAULT NULL,"+
			"PRIMARY KEY (`student_id`),"+
			"UNIQUE KEY `id_UNIQUE` (`student_id`),"+
			" UNIQUE KEY `email_UNIQUE` (`student_email`),"+
			"KEY `batch_id_idx` (`student_batch_id`),"+
			"CONSTRAINT `instituteid_batch` FOREIGN KEY (`student_batch_id`) REFERENCES `o9_instituteid_batch` (`batch_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			") ENGINE=InnoDB AUTO_INCREMENT=300000001 ";
	
	String STUDENT_SET_AUTOINCREMENT = "ALTER TABLE `o9_instituteid_student_master` AUTO_INCREMENT = 300000001";
	

	String STUDENT_TABLE_INSERT_TRIGGER="CREATE  TRIGGER `o9paathshala`.`o9_instituteid_student_master_AFTER_INSERT` AFTER INSERT ON `o9_instituteid_student_master` FOR EACH ROW insert into o9_login(id,name,email,password,institute_id) values(new.student_id,new.student_name,new.student_email,new.student_password,instituteid)";

	String STUDENT_TABLE_UPDATE_TRIGGER="CREATE  TRIGGER `o9paathshala`.`o9_instituteid_student_master_AFTER_UPDATE` AFTER UPDATE ON `o9_instituteid_student_master` FOR EACH ROW update o9_login set o9_login.name=new.student_name , o9_login.email=new.student_email"+ 
			" where o9_login.id=new.student_id and o9_login.institute_id=instituteid" ;

	String STUDENT_TABLE_DELETE_TRIGGER="CREATE  TRIGGER `o9paathshala`.`o9_instituteid_student_master_BEFORE_DELETE` BEFORE DELETE ON `o9_instituteid_student_master` FOR EACH ROW delete from o9_login where o9_login.id=OLD.student_id and o9_login.institute_id=instituteid ";

	String SECTION_TABLE="CREATE TABLE if not exists`o9_instituteid_section` ("+
			"`section_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`section_name` varchar(100) NOT NULL,"+
			"PRIMARY KEY (`section_id`)"+
			")";

	String SUBJECT_TABLE="CREATE TABLE if not exists`o9_instituteid_subject` ("+
			"`subject_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`subject_name` varchar(200) NOT NULL,"+
			"PRIMARY KEY (`subject_id`),"+
			" UNIQUE KEY `subject_name_UNIQUE` (`subject_name`)"+
			")";

	String FACULTY_BATCH_TABLE="CREATE TABLE if not exists`o9_instituteid_faculty_batch_mapping` ("+
			"`faculty_id` int(11) NOT NULL,"+
			"`batch_id` int(11) NOT NULL,"+
			"PRIMARY KEY (`faculty_id`,`batch_id`),"+
			" KEY `batchid_idx` (`batch_id`),"+
			" KEY `faculty_id_idx` (`faculty_id`),"+
			" CONSTRAINT `instituteid_batch_id` FOREIGN KEY (`batch_id`) REFERENCES `o9_instituteid_batch` (`batch_id`) ON DELETE CASCADE ON UPDATE NO ACTION,"+
			" CONSTRAINT `instituteid_faculty_id` FOREIGN KEY (`faculty_id`) REFERENCES `o9_instituteid_faculty_master` (`faculty_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")" ;

	String TEST_TABLE="CREATE TABLE if not exists`o9_instituteid_test` ("+
			"`test_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`test_name` varchar(100) NOT NULL,"+
			"`test_duration` int(11) NOT NULL,"+
			"`test_start_date` timestamp NULL DEFAULT NULL,"+
			"`test_end_date` timestamp NULL DEFAULT NULL,"+
			"`test_positive_mark` float NOT NULL,"+
			"`test_negative_mark` float NOT NULL,"+
			"`test_activation` tinyint(1) NOT NULL DEFAULT '1',"+
			"`test_upload_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
			"`test_created_by_id` int(11) NOT NULL,"+
			"`test_created_by_name` varchar(200) NOT NULL,"+
			"`test_type` tinyint(1) NOT NULL DEFAULT '0',"+
			"PRIMARY KEY (`test_id`),"+
			"UNIQUE KEY `test_name_UNIQUE` (`test_name`),"+
			" KEY `facultyid_idx` (`test_created_by_id`)"+
			")";

	String TEST_BATCH_TABLE="CREATE TABLE if not exists`o9_instituteid_test_batch_mapping` ("+
			"`test_id` int(11) NOT NULL,"+
			"`batch_id` int(11) NOT NULL,"+
			"PRIMARY KEY (`test_id`,`batch_id`),"+
			"KEY `instituteid_test_id_idx` (`test_id`),"+
			"KEY `instituteid_batch_id_idx` (`batch_id`),"+
			"CONSTRAINT `instituteid_batch_id_fk` FOREIGN KEY (`batch_id`) REFERENCES `o9_instituteid_batch` (`batch_id`) ON DELETE CASCADE ON UPDATE NO ACTION,"+
			"CONSTRAINT `instituteid_test_id_fk` FOREIGN KEY (`test_id`) REFERENCES `o9_instituteid_test` (`test_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String TEST_BATCH_INSERT_NOTIFY_TRIGGER="CREATE TRIGGER `o9_instituteid_test_batch_mapping_AINS` AFTER INSERT ON `o9_instituteid_test_batch_mapping` FOR EACH ROW"+
			" insert into o9_instituteid_test_notification(test_id,batch_id) values(new.test_id,new.batch_id)";

	String TEST_UPDATE_NOTIFY_TRIGGER="CREATE TRIGGER `o9_instituteid_test_AUPD` AFTER UPDATE ON `o9_instituteid_test` FOR EACH ROW"+
			" update o9_instituteid_test_notification set update_time = now() where test_id = old.test_id";

	String TEST_SECTION_TABLE="CREATE TABLE if not exists`o9_instituteid_test_section_mapping` ("+
			"`test_id` int(11) NOT NULL,"+
			"`section_id` int(11) NOT NULL,"+
			"PRIMARY KEY (`test_id`,`section_id`),"+
			"KEY `instituteid_test_id_idx` (`test_id`),"+
			"KEY `instituteid_section_id_idx` (`section_id`),"+
			"CONSTRAINT `instituteid_sectionid_fk2` FOREIGN KEY (`section_id`) REFERENCES `o9_instituteid_section` (`section_id`) ON DELETE CASCADE ON UPDATE NO ACTION,"+
			"CONSTRAINT `instituteid_testid_fk2` FOREIGN KEY (`test_id`) REFERENCES `o9_instituteid_test` (`test_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String MY_QUESTION_TABLE="CREATE TABLE if not exists`o9_instituteid_my_question` ("+
			"`question_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`question_content` blob NOT NULL,"+
			"`option1` blob,"+
			"`option2` blob,"+
			"`option3` blob,"+
			"`option4` blob,"+
			"`option5` blob,"+
			"`option6` blob,"+
			"`subject_id` int(11) NOT NULL,"+
			"`correct_answer` blob,"+
			"`topic` blob,"+
			"PRIMARY KEY (`question_id`),"+
			"KEY `subject_id_idx` (`subject_id`),"+
			"CONSTRAINT `instituteid_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `o9_instituteid_subject` (`subject_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String PURCHASED_QUESTION_TABLE="CREATE TABLE `o9_instituteid_purchased_question` ("+
			"`set_id` int(11) NOT NULL,"+
			"`purchase_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"+
			"`expire_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',"+
			" `purchased_by` varchar(50) NOT NULL,"+
			"PRIMARY KEY (`set_id`),"+
			"CONSTRAINT `instituteid_set_id` FOREIGN KEY (`set_id`) REFERENCES `o9_question_set` (`set_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")" ;

	String RESULTS_TABLE="CREATE TABLE if not exists`o9_instituteid_results` ("+
			"`student_id` int(11) NOT NULL,"+
			"`batch_id` int(11) NOT NULL,"+
			"`test_id` int(11) NOT NULL,"+
			"`attempt` int(11) NOT NULL,"+
			"`score` float NOT NULL,"+
			"`attempt_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,"+
			"`test_name` varchar(200) NOT NULL,"+
			"PRIMARY KEY (`student_id`,`batch_id`,`test_id`,`attempt`),"+
			"CONSTRAINT `instituteid_studentid` FOREIGN KEY (`student_id`) REFERENCES `o9_instituteid_student_master` (`student_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";


	String SECTION_MY_QUESTION_TABLE="CREATE TABLE if not exists`o9_instituteid_section_my_question_mapping` ("+
			"`section_id` int(11) NOT NULL,"+
			"`question_id` int(11) NOT NULL,"+
			"PRIMARY KEY (`section_id`,`question_id`),"+
			"KEY `instituteid_question_id_idx` (`question_id`),"+
			"KEY `instituteid_section_id_idx` (`section_id`),"+
			"CONSTRAINT `instituteid_section_id` FOREIGN KEY (`section_id`) REFERENCES `o9_instituteid_section` (`section_id`) ON DELETE CASCADE ON UPDATE NO ACTION,"+
			"CONSTRAINT `instituteid_question_id` FOREIGN KEY (`question_id`) REFERENCES `o9_instituteid_my_question` (`question_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String SECTION_PURCHASED_QUESTION_TABLE="CREATE TABLE if not exists`o9_instituteid_section_purchased_question_mapping` ("+
			"`section_id` int(11) NOT NULL,"+
			"`question_id` int(11) NOT NULL,"+
			"PRIMARY KEY (`section_id`,`question_id`),"+
			"KEY `instituteid_question_id_idx` (`question_id`),"+
			"KEY `instituteid_section_id_idx` (`section_id`),"+
			"CONSTRAINT `instituteid_questionid` FOREIGN KEY (`question_id`) REFERENCES `o9_question_bank` (`question_id`) ON DELETE CASCADE ON UPDATE NO ACTION,"+
			"CONSTRAINT `instituteid_sectionid` FOREIGN KEY (`section_id`) REFERENCES `o9_instituteid_section` (`section_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String MY_TEST_VIEW="create VIEW  `o9_instituteid_my_test_view`  AS select `c`.`test_id` AS `test_id`,`a`.`section_id` AS `section_id`,`a`.`section_name` AS `section_name`,`b`.`question_id` AS `question_id`,`b`.`question_content` AS `question_content`,`b`.`option1` AS `option1`,`b`.`option2` AS `option2`,`b`.`option3` AS `option3`,`b`.`option4` AS `option4`,`b`.`option5` AS `option5`,`b`.`option6` AS `option6`,`b`.`correct_answer` AS `correct_answer` from (((`o9_instituteid_test_section_mapping` `c` left join `o9_instituteid_section` `a` on((`c`.`section_id` = `a`.`section_id`))) left join `o9_instituteid_section_my_question_mapping` `d` on((`a`.`section_id` = `d`.`section_id`))) left join `o9_instituteid_my_question` `b` on((`d`.`question_id` = `b`.`question_id`)))";

	String PURCHASED_TEST_VIEW="create VIEW `o9_instituteid_purchased_test_view` AS select `c`.`test_id` AS `test_id`,`a`.`section_id` AS `section_id`,`a`.`section_name` AS `section_name`,`b`.`question_id` AS `question_id`,`b`.`question_content` AS `question_content`,`b`.`option1` AS `option1`,`b`.`option2` AS `option2`,`b`.`option3` AS `option3`,`b`.`option4` AS `option4`,`b`.`option5` AS `option5`,`b`.`option6` AS `option6`,`b`.`correct_answer` AS `correct_answer` from (((`o9_instituteid_test_section_mapping` `c` left join `o9_instituteid_section` `a` on((`c`.`section_id` = `a`.`section_id`))) left join `o9_instituteid_section_purchased_question_mapping` `d` on((`a`.`section_id` = `d`.`section_id`))) left join `o9_question_bank` `b` on((`d`.`question_id` = `b`.`question_id`)))";

	String RESULT_VIEW="CREATE  VIEW `o9_instituteid_results_view` AS select `a`.`student_id` AS `student_id`,`a`.`batch_id` AS `batch_id`,`a`.`test_id` AS `test_id`,`a`.`attempt` AS `attempt`,`a`.`score` AS `score`,`a`.`attempt_date` AS `attempt_date`,`a`.`test_name` AS `test_name`,`b`.`student_name` AS `student_name`,`c`.`batch_name` AS `batch_name` from ((`o9_instituteid_results` `a` left join `o9_instituteid_student_master` `b` on((`a`.`student_id` = `b`.`student_id`))) left join `o9_instituteid_batch` `c` on((`c`.`batch_id` = `b`.`student_batch_id`)))";

	String CREATE_EVENT="CREATE EVENT if not exists instituteid_event "+
			"ON SCHEDULE "+
			"EVERY 1 DAY "+
			"STARTS '2014-04-30 03:20:00 ' ON COMPLETION PRESERVE ENABLE "+ 
			"DO "+
			"delete from o9_instituteid_purchased_question where expire_time<= current_timestamp()";

	String FORUM_POST_TABLE="CREATE TABLE `o9_instituteid_post` " +
			" (`post_id` int(11) NOT NULL AUTO_INCREMENT, " +
			" `post_title` text NOT NULL,`post_content` " +
			"  text NOT NULL,`user_id` int(11) DEFAULT NULL," +
			"`post_time` timestamp NULL DEFAULT NULL," +
			"PRIMARY KEY (`post_id`)," +
			"UNIQUE KEY `post_id_UNIQUE` (`post_id`))";

	String FORUM_POST_ANSWER="CREATE TABLE `o9_instituteid_post_answer` ("+
			"`answer_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`post_id` int(11) NOT NULL,"+
			"`user_id` int(11) NOT NULL,"+
			"`answer_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"+
			"`answer_content` varchar(500) NOT NULL,"+
			"PRIMARY KEY (`answer_id`),"+
			"UNIQUE KEY `answer_id_UNIQUE` (`answer_id`),"+
			"KEY `fk_o9_instituteid_post_answer_1_idx` (`post_id`),"+
			"CONSTRAINT `fk_o9_instituteid_post_answer_1` FOREIGN KEY (`post_id`) REFERENCES `o9_instituteid_post` (`post_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String POST_ANSWER_LIKE="CREATE TABLE `o9_instituteid_post_answer_like_map` ("+
			"`answer_id` int(11) NOT NULL,"+
			"`user_id` int(11) NOT NULL,"+
			"KEY `fk_o9_instituteid_post_answer_like_map_1_idx` (`answer_id`),"+
			"CONSTRAINT `fk_o9_instituteid_post_answer_like_map_1` FOREIGN KEY (`answer_id`) REFERENCES `o9_instituteid_post_answer` (`answer_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String POST_LIKE="CREATE TABLE `o9_instituteid_post_like_user_map` ("+
			"`post_id` int(11) NOT NULL,"+
			"`user_id` int(11) NOT NULL,"+
			"KEY `fk_o9_instituteid_post_like_user_map_1_idx` (`post_id`),"+
			"CONSTRAINT `fk_o9_instituteid_post_like_user_map_1` FOREIGN KEY (`post_id`) REFERENCES `o9_instituteid_post` (`post_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String TAGS_TABLE="CREATE TABLE `o9_instituteid_tags` ("+
			"`tag_id` int(11) NOT NULL AUTO_INCREMENT,"+
			"`tag_name` varchar(45) NOT NULL,"+
			"`tag_desc` varchar(500) DEFAULT NULL,"+
			"`tag_reputation` int(11) DEFAULT NULL,"+
			"PRIMARY KEY (`tag_id`),"+
			"UNIQUE KEY `tag_id_UNIQUE` (`tag_id`),"+
			"UNIQUE KEY `tag_name_UNIQUE` (`tag_name`)"+
			")";
	String POST_TAG_MAP="CREATE TABLE `o9_instituteid_post_tag_map` ("+
			"`post_id` int(11) NOT NULL,"+
			"`tag_id` int(11) NOT NULL,"+
			"KEY `fk_o9_instituteid_post_tag_map_1_idx` (`tag_id`),"+
			"KEY `fk_o9_instituteid_post_tag_map_2_idx` (`post_id`),"+
			"CONSTRAINT `fk_o9_instituteid_post_tag_map_1` FOREIGN KEY (`tag_id`) REFERENCES `o9_instituteid_tags` (`tag_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,"+
			" CONSTRAINT `fk_o9_instituteid_post_tag_map_2` FOREIGN KEY (`post_id`) REFERENCES `o9_instituteid_post` (`post_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			") ";
	String FORUM_VIEW="CREATE  VIEW `o9_instituteid_forum_users` AS select `o9_instituteid_student_master`.`student_id` AS `user_id`,`o9_instituteid_student_master`.`student_name` AS `user_name` from `o9_instituteid_student_master` union select `o9_instituteid_faculty_master`.`faculty_id` AS `user_id`,`o9_instituteid_faculty_master`.`faculty_name` AS `user_name` from `o9_instituteid_faculty_master` union select `o9_login`.`id` AS `user_id`,`o9_login`.`name` AS `user_name` from `o9_login` where (`o9_login`.`id` = instituteid)";

	String TEST_DOC_TABLE="CREATE TABLE `o9_instituteid_test_doc` ("+
			"`test_id` int(11) NOT NULL,"+
			"`paper_id` varchar(45) NOT NULL,"+
			"`result_id` varchar(45),"+
			" PRIMARY KEY (`test_id`,`paper_id`),"+
			"KEY `o9_instituteid_test_doc_idx` (`test_id`),"+
			" CONSTRAINT `o9_instituteid_test_doc` FOREIGN KEY (`test_id`) REFERENCES `o9_instituteid_test` (`test_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")";

	String TEST_DOC_ANSWER_TABLE="CREATE TABLE `o9_instituteid_test_doc_answer` ("+
			"`test_id` int(11) NOT NULL,"+
			"`question` int(11) NOT NULL,"+
			"`correct_answer` varchar(45) NOT NULL,"+
			"PRIMARY KEY (`test_id`,`question`,`correct_answer`),"+
			"KEY `o9_instituteid_test_doc_answer_idx` (`test_id`),"+
			" CONSTRAINT `o9_instituteid_test_doc_answer` FOREIGN KEY (`test_id`) REFERENCES `o9_instituteid_test` (`test_id`) ON DELETE CASCADE ON UPDATE NO ACTION"+
			")"; 
}
