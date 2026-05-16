<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Patient Details</title>
            <link rel="stylesheet" type="text/css" href="css/style.css">
        </head>

        <body>
            <header>
                <div class="container">
                    <nav>
                        <ul>
                            <li><a href="index.jsp">Home</a></li>
                            <li><a href="patients">Patients</a></li>
                            <li><a href="doctors">Doctors</a></li>
                        </ul>
                    </nav>
                </div>
            </header>

            <div class="container">
                <h1>Patient Details</h1>

                <div class="details-header">
                    <div class="details-info">
                        <h2>
                            <c:out value="${patient.name}" />
                        </h2>
                        <p><strong>Age:</strong>
                            <c:out value="${patient.age}" />
                        </p>
                        <p><strong>Phone:</strong>
                            <c:out value="${patient.phone}" />
                        </p>
                        <p><strong>Email:</strong>
                            <c:out value="${patient.email}" />
                        </p>
                    </div>
                    <div>
                        <a href="patients?action=edit&id=${patient.id}" class="btn btn-primary">Edit Details</a>
                        <a href="patients" class="btn btn-secondary">Back to List</a>
                    </div>
                </div>

                <div class="mb-2">
                    <h2>Appointments</h2>

                    <!-- Book Appointment Form -->
                    <div class="form-container" style="max-width: 100%; margin: 1rem 0; padding: 1.5rem;">
                        <h3>Book New Appointment</h3>
                        <form action="patients" method="post" style="display: flex; gap: 1rem; align-items: flex-end;">
                            <input type="hidden" name="action" value="book" />
                            <input type="hidden" name="patientId" value="${patient.id}" />

                            <div style="flex-grow: 1;">
                                <label>Select Doctor</label>
                                <select name="doctorId" required>
                                    <option value="">-- Choose a Doctor --</option>
                                    <c:forEach var="doc" items="${allDoctors}">
                                        <option value="${doc.id}">Dr. ${doc.name} (${doc.specialization})</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Book Appointment</button>
                        </form>
                    </div>

                    <!-- List of Current Appointments -->
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>Doctor Name</th>
                                    <th>Specialization</th>
                                    <th>Doctor Email</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty patient.doctors}">
                                    <tr>
                                        <td colspan="4" class="text-center">No appointments booked.</td>
                                    </tr>
                                </c:if>
                                <c:forEach var="doctor" items="${patient.doctors}">
                                    <tr>
                                        <td>Dr.
                                            <c:out value="${doctor.name}" />
                                        </td>
                                        <td>
                                            <c:out value="${doctor.specialization}" />
                                        </td>
                                        <td>
                                            <c:out value="${doctor.email}" />
                                        </td>
                                        <td>
                                            <form action="patients" method="post" style="margin: 0;">
                                                <input type="hidden" name="action" value="cancel" />
                                                <input type="hidden" name="patientId" value="${patient.id}" />
                                                <input type="hidden" name="doctorId" value="${doctor.id}" />
                                                <button type="submit" class="btn btn-danger"
                                                    onclick="return confirm('Cancel this appointment?')">Cancel</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </body>

        </html>