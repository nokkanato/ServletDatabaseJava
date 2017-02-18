<%@ page import="static io.muic.ooc.webapp.Webapp.tracker" %>
<%@ page import="io.muic.ooc.webapp.service.MySQLService" %>
<%@ page import="io.muic.ooc.webapp.service.SecurityService" %>
<%@ page import="static io.muic.ooc.webapp.Webapp.editTracker" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<html>
<body>



<c:set var="tracker" value="${trackerUsername}" >   </c:set>

<sql:setDataSource
        var="myDS"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://localhost:3306/database_name"
        user="root" password="1234"
/>

<sql:query var="listUsers"   dataSource="${myDS}">
    SELECT * FROM test;
</sql:query>

<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of users</h2></caption>
        <tr>
            <th>ID</th>
            <th>username</th>
            <th>password</th>
        </tr>

        <c:forEach var="a" items="${listUsers.rows}">
            <tr>
                <td><c:out value="${a.id}" /></td>
                <td><c:out value="${a.username}" /></td>
                <td><c:out value="${a.password}" /></td>
                <td>
                    <c:if test = "${a.username!=  tracker}">

                    <form action="/manage" method="get">
                        <a href="/delete?userr=${a.username}" >delete</a>
                        <input type = "hidden" name ="user" value="${a.username}">
                        <%--<input type="submit" value="edit">--%>

                        </c:if>
                    </form>
                </td>
                <td>

                    <form action="/edit" method="get">
                        <a href="/edit?iddd=${a.id}&yim=${a.username}" >edit</a>
                        <input type = "hidden" name ="idd" value="${a.id}">

                    </form>


                </td>
            </tr>
        </c:forEach>


    </table>

</div>
<div align="center">


<form action="/login" method="get">
    <br><br>


    <input type="submit" value="Logout">
</form>

    <br><br>

    <form action="/register" method="get">
        <br><br>


        <input type="submit" value="add more user">
    </form>



</div>




</body>
</html>
