package database;

import javax.swing.JTable;

public class JtableUpdate 
{
	//更新JTable内数据
	public static Model jtableUpdate_query(JTable table, String sql, String[] paras)
	{
		//创建模型
		Model sm = new Model();
		sm.query(sql, paras);
		//更新显示
		if(table!=null)
			table.setModel(sm);
		return sm;
	}
	
	/*public static Model jtableUpdate_cud(JTable table, String sql, String[] paras)
	{
		//创建模型
		Model sm = new Model();
		sm.cud(sql, paras);
		//更新显示
		if(table!=null)
			table.setModel(sm);
		return sm;
	}*/
}
