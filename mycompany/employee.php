<?php
include('connection.php');

if(isset($_POST['name']) && isset($_POST['position'])
&& isset($_POST['salary']) && isset($_POST['experience'])){


  $name = $_POST['name'];
  $position = $_POST['position'];
  $salary = $_POST['salary'];
  $experience = $_POST['experience'];

  $query = "INSERT INTO employee(name, position, salary, experience)
  VALUES ('$name', '$position','$salary','$experience')";

  $data = mysqli_query($conn, $query);

  $employee = array();

  if($data){

    $id = mysqli_insert_id($conn);
    $q = "SELECT * FROM employee WHERE id = $id";
    $r = mysqli_query($conn, $q);

    $row = mysqli_fetch_assoc($r);
    $employee['name'] = $row['name'];
    $employee['position'] = $row['position'];
    $employee['salary'] = $row['salary'];
    $employee['experience'] = $row['experience'];

    echo json_encode($employee);

  }else{

    echo "Error!";

  }

exit;

}



 ?>
