INSERT INTO `assignmentbuilding` (`id`, `staffid`, `buildingid`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES (1, 2, 1, NULL, NULL, NULL, NULL),
       (2, 2, 3, NULL, NULL, NULL, NULL),
       (3, 3, 1, NULL, NULL, NULL, NULL),
       (4, 3, 4, NULL, NULL, NULL, NULL),
       (8, 2, 2, '2024-05-07 15:46:52', '2024-05-07 15:46:52', 'nguyenvana', 'nguyenvana'),
       (9, 3, 2, '2024-05-07 15:46:52', '2024-05-07 15:46:52', 'nguyenvana', 'nguyenvana'),
       (138, 3, 45, NULL, NULL, NULL, NULL),
       (139, 2, 45, NULL, NULL, NULL, NULL);

INSERT INTO `building` (`id`, `name`, `street`, `ward`, `district`, `structure`, `numberofbasement`, `floorarea`, `direction`, `level`, `rentprice`, `rentpricedescription`, `servicefee`, `carfee`, `motofee`, `overtimefee`, `waterfee`, `electricityfee`, `deposit`, `payment`, `renttime`, `decorationtime`, `brokeragefee`, `type`, `note`, `linkofbuilding`, `map`, `avatar`, `createddate`, `modifieddate`, `createdby`, `modifiedby`, `managername`, `managerphone`)
VALUES (1, 'Nam Giao Building Tower', '59 Phan Xích Long', 'Phường 2', 'QUAN_1', '', 2, 500, '', NULL, 15, '15 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, 'TANG_TRET, NGUYEN_CAN', '', NULL, NULL, '/building/purple.png', NULL, '2024-05-09 18:34:57', NULL, 'nguyenvana', 'Anh Nam-Chị Linh', '0915354727'),
       (2, 'ACM Tower', '96 Cao Thắng', 'Phường 4', 'QUAN_2', '', 2, 650, '', NULL, 18, '18 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, 'NGUYEN_CAN', '', NULL, NULL, '/building/blue.png', NULL, '2024-05-09 21:06:08', NULL, 'nguyenvana', 'Chú Thuận', '0173546263'),
       (3, 'Alpha 2 Building Tower', '153 Nguyễn Đình Chiểu', 'Phường 6', 'QUAN_1', '', 1, 200, '', NULL, 20, '20 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, 'NOI_THAT', '', NULL, NULL, NULL, NULL, '2024-05-09 21:06:52', NULL, 'nguyenvana', 'Cô Ly', '0555532578'),
       (4, 'IDD 1 Building', '111 Lý Chính Thắng', 'Phường 7', 'QUAN_4', '', 1, 200, '', NULL, 12, '12 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, 'NOI_THAT, TANG_TRET, NGUYEN_CAN', '', NULL, NULL, NULL, NULL, '2024-05-09 21:06:59', NULL, 'nguyenvana', 'Anh Long', '017345253'),
       (6, 'ICT Building', '69 Nguyễn Văn Cừ', 'Phường 8', 'QUAN_4', '', 10, 500, '', NULL, 13, '13 triệu/m2', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '', '', NULL, 'NOI_THAT', '', NULL, NULL, '/building/purple.png', NULL, '2024-05-22 21:17:19', NULL, 'nguyenvana', 'Anh Long', '017345253'),
       (45, 'HANHOME MAI CHI THO', '1235', '124', 'QUAN_5', '1345', 1345, 1345, '1345', '1345', 123, '1345', '1345', '13245', '12345', '1235', NULL, '1243', '2143', '1243', '2314', '1234', 2134.00, 'NOI_THAT, NGUYEN_CAN', '12532135143568', NULL, NULL, '/building/red.png', NULL, '2024-05-27 17:56:33', NULL, 'nguyenvana', '1234', '1243');

INSERT INTO `rentarea` (`id`, `value`, `buildingid`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES (659, 100, 1, '2024-05-09 18:34:57', '2024-05-09 18:34:57', 'nguyenvana', 'nguyenvana'),
       (660, 200, 1, '2024-05-09 18:34:57', '2024-05-09 18:34:57', 'nguyenvana', 'nguyenvana'),
       (880, 200, 2, '2024-05-09 21:06:08', '2024-05-09 21:06:08', 'nguyenvana', 'nguyenvana'),
       (881, 300, 2, '2024-05-09 21:06:08', '2024-05-09 21:06:08', 'nguyenvana', 'nguyenvana'),
       (882, 400, 2, '2024-05-09 21:06:08', '2024-05-09 21:06:08', 'nguyenvana', 'nguyenvana'),
       (883, 300, 3, '2024-05-09 21:06:52', '2024-05-09 21:06:52', 'nguyenvana', 'nguyenvana'),
       (884, 400, 3, '2024-05-09 21:06:52', '2024-05-09 21:06:52', 'nguyenvana', 'nguyenvana'),
       (885, 500, 3, '2024-05-09 21:06:52', '2024-05-09 21:06:52', 'nguyenvana', 'nguyenvana'),
       (886, 100, 4, '2024-05-09 21:06:59', '2024-05-09 21:06:59', 'nguyenvana', 'nguyenvana'),
       (887, 400, 4, '2024-05-09 21:06:59', '2024-05-09 21:06:59', 'nguyenvana', 'nguyenvana'),
       (1579, 700, 6, '2024-05-22 21:17:19', '2024-05-22 21:17:19', 'nguyenvana', 'nguyenvana'),
       (1654, 4, 45, '2024-05-27 17:56:33', '2024-05-27 17:56:33', 'nguyenvana', 'nguyenvana'),
       (1655, 3, 45, '2024-05-27 17:56:33', '2024-05-27 17:56:33', 'nguyenvana', 'nguyenvana'),
       (1656, 1, 45, '2024-05-27 17:56:33', '2024-05-27 17:56:33', 'nguyenvana', 'nguyenvana'),
       (1657, 2, 45, '2024-05-27 17:56:33', '2024-05-27 17:56:33', 'nguyenvana', 'nguyenvana');

INSERT INTO `role` (`id`, `name`, `code`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES (1, 'Quản lý', 'MANAGER', NULL, NULL, NULL, NULL),
       (2, 'Nhân viên', 'STAFF', NULL, NULL, NULL, NULL);

INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `phone`, `email`, `status`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES (1, 'nguyenvana', '$2a$10$3USLjIXhCxkxXNhqDvphqeFfKb5jeW8K4ACWYND2ePvB6I27x6pGq', 'Nguyễn Văn A', NULL, NULL, 1, NULL, '2024-05-29 21:48:40', NULL, 'nguyenvana'),
       (2, 'nguyenvanb', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'Nguyễn Văn B', NULL, NULL, 1, NULL, NULL, NULL, NULL),
       (3, 'nguyenvanc', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'Nguyễn Văn C', NULL, NULL, 1, NULL, NULL, NULL, NULL),
       (4, 'nguyenvand', '$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG', 'Nguyễn Văn D', NULL, NULL, 1, NULL, NULL, NULL, NULL);

INSERT INTO `user_role` (`id`, `role_id`, `user_id`, `createddate`, `modifieddate`, `createdby`, `modifiedby`)
VALUES (1, 1, 1, NULL, NULL, NULL, NULL),
       (2, 2, 2, NULL, NULL, NULL, NULL),
       (3, 2, 3, NULL, NULL, NULL, NULL),
       (4, 2, 4, NULL, NULL, NULL, NULL),
       (8, 1, 5, NULL, NULL, NULL, NULL),
       (9, 2, 6, NULL, NULL, NULL, NULL),
       (10, 2, 7, NULL, NULL, NULL, NULL);
