package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import model.Student;
import model.TranscriptEntry;
import service.PersonService;
import service.TranscriptEntryService;

/**
 * Servlet implementation class showAttendServlet
 */
@WebServlet("/showAttendServlet")
public class showAttendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showAttendServlet() {
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
		String sno=request.getParameter("sno");
		List<TranscriptEntry> tes=new ArrayList<TranscriptEntry>();
		TranscriptEntryService transcriptEntryService=new TranscriptEntryService();
		PersonService personService=new PersonService();
		try {
			tes=transcriptEntryService.findBySno(sno);
			JSONArray json = new JSONArray();
			 for (TranscriptEntry te : tes) {
				  JSONObject jo = new JSONObject();
	                jo.put("ssn", te.getStudent().getSsn());
	                jo.put("name",personService.findByStuSsn(te.getStudent().getSsn()).getName() );
	                jo.put("major", personService.findByStuSsn(te.getStudent().getSsn()).getMajor());
	                json.put(jo);
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
