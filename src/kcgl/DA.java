package kcgl;
import java.sql.*;
import java.util.Vector;

import javax.swing.JOptionPane;
public class DA {
	public static Connection buildConnectionWithCkgl() {
		Connection con = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ckgl","sa","open");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String searchAdminPassWord(String id) throws SQLException{
		String pass = null;
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select * from admin where aid = " + "'" + id + "'";
			ResultSet rs = st.executeQuery(Query);
			if (rs.next()) {
				pass = rs.getString(3);
			}
			rs.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			connection.close();
			e.printStackTrace();
			System.out.println("查找失败");
		}
		return pass;
	}
	
	public boolean insertItem(String id,String name,int count) throws SQLException  {
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select * from item where iid =" + "'" +id + "'";
			ResultSet rs = st.executeQuery(Query);
			if (rs.next()) {
				String update = "update item set icount = icount + " + String.valueOf(count) + "where iid = " + "'" +id + "'";
				st.executeUpdate(update);
			}
			else {
				String insert = "insert into item values(" + "'" + id + "'" + "," + "'" + name + "'" + "," +  count +")";
				st.executeUpdate(insert);			
			}
			rs.close();
			st.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
	}
	
	public boolean deleteItem(String id,String name,int count) throws SQLException  {
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select * from item where iid =" + "'" +id + "'";
			ResultSet rs = st.executeQuery(Query);
			if (rs.next()) {
				int c;
				c = rs.getInt(3);
				if(c >= count) {
					String update = "update item set icount = icount - " + String.valueOf(count) + "where iid = " + "'" +id + "'";
					st.executeUpdate(update);
					rs.close();
					st.close();
					return true;
				}
				else {
					rs.close();
					st.close();
					return false;
				}
			}
			else {
				rs.close();
				st.close();
				return false;		
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
	}
	
	public String[] searchItemByID(String id) throws SQLException {
		String[] sg = new String[3];
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select * from item where iid = " + id;
			ResultSet rs = st.executeQuery(Query);
			if(rs.next()) {
				sg[0] = rs.getString(1);
				sg[1] = rs.getString(2);
				sg[2] = String.valueOf(rs.getInt(3));
			}
			else {
				JOptionPane.showMessageDialog(null, "改商品不存在！", "警告", JOptionPane.ERROR_MESSAGE);
				return null;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return sg;
	}
	
	public boolean record(String iid,String aid,String time,int count) throws SQLException  {
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select rid from record";
			ResultSet rs = st.executeQuery(Query);
			ResultSet rs2;
			if (rs.next()) {
				long n;
				rs2 = st.executeQuery("select max(rid) from record");
				rs2.next();
				n = Long.valueOf(rs2.getString(1)).longValue();
				n = n + 1;
				String rid = String.valueOf(n);
				String insert = "insert into record values (" + "'" + rid + "'" +"," + "'" + iid + "'" + "," + "'" + aid + "'" + "," + "'" + time + "'" + "," + String.valueOf(count) + ")";
				st.executeUpdate(insert);
				rs.close();
				rs2.close();
				st.close();
				return true;
				}
			else {
				String insert = "insert into record values (" + "'" + "1000000001" + "'" +"," + "'" + iid + "'" + "," + "'" + aid + "'" + "," + "'" + time + "'" + "," + String.valueOf(count) + ")";
				st.executeUpdate(insert);
				rs.close();
				st.close();
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			connection.close();
		}
	}
	
	public Vector<String[]> itemInfo() throws SQLException {
		Vector <String[]> v = new Vector<String[]>();
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select * from item";
			ResultSet rs = st.executeQuery(Query);
			if (rs.next()) {
				do {
					String[] s = new String[3];
					s[0] = rs.getString(1);
					s[1] = rs.getString(2);
					s[2] = String.valueOf(rs.getInt(3));
					v.addElement(s);
				} while(rs.next());
				rs.close();
				st.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return v;
	}
	
	
	public Vector<String[]> recordInfo() throws SQLException {
		Vector <String[]> v = new Vector<String[]>();
		Connection connection = buildConnectionWithCkgl();
		try {
			Statement st = connection.createStatement();
			String Query = "select rid,item.iid,iname,admin.aid,aname,count,time from item inner join record on item.iid = record.iid inner join admin on record.aid = admin.aid";
			ResultSet rs = st.executeQuery(Query);
			if (rs.next()) {
				do {
					String[] s = new String[7];
					s[0] = rs.getString(1);
					s[1] = rs.getString(2);
					s[2] = rs.getString(3);
					s[3] = rs.getString(4);
					s[4] = rs.getString(5);
					s[5] = String.valueOf(rs.getInt(6));
					s[6] = rs.getString(7);
					v.addElement(s);
				} while(rs.next());
				rs.close();
				st.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			connection.close();
		}
		return v;
	}
}
