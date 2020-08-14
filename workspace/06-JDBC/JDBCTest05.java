/*
	�����ѯ�����(���������)
*/
import java.sql.*;

public class JDBCTest05{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			//1��ע������
			Class.forName("com.mysql.jdbc.Driver");
			//2����ȡ����
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","giantpanda");
			//3����ȡ���ݿ��������
			stmt = conn.createStatement();
			//4��ִ��SQL
			//����ֵ	ִ�����
			//int		executeUpdate(insert/delete/update)
			//ResultSet	executeQuery(select)
			String sql = "select empno,ename,sal from emp";
			rs = stmt.executeQuery(sql);//ר��ִ��DQL���ķ�����
			//5����������
			
			//boolean flag = rs.next();
			//System.out.println(flag1);
			/* if(flag1){
				//���ָ�����������
				//ȡ����
				//getString()�������ص��ǣ�
				//�������ݿ���˵����������ʲô������String����ʽȡ��;
				//(�����ڸ����֣��ڼ���,�±��1��ʼ)
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String sal = rs.getString(3);
				System.out.println(empno+","+ename+","+sal+",");
			} */
			System.out.println("===============================");
			System.out.println("empno"+"\t"+"ename"+"\t"+"sal"+"\t");
			while(rs.next()){
				//���ָ�����������
				//ȡ����
				//getString()�������ص��ǣ�
				//�������ݿ���˵����������ʲô������String����ʽȡ��;
				//(�����ڸ����֣��ڼ���,�±��1��ʼ)
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String sal = rs.getString(3);
				System.out.println(empno+"\t"+ename+"\t"+sal+"\t");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6���ͷ���Դ
			if(rs != null){
				try{
					rs.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(stmt != null){
				try{
					stmt.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			
		}
	}
}