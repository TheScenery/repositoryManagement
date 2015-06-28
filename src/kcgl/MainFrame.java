package kcgl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;
public class MainFrame extends JFrame {
	public static boolean flag = false;
	public static String AID = null;
	public static DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	private static MainFrame mainFrame = new MainFrame();
	private static MenuBar menuBar = new MenuBar();
	private static Menu menu1 = new Menu("登录");
	private static Menu menu2 = new Menu("商品管理");
	private static Menu menu3 = new Menu("记录信息");
	private static MenuItem mi1 = new MenuItem("管理员登录");
	private static MenuItem mi2 = new MenuItem("商品入库");
	private static MenuItem mi3 = new MenuItem("商品出库");
	private static MenuItem mi4 = new MenuItem("库存信息");
	private static MenuItem mi5 = new MenuItem("商品出出入库记录");
	public static void main(String args[]) throws SQLException {
		mainFrame.setMenuBar(menuBar);
		mainFrame.setBounds(300, 50, 800, 600);
		menuBar.add(menu1);
		//menuBar.add(menu2);
		//menuBar.add(menu3);
		menu1.add(mi1);
		menu2.add(mi2);
		menu2.add(mi3);
		menu2.add(mi4);
		menu3.add(mi5);
		mainFrame.setTitle("超市仓库管理信息系统");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		mi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogIn logIn = new LogIn();
			}
		});
		
		mi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ItemIn itemIn = new ItemIn();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		mi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ItemOut itemOut = new ItemOut();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mi4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ItemInfo itemInfo = new ItemInfo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		mi5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					RecordInfo rf = new RecordInfo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
	//RecordInfo r = new RecordInfo();
	}	
	
	public static void reset() {
		menuBar.add(menu2);
		menuBar.add(menu3);
	}
}
