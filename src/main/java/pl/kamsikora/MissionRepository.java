package pl.kamsikora;

import java.util.Set;

public interface MissionRepository {
    void save(Mission mission);

    Mission findByName(String name);

    Set<Mission> findAll();
}