<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <title>
                <c:if test="${doctor != null}">Edit Doctor</c:if>
                <c:if test="${doctor == null}">New Doctor</c:if>
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
                        <c:if test="${doctor != null}">Edit Doctor</c:if>
                        <c:if test="${doctor == null}">Add New Doctor</c:if>
                    </h2>

                    <form action="doctors" method="post">
                        <c:if test="${doctor != null}">
                            <input type="hidden" name="action" value="update" />
                            <input type="hidden" name="id" value="<c:out value='${doctor.id}' />" />
                        </c:if>
                        <c:if test="${doctor == null}">
                            <input type="hidden" name="action" value="insert" />
                        </c:if>

                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" name="name" value="<c:out value='${doctor.name}' />" required />
                        </div>

                        <div class="form-group">
                            <label>Specialization</label>
                            <input type="text" name="specialization" value="<c:out value='${doctor.specialization}' />"
                                required />
                        </div>

                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" value="<c:out value='${doctor.email}' />" required />
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Save</button>
                            <a href="doctors" class="btn btn-secondary">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </body>

        </html>