
INSERT INTO groups(id,name) VALUES (1,'admins');
INSERT INTO groups(id,name) VALUES (2,'users');

INSERT INTO authorities(id,name) VALUES (1,'admin');
INSERT INTO authorities(id,name) VALUES (2,'user');

INSERT INTO users(id,created_at,updated_at,username,nickname,password,enabled) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','admin','管理员','123654',TRUE );
INSERT INTO users(id,created_at,updated_at,username,nickname,password,enabled) VALUES (2,'2016-11-20 13:50:00','2016-11-20 13:50:00','chao','chao','123654',TRUE );
INSERT INTO users(id,created_at,updated_at,username,nickname,password,enabled) VALUES (3,'2016-11-20 13:50:00','2016-11-20 13:50:00','xin','xin','123654',FALSE );
INSERT INTO users(id,created_at,updated_at,username,nickname,password,enabled) VALUES (4,'2016-11-20 13:50:00','2016-11-20 13:50:00','qian','qian','123654',FALSE );

INSERT INTO group_members(id,username,group_id) VALUES (1,'admin',1);
INSERT INTO group_members(id,username,group_id) VALUES (2,'chao',1);
INSERT INTO group_members(id,username,group_id) VALUES (3,'xin',2);
INSERT INTO group_members(id,username,group_id) VALUES (4,'qian',2);

INSERT INTO group_authorities(id,group_id,authority_id) VALUES (1,1,1);
INSERT INTO group_authorities(id,group_id,authority_id) VALUES (2,2,2);

INSERT INTO blogs(id,created_at,updated_at,content,title,viewed_times,user_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fffffffff','wwww',0,2);

INSERT INTO blog_zan(id,blog_id,user_id) VALUES (1,1,2);

INSERT INTO blog_collect(id,blog_id,user_id) VALUES (1,1,2);

INSERT INTO comments(id,created_at,updated_at,content,user_id,blog_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fwefwefa',1,1);

INSERT INTO comment_zan(id,comment_id,user_id) VALUES (1,1,2);

INSERT INTO comment_cai(id,comment_id,user_id) VALUES (1,1,3);

INSERT INTO replies(id,created_at,updated_at,content,user_id,comment_id,reply_to_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fewfwaegfwaef',2,1,1);
