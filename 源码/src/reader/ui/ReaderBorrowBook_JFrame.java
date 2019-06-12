package reader.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.ImageIO;
import database.JtableUpdate;
import database.Model;
import main.main;
import reader.dao.Student;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ReaderBorrowBook_JFrame extends JFrame 
{
	private JPanel contentPane;
	public JTable infor_table;
	private JTextField bookInfo_textField;
	public JLabel personInfor_label;
	public JButton personInfo_button;
	//private JScrollPane scrollBar;
	/**
	 * Create the frame.
	 * @param sm 
	 */
	public ReaderBorrowBook_JFrame(main main)
	{
		setTitle("\u501F\u9605\u56FE\u4E66");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 927, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		infor_table = new JTable();
		//infor_table.setBounds(10, 168, 688, 243);
		contentPane.add(infor_table);
		//初始化table
		String sql = "select * from book";
		String []paras = {};
		Model sm = JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
		
		JScrollPane scrollPane = new JScrollPane(infor_table);
		scrollPane.setBounds(10, 168, 893, 243);
		contentPane.add(scrollPane);
		
		bookInfo_textField = new JTextField();
		bookInfo_textField.setColumns(10);
		bookInfo_textField.setBounds(252, 101, 297, 46);
		contentPane.add(bookInfo_textField);
		
		JButton findBook_button = new JButton("\u68C0\u7D22");
		findBook_button.setFont(new Font("宋体", Font.PLAIN, 17));
		findBook_button.setBounds(547, 101, 68, 46);
		contentPane.add(findBook_button);
		
		personInfor_label = new JLabel(main.studentInfo_label.getText());
		personInfor_label.setFont(new Font("宋体", Font.PLAIN, 20));
		personInfor_label.setHorizontalAlignment(SwingConstants.CENTER);
		personInfor_label.setBounds(237, 0, 390, 46);
		contentPane.add(personInfor_label);
		
		JButton borrow_button = new JButton("\u501F\u9605");
		borrow_button.setFont(new Font("宋体", Font.PLAIN, 17));
		borrow_button.setBounds(806, 421, 97, 46);
		contentPane.add(borrow_button);
		
		JButton borrowinfo_button = new JButton("\u67E5\u770B\u6211\u7684\u501F\u9605\u4FE1\u606F");
		borrowinfo_button.setFont(new Font("宋体", Font.PLAIN, 17));
		borrowinfo_button.setBounds(10, 421, 167, 46);
		contentPane.add(borrowinfo_button);
		
		/*注册事件区*/
		findBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//获取书名
				String bookname = bookInfo_textField.getText().trim();
				if(bookname.length() != 0)
				{
					String sql = "select * from book where 图书名称=?";
					String []paras = {bookname};
					//更新模型
					JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
				} else 
				{
					//书名为空时，输出全部书籍信息
					String sql = "select * from book";
					String []paras = {};
					//更新模型
					JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
				}
			}
		});
		borrow_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点击借阅
			{
				//获取选择行号，可选多行
				//int rowNum = infor_table.getSelectedRow();
				int[] sRow = infor_table.getSelectedRows();

				if(sRow==null) 
				{
					JOptionPane.showMessageDialog(null, "请在表中选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				else 
				{
					if (main.student!=null) 
					{
						if (main.student.freeze.equals("1")) 
						{
							JOptionPane.showMessageDialog(null, "您的账户被冻结，请您缴纳罚金", "提示", JOptionPane.ERROR_MESSAGE);
						}
						else if(main.student.canBorrowNumber<sRow.length)
						{
							JOptionPane.showMessageDialog(null, "借阅失败,您的可借书数不足", "提示", JOptionPane.ERROR_MESSAGE);
						}
						else 
						{
							for (int row : sRow) 
							{
								String bookNumber=((String) sm.getValueAt(row, 0)).trim();
								boolean rs = main.student.BorrowBook(bookNumber);
								if (rs) 
								{
									JOptionPane.showMessageDialog(null, "借阅成功", "提示", JOptionPane.INFORMATION_MESSAGE);
									main.student.canBorrowNumber--;
									main.student.borrownumber++;
								}
								else
								{
									JOptionPane.showMessageDialog(null, "借阅失败", "提示", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
					else if (main.teacher!=null) 
					{
						if (main.teacher.freeze.equals("1"))
						{
							JOptionPane.showMessageDialog(null, "您的账户被冻结，请您缴纳罚金", "提示", JOptionPane.ERROR_MESSAGE);
						}
						else if(main.teacher.canBorrowNumber<sRow.length)
						{
							JOptionPane.showMessageDialog(null, "借阅失败,您的可借书数不足", "提示", JOptionPane.ERROR_MESSAGE);
						}
						else 
						{
							for (int row : sRow) 
							{
								String bookNumber=((String) sm.getValueAt(row, 0)).trim();
								boolean rs = main.teacher.BorrowBook(bookNumber);
								if (rs) 
								{
									JOptionPane.showMessageDialog(null, "借阅成功", "提示", JOptionPane.INFORMATION_MESSAGE);
									main.teacher.canBorrowNumber--;
									main.teacher.borrownumber++;
								}
								else
								{
									JOptionPane.showMessageDialog(null, "借阅失败", "提示", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					}
					
					String sql="select * from book";
					JtableUpdate.jtableUpdate_query(infor_table, sql, null);
				}
			}
		});
		borrowinfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //查看借书信息
			{
				borrowInfo();
			}
		});
	}
	public void borrowInfo() 
	{
		BorrowInfo_JFrame borrowInfo_JFrame = new BorrowInfo_JFrame();
		borrowInfo_JFrame.setVisible(true);		
	}
}
