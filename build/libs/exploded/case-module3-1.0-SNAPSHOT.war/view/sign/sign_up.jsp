<!DOCTYPE html>
<html>
<head>
    <title>Welcome To Registration Form</title>
</head>

<body>
<!-- The Modal (contains the Sign Up form) -->
<div class="modal">
    <span onclick="dieu_huong()" class="close" title="Close Modal">times;</span>
    <form class="modal-content" method="post" >
        <div class="container">
            <h1>Sign Up</h1>
            <p>Please fill in this form to create an account.</p>
            <hr>
            <label ><b>Name</b></label>
            <input type="text" placeholder="Enter Name" name="name" id="t1" required>

            <label ><b>Address</b></label>
            <input type="text" placeholder="Enter Address" name="address" id="t2" required>

            <label ><b>Phone Number</b></label>
            <input type="text" placeholder="Enter Phone Number" name="phoneNumber" id="t3" required>

            <label ><b>User Name</b></label>
            <input type="text" placeholder="Enter User Name" name="username" id="t4" required>


            <label ><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="psw" id="t5" required>

            <label ><b>Repeat Password</b></label>
            <input type="password" placeholder="Repeat Password" name="psw-repeat" id="t6" required>

            <label>
                <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
            </label>

            <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

            <div class="clearfix">
                <button type="button" onclick="dieu_huong()" class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn" onclick="registration()">Sign Up</button>
            </div>
        </div>
    </form>
</div>
<style>
    * {box-sizing: border-box}
    input[type=text], input[type=password] {
        width: 100%;
        padding: 15px;
        margin: 5px 0 22px 0;
        display: inline-block;
        border: none;
        background: #f1f1f1;
    }

    /* Add a background color when the inputs get focus */
    input[type=text]:focus, input[type=password]:focus {
        background-color: #ddd;
        outline: none;
    }

    /* Set a style for all buttons */
    button {
        background-color: #4CAF50;
        color: white;
        padding: 14px 20px;
        margin: 8px 0;
        border: none;
        cursor: pointer;
        width: 100%;
        opacity: 0.9;
    }

    button:hover {
        opacity:1;
    }
    .cancelbtn {
        padding: 14px 20px;
        background-color: #f44336;
    }
    .cancelbtn, .signupbtn {
        float: left;
        width: 50%;
    }

    .container {
        padding: 16px;
    }

    .modal {

        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: #474e5d;
        padding-top: 50px;
    }


    .modal-content {
        background-color: #fefefe;
        margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
        border: 1px solid #888;
        width: 80%; /* Could be more or less, depending on screen size */
    }
    hr {
        border: 1px solid #f1f1f1;
        margin-bottom: 25px;
    }


    .close {
        position: absolute;
        right: 35px;
        top: 15px;
        font-size: 40px;
        font-weight: bold;
        color: #f1f1f1;
    }

    .close:hover,
    .close:focus {
        color: #f44336;
        cursor: pointer;
    }


    .clearfix::after {
        content: "";
        clear: both;
        display: table;
    }

    @media screen and (max-width: 300px) {
        .cancelbtn, .signupbtn {
            width: 100%;
        }
    }
</style>
<script>
    function dieu_huong() {
        if(location.href=="http://localhost:8080/welcome?action1=shopping&action2=sign-up")
        location.assign("/welcome?action1=shopping");
        else {
            location.assign("/welcome?action1=sell");
        }
    }

</script>
</body>
</html>
