package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.ConnectDatabase;
import database.ImageIO;
import manager.dao.Manager;
import reader.dao.Student;
import reader.dao.Teacher;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login_JDialog extends JDialog 
{
	private final JPanel contentPanel = new JPanel();
	private JTextField number_textField;
	private JPasswordField passwordField;
	private main mainFrame;

	/**
	 * Create the dialog.
	 * @param string 
	 */
	public Login_JDialog(main main, String readersCategory) 
	{
		setTitle("\u767B\u5F55");
		this.mainFrame=main;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		number_textField = new JTextField();//"201700800370"
		number_textField.setColumns(10);
		number_textField.setBounds(165, 44, 213, 21);
		contentPanel.add(number_textField);
		
		JLabel label = new JLabel("\u5E10\u53F7:");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(60, 44, 38, 21);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801:");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(60, 95, 50, 21);
		contentPanel.add(label_1);
		
		passwordField = new JPasswordField();
		//passwordField.setText("123456");
		passwordField.setBounds(165, 95, 213, 21);
		contentPanel.add(passwordField);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				if (readersCategory.equals("manager")) //管理员登录
				{
					String getnumber=number_textField.getText();
					String getpassword=passwordField.getText();
					if(getnumber.equals(""))
						JOptionPane.showMessageDialog(null, "账号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					else if (getpassword.equals("")) 
						JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					else 
					{
						/*验证*/
						ConnectDatabase condata=new ConnectDatabase();
						String sql="select * from manager where 管理员编号=?";
						String []paras = {getnumber};
						ResultSet rs=condata.queryExecute(sql,paras);
						try {
							if(!rs.next())//空
							{
								JOptionPane.showMessageDialog(null, "账号不存在", "错误", JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								String datapassword = rs.getString(2);//第二列数据-password
								if((datapassword.trim()).equals(getpassword))//匹配成功
								{
									mainFrame.manager=new Manager(rs.getString(1), rs.getString(3), rs.getString(4), rs.getString(5)) ;
									mainFrame.managerInfo_button.setIcon(new ImageIcon("personinfo.png"));
									dispose();
								}
								else 
									JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}
				
				else if (readersCategory.equals("student"))//学生登录
				{
					String getnumber=number_textField.getText();
					String getpassword=passwordField.getText();
					if(getnumber.equals(""))
						JOptionPane.showMessageDialog(null, "账号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					else if (getpassword.equals("")) 
						JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					else 
					{
						/*验证*/
						ConnectDatabase condata=new ConnectDatabase();
						String sql="select * from student where 读者编号=?";
						String []paras = {getnumber};
						ResultSet rs=condata.queryExecute(sql,paras);//无问号
						try {
							if(!rs.next())//空
							{
								JOptionPane.showMessageDialog(null, "账号不存在", "错误", JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								String datapassword = rs.getString(2);//第二列数据-password
								if((datapassword.trim()).equals(getpassword))//匹配成功
								{
									mainFrame.studentInfo_label.setText(getnumber+" "+rs.getString(3));
									mainFrame.studentInfo_button.setIcon(new ImageIcon("personinfo.png"));
									
									mainFrame.student=new Student(rs.getString("读者编号"), rs.getString("密码"), rs.getString("姓名"), rs.getString("性别"), rs.getString("学位"), rs.getString("学院"), rs.getString("联系电话"), 
											Integer.parseInt(rs.getString("可借书数")), Integer.parseInt(rs.getString("借书数")),rs.getString("是否冻结"));
									mainFrame.teacher=null;
									mainFrame.teacherInfo_label.setText("");
									dispose();
								}
								else 
									JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}
				else if (readersCategory.equals("teacher"))//老师登录
				{
					String getnumber=number_textField.getText();
					String getpassword=passwordField.getText();
					if(getnumber.equals(""))
						JOptionPane.showMessageDialog(null, "账号不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					else if (getpassword.equals("")) 
						JOptionPane.showMessageDialog(null, "密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					else 
					{
						/*验证*/
						ConnectDatabase condata=new ConnectDatabase();
						String sql="select * from teacher where 读者编号=?";
						String []paras = {getnumber};
						ResultSet rs=condata.queryExecute(sql,paras);//无问号
						try {
							if(!rs.next())//空
							{
								JOptionPane.showMessageDialog(null, "账号不存在", "错误", JOptionPane.ERROR_MESSAGE);
							}
							else 
							{
								String datapassword = rs.getString(2);//第二列数据-password
								if((datapassword.trim()).equals(getpassword))//匹配成功
								{
									mainFrame.teacherInfo_label.setText(getnumber+" "+rs.getString(3));
									mainFrame.teacherInfo_button.setIcon(new ImageIcon("personinfo.png"));
									mainFrame.teacher=new Teacher(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
											Integer.parseInt(rs.getString(7)), Integer.parseInt(rs.getString(8)), rs.getString(11));
									mainFrame.studentInfo_label.setText("");
									mainFrame.student=null;
									dispose();
								}
								else 
									JOptionPane.showMessageDialog(null, "密码错误", "错误", JOptionPane.ERROR_MESSAGE);
							}
						} catch (SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}
			}
			/*private void manager() 
			{
				Manager_JFrame manager_JFrame = new Manager_JFrame(main);
				manager_JFrame.setVisible(true);
			}*/
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
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
