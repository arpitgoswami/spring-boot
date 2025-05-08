package com.crud.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.DashBoardImages;
import com.crud.entity.Unit;
import com.crud.repository.DashBoardImagesRepository;
import com.crud.repository.UnitRepository;
import com.crud.response.Response;
import com.crud.service.UnitService;

import util.Utility;

@Service
public class UnitServiceImpl implements UnitService {
	
	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private DashBoardImagesRepository dashBoardImagesRepository;

	@Override
	public Response addUnit(Unit body) {
		Response response = new Response();
		
		try {
			Unit save = unitRepository.save(body);
			if(save!=null) {
				response.setCode(200);
				response.setMessage("Data Added Successfully");
				response.setObject(save);
				return response;
			}else {
				response.setCode(400);
				response.setMessage("Data Submittion is failed");
				response.setObject(null);
				return response;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
		
	}

	@Override
	public Response getUnitList() {
		Response response = new Response();
		
		try {
			List<Unit> all = unitRepository.findAll();
			if(all.isEmpty()) {
				response.setCode(400);
				response.setMessage("Data Not Found !");
				response.setObject(null);
				return response;
			}else {
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully");
				response.setObject(all);
				return response;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
	}

	

	@Override
	public Response getDashboardImages() {
		Response response = new Response();
		
		try {
			List<DashBoardImages> all = dashBoardImagesRepository.findAll();
			if(all.isEmpty()) {
				response.setCode(400);
				response.setMessage("Data not found");
				response.setObject(null);
				return response;
			}else {
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully !");
				response.setObject(all);
				return response;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
		
		
	}

	@Override
	public Response addDashboardImages(String name, MultipartFile dashboard_File) {
		Response response = new Response();
		DashBoardImages dashBoardImagesBody = new DashBoardImages();
		
		try {
			if(name!=null) {
				dashBoardImagesBody.setName(name);
			}else {
				response.setCode(400);
				response.setMessage("name can't be null");
				response.setObject(null);
				return response;
			}
			
			if(dashboard_File!=null) {
				String dashBoardImageFile = Utility.uploadFile(dashboard_File, "dashboard_File");
				if(dashBoardImageFile!=null) {
					dashBoardImagesBody.setFilePath(dashBoardImageFile);
				}else {
					response.setCode(400);
					response.setMessage("file upload failed");
					response.setObject(null);
					return response;
				}
			}else {
				response.setCode(400);
				response.setMessage("file can't be null");
				response.setObject(null);
				return response;
				
			}
			
			DashBoardImages save = dashBoardImagesRepository.save(dashBoardImagesBody);
			if(save!=null) {
				response.setCode(200);
				response.setMessage("Data added Successfully !");
				response.setObject(save);
				return response;
			}else {
				response.setCode(400);
				response.setMessage("Failed to add Data !");
				response.setObject(null);
				return response;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
			
	}

}
