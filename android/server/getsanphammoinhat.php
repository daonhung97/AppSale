<?php 
include "connect.php";
$mangsanphammoinhat = array();
$query = "SELECT * FROM sanpham ORDER BY ID DESC LIMIT 12";
$data = mysqli_query($conn,$query);
while ($row = mysqli_fetch_assoc($data)) {
	array_push($mangsanphammoinhat,new Sanphammoinhat(
		$row['id'],
		$row['tensanpham'],
		$row['giasanpham'],
		$row['hinhanhsanpham'],
		$row['motasanpham'],
		$row['idsanpham']));
}
echo json_encode($mangsanphammoinhat);

class Sanphammoinhat{
	function Sanphammoinhat($id,$tensanpham,$giasanpham,$hinhanhsanpham,$motasanpham,$idsanpham){
		$this->id=$id;
		$this->tensanpham=$tensanpham;
		$this->giasanpham=$giasanpham;
		$this->hinhanhsanpham=$hinhanhsanpham;
		$this->motasanpham=$motasanpham;
		$this->idsanpham=$idsanpham;

	}
}

?>