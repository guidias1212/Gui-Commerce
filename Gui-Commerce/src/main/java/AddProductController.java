
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addProduct")

public class AddProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/login");
			
		    dispatch.forward(request, response);
	   }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			HttpSession session = request.getSession(false);
			
	        if (session.getAttribute("userName")!=null){
	        	
				String un = (String) session.getAttribute("userName");
				
				Integer uid = (Integer) session.getAttribute("user_id");
				
				String prod = request.getParameter("product");
				
		        String price = request.getParameter("price");
		        
		        String url = request.getParameter("url");
		        
		        if (!prod.isEmpty()  && !price.isEmpty()  && !url.isEmpty() && !un.isEmpty()) {

		        	DBConnection connector = new DBConnection();
		        	
		        	PreparedStatement stmt = null;
		        	
		            try {
		            	
		            	Connection c = connector.setConnection();
		            	
		            	System.out.println("AddProductController - Opened database");
		                
		                c.setAutoCommit(false);
		                
		                System.out.println("AddProductController - Registering Product: " + prod + " price: " + price + " user_id: " + uid);
		                
		                String sql = "INSERT INTO products (user_id,product,price,link) "
		                   + "VALUES (?,?,?,?);";
		                
		                stmt = c.prepareStatement(sql);
		                
		                stmt.setInt(1, uid);
		                
		                stmt.setString(2, prod);
		                
		                stmt.setString(3, price);
		                
		                stmt.setString(4, url);
		                
		                stmt.executeUpdate();
		                
		                System.out.println("AddProductController - Query executed: Add new product");
		                
		                stmt.close();
		                
		                c.commit();
		                
		                c.close();
		                
		                System.out.println("AddProductController - Closed database");
		                
		             } catch (Exception e) {
		            	 
		                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
		                
		                System.exit(0);
		             }

		        	RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/success.jsp");
		        	
				    dispatch.forward(request, response);
				    
		        } else {
		        	
		        	RequestDispatcher dispatch = request.getRequestDispatcher("/settings");
		        	
				    dispatch.forward(request, response);
		        }
		        
	        } else {
	        	
	        	RequestDispatcher dispatch = request.getRequestDispatcher("/login");
	        	
			    dispatch.forward(request, response);
	        }
		}
	}