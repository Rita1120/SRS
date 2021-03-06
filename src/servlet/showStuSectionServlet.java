package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Professor;
import model.Section;
import service.ScheduleOfClassesService;

/**
 * Servlet implementation class showStuSectionServlet
 */
@WebServlet("/showStuSectionServlet")
public class showStuSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showStuSectionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8") ;	
		response.setContentType("text/html;charset=UTF-8");
		String semester=request.getParameter("semester");
		
		ScheduleOfClassesService scheduleOfClassesService=new ScheduleOfClassesService();
	
		
			
		try {
			
			List<Section> results=scheduleOfClassesService.getScheduleOfClass(semester);
			Professor p=new Professor();
			JSONArray json = new JSONArray();
			 for (Section result : results) {
				  JSONObject jo = new JSONObject();
	                jo.put("no", result.getSectionNo());
	                jo.put("day", result.getDayOfWeek());
	                jo.put("seat", result.getSeatingCapacity());
	                jo.put("time", result.getTimeOfDay());
	                jo.put("room", result.getRoom());
	                jo.put("cname",result.getRepresentedCourse().getCourseName());
	                jo.put("credit",result.getRepresentedCourse().getCredits());
	                jo.put("pname",result.getInstructor().getName());
	                json.put(jo);
			        //System.out.println("jo"+jo);
			    }
			 PrintWriter pw = response.getWriter();
			 System.out.println(json);
			 pw.println(json);
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
