package reader.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import database.ConnectDatabase;
import reader.business.Reader;
import reader.business.ReaderFunction_Interface;

public class Teacher extends Reader implements ReaderFunction_Interface
{
	public String college;//学院
	public String freeze;
	public Teacher(String number, String password, String name, String gender, String college, String phonenumber, int canBorrowNumber,int borrownumber, String freeze) 
	{
		super(number, password, name, gender, phonenumber, canBorrowNumber, borrownumber);
		this.college=college;
		this.freeze=freeze;
	}

	@Override
	public void SearchBook(String bookName) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public boolean BorrowBook(String bookNumber) 
	{
		ConnectDatabase conData = new ConnectDatabase();
		
		String []paras = {number,bookNumber};
		String sql="select * from teacherrecording where 读者编号=? and 图书编号=? and 还书时间 is null";
		ResultSet rs = conData.queryExecute(sql, paras);
		try 
		{
			if(rs.next())//借了一个一样的书还没还
			{
				return false;
			}
			else {
				sql = "INSERT INTO teacherrecording(读者编号,图书编号)  VALUES (?, ?)";	
				boolean ju = conData.cudExecute(sql,paras);
				return ju;
			}
		} catch (SQLException e) {
			return false;
		}
	}
	@Override
	public boolean ReturnBook(String bookNumber) 
	{
		ConnectDatabase condata = new ConnectDatabase();
		String sql = "update teacherrecording set 准备还书 = 1 where 图书编号=? and 读者编号=? and 还书时间 is null";
		String paras[]= {bookNumber,number};
		boolean ju = condata.cudExecute(sql,paras);
		return ju;
	}
	@Override
	public boolean alterPassword(String newPassword) 
	{
		if (!newPassword.equals(""))
		{
			try 
			{
				ConnectDatabase conData = new ConnectDatabase();
				String sql="UPDATE teacher SET 密码=? where 读者编号=? ";
				String []paras = {newPassword,this.number};
				boolean rs=conData.cudExecute(sql,paras);
				return rs;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "请输入完整信息", "错误", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return false;
	}

	@Override
	public boolean renew(String bookNumber) 
	{
		ConnectDatabase conData = new ConnectDatabase();
		String sql="select 是否续借 from teacherrecording where 读者编号=? and 图书编号=?";
		String []paras = {this.number,bookNumber};
		ResultSet rs = conData.queryExecute(sql, paras);
		try {
			String bol = rs.getString(1);
			if (bol.equals("0")) //没续借
			{
				sql="UPDATE teacherrecording SET 是否续借=1 where 读者编号=? and 图书编号=? ";//触发器实现续借
				boolean rs1 = conData.cudExecute(sql, paras);
				return rs1;
			}
			else {
				JOptionPane.showMessageDialog(null, "只能续借一次", "错误", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}

}
