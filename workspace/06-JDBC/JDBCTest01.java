/*
	JDBC 六步
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
			//1、注册驱动
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Driver driver = new com.mysql.jdbc.Driver();
			DriverManager.registerDriver(driver);
			
			//2、获取连接
			String url = "jdbc:mysql://127.0.0.1:3306/bjpowernode";
			String user = "root";
			String password = "giantpanda";
			conn = DriverManager.getConnection(url,user,password);
			System.out.println("数据库连接对象"+conn);
			
			//3、获取数据库操作对象
			stmt = conn.createStatement();
			
			//4、执行SQL
			String sql = "insert into t_user(username) values('zhangsan')";
			//专门执行DML语句的（insert，delete，update）
			//返回值是“返回影响的记录数目”。
			int count = stmt.executeUpdate(sql);//执行DML语句，
			System.out.println(count == 1 ? "插入成功" : "插入失败");
			//5、处理结果集
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			//6、释放资源
			//为了保证保证资源一定释放，在finally语句块中关闭资源。
			//并且要遵循，从小到大一次关闭。
			//分别对其try、、catch
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