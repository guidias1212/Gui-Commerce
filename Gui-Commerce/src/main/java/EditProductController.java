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

@WebServlet("/edit")

	public class EditProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("userName")!=null) {
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
			
		    dispatch.forward(request, response);
			
		} else {
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/login");
			
		    dispatch.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		
		String action = request.getParameter("ACTION");
		
		if (session.getAttribute("userName")!=null) {
			
			if (action.equals("settings")) {
				
				Integer pid = Integer.parseInt((String) request.getParameter("product_id"));
				
				String pname = (String) request.getParameter("product_name");
				
				String pprice = (String) request.getParameter("product_price");
				
				request.setAttribute("product_id", pid);
				
				request.setAttribute("product_name", pname);
				
				request.setAttribute("product_price", pprice);
				
				RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/edit.jsp");
				
			    dispatch.forward(request, response);
			    
			} else {

				Integer uid = (Integer) session.getAttribute("user_id");
				
				Integer pid = Integer.parseInt((String)request.getParameter("product_id"));
				
				String new_pname = (String)request.getParameter("product");
				
				String new_price = (String)request.getParameter("price");
				
				String new_url = (String)request.getParameter("url");
				
				DBConnection connector = new DBConnection();
				
				PreparedStatement stmt = null;
		        
		        try {
		        	
		        	Connection c = connector.setConnection();
		        	
		        	System.out.println("EditProductController - Opened database");
		            
		            c.setAutoCommit(false);

		            String sql = "SELECT * FROM products "
		            		+ "WHERE user_id=? AND  id=?;";
		            
		            stmt = c.prepareStatement(sql);
		            
		            stmt.setInt(1, uid);
		            
		            stmt.setInt(2, pid);
		            
		            ResultSet rs = stmt.executeQuery();
		            
		            System.out.println("EditProductController - Query executed: Match user id with product id");
		            
		            if (!rs.next()) { 
		            	
		            	System.out.println("EditProductController - No records found for this query...");
		            	
		            	rs.close();
		            	
			            stmt.close();
			            
		            } else {
		            	
		            	rs.close();
			            
			            sql = "UPDATE products " + 
			            		"SET product = ?, price = ?, link = ? " + 
			            		"WHERE id=?;";
			            
			            stmt = c.prepareStatement(sql);
			            
			            stmt.setString(1, new_pname);
			            
			            stmt.setString(2, new_price);
			            
			            stmt.setString(3, new_url);
			            
			            stmt.setInt(4, pid);
			            
			            stmt.executeUpdate();
			            
			            System.out.println("EditProductController - Query executed: Update product");
	
			            stmt.close();
			            
		                c.commit();
		                
		                c.close();
		                
		                System.out.println("EditProductController - Closed database");
		            	
		            }
	
		            rs.close();
		            
		            stmt.close();
		            
		            c.close();
		            
		            System.out.println("EditProductController - Closed database");
	
		            RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
		            
				    dispatch.forward(request, response);
				    
		         } catch ( Exception e ) {
		        	 
		            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		            
		            System.exit(0);
		         }
			}

		} else {
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/login");
			
		    dispatch.forward(request, response);
		}
	}
}