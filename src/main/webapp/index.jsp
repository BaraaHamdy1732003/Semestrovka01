<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Assuming you have a method in your UsersRepository to retrieve the logged-in user
    User user = (User) session.getAttribute("user");
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #e91e63; /* Pink background */
            color: #fff;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            text-align: center;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            margin: 10px;
            text-decoration: none;
            color: #fff;
            background-color: #007bff;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #0056b3;
        }

        .photo-cards {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
            justify-content: center;
            margin-top: 20px;
        }

        .photo-card {
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s;
        }

        .photo-card:hover {
            transform: scale(1.05);
        }

        .photo-card img {
            width: 100%;
            border-bottom: 2px solid #fff;
        }

        h1 {
            margin-bottom: 20px;
        }

        <%-- Style for hiding and showing buttons based on login status --%>
        .hide-if-logged-in {
        <%-- Hide the element if the user is logged in --%>
            display: <%= (user != null) ? "none" : "inline-block" %>;
        }

        .show-if-logged-in {
        <%-- Show the element if the user is logged in --%>
            display: <%= (user != null) ? "inline-block" : "none" %>;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Wallpaper</h1>

    <%-- Check if the user is logged in to determine which buttons to display --%>
    <a href="/login" class="button hide-if-logged-in">Login</a>
    <a href="/profile" class="button show-if-logged-in">Profile</a>

    <a href="/about" class="button">About</a>
    <a href="/upload" class="button">Upload</a>
</div>

<div class="container photo-cards">
    <div class="photo-card">
        <img src="photos/pink1.jpg" alt="Photo 1">
    </div>

    <div class="photo-card">
        <img src="photos/pink2.jpg" alt="Photo 2">
    </div>

    <div class="photo-card">
        <img src="photos/pink3.jpg" alt="Photo 3">
    </div>

    <div class="photo-card">
        <img src="photos/pink4.jpg" alt="Photo 4">
    </div>

    <div class="photo-card">
        <img src="photos/pink5.jpg" alt="Photo 5">
    </div>

    <div class="photo-card">
        <img src="photos/pink6.jpg" alt="Photo 6">
    </div>

    <div class="photo-card">
        <img src="photos/pink7.jpg" alt="Photo 7">
    </div>

    <div class="photo-card">
        <img src="photos/pink8.jpg" alt="Photo 8">
    </div>

    <div class="photo-card">
        <img src="photos/pink9.jpg" alt="Photo 9">
    </div>

    <div class="photo-card">
        <img src="photos/pink10.jpg" alt="Photo 10">
    </div>
</div>

</body>
</html>
