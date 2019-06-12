--建立数据库相关表之间的参照完整性约束
CREATE DATABASE SchoolBookManage

CREATE TABLE book
(
	图书编号   nvarchar(20)  NOT NULL,--编号
	图书名称   nvarchar(20)  NOT NULL,--书名
	类别   nvarchar(20)  NOT NULL,--类别
	作者 nvarchar(10) NOT NULL,--作者
	出版社   nvarchar(20) NOT NULL,--出版社
	出版日期 date NOT NULL,--出版日期
	总数 int NOT NULL,--总数
	在册数 int NOT NULL,--在册数
	PRIMARY KEY (图书编号)
)

CREATE TABLE borrowleaderboard
(
	图书编号 nvarchar(20) NOT NULL,
	借阅次数 int NOT NULL,
	PRIMARY KEY (图书编号),
	FOREIGN KEY (图书编号) REFERENCES book(图书编号) 	                    
		ON DELETE CASCADE	
		/*当删除book 表中的元组造成了与recording表不一致时拒绝删除*/
		ON UPDATE CASCADE
      	/*级联更新*/
)

CREATE TABLE student
(
	读者编号 nvarchar(20) NOT NULL,
	密码 nvarchar(20) NOT NULL,
	姓名 nvarchar(10) NOT NULL,
	性别 nvarchar(2) NOT NULL,
	学位 nvarchar(10) NOT NULL,
	学院 nvarchar(20) NOT NULL,
	联系电话 char(11) NOT NULL,
	可借书数 int NOT NULL,
	借书数 int NOT NULL,
	借阅期限 int NOT NULL,
	头像 IMAGE,
	是否冻结 bit,
	PRIMARY KEY (读者编号)
)

CREATE TABLE teacher
(
	读者编号 nvarchar(20) NOT NULL,
	密码 nvarchar(20) NOT NULL,
	姓名 nvarchar(10) NOT NULL,
	性别 nvarchar(2) NOT NULL,
	学院 nvarchar(20) NOT NULL,
	联系电话 char(11) NOT NULL,
	可借书数 int NOT NULL,
	借书数 int NOT NULL,
	借阅期限 int NOT NULL,
	头像 IMAGE,
	PRIMARY KEY (读者编号)
)

CREATE TABLE manager
(
	管理员编号 nvarchar(20) NOT NULL,
	密码 nvarchar(20) NOT NULL,
	姓名 nvarchar(10) NOT NULL,
	性别 nvarchar(2) NOT NULL,
	联系电话 char(11) NOT NULL,

	PRIMARY KEY (管理员编号)
)

CREATE TABLE recording
(
	读者编号 nvarchar(20) NOT NULL,
	图书编号 nvarchar(20) NOT NULL,
	借阅时间 datetime NOT NULL,
	读者类别 nvarchar(4),--教师、学生
	准备还书 bit NOT NULL,
	应还时间 datetime,
	还书时间 datetime,
	是否续借 bit NOT NULL,

	PRIMARY KEY (读者编号,图书编号,借阅时间), 				
    /*FOREIGN KEY (读者编号) REFERENCES student(读者编号) 
		ON DELETE NO ACTION
		/*当删除student 表中的元组造成了与recording表不一致时拒绝删除*/
        ON UPDATE CASCADE,    
		/*级联更新*/
	FOREIGN KEY (读者编号) REFERENCES teacher(读者编号)
		ON DELETE NO ACTION
		/*当删除student 表中的元组造成了与recording表不一致时拒绝删除*/
        ON UPDATE CASCADE,    
		/*级联更新*/*/
    FOREIGN KEY (图书编号) REFERENCES book(图书编号) 	                    
		ON DELETE NO ACTION 	
		/*当删除book 表中的元组造成了与recording表不一致时拒绝删除*/
		ON UPDATE CASCADE
      	/*级联更新*/
);
CREATE TABLE studentrecording
(
	读者编号 nvarchar(20) NOT NULL,
	图书编号 nvarchar(20) NOT NULL,
	借阅时间 datetime NOT NULL,
	准备还书 bit NOT NULL,
	应还时间 datetime,
	还书时间 datetime,
	是否续借 bit NOT NULL,

	PRIMARY KEY (读者编号,图书编号,借阅时间),
    FOREIGN KEY (读者编号) REFERENCES student(读者编号) 
		ON DELETE NO ACTION
		/*当删除student 表中的元组造成了与recording表不一致时拒绝删除*/
        ON UPDATE CASCADE,
		/*级联更新*/
    FOREIGN KEY (图书编号) REFERENCES book(图书编号) 	                    
		ON DELETE NO ACTION 	
		/*当删除book 表中的元组造成了与recording表不一致时拒绝删除*/
		ON UPDATE CASCADE
);
CREATE TABLE teacherrecording
(
	读者编号 nvarchar(20) NOT NULL,
	图书编号 nvarchar(20) NOT NULL,
	借阅时间 datetime NOT NULL,
	准备还书 bit NOT NULL,
	应还时间 datetime,
	还书时间 datetime,
	是否续借 bit NOT NULL,

	PRIMARY KEY (读者编号,图书编号,借阅时间), 				
	FOREIGN KEY (读者编号) REFERENCES teacher(读者编号)
		ON DELETE NO ACTION
		/*当删除teacher 表中的元组造成了与recording表不一致时拒绝删除*/
        ON UPDATE CASCADE,    
		/*级联更新*/
    FOREIGN KEY (图书编号) REFERENCES book(图书编号) 	                    
		ON DELETE NO ACTION 	
		/*当删除book 表中的元组造成了与recording表不一致时拒绝删除*/
		ON UPDATE CASCADE
      	/*级联更新*/
);

CREATE TABLE studentfine
(
	读者编号 nvarchar(20) NOT NULL,
	图书编号 nvarchar(20) NOT NULL,
	借阅时间 datetime NOT NULL,
	超期 smallint ,
	罚金 float,
	是否缴费 bit,

	PRIMARY KEY (读者编号,图书编号),
	FOREIGN KEY (读者编号,图书编号,借阅时间) REFERENCES studentrecording(读者编号,图书编号,借阅时间) 
		ON DELETE NO ACTION
        ON UPDATE CASCADE, 
)
CREATE TABLE teacherfine
(
	读者编号 nvarchar(20) NOT NULL,
	图书编号 nvarchar(20) NOT NULL,
	借阅时间 datetime NOT NULL,
	超期 smallint ,
	罚金 float,
	是否缴费 bit,

	PRIMARY KEY (读者编号,图书编号),
	FOREIGN KEY (读者编号,图书编号,借阅时间) REFERENCES teacherrecording(读者编号,图书编号,借阅时间) 
		ON DELETE NO ACTION
        ON UPDATE CASCADE, 
)
CREATE TABLE seat
(
	所在楼层 smallint NOT NULL,
	编号 smallint NOT NULL,
	读者编号 nvarchar(20) 

	PRIMARY KEY (所在楼层,编号),
	FOREIGN KEY (读者编号) REFERENCES student(读者编号) 
		ON DELETE NO ACTION
		/*当删除reader 表中的元组造成了与seat表不一致时拒绝删除*/
        ON UPDATE CASCADE,    
		/*级联更新*/
)
/*触发器区*/
--创建触发器，分别实现借书和还书时自动更新图书信息的在册数量,和续借,用deleted和inserted区分
CREATE TRIGGER studentreturn_trigger
ON studentrecording
FOR UPDATE
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20),@borrowTime datetime,@returnTime datetime,@shouldReturnTime datetime,@renew bit
	select @renew =deleted.是否续借
	from deleted

	select @returnTime = inserted.还书时间
	from inserted
		
	SELECT @booknumber = deleted.图书编号,@readerNumber=deleted.读者编号,@shouldReturnTime=deleted.应还时间,@borrowTime=deleted.借阅时间
	FROM deleted

	if(@returnTime is not null)--还书
	begin
		--书更改
		UPDATE book
		SET 在册数=在册数+1
		WHERE 图书编号=@booknumber
		--readyReturn归0
		UPDATE studentrecording
		SET 准备还书 = 0
		WHERE 读者编号=@readerNumber and 图书编号=@booknumber
		--读者更改
		UPDATE student
		set 可借书数=可借书数+1,借书数=借书数-1
		WHERE 读者编号=@readerNumber

		--计算罚金
		IF(DATEDIFF ( DAY , @shouldReturnTime , @returnTime )>0)
		BEGIN
			declare @overDate int,@money float
			set @overDate=DATEDIFF ( DAY , @shouldReturnTime , @returnTime )
			set @money=@overDate*0.5
			insert into studentfine values(@readerNumber,@booknumber,@borrowTime,@overDate,@money,0)
			--冻结账户
			UPDATE student
			set 是否冻结=1
			where 读者编号=@readerNumber
		END	
	end
	if(@returnTime is null and @renew = 0)--没续借过
	Begin
		select @renew = inserted.是否续借
		from inserted
		if(@renew = 1)--续借
		begin
 			UPDATE studentrecording
			set 应还时间=DATEADD(day,30,@shouldReturnTime)--datetime类型不可以
			where 读者编号=@readerNumber and 图书编号=@booknumber
		end
	end

CREATE TRIGGER teacherreturn_trigger
ON teacherrecording
FOR UPDATE
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20),@borrowTime datetime,@returnTime datetime,@shouldReturnTime datetime,@renew bit
	select @renew =deleted.是否续借
	from deleted

	select @returnTime = inserted.还书时间
	from inserted
		
	SELECT @booknumber = deleted.图书编号,@readerNumber=deleted.读者编号,@shouldReturnTime=deleted.应还时间,@borrowTime=deleted.借阅时间
	FROM deleted

	if(@returnTime is not null)--还书
	begin
		--书更改
		UPDATE book
		SET 在册数=在册数+1
		WHERE 图书编号=@booknumber
		--readyReturn归0
		UPDATE teacherrecording
		SET 准备还书 = 0
		WHERE 读者编号=@readerNumber and 图书编号=@booknumber
		--读者更改
		UPDATE teacher
		set 可借书数=可借书数+1,借书数=借书数-1
		WHERE 读者编号=@readerNumber
		--计算罚金
		IF(DATEDIFF ( DAY , @shouldReturnTime , @returnTime )>0)
		BEGIN
			declare @overDate int,@money float
			set @overDate=DATEDIFF ( DAY , @shouldReturnTime , @returnTime )
			set @money=@overDate*0.5
			insert into teacherfine values(@readerNumber,@booknumber,@borrowTime,@overDate,@money,0)
			--冻结账户
			UPDATE teacher
			set 是否冻结=1
			where 读者编号=@readerNumber
		END	
	end
	if(@returnTime is null and @renew = 0)--没续借过
	Begin
		select @renew = inserted.是否续借
		from inserted
		if(@renew = 1)--续借
		begin
 			UPDATE teacherrecording
			set 应还时间=DATEADD(day,30,@shouldReturnTime)--datetime类型不可以
			where 读者编号=@readerNumber and 图书编号=@booknumber
		end
	end

CREATE TRIGGER studentborrow_trigger--INSERT INTO recording(读者编号,图书编号) VALUES (?, ?)
ON  studentrecording
FOR INSERT
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20),@readerCategory nvarchar(4)
	SELECT @booknumber = inserted.图书编号,@readerNumber=inserted.读者编号
	FROM inserted

	select @readerCategory=student.学位
	from student
	where student.读者编号=@readerNumber

	UPDATE book
	SET 在册数=在册数-1
	WHERE 图书编号=@booknumber;

	--设置排行榜
	if((select count(*)
		from borrowleaderboard
		where borrowleaderboard.图书编号=@booknumber
		))>0
	begin
		UPDATE borrowleaderboard
		set 借阅次数=借阅次数+1
		where borrowleaderboard.图书编号=@booknumber
	end
	else
	begin
		INSERT INTO borrowleaderboard VALUES (@booknumber,'1')
	end
	--更新学生表,更新应还日期
	IF(@readerCategory = '本科生')
		begin
			--更新学生表
			UPDATE student
			set 可借书数=可借书数-1,借书数=借书数+1
			WHERE 读者编号=@readerNumber
			--更新应还日期，本科生30
			UPDATE studentrecording
			set 应还时间=DATEADD(day,30,GETDATE())
			WHERE 读者编号=@readerNumber and 图书编号=@booknumber
		end
	IF(@readerCategory = '硕士' or @readerCategory = '博士')
		begin
			--更新学生表
			UPDATE student
			set 可借书数=可借书数-1,借书数=借书数+1
			WHERE 读者编号=@readerNumber
			--更新应还日期，硕博60
			UPDATE studentrecording
			set 应还时间=DATEADD(day,60,GETDATE())
			WHERE 读者编号=@readerNumber and 图书编号=@booknumber
		end


CREATE TRIGGER teacherborrow_trigger--INSERT INTO recording(读者编号,图书编号) VALUES (?, ?)
ON  teacherrecording
FOR INSERT
AS
	DECLARE @booknumber nvarchar(20),@readerNumber nvarchar(20)
	SELECT @booknumber = inserted.图书编号,@readerNumber=inserted.读者编号
	FROM inserted

	UPDATE book
	SET 在册数=在册数-1
	WHERE 图书编号=@booknumber;

	--设置排行榜
	if((select count(*)
		from borrowleaderboard
		where borrowleaderboard.图书编号=@booknumber
		))>0
	begin
		UPDATE borrowleaderboard
		set 借阅次数=借阅次数+1
		where borrowleaderboard.图书编号=@booknumber
	end
	else
	begin
		INSERT INTO borrowleaderboard VALUES (@booknumber,'1')
	end

	UPDATE teacher
	set 可借书数=可借书数-1,借书数=借书数+1
	WHERE 读者编号=@readerNumber
	--更新应还日期，教师180天
	UPDATE teacherrecording
	set 应还时间=DATEADD(day,180,GETDATE())
	WHERE 读者编号=@readerNumber and 图书编号=@booknumber

drop TRIGGER teacherreturn_trigger
drop TRIGGER studentreturn_trigger

drop TRIGGER borrow_trigger

--使用触发器缴纳罚金
CREATE TRIGGER paystudentfine_trigger
ON studentfine
FOR UPDATE
AS
	DECLARE @readerNumber nvarchar(20)
	SELECT @readerNumber=inserted.读者编号
	FROM inserted

	update student
	set 是否冻结=0
	where student.读者编号=@readerNumber

Update studentfine set 是否缴费=1 where 读者编号='201700800370'

CREATE TRIGGER payteacherfine_trigger
ON teacherfine
FOR UPDATE
AS
	DECLARE @readerNumber nvarchar(20)
	SELECT @readerNumber=inserted.读者编号
	FROM inserted

	update teacher
	set 是否冻结=0
	where teacher.读者编号=@readerNumber

--创建视图查询各种图书的书号、书名、总数和在册数
CREATE VIEW book_view (图书编号,图书名称,总数,在册数)
as
SELECT 图书编号,图书名称,总数,在册数
FROM book

select * from book_view

--创建视图查看排行榜
CREATE VIEW leaderboard_view (图书编号,图书名称,类别,作者,出版社,借阅次数)
as
SELECT TOP 100 PERCENT book.图书编号,图书名称,类别,作者,出版社,借阅次数
FROM book,borrowleaderboard
where book.图书编号=borrowleaderboard.图书编号
order by 借阅次数

select * from leaderboard_view

drop  VIEW leaderboard_view
--创建存储过程查询指定读者借阅图书的情况
CREATE PROCEDURE studentborrow @readernumber nvarchar(20)
AS 
	SELECT studentrecording.读者编号,姓名,book.图书编号,book.图书名称,studentrecording.应还时间
	FROM student,studentrecording,book
	WHERE student.读者编号 = studentrecording.读者编号 AND student.读者编号=@readernumber and studentrecording.图书编号=book.图书编号 and studentrecording.还书时间 is null

CREATE PROCEDURE teacherborrow @readernumber nvarchar(20)
AS 
	SELECT teacherrecording.读者编号,姓名,book.图书编号,book.图书名称,teacherrecording.应还时间
	FROM teacher,teacherrecording,book
	WHERE teacher.读者编号 = teacherrecording.读者编号 AND teacher.读者编号=@readernumber and teacherrecording.图书编号=book.图书编号 and teacherrecording.还书时间 is null


--默认学生可借书数为20
ALTER TABLE student
ADD CONSTRAINT borrow1 DEFAULT 20 FOR 可借书数
--默认老师可借书数为30
ALTER TABLE teacher
ADD CONSTRAINT borrow2 DEFAULT 30 FOR 可借书数

--默认借书数为0
ALTER TABLE student
ADD CONSTRAINT borrowed1 DEFAULT 0 FOR 借书数

ALTER TABLE teacher
ADD CONSTRAINT borrowed2 DEFAULT 0 FOR 借书数
--默认借书时间为当前时间
ALTER TABLE studentrecording
ADD CONSTRAINT borrowtime1 DEFAULT GETDATE() FOR 借阅时间

ALTER TABLE teacherrecording
ADD CONSTRAINT borrowtime2 DEFAULT GETDATE() FOR 借阅时间
--默认冻结为0
ALTER TABLE student
ADD CONSTRAINT studentfreeze DEFAULT 0 FOR 是否冻结

ALTER TABLE teacher
ADD CONSTRAINT teacherfreeze DEFAULT 0 FOR 是否冻结
--默认准备还书为0
ALTER TABLE studentrecording
ADD CONSTRAINT readyreturn1 DEFAULT 0 FOR 准备还书

ALTER TABLE teacherrecording
ADD CONSTRAINT readyreturn2 DEFAULT 0 FOR 准备还书
--默认是否续借为0
ALTER TABLE studentrecording
ADD CONSTRAINT renew1 DEFAULT 0 FOR 是否续借

ALTER TABLE teacherrecording
ADD CONSTRAINT renew2 DEFAULT 0 FOR 是否续借


--插入新书在册数等于总数
CREATE TRIGGER booknumber_trigger
ON  book
FOR INSERT
AS
	DECLARE @exist int,@total int
	SELECT @exist = inserted.在册数,
		   @total = inserted.在册数
	FROM inserted
	IF(@exist is NULL)--在册数默认为总数
	BEGIN
		DECLARE @number nchar(20)
		SELECT @number = inserted.图书编号
		FROM inserted

		UPDATE book
		SET 在册数 = @total
		WHERE 图书编号=@number
	END
	IF(@exist is not NULL and @exist <> @total)--在册数不等于总数报错
	BEGIN
		PRINT '在册数应与总数一致'
		ROLLBACK
	END
	


CREATE PROCEDURE setseat 
   @floor AS smallint,@number AS smallint
AS
while @floor<=5
BEGIN
	while @number<=50
	BEGIN
		INSERT INTO seat  VALUES (@floor, @number,null)
		set @number = @number+1
	END
	set @floor = @floor+1
	set @number = 1
END

exec setseat 1,1
