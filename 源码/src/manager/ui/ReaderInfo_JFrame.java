package manager.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import database.JtableUpdate;
import database.Model;
import manager.dao.Manager;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReaderInfo_JFrame extends JFrame 
{
	private JPanel contentPane;
	public JTable infor_table ;
	private JButton alterReader_button;
	private Manager manager;
	JRadioButton student_radioButton;
	JRadioButton teacher_radioButton;
	private JTextField textField;
	Model sm = new Model();
	/**
	 * Create the frame.
	 * @param manager_JFrame 
	 * @param readerNumber 
	 */
	public ReaderInfo_JFrame(main.main main) 
	{
		manager=main.manager;
		setTitle("\u8BFB\u8005\u4FE1\u606F");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 753, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		infor_table = new JTable();	
		JScrollPane scrollPane = new JScrollPane(infor_table);
		scrollPane.setBounds(10, 57, 720, 272);
		contentPane.add(scrollPane);
		
		student_radioButton = new JRadioButton("\u5B66\u751F");
		student_radioButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String sql = "select 读者编号,密码,姓名,性别,联系电话,可借书数,借书数 from student";
				sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
				infor_table.setModel(sm);
			}
			
		});
		student_radioButton.setFont(new Font("宋体", Font.PLAIN, 20));
		student_radioButton.setBounds(21, 17, 79, 23);
		contentPane.add(student_radioButton);
		
		teacher_radioButton = new JRadioButton("\u6559\u5E08");
		teacher_radioButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String sql = "select 读者编号,密码,姓名,性别,联系电话,可借书数,借书数 from teacher";
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
						String sql = "select 读者编号,密码,姓名,性别,联系电话,可借书数,借书数 from student";
						sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
					}
					else {
						sm = manager.searchReader(readerNumber,"student");
						infor_table.setModel(sm);
					}
				}
				else if (teacher_radioButton.isSelected()) 
				{
					if(readerNumber.equals(""))
					{
						String sql = "select 读者编号,密码,姓名,性别,联系电话,可借书数,借书数 from teacher";
						sm = JtableUpdate.jtableUpdate_query(infor_table, sql, null);
					}
					else {
						sm = manager.searchReader(readerNumber,"teacher");
						infor_table.setModel(sm);
					}
				}
			}
		});
		findReader_button.setFont(new Font("宋体", Font.PLAIN, 20));
		findReader_button.setBounds(564, 17, 79, 25);
		contentPane.add(findReader_button);
	}
}
