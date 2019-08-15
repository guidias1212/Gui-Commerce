<body>
    <%
	    RequestDispatcher dispatch = request.getRequestDispatcher("/home");
	    dispatch.forward(request, response);
    %>
</body>