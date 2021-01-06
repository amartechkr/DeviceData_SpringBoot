package com.oxf.amar.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.oxf.amar.model.DeviceData;

@Repository
public interface DeviceRepositories extends MongoRepository<DeviceData, String>{

}
