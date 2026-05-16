package com.patientdoctor.dao;

import com.patientdoctor.entity.Doctor;
import java.util.List;

public interface DoctorDAO {
    void saveDoctor(Doctor doctor);

    void updateDoctor(Doctor doctor);

    void deleteDoctor(Long id);

    Doctor getDoctorById(Long id);

    List<Doctor> getAllDoctors();
}
