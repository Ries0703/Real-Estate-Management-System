DROP TABLE IF EXISTS `assignmentbuilding`;
CREATE TABLE `assignmentbuilding` (
                                      `id` bigint NOT NULL AUTO_INCREMENT,
                                      `staffid` bigint NOT NULL,
                                      `buildingid` bigint NOT NULL,
                                      `createddate` datetime DEFAULT NULL,
                                      `modifieddate` datetime DEFAULT NULL,
                                      `createdby` varchar(255) DEFAULT NULL,
                                      `modifiedby` varchar(255) DEFAULT NULL
) ENGINE = InnoDB;




DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) NOT NULL,
                            `street` varchar(255) DEFAULT NULL,
                            `ward` varchar(255) DEFAULT NULL,
                            `district` varchar(255) DEFAULT NULL,
                            `structure` varchar(255) DEFAULT NULL,
                            `numberofbasement` int DEFAULT NULL,
                            `floorarea` int DEFAULT NULL,
                            `direction` varchar(255) DEFAULT NULL,
                            `level` varchar(255) DEFAULT NULL,
                            `rentprice` int DEFAULT NULL,
                            `rentpricedescription` text,
                            `servicefee` varchar(255) DEFAULT NULL,
                            `carfee` varchar(255) DEFAULT NULL,
                            `motofee` varchar(255) DEFAULT NULL,
                            `overtimefee` varchar(255) DEFAULT NULL,
                            `waterfee` varchar(255) DEFAULT NULL,
                            `electricityfee` varchar(255) DEFAULT NULL,
                            `deposit` varchar(255) DEFAULT NULL,
                            `payment` varchar(255) DEFAULT NULL,
                            `renttime` varchar(255) DEFAULT NULL,
                            `decorationtime` varchar(255) DEFAULT NULL,
                            `brokeragefee` decimal(13, 2) DEFAULT NULL,
                            `type` varchar(255) DEFAULT NULL,
                            `note` varchar(255) DEFAULT NULL,
                            `linkofbuilding` varchar(255) DEFAULT NULL,
                            `map` varchar(255) DEFAULT NULL,
                            `avatar` varchar(255) DEFAULT NULL,
                            `createddate` datetime DEFAULT NULL,
                            `modifieddate` datetime DEFAULT NULL,
                            `createdby` varchar(255) DEFAULT NULL,
                            `modifiedby` varchar(255) DEFAULT NULL,
                            `managername` varchar(255) DEFAULT NULL,
                            `managerphone` varchar(255) DEFAULT NULL
) ENGINE = InnoDB;


DROP TABLE IF EXISTS `rentarea`;
CREATE TABLE `rentarea` (
                            `id` bigint NOT NULL AUTO_INCREMENT,
                            `value` int DEFAULT NULL,
                            `buildingid` bigint DEFAULT NULL,
                            `createddate` datetime DEFAULT NULL,
                            `modifieddate` datetime DEFAULT NULL,
                            `createdby` varchar(255) DEFAULT NULL,
                            `modifiedby` varchar(255) DEFAULT NULL
) ENGINE = InnoDB;


DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) NOT NULL,
                        `code` varchar(255) NOT NULL,
                        `createddate` datetime DEFAULT NULL,
                        `modifieddate` datetime DEFAULT NULL,
                        `createdby` varchar(255) DEFAULT NULL,
                        `modifiedby` varchar(255) DEFAULT NULL
) ENGINE = InnoDB;




DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `username` varchar(255) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `fullname` varchar(255) DEFAULT NULL,
                        `phone` varchar(255) DEFAULT NULL,
                        `email` varchar(255) DEFAULT NULL,
                        `status` int NOT NULL,
                        `createddate` datetime DEFAULT NULL,
                        `modifieddate` datetime DEFAULT NULL,
                        `createdby` varchar(255) DEFAULT NULL,
                        `modifiedby` varchar(255) DEFAULT NULL
) ENGINE = InnoDB;



DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `role_id` bigint NOT NULL,
                             `user_id` bigint NOT NULL,
                             `createddate` datetime DEFAULT NULL,
                             `modifieddate` datetime DEFAULT NULL,
                             `createdby` varchar(255) DEFAULT NULL,
                             `modifiedby` varchar(255) DEFAULT NULL
) ENGINE = InnoDB;


