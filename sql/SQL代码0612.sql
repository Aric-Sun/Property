drop table Property_User;
--用户信息表
Create table Property_User(
	username varchar(30) primary key,
	password varchar(30),
	IDNumber varchar(30),
	buildNo varchar(30),
	roomNo varchar(30),
	name varchar(30),
	jobUnit varchar(30),
	phone varchar(30),
	--建筑面积
	floorage varchar(30)
); 
insert into Property_User values('001','001','123456781','1','301','孙同学','南京市XX','13013013011','301m*301m');
insert into Property_User values('002','002','123456782','2','302','荣同学','北京市XX','13013013012','302m*302m');
insert into Property_User values('003','003','123456783','3','303','沈同学','天津市XX','13013013013','303m*303m');
insert into Property_User values('004','004','123456784','4','304','邱同学','苏州市XX','13013013014','304m*304m');
insert into Property_User values('005','005','123456785','5','305','陈同学','莆田市XX','13013013015','305m*305m');

drop table Property_Manager;
--管理员信息表
Create table Property_Manager(
	username varchar(30) primary key,
	password varchar(30),
	name varchar(30),
	phone varchar(30)
);
insert into Property_Manager values('101','101','孙管理','13013013011'); 
insert into Property_Manager values('102','102','荣管理','13013013012'); 
insert into Property_Manager values('103','103','沈管理','13013013013'); 
insert into Property_Manager values('104','104','邱管理','13013013014'); 
insert into Property_Manager values('105','105','陈管理','13013013015'); 

drop table Property_Current_Cost;
--最新全体业主费用情况
Create table Property_Current_Cost(
	username varchar(30),
	water varchar(30),
	electricity varchar(30),
	gas varchar(30),
	heating varchar(30),
	managementFee varchar(30),
	housePayment varchar(30),
	foreign key(username) references Property_User(username)
);
insert into Property_Current_Cost values('001','11.1','11.2','11.3','11.4','11.5','11.6');
insert into Property_Current_Cost values('002','21.1','21.2','21.3','21.4','21.5','21.6');
insert into Property_Current_Cost values('003','31.1','31.2','31.3','31.4','31.5','31.6');
insert into Property_Current_Cost values('004','41.1','41.2','41.3','41.4','41.5','41.6');
insert into Property_Current_Cost values('005','51.1','51.2','51.3','51.4','51.5','51.6');

CREATE OR REPLACE TRIGGER Tri_Current_Zero
before INSERT ON Property_Cost_Record
FOR EACH ROW
DECLARE 
BEGIN
	update Property_Current_Cost
	set water=:new.water,electricity=:new.electricity,gas=:new.gas,heating=:new.heating,managementFee=:new.managementFee,housePayment=:new.housePayment
	where username=:new.username;
END Tri_Current_Zero;
/


drop table Property_Cost_Record;
--抄表记录，
Create table Property_Cost_Record(
	username varchar(30),
	time date, --多条时间记录对应一个username
	water varchar(30),
	electricity varchar(30),
	gas varchar(30),
	heating varchar(30),
	managementFee varchar(30),
	housePayment varchar(30),
	foreign key(username) references Property_User(username)
);
insert into Property_Cost_Record values('001',to_date('2020-4-30','yyyy-mm-dd'),'11.1','11.2','11.3','11.4','11.5','11.6');
insert into Property_Cost_Record values('002',to_date('2020-4-30','yyyy-mm-dd'),'21.1','21.2','21.3','21.4','21.5','21.6');
insert into Property_Cost_Record values('003',to_date('2020-4-30','yyyy-mm-dd'),'31.1','31.2','31.3','31.4','31.5','31.6');
insert into Property_Cost_Record values('004',to_date('2020-4-30','yyyy-mm-dd'),'41.1','41.2','41.3','41.4','41.5','41.6');
insert into Property_Cost_Record values('005',to_date('2020-4-30','yyyy-mm-dd'),'51.1','51.2','51.3','51.4','51.5','51.6');
insert into Property_Cost_Record values('001',to_date('2020-5-31','yyyy-mm-dd'),'11.11','11.22','11.33','11.44','11.55','11.66');
insert into Property_Cost_Record values('002',to_date('2020-5-31','yyyy-mm-dd'),'21.11','21.22','21.33','21.44','21.55','21.66');
insert into Property_Cost_Record values('003',to_date('2020-5-31','yyyy-mm-dd'),'31.11','31.22','31.33','31.44','31.55','31.66');
insert into Property_Cost_Record values('004',to_date('2020-5-31','yyyy-mm-dd'),'41.11','41.22','41.33','41.44','41.55','31.66');
insert into Property_Cost_Record values('005',to_date('2020-5-31','yyyy-mm-dd'),'51.11','51.22','51.33','51.44','51.55','41.66');

drop table Property_Payment_Record;
--缴费记录，全体业主，一个人对应多条时间
Create table Property_Payment_Record(
	username varchar(30),
	time date,
	water number,
	electricity number,
	gas number,
	heating number,
	managementFee number,
	housePayment number,
	foreign key(username) references Property_User(username)
);
insert into Property_Payment_Record values('001',to_date('2020-4-30','yyyy-mm-dd'),'1','1','1','1','1','0');
insert into Property_Payment_Record values('002',to_date('2020-4-30','yyyy-mm-dd'),'1','1','1','1','0','0');
insert into Property_Payment_Record values('003',to_date('2020-4-30','yyyy-mm-dd'),'1','1','1','0','1','0');
insert into Property_Payment_Record values('004',to_date('2020-4-30','yyyy-mm-dd'),'1','1','0','1','1','0');
insert into Property_Payment_Record values('005',to_date('2020-4-30','yyyy-mm-dd'),'1','0','1','1','1','0');
insert into Property_Payment_Record values('001',to_date('2020-5-31','yyyy-mm-dd'),'1','0','1','1','1','0');
insert into Property_Payment_Record values('002',to_date('2020-5-31','yyyy-mm-dd'),'1','1','0','1','1','0');
insert into Property_Payment_Record values('003',to_date('2020-5-31','yyyy-mm-dd'),'1','1','1','0','1','0');
insert into Property_Payment_Record values('004',to_date('2020-5-31','yyyy-mm-dd'),'1','1','1','1','0','0');
insert into Property_Payment_Record values('005',to_date('2020-5-31','yyyy-mm-dd'),'1','1','1','1','1','1');