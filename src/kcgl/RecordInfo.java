package kcgl;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;

public class RecordInfo extends JFrame {

	private JTable table;
	public RecordInfo() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(350, 100, 650, 550);
		this.setResizable(false);
		setTitle("商品出入库记录信息");
		String[] name = {"记录编号","商品编号","商品名称","管理员编号","管理员名称","出入库数量","时间"};
		Vector <String[]> v = new Vector<String[]>();
		DA d = new DA(); 
		v = d.recordInfo();
		String[][] data = new String[v.size()][];
		for(int i=0;i<v.size();i++) {
			data[i] = v.get(i);
		}
		DefaultTableModel model = new DefaultTableModel(data,name); 
		table = new JTable(model);
		table.setCellSelectionEnabled(false);
		JScrollPane scrollpane = new JScrollPane(table);
		this.add(scrollpane);
		this.setVisible(true);
		table.setVisible(true);
	}
}
