package heo.servlet;

import heo.dao.MemberDao;
import heo.vo.Member;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		//Statement stmt = null;
		//ResultSet rs = null;
		
		try {
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			//conn = DriverManager.getConnection(
			//		"jdbc:mysql://localhost:3306",
			//		"root",
			//		"12345678");
			
			//드라이버 등록을 web.xml에서 Servlet init파라미터를 이용
			//Servlet등록도 @로 하지 않고 Servlet등록을 통해서 함
//			
//			ServletContext sc = this.getServletContext();
////			Class.forName(sc.getInitParameter("driver"));
////			conn = DriverManager.getConnection(
////					sc.getInitParameter("url"),
////					sc.getInitParameter("username"),
////					sc.getInitParameter("password"));
//			conn = (Connection)sc.getAttribute("conn");
//			
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(
//			"SELECT MNO,EMAIL,MNAME,CRE_DATE FROM MEMBERS" + 
//			" WHERE MNO=" + request.getParameter("no"));	
////			rs.next();
//			if(rs.next()){
//				request.setAttribute("member", 
//						new Member()
//							.setNo(rs.getInt("MNO"))
//							.setEmail(rs.getString("EMAIL"))
//							.setName(rs.getString("MNAME"))
//							.setCreatedDate(rs.getDate("CRE_DATE")));
//			} else{
//				throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");
//			}
			
			//서버에 셋팅
			//response.setContentType("text/html; charset=UTF-8");
//			 ServletContext sc = this.getServletContext();
//		      conn = (Connection) sc.getAttribute("conn"); 
//
//		      MemberDao memberDao = new MemberDao();
//		      memberDao.setConnection(conn);
		      
//		      6.contextlistener에서 연결하기	
		      ServletContext sc = this.getServletContext();
			  MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
		       
		      Member member = memberDao.selectOne(
		          Integer.parseInt(request.getParameter("no")));

		      request.setAttribute("member", member);
		      
		      request.setAttribute("viewUrl", "/member/MemberUpdateForm.jsp");

			
//			RequestDispatcher rd = request.getRequestDispatcher(
//					"/member/MemberUpdateForm.jsp");
//			rd.forward(request, response);
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<html><head><title>회원정보</title></head>");
//			out.println("<body><h1>회원정보</h1>");
//			out.println("<form action='update' method='post'>");
//			out.println("번호: <input type='text' name='no' value='" +
//				request.getParameter("no") + "' readonly><br>");
//			out.println("이름: <input type='text' name='name'" +
//				" value='" + rs.getString("MNAME")  + "'><br>");
//			out.println("이메일: <input type='text' name='email'" +
//				" value='" + rs.getString("EMAIL")  + "'><br>");
//			out.println("가입일: " + rs.getDate("CRE_DATE") + "<br>");
//			out.println("<input type='submit' value='저장'>");
//			out.println("<input type='button' value='취소'" + 
//				" onclick='location.href=\"list\"'>");
//			out.println("</form>");
//			out.println("</body></html>");
				
		} catch (Exception e) {
			 e.printStackTrace();
		      request.setAttribute("error", e);
		      RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
		      rd.forward(request, response);
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		//PreparedStatement stmt = null;

//1. web.xml에서 filter로 처리
		//request.setCharacterEncoding("UTF-8");
			
		try {
// 4. context			
//			ServletContext sc = this.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//			conn = DriverManager.getConnection(
//					sc.getInitParameter("url"),
//					sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
//			stmt = conn.prepareStatement(
//					"UPDATE MEMBERS SET EMAIL=?,MNAME=?,MOD_DATE=now()"
//					+ " WHERE MNO=?");
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("name"));
//			stmt.setInt(3, Integer.parseInt(request.getParameter("no")));
//			stmt.executeUpdate();
			
//// 5. dao
//			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
			
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
//			memberDao.update(new Member()
//				.setNo(Integer.parseInt(request.getParameter("no")))
//				.setName(request.getParameter("name"))
//				.setEmail(request.getParameter("email")));
//				
//			
//			response.sendRedirect("list");
			
			Member member = (Member)request.getAttribute("member");
			memberDao.update(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		} 
		
	}
	
	
	
	

}
