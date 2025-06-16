CREATE TABLE `emp_log` (
   `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID, 主键',
   `operate_time` datetime DEFAULT NULL COMMENT '操作时间',
   `info` varchar(2000) DEFAULT NULL COMMENT '日志信息',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工日志表'