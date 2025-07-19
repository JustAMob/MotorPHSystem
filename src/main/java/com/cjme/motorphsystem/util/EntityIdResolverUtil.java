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

/**
 *
 * @author JustAMob
 */
public class EntityIdResolverUtil {
    private static final DepartmentDAO departmentDAO = new DepartmentDAOImpl();
    private static final PositionDAO positionDAO = new PositionDAOImpl();
    private static final EmploymentStatusDAO statusDAO = new EmploymentStatusDAOImpl();

    public static int getDepartmentIdByName(String name) throws SQLException {
        return departmentDAO.getDepartmentIdByName(name);
    }

    public static int getPositionIdByName(String name) throws SQLException {
        return positionDAO.getPositionIdByName(name);
    }

    public static int getStatusIdByName(String name) throws SQLException {
        return statusDAO.getStatusIdByName(name);
    }
}
