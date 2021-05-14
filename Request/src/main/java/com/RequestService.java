package com;

//For REST Service
import javax.ws.rs.*;

import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

import model.Requests;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;





@Path("/Requests")
public class RequestService {
	
	Requests requestobj = new Requests();
	
	//read request 
	@GET  
	@Path("/")  
	@Produces(MediaType.TEXT_HTML)
	public String readRequests() {
		return requestobj.readRequests();
		
		
	}
	
	//insertrequest
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertRequest (@FormParam("custname")String custname, @FormParam("pname") String pname, @FormParam("department") String department, @FormParam("p_des") String p_des, @FormParam("p_purp") String p_purp,@FormParam("p_req") String p_req )
	{
		
		String output = requestobj.insertRequest(custname, pname, department, p_des, p_purp, p_req);
		
		return output;
		
	}
	
	//updaterequest
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updaterequest(String data) {
		
		JsonObject requestobj2 = new JsonParser().parse(data).getAsJsonObject();;
		
		String req_id = requestobj2.get("req_id").getAsString();
		String custname = requestobj2.get("custname").getAsString();
		String pname = requestobj2.get("pname ").getAsString();
		String department = requestobj2.get("department").getAsString();
		String p_des = requestobj2.get("p_des").getAsString();
		String p_purp = requestobj2.get("p_purp").getAsString();
		String p_req = requestobj2.get("p_req").getAsString();
		
		String output = requestobj.updaterequest(req_id, custname, pname, department, p_des, p_purp, p_req);
		
		return output;
		
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteRequest(String data) {
		Document doc = Jsoup.parse(data, "", Parser.xmlParser());
		String req_id = doc.select("req_id").text();

		String output = requestobj.deleteRequest(req_id);

		return output;
	}
	

}
