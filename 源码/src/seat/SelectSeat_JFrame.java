package seat;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import database.ConnectDatabase;
import main.main;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class SelectSeat_JFrame extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private main mainFrame;
	private HashMap<String, Component> componentMap;
	private JPanel firstFloor_panel;
	private JPanel secondFloor_panel;
	private JPanel thirdFloor_panel;
	private JPanel fourthFloor_panel;
	private JPanel fifthFloor_panel;
	/**
	 * Create the frame.
	 * @param main 
	 */
	public SelectSeat_JFrame(main main) 
	{
		setTitle("\u5EA7\u4F4D");
		mainFrame = main;
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 401, 498);
		setResizable(false);//不可最大化
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		firstFloor_panel = new JPanel();
		tabbedPane.addTab("1楼", null, firstFloor_panel, null);
		//firstFloor_panel.setLayout(new FormLayout());
		firstFloor_panel.setLayout(new FormLayout(new ColumnSpec[] 
				{
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton button1_1 = new JButton("1");
		button1_1.setName("button1_1");
		button1_1.addActionListener(this);
		firstFloor_panel.add(button1_1, "2, 2");
		
		JButton button1_2 = new JButton("2");
		button1_2.setName("button1_2");
		button1_2.addActionListener(this);
		firstFloor_panel.add(button1_2, "4, 2");
		
		JButton button1_47 = new JButton("47");
		button1_47.setName("button1_47");
		button1_47.addActionListener(this);
		firstFloor_panel.add(button1_47, "20, 2");
		
		JButton button1_48 = new JButton("48");
		button1_48.setName("button1_48");
		button1_48.addActionListener(this);
		firstFloor_panel.add(button1_48, "22, 2");
		
		JButton button1_3 = new JButton("3");
		button1_3.setName("button1_3");
		button1_3.addActionListener(this);
		firstFloor_panel.add(button1_3, "2, 4");
		
		JButton button1_4 = new JButton("4");
		button1_4.setName("button1_4");
		button1_4.addActionListener(this);
		firstFloor_panel.add(button1_4, "4, 4");
		
		JButton button1_49 = new JButton("49");
		button1_49.setName("button1_49");
		button1_49.addActionListener(this);
		firstFloor_panel.add(button1_49, "20, 4");
		
		JButton button1_50 = new JButton("50");
		button1_50.setName("button1_50");
		button1_50.addActionListener(this);
		firstFloor_panel.add(button1_50, "22, 4");
		
		JButton button1_5 = new JButton("5");
		button1_5.setName("button1_5");
		button1_5.addActionListener(this);
		firstFloor_panel.add(button1_5, "2, 8");
		
		JButton button1_6 = new JButton("6");
		button1_6.setName("button1_6");
		button1_6.addActionListener(this);
		firstFloor_panel.add(button1_6, "4, 8");
		
		JButton button1_43 = new JButton("43");
		button1_43.setName("button1_43");
		button1_43.addActionListener(this);
		firstFloor_panel.add(button1_43, "20, 8");
		
		JButton button1_44 = new JButton("44");
		button1_44.setName("button1_44");
		button1_44.addActionListener(this);
		firstFloor_panel.add(button1_44, "22, 8");
		
		JButton button1_7 = new JButton("7");
		button1_7.setName("button1_7");
		button1_7.addActionListener(this);
		firstFloor_panel.add(button1_7, "2, 10");
		
		JButton button1_8 = new JButton("8");
		button1_8.setName("button1_8");
		button1_8.addActionListener(this);
		firstFloor_panel.add(button1_8, "4, 10");
		
		JButton button1_45 = new JButton("45");
		button1_45.setName("button1_45");
		button1_45.addActionListener(this);
		firstFloor_panel.add(button1_45, "20, 10");
		
		JButton button1_46 = new JButton("46");
		button1_46.setName("button1_46");
		button1_46.addActionListener(this);
		firstFloor_panel.add(button1_46, "22, 10");
		
		JButton button1_9 = new JButton("9");
		button1_9.setName("button1_9");
		button1_9.addActionListener(this);
		firstFloor_panel.add(button1_9, "2, 14");
		
		JButton button1_10 = new JButton("10");
		button1_10.setName("button1_10");
		button1_10.addActionListener(this);
		firstFloor_panel.add(button1_10, "4, 14");
		
		JButton button1_39 = new JButton("39");
		button1_39.setName("button1_39");
		button1_39.addActionListener(this);
		firstFloor_panel.add(button1_39, "20, 14");
		
		JButton button1_40 = new JButton("40");
		button1_40.setName("button1_40");
		button1_40.addActionListener(this);
		firstFloor_panel.add(button1_40, "22, 14");
		
		JButton button1_11 = new JButton("11");
		button1_11.setName("button1_11");
		button1_11.addActionListener(this);
		firstFloor_panel.add(button1_11, "2, 16");
		
		JButton button1_12 = new JButton("12");
		button1_12.setName("button1_12");
		button1_12.addActionListener(this);
		firstFloor_panel.add(button1_12, "4, 16");
		
		JButton button1_41 = new JButton("41");
		button1_41.setName("button1_41");
		button1_41.addActionListener(this);
		firstFloor_panel.add(button1_41, "20, 16");
		
		JButton button1_42 = new JButton("42");
		button1_42.setName("button1_42");
		button1_42.addActionListener(this);
		firstFloor_panel.add(button1_42, "22, 16");
		
		JButton button1_13 = new JButton("13");
		button1_13.setName("button1_13");
		button1_13.addActionListener(this);
		firstFloor_panel.add(button1_13, "2, 20");
		
		JButton button1_14 = new JButton("14");
		button1_14.setName("button1_14");
		button1_14.addActionListener(this);
		firstFloor_panel.add(button1_14, "4, 20");
		
		JButton button1_35 = new JButton("35");
		button1_35.setName("button1_35");
		button1_35.addActionListener(this);
		firstFloor_panel.add(button1_35, "20, 20");
		
		JButton button1_36 = new JButton("36");
		button1_36.setName("button1_36");
		button1_36.addActionListener(this);
		firstFloor_panel.add(button1_36, "22, 20");
		
		JButton button1_15 = new JButton("15");
		button1_15.setName("button1_15");
		button1_15.addActionListener(this);
		firstFloor_panel.add(button1_15, "2, 22");
		
		JButton button1_16 = new JButton("16");
		button1_16.setName("button1_16");
		button1_16.addActionListener(this);
		firstFloor_panel.add(button1_16, "4, 22");
		
		JButton button1_37 = new JButton("37");
		button1_37.setName("button1_37");
		button1_37.addActionListener(this);
		firstFloor_panel.add(button1_37, "20, 22");
		
		JButton button1_38 = new JButton("38");
		button1_38.setName("button1_38");
		button1_38.addActionListener(this);
		firstFloor_panel.add(button1_38, "22, 22");
		
		JButton button1_17 = new JButton("17");
		button1_17.setName("button1_17");
		button1_17.addActionListener(this);
		firstFloor_panel.add(button1_17, "2, 26");
		
		JButton button1_18 = new JButton("18");
		button1_18.setName("button1_18");
		button1_18.addActionListener(this);
		firstFloor_panel.add(button1_18, "4, 26");
		
		JButton button1_21 = new JButton("21");
		button1_21.setName("button1_21");
		button1_21.addActionListener(this);
		firstFloor_panel.add(button1_21, "8, 26");
		
		JButton button1_22 = new JButton("22");
		button1_22.setName("button1_22");
		button1_22.addActionListener(this);
		firstFloor_panel.add(button1_22, "10, 26");
		
		JButton button1_23 = new JButton("23");
		button1_23.setName("button1_23");
		button1_23.addActionListener(this);
		firstFloor_panel.add(button1_23, "12, 26");
		
		JButton button1_24 = new JButton("24");
		button1_24.setName("button1_24");
		button1_24.addActionListener(this);
		firstFloor_panel.add(button1_24, "14, 26");
		
		JButton button1_25 = new JButton("25");
		button1_25.setName("button1_25");
		button1_25.addActionListener(this);
		firstFloor_panel.add(button1_25, "16, 26");
		
		JButton button1_31 = new JButton("31");
		button1_31.setName("button1_31");
		button1_31.addActionListener(this);
		firstFloor_panel.add(button1_31, "20, 26");
		
		JButton button1_32 = new JButton("32");
		button1_32.setName("button1_32");
		button1_32.addActionListener(this);
		firstFloor_panel.add(button1_32, "22, 26");
		
		JButton button1_19 = new JButton("19");
		button1_19.setName("button1_19");
		button1_19.addActionListener(this);
		firstFloor_panel.add(button1_19, "2, 28");
		
		JButton button1_20 = new JButton("20");
		button1_20.setName("button1_20");
		button1_20.addActionListener(this);
		firstFloor_panel.add(button1_20, "4, 28");
		
		JButton button1_26 = new JButton("26");
		button1_26.setName("button1_26");
		button1_26.addActionListener(this);
		firstFloor_panel.add(button1_26, "8, 28");
		
		JButton button1_27 = new JButton("27");
		button1_27.setName("button1_27");
		button1_27.addActionListener(this);
		firstFloor_panel.add(button1_27, "10, 28");
		
		JButton button1_28 = new JButton("28");
		button1_28.setName("button1_28");
		button1_28.addActionListener(this);
		firstFloor_panel.add(button1_28, "12, 28");
		
		JButton button1_29 = new JButton("29");
		button1_29.setName("button1_29");
		button1_29.addActionListener(this);
		firstFloor_panel.add(button1_29, "14, 28");
		
		JButton button1_30 = new JButton("30");
		button1_30.setName("button1_30");
		button1_30.addActionListener(this);
		firstFloor_panel.add(button1_30, "16, 28");
		
		JButton button1_33 = new JButton("33");
		button1_33.setName("button1_33");
		button1_33.addActionListener(this);
		firstFloor_panel.add(button1_33, "20, 28");
		
		JButton button1_34 = new JButton("34");
		button1_34.setName("button1_34");
		button1_34.addActionListener(this);
		firstFloor_panel.add(button1_34, "22, 28");
		
		secondFloor_panel = new JPanel();
		tabbedPane.addTab("2楼", null, secondFloor_panel, null);
		secondFloor_panel.setLayout(new FormLayout(new ColumnSpec[] 
				{
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton button2_1 = new JButton("1");
		button2_1.setName("button2_1");
		button2_1.addActionListener(this);
		secondFloor_panel.add(button2_1, "2, 2");
		
		JButton button2_2 = new JButton("2");
		button2_2.setName("button2_2");
		button2_2.addActionListener(this);
		secondFloor_panel.add(button2_2, "4, 2");
		
		JButton button2_47 = new JButton("47");
		button2_47.setName("button2_47");
		button2_47.addActionListener(this);
		secondFloor_panel.add(button2_47, "20, 2");
		
		JButton button2_48 = new JButton("48");
		button2_48.setName("button2_48");
		button2_48.addActionListener(this);
		secondFloor_panel.add(button2_48, "22, 2");
		
		JButton button2_3 = new JButton("3");
		button2_3.setName("button2_3");
		button2_3.addActionListener(this);
		secondFloor_panel.add(button2_3, "2, 4");
		
		JButton button2_4 = new JButton("4");
		button2_4.setName("button2_4");
		button2_4.addActionListener(this);
		secondFloor_panel.add(button2_4, "4, 4");
		
		JButton button2_49 = new JButton("49");
		button2_49.setName("button2_49");
		button2_49.addActionListener(this);
		secondFloor_panel.add(button2_49, "20, 4");
		
		JButton button2_50 = new JButton("50");
		button2_50.setName("button2_50");
		button2_50.addActionListener(this);
		secondFloor_panel.add(button2_50, "22, 4");
		
		JButton button2_5 = new JButton("5");
		button2_5.setName("button2_5");
		button2_5.addActionListener(this);
		secondFloor_panel.add(button2_5, "2, 8");
		
		JButton button2_6 = new JButton("6");
		button2_6.setName("button2_6");
		button2_6.addActionListener(this);
		secondFloor_panel.add(button2_6, "4, 8");
		
		JButton button2_43 = new JButton("43");
		button2_43.setName("button2_43");
		button2_43.addActionListener(this);
		secondFloor_panel.add(button2_43, "20, 8");
		
		JButton button2_44 = new JButton("44");
		button2_44.setName("button2_44");
		button2_44.addActionListener(this);
		secondFloor_panel.add(button2_44, "22, 8");
		
		JButton button2_7 = new JButton("7");
		button2_7.setName("button2_7");
		button2_7.addActionListener(this);
		secondFloor_panel.add(button2_7, "2, 10");
		
		JButton button2_8 = new JButton("8");
		button2_8.setName("button2_8");
		button2_8.addActionListener(this);
		secondFloor_panel.add(button2_8, "4, 10");
		
		JButton button2_45 = new JButton("45");
		button2_45.setName("button2_45");
		button2_45.addActionListener(this);
		secondFloor_panel.add(button2_45, "20, 10");
		
		JButton button2_46 = new JButton("46");
		button2_46.setName("button2_46");
		button2_46.addActionListener(this);
		secondFloor_panel.add(button2_46, "22, 10");
		
		JButton button2_9 = new JButton("9");
		button2_9.setName("button2_9");
		button2_9.addActionListener(this);
		secondFloor_panel.add(button2_9, "2, 14");
		
		JButton button2_10 = new JButton("10");
		button2_10.setName("button2_10");
		button2_10.addActionListener(this);
		secondFloor_panel.add(button2_10, "4, 14");
		
		JButton button2_39 = new JButton("39");
		button2_39.setName("button2_39");
		button2_39.addActionListener(this);
		secondFloor_panel.add(button2_39, "20, 14");
		
		JButton button2_40 = new JButton("40");
		button2_40.setName("button2_40");
		button2_40.addActionListener(this);
		secondFloor_panel.add(button2_40, "22, 14");
		
		JButton button2_11 = new JButton("11");
		button2_11.setName("button2_11");
		button2_11.addActionListener(this);
		secondFloor_panel.add(button2_11, "2, 16");
		
		JButton button2_12 = new JButton("12");
		button2_12.setName("button2_12");
		button2_12.addActionListener(this);
		secondFloor_panel.add(button2_12, "4, 16");
		
		JButton button2_41 = new JButton("41");
		button2_41.setName("button2_41");
		button2_41.addActionListener(this);
		secondFloor_panel.add(button2_41, "20, 16");
		
		JButton button2_42 = new JButton("42");
		button2_42.setName("button2_42");
		button2_42.addActionListener(this);
		secondFloor_panel.add(button2_42, "22, 16");
		
		JButton button2_13 = new JButton("13");
		button2_13.setName("button2_13");
		button2_13.addActionListener(this);
		secondFloor_panel.add(button2_13, "2, 20");
		
		JButton button2_14 = new JButton("14");
		button2_14.setName("button2_14");
		button2_14.addActionListener(this);
		secondFloor_panel.add(button2_14, "4, 20");
		
		JButton button2_35 = new JButton("35");
		button2_35.setName("button2_35");
		button2_35.addActionListener(this);
		secondFloor_panel.add(button2_35, "20, 20");
		
		JButton button2_36 = new JButton("36");
		button2_36.setName("button2_36");
		button2_36.addActionListener(this);
		secondFloor_panel.add(button2_36, "22, 20");
		
		JButton button2_15 = new JButton("15");
		button2_15.setName("button2_15");
		button2_15.addActionListener(this);
		secondFloor_panel.add(button2_15, "2, 22");
		
		JButton button2_16 = new JButton("16");
		button2_16.setName("button2_16");
		button2_16.addActionListener(this);
		secondFloor_panel.add(button2_16, "4, 22");
		
		JButton button2_37 = new JButton("37");
		button2_37.setName("button2_37");
		button2_37.addActionListener(this);
		secondFloor_panel.add(button2_37, "20, 22");
		
		JButton button2_38 = new JButton("38");
		button2_38.setName("button2_38");
		button2_38.addActionListener(this);
		secondFloor_panel.add(button2_38, "22, 22");
		
		JButton button2_17 = new JButton("17");
		button2_17.setName("button2_17");
		button2_17.addActionListener(this);
		secondFloor_panel.add(button2_17, "2, 26");
		
		JButton button2_18 = new JButton("18");
		button2_18.setName("button2_18");
		button2_18.addActionListener(this);
		secondFloor_panel.add(button2_18, "4, 26");
		
		JButton button2_21 = new JButton("21");
		button2_21.setName("button2_21");
		button2_21.addActionListener(this);
		secondFloor_panel.add(button2_21, "8, 26");
		
		JButton button2_22 = new JButton("22");
		button2_22.setName("button2_22");
		button2_22.addActionListener(this);
		secondFloor_panel.add(button2_22, "10, 26");
		
		JButton button2_23 = new JButton("23");
		button2_23.setName("button2_23");
		button2_23.addActionListener(this);
		secondFloor_panel.add(button2_23, "12, 26");
		
		JButton button2_24 = new JButton("24");
		button2_24.setName("button2_24");
		button2_24.addActionListener(this);
		secondFloor_panel.add(button2_24, "14, 26");
		
		JButton button2_25 = new JButton("25");
		button2_25.setName("button2_25");
		button2_25.addActionListener(this);
		secondFloor_panel.add(button2_25, "16, 26");
		
		JButton button2_31 = new JButton("31");
		button2_31.setName("button2_31");
		button2_31.addActionListener(this);
		secondFloor_panel.add(button2_31, "20, 26");
		
		JButton button2_32 = new JButton("32");
		button2_32.setName("button2_32");
		button2_32.addActionListener(this);
		secondFloor_panel.add(button2_32, "22, 26");
		
		JButton button2_19 = new JButton("19");
		button2_19.setName("button2_19");
		button2_19.addActionListener(this);
		secondFloor_panel.add(button2_19, "2, 28");
		
		JButton button2_20 = new JButton("20");
		button2_20.setName("button2_20");
		button2_20.addActionListener(this);
		secondFloor_panel.add(button2_20, "4, 28");
		
		JButton button2_26 = new JButton("26");
		button2_26.setName("button2_26");
		button2_26.addActionListener(this);
		secondFloor_panel.add(button2_26, "8, 28");
		
		JButton button2_27 = new JButton("27");
		button2_27.setName("button2_27");
		button2_27.addActionListener(this);
		secondFloor_panel.add(button2_27, "10, 28");
		
		JButton button2_28 = new JButton("28");
		button2_28.setName("button2_28");
		button2_28.addActionListener(this);
		secondFloor_panel.add(button2_28, "12, 28");
		
		JButton button2_29 = new JButton("29");
		button2_29.setName("button2_29");
		button2_29.addActionListener(this);
		secondFloor_panel.add(button2_29, "14, 28");
		
		JButton button2_30 = new JButton("30");
		button2_30.setName("button2_30");
		button2_30.addActionListener(this);
		secondFloor_panel.add(button2_30, "16, 28");
		
		JButton button2_33 = new JButton("33");
		button2_33.setName("button2_33");
		button2_33.addActionListener(this);
		secondFloor_panel.add(button2_33, "20, 28");
		
		JButton button2_34 = new JButton("34");
		button2_34.setName("button2_34");
		button2_34.addActionListener(this);
		secondFloor_panel.add(button2_34, "22, 28");
		
		thirdFloor_panel = new JPanel();
		tabbedPane.addTab("3楼", null, thirdFloor_panel, null);
		thirdFloor_panel.setLayout(new FormLayout(new ColumnSpec[] 
				{
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton button3_1 = new JButton("1");
		button3_1.setName("button3_1");
		button3_1.addActionListener(this);
		thirdFloor_panel.add(button3_1, "2, 2");
		
		JButton button3_2 = new JButton("2");
		button3_2.setName("button3_2");
		button3_2.addActionListener(this);
		thirdFloor_panel.add(button3_2, "4, 2");
		
		JButton button3_47 = new JButton("47");
		button3_47.setName("button3_47");
		button3_47.addActionListener(this);
		thirdFloor_panel.add(button3_47, "20, 2");
		
		JButton button3_48 = new JButton("48");
		button3_48.setName("button3_48");
		button3_48.addActionListener(this);
		thirdFloor_panel.add(button3_48, "22, 2");
		
		JButton button3_3 = new JButton("3");
		button3_3.setName("button3_3");
		button3_3.addActionListener(this);
		thirdFloor_panel.add(button3_3, "2, 4");
		
		JButton button3_4 = new JButton("4");
		button3_4.setName("button3_4");
		button3_4.addActionListener(this);
		thirdFloor_panel.add(button3_4, "4, 4");
		
		JButton button3_49 = new JButton("49");
		button3_49.setName("button3_49");
		button3_49.addActionListener(this);
		thirdFloor_panel.add(button3_49, "20, 4");
		
		JButton button3_50 = new JButton("50");
		button3_50.setName("button3_50");
		button3_50.addActionListener(this);
		thirdFloor_panel.add(button3_50, "22, 4");
		
		JButton button3_5 = new JButton("5");
		button3_5.setName("button3_5");
		button3_5.addActionListener(this);
		thirdFloor_panel.add(button3_5, "2, 8");
		
		JButton button3_6 = new JButton("6");
		button3_6.setName("button3_6");
		button3_6.addActionListener(this);
		thirdFloor_panel.add(button3_6, "4, 8");
		
		JButton button3_43 = new JButton("43");
		button3_43.setName("button3_43");
		button3_43.addActionListener(this);
		thirdFloor_panel.add(button3_43, "20, 8");
		
		JButton button3_44 = new JButton("44");
		button3_44.setName("button3_44");
		button3_44.addActionListener(this);
		thirdFloor_panel.add(button3_44, "22, 8");
		
		JButton button3_7 = new JButton("7");
		button3_7.setName("button3_7");
		button3_7.addActionListener(this);
		thirdFloor_panel.add(button3_7, "2, 10");
		
		JButton button3_8 = new JButton("8");
		button3_8.setName("button3_8");
		button3_8.addActionListener(this);
		thirdFloor_panel.add(button3_8, "4, 10");
		
		JButton button3_45 = new JButton("45");
		button3_45.setName("button3_45");
		button3_45.addActionListener(this);
		thirdFloor_panel.add(button3_45, "20, 10");
		
		JButton button3_46 = new JButton("46");
		button3_46.setName("button3_46");
		button3_46.addActionListener(this);
		thirdFloor_panel.add(button3_46, "22, 10");
		
		JButton button3_9 = new JButton("9");
		button3_9.setName("button3_9");
		button3_9.addActionListener(this);
		thirdFloor_panel.add(button3_9, "2, 14");
		
		JButton button3_10 = new JButton("10");
		button3_10.setName("button3_10");
		button3_10.addActionListener(this);
		thirdFloor_panel.add(button3_10, "4, 14");
		
		JButton button3_39 = new JButton("39");
		button3_39.setName("button3_39");
		button3_39.addActionListener(this);
		thirdFloor_panel.add(button3_39, "20, 14");
		
		JButton button3_40 = new JButton("40");
		button3_40.setName("button3_40");
		button3_40.addActionListener(this);
		thirdFloor_panel.add(button3_40, "22, 14");
		
		JButton button3_11 = new JButton("11");
		button3_11.setName("button3_11");
		button3_11.addActionListener(this);
		thirdFloor_panel.add(button3_11, "2, 16");
		
		JButton button3_12 = new JButton("12");
		button3_12.setName("button3_12");
		button3_12.addActionListener(this);
		thirdFloor_panel.add(button3_12, "4, 16");
		
		JButton button3_41 = new JButton("41");
		button3_41.setName("button3_41");
		button3_41.addActionListener(this);
		thirdFloor_panel.add(button3_41, "20, 16");
		
		JButton button3_42 = new JButton("42");
		button3_42.setName("button3_42");
		button3_42.addActionListener(this);
		thirdFloor_panel.add(button3_42, "22, 16");
		
		JButton button3_13 = new JButton("13");
		button3_13.setName("button3_13");
		button3_13.addActionListener(this);
		thirdFloor_panel.add(button3_13, "2, 20");
		
		JButton button3_14 = new JButton("14");
		button3_14.setName("button3_14");
		button3_14.addActionListener(this);
		thirdFloor_panel.add(button3_14, "4, 20");
		
		JButton button3_35 = new JButton("35");
		button3_35.setName("button3_35");
		button3_35.addActionListener(this);
		thirdFloor_panel.add(button3_35, "20, 20");
		
		JButton button3_36 = new JButton("36");
		button3_36.setName("button3_36");
		button3_36.addActionListener(this);
		thirdFloor_panel.add(button3_36, "22, 20");
		
		JButton button3_15 = new JButton("15");
		button3_15.setName("button3_15");
		button3_15.addActionListener(this);
		thirdFloor_panel.add(button3_15, "2, 22");
		
		JButton button3_16 = new JButton("16");
		button3_16.setName("button3_16");
		button3_16.addActionListener(this);
		thirdFloor_panel.add(button3_16, "4, 22");
		
		JButton button3_37 = new JButton("37");
		button3_37.setName("button3_37");
		button3_37.addActionListener(this);
		thirdFloor_panel.add(button3_37, "20, 22");
		
		JButton button3_38 = new JButton("38");
		button3_38.setName("button3_38");
		button3_38.addActionListener(this);
		thirdFloor_panel.add(button3_38, "22, 22");
		
		JButton button3_17 = new JButton("17");
		button3_17.setName("button3_17");
		button3_17.addActionListener(this);
		thirdFloor_panel.add(button3_17, "2, 26");
		
		JButton button3_18 = new JButton("18");
		button3_18.setName("button3_18");
		button3_18.addActionListener(this);
		thirdFloor_panel.add(button3_18, "4, 26");
		
		JButton button3_21 = new JButton("21");
		button3_21.setName("button3_21");
		button3_21.addActionListener(this);
		thirdFloor_panel.add(button3_21, "8, 26");
		
		JButton button3_22 = new JButton("22");
		button3_22.setName("button3_22");
		button3_22.addActionListener(this);
		thirdFloor_panel.add(button3_22, "10, 26");
		
		JButton button3_23 = new JButton("23");
		button3_23.setName("button3_23");
		button3_23.addActionListener(this);
		thirdFloor_panel.add(button3_23, "12, 26");
		
		JButton button3_24 = new JButton("24");
		button3_24.setName("button3_24");
		button3_24.addActionListener(this);
		thirdFloor_panel.add(button3_24, "14, 26");
		
		JButton button3_25 = new JButton("25");
		button3_25.setName("button3_25");
		button3_25.addActionListener(this);
		thirdFloor_panel.add(button3_25, "16, 26");
		
		JButton button3_31 = new JButton("31");
		button3_31.setName("button3_31");
		button3_31.addActionListener(this);
		thirdFloor_panel.add(button3_31, "20, 26");
		
		JButton button3_32 = new JButton("32");
		button3_32.setName("button3_32");
		button3_32.addActionListener(this);
		thirdFloor_panel.add(button3_32, "22, 26");
		
		JButton button3_19 = new JButton("19");
		button3_19.setName("button3_19");
		button3_19.addActionListener(this);
		thirdFloor_panel.add(button3_19, "2, 28");
		
		JButton button3_20 = new JButton("20");
		button3_20.setName("button3_20");
		button3_20.addActionListener(this);
		thirdFloor_panel.add(button3_20, "4, 28");
		
		JButton button3_26 = new JButton("26");
		button3_26.setName("button3_26");
		button3_26.addActionListener(this);
		thirdFloor_panel.add(button3_26, "8, 28");
		
		JButton button3_27 = new JButton("27");
		button3_27.setName("button3_27");
		button3_27.addActionListener(this);
		thirdFloor_panel.add(button3_27, "10, 28");
		
		JButton button3_28 = new JButton("28");
		button3_28.setName("button3_28");
		button3_28.addActionListener(this);
		thirdFloor_panel.add(button3_28, "12, 28");
		
		JButton button3_29 = new JButton("29");
		button3_29.setName("button3_29");
		button3_29.addActionListener(this);
		thirdFloor_panel.add(button3_29, "14, 28");
		
		JButton button3_30 = new JButton("30");
		button3_30.setName("button3_30");
		button3_30.addActionListener(this);
		thirdFloor_panel.add(button3_30, "16, 28");
		
		JButton button3_33 = new JButton("33");
		button3_33.setName("button3_33");
		button3_33.addActionListener(this);
		thirdFloor_panel.add(button3_33, "20, 28");
		
		JButton button3_34 = new JButton("34");
		button3_34.setName("button3_34");
		button3_34.addActionListener(this);
		thirdFloor_panel.add(button3_34, "22, 28");
		
		fourthFloor_panel = new JPanel();
		tabbedPane.addTab("4楼", null, fourthFloor_panel, null);
		fourthFloor_panel.setLayout(new FormLayout(new ColumnSpec[] 
				{
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton button4_1 = new JButton("1");
		button4_1.setName("button4_1");
		button4_1.addActionListener(this);
		fourthFloor_panel.add(button4_1, "2, 2");
		
		JButton button4_2 = new JButton("2");
		button4_2.setName("button4_2");
		button4_2.addActionListener(this);
		fourthFloor_panel.add(button4_2, "4, 2");
		
		JButton button4_47 = new JButton("47");
		button4_47.setName("button4_47");
		button4_47.addActionListener(this);
		fourthFloor_panel.add(button4_47, "20, 2");
		
		JButton button4_48 = new JButton("48");
		button4_48.setName("button4_48");
		button4_48.addActionListener(this);
		fourthFloor_panel.add(button4_48, "22, 2");
		
		JButton button4_3 = new JButton("3");
		button4_3.setName("button4_3");
		button4_3.addActionListener(this);
		fourthFloor_panel.add(button4_3, "2, 4");
		
		JButton button4_4 = new JButton("4");
		button4_4.setName("button4_4");
		button4_4.addActionListener(this);
		fourthFloor_panel.add(button4_4, "4, 4");
		
		JButton button4_49 = new JButton("49");
		button4_49.setName("button4_49");
		button4_49.addActionListener(this);
		fourthFloor_panel.add(button4_49, "20, 4");
		
		JButton button4_50 = new JButton("50");
		button4_50.setName("button4_50");
		button4_50.addActionListener(this);
		fourthFloor_panel.add(button4_50, "22, 4");
		
		JButton button4_5 = new JButton("5");
		button4_5.setName("button4_5");
		button4_5.addActionListener(this);
		fourthFloor_panel.add(button4_5, "2, 8");
		
		JButton button4_6 = new JButton("6");
		button4_6.setName("button4_6");
		button4_6.addActionListener(this);
		fourthFloor_panel.add(button4_6, "4, 8");
		
		JButton button4_43 = new JButton("43");
		button4_43.setName("button4_43");
		button4_43.addActionListener(this);
		fourthFloor_panel.add(button4_43, "20, 8");
		
		JButton button4_44 = new JButton("44");
		button4_44.setName("button4_44");
		button4_44.addActionListener(this);
		fourthFloor_panel.add(button4_44, "22, 8");
		
		JButton button4_7 = new JButton("7");
		button4_7.setName("button4_7");
		button4_7.addActionListener(this);
		fourthFloor_panel.add(button4_7, "2, 10");
		
		JButton button4_8 = new JButton("8");
		button4_8.setName("button4_8");
		button4_8.addActionListener(this);
		fourthFloor_panel.add(button4_8, "4, 10");
		
		JButton button4_45 = new JButton("45");
		button4_45.setName("button4_45");
		button4_45.addActionListener(this);
		fourthFloor_panel.add(button4_45, "20, 10");
		
		JButton button4_46 = new JButton("46");
		button4_46.setName("button4_46");
		button4_46.addActionListener(this);
		fourthFloor_panel.add(button4_46, "22, 10");
		
		JButton button4_9 = new JButton("9");
		button4_9.setName("button4_9");
		button4_9.addActionListener(this);
		fourthFloor_panel.add(button4_9, "2, 14");
		
		JButton button4_10 = new JButton("10");
		button4_10.setName("button4_10");
		button4_10.addActionListener(this);
		fourthFloor_panel.add(button4_10, "4, 14");
		
		JButton button4_39 = new JButton("39");
		button4_39.setName("button4_39");
		button4_39.addActionListener(this);
		fourthFloor_panel.add(button4_39, "20, 14");
		
		JButton button4_40 = new JButton("40");
		button4_40.setName("button4_40");
		button4_40.addActionListener(this);
		fourthFloor_panel.add(button4_40, "22, 14");
		
		JButton button4_11 = new JButton("11");
		button4_11.setName("button4_11");
		button4_11.addActionListener(this);
		fourthFloor_panel.add(button4_11, "2, 16");
		
		JButton button4_12 = new JButton("12");
		button4_12.setName("button4_12");
		button4_12.addActionListener(this);
		fourthFloor_panel.add(button4_12, "4, 16");
		
		JButton button4_41 = new JButton("41");
		button4_41.setName("button4_41");
		button4_41.addActionListener(this);
		fourthFloor_panel.add(button4_41, "20, 16");
		
		JButton button4_42 = new JButton("42");
		button4_42.setName("button4_42");
		button4_42.addActionListener(this);
		fourthFloor_panel.add(button4_42, "22, 16");
		
		JButton button4_13 = new JButton("13");
		button4_13.setName("button4_13");
		button4_13.addActionListener(this);
		fourthFloor_panel.add(button4_13, "2, 20");
		
		JButton button4_14 = new JButton("14");
		button4_14.setName("button4_14");
		button4_14.addActionListener(this);
		fourthFloor_panel.add(button4_14, "4, 20");
		
		JButton button4_35 = new JButton("35");
		button4_35.setName("button4_35");
		button4_35.addActionListener(this);
		fourthFloor_panel.add(button4_35, "20, 20");
		
		JButton button4_36 = new JButton("36");
		button4_36.setName("button4_36");
		button4_36.addActionListener(this);
		fourthFloor_panel.add(button4_36, "22, 20");
		
		JButton button4_15 = new JButton("15");
		button4_15.setName("button4_15");
		button4_15.addActionListener(this);
		fourthFloor_panel.add(button4_15, "2, 22");
		
		JButton button4_16 = new JButton("16");
		button4_16.setName("button4_16");
		button4_16.addActionListener(this);
		fourthFloor_panel.add(button4_16, "4, 22");
		
		JButton button4_37 = new JButton("37");
		button4_37.setName("button4_37");
		button4_37.addActionListener(this);
		fourthFloor_panel.add(button4_37, "20, 22");
		
		JButton button4_38 = new JButton("38");
		button4_38.setName("button4_38");
		button4_38.addActionListener(this);
		fourthFloor_panel.add(button4_38, "22, 22");
		
		JButton button4_17 = new JButton("17");
		button4_17.setName("button4_17");
		button4_17.addActionListener(this);
		fourthFloor_panel.add(button4_17, "2, 26");
		
		JButton button4_18 = new JButton("18");
		button4_18.setName("button4_18");
		button4_18.addActionListener(this);
		fourthFloor_panel.add(button4_18, "4, 26");
		
		JButton button4_21 = new JButton("21");
		button4_21.setName("button4_21");
		button4_21.addActionListener(this);
		fourthFloor_panel.add(button4_21, "8, 26");
		
		JButton button4_22 = new JButton("22");
		button4_22.setName("button4_22");
		button4_22.addActionListener(this);
		fourthFloor_panel.add(button4_22, "10, 26");
		
		JButton button4_23 = new JButton("23");
		button4_23.setName("button4_23");
		button4_23.addActionListener(this);
		fourthFloor_panel.add(button4_23, "12, 26");
		
		JButton button4_24 = new JButton("24");
		button4_24.setName("button4_24");
		button4_24.addActionListener(this);
		fourthFloor_panel.add(button4_24, "14, 26");
		
		JButton button4_25 = new JButton("25");
		button4_25.setName("button4_25");
		button4_25.addActionListener(this);
		fourthFloor_panel.add(button4_25, "16, 26");
		
		JButton button4_31 = new JButton("31");
		button4_31.setName("button4_31");
		button4_31.addActionListener(this);
		fourthFloor_panel.add(button4_31, "20, 26");
		
		JButton button4_32 = new JButton("32");
		button4_32.setName("button4_32");
		button4_32.addActionListener(this);
		fourthFloor_panel.add(button4_32, "22, 26");
		
		JButton button4_19 = new JButton("19");
		button4_19.setName("button4_19");
		button4_19.addActionListener(this);
		fourthFloor_panel.add(button4_19, "2, 28");
		
		JButton button4_20 = new JButton("20");
		button4_20.setName("button4_20");
		button4_20.addActionListener(this);
		fourthFloor_panel.add(button4_20, "4, 28");
		
		JButton button4_26 = new JButton("26");
		button4_26.setName("button4_26");
		button4_26.addActionListener(this);
		fourthFloor_panel.add(button4_26, "8, 28");
		
		JButton button4_27 = new JButton("27");
		button4_27.setName("button4_27");
		button4_27.addActionListener(this);
		fourthFloor_panel.add(button4_27, "10, 28");
		
		JButton button4_28 = new JButton("28");
		button4_28.setName("button4_28");
		button4_28.addActionListener(this);
		fourthFloor_panel.add(button4_28, "12, 28");
		
		JButton button4_29 = new JButton("29");
		button4_29.setName("button4_29");
		button4_29.addActionListener(this);
		fourthFloor_panel.add(button4_29, "14, 28");
		
		JButton button4_30 = new JButton("30");
		button4_30.setName("button4_30");
		button4_30.addActionListener(this);
		fourthFloor_panel.add(button4_30, "16, 28");
		
		JButton button4_33 = new JButton("33");
		button4_33.setName("button4_33");
		button4_33.addActionListener(this);
		fourthFloor_panel.add(button4_33, "20, 28");
		
		JButton button4_34 = new JButton("34");
		button4_34.setName("button4_34");
		button4_34.addActionListener(this);
		fourthFloor_panel.add(button4_34, "22, 28");
		
		fifthFloor_panel = new JPanel();
		tabbedPane.addTab("5楼", null, fifthFloor_panel, null);
		fifthFloor_panel.setLayout(new FormLayout(new ColumnSpec[] 
				{
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JButton button5_1 = new JButton("1");
		button5_1.setName("button5_1");
		button5_1.addActionListener(this);
		fifthFloor_panel.add(button5_1, "2, 2");
		
		JButton button5_2 = new JButton("2");
		button5_2.setName("button5_2");
		button5_2.addActionListener(this);
		fifthFloor_panel.add(button5_2, "4, 2");
		
		JButton button5_47 = new JButton("47");
		button5_47.setName("button5_47");
		button5_47.addActionListener(this);
		fifthFloor_panel.add(button5_47, "20, 2");
		
		JButton button5_48 = new JButton("48");
		button5_48.setName("button5_48");
		button5_48.addActionListener(this);
		fifthFloor_panel.add(button5_48, "22, 2");
		
		JButton button5_3 = new JButton("3");
		button5_3.setName("button5_3");
		button5_3.addActionListener(this);
		fifthFloor_panel.add(button5_3, "2, 4");
		
		JButton button5_4 = new JButton("4");
		button5_4.setName("button5_4");
		button5_4.addActionListener(this);
		fifthFloor_panel.add(button5_4, "4, 4");
		
		JButton button5_49 = new JButton("49");
		button5_49.setName("button5_49");
		button5_49.addActionListener(this);
		fifthFloor_panel.add(button5_49, "20, 4");
		
		JButton button5_50 = new JButton("50");
		button5_50.setName("button5_50");
		button5_50.addActionListener(this);
		fifthFloor_panel.add(button5_50, "22, 4");
		
		JButton button5_5 = new JButton("5");
		button5_5.setName("button5_5");
		button5_5.addActionListener(this);
		fifthFloor_panel.add(button5_5, "2, 8");
		
		JButton button5_6 = new JButton("6");
		button5_6.setName("button5_6");
		button5_6.addActionListener(this);
		fifthFloor_panel.add(button5_6, "4, 8");
		
		JButton button5_43 = new JButton("43");
		button5_43.setName("button5_43");
		button5_43.addActionListener(this);
		fifthFloor_panel.add(button5_43, "20, 8");
		
		JButton button5_44 = new JButton("44");
		button5_44.setName("button5_44");
		button5_44.addActionListener(this);
		fifthFloor_panel.add(button5_44, "22, 8");
		
		JButton button5_7 = new JButton("7");
		button5_7.setName("button5_7");
		button5_7.addActionListener(this);
		fifthFloor_panel.add(button5_7, "2, 10");
		
		JButton button5_8 = new JButton("8");
		button5_8.setName("button5_8");
		button5_8.addActionListener(this);
		fifthFloor_panel.add(button5_8, "4, 10");
		
		JButton button5_45 = new JButton("45");
		button5_45.setName("button5_45");
		button5_45.addActionListener(this);
		fifthFloor_panel.add(button5_45, "20, 10");
		
		JButton button5_46 = new JButton("46");
		button5_46.setName("button5_46");
		button5_46.addActionListener(this);
		fifthFloor_panel.add(button5_46, "22, 10");
		
		JButton button5_9 = new JButton("9");
		button5_9.setName("button5_9");
		button5_9.addActionListener(this);
		fifthFloor_panel.add(button5_9, "2, 14");
		
		JButton button5_10 = new JButton("10");
		button5_10.setName("button5_10");
		button5_10.addActionListener(this);
		fifthFloor_panel.add(button5_10, "4, 14");
		
		JButton button5_39 = new JButton("39");
		button5_39.setName("button5_39");
		button5_39.addActionListener(this);
		fifthFloor_panel.add(button5_39, "20, 14");
		
		JButton button5_40 = new JButton("40");
		button5_40.setName("button5_40");
		button5_40.addActionListener(this);
		fifthFloor_panel.add(button5_40, "22, 14");
		
		JButton button5_11 = new JButton("11");
		button5_11.setName("button5_11");
		button5_11.addActionListener(this);
		fifthFloor_panel.add(button5_11, "2, 16");
		
		JButton button5_12 = new JButton("12");
		button5_12.setName("button5_12");
		button5_12.addActionListener(this);
		fifthFloor_panel.add(button5_12, "4, 16");
		
		JButton button5_41 = new JButton("41");
		button5_41.setName("button5_41");
		button5_41.addActionListener(this);
		fifthFloor_panel.add(button5_41, "20, 16");
		
		JButton button5_42 = new JButton("42");
		button5_42.setName("button5_42");
		button5_42.addActionListener(this);
		fifthFloor_panel.add(button5_42, "22, 16");
		
		JButton button5_13 = new JButton("13");
		button5_13.setName("button5_13");
		button5_13.addActionListener(this);
		fifthFloor_panel.add(button5_13, "2, 20");
		
		JButton button5_14 = new JButton("14");
		button5_14.setName("button5_14");
		button5_14.addActionListener(this);
		fifthFloor_panel.add(button5_14, "4, 20");
		
		JButton button5_35 = new JButton("35");
		button5_35.setName("button5_35");
		button5_35.addActionListener(this);
		fifthFloor_panel.add(button5_35, "20, 20");
		
		JButton button5_36 = new JButton("36");
		button5_36.setName("button5_36");
		button5_36.addActionListener(this);
		fifthFloor_panel.add(button5_36, "22, 20");
		
		JButton button5_15 = new JButton("15");
		button5_15.setName("button5_15");
		button5_15.addActionListener(this);
		fifthFloor_panel.add(button5_15, "2, 22");
		
		JButton button5_16 = new JButton("16");
		button5_16.setName("button5_16");
		button5_16.addActionListener(this);
		fifthFloor_panel.add(button5_16, "4, 22");
		
		JButton button5_37 = new JButton("37");
		button5_37.setName("button5_37");
		button5_37.addActionListener(this);
		fifthFloor_panel.add(button5_37, "20, 22");
		
		JButton button5_38 = new JButton("38");
		button5_38.setName("button5_38");
		button5_38.addActionListener(this);
		fifthFloor_panel.add(button5_38, "22, 22");
		
		JButton button5_17 = new JButton("17");
		button5_17.setName("button5_17");
		button5_17.addActionListener(this);
		fifthFloor_panel.add(button5_17, "2, 26");
		
		JButton button5_18 = new JButton("18");
		button5_18.setName("button5_18");
		button5_18.addActionListener(this);
		fifthFloor_panel.add(button5_18, "4, 26");
		
		JButton button5_21 = new JButton("21");
		button5_21.setName("button5_21");
		button5_21.addActionListener(this);
		fifthFloor_panel.add(button5_21, "8, 26");
		
		JButton button5_22 = new JButton("22");
		button5_22.setName("button5_22");
		button5_22.addActionListener(this);
		fifthFloor_panel.add(button5_22, "10, 26");
		
		JButton button5_23 = new JButton("23");
		button5_23.setName("button5_23");
		button5_23.addActionListener(this);
		fifthFloor_panel.add(button5_23, "12, 26");
		
		JButton button5_24 = new JButton("24");
		button5_24.setName("button5_24");
		button5_24.addActionListener(this);
		fifthFloor_panel.add(button5_24, "14, 26");
		
		JButton button5_25 = new JButton("25");
		button5_25.setName("button5_25");
		button5_25.addActionListener(this);
		fifthFloor_panel.add(button5_25, "16, 26");
		
		JButton button5_31 = new JButton("31");
		button5_31.setName("button5_31");
		button5_31.addActionListener(this);
		fifthFloor_panel.add(button5_31, "20, 26");
		
		JButton button5_32 = new JButton("32");
		button5_32.setName("button5_32");
		button5_32.addActionListener(this);
		fifthFloor_panel.add(button5_32, "22, 26");
		
		JButton button5_19 = new JButton("19");
		button5_19.setName("button5_19");
		button5_19.addActionListener(this);
		fifthFloor_panel.add(button5_19, "2, 28");
		
		JButton button5_20 = new JButton("20");
		button5_20.setName("button5_20");
		button5_20.addActionListener(this);
		fifthFloor_panel.add(button5_20, "4, 28");
		
		JButton button5_26 = new JButton("26");
		button5_26.setName("button5_26");
		button5_26.addActionListener(this);
		fifthFloor_panel.add(button5_26, "8, 28");
		
		JButton button5_27 = new JButton("27");
		button5_27.setName("button5_27");
		button5_27.addActionListener(this);
		fifthFloor_panel.add(button5_27, "10, 28");
		
		JButton button5_28 = new JButton("28");
		button5_28.setName("button5_28");
		button5_28.addActionListener(this);
		fifthFloor_panel.add(button5_28, "12, 28");
		
		JButton button5_29 = new JButton("29");
		button5_29.setName("button5_29");
		button5_29.addActionListener(this);
		fifthFloor_panel.add(button5_29, "14, 28");
		
		JButton button5_30 = new JButton("30");
		button5_30.setName("button5_30");
		button5_30.addActionListener(this);
		fifthFloor_panel.add(button5_30, "16, 28");
		
		JButton button5_33 = new JButton("33");
		button5_33.setName("button5_33");
		button5_33.addActionListener(this);
		fifthFloor_panel.add(button5_33, "20, 28");
		
		JButton button5_34 = new JButton("34");
		button5_34.setName("button5_34");
		button5_34.addActionListener(this);
		fifthFloor_panel.add(button5_34, "22, 28");
		
	    createComponentMap();
	    //让被选中中的座位不可选
	    {			
			ConnectDatabase condata=new ConnectDatabase();
			{
				String sql = "select 所在楼层,编号 from seat where 读者编号 is not null";
				ResultSet rs = condata.queryExecute(sql, null);
				try {
					while(rs.next())
					{
						String seatFloor = rs.getString(1);
						String seatNumber = rs.getString(2);
						String buttonName = "button"+seatFloor+"_"+seatNumber;
						JButton button = (JButton) getComponentByName(buttonName);
						button.setEnabled(false);
					}
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
	    }
	}
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{	
		ConnectDatabase condata=new ConnectDatabase();
		String seatFloor = tabbedPane.getSelectedIndex()+1+"";
		String seatNumber = arg0.getActionCommand();
		String readerNumber = mainFrame.student.number;
		
		String sql = "UPDATE seat set 读者编号=? where 所在楼层=? and 编号=?";					
		String []paras = {readerNumber,seatFloor,seatNumber};//rowNum, 0 ：0是第0列
		boolean ju = condata.cudExecute(sql,paras);
		if (ju) 
		{
			JOptionPane.showMessageDialog(null, "选座成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
		else 
			JOptionPane.showMessageDialog(null, "选座失败", "提示", JOptionPane.ERROR_MESSAGE);
	}
	private void createComponentMap() 
	{
        componentMap = new HashMap<String,Component>();
        Component[] components1 = this.firstFloor_panel.getComponents();
        Component[] components2 = this.secondFloor_panel.getComponents();
        Component[] components3 = this.thirdFloor_panel.getComponents();
        Component[] components4 = this.fourthFloor_panel.getComponents();
        Component[] components5 = this.fifthFloor_panel.getComponents();
        
        for (int i=0; i < components1.length; i++) {
                componentMap.put(components1[i].getName(), components1[i]);
        }
        for (int i=0; i < components2.length; i++) {
            componentMap.put(components2[i].getName(), components2[i]);
        }
        for (int i=0; i < components3.length; i++) {
            componentMap.put(components3[i].getName(), components3[i]);
        }
        for (int i=0; i < components4.length; i++) {
            componentMap.put(components4[i].getName(), components4[i]);
        }
        for (int i=0; i < components5.length; i++) {
            componentMap.put(components5[i].getName(), components5[i]);
        }
	}

	public Component getComponentByName(String name) 
	{
        if (componentMap.containsKey(name)) {
                return (Component) componentMap.get(name);
        }
        else 
        	return null;
	}
}
