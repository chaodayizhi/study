/*
	ע�������ĳ��÷�ʽ��
*/
import java.sql.*;

public class JDBCTest03{
	public static void main(String[] args){
		try{
			//1.ע������
			// ע�������ĵ�һ��д����������
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			//ע�������ĵڶ���д��(����)
			//Ϊʲô���ַ�ʽ���ã�
			//��Ϊ�������ַ������ַ�������д�������ļ��У�xx.properties��
			//���·�������Ҫ����ֵ����Ϊ����ֻ������������ض�����
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.��ȡ����
			Connection  conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","giantpanda");
			System.out.println(conn);
			//com.mysql.jdbc.JDBC4Connection@5a10411
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		
	}
}