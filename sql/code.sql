create table Property_User(
	id int primary key,
	username varchar2(32) unique not null,
	password varchar2(32) not null
);

insert into Property_User values(1,'superbaby','123');

select to_date('2020-4', 'yyyy-mm') from dual;
select to_char(to_date('2020-4', 'yyyy-mm'), 'yyyy-mm') from dual 
where to_char(sysdate,'yyyy-mm')=to_char(to_date('2020-6','yyyy-mm'),'yyyy-mm');