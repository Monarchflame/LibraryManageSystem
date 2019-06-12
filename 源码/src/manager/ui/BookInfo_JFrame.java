package manager.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import book.dao.Book;
import database.JtableUpdate;
import database.Model;
import main.main;
import manager.dao.Manager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class BookInfo_JFrame extends JFrame 
{
	private JPanel contentPane;
	public JTable infor_table;
	private JTextField bookInfo_textField;
	private JButton alterBook_button;
	private JButton addBook_button;
	public Manager manager;
	static Model sm;
	private JButton alterBookNumber_button;
	//private JScrollPane scrollBar;
	/**
	 * Create the frame.
	 * @param sm 
	 */
	public BookInfo_JFrame(main main)
	{
		manager = main.manager;
		setTitle("\u56FE\u4E66\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 933, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		infor_table = new JTable();
		infor_table.setBounds(10, 168, 688, 243);
		contentPane.add(infor_table);
		//初始化table
		String sql = "select * from book";
		String []paras = {};
		sm = JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
		
		JScrollPane scrollPane = new JScrollPane(infor_table);
		scrollPane.setBounds(10, 148, 899, 243);
		contentPane.add(scrollPane);
		
		bookInfo_textField = new JTextField();
		bookInfo_textField.setColumns(10);
		bookInfo_textField.setBounds(218, 55, 339, 46);
		contentPane.add(bookInfo_textField);
		
		JButton findBook_button = new JButton("\u68C0\u7D22");
		findBook_button.setFont(new Font("宋体", Font.PLAIN, 17));
		findBook_button.setBounds(555, 55, 68, 46);
		contentPane.add(findBook_button);
		
		JButton deleteBook_button = new JButton("\u5220\u9664\u56FE\u4E66");
		deleteBook_button.setFont(new Font("宋体", Font.PLAIN, 17));
		deleteBook_button.setBounds(692, 401, 115, 46);
		contentPane.add(deleteBook_button);
		
		alterBook_button = new JButton("\u4FEE\u6539\u56FE\u4E66");
		alterBook_button.setFont(new Font("宋体", Font.PLAIN, 17));
		alterBook_button.setBounds(532, 401, 115, 46);
		contentPane.add(alterBook_button);
		
		addBook_button = new JButton("\u65B0\u6DFB\u56FE\u4E66");
		addBook_button.setFont(new Font("宋体", Font.PLAIN, 17));
		addBook_button.setBounds(134, 401, 115, 46);
		contentPane.add(addBook_button);
		
		alterBookNumber_button = new JButton("\u589E\u52A0/\u51CF\u5C11\u56FE\u4E66\u6570\u76EE");
		alterBookNumber_button.setFont(new Font("宋体", Font.PLAIN, 17));
		alterBookNumber_button.setBounds(301, 401, 190, 46);
		contentPane.add(alterBookNumber_button);
		
		/*注册事件区*/
		/*添加图书*/
		addBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				addBook();
			}
		});
		/*查找图书*/
		findBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String bookName = bookInfo_textField.getText().trim();
				sm = manager.searchBook(bookName);
				infor_table.setModel(sm);
			}
		});
		//删除图书
		deleteBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点击删除
			{
				int rowNum = infor_table.getSelectedRow();
				if(rowNum == -1) 
				{
					JOptionPane.showMessageDialog(null, "请在表中选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
					return ;
				}
				else 
				{
					String bookNumber=((String) sm.getValueAt(rowNum, 0)).trim();
					boolean rs=manager.deleteBook(bookNumber);
					if (rs) {
						JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(null, "删除失败", "错误", JOptionPane.ERROR_MESSAGE);
					}
					String sql = "select * from book";
					String []paras = {};
					sm=JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
				}
			}
		});
		//修改图书
		alterBook_button.addActionListener(new ActionListener() //点击修改
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				alterBook();
			}
		});
		//修改图书数目
		alterBookNumber_button.addActionListener(new ActionListener() 
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
					String bookNumber=((String) sm.getValueAt(rowNum, 0)).trim();
					int bookNowNumber=Integer.parseInt(sm.getValueAt(rowNum, 7).toString());
					int bookChange = Integer.parseInt(JOptionPane.showInputDialog("请输入修改数目,减少书本数目请输入负数"));
					if(bookChange<0 && Math.abs(bookChange)>bookNowNumber)
					{
						JOptionPane.showMessageDialog(null, "减少数目不得大于在册数", "错误", JOptionPane.ERROR_MESSAGE);
					}
					else 
					{
						boolean rs=manager.asBook(bookNumber,bookChange);
						if(rs)
						{
							JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							String sql = "select * from book";
							String []paras = {};
							JtableUpdate.jtableUpdate_query(infor_table, sql, paras);
						}
						else {
							JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
	}
	
	public void alterBook() 
	{
		int rowNum = infor_table.getSelectedRow();
		if(rowNum == -1) 
		{
			JOptionPane.showMessageDialog(null, "请在表中选择一行", "提示", JOptionPane.INFORMATION_MESSAGE);
			return ;
		}
		else 
		{
			String bookNumber;//书籍编号
			String bookName;//书名
			String category;//类别
			String author;//作者
			String publishingHouse;//出版社
			String publicationDate;//出版日期
					
			bookNumber=((String) infor_table.getValueAt(rowNum, 0)).trim();//number
			bookName=((String) infor_table.getValueAt(rowNum, 1)).trim();	
			category=((String) infor_table.getValueAt(rowNum, 2)).trim();
			author=((String) infor_table.getValueAt(rowNum, 3)).trim();
			publishingHouse=((String) infor_table.getValueAt(rowNum, 4)).trim();
			publicationDate=((String) infor_table.getValueAt(rowNum, 5)).trim();
					
			Book oldBook = new Book(bookNumber, bookName, category, author, publishingHouse, publicationDate);
			AlterBook_JDialog inBook_JDialog =new AlterBook_JDialog(manager, oldBook,this);
			inBook_JDialog.setVisible(true);
		}
	}
	public void addBook() 
	{
		AddBook_JDialog addBook_JDialog = new AddBook_JDialog(this);
		addBook_JDialog.setVisible(true);
	}
}
