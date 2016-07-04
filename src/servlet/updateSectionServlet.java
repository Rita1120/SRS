package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Professor;
import model.Section;
import service.PersonService;
import service.SectionService;

/**
 * Servlet implementation class updateSectionServlet
 */
@WebServlet("/updateSectionServlet")
public class updateSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateSectionServlet() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter pw = response.getWriter();
         String sno="";
         String room="";
         int seat=0;
         String day="";
         String time="";
         String p="";
         if(!request.getParameter("sno").equals("")){
        	 sno=request.getParameter("sno");
         }

         if(!request.getParameter("room").equals("")){
        	 room=request.getParameter("room");
         }

         if(!request.getParameter("day").equals("")){
        	 day=request.getParameter("day");
         }
         if(!request.getParameter("time").equals("")){
        	 time=request.getParameter("time");
         }
         if(!request.getParameter("p").equals("")){
        	 p=request.getParameter("p");
         }
         if(Integer.parseInt(request.getParameter("seat"))!=0){
        	 seat=Integer.parseInt(request.getParameter("seat"));
         }
         Section section=new Section();
         section.setSectionNo(sno);
         section.setDayOfWeek(day);
         section.setTimeOfDay(time);
         section.setRoom(room);
         section.setSeatingCapacity(seat);
         Professor pro=new Professor();
         pro.setSsn(p);
         section.setInstructor(pro);
         
         SectionService sectionService=new SectionService();
         //System.out.println("ssn:"+ssn+"  "+"name:"+"  "+name+"title:"+"  "+title+"department:"+"  "+department);

         try {
			if(sectionService.updateSection(section)){
				pw.println("修改成功");
			}
			else{
				pw.println("修改失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
