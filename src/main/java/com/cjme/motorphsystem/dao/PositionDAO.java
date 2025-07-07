/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cjme.motorphsystem.dao;

import com.cjme.motorphsystem.model.Position;
import java.util.List;

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
}
