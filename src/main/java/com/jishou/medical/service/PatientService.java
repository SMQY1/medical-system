package com.jishou.medical.service;

import com.jishou.medical.entity.Patient;
import com.jishou.medical.mapper.PatientMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientMapper patientMapper;

    public PatientService(PatientMapper patientMapper) {
        this.patientMapper = patientMapper;
    }

    public List<Patient> listAll() {
        return patientMapper.selectList(null);
    }
}
