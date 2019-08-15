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

@WebServlet("/delete")

	public class DeleteProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
			
		    dispatch.forward(request, response);
	   }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("userName")!=null) {
			
			Integer uid = (Integer) session.getAttribute("user_id");
			
			Integer pid = Integer.parseInt((String)request.getParameter("product_id"));
			
			System.out.println("DeleteProductController - Deleting product id: " + pid);
			
			DBConnection connector = new DBConnection();
			
			PreparedStatement stmt = null;
	        
	        try {
	        	
	        	Connection c = connector.setConnection();
	        	
	        	System.out.println("DeleteProductController - Opened database");
	            
	            c.setAutoCommit(false);
	            
	            String sql = "SELECT * FROM products "
	            		+ "WHERE user_id=? AND  id=?;";
	            
	            stmt = c.prepareStatement(sql);
	            
	            stmt.setInt(1, uid);
	            
	            stmt.setInt(2, pid);
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            System.out.println("DeleteProductController - Query executed: Match user id with product id");
	            
	            if (!rs.next()) { 
	            	
	            	System.out.println("DeleteProductController - No records found for this query...");
	            	
	            	rs.close();
	            	
		            stmt.close();
		            
	            } else {   
		            
		            sql = "DELETE FROM products WHERE id=?;";
		            
		            stmt = c.prepareStatement(sql);
		            
		            stmt.setInt(1, pid);
		            
		            stmt.executeUpdate();
		            
		            System.out.println("DeleteProductController - Query executed: Delete product");
	                
	                stmt.close();
	                
	                c.commit();
	                
	                c.close();
	                
	                System.out.println("DeleteProductController - Closed database");
	            }

	            RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
	            
			    dispatch.forward(request, response);
			    
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