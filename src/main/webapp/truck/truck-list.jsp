<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 08/11/2020
  Time: 23:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Truck Docs Management App</title>
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
        <h3 class="text-center">List of Trucks</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/newtruck" class="btn btn-success">Add New Truck</a>
            <a href="<%=request.getContextPath()%>/validatyTruck" class="btn btn-success">Trucks validity period ends</a>
        </div>
        <br>
        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="thead-light">
                <tr>
                    <th width="2%">ID</th>
                    <th width="17%">Model</th>
                    <th width="10%">Type</th>
                    <th width="8%">Year Of Production</th>
                    <th width="12%">Register Sign</th>
                    <th width="12%">Insurance Number</th>
                    <th width="12%">Insurance Validity</th>
                    <th width="12%">Technical Inspection Validity</th>
                    <th width="35">Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="truck" items="${listTrucks}">

                    <tr>
                        <td>
                            <c:out value="${truck.id}" />
                        </td>
                        <td>
                            <c:out value="${truck.model}" />
                        </td>
                        <td>
                            <c:out value="${truck.type}" />
                        </td>
                        <td>
                            <c:out value="${truck.yearOfProduction}" />
                        </td>
                        <td>
                            <c:out value="${truck.registerSign}" />
                        </td>
                        <td>
                            <c:out value="${truck.insuranceNumber}" />
                        </td>
                        <td>
                            <c:out value="${truck.insuranceValidity}" />
                        </td>
                        <td>
                            <c:out value="${truck.technicalInspectionValidity}" />
                        </td>
                        <td><a href="editTruck?id=<c:out value='${truck.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="deleteTruck?id=<c:out value='${truck.id}' />">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </div>
</div>
</body>

</html>

