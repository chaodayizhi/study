
import java.sql.*;
public class JDBCTest02{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		
		try{
			//1.注册驱动
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//2.获取连接
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bjpowernode","root","giantpanda");
			
			//3.获取数据库操作对象
			stmt = conn.createStatement();
			//4.执行sql
			//String sql = "delete from t_user where id = 6";
			String sql = "update t_user set username='lisi' where id=7";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "成功":"失败");
			//5.处理结果集
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//6.释放资源
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