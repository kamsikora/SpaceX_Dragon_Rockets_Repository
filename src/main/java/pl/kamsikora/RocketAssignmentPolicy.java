package pl.kamsikora;

public interface RocketAssignmentPolicy {
    boolean canAssign(Rocket rocket);

    void assign(Rocket rocket, Mission mission);

    void unassign(Rocket rocket);

    void repair(Rocket rocket);
}