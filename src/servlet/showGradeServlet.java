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

import model.Course;
import model.Professor;
import model.Student;
import model.TranscriptEntry;
import service.CourseService;
import service.SectionService;
import service.TranscriptEntryService;

/**
 * Servlet implementation class showGradeServlet
 */
@WebServlet("/showGradeServlet")
public class showGradeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showGradeServlet() {
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
		 String ssn=request.getParameter("ssn"); 
		 
		 TranscriptEntryService transcriptEntryService=new TranscriptEntryService();
		 CourseService courseService=new CourseService();
		 SectionService sectionService=new SectionService();
		 Student student=new Student();
		 student.setSsn(ssn);;
		try {
			List<TranscriptEntry> results=transcriptEntryService.findGrade(student);
			
			JSONArray json = new JSONArray();
			 for (TranscriptEntry result : results) {
				  JSONObject jo = new JSONObject();
	                jo.put("cname", courseService.findByNo(sectionService.findBySno(result.getSection().getSectionNo()).getRepresentedCourse().getCourseNo()).getCourseName());
	                jo.put("sno",result.getSection().getSectionNo());
	                jo.put("credits",courseService.findByNo(sectionService.findBySno(result.getSection().getSectionNo()).getRepresentedCourse().getCourseNo()).getCredits());
	                jo.put("grade", result.getGrade());
	                json.put(jo);
			        //System.out.println("jo"+jo);
			    }
			 PrintWriter pw = response.getWriter();
			 //System.out.println(json);
			 pw.println(json);
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
