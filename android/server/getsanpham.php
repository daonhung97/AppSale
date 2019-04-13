<?php 
include "connect.php";
$page =$_GET['page'];
$type =$_GET['type'];
$idsp =1;
 // $_POST['idsanpham'];
$space = 25; // giới hạn số lg sản phẩm
$limit= ($page - 1) * $space; // xác định vị trí đầu tiên
$mangsanpham = array();
$query="SELECT * FROM sanpham WHERE idsanpham = $type LIMIT $limit,$space" ;
$data=mysqli_query($conn,$query);
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangsanpham,new sanpham(
		$row['id'],
		$row['tensanpham'],
		$row['giasanpham'],
		$row['hinhanhsanpham'],
		$row['motasanpham'],
		$row['idsanpham']
	));
	
}
echo json_encode($mangsanpham);

class Sanpham{
	function Sanpham($id,$tensanpham,$giasanpham,$hinhanhsanpham,$motasanpham,$idsanpham){
		$this->id =$id;
		$this->tensanpham=$tensanpham;
		$this->giasanpham=$giasanpham;
		$this->hinhanhsanpham=$hinhanhsanpham;
		$this->motasanpham=$motasanpham;
		$this->idsanpham=$idsanpham;

	}
 }
 ?>