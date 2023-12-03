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
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #ff0066; /* Pink color for the heading */
        }

        label {
            display: block;
            margin: 10px 0;
            color: #ff0066; /* Pink color for labels */
        }

        input[type="file"] {
            width: 100%;
            padding: 10px;
            border: 1px solid #ff0066; /* Pink color for borders */
            border-radius: 5px;
        }

        button {
            padding: 10px 20px;
            background-color: #ff0066; /* Pink color for the button */
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        a {
            display: block;
            margin-top: 10px;
            text-decoration: none;
            color: #ff0066; /* Pink color for the link */
        }
    </style>
</head>
<body>
<div class="container">
    <h2>File Upload</h2>
    <form action="/insert" method="post" enctype="multipart/form-data">
        <label for="file">Select File:</label>
        <input type="file" id="file" name="file" required>
        <br>
        <button type="submit">Upload</button>
    </form>

    <a href="/" style="color: #ff0066;">Home</a> <!-- Set link color to pink -->
</div>
</body>
</html>
