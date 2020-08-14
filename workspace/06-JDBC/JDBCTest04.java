/*
���������ݿ��������Ϣ���õ������ļ���
*/
import java.sql.*;
import java.util.*;
public class JDBCTest04{
	public static void main(String[] args){
		//ʹ����Դ�����������������ļ���
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		
		String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			//1.ע������
			//String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			//2.��ȡ����
			//String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
			//String user = "root";
			//String password = "giantpanda";
			conn = DriverManager.getConnection(url,user,password);
			
			//3.��ȡ���ݿ��������
			stmt = conn.createStatement();
			//4.ִ��sql
			//String sql = "delete from t_user where id = 6";
			String sql = "update t_user set username='lisi' where id=7";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "�ɹ�":"ʧ��");
			//5.��������
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6.�ͷ���Դ
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		
	}
	
}