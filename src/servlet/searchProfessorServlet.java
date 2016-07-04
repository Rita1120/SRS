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
import service.PersonService;

/**
 * Servlet implementation class searchProfessorServlet
 */
@WebServlet("/searchProfessorServlet")
public class searchProfessorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchProfessorServlet() {
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
		 String title="";
         String department="";
         if(!request.getParameter("title").equals("")){
        	 title=request.getParameter("title");
         }
         if(!request.getParameter("department").equals("")){
        	 department=request.getParameter("department");
         }
         Professor professor=new Professor();
         professor.setTitle(title);
         professor.setDepartment(department);
         
		try {
		    PersonService personService=new PersonService();
			List<Professor> results=personService.findProfessor(professor);
			
			JSONArray json = new JSONArray();
			 for (Professor result : results) {
				  JSONObject jo = new JSONObject();
	                jo.put("ssn", result.getSsn());
	                jo.put("name", result.getName());
	                jo.put("department", result.getDepartment());
	                jo.put("title", result.getTitle());
	                json.put(jo);
			       // System.out.println("jo"+jo);
			    }
			 //System.out.println(json);
			 pw.println(json);
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
