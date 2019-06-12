package reader.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.main;
import reader.dao.Student;
import reader.dao.Teacher;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AlterPassword_JDialog extends JDialog 
{
	private final JPanel contentPanel = new JPanel();
	private JTextField oldPassword_textField;
	private JTextField newPassword_textField;
	private String oldPassword;
	private String newPassword;
	private String readersCategory;
	/**
	 * Create the dialog.
	 */
	public AlterPassword_JDialog(Student student,Teacher teacher,ReaderInfo_JFrame readerInfo_JFrame) 
	{
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 450, 166);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("\u521D\u59CB\u5BC6\u7801\uFF1A");
		label.setBounds(39, 10, 78, 21);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPanel.add(label);
		
		JLabel label_6 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(39, 55, 66, 21);
		contentPanel.add(label_6);
		
		oldPassword_textField = new JTextField();
		oldPassword_textField.setBounds(186, 10, 187, 21);
		oldPassword_textField.setColumns(10);
		contentPanel.add(oldPassword_textField);
		
		newPassword_textField = new JTextField();
		newPassword_textField.setColumns(10);
		newPassword_textField.setBounds(186, 55, 187, 21);
		contentPanel.add(newPassword_textField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent arg0) //点击ok
					{
						oldPassword=oldPassword_textField.getText();
						newPassword=newPassword_textField.getText();
						if (student!=null) 
                    	{
							if (oldPassword.equals(student.password)) 
		                    {
		                    	
								student.password=newPassword;
		                    	boolean rs = student.alterPassword(newPassword);
								if (rs) {
									JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
									//sql为update不可以直接jtableUpdate
									main.student=student;
								}
								else {
									JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
								}
								dispose();
							}
		                    else {
		                    	JOptionPane.showMessageDialog(null, "初始密码错误", "错误", JOptionPane.ERROR_MESSAGE);
							}
						}
                    	else if (teacher!=null) 
                    	{
                    		if (oldPassword.equals(teacher.password)) 
    	                    {
    	                    	
                    			teacher.password=newPassword;
    	                    	boolean rs = teacher.alterPassword(newPassword);
    							if (rs) {
    								JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
    								//sql为update不可以直接jtableUpdate
    								main.teacher=teacher;
    							}
    							else {
    								JOptionPane.showMessageDialog(null, "修改失败", "错误", JOptionPane.ERROR_MESSAGE);
    							}
    							dispose();
    						}
    	                    else {
    	                    	JOptionPane.showMessageDialog(null, "初始密码错误", "错误", JOptionPane.ERROR_MESSAGE);
    						}
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
}
