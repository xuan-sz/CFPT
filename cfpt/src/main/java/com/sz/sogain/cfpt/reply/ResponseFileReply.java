package com.sz.sogain.cfpt.reply;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter @Setter @NoArgsConstructor @ToString
@JsonInclude(Include.NON_NULL)
public class ResponseFileReply {

	private String url;
	private String name;
	private String id;
	private String type;
	private String code;
	private String msg;
	private String status;
	
//	public ResponseFileReply convert(MsAccessoryEntity entity) {
//		this.setId(entity.getId());
//		this.setName(entity.getName());
//		this.setType(entity.getType());
//		this.setUrl(entity.getUrlPath());
//		this.setStatus("done");
//		return this;
//	}
}
