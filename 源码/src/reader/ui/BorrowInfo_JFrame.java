package reader.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.JtableUpdate;
import database.Model;
import main.main;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BorrowInfo_JFrame extends JFrame {

	private JPanel contentPane;
	public JTable table;
	private JButton renew_button;
	String sql = null;
	/**
	 * Create the frame.
	 */
	public BorrowInfo_JFrame() 
	{
		setTitle("\u501F\u9605\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 666, 355);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 642, 253);
		contentPane.add(scrollPane);
		table = new JTable();
		scrollPane.setViewportView(table);
		
		//String sql = null;//放在这还是局部变量？
		String []paras =new String[1];
		//调用存储过程查看借书信息
		if (main.student!=null) {
			sql="EXEC studentborrow @readernumber=?";
			paras[0] = main.student.number;
		}
		else if (main.teacher!=null) {
			sql="EXEC teacherborrow @readernumber=?";
			paras[0] = main.teacher.number;
		}
		Model sm=JtableUpdate.jtableUpdate_query(table, sql, paras);
		
		renew_button = new JButton("\u7EED\u501F");
		renew_button.addActionListener(new ActionListener() //续借
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int[] sRow = table.getSelectedRows();

				if(sRow==null) 
				{
					JOptionPane.showMessageDialog(null, "请在表中选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				else 
				{
					for (int row : sRow) 
					{
						String bookNumber=((String) sm.getValueAt(row, 2)).trim();
						if (main.student!=null && main.teacher==null)
						{
							boolean rs = main.student.renew(bookNumber);
							if (rs) 
							{
								JOptionPane.showMessageDialog(null, "续借成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								JtableUpdate.jtableUpdate_query(table, sql, paras);
							}
							else
								JOptionPane.showMessageDialog(null, "续借失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
						else if (main.student==null && main.teacher!=null) 
						{
							boolean rs = main.teacher.renew(bookNumber);
							if (rs) 
							{
								JOptionPane.showMessageDialog(null, "续借成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								JtableUpdate.jtableUpdate_query(table, sql, paras);
							}
							else
								JOptionPane.showMessageDialog(null, "续借失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		renew_button.setFont(new Font("宋体", Font.PLAIN, 20));
		renew_button.setBounds(534, 268, 108, 50);
		contentPane.add(renew_button);
	}
}
