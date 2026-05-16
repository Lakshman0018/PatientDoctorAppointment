<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Patients</title>
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
                <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 2rem;">
                    <h1>Patients</h1>
                    <a href="patients?action=new" class="btn btn-primary">Add New Patient</a>
                </div>

                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Age</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="patient" items="${listPatients}">
                                <tr>
                                    <td>
                                        <c:out value="${patient.id}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.age}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.phone}" />
                                    </td>
                                    <td>
                                        <c:out value="${patient.email}" />
                                    </td>
                                    <td class="actions">
                                        <a href="patients?action=view&id=${patient.id}"
                                            class="btn btn-secondary">View</a>
                                        <a href="patients?action=edit&id=${patient.id}"
                                            class="btn btn-secondary">Edit</a>
                                        <a href="patients?action=delete&id=${patient.id}" class="btn btn-danger"
                                            onclick="return confirm('Are you sure?')">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </body>

        </html>