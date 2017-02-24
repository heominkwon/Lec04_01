package heo.listener;

import heo.dao.MemberDao;

import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;


@WebListener
public class ContextLoaderListener implements ServletContextListener {
	//Connection conn = null;
	//커넥션 풀 사용하기
	//DBConnectionPool connPool;
	// Apache DBCP 적용
	BasicDataSource ds;
	
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		// TODO Auto-generated method stub
		try {
			ServletContext sc = event.getServletContext();
			
			
//			connPool = new DBConnectionPool(
//					sc.getInitParameter("driver"),
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
			
//			
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//				sc.getInitParameter("url"),
//				sc.getInitParameter("username"),
//				sc.getInitParameter("password"));
			
//			ds = new BasicDataSource();
//			ds.setDriverClassName(sc.getInitParameter("driver"));
//			ds.setUrl(sc.getInitParameter("url"));
//			ds.setUsername(sc.getInitParameter("username"));
//			ds.setPassword(sc.getInitParameter("password"));
			
			//was context.xml설정 사용하기
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup(
					"java:comp/env/jdbc/myconn");
			
			MemberDao memberDao = new MemberDao();
			//memberDao.setDBConnectionPool(ds);
			//memberDao.setConnection(connPool);
			memberDao.setDataSource(ds);
			
			sc.setAttribute("memberDao", memberDao);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		try{if(ds!=null) ds.close();} catch(SQLException e){}
		
	}

	

}
