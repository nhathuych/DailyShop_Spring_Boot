<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Sending Email with Freemarker HTML Template Example</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
        }
        .custom-class {
        	    background-color: rgba(210, 0, 123, 0);
    			text-decoration: none;
			    display: inline-block;
			    width: 205px;
			    height: 26px;
			    text-align: center;
			    border-radius: 7px;
			    font-size: 17px;
			    line-height: 24px;
        }
        .custom-class:hover {
        	background-color: rgba(210, 0, 157, 0);
        }
    </style>
</head>
<body>

   <h4>Date-Time Expire: ${expireDate}</h4>
   <a class='custom-class' href='http://localhost:8080${href}?token=${token}'>Click here to reset password</a>
</body>
</html>
