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

// ServletContext에 보관된 Connection 객체 사용  
// jsp사용
@WebServlet("/member/add")
public class MemberAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("viewUrl", "/member/MemberForm.jsp");
		
//		response.setContentType("text/html; charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>회원 등록</title></head>");
//		out.println("<body><h1>회원 등록</h1>");
//		out.println("<form action='add' method='post'>");
//		out.println("이름: <input type='text' name='name'><br>");
//		out.println("이메일: <input type='text' name='email'><br>");
//		out.println("암호: <input type='password' name='password'><br>");
//		out.println("<input type='submit' value='추가'>");
//		out.println("<input type='reset' value='취소'>");
//		out.println("</form>");
//		out.println("</body></html>");
	}
	
	@Override
	protected void doPost(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn = null;
		//PreparedStatement stmt = null;
		

		try {
// 4. context에서 읽어와서 실행하기
//			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn");  
//			stmt = conn.prepareStatement(
//					"INSERT INTO MEMBERS(EMAIL,PWD,MNAME,CRE_DATE,MOD_DATE)"
//					+ " VALUES (?,?,?,NOW(),NOW())");
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("password"));
//			stmt.setString(3, request.getParameter("name"));
//			stmt.executeUpdate();
			
//// 5. dao에서 읽어와서 실행하기
//			ServletContext sc = this.getServletContext();
//			conn = (Connection) sc.getAttribute("conn"); 
//			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
// 6.contextlistener에서 연결하기			
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			
			
//			DispathcerServlet으로 변경			
//			memberDao.insert(new Member()
//				.setEmail(request.getParameter("email"))
//				.setPassword(request.getParameter("password"))
//				.setName(request.getParameter("name")));
//			response.sendRedirect("list");
			
			Member member = (Member)request.getAttribute("member");
			memberDao.insert(member);
			
			request.setAttribute("viewUrl", "redirect:list.do");
			
			
			
		} catch (Exception e) {
			  e.printStackTrace();
		      request.setAttribute("error", e);
		      RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
		      rd.forward(request, response);
			
		}
	}
}
