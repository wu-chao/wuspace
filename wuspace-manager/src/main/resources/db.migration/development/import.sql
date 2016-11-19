INSERT INTO user(id,created_at,updated_at,nickname,password) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','管理员','123654');
INSERT INTO user(id,created_at,updated_at,nickname,password) VALUES (2,'2016-11-20 13:50:00','2016-11-20 13:50:00','chao','123654');
INSERT INTO user(id,created_at,updated_at,nickname,password) VALUES (3,'2016-11-20 13:50:00','2016-11-20 13:50:00','xin','123654');
INSERT INTO user(id,created_at,updated_at,nickname,password) VALUES (4,'2016-11-20 13:50:00','2016-11-20 13:50:00','qian','123654');

INSERT INTO blog(id,created_at,updated_at,content,title,viewed_times,user_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fffffffff','wwww',0,1);

INSERT INTO comment(id,created_at,updated_at,content,user_id,blog_id,zan_user_id,cai_user_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fwefwefa',1,1,3,4);

INSERT INTO reply(id,created_at,updated_at,content,user_id,comment_id,reply_to_id) VALUES (1,'2016-11-20 13:50:00','2016-11-20 13:50:00','fewfwaegfwaef',2,1,1);
