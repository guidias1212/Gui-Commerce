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

@WebServlet("/home")

	public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			ArrayList<HashMap<String, String>> products = new ArrayList<HashMap<String, String>>();
			
			DBConnection connector = new DBConnection();

			PreparedStatement stmt = null;
	        
	        try {
	        	
	        	Connection c = connector.setConnection();
	        	
	        	System.out.println("HomeController - Opened database");
	        	
	            String sql = "SELECT products.*, users.name " + 
	            		"FROM products " + 
	            		"INNER JOIN users " + 
	            		"ON products.user_id=users.id;";
	            
	            stmt = c.prepareStatement(sql);
	            
	            ResultSet rs = stmt.executeQuery();
	            
	            System.out.println("HomeController - Query executed: Get products table");
	            
	            while ( rs.next() ) {
	            	
	            	String pid = rs.getString("id");
	            	
	                String prod = rs.getString("product");
	                
	                String price = rs.getString("price");
	                
	                String url = rs.getString("link");
	                
	                String uname = rs.getString("name");
	                
	                HashMap<String, String> product = new HashMap<String, String>();
	                
	                product.put("pid",pid);
	                
	                product.put("user_name",uname);
	                
	                product.put("prod",prod);
	                
	                product.put("price",price);
	                
	                product.put("url",url);
	                
	                products.add(product);
	            }
	            
	            rs.close();
	            
	            stmt.close();
	            
	            c.close();
	            
	            System.out.println("HomeController - Closed database");
	            
	            request.setAttribute("products", products);
	            
	            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/home.jsp");
	            
			    dispatch.forward(request, response);
			    
	         } catch ( Exception e ) {
	        	 
	            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	            
	            System.exit(0);
	         } 
	   }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			doGet(request,response);
			
		}
	}