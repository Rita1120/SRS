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
import service.CourseService;
import service.PersonService;
import service.SectionService;

/**
 * Servlet implementation class showSectionServlet
 */
@WebServlet("/showSectionServlet")
public class showSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showSectionServlet() {
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
		SectionService sectionService=new SectionService();
	    CourseService courseService=new CourseService();
	    PersonService personService=new PersonService();
	    
		try {
			List<Section> results=sectionService.findAll();
			Professor p=new Professor();
			JSONArray json = new JSONArray();
			 for (Section result : results) {
				  JSONObject jo = new JSONObject();
	                jo.put("no", result.getSectionNo());
	                jo.put("day", result.getDayOfWeek());
	                jo.put("seat", result.getSeatingCapacity());
	                jo.put("time", result.getTimeOfDay());
	                jo.put("room", result.getRoom());
	                jo.put("cname",courseService.findByNo(result.getRepresentedCourse().getCourseNo()).getCourseName());
	                jo.put("pname", personService.findBySsn(result.getInstructor().getSsn()).getName());
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
