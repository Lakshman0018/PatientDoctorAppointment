package com.patientdoctor.service;

import com.patientdoctor.dao.DoctorDAO;
import com.patientdoctor.dao.DoctorDAOImpl;
import com.patientdoctor.dao.PatientDAO;
import com.patientdoctor.dao.PatientDAOImpl;
import com.patientdoctor.entity.Doctor;
import com.patientdoctor.entity.Patient;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    // Dependency Injection would be better, but for simple Servlet/JSP app without
    // Spring, direct instantiation is common.
    // However, adhering to SOLID (DIP), we should depend on abstractions.
    private PatientDAO patientDAO = new PatientDAOImpl();
    private DoctorDAO doctorDAO = new DoctorDAOImpl();

    @Override
    public void addPatient(Patient patient) {
        patientDAO.savePatient(patient);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientDAO.updatePatient(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientDAO.deletePatient(id);
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientDAO.getPatientById(id);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientDAO.getAllPatients();
    }

    @Override
    public void bookAppointment(Long patientId, Long doctorId) {
        // This operation requires a transaction spanning both retrieval and update.
        // DAO methods manage their own transactions, which is not ideal for business
        // logic spanning multiple DAOs.
        // Ideally, Service should manage Transaction.
        // For this assignment, I will re-implement the logic using a single session
        // here purely for the relational update
        // OR rely on DAO updates if they handle cascading correctly.
        // Since Patient.doctors has CascadeType.ALL, updating patient should save the
        // relation.

        // But wait, DAOs open distinct sessions.
        // For a ManyToMany update, fetching entities in one session and updating them
        // in another works if they are re-attached or if we use `update`.

        Patient patient = patientDAO.getPatientById(patientId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);

        if (patient != null && doctor != null) {
            patient.addDoctor(doctor);
            // We use a custom transaction here or rely on updatePatient which opens a new
            // session.
            // Since patient is detached after getPatientById closes session,
            // updatePatient(patient) will re-attach/merge it.
            patientDAO.updatePatient(patient);
        }
    }

    @Override
    public void cancelAppointment(Long patientId, Long doctorId) {
        Patient patient = patientDAO.getPatientById(patientId);
        Doctor doctor = doctorDAO.getDoctorById(doctorId);

        if (patient != null && doctor != null) {
            patient.removeDoctor(doctor);
            patientDAO.updatePatient(patient);
        }
    }
}
