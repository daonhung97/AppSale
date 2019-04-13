<?php 
include "connect.php";
$query = "SELECT * FROM loaisanpham";
$data = mysqli_query($conn,$query);
$mangloaisanpham = array();
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangloaisanpham,new Loaisanpham(
$row['id'],
$row['tenloaisanpham'],
$row['hinhanhsanpham']));
}
echo json_encode($mangloaisanpham);
	class Loaisanpham{
		function Loaisanpham($id,$tenloaisanpham,$hinhanhsanpham){
			$this->id=$id;
			$this->tenloaisanpham= $tenloaisanpham;
			$this->hinhanhsanpham=$hinhanhsanpham;


		}
	}
 ?>