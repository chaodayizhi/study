/*
	处理查询结果集(遍历结果集)
*/
import java.sql.*;

public class JDBCTest05{
	public static void main(String[] args){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			//1、注册驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2、获取连接
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode","root","giantpanda");
			//3、获取数据库操作对象
			stmt = conn.createStatement();
			//4、执行SQL
			//返回值	执行语句
			//int		executeUpdate(insert/delete/update)
			//ResultSet	executeQuery(select)
			String sql = "select empno,ename,sal from emp";
			rs = stmt.executeQuery(sql);//专门执行DQL语句的方法。
			//5、处理结果集
			
			//boolean flag = rs.next();
			//System.out.println(flag1);
			/* if(flag1){
				//光标指向的行有数据
				//取数据
				//getString()方法的特点是：
				//无论数据库中说话间类型是什么，都以String的形式取出;
				//(括号内跟数字，第几列,下标从1开始)
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String sal = rs.getString(3);
				System.out.println(empno+","+ename+","+sal+",");
			} */
			System.out.println("===============================");
			System.out.println("empno"+"\t"+"ename"+"\t"+"sal"+"\t");
			while(rs.next()){
				//光标指向的行有数据
				//取数据
				//getString()方法的特点是：
				//无论数据库中说话间类型是什么，都以String的形式取出;
				//(括号内跟数字，第几列,下标从1开始)
				String empno = rs.getString(1);
				String ename = rs.getString(2);
				String sal = rs.getString(3);
				System.out.println(empno+"\t"+ename+"\t"+sal+"\t");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//6、释放资源
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