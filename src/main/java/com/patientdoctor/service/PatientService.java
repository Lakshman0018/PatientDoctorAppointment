package com.patientdoctor.service;

import com.patientdoctor.entity.Patient;
import java.util.List;

public interface PatientService {
    void addPatient(Patient patient);

    void updatePatient(Patient patient);

    void deletePatient(Long id);

    Patient getPatientById(Long id);

    List<Patient> getAllPatients();

    void bookAppointment(Long patientId, Long doctorId);

    void cancelAppointment(Long patientId, Long doctorId);
}
