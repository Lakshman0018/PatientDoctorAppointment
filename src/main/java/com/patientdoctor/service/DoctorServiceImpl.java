package com.patientdoctor.service;

import com.patientdoctor.dao.DoctorDAO;
import com.patientdoctor.dao.DoctorDAOImpl;
import com.patientdoctor.entity.Doctor;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDAO doctorDAO = new DoctorDAOImpl();

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDAO.saveDoctor(doctor);
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        doctorDAO.updateDoctor(doctor);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorDAO.deleteDoctor(id);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorDAO.getDoctorById(id);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorDAO.getAllDoctors();
    }
}
