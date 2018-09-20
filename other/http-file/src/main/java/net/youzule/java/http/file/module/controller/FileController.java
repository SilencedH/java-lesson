package net.youzule.java.http.file.module.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import net.youzule.java.http.file.module.service.FileService;

/**  
* @Title: FileUploadController.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月19日  上午11:29:50
*/

@RestController
@RequestMapping("/file")
public class FileController {
	
	@Autowired
	private FileService fileService ;
	
	@RequestMapping("/upload1")
	public String upload(@RequestParam("fileName") MultipartFile file) {
		
		return fileService.upload(file);
	}

}
