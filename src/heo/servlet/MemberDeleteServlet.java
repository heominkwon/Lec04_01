package heo.servlet;

import heo.dao.MemberDao;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/delete")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Connection conn = null;
		//PreparedStatement  stmt  = null;
		
		
		try {

// 4. context
//			ServletContext sc = this.getServletContext();
////			Class.forName(sc.getInitParameter("driver"));
////			conn = DriverManager.getConnection(
////					sc.getInitParameter("url"),
////					sc.getInitParameter("username"),
////					sc.getInitParameter("password"));
//			conn = (Connection) sc.getAttribute("conn");
//			stmt = conn.prepareStatement("DELETE FROM MEMBERS WHERE MNO=?");
//			stmt.setInt(1, Integer.parseInt(request.getParameter("no")));
//			stmt.executeUpdate();
// 5. dao
//			ServletContext sc = this.getServletContext();
//			conn = (Connection)sc.getAttribute("conn");
//			
//			MemberDao memberDao = new MemberDao();
//			memberDao.setConnection(conn);
			
// 6.contextlistener에서 연결하기				
			ServletContext sc = this.getServletContext();
			MemberDao memberDao = (MemberDao)sc.getAttribute("memberDao");
			
			memberDao.delete(Integer.parseInt(request.getParameter("no")));
					
			
//			response.sendRedirect("list");
			request.setAttribute("viewUrl", "redirect:list.do");
							
			
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			
			
		}
		
	}
	
	
	

}
