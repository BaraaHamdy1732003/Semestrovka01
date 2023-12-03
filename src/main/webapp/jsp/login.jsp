<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #ff66b2; /* Set background color to a pink shade */
        }

        .container {
            max-width: 400px;
            margin: 20px auto;
            text-align: center;
        }

        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 15px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            font-size: 14px;
            margin-bottom: 5px;
            color: #ff0066; /* Pink color for labels */
        }

        .form-group input {
            width: 100%;
            padding: 10px;
            font-size: 14px;
            border: 1px solid #ff0066; /* Pink color for borders */
            border-radius: 8px;
            box-sizing: border-box;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            text-decoration: none;
            color: #fff;
            background-color: #ff0066; /* Pink color for the button */
            border-radius: 8px;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #cc0052; /* Darker pink on hover */
        }

        .register-link {
            display: block;
            margin-top: 10px;
            font-size: 12px;
            color: #ff0066; /* Pink color for the link */
        }

        .register-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="form-container">
        <form action="/login" method="post">
            <div class="form-group">
                <label for="uname">Username:</label>
                <input type="text" id="uname" name="uname" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <a href="/"><button type="submit" class="button">Login</button></a>
        </form>
        <a href="/register" class="register-link">Don't have an account? Register here.</a>
    </div>
</div>
</body>
</html>
