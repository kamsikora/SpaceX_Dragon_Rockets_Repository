package pl.kamsikora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class MissionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(MissionManager.class);

    private final MissionRepository missionRepository;
    private final RocketAssignmentService rocketAssignmentService;

    public MissionManager(MissionRepository missionRepository, RocketAssignmentService rocketAssignmentService) {
        this.missionRepository = missionRepository;
        this.rocketAssignmentService = rocketAssignmentService;
    }

    public void assignRocketToMission(Rocket rocket, String missionName) {
        Mission mission = missionRepository.findByName(missionName);
        if (mission == null) {
            throw new IllegalStateException("Mission " + missionName + " does not exist.");
        }

        rocketAssignmentService.assignRocketToMission(rocket, mission);
    }

    public void prepareMissionSummary() {
        Set<Mission> missions = missionRepository.findAll();

        missions.stream().sorted((m1, m2) ->
                        Integer.compare(m2.getRockets().size(), m1.getRockets().size()))
                .forEach(this::logMissionSummary);
    }

    private void logMissionSummary(Mission mission) {
        LOGGER.info("• {} - {} - Dragons: {}", mission.getName(), mission.getStatus(), mission.getRockets().size());
        for (Rocket rocket : mission.getRockets()) {
            LOGGER.info("  o {} - Status: {}", rocket.getName(), rocket.getStatus());
        }
    }
}