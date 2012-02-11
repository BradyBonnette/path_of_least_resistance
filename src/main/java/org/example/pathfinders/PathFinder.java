package org.example.pathfinders;

import org.example.Grid;
import org.example.ResistanceCoordinate;

import java.util.List;

public interface PathFinder {

    List<ResistanceCoordinate> findPath(Grid grid);

}
