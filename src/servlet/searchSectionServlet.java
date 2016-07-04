package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.concurrent.ScheduledService;
import model.Course;
import model.Person;
import model.Professor;
import model.ScheduleOfClasses;
import model.Section;
import service.CourseService;
import service.PersonService;
import service.ScheduleOfClassesService;
import service.SectionService;

/**
 * Servlet implementation class searchSectionServlet
 */
@WebServlet("/searchSectionServlet")
public class searchSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchSectionServlet() {
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
		PrintWriter pw = response.getWriter();
		 String sec="";
         String c="";
         if(!request.getParameter("sec").equals("")){
        	 sec=request.getParameter("sec");
         }
         if(!request.getParameter("c").equals("")){
        	 c=request.getParameter("c");
         }
         Section section=new Section();
         Course course=new Course();
         course.setCourseNo(c);
         section.setSectionNo(sec);
         section.setRepresentedCourse(course);
         
         ScheduleOfClasses soc=new ScheduleOfClasses();
         HashMap<String, Section> hashMap=new HashMap<>();
         hashMap.put("section", section);
         soc.setSectionsOffered(hashMap);
         soc.setSemester(sec);
         
         ScheduleOfClassesService socs=new ScheduleOfClassesService();
         SectionService ss=new SectionService();
         Section section2;
		try {
			String a=socs.findSno(soc);
			System.out.println(a);
			section2 = ss.findBySno(a);
		 System.out.println("section2"+section2);
		 CourseService courseService=new CourseService();
		 PersonService personService=new PersonService();

     			  JSONObject jo = new JSONObject();
     			   jo.put("no", section2.getSectionNo());
	                jo.put("day", section2.getDayOfWeek());
	                jo.put("seat", section2.getSeatingCapacity());
	                jo.put("time", section2.getTimeOfDay());
	                jo.put("room", section2.getRoom());
	                System.out.println("servlet"+section2.getInstructor().getSsn());
	                jo.put("cname",courseService.findByNo(section2.getRepresentedCourse().getCourseNo()).getCourseName());
	                jo.put("pname", personService.findBySsn(section2.getInstructor().getSsn()).getName());
			   System.err.println(jo);
			 pw.println("["+jo+"]");
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
