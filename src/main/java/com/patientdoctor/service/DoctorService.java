package com.patientdoctor.service;

import com.patientdoctor.entity.Doctor;
import java.util.List;

public interface DoctorService {
    void addDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Long id);

    Doctor getDoctorById(Long id);

    List<Doctor> getAllDoctors();
}
