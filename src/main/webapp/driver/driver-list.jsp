<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 07/11/2020
  Time: 18:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>User Management Application</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #169a00">
        <div>
            <a href="<%=request.getContextPath()%>/driver" class="navbar-brand"> Truck Docs Management App </a>
        </div>
        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/driver" class="nav-link">Drivers</a></li>
            <li><a href="<%=request.getContextPath()%>/truck" class="nav-link">Trucks</a></li>
        </ul>
    </nav>
</header>
<br>

<div class="row">

    <div class="container">
        <h3 class="text-center">List of Drivers</h3>
        <hr>
        <div class="container text-left" >
            <a href="<%=request.getContextPath()%>/newdriver" class="btn btn-success">Add New Driver</a>
            <a href="<%=request.getContextPath()%>/validatyDriver" class="btn btn-success">Drivers validity period ends</a>
        </div>
        <br>
        <div class="table-responsive">
        <table class="table table-bordered">
            <thead class="thead-light">
            <tr>
                <th width="2%">ID</th>
                <th width="20%">Name</th>
                <th width="10%">Driver Licence Number</th>
                <th width="8%">Category</th>
                <th width="12%">DL Validity</th>
                <th width="15%">Medical Certificate Number</th>
                <th width="15%">Medical Certificate Validity</th>
                <th width="20">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="driver" items="${listDriver}">
                <tr>
                    <td>
                        <c:out value="${driver.id}" />
                    </td>
                    <td>
                        <c:out value="${driver.name}" />
                    </td>
                    <td>
                        <c:out value="${driver.driverLicenceNum}" />
                    </td>
                    <td>
                        <c:out value="${driver.category}" />
                    </td>
                    <td>
                        <c:out value="${driver.dlvalidity}" />
                    </td>
                    <td>
                        <c:out value="${driver.medicalCertificateNumber}" />
                    </td>
                    <td>
                        <c:out value="${driver.mcvalidaty}" />
                    </td>
                    <td><a href="editDriver?id=<c:out value='${driver.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteDriver?id=<c:out value='${driver.id}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
        </div>
    </div>
</div>
</body>

</html>
