package com.o9paathshala.dao;
public interface SQLConstants {
	/*--------------Abhishek Queries--------------------*/
	String CHECK_STUDENT_REGISTRATION = "select `o9_login`.`email` from `o9paathshala`.`o9_login` where email=? and institute_id = ? ";
	String REGISTER_STUDENT = "INSERT INTO `o9paathshala`.`o9_100000001_student_master` (`student_name`,`student_batch_id`, `student_email`,`student_password`) VALUES(? ,1, ? ,?)";
	String GET_STUDENT_SESSION_DATA = "SELECT `o9_login`.*, `o9_institute_master`.`institute_name` FROM `o9paathshala`.`o9_login` "
			+ "left join `o9_institute_master` on `o9_institute_master`.`institute_id` = `o9_login`.`institute_id` "
			+ "where `o9_login`.`email` = ? ";
	
	String CHECK_INSTITUTE_REGISTRATION_BY_EMAIL = "select `o9_institute_master`.`institute_id` from `o9paathshala`.`o9_institute_master` where institute_email=?";
	String CHECK_INSTITUTE_REGISTRATION_BY_NAME = "SELECT `o9_institute_master`.`institute_email`  FROM `o9paathshala`.`o9_institute_master` "
			+ "where `o9_institute_master`.`institute_name` = ?";
	String REGISTER_INSTITUTE = "insert into o9_institute_master(institute_email,institute_name,institute_type,institute_activation_link) values(?,?,?,?)";
	String GET_INSTITUTE_SESSION_DATA = "SELECT `o9_login`.*, `o9s_institute_master`.`institute_name` FROM `o9paathshala`.`o9_login` "
			+ "left join `o9_institute_master` on `o9_institute_master`.`institute_id` = `o9_login`.`institute_id` where `o9_login`.`email` = ? ";
	String GET_USER = "SELECT `o9_login`.*, `o9_institute_master`.`institute_name` FROM `o9paathshala`.`o9_login` "
			+ "left join `o9_institute_master` on `o9_institute_master`.`institute_id` = `o9_login`.`institute_id` "
			+ "where `o9_login`.`email` = ? ";
	String CHECK_LOGIN="select exists(select 1 from o9_login where email=? and password=? limit 1) as count";
	String UPDATE_DEFAULT_INSTITUTE = "UPDATE `o9paathshala`.`o9_login` SET `default_institute_id` = 1 WHERE `institute_id` = ? and id = ?";
	String GET_INSTITUTE_BY_LINK="select o9_institute_master.*, o9_login.name from o9_institute_master left join o9_login on o9_institute_master.institute_id = o9_login.id where institute_activation_link = ?";
	String UPDATE_ACTIVATION_PASSWORD="update o9_login a left join o9_institute_master b on a.id=b.institute_id set a.password=? where b.institute_activation_link=?";
	String UPDATE_ACTIVATION_LINK = "update o9_institute_master set institute_activation_link = ? where institute_activation_link = ? ";
	String FORGOT_PASSWORD = "UPDATE `o9paathshala`.`o9_login` SET `password` = ? WHERE `email` = ?";
	/*--------------Abhishek Queries--------------------*/
	/*--------------Abhishek  forum Queries--------------------*/
	String GET_ALL_TAGS = "SELECT o9_INSTITUTE_ID_tags.* FROM `o9paathshala`.`o9_INSTITUTE_ID_tags` where tag_name ";
	String COUNT_ALL_TAGS = "select DISTINCT count(*) as count from o9_INSTITUTE_ID_tags";
	String COUNT_GROUPS = "SELECT count(*) as count from  institute_o9_INSTITUTE_ID_forum";
	
	String ADD_TAG = "INSERT INTO `o9paathshala`.`o9_INSTITUTE_ID_tags` (`tag_name`,`tag_desc`,`tag_reputation`) VALUES(?,?,0)";
	String DELETE_TAG = "DELETE FROM `o9paathshala`.`o9_INSTITUTE_ID_tags` WHERE `tag_id` = ?";
	String UPDATE_TAG = "UPDATE `o9paathshala`.`o9_INSTITUTE_ID_tags` SET `tag_name` = ?, `tag_desc` = ? WHERE `tag_id` = ?";
	String GET_TAGS = "SELECT `o9_INSTITUTE_ID_tags`.`tag_id`, `o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,"
			+ "`o9_INSTITUTE_ID_tags`.`tag_reputation` FROM `o9paathshala`.`o9_INSTITUTE_ID_tags` where `o9_INSTITUTE_ID_tags`.`tag_name`"
			+ " LIKE_DATA  ORDER_DATA ";
	
	String UPDATE_TAG_REPUTATION = "UPDATE `o9paathshala`.`o9_INSTITUTE_ID_tags` SET `tag_reputation` = `tag_reputation` + 1 WHERE `tag_id` = ?";
	
	String POST_QUESTION = "INSERT INTO `o9paathshala`.`o9_INSTITUTE_ID_post` "
			+ "(`post_title`,`post_content`,`user_id`,`post_time`) "
			+ "VALUES(?,?,?,now())";
	
	String QUESTION_TAG_MAP = "INSERT INTO `o9paathshala`.`o9_INSTITUTE_ID_post_tag_map`"
			+ "(`post_id`,`tag_id`)"
			+ "VALUES(LAST_INSERT_ID(),?)";
	
	
	String GET_QUESTION_FOR_MINE_STUDENT = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, `q`.`user_name`, q.`answers` , "
			+ "q.`reputation`, `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,  "
			+ "`o9_INSTITUTE_ID_tags`.`tag_reputation`, q.liked "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers`, "
			+ "d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked  "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
			+ "	count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation  "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers  "
			+ "FROM (   "
			+ "Select `o9_INSTITUTE_ID_post`.`post_id`,`o9_INSTITUTE_ID_post`.`post_title`, "
			+ "`o9_INSTITUTE_ID_post`.`post_content`,`o9_INSTITUTE_ID_post`.`post_time`, "
			+ "`o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name`"
			+ "FROM `o9_INSTITUTE_ID_post`  "
			+ "left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id`  "
			+ "where `o9_INSTITUTE_ID_post`.`user_id`  = ? limit ?,?"
			+ ") as a "
			+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by   a.post_id"
			+ "	) as c "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` "
			+ "group by d.`post_id`"
			+ "	) as q "
			+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` order by  q.post_time desc ";
	
	String GET_QUESTION_FOR_NEWEST_STUDENT = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, `q`.`user_name`, q.`answers` , "
			+ "q.`reputation`, `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,  "
			+ "`o9_INSTITUTE_ID_tags`.`tag_reputation`, q.liked "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers`, "
			+ "d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked  "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
			+ "	count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation  "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers  "
			+ "FROM (   "
			+ "Select `o9_INSTITUTE_ID_post`.`post_id`,`o9_INSTITUTE_ID_post`.`post_title`, "
			+ "`o9_INSTITUTE_ID_post`.`post_content`,`o9_INSTITUTE_ID_post`.`post_time`, "
			+ "`o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name`"
			+ "FROM `o9_INSTITUTE_ID_post`  "
			+ "left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id`  "
			+ " limit ?,?"
			+ ") as a "
			+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by   a.post_id"
			+ "	) as c "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` "
			+ "group by d.`post_id`"
			+ "	) as q "
			+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` order by  q.post_time desc ";
	
	String GET_QUESTION_FOR_ANSWERED_STUDENT = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, `q`.`user_name`, q.`answers` , "
			+ "q.`reputation`, `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,  "
			+ "`o9_INSTITUTE_ID_tags`.`tag_reputation`, q.liked "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers`, "
			+ "d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked  "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
			+ "	count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation  "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers  "
			+ "FROM (   "
			+ "Select `o9_INSTITUTE_ID_post`.`post_id`,`o9_INSTITUTE_ID_post`.`post_title`, "
			+ "`o9_INSTITUTE_ID_post`.`post_content`,`o9_INSTITUTE_ID_post`.`post_time`, "
			+ "`o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name`"
			+ "FROM `o9_INSTITUTE_ID_post`  "
			+ "left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id`  "
			+ "order by  o9_INSTITUTE_ID_post.post_time desc "
			+ " limit ?,?"
			+ ") as a "
			+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by   a.post_id "
			+ "	) as c "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` "
			+ "group by d.`post_id`"
			+ "	) as q "
			+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` order by  q.answers desc  ";
	
	String GET_QUESTION_FOR_UNANSWERED_STUDENT = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, `q`.`user_name`, q.`answers` , "
			+ "q.`reputation`, `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,  "
			+ "`o9_INSTITUTE_ID_tags`.`tag_reputation`, q.liked "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers`, "
			+ "d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked  "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
			+ "	count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation  "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers  "
			+ "FROM (   "
			+ "Select `o9_INSTITUTE_ID_post`.`post_id`,`o9_INSTITUTE_ID_post`.`post_title`, "
			+ "`o9_INSTITUTE_ID_post`.`post_content`,`o9_INSTITUTE_ID_post`.`post_time`, "
			+ "`o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name`"
			+ "FROM `o9_INSTITUTE_ID_post`  "
			+ "left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id`  "
			+ "order by  o9_INSTITUTE_ID_post.post_time desc "
			+ " limit ?,?"
			+ ") as a "
			+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by   a.post_id "
			+ "	) as c "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "where answers = 0 "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` "
			+ "group by d.`post_id`"
			+ "	) as q "
			+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` order by  q.post_time desc   ";
	
	String GET_QUESTION_FOR_REPUTED_STUDENT = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`, `q`.`user_name`, q.`answers` , "
			+ "q.`reputation`, `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`,  "
			+ "`o9_INSTITUTE_ID_tags`.`tag_reputation`, q.liked "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, d.`answers`, "
			+ "d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked  "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, `c`.`user_name`, c.`answers` ,"
			+ "	count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation  "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers  "
			+ "FROM (   "
			+ "Select `o9_INSTITUTE_ID_post`.`post_id`,`o9_INSTITUTE_ID_post`.`post_title`, "
			+ "`o9_INSTITUTE_ID_post`.`post_content`,`o9_INSTITUTE_ID_post`.`post_time`, "
			+ "`o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name`"
			+ "FROM `o9_INSTITUTE_ID_post`  "
			+ "left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id`  "
			+ "order by  o9_INSTITUTE_ID_post.post_time desc "
			+ " limit ?,?"
			+ ") as a "
			+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by   a.post_id "
			+ "	) as c "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` "
			+ "group by d.`post_id`"
			+ "	) as q "
			+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` order by  q.reputation desc  ";
	
	
	String GET_TAGS_OF_A_QUESTION = "SELECT `o9_INSTITUTE_ID_tags`.`tag_id`,`o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`, `o9_INSTITUTE_ID_tags`.`tag_reputation` "
			+ "FROM `o9paathshala`.`o9_INSTITUTE_ID_post_tag_map` "
			+ "left join `o9_INSTITUTE_ID_tags`  on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` "
			+ "where `o9_INSTITUTE_ID_post_tag_map`.`post_id` = ? group by `o9_INSTITUTE_ID_post_tag_map`.`tag_id`";
	
	String GET_LAST_INCREMENTED_ID = "SELECT LAST_INSERT_ID()";
	
	String UPDATE_QUESTION_LIKE = "INSERT INTO `o9paathshala`.`o9_INSTITUTE_ID_post_like_user_map` (`post_id`,`user_id`) VALUES(?,?)";
	
	String UPDATE_QUESTION_UNLIKE = "DELETE FROM `o9paathshala`.`o9_INSTITUTE_ID_post_like_user_map` WHERE post_id = ? and user_id = ? ";
	
	String UPDATE_ANSWER_LIKE = "INSERT INTO `o9paathshala`.`o9_INSTITUTE_ID_post_answer_like_map` (`answer_id`,`user_id`) VALUES(?,?)";
	
	String UPDATE_ANSWER_UNLIKE = "DELETE FROM `o9paathshala`.`o9_INSTITUTE_ID_post_answer_like_map` WHERE answer_id = ? and user_id = ? ";
	
	String POST_ANSWER = "INSERT INTO `o9paathshala`.`o9_INSTITUTE_ID_post_answer` "
			+ "(`post_id`,`user_id`,`answer_time`,`answer_content`) "
			+ "VALUES( ? , ?  ,now(), ? )";
	
	String GET_POST_ANSWER = "select `a`.`answer_id`, `a`.`user_id`, `a`.`answer_content`, `a`.`user_name`, `a`.`reputation`, `a`.`answer_time`, "
			+ "count(`o9_INSTITUTE_ID_post_answer_like_map`.`answer_id`) as liked "
			+ "from( "
			+ "SELECT `o9_INSTITUTE_ID_post_answer`.`answer_id`,`o9_INSTITUTE_ID_post_answer`.`post_id` ,`o9_INSTITUTE_ID_post_answer`.`user_id`, `o9_INSTITUTE_ID_post_answer`.`answer_time`, "
			+ "`o9_INSTITUTE_ID_post_answer`.`answer_content`,`o9_INSTITUTE_ID_forum_users`.`user_name`, count(`o9_INSTITUTE_ID_post_answer_like_map`.`answer_id`) as reputation "
			+ "from `o9_INSTITUTE_ID_post_answer`left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post_answer`.`user_id` "
			+ "left join `o9_INSTITUTE_ID_post_answer_like_map` on `o9_INSTITUTE_ID_post_answer_like_map`.`answer_id` = `o9_INSTITUTE_ID_post_answer`.`answer_id` "
			+ "group by `o9_INSTITUTE_ID_post_answer`.`answer_id`) as a "
			+ "left join `o9_INSTITUTE_ID_post_answer_like_map` on `a`.`answer_id` = `o9_INSTITUTE_ID_post_answer_like_map`.`answer_id` and `o9_INSTITUTE_ID_post_answer_like_map`.`user_id` = ? "
			+ "where `a`.`post_id` = ? "
			+ "group by `a`.`answer_id` "
			+ "order by `a`.`answer_time` desc";
	
	
	String GET_EXPLORED_QUESTION = "Select `q`.`post_id`, `q`.`post_title`, `q`.`post_content`, `q`.`post_time`, `q`.`user_id`,`q`.`user_name`, "
			+ "q.`answers` ,q.`reputation`, q.liked , `o9_INSTITUTE_ID_tags`.`tag_id`, `o9_INSTITUTE_ID_tags`.`tag_name`, `o9_INSTITUTE_ID_tags`.`tag_desc`, `o9_INSTITUTE_ID_tags`.`tag_reputation` "
			+ "from( "
			+ "Select `d`.`post_id`, `d`.`post_title`, `d`.`post_content`, `d`.`post_time`, `d`.`user_id`, `d`.`user_name`, "
			+ "d.`answers` ,d.`reputation` , count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as liked "
			+ "from ( "
			+ "SELECT  `c`.`post_id`, `c`.`post_title`, `c`.`post_content`, `c`.`post_time`, `c`.`user_id`, "
			+ "`c`.`user_name`, c.`answers` ,count(`o9_INSTITUTE_ID_post_like_user_map`.`user_id`) as reputation "
			+ "FROM ( "
			+ "SELECT `a`.`post_id`, `a`.`post_title`, `a`.`post_content`, `a`.`post_time`, `a`.`user_id`, `a`.`user_name`, "
			+ "count(`o9_INSTITUTE_ID_post_answer`.`answer_id`) as answers "
			+ "FROM ( "
			+ "Select `o9_INSTITUTE_ID_post`.`post_id`, `o9_INSTITUTE_ID_post`.`post_title`, `o9_INSTITUTE_ID_post`.`post_content`, `o9_INSTITUTE_ID_post`.`post_time`, `o9_INSTITUTE_ID_post`.`user_id`, `o9_INSTITUTE_ID_forum_users`.`user_name` "
			+ "FROM `o9_INSTITUTE_ID_post` left join `o9_INSTITUTE_ID_forum_users` on `o9_INSTITUTE_ID_forum_users`.`user_id` = `o9_INSTITUTE_ID_post`.`user_id` "
			+ "where `o9_INSTITUTE_ID_post`.`post_id` = ? "
			+ "group by `o9_INSTITUTE_ID_post`.`post_id` "
			+ ") as a "
			+ "left join `o9_INSTITUTE_ID_post_answer` on `o9_INSTITUTE_ID_post_answer`.`post_id` = `a`.`post_id` "
			+ "group by `a`.`post_id` "
			+ ") as c "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `c`.`post_id` "
			+ "group by c.`post_id` "
			+ ") as d "
			+ "left join `o9_INSTITUTE_ID_post_like_user_map` on `o9_INSTITUTE_ID_post_like_user_map`.`post_id` = `d`.`post_id` and o9_INSTITUTE_ID_post_like_user_map.`user_id` = ? "
			+ "group by d.`post_id`  "
			+ ") as q "
			+ "left join `o9_INSTITUTE_ID_post_tag_map` on `o9_INSTITUTE_ID_post_tag_map`.`post_id` = `q`.`post_id` "
			+ "left join `o9_INSTITUTE_ID_tags` on `o9_INSTITUTE_ID_tags`.`tag_id` = `o9_INSTITUTE_ID_post_tag_map`.`tag_id` ";
	
	
	/*--------------Harshit Queries--------------------*/
	String COUNT="select count(*) as count from o9_instituteid_batch where batch_name";
	String GET_BATCH="select o9_instituteid_batch.batch_id ,  o9_instituteid_batch.batch_name, count(o9_instituteid_student_master.student_id) as students from o9_instituteid_batch left join o9_instituteid_student_master on o9_instituteid_batch.batch_id=o9_instituteid_student_master.student_batch_id where o9_instituteid_batch.batch_name ";
	String CHECK_BATCH_NAME="select exists(select 1 from o9_instituteid_batch where batch_name=? limit 1) as count";
	String INSERT_BATCH="insert ignore into o9_instituteid_batch(batch_name) values(?)";
	String UPLOAD_STUDENT_CSV="LOAD DATA LOCAL INFILE '?' IGNORE INTO TABLE o9_instituteid_student_master FIELDS TERMINATED BY '/' LINES TERMINATED BY ';' (student_name, student_email,student_password) set student_batch_id=LAST_INSERT_ID()";
	String DELETE_BATCH="delete from o9_instituteid_batch where batch_id=?";
	String UPDATE_BATCH_NAME = "update o9_instituteid_batch set batch_name=? where batch_name=?";
	String COUNT_STUDENT_ON_BATCH="select count(*) as count from o9_instituteid_student_master where student_batch_id=? and student_name";
	String GET_STUDENTS_ON_BATCH = "select student_name,student_email from o9_instituteid_student_master where student_batch_id=? and student_name";
	String COUNT_STUDENT ="select count(*) as count from o9_instituteid_student_master where student_name";
	String GET_STUDENTS = "select a.student_name,a.student_id,a.student_email,a.student_contact,b.batch_name from o9_instituteid_student_master a left join o9_instituteid_batch b on a.student_batch_id=b.batch_id where a.student_name";
	String GET_STUDENT_ON_ID ="select a.*,b.batch_name from o9_instituteid_student_master a left join o9_instituteid_batch b on a.student_batch_id=b.batch_id where a.student_id=?" ;
	String DELETE_STUDENT = "delete from o9_instituteid_student_master where student_id=?";
	String GET_BATCHES="select * from o9_instituteid_batch";
	String ADD_SINGLE_STUDENT = "insert ignore into o9_instituteid_student_master(student_email,student_name,student_password,student_batch_id) values(?,?,?,?)";
	String CHECK_STUDENT_EMAIL="select exists(select 1 from o9_instituteid_student_master where student_email=? limit 1) as count";
	String UPLOAD_STUDENTS="LOAD DATA LOCAL INFILE 'path' IGNORE INTO TABLE o9_instituteid_student_master FIELDS TERMINATED BY '/' LINES TERMINATED BY ';' (student_name, student_email,student_password) set student_batch_id=?";
	String CHANGE_BATCH = "update o9_instituteid_student_master set student_batch_id=? where student_id=?";
	String COUNT_FACULTY = "select count(*) as count from o9_instituteid_faculty_master where faculty_name";
	String GET_FACULTIES = "select faculty_name,faculty_id,faculty_email,faculty_contact from o9_instituteid_faculty_master where faculty_name";
	String GET_FACULTY_ON_ID = "select * from o9_instituteid_faculty_master where faculty_id=?";
	String DELETE_FACULTY = "delete from o9_instituteid_faculty_master where faculty_id=?";
	String COUNT_BATCHES_ON_FACULTY="select count(*) as count from o9_instituteid_faculty_batch_mapping where faculty_id=?";
	String GET_BATCHES_ON_FACULTY = "select a.batch_name,a.batch_id from o9_instituteid_batch a left join o9_instituteid_faculty_batch_mapping b on b.batch_id=a.batch_id where b.faculty_id=? ";
	String CHECK_FACULTY_EMAIL = "select exists(select 1 from o9_instituteid_faculty_master where faculty_email=? limit 1) as count";
	String ADD_FACULTY = "insert ignore into o9_instituteid_faculty_master(faculty_email,faculty_name,faculty_password) values(?,?,?)";
	String ASSIGN_BATCH_TO_FACULTY = "insert ignore into o9_instituteid_faculty_batch_mapping(faculty_id,batch_id) values(LAST_INSERT_ID(),?)";
	String AUTO_COMPLETE_STUDENT = "select student_id,student_name,student_email from o9_instituteid_student_master where student_name";
	String AUTO_COMPLETE_FACULTY = "select faculty_id,faculty_name from o9_instituteid_faculty_master where faculty_name";
	String ALLOT_BATCH_TO_FACULTY = "insert ignore into o9_instituteid_faculty_batch_mapping(faculty_id,batch_id) values(?,?)";
	String DEALLOT_BATCH_TO_FACULTY = "delete from o9_instituteid_faculty_batch_mapping where faculty_id=? and batch_id=?";
	String COUNT_SUBJECT = "select count(*) as count from o9_instituteid_subject where subject_name";
	String GET_SUBJECTS = "select * from o9_instituteid_subject where subject_name";
	String CHECK_SUBJECT_NAME="select exists(select 1 from o9_instituteid_subject where subject_name=? limit 1) as count";
	String ADD_SUBJECT = "insert ignore into o9_instituteid_subject(subject_name) values(?)";
	String UPDATE_SUBJECT_NAME = "update o9_instituteid_subject set subject_name=? where subject_name=?";
	String DELETE_SUBJECT = "delete from o9_instituteid_subject where subject_id=?";
	String SUBJECT_LIST = "select * from o9_instituteid_subject";
	String COUNT_MY_QUESTION = "select DISTINCT count(*) as count from o9_instituteid_my_question where subject_id=? and topic";
	String GET_MY_QUESTIONS = "select DISTINCT question_id,question_content,topic from o9_instituteid_my_question where subject_id=? and topic";
	String GET_QUESTION_ON_ID = "select question_id,question_content,option1,option2,option3,option4,option5,option6,correct_answer from o9_instituteid_my_question where question_id=?";
	String DELETE_QUESTION = "delete from o9_instituteid_my_question where question_id=?";
	String UPDATE_QUESTION = "update o9_instituteid_my_question set question_content=?,option1=?,option2=?,option3=?,option4=?,option5=?,option6=?,correct_answer=? where question_id=?";
	String O9_SUBJECT_LIST = "select * from o9_subject";
	String COUNT_PURCHASED_QUESTION = "select count(*) as count from (select distinct a.question_id from o9_question_bank a left join o9_question_set_mapping b on a.question_id=b.question_id where b.set_id in (select c.set_id from o9_instituteid_purchased_question c) and a.subject_id=? and a.topic";
	String GET_PURCHASED_QUESTIONS = "select distinct a.question_id,a.topic,a.question_content from o9_question_bank a left join o9_question_set_mapping b on a.question_id=b.question_id where b.set_id in (select c.set_id from o9_instituteid_purchased_question c) and a.subject_id=? and a.topic";
	String GET_PURCHASED_QUESTION_ON_ID = "select question_id,question_content,option1,option2,option3,option4,option5,option6,correct_answer from o9_question_bank where question_id=? ";
	String UPLOAD_QUESTION = "LOAD DATA LOCAL INFILE 'path' REPLACE INTO TABLE o9_instituteid_my_question FIELDS TERMINATED BY '#@' LINES TERMINATED BY '::;' (question_content,option1,option2,option3,option4,option5,option6,correct_answer,topic) set subject_id=?";
	String ADD_SINGLE_QUESTION = "insert ignore into o9_instituteid_my_question(question_content,option1,option2,option3,option4,option5,option6,correct_answer,subject_id,topic) values(?,?,?,?,?,?,?,?,?,?)";
	String CREATE_TEST = "insert ignore into o9_instituteid_test(test_name,test_duration,test_start_date,test_end_date,test_positive_mark,test_negative_mark,test_created_by_id,test_created_by_name) values(?,?,?,?,?,?,?,?)";
	String CREATE_SECTION = "insert ignore into o9_instituteid_section(section_name) values(?)";
	String TEST_SECTION = "insert ignore into o9_instituteid_test_section_mapping(test_id,section_id) values((select max(test_id) from o9_instituteid_test) ,last_insert_id())";
	String TEST_BATCH = "insert ignore into o9_instituteid_test_batch_mapping(test_id,batch_id) values((select max(test_id) from o9_instituteid_test),?)";
	String CHECK_TEST_NAME = "select exists(select 1 from o9_instituteid_test where test_name=? limit 1) as count";
	String CHECK_EDIT_TEST_NAME = "select exists(select 1 from o9_instituteid_test where test_name=? and test_id!=? limit 1) as count";
	String GET_ADVANCE_TEST_DETAIL="select a.test_id from o9_instituteid_test where a.test_name=?";
	String GET_TEST_DETAIL = "select a.test_id,c.section_name,c.section_id from o9_instituteid_test a left join o9_instituteid_test_section_mapping b on a.test_id=b.test_id left join o9_instituteid_section c on b.section_id=c.section_id where a.test_name=?";
	String SECTION_MY_QUESTION_MAP = "insert ignore into  o9_instituteid_section_my_question_mapping(section_id,question_id) values(?,?)";
	String SECTION_PURCHASED_QUESTION_MAP = "insert ignore into  o9_instituteid_section_purchased_question_mapping(section_id,question_id) values(?,?)";
	String COUNT_TEST = "select count(*) as count from o9_instituteid_test where test_name";
	String GET_TESTS = "select * from o9_instituteid_test where test_name";
	String UPDATE_ACTIVATION = "update o9_instituteid_test set test_activation=? where test_id=?";
	String DELETE_TEST = "delete from o9_instituteid_test where test_id=?";
	String COUNT_MY_TEST_QUESTION = "select count(*) as count from o9_instituteid_my_test_view where test_id=? and section_name";
	String GET_MY_TEST_QUESTIONS = "select question_id,section_id,question_content,section_name from o9_instituteid_my_test_view where test_id=? and section_name";
	String DELETE_MY_TEST_QUESTION = "delete from o9_instituteid_section_my_question_mapping where question_id=? and section_id=?";
	String COUNT_MY_PURCHASED_QUESTION = "select count(*) as count from o9_instituteid_purchased_test_view where test_id=? and section_name";
	String GET_MY_PURCHASED_TEST_QUESTIONS = "select question_id,section_id,question_content,section_name from o9_instituteid_purchased_test_view where test_id=? and section_name";
	String DELETE_PURCHASED_TEST_QUESTION = "delete from o9_instituteid_section_purchased_question_mapping where question_id=? and section_id=?";
	String UPDATE_TEST = "update o9_instituteid_test set test_name=?,test_duration=?,test_start_date=?,test_end_date=?,test_positive_mark=?,test_negative_mark=? where test_id=?";
	String TEST_LIST = "select test_name,test_id from o9_instituteid_test order by test_upload_time desc";
	String COUNT_RESULTS = "select count(*) as count from o9_instituteid_results_view where test_id=? and batch_id=? and student_name";
	String GET_RESULTS = "select student_id,attempt,score,attempt_date,test_name,student_name,batch_name from o9_instituteid_results_view where test_id=? and batch_id=? and student_name";
	String GET_MY_RESULT = "select test_name,score from o9_instituteid_results_view where student_id=? order by attempt_date asc";
	String GET_FACULTY_TESTS = "select * from o9_instituteid_test where test_created_by_id=? and test_name";
	String COUNT_FACULTY_TEST = "select count(*) as count from o9_instituteid_test where test_created_by_id=? and test_name";
	String COUNT_STUDENT_TEST = "select count(*) as count from o9_instituteid_test_batch_mapping a left join o9_instituteid_test b on a.test_id=b.test_id where  a.batch_id=? and b.test_activation=? and b.test_name" ;
	String GET_ATTEMPTED_TEST = "select test_id from o9_instituteid_results_view where student_id=? and test_name";
	String GET_STUDENT_TEST = "select b.test_id,b.test_name,b.test_duration,b.test_start_date,b.test_end_date,b.test_positive_mark,b.test_negative_mark,test_created_by_name from o9_instituteid_test_batch_mapping a left join o9_instituteid_test b on a.test_id=b.test_id where  a.batch_id=? and b.test_activation=? and b.test_name";
	String START_TEST="select * from (select * from o9_instituteid_my_test_view  union select * from o9_instituteid_purchased_test_view ) t where test_id=? order by section_name";
	String GET_TEST="select * from o9_instituteid_test where test_id=? and test_activation=1";
	String SAVE_RESULT="insert into o9_instituteid_results(test_id,test_name,score,batch_id,student_id,attempt)values(?,?,?,?,?,(select COALESCE(MAX(attempt), 0)+1 from o9_instituteid_results_view  where test_id=? and student_id=?))";
	String GET_STUDENT_BATCH = "select student_batch_id from o9_instituteid_student_master where student_id=?";
	String COUNT_MY_RESULTS = "select count(*) as count from o9_instituteid_results where student_id=? and test_name";
	String GET_MY_RESULTS = "select test_name,score,attempt,attempt_date from o9_instituteid_results where student_id=? and test_name";
	String STUDENT_TEST_LIST = "select a.test_name,b.test_id from o9_instituteid_test_batch_mapping b left join o9_instituteid_test a on a.test_id=b.test_id where b.batch_id=? order by test_upload_time desc";
	String LEADERBOARD_COUNT = "select count(*) as count from o9_instituteid_results_view where test_id=?" ;
	String GET_STUDENTS_RANK = "select student_id,student_name,score,attempt from o9_instituteid_results_view where test_id=? order by attempt asc, score desc,attempt_date asc";
	String INSTITUTE_PROFILE = "select institute_name ,institute_email ,institute_contact ,institute_address  from o9_institute_master where institute_id=?";
	String UPDATE_INSTITUTE_PROFILE = "update o9_institute_master set institute_name=? , institute_email=?,institute_contact=?, institute_address=? where institute_id=?";
	String UPDATE_PASSWORD = "update o9_login set password=? where email=? and id=? and institute_id=?";
	String UPDATE_FACULTY_PROFILE = "update o9_instituteid_faculty_master set faculty_name=? , faculty_email=?,faculty_dob=?,faculty_gender=?,faculty_contact=?, faculty_address=? where faculty_id=?";
	String UPDATE_STUDENT_PROFILE = "update o9_instituteid_student_master set student_name=? , student_email=?,student_dob=?,student_gender=?,student_contact=?, student_address=? where student_id=?";
	String TOTAL_TEST = "select count(*) as count from o9_instituteid_test";
	String TOTAL_FACULTY = "select count(*) as count from o9_instituteid_faculty_master";
	String TOTAL_QUESTION = "select  count(*) as count from o9_instituteid_my_question";
	String TOTAL_SET = "select count(*) as count from o9_question_set";
	String TOTAL_STUDENT = "select count(*) as count from o9_instituteid_student_master";
	String TOTAL_SUBJECT = "select count(*) as count from o9_instituteid_subject";
	String TOTAL_UNATTEMPTED_TEST = "select count(*) as count from o9_instituteid_test_batch_mapping where batch_id=? and test_id not in (select test_id from o9_instituteid_results where student_id=?)";
	String UNATTEMPTED_TEST = "select b.test_name,b.test_start_date,b.test_end_date from o9_instituteid_test_batch_mapping a left join o9_instituteid_test b on a.test_id=b.test_id   where a.batch_id=? and a.test_id not in (select test_id from o9_instituteid_results where student_id=?)";
	String TOTAL_MY_TEST = "select count(*) as count from o9_instituteid_test where test_created_by_id=?";
	String TOTAL_MY_STUDENT = "select count(*) as count from o9_instituteid_student_master where student_batch_id  in (select batch_id from o9_instituteid_faculty_batch_mapping where faculty_id=?)";
	String GET_FACULTY_STUDENT = "select a.student_name,a.student_id,a.student_email,a.student_contact,b.batch_name from o9_instituteid_student_master a left join o9_instituteid_batch b on a.student_batch_id=b.batch_id where a.student_batch_id in (select batch_id from o9_instituteid_faculty_batch_mapping where faculty_id=?) and student_name";
	String MY_RANK = "select student_id,student_name,score from o9_instituteid_results_view where test_id=? order by attempt asc, score desc,attempt_date asc" ;
	String GET_MY_INSTITUTES = "select a.institute_id,b.institute_name from o9_login a left join o9_institute_master b on a.institute_id=b.institute_id where a.email=?";
	String SWITCH_USER = "SELECT `o9_login`.*, `o9_institute_master`.`institute_name` FROM `o9paathshala`.`o9_login` "
			+ "left join `o9_institute_master` on `o9_institute_master`.`institute_id` = `o9_login`.`institute_id` "
			+ "where `o9_login`.`email` = ? and `o9_login`.`password` = ? and `o9_login`.`institute_id` = ?  ";

	String COUNT_SETS = "select count(*) as count from o9_question_set a left join o9_subject b on a.subject_id=b.subject_id where b.subject_name";
	/*--------------Harshit Queries--------------------*/
	String GET_SETS = "select a.set_id,a.set_name,b.subject_name from o9_question_set a left join o9_subject b on a.subject_id=b.subject_id where b.subject_name ";
	String ADD_SET = "insert into o9_instituteid_purchased_question(set_id,expire_time,purchased_by) values(?,?,?)";
	String ADD_SALE = "insert into o9_sales(package,sets,institute_id,email) values(?,?,?,?)";
	String TOTAL_POST = "select count(*) as count from o9_instituteid_post";
	String CREATE_ADVANCE_TEST = "insert ignore into o9_instituteid_test(test_name,test_duration,test_start_date,test_end_date,test_positive_mark,test_negative_mark,test_created_by_id,test_created_by_name,test_type) values(?,?,?,?,?,?,?,?,1)";
	String ADD_ADVANCE_TEST_QUESTION = "LOAD DATA LOCAL INFILE '?' REPLACE INTO TABLE o9_instituteid_test_doc_answer FIELDS TERMINATED BY '/' LINES TERMINATED BY ';' (test_id, question,correct_answer)";
	String ADVANCE_TEST_DOC = "insert into o9_instituteid_test_doc(test_id,paper_id,result_id) values(?,?,?)";
	
}
/* table create if not exists se karni hongi*/