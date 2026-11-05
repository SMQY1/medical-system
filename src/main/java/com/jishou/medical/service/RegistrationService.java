package com.jishou.medical.service;

import com.jishou.medical.entity.Registration;
import com.jishou.medical.mapper.RegistrationMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    private final RegistrationMapper registrationMapper;

    public RegistrationService(RegistrationMapper registrationMapper) {
        this.registrationMapper = registrationMapper;
    }

    public List<Registration> listByPatient(Long patientId) {
        return registrationMapper.selectList(null);
    }
}
