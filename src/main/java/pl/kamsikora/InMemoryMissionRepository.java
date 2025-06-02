package pl.kamsikora;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemoryMissionRepository implements MissionRepository {
    private final Map<String, Mission> missions = new HashMap<>();

    @Override
    public void save(Mission mission) {
        missions.put(mission.getName(), mission);
    }

    @Override
    public Mission findByName(String name) {
        return missions.get(name);
    }

    @Override
    public Set<Mission> findAll() {
        return new HashSet<>(missions.values());
    }
}