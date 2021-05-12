package com.company.asquith;

public class Task implements Comparable<Task> {
    private String title;
    private String description;
    private int priority;


    public Task(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    /*@Override
    public int compareTo(Task o) {
        if (priority == o.priority) {
            return 0;
        } else if (priority < o.priority) {
            return 1;
        } else {
            return -1;
        }
    }*/

    @Override
    public int compareTo(Task o) {
        return Integer.compare(o.priority, priority);
    }

    /*@Override
    public int compareTo(Task o) {
        return title.compareTo(o.title);
    }*/

    @Override
    public String toString() {
        return "Tasks{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", priority=" + priority +
                '}';
    }
}
