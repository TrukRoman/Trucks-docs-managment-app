<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 07/11/2020
  Time: 18:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
    <title>Trucks Docs Management App</title>
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
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${driver != null}">
            <form action="updateDriver" method="post">
                </c:if>
                <c:if test="${driver == null}">
                <form action="insertDriver" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${driver != null}">
                                Edit User
                            </c:if>
                            <c:if test="${driver == null}">
                                Add New Driver
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${driver != null}">
                        <input type="hidden" name="id" value="<c:out value='${driver.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Driver Name</label> <input type="text" value="<c:out value='${driver.name}' />" class="form-control" name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Driver Licence Number</label> <input type="text" value="<c:out value='${driver.driverLicenceNum}' />" class="form-control" name="driverLicenceNum">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Driver licence category</label> <input type="text" value="<c:out value='${driver.category}' />" class="form-control" name="category">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Driver licence validity</label> <input type="text" value="<c:out value='${driver.dlvalidity}' />" class="form-control" name="dlvalidity">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Medical Certificate Number</label> <input type="text" value="<c:out value='${driver.medicalCertificateNumber}' />" class="form-control" name="medicalCertificateNumber">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Medical Certificate validity</label> <input type="text" value="<c:out value='${driver.mcvalidaty}' />" class="form-control" name="mcvalidaty">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>
