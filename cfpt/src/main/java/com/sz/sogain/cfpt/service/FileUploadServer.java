package com.sz.sogain.cfpt.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sz.sogain.cfpt.reply.ResponseFileReply;
import com.sz.sogain.cfpt.utils.FileType;
import com.sz.sogain.cfpt.utils.TimeUtil;

@Service
@Transactional
public class FileUploadServer {
	
	final static String DEFAULT1 = "%s.%s";
	final static String DEFAULT2 = "%s/%s";
	Logger log = LoggerFactory.getLogger(FileUploadServer.class);
	
	@Value("${upload.url}")
	private String dirUrl;
	public List<ResponseFileReply> uploadFile(MultipartFile[] files)throws IOException  {
		log.info("filesLength----->"+files.length);
		List<ResponseFileReply> list = new ArrayList<ResponseFileReply>();
		File fileDir = new File(dirUrl);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		if (!StringUtils.isEmpty(files) && files.length > 0) {
			for (MultipartFile multipartFile : files) {
				ResponseFileReply file = new ResponseFileReply();
				InputStream in = multipartFile.getInputStream();
				// 根据文件头获取文件类型
				String type = FileType.getFileType(in);
				if (StringUtils.isEmpty(type)) {
					throw new IOException("Upload file format error!");
				}
				file.setType(type);
				String newName = String.format(DEFAULT1, TimeUtil.getTimeString(), type);
				file.setName(newName);
				String fileTypeDirPath = String.format(DEFAULT2, fileDir.getAbsolutePath(), type);
				File fileTypeDir = new File(fileTypeDirPath);
				if (!fileTypeDir.exists()) {
					fileTypeDir.mkdir();
				}
				file.setUrl(String.format(DEFAULT2, type, newName));
				File newFile = new File(fileTypeDir.getAbsolutePath(), newName);
				try {
					multipartFile.transferTo(newFile);
					list.add(file);
				} catch (IOException e) {
					file.setCode("500");
					file.setMsg(e.getMessage());
					throw new IOException(e.getMessage());
				}
			}
		}
		return list;
	}

}
