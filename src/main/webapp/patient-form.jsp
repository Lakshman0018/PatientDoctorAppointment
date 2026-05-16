<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>
                <c:if test="${patient != null}">Edit Patient</c:if>
                <c:if test="${patient == null}">New Patient</c:if>
            </title>
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

            <div class="container" style="margin-top: 3rem;">
                <div class="form-container">
                    <h2 class="text-center">
                        <c:if test="${patient != null}">Edit Patient</c:if>
                        <c:if test="${patient == null}">Add New Patient</c:if>
                    </h2>

                    <form action="patients" method="post">
                        <c:if test="${patient != null}">
                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="id" value="<c:out value='${patient.id}' />" />
                        </c:if>
                        <c:if test="${patient == null}">
                            <input type="hidden" name="action" value="insert" />
                        </c:if>

                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" value="<c:out value='${patient.name}' />" required />
                        </div>

                        <div class="form-group">
                            <label>Age</label>
                            <input type="number" name="age" value="<c:out value='${patient.age}' />" required />
                        </div>

                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" name="phone" value="<c:out value='${patient.phone}' />" required />
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" value="<c:out value='${patient.email}' />" required />
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <a href="patients" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </body>

        </html>