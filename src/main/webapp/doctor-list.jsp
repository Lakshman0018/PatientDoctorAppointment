<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>Doctors</title>
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
                    <h1>Doctors</h1>
                    <a href="doctors?action=new" class="btn btn-primary">Add New Doctor</a>
                </div>

                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Specialization</th>
                                <th>Email</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="doctor" items="${listDoctors}">
                                <tr>
                                    <td>
                                        <c:out value="${doctor.id}" />
                                    </td>
                                    <td>Dr.
                                        <c:out value="${doctor.name}" />
                                    </td>
                                    <td>
                                        <c:out value="${doctor.specialization}" />
                                    </td>
                                    <td>
                                        <c:out value="${doctor.email}" />
                                    </td>
                                    <td class="actions">
                                        <a href="doctors?action=edit&id=${doctor.id}" class="btn btn-secondary">Edit</a>
                                        <a href="doctors?action=delete&id=${doctor.id}" class="btn btn-danger"
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