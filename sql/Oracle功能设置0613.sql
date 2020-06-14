--年度费用总结视图
create or replace view Year_Report_View
as 
select username,sum(water) WaterYear,sum(electricity) ElectrictyYear,sum(gas) GasYear, sum(heating) HeatingYear,sum(managementFee) ManagementFeeYear,sum(housePayment) HousePaymentYear 
from Property_Cost_Record 
group by username;

--查询未全缴费记录视图,管理员 
create or replace view Unpaid_View
as
select * from Property_Payment_Record where water=0 or electricity=0 or gas=0 or heating=0 or managementFee=0 or housePayment=0 order by username;

--缴费中各字段大量值为1,0的位图索引
CREATE BITMAP INDEX Payment_Record_Index ON Property_Payment_Record(water,electricity,gas,heating,managementFee,housePayment);

--对常用排序字段创建b树索引
CREATE INDEX Property_Username_Index ON Property_User(name);

CREATE INDEX Property_Cost_Record_Index ON Property_Cost_Record(time);

CREATE INDEX Property_Payment_Record_Index ON Property_Payment_Record(time);

--Property_Cost_Record表中时间字段为null时自动为其获取当前时间
CREATE OR REPLACE TRIGGER Tri_Time_Null_Cost
before INSERT or update ON Property_Cost_Record
FOR EACH ROW
when(new.time is null)
DECLARE 
currentTime date;
BEGIN
select to_char(sysdate,'yyyy-mm-dd') into :new.time from dual;
END Tri_Time_Null_Cost;
/

--Property_Payment_Record表中时间字段为null时自动为其获取当前时间
CREATE OR REPLACE TRIGGER Tri_Time_Null_Payment
before INSERT or update ON Property_Cost_Record
FOR EACH ROW
when(new.time is null)
DECLARE 
currentTime date;
BEGIN
select to_char(sysdate,'yyyy-mm-dd') into :new.time from dual;
END Tri_Time_Null_Cost;
/
--origin for backup
--对指定时间的所有业主需要缴费的总额计算并输出
set serverout on;
CREATE OR REPLACE PROCEDURE Total(ptime date)
AS
	total_cost number;
	cost_record_row Property_Cost_Record%rowtype;
	cursor cur_cost is
		select *
		from Property_Cost_Record
		where time=ptime;
BEGIN
	open cur_cost;
	loop
	fetch cur_cost into cost_record_row;
	exit when cur_cost %notfound;
	total_cost:=cost_record_row.water+cost_record_row.electricity+cost_record_row.gas+cost_record_row.heating+cost_record_row.managementFee+cost_record_row.housePayment;
	dbms_output.put_line('Username='||cost_record_row.username||'  时间：'||cost_record_row.time||'  需要缴费总额：'|| total_cost);
	end loop;
	close cur_cost;
END;
/

--对指定时间的所有业主需要缴费的总额计算并输出
create table Property_Cost_total(
	month varchar2(16),
	username varchar2(30),
	total_cost number,
	foreign key(username) references Property_User(username)
);

set serverout on;
CREATE OR REPLACE PROCEDURE Total(
	ptime varchar2,
	out_return out sys_refcursor
	)
AS
	pUsername Property_User.username%type;
	total_cost number;
	cost_record_row Property_Cost_Record%rowtype;
	cursor cur_cost is
		select *
		from Property_Cost_Record
		where to_char(time,'yyyy-mm')=to_char(to_date(ptime,'yyyy-mm'),'yyyy-mm');
BEGIN
	open cur_cost;
	delete from Property_Cost_total;
	loop
	fetch cur_cost into cost_record_row;
	exit when cur_cost %notfound;
	total_cost:=cost_record_row.water+cost_record_row.electricity+cost_record_row.gas+cost_record_row.heating+cost_record_row.managementFee+cost_record_row.housePayment;
	-- dbms_output.put_line('Username='||cost_record_row.username||'  时间：'||cost_record_row.time||'  需要缴费总额：'|| total_cost);
	insert into Property_Cost_total values(to_char(to_date(ptime,'yyyy-mm'),'yyyy-mm'), cost_record_row.username, total_cost);
	end loop;
	commit;
	open out_return for 'select * from Property_Cost_total';
	close cur_cost;
END;
/

-- declare
-- 	ptime varchar2(8);
-- 	pUsername Property_User.username%type;
-- 	total_cost number;
-- begin
-- 	ptime:='2020-4';
-- 	total(ptime,pUsername,total_cost);
-- 	dbms_output.put_line('Username='||pUsername||'  时间：'||ptime||'  需要缴费总额：'|| total_cost);
-- end;
-- /

declare 
	ptime varchar2(8);
	cur_cost_return sys_refcursor;
	cur Property_Cost_total%rowtype;
begin
	-- ptime:='2020-4';
	-- Total(ptime,cur_cost_return);
	-- for cur in cur_cost_return loop
	-- 	exit when cur_cost_return%notfound;
	-- 	dbms_output.put_line('姓名：'||cur.username||'时间：'||cur.month||'总额：'||cur.total_cost);
	-- end loop;
	ptime:='2020-4';
	Total(ptime,cur_cost_return);
	loop
		fetch cur_cost_return into cur;
		exit when cur_cost_return%notfound;
		dbms_output.put_line('姓名：'||cur.username||'时间：'||cur.month||'总额：'||cur.total_cost);
	end loop;
	close cur_cost_return;
end;
/

--查询年度汇总 需要缴费的总额 管理员
CREATE OR REPLACE PROCEDURE Total_Year(
	ptime varchar2,
	out_return out sys_refcursor
	)
AS
	pUsername Property_User.username%type;
	total_cost number;
	cost_record_row Property_Cost_Record%rowtype;
	cursor cur_cost is
		select *
		from Property_Cost_Record
		where to_char(time,'yyyy')=to_char(to_date(ptime,'yyyy'),'yyyy');
BEGIN
	open cur_cost;
	delete from Property_Cost_total;
	loop
	fetch cur_cost into cost_record_row;
	exit when cur_cost %notfound;
	total_cost:=cost_record_row.water+cost_record_row.electricity+cost_record_row.gas+cost_record_row.heating+cost_record_row.managementFee+cost_record_row.housePayment;
	insert into Property_Cost_total values(to_char(to_date(ptime,'yyyy'),'yyyy'), cost_record_row.username, total_cost);
	end loop;
	commit;
	open out_return for 'select username,month year,sum(total_cost) total_cost from Property_Cost_total group by username,month';
	close cur_cost;
END;
/
