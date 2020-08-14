drop table if exists t_user;
create table t_user(
	id			bigint auto_increment,
	loginName	varchar(255),
	loginPwd	varchar(255),
	realName	varchar(255),
	primary key(id)
);
	
insert into t_user(loginName,loginPwd,realName) values('zhangsan','123','zs');
insert into t_user(loginName,loginPwd,realName)  values('lisi','123','ls');
insert into t_user(loginName,loginPwd,realName)  values('wangwu','123','ww');
commit;
select * from t_user;
