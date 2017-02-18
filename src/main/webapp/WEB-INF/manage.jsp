<%@ page import="static io.muic.ooc.webapp.Webapp.tracker" %>
<html>
<body>
<form action="/manage" method="post">

    <tr>
        <td>Request change Username:<br/>

            <input type="text" name="username"/></td>
        <td>username:<br/>
            <input type="password" name="oldPassword"></td>
        <td>old Password:<br/>
            <input type="password" name="newPassword">
            </td>New Password

        <input type = "hidden" name ="user" value="${a.username}">




    </tr>
    <p> ${error}  </p>

    <input type="submit" value="Submit">








</form>

</body>
</html>