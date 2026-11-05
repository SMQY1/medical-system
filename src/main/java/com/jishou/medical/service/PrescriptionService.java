package com.jishou.medical.service;

import com.jishou.medical.entity.Prescription;
import com.jishou.medical.mapper.PrescriptionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionService {

    private final PrescriptionMapper prescriptionMapper;

    public PrescriptionService(PrescriptionMapper prescriptionMapper) {
        this.prescriptionMapper = prescriptionMapper;
    }

    public List<Prescription> listByRegistration(Long registrationId) {
        return prescriptionMapper.selectList(null);
    }
}
