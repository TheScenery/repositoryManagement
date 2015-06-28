package kcgl;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;

public class ItemOut extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_count;
	private JButton button_cancel;
	private JButton button_ok;
	private JLabel label_3;
	private JTextField textField_totalCount;
	private JButton button_search;

	public ItemOut() throws SQLException{
		setTitle("商品出库管理");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 650, 550);
		this.setVisible(true);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("商品编号：");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(42, 61, 133, 32);
		contentPane.add(label);
		
		textField_id = new JTextField();
		textField_id.setBounds(150, 65, 150, 29);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		JLabel label_1 = new JLabel("商品名称：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(355, 57, 101, 41);
		contentPane.add(label_1);
		
		textField_name = new JTextField();
		textField_name.setBounds(453, 65, 150, 29);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel label_2 = new JLabel("出库数量：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(42, 159, 101, 32);
		contentPane.add(label_2);
		
		textField_count = new JTextField();
		textField_count.setBounds(150, 163, 150, 29);
		contentPane.add(textField_count);
		textField_count.setColumns(10);
		
		button_ok = new JButton("确定");
		button_ok.setFont(new Font("宋体", Font.PLAIN, 20));
		button_ok.setForeground(Color.DARK_GRAY);
		button_ok.setBackground(Color.LIGHT_GRAY);
		button_ok.setBounds(256, 317, 124, 41);
		contentPane.add(button_ok);
		
		button_cancel = new JButton("取消");
		button_cancel.setFont(new Font("宋体", Font.PLAIN, 20));
		button_cancel.setBackground(Color.LIGHT_GRAY);
		button_cancel.setForeground(Color.BLACK);
		button_cancel.setBounds(425, 317, 124, 41);
		contentPane.add(button_cancel);
		
		label_3 = new JLabel("库存数量：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 20));
		label_3.setBounds(355, 150, 101, 41);
		contentPane.add(label_3);
		
		textField_totalCount = new JTextField();
		textField_totalCount.setColumns(10);
		textField_totalCount.setBounds(453, 159, 150, 29);
		contentPane.add(textField_totalCount);
		
		button_search = new JButton("查询");
		button_search.setForeground(Color.DARK_GRAY);
		button_search.setFont(new Font("宋体", Font.PLAIN, 20));
		button_search.setBackground(Color.LIGHT_GRAY);
		button_search.setBounds(84, 317, 124, 41);
		contentPane.add(button_search);
		
		button_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DA d = new DA();
				String id,name,time;
				int count;
				id = textField_id.getText();
				name = textField_name.getText();
				time = MainFrame.format.format(new Date());
				count = Integer.parseInt(textField_count.getText());
				try {
					boolean f = d.deleteItem(id, name, count);
					if(f) {
						d.record(id, MainFrame.AID, time, -count);
						JOptionPane.showMessageDialog(null, "商品出库成功！", "提示", JOptionPane.INFORMATION_MESSAGE); 
					}
					else {
						JOptionPane.showMessageDialog(null, "库存不足！出库失败", "警告", JOptionPane.ERROR_MESSAGE);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		button_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DA d = new DA();
				String[] sg;
				String id,name,totalCount;
				id = textField_id.getText();
				try {
					sg = d.searchItemByID(id);
					id = sg[0];
					name = sg[1];
					totalCount = sg[2];
					textField_name.setText(name);
					textField_totalCount.setText(totalCount);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		});
		
		
		button_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_id.setText("");
				textField_name.setText("");
				textField_count.setText("");
				textField_totalCount.setText("");
			}
		});
	}
}
