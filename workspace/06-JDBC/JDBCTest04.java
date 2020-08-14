/*
将连接数据库的所有信息配置到配置文件中
*/
import java.sql.*;
import java.util.*;
public class JDBCTest04{
	public static void main(String[] args){
		//使用资源绑定器，绑定属性配置文件。
		ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
		
		String driver = bundle.getString("driver");
		String url = bundle.getString("url");
		String user = bundle.getString("user");
		String password = bundle.getString("password");
		
		Connection conn = null;
		Statement stmt = null;
		
		try{
			//1.注册驱动
			//String driver = "com.mysql.jdbc.Driver";
			Class.forName(driver);
			//2.获取连接
			//String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
			//String user = "root";
			//String password = "giantpanda";
			conn = DriverManager.getConnection(url,user,password);
			
			//3.获取数据库操作对象
			stmt = conn.createStatement();
			//4.执行sql
			//String sql = "delete from t_user where id = 6";
			String sql = "update t_user set username='lisi' where id=7";
			int count = stmt.executeUpdate(sql);
			System.out.println(count == 1 ? "成功":"失败");
			//5.处理结果集
			
		}catch(Exception e){
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