package com.oxf.amar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "DeviceData")
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeviceData {

	@Id
	private String deviceId;
	
	private String deviceType;
	private String DeviceName;
	private String OSVersion;
	private String ram;
	private String storage;
	private String cpu;
	
}
