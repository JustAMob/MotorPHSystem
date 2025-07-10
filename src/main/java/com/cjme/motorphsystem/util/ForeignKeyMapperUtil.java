/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cjme.motorphsystem.util;

import com.cjme.motorphsystem.dao.DepartmentDAO;
import com.cjme.motorphsystem.dao.EmploymentStatusDAO;
import com.cjme.motorphsystem.dao.PositionDAO;
import com.cjme.motorphsystem.dao.implementations.DepartmentDAOImpl;
import com.cjme.motorphsystem.dao.implementations.EmploymentStatusDAOImpl;
import com.cjme.motorphsystem.dao.implementations.PositionDAOImpl;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public class ForeignKeyMapperUtil {
    public static Map<String, Integer> departmentMap = new HashMap<>();
    public static Map<String, Integer> positionMap = new HashMap<>();
    public static Map<String, Integer> statusMap = new HashMap<>();

    public static void loadMappings() throws SQLException {
        DepartmentDAO deptDAO = new DepartmentDAOImpl();
        departmentMap = deptDAO.getDepartmentNameIdMap();

        PositionDAO posDAO = new PositionDAOImpl();
        positionMap = posDAO.getPositionNameIdMap();

        EmploymentStatusDAO statDAO = new EmploymentStatusDAOImpl();
        statusMap = statDAO.getEmploymentStatusNameIdMap();
    }
}
