<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>
<body>

    <div>
        <h2>Uploaded files</h2>

        <body>
              <c:forEach var = "val" items="${fileList}">
                 <!--file: <a href="/download/${val}">${val}</a><p>-->
                 file: <a href="/download/${val}">${val}</a><p>
              </c:forEach>
        </body>
    </div>

	<div>
	    <h2>Upload file</h2>                                                                                                                             </h1>

	    <form method="POST" action="/upload" enctype="multipart/form-data">
		    <input type="file" name="file" /><br />
		    <br /> <input type="submit" value="Submit" />
        </form>
    </div>
</body>
</html>