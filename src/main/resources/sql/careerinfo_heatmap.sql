CREATE TABLE job(
	jid INT PRIMARY KEY AUTO_INCREMENT,
	-- 公司名称
	cname VARCHAR(30) NOT NULL,
	-- 职业名称
	jname VARCHAR(30) NOT NULL,
	-- 经度
	lon DOUBLE NOT NULL,
	-- 纬度
	lat DOUBLE NOT NULL,
	-- 最低工资
	minwage DOUBLE NOT NULL,
	-- 最高工资
	maxwage DOUBLE NOT NULL,
	-- 公司省市位置
	province VARCHAR(10),
	-- 职业亮点
	highlights VARCHAR(255),
	-- 工作经验要求
	erequir VARCHAR(100),
	-- 公司简介
	cintroduction TEXT,
	-- 职业描述
	jintroduction TEXT
);

-- DROP TABLE job;

-- SELECT jintroduction FROM job WHERE jid = 1;

CREATE TABLE user(
	uid INT PRIMARY KEY AUTO_INCREMENT,
	-- 	用户名
	username VARCHAR(20) NOT NULL,
	-- 密码
	password VARCHAR(20) NOT NULL,
	-- 邮箱
	email VARCHAR(20),
	-- 手机号码
	phone VARCHAR(20),
	-- 性别
	gender VARCHAR(1),
	-- 生日
	birthday DATE,
	-- 头像地址
	avatar VARCHAR(255)
);

INSERT INTO user VALUES(NULL,'xgblack','123456','gmg@xgblack.cn','15700001111','男','1997-09-01');

UPDATE user SET birthday = '1997-09-01'WHERE uid = 1;

SELECT * FROM user;

SELECT * FROM user WHERE username = 'xgblack' AND password = '123456';

SELECT uid,username,password,email,phone,gender,birthday,avatar FROM user
WHERE username = 'xgblack' AND password = '123456';

SELECT * FROM job LIMIT 5,15;

SELECT COUNT(*) FROM job WHERE 1 = 1;

SELECT * FROM job WHERE 1 = 1 AND minwage >= 4000 AND jname LIKE '%java%';

SELECT * FROM job WHERE 1 = 1  AND jname LIKE '%python%';

SELECT jid,lat,lon,minwage,maxwage FROM job ;

SELECT lat ,lon AS lng,minwage AS count FROM job;

SELECT lat ,lon AS lng,((minwage + maxwage) / 2 ) AS count FROM job;

SELECT lat ,lon AS lng,minwage AS count FROM job WHERE 1 = 1 AND province LIKE '%北京%';

SELECT jid,lat,lon,minwage,maxwage FROM job LIMIT 12,10;


	