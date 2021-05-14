package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class RequestAPI
 */
@WebServlet("/RequestAPI")
public class RequestAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Requests requestobj=new Requests();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestAPI() {
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

		String output = requestobj.insertRequest(request.getParameter("custname"),
				request.getParameter("pname"),
				request.getParameter("department"),
				request.getParameter("p_des"),
				request.getParameter("p_purp"),
				request.getParameter("p_req"));
				response.getWriter().write(output);
	}
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = requestobj.updaterequest(paras.get("hidItemIDSave").toString(),
		 paras.get("custname").toString(),
		paras.get("pname").toString(),
		paras.get("department").toString(),
		paras.get("p_des").toString(),
		paras.get("p_purp").toString(),
		paras.get("p_req").toString());
		response.getWriter().write(output);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = requestobj.deleteRequest(paras.get("req_id").toString());
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

}
