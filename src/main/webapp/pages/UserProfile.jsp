<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%--
The following import statement is required in order to use the OWASP Java Encoder.
Reference:
https://owasp.org/www-project-java-encoder/
https://mvnrepository.com/artifact/org.owasp.encoder/encoder
 --%>
<%@ page import="org.owasp.encoder.Encode" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Database Data</title>
</head>
<body>

<h1 style="background-color:rgb(255, 165, 0);">User Profile</h1>

<%-- Example:  The OWASP Java Encoder is used here to HTML encode untrusted data
before it is rendered in the response.  The context of the data is rendered in between
HTML tags so this method is appropriate.  Depending on the context, the required method
may be different.

Reference:  https://javadoc.io/doc/org.owasp.encoder/encoder/latest/index.html
--%>
<p>
    ${Encode.forHtml(user.username)}
</p>

<%-- Example: The OWASP Java Encoder is used here to encode untrusted input before
rendering the data in the response.  The context of the data is rendered within a
JavaScript String, so the forJavaScript() method is appropriate. Depending on the context,
the required method may be different.

Reference:  https://javadoc.io/doc/org.owasp.encoder/encoder/latest/index.html
--%>
<script type="text/javascript">
    let data = "${Encode.forJavaScript(user.username)}";
</script>

</body>
</html>
