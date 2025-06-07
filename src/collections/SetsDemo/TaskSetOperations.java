package collections.SetsDemo;

import java.util.*;

public class TaskSetOperations {
    public static void main(String[] args) {
        Set<Task> tasks = TaskData.getTask("all");
        sortAndPrint("All Tasks", tasks);

        Comparator<Task> sortByPriority = Comparator.comparing(Task::getPriority);
        Set<Task> annsTasks = TaskData.getTask("ann");
        sortAndPrint("Ann's Tasks", annsTasks, sortByPriority);

        Set<Task> bobsTasks = TaskData.getTask("bob");
        Set<Task> carolsTasks = TaskData.getTask("carol");
        List<Set<Task>> tasksetList = List.of(annsTasks, bobsTasks, carolsTasks);

//        Question 2: Which task have been assigned to team members
        Set<Task> assignedTasks = getUnion(tasksetList);
        sortAndPrint("Assigned Tasks", assignedTasks);

//        Question1; List of all the tasks
        Set<Task> totalTaskSet = getUnion(List.of(tasks, assignedTasks));
        sortAndPrint("Final Task Set", totalTaskSet);

        Set<Task> missingTasks = getDifference(totalTaskSet, tasks);
        sortAndPrint("Missing Tasks from Total List", missingTasks);

        Set<Task> unassignedTasks = getDifference(tasks, assignedTasks);
        sortAndPrint("Unassigned Tasks", unassignedTasks, sortByPriority);

//        Check if two membes are working on same tasks and get the list of same tasks two members are working on
        Set<Task> overlap = getUnion(List.of(
                getIntersect(annsTasks, bobsTasks),
                getIntersect(bobsTasks, carolsTasks),
                getIntersect(annsTasks, carolsTasks)
        ));
        sortAndPrint("Same Task assigned to multiple", overlap, sortByPriority);

        List<Task> overlapping = new ArrayList<>();
        for (Set<Task> set : tasksetList) {
            Set<Task> dupes = getIntersect(set, overlap);
            overlapping.addAll(dupes);
        }
        Comparator<Task> priorityNatural = sortByPriority.thenComparing(Comparator.naturalOrder());
        sortAndPrint("Overlapping", overlapping, priorityNatural);

    }

    private static void sortAndPrint(String header, Collection<Task> collection) {
        sortAndPrint(header, collection, null);
    }

    private static void sortAndPrint(String header, Collection<Task> collection, Comparator<Task> sorter) {
        String lineSeparator = "-".repeat(90);
        System.out.println(lineSeparator);
        System.out.println(header);
        System.out.println(lineSeparator);

        List<Task> taskList = new ArrayList<>(collection);
        taskList.sort(sorter);
        taskList.forEach(System.out::println);
    }

    private static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> union = new HashSet<>();
        for (Set<Task> taskSet : sets) {
            union.addAll(taskSet);
        }
        return union;
    }

    private static Set<Task> getIntersect(Set<Task> a, Set<Task> b) {
        Set<Task> intersect = new HashSet<>(a);
        intersect.retainAll(b);
        return intersect;
    }

    private static Set<Task> getDifference(Set<Task> a, Set<Task> b) {
        Set<Task> abDiff = new HashSet<>(a);
        abDiff.removeAll(b);
        return abDiff;
    }
}
