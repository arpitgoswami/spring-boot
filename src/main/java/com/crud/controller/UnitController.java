package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.DashBoardImages;
import com.crud.entity.Unit;
import com.crud.response.Response;
import com.crud.service.UnitService;
@CrossOrigin (origins="*", maxAge=3600)
@RestController
@RequestMapping("/unit")
public class UnitController {
	
	@Autowired
	private UnitService unitService;
	
	@PostMapping("/add")
	public Response addunit(@RequestBody Unit body) {
		Response unit = unitService.addUnit(body);
		return unit;
	}
	
	
	@GetMapping("/get")
	public Response getUnit() {
		Response unitList = unitService.getUnitList();
		return unitList;
	}
	
	
	@PostMapping("/add_dashboard_images")
	public Response addDashBoardImages(@RequestPart String name,@RequestPart(value="dashboard_File",required=true) MultipartFile dashboard_File) {
		Response dashboardImages = unitService.addDashboardImages(name, dashboard_File);
		return dashboardImages;
		
		}
	
	@GetMapping("/getDashboardImages")
	public Response getDashboardImages() {
		Response dashboardImages = unitService.getDashboardImages();
		return dashboardImages;
	}

}
