package dmantz.training.jic.Controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.json.simple.JSONObject;
/*import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONArray;
*/
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.noggit.JSONUtil;

 

public class JicController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		
		response.setContentType("application/json");
		PrintWriter out=response.getWriter();
		
		 String inputParams = request.getParameter("inputJsonStr");
		 System.out.println("in dost");
		 System.out.println(inputParams);
	
		 JSONParser parser=new JSONParser();
		 JSONObject json=null;
	    
		try {
			 json=(JSONObject)parser.parse(inputParams);
	     
			System.out.println(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.close();
		
		}
		
		
		// String urlString = "http://192.168.100.10:8983/solr/jobsearch";
		 		
	    // SolrClient client = new HttpSolrClient.Builder("urlString").build(); 
		
			
			SolrClient client = new HttpSolrClient.Builder("http://192.168.100.10:8983/solr/jobsearch").build();

	      
	      SolrQuery query = new SolrQuery(); 
	     
		String searchText=(String)json.get("searchtext");
		
	    
	   // query.setParam("q",searchText);
		query.setQuery(searchText);
		//query.addFilterQuery("jobtitle:tester");
		//query.addFilterQuery("experience:1");
	   
	    
	                    
		query.setParam("wt", "json");
		
		//query.setQuery("id:1");
		//query.setQuery("id:3");
		//query.setQuery("education:BTech(CSE/EEE/ECE/IT)");
		//query.setQuery("jobtitle:software developer");
		//query.setQuery("id:4");
		
		//query.setQuery("q:6");
		
		//query.addField("*");
	    QueryResponse queryResponse = null;
	    String responsetext=null;
		
			try {
				
				queryResponse = client.query(query);
				 SolrDocumentList docs=queryResponse.getResults();
				 
				 String returnvalue = JSONUtil.toJSON(docs);
				 System.out.println(returnvalue);
				
				// JSONObject results=new JSONObject();
				 //returnresults.get("docs");
				 
				 //System.out.println(docs);
				 
				 responsetext=(String)returnvalue.toString();
				
				//responsetext=(String)docs.toString();
				 System.out.println("Stringified Response: " + responsetext);
				 out.println(responsetext);
				 out.flush();
				 out.close();
				 
			} catch (SolrServerException e1) {
				
				e1.printStackTrace();
			//out.print(b);
		
        try {
			client.commit();
			out.close();
			
			
			//System.out.println(client);
		} catch (SolrServerException e11) {
			
			e11.printStackTrace();
		}
		}
		    	}
}




		



