package heo.servlet;

import heo.dao.MemberDao;
import heo.vo.Member;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		Connection conn = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		
		try {
			
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			Member member = memberDao.exist(
					request.getParameter("email"),
					request.getParameter("password"));
			
			if(member != null){
				System.out.println("test1");
				HttpSession session = request.getSession();
				session.setAttribute("member", member);
//				response.sendRedirect("../member/list");
		        request.setAttribute("viewUrl", "redirect:../member/list.do");
		        System.out.println("test2");
				
			} else {
//				RequestDispatcher rd = request.getRequestDispatcher(
//						"/auth/LogInFail.jsp");
//				rd.forward(request, response);
				request.setAttribute("viewUrl", "/auth/LoginFail.jsp");
			}		
			
			//ServletContext sc = this.getServletContext();
			//conn = (Connection) sc.getAttribute("conn");
//			stmt = conn.prepareStatement(
//					"SELECT MNAME, EMAIL FROM MEMBERS"
//					+ " WHERE EMAIL=? AND PWD=?");
//			stmt.setString(1, request.getParameter("email"));
//			stmt.setString(2, request.getParameter("password"));
//			rs = stmt.executeQuery();
//			if(rs.next()){
//				Member member = new Member()
//					.setEmail(rs.getString("EMAIL"))
//					.setName(rs.getString("MNAME"));
//				HttpSession session = request.getSession();
//				session.setAttribute("member", member);
//				
//				response.sendRedirect("../member/list");		
//			} else {
//				RequestDispatcher rd = request.getRequestDispatcher(
//						"/auth/LogInFail.jsp");
//				rd.forward(request, response);
//			}
			
			
		} catch (Exception e) {
			throw new ServletException(e);
		} 
		
		
	}
	
	

}
