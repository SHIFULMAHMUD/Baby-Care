<?php
if($_SERVER['REQUEST_METHOD']=='POST') {
//Getting values
    $user_name = $_POST['name'];
    $user_phone = $_POST['cell'];
    $user_email = $_POST['email'];
    $user_gender = $_POST['gender'];
    $user_password = $_POST['password'];

    $user_password = password_hash($user_password, PASSWORD_DEFAULT);
    require_once 'connect.php';
    $sql="INSERT INTO user(name,cell,email,gender,password) VALUES ('$user_name','$user_phone','$user_email','$user_gender','$user_password')";
    if (mysqli_query($conn, $sql)){

        $result["success"]="1";
        $result["message"]="success";
        echo json_encode($result);
        mysqli_close($conn);
    }else{
        $result["success"]="0";
        $result["message"]="error";
        echo json_encode($result);
        mysqli_close($conn);
    }
}
?>