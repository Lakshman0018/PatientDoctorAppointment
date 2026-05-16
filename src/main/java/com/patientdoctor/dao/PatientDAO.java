package com.patientdoctor.dao;

import com.patientdoctor.entity.Patient;
import java.util.List;

public interface PatientDAO {
    void savePatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatient(Long id);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();
}
