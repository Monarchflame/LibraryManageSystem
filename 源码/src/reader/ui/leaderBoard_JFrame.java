package reader.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import database.JtableUpdate;
import database.Model;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class leaderBoard_JFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public leaderBoard_JFrame() 
	{
		setTitle("\u501F\u9605\u6392\u884C\u699C");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 705, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		String sql="select * from leaderboard_view";
		JtableUpdate.jtableUpdate_query(table, sql, null);
	}

}
