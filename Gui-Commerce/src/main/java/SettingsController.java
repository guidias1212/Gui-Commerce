import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/settings")

	public class SettingsController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<HashMap<String, String>> products = new ArrayList<HashMap<String, String>>();
		
		HttpSession session = request.getSession(false);
		
		if (session.getAttribute("userName") != null) {
			
			Integer uid = (Integer) session.getAttribute("user_id");
			
			DBConnection connector = new DBConnection();
			
			PreparedStatement stmt = null;
	        
	        try {
	        	
	        	Connection c = connector.setConnection();
	        	
	        	System.out.println("SettingsController - Opened database");

	            String sql = "SELECT * FROM products "
	            		+ "WHERE user_id=?;";
	            
	            stmt = c.prepareStatement(sql);
	            
	            stmt.setInt(1, uid);
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            System.out.println("SettingsController - Query executed: Get user product table");

	            while ( rs.next() ) {
	            	
	            	Integer pid = rs.getInt("id");
	            	
	            	Integer user_id = rs.getInt("user_id");
	            	
	                String prod = rs.getString("product");
	                
	                String price = rs.getString("price");
	                
	                String url = rs.getString("link");
	                
	                HashMap<String, String> product = new HashMap<String, String>();
	                
	                product.put("pid",Integer.toString(pid));
	                
	                product.put("user_id",Integer.toString(user_id));
	                
	                product.put("prod",prod);
	                
	                product.put("price",price);
	                
	                product.put("url",url);
	                
	                products.add(product);
	            }
	            
	            rs.close();
	            
	            stmt.close();
	            
	            c.close();
	            
	            System.out.println("SettingsController - Closed database");
	            
	            request.setAttribute("products", products);
	            
	            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user_settings.jsp");
	            
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
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			doGet(request,response);
			
		}
	}