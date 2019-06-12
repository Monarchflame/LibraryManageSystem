package manager.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.JtableUpdate;
import database.Model;
import main.main;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class ReturnBook_JFrame extends JFrame 
{

	private JPanel contentPane;
	private JTable infor_table;
	private Model sm;
	JRadioButton student_radioButton;
	JRadioButton teacher_radioButton;
	private JTextField textField;
	/**
	 * Create the frame.
	 * @param main 
	 */
	public ReturnBook_JFrame(main main) 
	{
		setTitle("\u5904\u7406\u5F52\u8FD8");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 740, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		infor_table = new JTable();
		
		JScrollPane scrollPane = new JScrollPane(infor_table);
		scrollPane.setBounds(5, 62, 711, 274);
		contentPane.add(scrollPane);
		//初始化表
		String sql = "select 读者编号,studentrecording.图书编号,book.图书名称,借阅时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
		String []paras = {};
		sm = JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
		
		JButton button = new JButton("\u5904\u7406");
		button.addActionListener(new ActionListener() //单击处理
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				int rowNum = infor_table.getSelectedRow();
				if(rowNum == -1) 
				{
					JOptionPane.showMessageDialog(null, "请在表中选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				else 
				{
					String readerNumber=((String) sm.getValueAt(rowNum, 0)).trim();
					String bookNumber=((String) sm.getValueAt(rowNum, 1)).trim();
					main.manager.returnBook(bookNumber, readerNumber);
					String sql1 = null;
					if (student_radioButton.isSelected()) {
						sql1 = "select 读者编号,studentrecording.图书编号,book.图书名称,借阅时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
					}
					else if (teacher_radioButton.isSelected()) {
						sql1 = "select 读者编号,teacherrecording.图书编号,book.图书名称,借阅时间 from teacherrecording,book where book.图书编号=teacherrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
					}
					JtableUpdate.jtableUpdate_query(infor_table, sql1, paras);//当还完一本后,sm应更新
				}
			}
		});
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		button.setBounds(619, 346, 97, 36);
		contentPane.add(button);
		
		student_radioButton = new JRadioButton("\u5B66\u751F");
		student_radioButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String sql = "select 读者编号,studentrecording.图书编号,book.图书名称,借阅时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
				sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
				infor_table.setModel(sm);
			}
			
		});
		student_radioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		student_radioButton.setBounds(21, 17, 79, 23);
		student_radioButton.setSelected(true);
		contentPane.add(student_radioButton);
		
		teacher_radioButton = new JRadioButton("\u6559\u5E08");
		teacher_radioButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String sql = "select 读者编号,teacherrecording.图书编号,book.图书名称,借阅时间 from teacherrecording,book where book.图书编号=teacherrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
				sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
				infor_table.setModel(sm);
			}
			
		});
		teacher_radioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		teacher_radioButton.setBounds(116, 17, 79, 23);
		contentPane.add(teacher_radioButton);
		//设组
		ButtonGroup group=new ButtonGroup();
		group.add(student_radioButton);
		group.add(teacher_radioButton);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 20));
		textField.setBounds(306, 17, 228, 24);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u7F16\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(222, 18, 74, 21);
		contentPane.add(label);
		
		JButton findReader_button = new JButton("\u67E5\u8BE2");
		findReader_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String readerNumber = textField.getText();
				if(student_radioButton.isSelected())
				{
					if(readerNumber .equals(""))
					{
						String sql = "select 读者编号,studentrecording.图书编号,book.图书名称,借阅时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
						sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
					}
					else {
						String sql = "select 读者编号,studentrecording.图书编号,book.图书名称,借阅时间 from studentrecording,book where book.图书编号=studentrecording.图书编号 and 准备还书 = 1 and 读者编号=? and 还书时间 is null";
						String []paras = {readerNumber};
						sm = JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
					}
				}
				else if (teacher_radioButton.isSelected()) 
				{
					if(readerNumber.equals(""))
					{
						String sql = "select 读者编号,teacherrecording.图书编号,book.图书名称,借阅时间 from teacherrecording,book where book.图书编号=teacherrecording.图书编号 and 准备还书 = 1 and 还书时间 is null";
						sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
					}
					else {
						String sql = "select 读者编号,teacherrecording.图书编号,book.图书名称,借阅时间 from teacherrecording,book where book.图书编号=teacherrecording.图书编号 and 准备还书 = 1 and 读者编号=? and 还书时间 is null";
						String []paras = {readerNumber};
						sm = JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
					}
				}
			}
		});
		findReader_button.setFont(new Font("宋体", Font.PLAIN, 20));
		findReader_button.setBounds(564, 17, 79, 25);
		contentPane.add(findReader_button);
	}
}
