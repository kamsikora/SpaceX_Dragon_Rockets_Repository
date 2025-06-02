package pl.kamsikora;

import java.util.HashSet;
import java.util.Set;

class Mission {
    private final String name;
    private final Set<Rocket> rockets = new HashSet<>();
    private MissionStatus status = MissionStatus.SCHEDULED;

    Mission(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    Set<Rocket> getRockets() {
        return rockets;
    }

    MissionStatus getStatus() {
        return status;
    }

    void assignRocket(Rocket rocket, MissionStatus status) {
        rockets.add(rocket);
        this.status = status;
    }

    void unassignRocket(Rocket rocket) {
        rockets.remove(rocket);
        if (rockets.isEmpty()) {
            this.status = MissionStatus.ENDED;
        }
    }

    void pauseMission() {
        this.status = MissionStatus.PENDING;
    }
}