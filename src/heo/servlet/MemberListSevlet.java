package heo.servlet;

import heo.vo.Member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
public class MemberListSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see javax.servlet.GenericServlet#service(javax.servlet.ServletRequest, javax.servlet.ServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			
//1. 데이타 베이스 직접 사용하기						
//			//데이타 베이스 드라이브 등록
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			//데이타 베이스 연결
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/test",
//					"root", 
//					"12345678");	
//			//실행
			//this.getInitParameter를 이용하여 web.xml에서 등록된 정보를 가져온다.
			//@WebServlet("/member/list")를 사용하면 web.xml에서 가져올 수가 없다. web.xml에 서브릿 등록필요
//2. servlet의 init파라미터를 이용하여 사용하기
//			Class.forName(this.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//					this.getInitParameter("url"),
//					this.getInitParameter("username"),
//					this.getInitParameter("password"));
	
//3. context param 사용하기
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			//실행 결과 담기
			rs = stmt.executeQuery(
					"SELECT MNO,MNAME,EMAIL,CRE_DATE" +
					" FROM MEMBERS"+
					" ORDER BY MNO ASC");
			
			//서버에 셋팅
			response.setContentType("text/html; charset=UTF-8");
					
// JSP로 변경하기			
//			PrintWriter out = response.getWriter();
//			out.println("<html><head><title>회원목록</title><head>");
//		
//			out.println("<body>");
//			out.println("<a href='add'>회원등록</a><br>");
//			while(rs.next()){
//				out.println(
//						rs.getInt("MNO") + "," +
//						"<a href='update?no=" + rs.getInt("MNO") + "'>" +
//						rs.getString("MNAME") + "," +
//						rs.getString("EMAIL") + "," +
//						rs.getDate("CRE_DATE") +
//						"<a href='delete?no=" + rs.getInt("MNO") + 
//						"'>[삭제]</a><br>");
//			}
//			out.println("</body></html>");
			
			//member 클래스 배열을 생성
			ArrayList<Member> members = new ArrayList<Member>();
			// 데이터베이스에서 회원 정보를 가져와 Member에 담는다.
			// 그리고 Member객체를 ArrayList에 추가한다.
			while(rs.next()) {
				members.add(new Member()
				            .setNo(rs.getInt("MNO"))
							.setName(rs.getString("MNAME"))
							.setEmail(rs.getString("EMAIL"))
							.setCreatedDate(rs.getDate("CRE_DATE"))	);
			}
			
			// request에 회원 목록 데이터 보관 
			// 클라이언트에 셋팅
			request.setAttribute("members", members); 
			
			//JSP출력 클라이언트로 위임
			RequestDispatcher rd = request.getRequestDispatcher("/member/MemberList.jsp");
			rd.include(request, response);
			
			
			
		} catch(Exception e){
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.include(request, response);
		} finally {
			try { if(rs != null) rs.close();} catch(Exception e){}
			try { if(stmt != null) stmt.close();} catch(Exception e){}
			try { if(conn != null) conn.close();} catch(Exception e){}
		}
		
		
	}
	
	

}
