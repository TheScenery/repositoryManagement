package kcgl;

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

public class ItemIn extends JFrame {

	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_count;
	private JButton button_cancel;
	private JButton button_ok;

	public ItemIn() throws SQLException{
		setTitle("商品入库管理");
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
		label.setBounds(155, 61, 133, 32);
		contentPane.add(label);
		
		textField_id = new JTextField();
		textField_id.setBounds(326, 65, 150, 29);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		JLabel label_1 = new JLabel("商品名称：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		label_1.setBounds(155, 147, 101, 41);
		contentPane.add(label_1);
		
		textField_name = new JTextField();
		textField_name.setBounds(326, 151, 150, 29);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		JLabel label_2 = new JLabel("入库数量：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		label_2.setBounds(155, 230, 101, 32);
		contentPane.add(label_2);
		
		textField_count = new JTextField();
		textField_count.setBounds(326, 230, 150, 29);
		contentPane.add(textField_count);
		textField_count.setColumns(10);
		
		button_ok = new JButton("确定");
		button_ok.setFont(new Font("宋体", Font.PLAIN, 20));
		button_ok.setForeground(Color.DARK_GRAY);
		button_ok.setBackground(Color.LIGHT_GRAY);
		button_ok.setBounds(164, 317, 124, 41);
		contentPane.add(button_ok);
		
		button_cancel = new JButton("取消");
		button_cancel.setFont(new Font("宋体", Font.PLAIN, 20));
		button_cancel.setBackground(Color.LIGHT_GRAY);
		button_cancel.setForeground(Color.BLACK);
		button_cancel.setBounds(343, 317, 124, 41);
		contentPane.add(button_cancel);
		
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
					boolean f = d.insertItem(id, name, count);
					if(f) {
						d.record(id, MainFrame.AID, time, count);
						JOptionPane.showMessageDialog(null, "入库成功！", "提示", JOptionPane.INFORMATION_MESSAGE); 
					}
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
			}
		});
	}
}
