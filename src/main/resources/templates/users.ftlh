<html lang="en">
<head>
    <link rel="stylesheet" href="/users.css", type="text/css">
</head>
<body>
<div class="container">
    <h2>USERS</h2>
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">ID</div>
            <div class="col col-2">LOGIN</div>
            <div class="col col-3">PASSWORD</div>
            <div class="col col-4">ROLE</div>
        </li>
        <#list users as users>
        <li class="table-row">
            <div class="col col-1">${users.id}</div>
            <div class="col col-2">${users.login}</div>
            <div class="col col-3">${users.password}</div>
            <div class="col col-4">${users.role}</div>
        </li>
        </#list>
    </ul>
</div>
</body>
</html>