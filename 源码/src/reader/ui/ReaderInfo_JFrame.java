package reader.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.ImageIO;
import main.main;
import reader.ui.AlterPassword_JDialog;
import reader.dao.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReaderInfo_JFrame extends JFrame 
{
	private JPanel contentPane;
	public JTextArea broBook_textArea;
	JLabel number_label;
	String readersCategory;
	String fine="0";
	/**
	 * Create the frame.
	 */
	public ReaderInfo_JFrame(main main) 
	{
		setTitle("\u4E2A\u4EBA\u8D44\u6599");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 514, 376);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("\u8D26\u53F7\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 15));
		label_1.setBounds(177, 10, 55, 20);
		contentPane.add(label_1);
		
		number_label = new JLabel();
		number_label.setBounds(278, 10, 150, 20);
		contentPane.add(number_label);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 15));
		label.setBounds(177, 55, 55, 20);
		contentPane.add(label);
		
		JLabel name_label = new JLabel();
		name_label.setBounds(278, 55, 150, 20);
		contentPane.add(name_label);
		
		JLabel label_3 = new JLabel("\u6027\u522B\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 15));
		label_3.setBounds(177, 100, 55, 20);
		contentPane.add(label_3);
		
		JLabel gender_label = new JLabel();
		gender_label.setBounds(278, 100, 150, 20);
		contentPane.add(gender_label);
		
		JLabel label_4 = new JLabel("\u53EF\u501F\u9605\u4E66\u6570\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 15));
		label_4.setBounds(177, 145, 91, 20);
		contentPane.add(label_4);
		
		JLabel borNumber_label = new JLabel();
		borNumber_label.setBounds(278, 145, 150, 20);
		contentPane.add(borNumber_label);
		
		JLabel label_6 = new JLabel("\u501F\u9605\u4E66\u76EE\uFF1A");
		label_6.setFont(new Font("宋体", Font.PLAIN, 15));
		label_6.setBounds(177, 190, 91, 20);
		contentPane.add(label_6);
		
		broBook_textArea = new JTextArea();
		broBook_textArea.setForeground(Color.BLACK);
		JScrollPane scrl = new JScrollPane(broBook_textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);  
		scrl.setSize(212, 131);
		scrl.setLocation(278, 198);
		broBook_textArea.setBounds(298, 205, 212, 76);
		broBook_textArea.setEditable(false);
		setBorrowedBook();//添加书目和罚金
		
		JLabel fine_label = new JLabel("");
		fine_label.setFont(new Font("宋体", Font.PLAIN, 17));
		fine_label.setForeground(Color.red);
		fine_label.setBounds(20, 234, 248, 34);
		contentPane.add(fine_label);
		
		JLabel avatar_label = new JLabel("");
		avatar_label.setBounds(10, 10, 130, 130);
		avatar_label.setHorizontalAlignment(JLabel.CENTER);
		contentPane.add(avatar_label);
		
		if (main.student!=null && main.teacher==null) 
		{
			readersCategory="student";
			number_label.setText(main.student.number);
			name_label.setText(main.student.name);
			gender_label.setText(main.student.gender);
			borNumber_label.setText(main.student.canBorrowNumber+"");
			ImageIO.readerAvatarFromDatabase(number_label.getText(),avatar_label,null,readersCategory);
			if (main.student.freeze.equals("1")) {
				fine_label.setText("您有"+fine+"元罚金未缴纳！");
			}
		}
		else if (main.student==null && main.teacher!=null) 
		{
			readersCategory="teacher";
			number_label.setText(main.teacher.number);
			name_label.setText(main.teacher.name);
			gender_label.setText(main.teacher.gender);
			borNumber_label.setText(main.teacher.canBorrowNumber+"");
			ImageIO.readerAvatarFromDatabase(number_label.getText(),avatar_label,null,readersCategory);
		}
		
		JButton changeAvatar_button = new JButton("\u4FEE\u6539\u5934\u50CF");
		changeAvatar_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //修改头像
			{
				//初始化文件选择框
				JFileChooser fDialog = new JFileChooser();
				//设置只选文件
				fDialog.setFileSelectionMode(JFileChooser.FILES_ONLY);
				//设置文件选择框的标题
				fDialog.setDialogTitle("请选择所要打开的文件");
				//弹出选择框
				int returnVal = fDialog.showOpenDialog(null);
				// 如果是选择了文件
				if(JFileChooser.APPROVE_OPTION == returnVal)
				{
					String filepath = fDialog.getSelectedFile().getAbsolutePath();
					try
					{
						/*把头像插入到数据库中*/
						ImageIO.setAvatarToDatabase(number_label.getText(),filepath,avatar_label,null,readersCategory);
						/*读取头像，设置到面板*/
						ImageIO.readerAvatarFromDatabase(number_label.getText(),avatar_label,null,readersCategory);
					}
					catch (Exception e) 
					{
						JOptionPane.showMessageDialog(fDialog, this, "文件选取失败", returnVal);
						e.printStackTrace();
					}
				}
			}
		});
		changeAvatar_button.setFont(new Font("宋体", Font.PLAIN, 17));
		changeAvatar_button.setBounds(20, 150, 106, 34);
		contentPane.add(changeAvatar_button);
		contentPane.add(scrl);
		
		JButton alterInfo_button = new JButton("\u4FEE\u6539\u5BC6\u7801");
		alterInfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				alterInfo();
			}
		});
		alterInfo_button.setFont(new Font("宋体", Font.PLAIN, 17));
		alterInfo_button.setBounds(20, 190, 106, 34);
		contentPane.add(alterInfo_button);
		
		JButton logout_button = new JButton("\u6CE8\u9500");
		logout_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				logout(main);
			}
		});
		logout_button.setFont(new Font("宋体", Font.PLAIN, 20));
		logout_button.setBounds(30, 283, 97, 46);
		contentPane.add(logout_button);
	}
	/*
	 * 显示已借阅的书目和罚金
	 * */
	private void setBorrowedBook() 
	{//为什么字体不能改
		ConnectDatabase condata=new ConnectDatabase();
		String sql="";
		String []paras = new String[1];
		if (main.student!=null && main.teacher==null) 
		{
			sql="select book.图书名称 from student ,studentrecording,book where student.读者编号=studentrecording.读者编号 and book.图书编号=studentrecording.图书编号 and 还书时间 is null and student.读者编号=?";
			paras[0]=main.student.number;
		}
		else if (main.student==null && main.teacher!=null) 
		{
			sql="select book.图书名称 from teacher ,teacherrecording,book where teacher.读者编号=teacherrecording.读者编号 and book.图书编号=teacherrecording.图书编号 and 还书时间 is null and teacher.读者编号=?";
			paras[0]=main.teacher.number;
		}
		ResultSet rs=condata.queryExecute(sql,paras);
		
		try {
			while(rs.next())
			{
				String string = rs.getString(1);
				broBook_textArea.append(string.trim()+"\n");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		if (main.student!=null && main.teacher==null) 
		{
			sql="select 罚金 from studentfine WHERE 读者编号=? and 是否缴费 = 0";
		}
		else if (main.student==null && main.teacher!=null) 
		{
			sql="select 罚金 from teacherfine WHERE 读者编号=? and 是否缴费 = 0";
		}
		rs=condata.queryExecute(sql,paras);
		try {
			while(rs.next())
			{
				fine = Double.parseDouble(rs.getString(1))+Double.parseDouble(fine)+"";
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	private void alterInfo()
	{
		AlterPassword_JDialog inReader_JDialog =new AlterPassword_JDialog(main.student,main.teacher,this);
		inReader_JDialog.setVisible(true);
	}
	public void logout(main main)
	{
		main.student=null;
		main.teacher=null;
		main.studentInfo_label.setText("");
		main.teacherInfo_label.setText("");
		main.studentInfo_button.setIcon(new ImageIcon("login.png"));
		dispose();
	}
}
