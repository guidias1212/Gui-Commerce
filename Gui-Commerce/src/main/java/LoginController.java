import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")

	public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			HttpSession session = request.getSession(false);
			
			if (session.getAttribute("userName")!=null) {
				
				RequestDispatcher dispatch = request.getRequestDispatcher("settings");
				
			    dispatch.forward(request, response);
			    
			} else {
				
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
				
			    dispatch.forward(request, response);
			}
	   }
		
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
	    	String un = request.getParameter("username");
	    	
	        String pw = request.getParameter("password");
	        
	        if (!un.isEmpty()  && !pw.isEmpty()) {
		        
	        	DBConnection connector = new DBConnection();
	        	
	        	PreparedStatement stmt = null;
	        	
		        try {
		        	
		        	Connection c = connector.setConnection();
		            
		        	System.out.println("LoginController - Opened database");
		            
		            String sql = "SELECT id, username, password FROM users " +
		 	               "WHERE username=?;";
		            
		            stmt = c.prepareStatement(sql);
		            
		            stmt.setString(1, un);
		            
		            ResultSet rs = stmt.executeQuery();
		            
		            System.out.println("LoginController - Query executed: Match username and password");
		            
		            while ( rs.next() ) {
		            	
		            	Integer uid = rs.getInt("id");
		            	
		                String user = rs.getString("username");
		                
		                String pass = rs.getString("password");
		                
		                if (un.equals(user) && pw.equals(pass)) {
		                	
		                	HttpSession session = request.getSession(true);
		    	        	
		    	        	session.setAttribute("userName", un);
		    	        	
		    	        	session.setAttribute("user_id", uid);

		    	        	RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
		    	        	
		    			    dispatch.forward(request, response);
		                	
		                	rs.close();
		                	
		    	            stmt.close();
		    	            
		    	            c.close();
		    	            
		    	            System.out.println("LoginController - Closed database");
		    	            
		    	            return;
		                }
		            }
		            
		            rs.close();
		            
		            stmt.close();
		            
		            c.close();
		            
		            System.out.println("LoginController - Closed database");
		            
		            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		            
				    dispatch.forward(request, response);
				    
		         } catch ( Exception e ) {
		        	 
		            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		            
		            System.exit(0);
		         }
		        
	        } else {
	        	
	        	RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
	        	
			    dispatch.forward(request, response);
	        }
	    }
	}