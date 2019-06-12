package database;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
 
public class Model extends AbstractTableModel//返回表格
{
	public String datebasename="bookManage";
	private Vector<String> columnNames;//存放列
	private Vector<Vector<String>> rowDates;//存放元组
	
	//增删改
	public boolean cud(String sql, String []paras)
	{
		return new ConnectDatabase().cudExecute(sql, paras);
	}
	
	//查询
	public void query(String sql, String []paras)//只写一个query是为了更新简单
	{
		ConnectDatabase conData = null;
		//初始化JTable信息
		columnNames = new Vector<String>();//列，实现了一个动态数组，列名为元素
		rowDates = new Vector<Vector<String>>();//实现了一个动态数组，元组为元素
		try 
		{
			conData = new ConnectDatabase();conData = new ConnectDatabase();
			ResultSet rs = conData.queryExecute(sql, paras);
			
			ResultSetMetaData metaData = rs.getMetaData(); 
			int colum = metaData.getColumnCount();    
			for (int i = 1; i <= colum; i++)    
			{    
			 	//获取列名    
			 	String columName = metaData.getColumnName(i); 
			 	columnNames.add(columName);
			}
			while(rs.next()) //遍历结果集
			{//有几列就加多少
				Vector<String> row = new Vector<String>();//默认大小为10，一个元素就是元组的一个属性
				for (int i = 1; i <= colum; i++)
				{    
				 	//获取元组属性   
				 	String attributes = rs.getString(i); 
				 	row.add(attributes);
				}
				rowDates.add(row);//一个元素就是数据库中一个元组
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conData.close();
		}
	}
	
	@Override
	public int getColumnCount() 
	{
		// TODO Auto-generated method stub
		return this.columnNames.size();// 返回此向量中的组件数。
	}
 
	@Override
	public int getRowCount() 
	{
		// TODO Auto-generated method stub
		return this.rowDates.size();
	}
 
	@Override
	public Object getValueAt(int row, int col)
	{
		// TODO Auto-generated method stub
		if(!rowDates.isEmpty())
			return ((Vector<?>)this.rowDates.get(row)).get(col);
		else
			return null;
	}
	
	@Override
	public String getColumnName(int column) 
	{
		// TODO Auto-generated method stub
		return (String)this.columnNames.get(column);
	}
}
