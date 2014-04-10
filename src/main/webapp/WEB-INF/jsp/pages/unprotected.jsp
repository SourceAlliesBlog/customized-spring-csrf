<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="protectedUrl" value="/protected"/>
<c:url var="unprotectedUrl" value="/unprotected"/>
<c:url var="logoutUrl" value="/j_spring_security_logout"/>

<!DOCTYPE html>

<html lang="en-US">
<head>
    <meta charset="utf-8">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" media="screen"
          href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
    <script type="text/javascript" src="<c:url value="/resources/jquery/jquery.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script>
    <style type="text/css">
        body { padding-top: 70px; }
    </style>

</head>
<body>

<div class="container-fluid">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="col-md-12">
                <div class="navbar-collapse collapse" id="additional-nav">
                    <sec:authorize access="isAnonymous()">
                        <ul class="nav navbar-nav">
                            <li id="login"><a href="${loginUrl}">Login</a></li>
                        </ul>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <ul class="nav navbar-nav">
                            <li><a id="protected" href="${protectedUrl}">Protected</a></li>
                            <li class="active"><a id="unprotected" href="${unprotectedUrl}">Unprotected</a></li>
                        </ul>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a id="logout" href="${logoutUrl}">Logout</a></li>
                        </ul>
                    </sec:authorize>
                </div>
            </div>
        </div>
    </nav>

    <ol class="breadcrumb">
        <li class="active">Unprotected</li>
    </ol>

    <div class="row">
        <div class="col-md-12">

            <c:if test="${message != null}">
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    <c:out value="${message}"/>
                </div>
            </c:if>

            <form:form action="${unprotectedUrl}" class="form-horizontal" htmlEscape="true" modelAttribute="nameRequest"
                       method="POST">

                <fieldset>
                    <div class="form-group">
                        <label for="first" class="col-xs-4 col-sm-2 control-label">First Name</label>

                        <div class="col-xs-8 col-sm-4">
                            <form:input path="first" id="first"
                                        type="text"
                                        class="form-control"
                                        placeholder="First Name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="last" class="col-xs-4 col-sm-2 control-label">Last Name</label>

                        <div class="col-xs-8 col-sm-4">
                            <form:input path="last" id="last"
                                        type="text" class="form-control"
                                        placeholder="Last Name"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-4 col-sm-offset-2 col-xs-10">
                            <button type="submit" id="submit" class="btn btn-primary btn-large">Submit</button>
                        </div>
                    </div>
                </fieldset>
            </form:form>
        </div>
    </div>
</div>

</body>
</html>