<%--
  Created by IntelliJ IDEA.
  User: roman
  Date: 08/11/2020
  Time: 23:43
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
            <c:if test="${truck != null}">
            <form action="updateTruck" method="post">
                </c:if>
                <c:if test="${truck == null}">
                <form action="insertTruck" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${truck != null}">
                                Edit Truck
                            </c:if>
                            <c:if test="${truck == null}">
                                Add New Truck
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${truck != null}">
                        <input type="hidden" name="id" value="<c:out value='${truck.id}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>Model</label> <input type="text" value="<c:out value='${truck.model}' />" class="form-control" name="model" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Type</label> <input type="text" value="<c:out value='${truck.type}' />" class="form-control" name="type">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Year Of Production</label> <input type="text" value="<c:out value='${truck.yearOfProduction}' />" class="form-control" name="yearOfProduction">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Register Sign</label> <input type="text" value="<c:out value='${truck.registerSign}' />" class="form-control" name="registerSign">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Insurance Number</label> <input type="text" value="<c:out value='${truck.insuranceNumber}' />" class="form-control" name="insuranceNumber">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Insurance Validity</label> <input type="text" value="<c:out value='${truck.insuranceValidity}' />" class="form-control" name="insuranceValidity">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Technical Inspection Validity</label> <input type="text" value="<c:out value='${truck.technicalInspectionValidity}' />" class="form-control" name="technicalInspectionValidity">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>

</html>
