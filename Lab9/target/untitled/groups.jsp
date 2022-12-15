<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setBundle basename="resources"/>

<jsp:include page="layout.jsp"></jsp:include>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/material-design-iconic-font/2.2.0/css/material-design-iconic-font.min.css" integrity="sha256-3sPp8BkKUE7QyPSl6VfBByBroQbKxKG7tsusY2mhbVY=" crossorigin="anonymous" />
<link rel="stylesheet" href="css/read.css">

<div class="container">
    <div class="row">
        <div class="col-lg-10 mx-auto mb-4">
            <div class="section-title text-center ">
                <h3 class="top-c-sep">List of groups</h3>
                <p>Here you can observe all the groups of the university</p>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-lg-10 mx-auto">
            <div class="career-search mb-60">

                <c:forEach var="group" items="${groups}">
                <div class="filter-result">
                    <div class="job-box d-md-flex align-items-center justify-content-between mb-30">
                        <div class="job-left my-4 d-md-flex align-items-center flex-wrap">
                            <div class="job-content">
                                <h5 class="text-center text-md-left"> ${group.name} </h5>
                                <h5 class="text-center text-md-left"> Course: ${group.course} </h5>

                            </div>

                        </div>
                        <div style="position: absolute; left: 838px; " >
                            <form action="home" method="get">
                                <input name="command" type="hidden" value="deleteGroup">
                                <input name="id" type="hidden" value="${group.id}">
                                <button class="btn btn-danger" type="submit">Delete</button>
                            </form>
                        </div>
                        <div style="position: absolute; left: 768px;"  >
                            <div style="float: right; margin-left: 3px">
                                <form action="home" method="get">
                                    <input name="command" type="hidden" value="editGroup">
                                    <input name="id" type="hidden" value="${group.id}">
                                    <button class="btn btn-warning" type="submit">Edit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                    </div>
                    </c:forEach>



                </div>
            </div>

        </div>


    </div>
</div>
