<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ff66b2; /* Set background color to a pink shade */
            text-align: center;
        }

        h2 {
            color: #ff0066; /* Pink color for the heading */
        }

        p {
            color: #ff0066; /* Pink color for paragraphs */
        }

        a {
            text-decoration: none;
            color: #ff0066; /* Pink color for links */
            border: 1px solid #ff0066; /* Pink color for border */
            padding: 5px 10px;
            border-radius: 5px;
            display: inline-block;
            margin-top: 10px;
            transition: background-color 0.3s;
        }

        a:hover {
            background-color: #ff0066; /* Change background color on hover */
            color: #fff; /* Change text color on hover */
        }
    </style>
    <title>Files List</title>
    <!-- Add any additional styles or scripts if needed -->
</head>
<body>
<div>
    <h2>Files List</h2>
    <%-- Use JSTL to iterate through the list of files --%>
    <c:forEach var="file" items="${FilesForJsp}">
        <p>File Name: ${file.originalName}</p>
        <p>Content Type: ${file.contentType}</p>
        <p>Size: ${file.size}</p>

        <!-- Provide a link to download the file -->
        <a href="/files?fileName=${file.storageName}" target="_blank">Download</a>

        <hr/>
    </c:forEach>
</div>
</body>
</html>
