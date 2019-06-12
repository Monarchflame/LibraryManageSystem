package manager.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import book.dao.Book;
import database.ConnectDatabase;
import database.JtableUpdate;
import database.Model;
import main.main;
import manager.business.Interface_manager;
import manager.ui.AddBook_JDialog;
import manager.ui.AlterBook_JDialog;
import manager.ui.BookInfo_JFrame;
import manager.ui.ReaderInfo_JFrame;
import reader.dao.Student;

public class Manager implements Interface_manager
{
	public String number;//工作号
	public String name;//姓名
	public String gender;
	public String phonenumber;//电话
	private ConnectDatabase condata=new ConnectDatabase();
	public Manager(String number,String name,String gender,String phonenumber) 
	{
		this.number=number;
		this.name=name;
		this.gender=gender;
		this.phonenumber=phonenumber;
	}
	@Override
	public Model searchBook(String bookName)
	{
		String sql="select * from book where 图书名称=?";
		String []paras= {bookName};
		if(bookName.equals(""))
		{
			sql="select * from book";
			paras = null;
		}
		return JtableUpdate.jtableUpdate_query(null, sql, paras);
	}

	@Override
	public void outEveryBook() 
	{
		// TODO 自动生成的方法存根
		
	}
	@Override
	public boolean deleteBook(String bookNumber) 
	{
		try 
		{
			String sql="delete from book where 图书编号=?";
			String []paras = {bookNumber};
			return condata.cudExecute(sql,paras);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean addBook(Book book,int total) 
	{
		if (!book.bookNumber.equals("") && !book.bookName.equals("") && !book.category.equals("") && !book.author.equals("") && !book.publishingHouse.equals("") && !book.publicationDate.equals("") && total!=0)
		{
			try 
			{
				String sql="insert into book values (?,?,?,?,?,?,?,?)";
				String []paras = {book.bookNumber,book.bookName,book.category,book.author,book.publishingHouse,book.publicationDate,total+"",total+""};
				boolean rs=condata.cudExecute(sql,paras);
				return rs;
			} catch (Exception e) {
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	@Override
	public Model alterBook(String oldBookNumber,Book newBook)
	{
		if (oldBookNumber==null && newBook==null)//直接点击按钮
		{
			String sql="select * from book";
			String []paras = null;
			return JtableUpdate.jtableUpdate_query(null, sql, paras);
		}
		else if (oldBookNumber==null && newBook!=null)
		{
			String sql="select * from book where 图书编号=?";
			String []paras = {newBook.bookNumber};
			return JtableUpdate.jtableUpdate_query(null, sql, paras);
		}
		else if (!newBook.bookNumber.equals("") && !newBook.bookName.equals("") && !newBook.category.equals("") && !newBook.author.equals("") && !newBook.publishingHouse.equals("") && !newBook.publicationDate.equals(""))
		{
			try 
			{
				newBook.category=newBook.category.split(" ")[1];
				String sql="UPDATE book SET 图书编号=? ,图书名称=? ,类别=? ,作者=? ,出版社=? ,出版日期=? where 图书编号=? ";
				String []paras = {newBook.bookNumber,newBook.bookName,newBook.category,newBook.author,newBook.publishingHouse,newBook.publicationDate,oldBookNumber};
				boolean rs=condata.cudExecute(sql,paras);
				if (rs) {
					JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					//sql为update不可以直接jtableUpdate
					sql = "select * from book";
					return JtableUpdate.jtableUpdate_query(null, sql, null);
				}
				else {
					JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else
		{
			String sql="select * from book where 图书编号=?";
			String []paras = {newBook.bookNumber};
			return JtableUpdate.jtableUpdate_query(null, sql, paras);
		}
		return null;
	}
	@Override
	public boolean asBook(String bookNumber, int bookChange) 
	{
		String sql="UPDATE book set 在册数=在册数+"+bookChange+""+",总数=总数+"+bookChange+""+" where 图书编号=?";
		String []paras= {bookNumber};
		boolean ju = condata.cudExecute(sql,paras);
		return ju;
	}
	@Override
	public void returnBook(String bookNumber, String readerNumber) 
	{
		String sql1="UPDATE studentrecording set 还书时间 = GETDATE() WHERE 读者编号=? and 图书编号=? and 还书时间 is null";
		String []paras= {readerNumber,bookNumber};
		boolean ju1 = condata.cudExecute(sql1,paras);
		String sql2="UPDATE teacherrecording set 还书时间 = GETDATE() WHERE 读者编号=? and 图书编号=? and 还书时间 is null";
		boolean ju2 = condata.cudExecute(sql2,paras);
		if (ju1) 
		{
			String sql = "select 超期,罚金 from studentfine WHERE 读者编号=? and 图书编号=? and 是否缴费 = 0";
			ResultSet rs = condata.queryExecute(sql, paras);
			try {
				if (rs.next()) //超期
				{
					String overDate = rs.getString(1);
					String fine = rs.getString(2);
					JOptionPane.showMessageDialog(null, "借书超期"+overDate+"天,请交纳"+fine+"元罚金", "提示", JOptionPane.INFORMATION_MESSAGE);
					if (main.student!=null) {
						main.student.freeze="1";
					}
					else if (main.teacher!=null) {
						main.teacher.freeze="1";
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "操作成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		else if (ju2) 
		{
			String sql = "select 超期,罚金 from teacherfine WHERE 读者编号=? and 图书编号=? and 是否缴费 = 0";
			ResultSet rs = condata.queryExecute(sql, paras);
			try {
				if (rs.next()) //超期
				{
					String overDate = rs.getString(1);
					String fine = rs.getString(2);
					JOptionPane.showMessageDialog(null, "借书超期"+overDate+"天,请交纳"+fine+"元罚金", "提示", JOptionPane.INFORMATION_MESSAGE);
					if (main.student!=null) {
						main.student.freeze="1";
					}
					else if (main.teacher!=null) {
						main.teacher.freeze="1";
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "操作成功", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		else
			JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.ERROR_MESSAGE);
	}
	@Override
	public Model searchReader(String readerNumber,String readerCategary) 
	{
		String sql=null;
		if (readerCategary.equals("student")) 
		{
			sql="select 读者编号,密码,姓名,性别,联系电话,可借书数,借书数 from student where 读者编号=?";
		}
		else if (readerCategary.equals("teacher")) {
			sql="select 读者编号,密码,姓名,性别,联系电话,可借书数,借书数 from teacher where 读者编号=?";
		}
		String []paras= {readerNumber};
		return JtableUpdate.jtableUpdate_query(null, sql, paras);		
	}
	@Override
	public boolean payFine(String readerNumber,String readerCategary) 
	{
		ConnectDatabase condata=new ConnectDatabase();
		String sql=null;
		String []paras = {readerNumber};
		String fine = "0";
		if (readerCategary.equals("student")) 
		{
			sql="select 罚金 from studentfine where 读者编号=? and 是否缴费=0";
			ResultSet rs=condata.queryExecute(sql,paras);
			try {
				while(rs.next())
				{
					fine = Double.parseDouble(rs.getString(1))+Double.parseDouble(fine)+"";
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (!fine.equals("0")) {
				int n = JOptionPane.showConfirmDialog(null, "缴纳罚金："+fine+"元", "提示",JOptionPane.YES_NO_OPTION);
				if (n==0) {
					sql="update studentfine set 是否缴费=1 where 读者编号=? and 是否缴费=0";
					return condata.cudExecute(sql, paras);
				}
				else
					return false;
			}
			else 
				return false;	
		}
		else if (readerCategary.equals("teacher")) 
		{
			sql="select 罚金 from teacherfine where 读者编号=? and 是否缴费=0";
			ResultSet rs=condata.queryExecute(sql,paras);
			try {
				while(rs.next())
				{
					fine = Double.parseDouble(rs.getString(1))+Double.parseDouble(fine)+"";
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (!fine.equals("0")) {
				int n = JOptionPane.showConfirmDialog(null, "缴纳罚金："+fine+"元", "提示",JOptionPane.YES_NO_OPTION);
				if (n==0) {
					sql="update teacherfine set 是否缴费=1 where 读者编号=? and 是否缴费=0";
					return condata.cudExecute(sql, paras);
				}
				else
					return false;
			}
			else 
				return false;	
		}
		else 
			return false;	
	}
}
