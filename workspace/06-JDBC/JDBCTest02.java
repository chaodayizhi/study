
import java.sql.*;
public class JDBCTest02{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		
		try{
			//1.ע������
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//2.��ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","giantpanda");
			
			//3.��ȡ���ݿ��������
			stmt = conn.createStatement();
			//4.ִ��sql
			//String sql = "delete from t_user where id = 6";
			String sql = "update t_user set username='lisi' where id=7";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "�ɹ�":"ʧ��");
			//5.��������
			
		}catch(SQLException e){
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