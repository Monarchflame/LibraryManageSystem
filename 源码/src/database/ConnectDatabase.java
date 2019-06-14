/*数据库底层连接*/
package database;
import java.sql.*;

public class ConnectDatabase
{
	//数据库
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url = "jdbc:sqlserver://localhost; DatabaseName=SchoolBookManage";
	private String user = "sa";
	private String passwd = "123456";
	
	private void run(String sql) throws ClassNotFoundException, SQLException 
	{
		//1、加载驱动
		Class.forName(driver);
		//2、连接
		ct = DriverManager.getConnection(url, user, passwd);
		//3、创建PreparedStatement,用来执行SQL语句
		ps = ct.prepareStatement(sql);
	}
	
	//查询，返回查询结果集
	public ResultSet queryExecute(String sql, String []paras)
	{
		try
		{
			run(sql);
			//给问号赋值
			if(paras != null)
			{
				for(int i = 0; i < paras.length; i++) 
				{
					ps.setString(i + 1, paras[i]);
				}
			}
			//执行
			try {//当无结果集返回时不报错
				rs = ps.executeQuery();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		//返回值
		return rs;
	}
		
	//增删改
	public boolean cudExecute(String sql, String []paras)
	{
		boolean b = true;
		try 
		{
			run(sql);
			//给问号赋值
			for(int i = 0; i < paras.length; i++)
			{
				ps.setString(i + 1, paras[i]);
			}
			//执行
			if(ps.executeUpdate() != 1) 
				b = false;
		} 
		catch (Exception e) 
		{
			b = false;
			e.printStackTrace();
		} 
		finally 
		{
			this.close();
		}
		//返回值
			return b;
	}
		
	//关闭资源
	public void close()
	{
		try 
		{
			if(rs!=null) rs.close();
			if(ps!=null) ps.close();
			if(ct!=null) ct.close();
		} 
		catch (Exception e2) 
		{
			e2.printStackTrace();
		}
	}
}

