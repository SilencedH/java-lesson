package net.youzule.java.http.file.module.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**  
* @Title: ExcelService.java
* @Description:  
* @author：zhaodahai  
* @date 2018年9月15日  下午4:49:08
*/

public interface ExcelService {
	void doResponse(HttpServletResponse response);
	void exportExcel(HttpServletResponse response);
	void exportExcel1(HttpServletResponse response);
	String exportExcel2(HttpServletResponse response);
	//导入
	String importExcel1(MultipartFile file);
}
