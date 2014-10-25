package com.rest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.rest.service.mappers.CreateTorrentResponseMapper;
import com.rest.service.mappers.DefaultDirectoryRequestMapper;
import com.rest.service.mappers.GenericResponseStatusMapper;
import com.rest.service.mappers.GenericTorrentResponseMapper;
import com.rest.service.mappers.MonitorResponseMapper;
import com.sun.jersey.api.view.Viewable;

@Path("/service")
public class TorrentService {

	private static String localDefaultDirectory;
	
	@GET
	@Path("/defaultdirectory")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDefaultDirectory(){
		
		String response = null;
		DefaultDirectoryRequestMapper responseMapper = new DefaultDirectoryRequestMapper();
		responseMapper.setDefaultDirectory(localDefaultDirectory);
		try {
			response = (new ObjectMapper().writeValueAsString(responseMapper));
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return response;
	}
	
	@POST
	@Path("/defaultdirectory")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String setDefaultDirectory(String requestJson){
		
		ObjectMapper mapper = new ObjectMapper();
		String responseString = null;
		
		DefaultDirectoryRequestMapper requestMapper = null;
		Map<String, String> requestMap = null;
		try {
			System.out.println("request json: "+requestJson);
			requestMap = mapper.readValue(requestJson, Map.class);
			System.out.println("default directory from map: "+requestMap.get("defaultDirectory"));
			requestMapper = mapper.readValue(requestJson, DefaultDirectoryRequestMapper.class);
			System.out.println("default directory from bean: "+requestMapper.getDefaultDirectory());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		localDefaultDirectory = requestMapper.getDefaultDirectory();
		GenericResponseStatusMapper response = new GenericResponseStatusMapper();
		response.setSuccess("true");
		response.setMessage("Default directory set sucessfully");
		
		try {
			responseString = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseString;
	}
	
	@POST
	@Path("/createtorrent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createTorrent(String requestJson){
		
		String responseString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		CreateTorrentResponseMapper response = new CreateTorrentResponseMapper();
		response.setSuccess("true");
		response.setMessage("Torrent created successfully");
		response.setDefaultDirectory(localDefaultDirectory);
		
		try {
			responseString = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseString;
	}
	
	@GET
	@Path("/gettorrents")
	@Produces(MediaType.APPLICATION_JSON)
	public String getTorrents(){
		
		ObjectMapper mapper = new ObjectMapper();
		String responseString = null;
		
		List<MonitorResponseMapper> responselist = new ArrayList<MonitorResponseMapper>();
		
		MonitorResponseMapper metadata = new MonitorResponseMapper();
		metadata.setDownloadSpeed("50kbps");
		metadata.setEta("12 min");
		metadata.setFileName("abc.txt");
		metadata.setPeers("50");
		metadata.setProgress("40%");
		metadata.setSeeds("10");
		metadata.setSize("40GB");
		metadata.setStatus("Sharing");
		metadata.setUploadSpeed("20kbps");
		responselist.add(metadata);
		
		MonitorResponseMapper metadata2 = new MonitorResponseMapper();
		metadata2.setDownloadSpeed("100kbps");
		metadata2.setEta("6 min");
		metadata2.setFileName("qwerty.txt");
		metadata2.setPeers("40");
		metadata2.setProgress("80%");
		metadata2.setSeeds("20");
		metadata2.setSize("80GB");
		metadata2.setStatus("Seeding");
		metadata2.setUploadSpeed("40kbps");
		responselist.add(metadata2);
		
		try {
			responseString = mapper.writeValueAsString(responselist);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseString;
	}
	
	@POST
	@Path("/starttorrent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String startTorrent(String requestJson){
		
		String responseString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		GenericTorrentResponseMapper response = new GenericTorrentResponseMapper();
		
		response.setSuccess("true");
		response.setMessage("Torrent started");
		
		MonitorResponseMapper metadata = new MonitorResponseMapper();
		metadata.setDownloadSpeed("0kbps");
		metadata.setEta("0 min");
		metadata.setFileName("abc.txt");
		metadata.setPeers("0");
		metadata.setProgress("0%");
		metadata.setSeeds("0");
		metadata.setSize("0GB");
		metadata.setStatus("Sharing");
		metadata.setUploadSpeed("0kbps");
		response.setMetadata(metadata);
		
		try {
			responseString = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseString;
	}
 
	@POST
	@Path("/pausetorrent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String pauseTorrent(String requestJson){
		
		String responseString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		GenericResponseStatusMapper genericresponse = new GenericResponseStatusMapper();
		
		genericresponse.setSuccess("true");
		genericresponse.setMessage("Torrent paused");
		
		try {
			responseString = mapper.writeValueAsString(genericresponse);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseString;
	}
	
	@POST
	@Path("/deletetorrent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String deleteTorrent(String requestJson){
		
		String responseString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		GenericResponseStatusMapper genericresponse = new GenericResponseStatusMapper();
		
		genericresponse.setSuccess("true");
		genericresponse.setMessage("Torrent deleted");
		
				
		try {
			responseString = mapper.writeValueAsString(genericresponse);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseString;
	}
	
	@POST
	@Path("/downloadtorrent")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String downloadTorrent(String requestJson){
		
		String responseString = null;
		ObjectMapper mapper = new ObjectMapper();
		
		GenericTorrentResponseMapper response = new GenericTorrentResponseMapper();
		
		response.setSuccess("true");
		response.setMessage("Torrent resumed");
		
		MonitorResponseMapper metadata = new MonitorResponseMapper();
		metadata.setDownloadSpeed("0kbps");
		metadata.setEta("0 min");
		metadata.setFileName("abc.txt");
		metadata.setPeers("0");
		metadata.setProgress("0%");
		metadata.setSeeds("0");
		metadata.setSize("0GB");
		metadata.setStatus("Sharing");
		metadata.setUploadSpeed("0kbps");
		response.setMetadata(metadata);
		
		try {
			responseString = mapper.writeValueAsString(response);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseString;

	}
	
	@GET
	@Path("/view")
	@Produces(MediaType.TEXT_HTML)
	public Response downloadTorrent(){
		
		
	        return Response.ok(new Viewable("/index.html")).build();
	    
	}
}
