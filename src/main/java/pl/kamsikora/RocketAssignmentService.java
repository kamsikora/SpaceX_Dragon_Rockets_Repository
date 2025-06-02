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
        assignmentPolicy.assign(rocket, mission);
    }

    public void unassignRocket(Rocket rocket) {
        assignmentPolicy.unassign(rocket);
    }
}