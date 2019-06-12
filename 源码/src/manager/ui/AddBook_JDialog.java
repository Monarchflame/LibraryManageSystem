package manager.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import book.dao.Book;
import database.JtableUpdate;
import database.Model;

public class AddBook_JDialog extends JDialog
{
	private final JPanel contentPanel = new JPanel();
	private JTextField number_textField;
	private JTextField name_textField;
	private JTextField author_textField;
	private JTextField publishingHouse_textField;

	public Book book;
	private JTextField total_textField;
	private JComboBox<String> day_comboBox;
	private JComboBox<String> month_comboBox;
	private JComboBox<String> year_comboBox;
	private boolean leapYear = false;;
	/**
	 * Create the dialog.
	 * @param main 
	 */
	public AddBook_JDialog(BookInfo_JFrame bookInfo_JFrame)
	{
		setTitle("\u8BF7\u8F93\u5165\u56FE\u4E66\u4FE1\u606F");
		setBounds(100, 100, 495, 431);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel number_label = new JLabel("\u7F16\u53F7\uFF1A");
		number_label.setFont(new Font("宋体", Font.PLAIN, 14));
		number_label.setBounds(39, 26, 43, 21);
		contentPanel.add(number_label);
		
		JLabel name_label = new JLabel("\u4E66\u540D\uFF1A");
		name_label.setFont(new Font("宋体", Font.PLAIN, 14));
		name_label.setBounds(39, 75, 43, 21);
		contentPanel.add(name_label);
		
		JLabel author_label = new JLabel("\u4F5C\u8005\uFF1A");
		author_label.setFont(new Font("宋体", Font.PLAIN, 14));
		author_label.setBounds(39, 169, 43, 21);
		contentPanel.add(author_label);
		
		JLabel publishingHouse_label = new JLabel("\u51FA\u7248\u793E\uFF1A");
		publishingHouse_label.setFont(new Font("宋体", Font.PLAIN, 14));
		publishingHouse_label.setBounds(39, 216, 78, 21);
		contentPanel.add(publishingHouse_label);
		
		number_textField = new JTextField("A");
		number_textField.setBounds(186, 26, 187, 21);
		contentPanel.add(number_textField);
		number_textField.setColumns(10);
		
		name_textField = new JTextField();
		name_textField.setColumns(10);
		name_textField.setBounds(186, 75, 187, 21);
		contentPanel.add(name_textField);
		
		author_textField = new JTextField();
		author_textField.setColumns(10);
		author_textField.setBounds(186, 169, 187, 21);
		contentPanel.add(author_textField);
		
		publishingHouse_textField = new JTextField();
		publishingHouse_textField.setColumns(10);
		publishingHouse_textField.setBounds(186, 216, 187, 21);
		contentPanel.add(publishingHouse_textField);
		
		JLabel category_label = new JLabel("\u7C7B\u522B\uFF1A");
		category_label.setFont(new Font("宋体", Font.PLAIN, 14));
		category_label.setBounds(39, 123, 43, 21);
		contentPanel.add(category_label);
		
		JComboBox<String> category_comboBox = new JComboBox<String>();
		category_comboBox.setModel(new DefaultComboBoxModel(new String[] {"A \u9A6C\u514B\u601D\u4E3B\u4E49\u3001\u5217\u5B81\u4E3B\u4E49\u3001\u6BDB\u6CFD\u4E1C\u601D\u60F3\u3001\u9093\u5C0F\u5E73\u7406\u8BBA", "B \u54F2\u5B66\u3001\u5B97\u6559", "C \u793E\u4F1A\u79D1\u5B66\u603B\u8BBA", "D \u653F\u6CBB\u3001\u6CD5\u5F8B", "E \u519B\u4E8B", "F \u7ECF\u6D4E", "G \u6587\u5316\u3001\u79D1\u5B66\u3001\u6559\u80B2\u3001\u4F53\u80B2", "H \u8BED\u8A00\u3001\u6587\u5B57", "I \u6587\u5B66", "J \u827A\u672F", "K \u5386\u53F2\u3001\u5730\u7406", "N \u81EA\u7136\u79D1\u5B66\u603B\u8BBA", "O \u6570\u7406\u79D1\u5B66\u548C\u5316\u5B66", "P \u5929\u6587\u5B66\u3001\u5730\u7403\u79D1\u5B66", "Q \u751F\u7269\u79D1\u5B66", "R \u533B\u836F\u3001\u536B\u751F", "S \u519C\u4E1A\u79D1\u5B66", "T \u5DE5\u4E1A\u6280\u672F", "U \u4EA4\u901A\u8FD0\u8F93", "V \u822A\u7A7A\u3001\u822A\u5929", "X \u73AF\u5883\u79D1\u5B66", "Z \u7EFC\u5408\u6027\u56FE\u4E66"}));
		category_comboBox.setSelectedIndex(0);
		category_comboBox.setBounds(186, 122, 187, 23);
		category_comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String category = category_comboBox.getSelectedItem().toString();
				char letter = category.charAt(0);
				number_textField.setText(String.valueOf(letter));
			}
		});
		contentPanel.add(category_comboBox);
		
		JLabel publicationDate_label = new JLabel("\u51FA\u7248\u65E5\u671F\uFF1A");
		publicationDate_label.setFont(new Font("宋体", Font.PLAIN, 14));
		publicationDate_label.setBounds(39, 265, 78, 21);
		contentPanel.add(publicationDate_label);
		
		day_comboBox = new JComboBox<String>();
		day_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		day_comboBox.setSelectedIndex(0);
		day_comboBox.setBounds(330, 265, 65, 23);
		//day_comboBox.setSelectedItem(0);
		contentPanel.add(day_comboBox);
		
		month_comboBox = new JComboBox<String>();
		month_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		month_comboBox.setSelectedIndex(0);
		month_comboBox.setBounds(250, 264, 60, 23);
		contentPanel.add(month_comboBox);
		month_comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				setDate();
			}
		});
		
		
		year_comboBox = new JComboBox<String>();
		year_comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1900", "1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916", "1917", "1918", "1919", "1920", 
				"1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", 
				"1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", 
				"1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", 
				"1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", 
				"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019"}));
		year_comboBox.setBounds(153, 264, 78, 23);
		year_comboBox.setSelectedIndex(0);
		contentPanel.add(year_comboBox);
		year_comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				int year = Integer.parseInt(year_comboBox.getSelectedItem().toString());
				if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) //闰年
				{
					leapYear=true;
				}
				//month_comboBox.actionPerformed(arg0);//这样会有问题,month_comboBox没有选中的值，我也不知道为什么
				setDate();
			}
		});
		
		JLabel year_label = new JLabel("\u5E74");
		year_label.setFont(new Font("宋体", Font.PLAIN, 14));
		year_label.setBounds(231, 266, 18, 18);
		contentPanel.add(year_label);
		
		JLabel month_label = new JLabel("\u6708");
		month_label.setFont(new Font("宋体", Font.PLAIN, 14));
		month_label.setBounds(312, 266, 18, 18);
		contentPanel.add(month_label);
		
		JLabel day_label = new JLabel("\u65E5");
		day_label.setHorizontalAlignment(SwingConstants.CENTER);
		day_label.setFont(new Font("宋体", Font.PLAIN, 14));
		day_label.setBounds(394, 266, 18, 18);
		contentPanel.add(day_label);
		
		JLabel label = new JLabel("\u603B\u6570\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(39, 308, 78, 21);
		contentPanel.add(label);
		
		total_textField = new JTextField();
		total_textField.setColumns(10);
		total_textField.setBounds(186, 308, 187, 21);
		contentPanel.add(total_textField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("ok");
				okButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) //添加修改
					{
						String bookNumber;//书籍编号
						String bookName;//书名
						String category;//类别
						String author;//作者
						String publishingHouse;//出版社
						String publicationDate;//出版日期
						int total;
						bookNumber=number_textField.getText();
						bookName=name_textField.getText();
						author=author_textField.getText();
						category=category_comboBox.getSelectedItem().toString().split(" ")[1];
						publishingHouse=publishingHouse_textField.getText();
						publicationDate=year_comboBox.getSelectedItem().toString()+"-"+month_comboBox.getSelectedItem().toString()+"-"+day_comboBox.getSelectedItem().toString();
						total=Integer.parseInt(total_textField.getText());
						
						book = new Book(bookNumber,bookName,category,author,publishingHouse,publicationDate);
						if(total==0)
						{
							JOptionPane.showMessageDialog(null, "图书总数不得为0", "错误", JOptionPane.ERROR_MESSAGE);
						}
						else if (!bookNumber.equals("") && !bookName.equals("") && !category.equals("") && !author.equals("") && !publishingHouse.equals("") && !publicationDate.equals("")&& total!=0) 
						{
							boolean rs = bookInfo_JFrame.manager.addBook(book,total);
							if (rs) {
								String sql="select * from book";
								String[] paras= {};
								BookInfo_JFrame.sm=JtableUpdate.jtableUpdate_query(bookInfo_JFrame.infor_table, sql, paras);
								JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
							else {
								JOptionPane.showMessageDialog(null, "添加失败", "错误", JOptionPane.ERROR_MESSAGE);
							}
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "请输入完整信息", "错误", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void setDate()
	{
		day_comboBox.removeAllItems();
		if(Integer.parseInt(month_comboBox.getSelectedItem().toString())==2) //选了二月
		{
			if(leapYear)//闰年
				for (int h=1; h<=29; h++)
					day_comboBox.addItem(h+"");
			else
				for (int h=1; h<=28; h++)
					day_comboBox.addItem(h+"");
		}
		else //没选二月
		{
			List<String> m1 = Arrays.asList("1","3","5","7","8","10","12");
			if(m1.contains(month_comboBox.getSelectedItem().toString()))
			{
				for (int h=1; h<=31; h++)
					day_comboBox.addItem(h+"");
			}
			else 
			{
				for (int h=1; h<=30; h++)
					day_comboBox.addItem(h+"");
			}
		}
	}
}
