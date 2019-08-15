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

@WebServlet("/deleteUser")

	public class DeleteUserController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
			
		    dispatch.forward(request, response);
	   }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("userName")!=null) {
			
			Integer uid = (Integer) session.getAttribute("user_id");
			
			System.out.println("DeleteUserController - Deleting user id: " + uid);
			
			DBConnection connector = new DBConnection();
			
			PreparedStatement stmt = null;
	        
	        try {
	        	
	        	Connection c = connector.setConnection();
	        	
	        	System.out.println("DeleteUserController - Opened database");
	            
	            c.setAutoCommit(false);

	            String sql = "SELECT * FROM users "
	            		+ "WHERE id=?;";
	            
	            stmt = c.prepareStatement(sql);
	            
	            stmt.setInt(1, uid);
	              
	            ResultSet rs = stmt.executeQuery();
	            
	            System.out.println("DeleteUserController - Query executed: Check user id");
	            
	            if (!rs.next()) { 
	            	
	            	System.out.println("DeleteUserController - No records found for this query...");
	            	
	            	rs.close();
	            	
		            stmt.close();
		            
	            } else {   
		            
	            	sql = "DELETE FROM products WHERE user_id=?;";
		            
		            stmt = c.prepareStatement(sql);
		            
		            stmt.setInt(1, uid);
		            
		            stmt.executeUpdate();
		            
		            System.out.println("DeleteUserController - Query executed: Delete products from user: " + uid);
		            
		            sql = "DELETE FROM users WHERE id=?;";
		            
		            stmt = c.prepareStatement(sql);
		            
		            stmt.setInt(1, uid);
		            
		            stmt.executeUpdate();
		            
		            System.out.println("DeleteUserController - Query executed: Delete user: " + uid);
	                
	                stmt.close();
	                
	                c.commit();
	                
	                c.close();
	                
	                System.out.println("DeleteUserController - Closed database");
	            }
	            
	            session.invalidate();
	            
	            response.sendRedirect("home");
			    
	         } catch ( Exception e ) {
	        	 
	            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	            
	            System.exit(0);
	         }

		} else {
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/login");
			
		    dispatch.forward(request, response);
		}
	}
}