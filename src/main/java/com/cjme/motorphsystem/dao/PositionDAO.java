/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Position;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JustAMob
 */
public interface PositionDAO {
      int addPosition(Position position);
    Position getPositionById(int id);
    List<Position> getAllPositions();
    void updatePosition(Position position);
    void deletePosition(int id);

    public Map<String, Integer> getPositionNameIdMap()throws SQLException;

  
}
