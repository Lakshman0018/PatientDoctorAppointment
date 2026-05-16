package com.patientdoctor.controller;

import com.patientdoctor.entity.Doctor;
import com.patientdoctor.service.DoctorService;
import com.patientdoctor.service.DoctorServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors")
public class DoctorServlet extends HttpServlet {
    private DoctorService doctorService;

    public void init() {
        doctorService = new DoctorServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "list";

        switch (action) {
            case "insert":
                insertDoctor(request, response);
                break;
            case "update":
                updateDoctor(request, response);
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
                insertDoctor(request, response);
                break;
            case "delete":
                deleteDoctor(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateDoctor(request, response);
                break;
            default:
                listDoctors(request, response);
                break;
        }
    }

    private void listDoctors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Doctor> listDoctors = doctorService.getAllDoctors();
        request.setAttribute("listDoctors", listDoctors);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Doctor existingDoctor = doctorService.getDoctorById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        request.setAttribute("doctor", existingDoctor);
        dispatcher.forward(request, response);
    }

    private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");

        Doctor newDoctor = new Doctor();
        newDoctor.setName(name);
        newDoctor.setSpecialization(specialization);
        newDoctor.setEmail(email);

        doctorService.addDoctor(newDoctor);
        response.sendRedirect("doctors");
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String email = request.getParameter("email");

        Doctor doctor = doctorService.getDoctorById(id);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setEmail(email);

        doctorService.updateDoctor(doctor);
        response.sendRedirect("doctors");
    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        doctorService.deleteDoctor(id);
        response.sendRedirect("doctors");
    }
}
