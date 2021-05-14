package model;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Requests {
	private Connection connect() {
		
		Connection con = null;
		
		try{  
			String driver ="com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3308/gb"; 
			String username = "root"; 
			String password = "";
			  
			if(con == null || con.isClosed())
			{
				Class.forName(driver); 
				con = DriverManager.getConnection(url,username,password); 
			}
			
		} catch(Exception e){
			System.out.println(e);
		}
		return con;
		
	}
	public String insertRequest(String custname,String pname,String department,String p_des,String p_purp,String p_req) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error in Connect with Database";
				
			}
			String query = "Insert into requests (`req_id`, `custname`, `pname`, `department`, `p_des`, `p_purp`, `p_req`)"
					+"values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedSt = con.prepareStatement(query);
			 
			preparedSt.setInt(1,0);
			preparedSt.setString(2, custname);
			preparedSt.setString(3, pname);
			preparedSt.setString(4, department);
			preparedSt.setString(5, p_des);
			preparedSt.setString(6, p_purp);
			preparedSt.setString(7, p_req);
			
			preparedSt.execute();
			con.close();
			
			output = "Successfully Requested";
		}
		catch (Exception e)
		{
			output = "Error while requesting.";
			System.err.println(e.getMessage());
			}
		
		return output;
		
	}
	public String readRequests() {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error in Connect with Database";
				
			}
			// Prepare the html table to be displayed
			output = "<table border = '1'> <tr><th> Request ID </th><th> Customer Name </th>" + 
					"<th>Project Name</th>" +					
					"<th>Department</th>" +
					 "<th>Project Description </th>"+
					 "<th>Project Purpose</th" +
					 "<th> Project Requirement</th> " +
					 "<th>Update</th> <th> Delete</th></tr>";
			
			String query = "Select * from  requests";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				String req_id = Integer.toString(rs.getInt("req_id"));
				String custname = rs.getString("custname");
				String department = rs.getString("department");
				String p_des = rs.getString("p_des");
				String p_purp = rs.getString("p_purp");
				String p_req = rs.getString("p_req");
				
				// Add into the html table
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + req_id + "'>"
						 + req_id + "</td>"; 
				output += "<td>" + custname + "</td>";
				output += "<td>" + department + "</td>";
				output += "<td>" + p_des + "</td>";
				output += "<td>" +  p_purp + "</td>";
				output += "<td>" +  p_req + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'  class=' btnUpdate btn btn-secondary'></td><td><form method='post' action='items.jsp'><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'><input name='hidItemIDDelete' type='hidden' value='" + req_id + "'>" + "</form></td></tr>"; 
				
			}
			con.close();
			
			output += "</table>";
			
		}
			catch (Exception e)
			{
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
			}	
		return output;
		
	}
	
	public String updaterequest(String id, String custname,String pname,String department,String p_des,String p_purp,String p_req) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error in Connect with Database";
				
			}
			
			String query = "Update requests Set custname = ?, pname=?, department = ?, p_des = ?, p_purp = ?, p_req = ? Where req_id = ?";
			
			PreparedStatement prst = con.prepareStatement(query);
			
			
			prst.setString(1, custname);
			prst.setString(2, pname);
			prst.setString(3, department);
			prst.setString(4, p_des);
			prst.setString(5, p_purp);
			prst.setString(6, p_req);
			
			prst.execute();
			con.close();
			output = "Updated successfully";
			
		}	
			catch (Exception e)
			{
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
			}
			
			return output;
		
		
	}
	
	public String deleteRequest(String req_id)
		{
			String output = "";
			
				try
					{
						Connection con = connect();
						if (con == null)
							{
								return "Error while connecting to the database for deleting."; 
								}
						
						// create a prepared statement
						String query = "delete from requests where req_id=?";
						PreparedStatement preparedStmt = con.prepareStatement(query);
						
					
							preparedStmt.setInt(1, Integer.parseInt(req_id));
							
							// execute the statement
							preparedStmt.execute();
							con.close();
							output = "Deleted successfully";
								}
					catch (Exception e)
					{
							output = "Error while deleting the item.";
								System.err.println(e.getMessage());
						}
							return output;
		}
	
	

}
