package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import database.ConnectDatabase;
import database.ImageIO;
import database.Model;
import manager.dao.Manager;
import manager.ui.BookInfo_JFrame;
import manager.ui.ReturnBook_JFrame;
import reader.dao.Student;
import reader.dao.Teacher;
import reader.ui.BorrowInfo_JFrame;
import reader.ui.ReaderBorrowBook_JFrame;
import reader.ui.ReaderInfo_JFrame;
import reader.ui.ReaderReturnBook_JFrame;
import reader.ui.leaderBoard_JFrame;
import seat.SelectSeat_JFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class main extends JFrame 
{
	private Model sm = null;
	private JPanel contentPane;
	public JLabel studentInfo_label;
	public static Student student=null; //进行操作的读者
	public static Manager manager=null;
	public static Teacher teacher=null;
	public JButton retreatSeat_button;
	public JButton selectSeat_button;
	JButton studentBorrowBook_button;
	JButton studentReturnBook_button;
	public JButton studentInfo_button;
	JButton leaderBoard_button;
	JButton studentBorrowInfo_button;
	JButton teacherInfo_button;
	private JButton teacherBorrowinfo_button;
	private JButton leaderBoard_button_2;
	private JButton teacherReturnBook_button;
	private JButton teacherBorrowBook_button;
	public JLabel teacherInfo_label;
	JButton managerInfo_button;
	private JButton bookManage_button;
	private JButton findReader_button;
	private JButton managerReturnBook_button;
	private JButton payFine_button;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public main() 
	{	
		try {//设置主题
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());//!!!!!
		} catch (Exception e) {
			System.err.println("set skin fail!");
		}
		setIconImage(Toolkit.getDefaultToolkit().getImage("book.jpg"));
		setTitle("\u56FE\u4E66\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击退出关闭程序
		setBounds(100, 100, 943, 539);
		setResizable(false);//不可最大化
		setLocationRelativeTo(null); //设置窗口居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 10, 929, 491);
		contentPane.add(tabbedPane);
		
		JPanel student_panel = new JPanel();
		student_panel.setToolTipText("\u5B66\u751F\u4E13\u533A");
		tabbedPane.addTab("学生专区", null, student_panel, null);
		student_panel.setLayout(null);
		
		JPanel teacher_panel = new JPanel();
		teacher_panel.setToolTipText("\u6559\u5E08\u4E13\u533A");
		tabbedPane.addTab("教师专区", null, teacher_panel, null);
		teacher_panel.setLayout(null);
		
		teacherInfo_label = new JLabel("");
		teacherInfo_label.setHorizontalAlignment(SwingConstants.CENTER);
		teacherInfo_label.setFont(new Font("宋体", Font.PLAIN, 20));
		teacherInfo_label.setBounds(199, 115, 297, 46);
		teacher_panel.add(teacherInfo_label);
		
		teacherInfo_button = new JButton("");
		teacherInfo_button.setFont(new Font("宋体", Font.PLAIN, 14));
		teacherInfo_button.setBounds(199, 10, 300, 288);
		teacher_panel.add(teacherInfo_button);
		
		teacherBorrowinfo_button = new JButton("");
		teacherBorrowinfo_button.setFont(new Font("宋体", Font.PLAIN, 20));
		teacherBorrowinfo_button.setBounds(502, 10, 300, 144);
		teacher_panel.add(teacherBorrowinfo_button);
		
		leaderBoard_button_2 = new JButton("");
		leaderBoard_button_2.setFont(new Font("宋体", Font.PLAIN, 20));
		leaderBoard_button_2.setBounds(502, 154, 300, 144);
		teacher_panel.add(leaderBoard_button_2);
		
		teacherReturnBook_button = new JButton("");
		teacherReturnBook_button.setFont(new Font("宋体", Font.PLAIN, 20));
		teacherReturnBook_button.setBounds(502, 299, 300, 144);
		teacher_panel.add(teacherReturnBook_button);
		
		teacherBorrowBook_button = new JButton("");
		teacherBorrowBook_button.setFont(new Font("宋体", Font.PLAIN, 20));
		teacherBorrowBook_button.setBounds(199, 299, 300, 144);
		teacher_panel.add(teacherBorrowBook_button);
		
		JPanel manager_panel = new JPanel();
		manager_panel.setToolTipText("\u7BA1\u7406\u5458\u4E13\u533A");
		tabbedPane.addTab("管理员专区", null, manager_panel, null);
		manager_panel.setLayout(null);
		
		managerInfo_button = new JButton("");
		managerInfo_button.setFont(new Font("宋体", Font.PLAIN, 14));
		managerInfo_button.setBounds(199, 10, 300, 288);
		manager_panel.add(managerInfo_button);
		
		bookManage_button = new JButton("");
		bookManage_button.setFont(new Font("宋体", Font.PLAIN, 15));
		bookManage_button.setBounds(500, 10, 300, 144);
		manager_panel.add(bookManage_button);
		
		findReader_button = new JButton("");
		findReader_button.setFont(new Font("宋体", Font.PLAIN, 15));
		findReader_button.setBounds(199, 298, 300, 144);
		manager_panel.add(findReader_button);
		
		managerReturnBook_button = new JButton("");
		managerReturnBook_button.setFont(new Font("宋体", Font.PLAIN, 15));
		managerReturnBook_button.setBounds(500, 154, 300, 144);
		manager_panel.add(managerReturnBook_button);
		
		payFine_button = new JButton("");
		payFine_button.setFont(new Font("宋体", Font.PLAIN, 15));
		payFine_button.setBounds(500, 298, 300, 144);
		manager_panel.add(payFine_button);
		
		studentInfo_label = new JLabel("");
		studentInfo_label.setHorizontalAlignment(SwingConstants.CENTER);
		studentInfo_label.setFont(new Font("宋体", Font.PLAIN, 20));
		studentInfo_label.setBounds(313, 98, 297, 46);
		student_panel.add(studentInfo_label);
		//studentLogout_button.setVisible(false);
		
		selectSeat_button = new JButton("");
		selectSeat_button.setBounds(10, 10, 300, 144);
		selectSeat_button.setFont(new Font("宋体", Font.PLAIN, 20));
		//selectSeat_button.setVisible(false);
		student_panel.add(selectSeat_button);
		
		retreatSeat_button = new JButton("");
		retreatSeat_button.setBounds(10, 154, 300, 144);
		//retreatSeat_button.setVisible(false);
		student_panel.add(retreatSeat_button);
		retreatSeat_button.setFont(new Font("宋体", Font.PLAIN, 20));
		
		studentBorrowBook_button = new JButton("");
		studentBorrowBook_button.setBounds(138, 299, 300, 144);
		//studentBorrowBook_button.setVisible(false);
		student_panel.add(studentBorrowBook_button);
		studentBorrowBook_button.setFont(new Font("宋体", Font.PLAIN, 20));
		
		studentReturnBook_button = new JButton("");
		studentReturnBook_button.setBounds(467, 299, 300, 144);
		//studentReturnBook_button.setVisible(false);
		student_panel.add(studentReturnBook_button);
		studentReturnBook_button.setFont(new Font("宋体", Font.PLAIN, 20));
		
		studentInfo_button = new JButton("");
		studentInfo_button.setFont(new Font("宋体", Font.PLAIN, 14));
		studentInfo_button.setBounds(310, 10, 300, 288);//这样getHeight和getWidth都是0？？？？？
		//studentInfo_button.setVisible(false);
		student_panel.add(studentInfo_button);
		
		leaderBoard_button = new JButton("");
		leaderBoard_button.setFont(new Font("宋体", Font.PLAIN, 20));
		leaderBoard_button.setBounds(613, 154, 300, 144);
		//leaderBoard_button.setVisible(false);
		student_panel.add(leaderBoard_button);
		
		studentBorrowInfo_button = new JButton("");
		studentBorrowInfo_button.setFont(new Font("宋体", Font.PLAIN, 20));
		studentBorrowInfo_button.setBounds(613, 10, 300, 144);
		student_panel.add(studentBorrowInfo_button);
		
		setImage();
		studentInfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //个人信息
			{
				if(student==null)//没登录
					login("student");
				else
					personalInformation();
			}
		});
		selectSeat_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) //选座
			{
				if(studentInfo_label.getText().equals(""))//没登录
				{
					login("student");
				}
				else 
				{
					ConnectDatabase condata=new ConnectDatabase();
					String sql = "select * from seat where 读者编号=?";					
					String []paras = {studentInfo_label.getText().split(" ")[0].trim()};
					ResultSet rs=condata.queryExecute(sql,paras);
					try {
						if(rs.next())
						{
							JOptionPane.showMessageDialog(null, "你已选座", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							selectSeat();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
		retreatSeat_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点击退座
			{
				if(studentInfo_label.getText().equals(""))//没登录
				{
					login("student");
				}
				else 
				{
					ConnectDatabase condata=new ConnectDatabase();
					String sql = "select * from seat where 读者编号=?";					
					String []paras = {studentInfo_label.getText().split(" ")[0].trim()};
					ResultSet rs=condata.queryExecute(sql,paras);
					try {
						if(rs.next())//就一行
						{
							String floor = rs.getString(1);
							String number = rs.getString(2);
							int choice = JOptionPane.showConfirmDialog(null, "确定退座？\n座位信息："+floor+"楼 "+number+"号","提示",JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
							// 0=yes, 1=no, 2=cancel
							if(choice == 0)
							{
								sql = "UPDATE seat set 读者编号=null where 读者编号=?";	
								boolean ju = condata.cudExecute(sql,paras);
								if(ju)
									JOptionPane.showMessageDialog(null, "退座成功", "提示", JOptionPane.INFORMATION_MESSAGE);
								else 
									JOptionPane.showMessageDialog(null, "退座失败", "提示", JOptionPane.ERROR_MESSAGE);
							}
						}
						else //空的，没选座
						{
							JOptionPane.showMessageDialog(null, "没有选座", "提示", JOptionPane.INFORMATION_MESSAGE);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		studentBorrowBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点借书
			{
				if(student==null)//没登录
				{
					login("student");
				}
				else 
				{
					borrowBook(); 
				}
			}
		});
		studentReturnBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点击还书
			{
				if(student==null)//没登录
				{
					login("student");
				}
				else 
				{
					returnBook();
				}
			}
		});
		leaderBoard_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				leaderBoard();
			}
		});
		leaderBoard_button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				leaderBoard();
			}
		});
		studentBorrowInfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //查看借书信息
			{
				if(student==null)//没登录
					login("student");
				else
					borrowInfo();
			}
		});
		teacherBorrowinfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(teacher==null)//没登录
					login("teacher");
				else
					borrowInfo();
			}
		});
		teacherInfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //个人信息
			{
				if(teacher==null)//没登录
				{
					login("teacher");
				}
				else 
					personalInformation();
			}
		});
		teacherBorrowBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点借书
			{
				if(teacher==null)//没登录
				{
					login("teacher");
				}
				else 
				{
					borrowBook(); 
				}
			}
		});
		
		teacherReturnBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) //点击还书
			{
				if(teacher==null)//没登录
				{
					login("teacher");
				}
				else 
				{
					returnBook();
				}
			}
		});
		
		/*管理员区*/
		managerInfo_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(manager==null)//没登录
					login("manager");
			}
		});
		bookManage_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(manager==null)//没登录
					login("manager");
				else
					bookManage();
			}
		});
		managerReturnBook_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(manager==null)//没登录
					login("manager");
				else
					managerReturnBook();
			}
		});
		findReader_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if(manager==null)//没登录
					login("manager");
				else
					findReader();
			}
		});
		payFine_button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try {
					if(manager==null)//没登录
						login("manager");
					else
						payFine();
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
	}

	public void selectSeat()
	{
		SelectSeat_JFrame selectSeat = new SelectSeat_JFrame(this);
		selectSeat.setVisible(true);
	}
	public void borrowBook() 
	{
		ReaderBorrowBook_JFrame borrowFrame = new ReaderBorrowBook_JFrame(this);
		borrowFrame.setVisible(true);
	}
	public void returnBook() 
	{
		ReaderReturnBook_JFrame return_JFrame = new ReaderReturnBook_JFrame(this);
		return_JFrame.setVisible(true);
	}
	public void login(String string)
	{
		Login_JDialog loginJD=new Login_JDialog(this,string);
		loginJD.setVisible(true);
	}
	public void personalInformation()
	{
		ReaderInfo_JFrame rFrame=new ReaderInfo_JFrame(this);
		rFrame.setVisible(true);
	}
	public void leaderBoard()
	{
		leaderBoard_JFrame leaderBoard_JFrame = new leaderBoard_JFrame();
		leaderBoard_JFrame.setVisible(true);
	}
	public void borrowInfo() 
	{
		BorrowInfo_JFrame borrowInfo_JFrame = new BorrowInfo_JFrame();
		borrowInfo_JFrame.setVisible(true);		
	}
	public void findReader()
	{
		manager.ui.ReaderInfo_JFrame readerInfo_JFrame = new manager.ui.ReaderInfo_JFrame(this);
		readerInfo_JFrame.setVisible(true);
	}
	public void bookManage() 
	{
		manager.ui.BookInfo_JFrame bookInfo_JFrame = new manager.ui.BookInfo_JFrame(this);
		bookInfo_JFrame.setVisible(true);
	}
	public void managerReturnBook()
	{
		manager.ui.ReturnBook_JFrame returnBook_JFrame = new manager.ui.ReturnBook_JFrame(this);
		returnBook_JFrame.setVisible(true);
	}
	public void payFine() throws SQLException
	{
		String readerNumber = JOptionPane.showInputDialog("请输入读者编号");
		if (readerNumber!=null) 
		{
			boolean rs = false;
			if (main.student!=null) {
				rs=manager.payFine(readerNumber,"student");
			}
			else if (main.teacher!=null) {
				rs=manager.payFine(readerNumber,"teacher");
			}
			if (rs) {
				if (main.student!=null) {
					main.student.freeze="0";
				}
				else if (main.teacher!=null) {
					main.teacher.freeze="0";
				}
				JOptionPane.showMessageDialog(null, "缴费成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "缴费失败", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public void setImage() 
	{
		studentBorrowBook_button.setIcon(new ImageIcon("picture//borrow.png"));
		studentInfo_button.setIcon(new ImageIcon("picture//login.png"));
		studentReturnBook_button.setIcon(new ImageIcon("picture//return.png"));
		selectSeat_button.setIcon(new ImageIcon("picture//selectseat.png"));
		retreatSeat_button.setIcon(new ImageIcon("picture//retreatseat.png"));
		leaderBoard_button.setIcon(new ImageIcon("picture//list.png"));
		studentBorrowInfo_button.setIcon(new ImageIcon("picture//borrowinfo.png"));
		
		teacherBorrowBook_button.setIcon(new ImageIcon("picture//borrow.png"));
		teacherInfo_button.setIcon(new ImageIcon("picture//login.png"));
		teacherReturnBook_button.setIcon(new ImageIcon("picture//return.png"));
		leaderBoard_button_2.setIcon(new ImageIcon("picture//list.png"));
		teacherBorrowinfo_button.setIcon(new ImageIcon("picture//borrowinfo.png"));
		
		managerInfo_button.setIcon(new ImageIcon("picture//login.png"));
		bookManage_button.setIcon(new ImageIcon("picture//bookmanage.png"));
		managerReturnBook_button.setIcon(new ImageIcon("picture//returnbook.png"));
		findReader_button.setIcon(new ImageIcon("picture//findreader.png"));
		payFine_button.setIcon(new ImageIcon("picture//fine.png"));
	}
}
