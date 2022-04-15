package com.sz.sogain.cfpt.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sz.sogain.cfpt.cfptinterface.util.NotValidationRequiredApiController;
import com.sz.sogain.cfpt.reply.ResponseFileReply;
import com.sz.sogain.cfpt.service.FileUploadServer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "upload-controller", description = "upload file API")
@NotValidationRequiredApiController
public class FileUploadController {
	/**
	 * 2022/04/15
	 * react框架與 vue框架上傳組件的請求頭包含的body不一樣，所以需要用不同的注解
	 * react : @RequestPart("file")
	 * vue : @RequestParam(required = false)
	 * 
	 */
	
	@Autowired
	private FileUploadServer fileUploadServer;
	
	@PostMapping("/api/file/react/upload")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ResponseFileReply.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized operation"),
			@ApiResponse(responseCode = "404", description = "data not found"), })
	@ResponseBody
	public List<ResponseFileReply> commonUploadFile(@RequestPart("file") MultipartFile[] files,
			HttpServletRequest request) throws IOException {
		return fileUploadServer.uploadFile(files);
	}
	
	@PostMapping("/api/file/vue/upload")
	@Operation(summary = "Upload file", description = "", tags = { "projectreference" })
	@Parameter(name = "files", description = "params MultipartFile")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = ResponseFileReply.class))),
			@ApiResponse(responseCode = "401", description = "unauthorized operation"),
			@ApiResponse(responseCode = "404", description = "data not found"), })
	public List<ResponseFileReply> uploadFile(@RequestParam(required = false) MultipartFile[] files,
			HttpServletRequest request) throws IOException {
		return fileUploadServer.uploadFile(files);
	}

}
