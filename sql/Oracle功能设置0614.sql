--年度费用总结视图
create or replace view Year_Report_View
as 
select username,sum(water) WaterYear,sum(electricity) ElectrictyYear,sum(gas) GasYear, sum(heating) HeatingYear,sum(managementFee) ManagementFeeYear,sum(housePayment) HousePaymentYear 
from Property_Cost_Record 
group by username;

--查询未全缴费记录视图
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

--抄表后对当前费用改为最新
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

--对指定时间的所有业主需要缴费的总额计算并输出
set serverout on;
CREATE OR REPLACE PROCEDURE Total_Day(ptime date,ptotal out varchar2)
AS
	total_cost number;
	cost_record_row Property_Cost_Record%rowtype;
	cursor cur_cost is
		select *
		from Property_Cost_Record
		where to_char(time,'yyyy-mm-dd')=to_char(ptime,'yyyy-mm-dd');
BEGIN
	open cur_cost;
	loop
	fetch cur_cost into cost_record_row;
	exit when cur_cost %notfound;
	total_cost:=cost_record_row.water+cost_record_row.electricity+cost_record_row.gas+cost_record_row.heating+cost_record_row.managementFee+cost_record_row.housePayment;
	dbms_output.put_line('Username='||cost_record_row.username||'  时间：'||cost_record_row.time||'  需要缴费总额：'|| total_cost);
	ptotal:=(ptotal||'('||cost_record_row.username||','||cost_record_row.time||','||total_cost||');');
	end loop;
	dbms_output.put_line(ptotal);
	close cur_cost;
END;
/

CREATE OR REPLACE PROCEDURE Total_Year(ptime date,ptotal out varchar2)
AS
	total_cost number;
	cost_record_row Property_Cost_Record%rowtype;
	cursor cur_cost is
		select *
		from Property_Cost_Record
		where to_char(time,'yyyy')=to_char(ptime,'yyyy');
BEGIN
	open cur_cost;
	loop
	fetch cur_cost into cost_record_row;
	exit when cur_cost %notfound;
	total_cost:=cost_record_row.water+cost_record_row.electricity+cost_record_row.gas+cost_record_row.heating+cost_record_row.managementFee+cost_record_row.housePayment;
	dbms_output.put_line('Username='||cost_record_row.username||'  时间：'||cost_record_row.time||'  需要缴费总额：'|| total_cost);
	ptotal:=(ptotal||'('||cost_record_row.username||','||cost_record_row.time||','||total_cost||');');
	end loop;
	dbms_output.put_line(ptotal);
	close cur_cost;
END;
/

CREATE OR REPLACE PROCEDURE Total_Month(ptime date,ptotal out varchar2)
AS
	total_cost number;
	cost_record_row Property_Cost_Record%rowtype;
	cursor cur_cost is
		select *
		from Property_Cost_Record
		where to_char(time,'yyyy-mm')=to_char(ptime,'yyyy-mm');
BEGIN
	open cur_cost;
	loop
	fetch cur_cost into cost_record_row;
	exit when cur_cost %notfound;
	total_cost:=cost_record_row.water+cost_record_row.electricity+cost_record_row.gas+cost_record_row.heating+cost_record_row.managementFee+cost_record_row.housePayment;
	dbms_output.put_line('Username='||cost_record_row.username||'  时间：'||cost_record_row.time||'  需要缴费总额：'|| total_cost);
	ptotal:=(ptotal||'('||cost_record_row.username||','||cost_record_row.time||','||total_cost||');');
	end loop;
	dbms_output.put_line(ptotal);
	close cur_cost;
END;
/

--测试费用总额的过程
declare
	total varchar(4000);
begin
  	Total_Day(to_date('2020/4/30','yyyy-mm-dd'),total);
end;

