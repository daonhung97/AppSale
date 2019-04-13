-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 12, 2019 lúc 10:06 AM
-- Phiên bản máy phục vụ: 10.1.37-MariaDB
-- Phiên bản PHP: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `thietbi`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitietdonhang`
--

CREATE TABLE `chitietdonhang` (
  `id` int(11) NOT NULL,
  `madonhang` int(11) NOT NULL,
  `masanpham` int(11) NOT NULL,
  `tensanpham` varchar(10000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `giasanpham` int(11) NOT NULL,
  `soluongsanpham` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chitietdonhang`
--

INSERT INTO `chitietdonhang` (`id`, `madonhang`, `masanpham`, `tensanpham`, `giasanpham`, `soluongsanpham`) VALUES
(1, 5, 11, 'Samsung Galaxy A8 Star Price', 12000000, 1),
(2, 5, 12, 'Inspiron 17 5000', 22450000, 3),
(3, 5, 11, 'Samsung Galaxy A8 Star Price', 12000000, 1),
(4, 5, 12, 'Inspiron 17 5000', 22450000, 3),
(5, 5, 11, 'Samsung Galaxy A8 Star Price', 12000000, 1),
(6, 6, 11, 'Samsung Galaxy A8 Star Price', 48000000, 4),
(7, 6, 10, 'Apple MacBook-review-lid', 77100000, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `id` int(11) NOT NULL,
  `tenkhachhang` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sodienthoai` int(11) NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`id`, `tenkhachhang`, `sodienthoai`, `email`) VALUES
(1, 'phat', 2147483647, 'abc@gmail.com'),
(2, 'nhung', 123456789, 'daonhung@gmail.com'),
(3, 'hehv', 3195, 'nhsg@gmail.com'),
(4, 'hehv', 3195, 'nhsg@gmail.com'),
(5, 'hwhh', 96948, 'jah@gmail.com'),
(6, 'nhung', 16986, 'cntt14d@gmail.com');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `id` int(11) NOT NULL,
  `tenloaisanpham` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `hinhanhsanpham` varchar(500) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `loaisanpham`
--

INSERT INTO `loaisanpham` (`id`, `tenloaisanpham`, `hinhanhsanpham`) VALUES
(1, 'Điện Thoại', 'https://saigon24gio.files.wordpress.com/2015/07/smartphone-re-pin-trau.jpg'),
(2, 'Laptop', 'https://cdn.tgdd.vn/Files/2013/11/13/534283/ImageAttach/dell-inspiron-5537-core-i5-4200u-15-6-quot-ram-4gb-hdd-750gb-amd-radeon-hd-8670m-2gb-m5i55528618381383640684.jpg');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sanpham`
--

CREATE TABLE `sanpham` (
  `id` int(11) NOT NULL,
  `tensanpham` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `giasanpham` int(15) NOT NULL,
  `hinhanhsanpham` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `motasanpham` varchar(10000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idsanpham` int(3) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `sanpham`
--

INSERT INTO `sanpham` (`id`, `tensanpham`, `giasanpham`, `hinhanhsanpham`, `motasanpham`, `idsanpham`) VALUES
(1, 'Điện thoại iphone7 Plus 25600', 13990000, 'http://img.vinanet.vn/zoom/640/Uploaded/hienhoa/2016_07_21/tin/ip7_LGPV.jpg', ' High Resolution Dome DNR Camera:\r\n\r\n• 1/3 High Sensitivity CCD, độ phân giải 600TV Lines (colour), 700 TV Lines (B/W), Fixed Lens: 3.6 mm\r\n\r\n•   độ nhạy sáng : 0.1 lux color, 0.02 lux(BW), 0.00001 lux (sens-up)\r\n\r\n• Sens-up, 3D DNR, Digital WDR, AGC, HSBLC, AWB, Morror, Motion detection, Privacy, Dome Size:68 mm', 1),
(2, 'Điện thoại iphone 7 25600', 24500000, 'http://thanhtungiphone.com/wp-content/uploads/2018/07/iphone-x-256gb-moi.jpg', ' High Resolution Dome DNR Camera:\r\n\r\n• 1/3 High Sensitivity CCD, độ phân giải 600TV Lines (colour), 700 TV Lines (B/W), Fixed Lens: 3.6 mm\r\n\r\n•   độ nhạy sáng : 0.1 lux color, 0.02 lux(BW), 0.00001 lux (sens-up)\r\n\r\n• Sens-up, 3D DNR, Digital WDR, AGC, HSBLC, AWB, Morror, Motion detection, Privacy, Dome Size:68 mm', 1),
(3, 'Điện thoại Samsung J7', 7560000, 'https://cdn.tgdd.vn/Products/Images/42/103404/samsung-galaxy-j7-pro-hh-600x600.jpg', 'Màn hình: 4.7 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 1),
(4, 'Laptop Dell 3576', 10500000, 'https://cdn.tgdd.vn/Products/Images/44/166521/dell-inspiron-3576-p63f002n76f-450-600x600.png', 'Màn hình: 14.7 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 2),
(5, 'Laptop Acer ', 15700000, 'https://cdn.tgdd.vn/Products/Images/44/184747/acer-aspire-e5-576-34nd-nxgrysv004-600x600.jpg', 'Màn hình: 15 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 2),
(6, 'Điện thoại Sony Xperia XZ2', 20756000, 'https://cdn.tgdd.vn/Products/Images/42/157479/sony-xperia-xz2-premium-2-400x460.png', 'Màn hình: 4.7 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 1),
(7, 'Laptop Asus X507MA-BR069T', 20500000, 'https://img1.phongvu.vn/media/catalog/product/cache/23/image/800x/9df78eab33525d08d6e5fb8d27136e95/a/s/asus-x507-1.jpg', 'Màn hình: 14.7 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 2),
(8, 'HTC Desire 628 Dual SIM', 4500000, 'https://cf4.s3.souqcdn.com/item/2016/08/08/11/34/46/60/item_XL_11344660_15788573.jpg', 'Màn hình: 4.7 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 1),
(9, 'Laptop HP Envy 13 ah0025TU i5 ', 17540000, 'http://laptopno1.com/Upload/Img/Products/HP%20ENVY%2013%202019.png', 'Màn hình: 15 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 2),
(10, 'Apple MacBook-review-lid', 25700000, 'https://www.imore.com/sites/imore.com/files/styles/xlarge_wm_blw/public/field/image/2017/06/macbook-2017-review-hero.jpg?itok=aLjIcQmp', 'Màn hình: 17 in, IPS LCD, 750 x 1334 px\r\nCamera chính: 12.0 Mp\r\nCamera phụ: 7.0 Mp\r\nCPU: Apple A10 Fusion, Quad-core 2.34 GHz (2x Hurricane + 2x Zephyr)\r\nGPU: PowerVR Series7XT Plus (six-core graphics)\r\nBộ nhớ trong: 128GB, 2GB RAM\r\nMạng di động: 2G, 3G, 4G\r\nWiFi: 802.11 a/b/g/n/ac\r\nBluetooth: v4.2\r\nGPS: A-GPS, GLONASS\r\nSim Card: 1 Sim, Nano-Sim\r\nPin: 1960 mAh, Li-Ion, không thể tháo rời', 2),
(11, 'Samsung Galaxy A8 Star Price', 12000000, 'https://www.91-img.com/pictures/129255-v3-samsung-galaxy-a8-star-mobile-phone-large-1.jpg', '- Thiết kế ấn tượng với màn hình vô cực Infinity Display\r\n- Cấu hình mạnh mẽ, RAM 6GB, ROM 64GB\r\n- Khả năng chống nước đạt chuẩn IP68\r\n- Camera sau kép 12MP, camera trước 8MP\r\n- Sử dụng hệ điều hành Android 8.0 (Oreo)', 1),
(12, 'Inspiron 17 5000', 22450000, 'https://i.dell.com/sites/csimages/Videos_Images/en/9eb776ec-d2b3-450c-b340-e1b5f6f31eeb.jpg', '17-inch laptop designed to entertain the whole family, with a head-turning finish, an expansive display, and an array of optional features.', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitietdonhang`
--
ALTER TABLE `chitietdonhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT cho bảng `loaisanpham`
--
ALTER TABLE `loaisanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `sanpham`
--
ALTER TABLE `sanpham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
