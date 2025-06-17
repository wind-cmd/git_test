package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**报表查询，职位员工人数
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzData {
    private List clazzList;
    private List dataList;
}