package pl.kamsikora;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RocketAssignmentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RocketAssignmentService.class);

    private final RocketAssignmentPolicy assignmentPolicy;

    public RocketAssignmentService(RocketAssignmentPolicy assignmentPolicy) {
        this.assignmentPolicy = assignmentPolicy;
    }

    public void assignRocketToMission(Rocket rocket, Mission mission) {
        if (rocket.getStatus() == RocketStatus.ON_GROUND) {
            mission.assignRocket(rocket, MissionStatus.IN_PROGRESS);
            assignmentPolicy.assign(rocket, mission);
        } else if (rocket.getStatus() == RocketStatus.IN_REPAIR) {
            mission.assignRocket(rocket, MissionStatus.PENDING);
            assignmentPolicy.assign(rocket, mission);
        } else {
            throw new IllegalStateException("Cannot assign rocket " + rocket.getName() + " to mission " + mission.getName());
        }
        LOGGER.info("Assigned rocket {} to mission {}", rocket.getName(), mission.getName());
    }

    public void unassignRocket(Rocket rocket) {
        if (rocket.getMission() != null) {
            String missionName = rocket.getMission().getName();
            rocket.getMission().unassignRocket(rocket);
            assignmentPolicy.unassign(rocket);

            LOGGER.info("Unassigned rocket {} from mission {}", rocket.getName(), missionName);
        } else {
            LOGGER.warn("Rocket {} is not assigned to any mission", rocket.getName());
        }
    }
}