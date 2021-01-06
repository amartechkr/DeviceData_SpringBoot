package com.oxf.amar.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oxf.amar.model.DeviceData;
import com.oxf.amar.repositories.DeviceRepositories;

@RestController
@RequestMapping("/api")
public class DeviceController {

	private static final Logger LOGGER=LoggerFactory.getLogger(DeviceController.class); 
	
	@Autowired
	private DeviceRepositories deviceRepositories;

	// create the device Data
	@PostMapping("/add_devices")
	public ResponseEntity<DeviceData> createDeviceData(@RequestBody DeviceData deviceData) {
		LOGGER.info("Add device called {}");
		try {

			DeviceData _deviceData = deviceRepositories.save(new DeviceData(deviceData.getDeviceId(),
					deviceData.getDeviceType(), deviceData.getDeviceName(), deviceData.getOSVersion(),
					deviceData.getCpu(), deviceData.getRam(), deviceData.getStorage()));

			return new ResponseEntity<>(_deviceData, HttpStatus.CREATED);

		} catch (Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update the Device Details
	@PutMapping("/devices/{deviceId}")
	public ResponseEntity<DeviceData> updateDeviceData(@PathVariable("deviceId") String deviceId,
			@RequestBody DeviceData deviceData) {
		LOGGER.info("update device called {}");
		Optional<DeviceData> device = deviceRepositories.findById(deviceId);

		if (device.isPresent()) {
			DeviceData _deviceData = device.get();

			_deviceData.setDeviceType(deviceData.getDeviceType());
			_deviceData.setDeviceName(deviceData.getDeviceName());
			_deviceData.setOSVersion(deviceData.getOSVersion());
			_deviceData.setCpu(deviceData.getCpu());
			_deviceData.setRam(deviceData.getRam());
			_deviceData.setStorage(deviceData.getStorage());

			return new ResponseEntity<>(deviceRepositories.save(_deviceData), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	// Get Device based on deviceId, to get any device Data
	@GetMapping("/device/{deviceId}")
	public ResponseEntity<DeviceData> getDeviceById(@PathVariable("deviceId") String deviceId) {
		LOGGER.info("Get device by deviceID called {}");
		Optional<DeviceData> devideDataInfo = deviceRepositories.findById(deviceId);

		if (devideDataInfo.isPresent()) {
			return new ResponseEntity<>(devideDataInfo.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Get all device
	@GetMapping("/all_device")
	public ResponseEntity<DeviceData> findAllDevice() {
		LOGGER.info("Get All device called {}");
		try {
			deviceRepositories.findAll();

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// delete device details
	@DeleteMapping("/devices/{deviceId}")
	public ResponseEntity<DeviceData> deleteDeviceById(@PathVariable("deviceId") String deviceId) {
		LOGGER.info("Delete device called {}");
		try {
			deviceRepositories.deleteById(deviceId);

			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
