<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management</title>
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

    <div class="container text-center" style="margin-top: 5rem;">
        <h1>Hospital Management System</h1>
        <p style="color: var(--text-muted); font-size: 1.2rem; margin-bottom: 3rem;">
            Efficiently manage patient records, doctor profiles, and appointments directly from your dashboard.
        </p>

        <div class="card-grid">
            <div class="card">
                <h2>Patients</h2>
                <p>Manage patient records and view history.</p>
                <a href="patients" class="btn btn-primary">Manage Patients</a>
            </div>
            <div class="card">
                <h2>Doctors</h2>
                <p>Manage doctor profiles and specializations.</p>
                <a href="doctors" class="btn btn-primary">Manage Doctors</a>
            </div>
        </div>
    </div>
</body>
</html>
