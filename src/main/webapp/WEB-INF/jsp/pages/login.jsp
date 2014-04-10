<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:url var="loginPostUrl" value="/j_spring_security_check"/>
<c:url var="protectedUrl" value="/protected"/>
<c:url var="unprotectedUrl" value="/unprotected"/>
<c:url var="logoutUrl" value="/j_spring_security_logout"/>
<c:url var="loginUrl" value="/login"/>

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
                            <li class="active" id="login"><a href="${loginUrl}">Login</a></li>
                        </ul>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <ul class="nav navbar-nav">
                            <li><a id="protected" href="${protectedUrl}">Protected</a></li>
                            <li><a id="unprotected" href="${unprotectedUrl}">Unprotected</a></li>
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
        <li class="active">Login</li>
    </ol>

    <div class="row">
        <div class="col-md-12">

            <c:if test="${logout}">
                <div class="alert alert-success alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                    You have successfully logged out.
                </div>
            </c:if>

            <c:if test="${errors}">
                <div class="alert alert-danger alert-dismissable">
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                </div>
            </c:if>

            <form role="form" class="form-horizontal" action="${loginPostUrl}" method="POST">
                <div class="form-group">
                    <label for="username" class="col-xs-4 col-sm-2 control-label">Login</label>

                    <div class="col-xs-8 col-sm-4">
                        <input type="text" id="username" name="j_username" class="form-control" pattern="\w+"
                               title="Fill in your username" autocomplete="off">
                    </div>
                </div>
                <div class="form-group">
                    <label for="password" class="col-xs-4 col-sm-2 control-label">Password</label>

                    <div class="col-xs-8 col-sm-4">
                        <input id="password" name='j_password' type="password" class="form-control"
                               title="Fill in your password" autocomplete="off">
                    </div>
                </div>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <div class="col-xs-offset-4 col-sm-offset-2 col-xs-10">
                        <button type="submit" id="login" class="btn btn-primary btn-large">Login</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>