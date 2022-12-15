<!DOCTYPE html>
<html>
<head>
  <title>University</title>
  <meta name="viewport" content="width=device-width,initial-scale=1">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
  <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
  <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
  <div class="container">
    <a class="navbar-brand" href="#">
      <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/95/University_hat_icon.svg/1200px-University_hat_icon.svg.png" alt="..." height="36">
    </a>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav ms-auto">
        <li class="nav-item">
          <a href="/home" class="nav-link active">Home</a>
        </li>
        <li class="nav-item">
          <a href="/home?command=getStudents" class="nav-link">View students</a>
        </li>
        <li class="nav-item">
          <a href="/home?command=getStudentForm" class="nav-link">Create new student</a>
        </li>
        <li class="nav-item">
          <a href="/home?command=getGroups" class="nav-link">View groups</a>
        </li>
        <li class="nav-item" >
          <a href="create_group.jsp" class="nav-link">Create new group</a>
        </li>
        <li class="nav-item" >
          <a href="specific.jsp" class="nav-link">Get students by specific group</a>
        </li>

      </ul>
    </div>
  </div>
</nav>
</body>
</html>