package pl.kamsikora;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MissionManagerTest {

    @Test
    void assignRocketToMission_success() {
        MissionRepository missionRepository = mock(MissionRepository.class);
        RocketAssignmentService service = mock(RocketAssignmentService.class);
        MissionManager manager = new MissionManager(missionRepository, service);

        Rocket rocket = mock(Rocket.class);
        Mission mission = mock(Mission.class);

        when(missionRepository.findByName("Mars")).thenReturn(mission);

        manager.assignRocketToMission(rocket, "Mars");

        verify(service).assignRocketToMission(rocket, mission);
    }

    @Test
    void assignRocketToMission_missionNotFound() {
        MissionRepository missionRepository = mock(MissionRepository.class);
        RocketAssignmentService service = mock(RocketAssignmentService.class);
        MissionManager manager = new MissionManager(missionRepository, service);

        Rocket rocket = mock(Rocket.class);

        when(missionRepository.findByName("Mars")).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> manager.assignRocketToMission(rocket, "Mars"));
    }

    @Test
    void prepareMissionSummary_sortsAndLogsMissions() {
        MissionRepository missionRepository = mock(MissionRepository.class);
        RocketAssignmentService service = mock(RocketAssignmentService.class);
        MissionManager manager = new MissionManager(missionRepository, service);

        Mission mission1 = mock(Mission.class);
        Mission mission2 = mock(Mission.class);
        Mission mission3 = mock(Mission.class);
        Mission mission4 = mock(Mission.class);
        Mission mission5 = mock(Mission.class);
        Mission mission6 = mock(Mission.class);

        Rocket rocket1 = mock(Rocket.class);
        Rocket rocket2 = mock(Rocket.class);
        Rocket rocket3 = mock(Rocket.class);
        Rocket rocket4 = mock(Rocket.class);
        Rocket rocket5 = mock(Rocket.class);

        when(mission1.getName()).thenReturn("Mars");
        when(mission2.getName()).thenReturn("Luna1");
        when(mission3.getName()).thenReturn("Double Landing");
        when(mission4.getName()).thenReturn("Transit");
        when(mission5.getName()).thenReturn("Luna2");
        when(mission6.getName()).thenReturn("Vertical Landing");

        when(mission2.getRockets()).thenReturn(Set.of(rocket1, rocket2));
        when(mission4.getRockets()).thenReturn(Set.of(rocket3, rocket4, rocket5));

        when(mission1.getStatus()).thenReturn(MissionStatus.IN_PROGRESS);
        when(mission2.getStatus()).thenReturn(MissionStatus.IN_PROGRESS);
        when(mission3.getStatus()).thenReturn(MissionStatus.SCHEDULED);
        when(mission4.getStatus()).thenReturn(MissionStatus.IN_PROGRESS);
        when(mission5.getStatus()).thenReturn(MissionStatus.ENDED);
        when(mission6.getStatus()).thenReturn(MissionStatus.SCHEDULED);

        when(rocket1.getName()).thenReturn("Dragon 1");
        when(rocket1.getStatus()).thenReturn(RocketStatus.IN_SPACE);
        when(rocket2.getName()).thenReturn("Dragon 2");
        when(rocket2.getStatus()).thenReturn(RocketStatus.IN_SPACE);
        when(rocket3.getName()).thenReturn("Red Dragon");
        when(rocket3.getStatus()).thenReturn(RocketStatus.IN_SPACE);
        when(rocket4.getName()).thenReturn("Dragon XL");
        when(rocket4.getStatus()).thenReturn(RocketStatus.IN_SPACE);
        when(rocket5.getName()).thenReturn("Falcon Heavy");
        when(rocket5.getStatus()).thenReturn(RocketStatus.IN_SPACE);

        when(missionRepository.findAll()).thenReturn(Set.of(mission1, mission2, mission3, mission4, mission5, mission6));

        manager.prepareMissionSummary();

        verify(missionRepository).findAll();
        verify(mission2, atLeastOnce()).getRockets();
        verify(mission4, atLeastOnce()).getRockets();
    }
}