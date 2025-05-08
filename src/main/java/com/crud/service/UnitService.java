package com.crud.service;

import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.DashBoardImages;
import com.crud.entity.Unit;
import com.crud.response.Response;

public interface UnitService {
	
	public Response addUnit(Unit body);
	public Response getUnitList();
	
	public Response addDashboardImages(String name,MultipartFile dashboard_File);
	public Response getDashboardImages();

}
