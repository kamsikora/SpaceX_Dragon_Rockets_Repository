package pl.kamsikora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RocketAssignmentServiceTest {

    private RocketAssignmentPolicy assignmentPolicy;
    private RocketAssignmentService service;
    private Rocket rocket;
    private Mission mission;

    @BeforeEach
    void setUp() {
        assignmentPolicy = mock(DefaultRocketAssignmentPolicy.class);
        service = new RocketAssignmentService(assignmentPolicy);
        rocket = mock(Rocket.class);
        mission = mock(Mission.class);
    }

    @Test
    void assignRocketToMission_onGround_success() {
        when(rocket.getStatus()).thenReturn(RocketStatus.ON_GROUND);
        when(rocket.getName()).thenReturn("Dragon");
        when(mission.getName()).thenReturn("Mars");

        service.assignRocketToMission(rocket, mission);

        verify(assignmentPolicy).assign(rocket, mission);
        verify(mission).assignRocket(rocket, MissionStatus.IN_PROGRESS);
    }

    @Test
    void assignRocketToMission_inRepair_success() {
        when(rocket.getStatus()).thenReturn(RocketStatus.IN_REPAIR);
        when(rocket.getName()).thenReturn("Dragon");
        when(mission.getName()).thenReturn("Mars");

        service.assignRocketToMission(rocket, mission);

        verify(assignmentPolicy).assign(rocket, mission);
        verify(mission).assignRocket(rocket, MissionStatus.PENDING);
    }

    @Test
    void assignRocketToMission_invalidStatus_throws() {
        when(rocket.getStatus()).thenReturn(RocketStatus.IN_SPACE);
        when(rocket.getName()).thenReturn("Dragon");
        when(mission.getName()).thenReturn("Mars");

        assertThrows(IllegalStateException.class, () -> service.assignRocketToMission(rocket, mission));
    }

    @Test
    void unassignRocket_withMission_success() {
        Mission assignedMission = mock(Mission.class);
        when(rocket.getMission()).thenReturn(assignedMission);
        when(rocket.getName()).thenReturn("Dragon");
        when(assignedMission.getName()).thenReturn("Mars");

        service.unassignRocket(rocket);

        verify(assignmentPolicy).unassign(rocket);
        verify(assignedMission).unassignRocket(rocket);
    }

    @Test
    void unassignRocket_withoutMission_warns() {
        when(rocket.getMission()).thenReturn(null);
        when(rocket.getName()).thenReturn("Dragon");

        service.unassignRocket(rocket);

        verify(assignmentPolicy, never()).unassign(rocket);
    }
}