/*
	注册驱动的常用方式！
*/
import java.sql.*;

public class JDBCTest03{
	public static void main(String[] args){
		try{
			//1.注册驱动
			// 注册驱动的第一种写法。不常用
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			//注册驱动的第二种写法(常用)
			//为什么这种方式常用？
			//因为参数是字符串，字符串可以写入配置文件中（xx.properties）
			//以下方法不需要返回值，因为我们只想用它的类加载动作。
			Class.forName("com.mysql.jdbc.Driver");
			
			//2.获取连接
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