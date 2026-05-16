package com.patientdoctor.controller;

import com.patientdoctor.entity.Doctor;
import com.patientdoctor.entity.Patient;
import com.patientdoctor.service.DoctorService;
import com.patientdoctor.service.DoctorServiceImpl;
import com.patientdoctor.service.PatientService;
import com.patientdoctor.service.PatientServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {
    private PatientService patientService;
    private DoctorService doctorService;

    public void init() {
        patientService = new PatientServiceImpl();
        doctorService = new DoctorServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action) {
            case "insert":
                insertPatient(request, response);
                break;
            case "update":
                updatePatient(request, response);
                break;
            case "book":
                bookAppointment(request, response);
                break;
            case "cancel":
                cancelAppointment(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "insert":
                insertPatient(request, response);
                break;
            case "delete":
                deletePatient(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updatePatient(request, response);
                break;
            case "view":
                viewPatient(request, response);
                break;
            default:
                listPatients(request, response);
                break;
        }
    }

    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Patient> listPatients = patientService.getAllPatients();
        request.setAttribute("listPatients", listPatients);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Patient existingPatient = patientService.getPatientById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        request.setAttribute("patient", existingPatient);
        dispatcher.forward(request, response);
    }

    private void insertPatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Patient newPatient = new Patient();
        newPatient.setName(name);
        newPatient.setAge(age);
        newPatient.setPhone(phone);
        newPatient.setEmail(email);

        patientService.addPatient(newPatient);
        response.sendRedirect("patients");
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        Patient patient = patientService.getPatientById(id);
        patient.setName(name);
        patient.setAge(age);
        patient.setPhone(phone);
        patient.setEmail(email);

        patientService.updatePatient(patient);
        response.sendRedirect("patients");
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        patientService.deletePatient(id);
        response.sendRedirect("patients");
    }

    private void viewPatient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Patient patient = patientService.getPatientById(id);
        List<Doctor> allDoctors = doctorService.getAllDoctors(); // For booking dropdown

        request.setAttribute("patient", patient);
        request.setAttribute("allDoctors", allDoctors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-view.jsp");
        dispatcher.forward(request, response);
    }

    private void bookAppointment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long patientId = Long.parseLong(request.getParameter("patientId"));
        Long doctorId = Long.parseLong(request.getParameter("doctorId"));
        patientService.bookAppointment(patientId, doctorId);
        response.sendRedirect("patients?action=view&id=" + patientId);
    }

    private void cancelAppointment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long patientId = Long.parseLong(request.getParameter("patientId"));
        Long doctorId = Long.parseLong(request.getParameter("doctorId"));
        patientService.cancelAppointment(patientId, doctorId);
        response.sendRedirect("patients?action=view&id=" + patientId);
    }
}
