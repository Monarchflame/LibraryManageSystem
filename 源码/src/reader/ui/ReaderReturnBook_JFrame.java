package reader.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.ImageIO;
import database.JtableUpdate;
import database.Model;
import main.main;
import reader.dao.Student;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class ReaderReturnBook_JFrame extends JFrame {

	private JPanel contentPane;
	private JTable borrowedBook_table;
	Model sm;
	/**
	 * Create the frame.
	 */
	public ReaderReturnBook_JFrame(main main) 
	{
		setTitle("\u7533\u8BF7\u8FD8\u4E66");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 750, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		borrowedBook_table = new JTable();
		//初始化表
		String sql = null;
		String []paras =new String[1];
		if (main.student!=null) {
			sql = "select book.图书编号,book.图书名称,借阅时间,应还时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 读者编号=? and 还书时间 is null and 准备还书 = 0";
			paras[0] = main.student.number;
		}
		else if (main.teacher!=null) {
			sql = "select book.图书编号,book.图书名称,借阅时间,应还时间 from teacherrecording,book where book.图书编号=teacherrecording.图书编号 and 读者编号=? and 还书时间 is null and 准备还书 = 0";
			paras[0] = main.teacher.number;
		}
		sm = JtableUpdate.jtableUpdate_query(borrowedBook_table, sql, paras);
		
		JScrollPane scrollPane = new JScrollPane(borrowedBook_table);
		scrollPane.setBounds(12, 43, 716, 222);
		contentPane.add(scrollPane);
		
		JButton returnBook_button = new JButton("\u8FD8\u4E66");
		returnBook_button.addActionListener(new ActionListener() //点击还书
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int[]sRow = borrowedBook_table.getSelectedRows();
				if(sRow==null) 
				{
					JOptionPane.showMessageDialog(null, "请在表中选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				else
				{
					boolean rs = false;
					for (int row : sRow) 
					{
						String readerNumber="";
						if (main.student!=null && main.teacher==null)
						{
							readerNumber = main.student.number;
							String bookNumber=((String) sm.getValueAt(row, 0)).trim();
							rs = main.student.ReturnBook(bookNumber);
							if (rs) 
							{
								JOptionPane.showMessageDialog(null, "还书待处理", "提示", JOptionPane.INFORMATION_MESSAGE);
								String sql = "select book.图书编号,book.图书名称,借阅时间,应还时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 读者编号=? and 准备还书 = 0 and 还书时间 is null";
								String []paras=new String [1];
								paras[0]= readerNumber;
								sm = JtableUpdate.jtableUpdate_query(borrowedBook_table, sql, paras);
							}
							else
								JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
						else if (main.student==null && main.teacher!=null) 
						{
							readerNumber = main.teacher.number;
							String bookNumber=((String) sm.getValueAt(row, 0)).trim();
							rs = main.teacher.ReturnBook(bookNumber);
							if (rs) 
							{
								JOptionPane.showMessageDialog(null, "还书待处理", "提示", JOptionPane.INFORMATION_MESSAGE);
								String sql = "select book.图书编号,book.图书名称,借阅时间,应还时间 from teacherrecording,book where book.图书编号=teacherrecording.图书编号 and 读者编号=? and 准备还书 = 0 and 还书时间 is null";
								String []paras=new String [1];
								paras[0]= readerNumber;
								sm = JtableUpdate.jtableUpdate_query(borrowedBook_table, sql, paras);
							}
							else
								JOptionPane.showMessageDialog(null, "操作失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		returnBook_button.setFont(new Font("宋体", Font.PLAIN, 20));
		returnBook_button.setBounds(627, 282, 102, 43);
		contentPane.add(returnBook_button);
	}
}
