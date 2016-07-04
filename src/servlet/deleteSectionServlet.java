package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ScheduleOfClasses;
import model.Section;
import service.PersonService;
import service.ScheduleOfClassesService;

/**
 * Servlet implementation class deleteSectionServlet
 */
@WebServlet("/deleteSectionServlet")
public class deleteSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteSectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		String no="";
		if(!request.getParameter("no").equals("")){
			no=request.getParameter("no");
		}
		
		
		Section section=new Section();
		section.setSectionNo(no);
		
		ScheduleOfClasses s=new ScheduleOfClasses();
	    HashMap<String, Section> hashMap=new HashMap<>();
        hashMap.put("section", section);
        s.setSectionsOffered(hashMap);
		ScheduleOfClassesService soc=new ScheduleOfClassesService();
		boolean a=false;
		try {
			a=soc.deleteScheduleOfClasses(s);
			if(a){
				response.sendRedirect("courseManage.jsp?msg=1");
			}
			else{
				response.sendRedirect("courseManage.jsp?msg=2");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
