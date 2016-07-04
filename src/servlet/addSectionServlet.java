package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javafx.concurrent.ScheduledService;
import model.Course;
import model.Professor;
import model.ScheduleOfClasses;
import model.Section;
import service.PersonService;
import service.ScheduleOfClassesService;
import service.SectionService;

/**
 * Servlet implementation class addSectionServlet
 */
@WebServlet("/addSectionServlet")
public class addSectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addSectionServlet() {
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
		 String no="";
         String course="";
         String room="";
         int seat=0;
         String semester="";
         String day="";
         String time="";
         String p="";
         
         if(!request.getParameter("no").equals("")){
        	 no=request.getParameter("no");
         }
         if(!request.getParameter("course").equals("")){
        	 course=request.getParameter("course");
         }
         if(!request.getParameter("room").equals("")){
        	 room=request.getParameter("room");
         }
         if(!request.getParameter("semester").equals("")){
        	 semester=request.getParameter("semester");
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
         System.out.println("no:"+no+"  "+"course:"+"  "+course+"room:"+"  "+room+"semester:"+"  "+day+"day:"+"  "+"time:"+"  "+time+"p:"+"  "+p+" "+seat);
         
         Section section=new Section();
         section.setSectionNo(no);
         section.setDayOfWeek(day);
         section.setTimeOfDay(time);
         section.setRoom(room);
         section.setSeatingCapacity(seat);
         
         Course c=new Course();
         c.setCourseNo(course);
         
         Professor p1= new Professor();
         p1.setSsn(p);
         
         section.setRepresentedCourse(c);
         section.setInstructor(p1);
         
         
         SectionService sectionService=new SectionService();
        
         ScheduleOfClasses s=new ScheduleOfClasses();
         s.setSemester(semester);
         HashMap<String, Section> hashMap=new HashMap<>();
         hashMap.put("section", section);
         s.setSectionsOffered(hashMap);
         ScheduleOfClassesService soc=new ScheduleOfClassesService();
        
    
       
         try {
			if(sectionService.addSection(section)&&soc.addScheduleOfClasses(s)){
				pw.println("新增成功");
			}
			else{
				pw.println("新增失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
