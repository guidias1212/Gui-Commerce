import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")

	public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
			
		    dispatch.forward(request, response);
	   }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			String name = request.getParameter("name");
			
			String un = request.getParameter("username");
			
	        String pw1 = request.getParameter("password_1");
	        
	        String pw2 = request.getParameter("password_2");
	        
	        if (!pw1.isEmpty()  && !pw2.isEmpty()  && !un.isEmpty() && !name.isEmpty() && pw1.equals(pw2)) {
	        	
	        	DBConnection connector = new DBConnection();
	        	
	        	PreparedStatement stmt = null;
	        	
	            try {
	                
	            	Connection c = connector.setConnection();
	            	
	            	System.out.println("RegisterController - Opened database");
	                
	                c.setAutoCommit(false);
	                
	                System.out.println("RegisterController - Registering Username: " + un + " Password: " + pw1);
	                
	                String sql = "INSERT INTO users (username,password,name) "
	                   + "VALUES (?,?,?);";
	                
	                stmt = c.prepareStatement(sql);
	                
	                stmt.setString(1, un);
	                
	                stmt.setString(2, pw1);
	                
	                stmt.setString(3, name);
	                
	                stmt.executeUpdate();
	                
	                System.out.println("RegisterController - Query executed: Register new user");
	                
	                stmt.close();
	                
	                c.commit();
	                
	                c.close();
	                
	                System.out.println("RegisterController - Closed database");
	                
	             } catch (Exception e) {
	            	 
	                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	                
	                System.exit(0);
	             }

	        	RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
	        	
			    dispatch.forward(request, response);
	        	
	        } else {
	        	
	        	RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/register.jsp");
	        	
			    dispatch.forward(request, response);
	        }
		}
	}