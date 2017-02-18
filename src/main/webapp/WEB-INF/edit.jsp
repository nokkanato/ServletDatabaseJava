<%@ page import="static io.muic.ooc.webapp.Webapp.editTracker" %>
<html>


<body>

<h1> Edit  </h1>

<tr>
    <td>

        <form action="/edit" method="post">

            Username:<br/>
            <input type="text" name="hola" placeholder="username"/>

            <%--<% editTracker.setUsername("username");%>--%>


            <br/>



            <input type="submit" value="submit">
        </form>






    </td>
</tr>



</body>

</html>