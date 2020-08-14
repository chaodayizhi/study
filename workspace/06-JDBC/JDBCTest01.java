/*
	JDBC ����
*/
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class JDBCTest01{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		try{
			//1��ע������
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			//2����ȡ����
			String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
			String user = "root";
			String password = "giantpanda";
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("���ݿ����Ӷ���"+conn);
			
			//3����ȡ���ݿ��������
			stmt = conn.createStatement();
			
			//4��ִ��SQL
			String sql = "insert into t_user(username) values('zhangsan')";
			//ר��ִ��DML���ģ�insert��delete��update��
			//����ֵ�ǡ�����Ӱ��ļ�¼��Ŀ����
			int count = stmt.executeUpdate(sql);//ִ��DML��䣬
			System.out.println(count == 1 ? "����ɹ�" : "����ʧ��");
			//5����������
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//6���ͷ���Դ
			//Ϊ�˱�֤��֤��Դһ���ͷţ���finally�����йر���Դ��
			//����Ҫ��ѭ����С����һ�ιرա�
			//�ֱ����try����catch
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