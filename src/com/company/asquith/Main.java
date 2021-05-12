package com.company.asquith;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        int userOption = -1;
        List<Task> taskList = new ArrayList<>();
        Gson gson = new Gson();

        while (userOption != 0) {
            System.out.println("Please choose an option: ");
            System.out.println("(1) Add a task.");
            System.out.println("(2) Remove a task.");
            System.out.println("(3) Update a task.");
            System.out.println("(4) List tasks.");
            System.out.println("(5) Save tasks.");
            System.out.println("(6) Load tasks.");
            System.out.println("(0) Exit. ");
            userOption = handleExceptionInt("Invalid Option!"); //Passes only the error message

            switch (userOption) {
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    addTask(taskList); //List is passed into method and added to
                    break;
                case 2:
                    removeTask(taskList);
                    break;
                case 3:
                    updateTask(taskList);
                    break;
                case 4:
                    listOptions(taskList);
                    break;
                case 5:
                    saveTasks(taskList);
                    break;
                case 6:
                    try {
                        TaskList task = loadTasks(taskList);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    static void saveTasks(List<Task> taskList) throws IOException {
        Gson gson = new Gson();
        FileWriter writer = new FileWriter("src/com/company/asquith/data.json");
        gson.toJson(taskList, writer);
        writer.close();
    }

    static TaskList loadTasks(List<Task> taskList) throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("src/com/company/asquith/data.json");
        try {
            return gson.fromJson(reader, TaskList.class);
        }
        finally {
            reader.close();
        }
    }


    static void addTask(List<Task> taskList) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the new task's name.");
        String title = input.nextLine();
        System.out.println("Enter the new task's description.");
        String description = input.nextLine();
        int priority = handleExceptionInt("Enter the new task's priority (0-5)", "Invalid input!");

        taskList.add(new Task(title, description, priority));
    }

    static void removeTask(List<Task> taskList) {
        if (!taskList.isEmpty()) {
            int removeIndex = handleExceptionInt("Enter the index of the task to remove.", "Invalid input!");
            taskList.remove(removeIndex);
        } else {
            System.out.println("The task list is empty!\n");
        }
    }

    static void updateTask(List<Task> taskList) {
        Scanner input = new Scanner(System.in);

        if (!taskList.isEmpty()) {
            int setIndex = handleExceptionInt("Enter the index of the task to update", "Invalid input!");
            System.out.println("Enter the new title.");
            String title = input.nextLine();
            System.out.println("Enter the new description");
            String description = input.nextLine();
            int priority = handleExceptionInt("Enter the new priority", "Invalid input!");

            Task updatedTask = new Task(title, description, priority);
            taskList.set(setIndex, updatedTask);
        } else {
            System.out.println("The task list is empty!\n");
        }
    }

    static void listOptions(List<Task> taskList) {
        Scanner input = new Scanner(System.in);

        System.out.println("How do you want your tasks listed?");
        System.out.println("(1) List all tasks");
        System.out.println("(2) List all tasks of a certain priority");
        int listOption = input.nextInt();
        switch (listOption) {
            case 1:
                listTasks(taskList);
                break;
            case 2:
                listPriorityTask(taskList);
                break;
        }
    }

    static void listTasks(List<Task> taskList) {
        Collections.sort(taskList);
        if (taskList.size() > 0) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i) + ".\t Title: " + task.getTitle());
                System.out.println("\t Description: " + task.getDescription());
                System.out.println("\t Priority: " + task.getPriority() + "\n");
            }
        } else {
            System.out.println("The task list is empty!\n");
        }
    }

    static void listPriorityTask(List<Task> taskList) {
        Collections.sort(taskList);
        if (taskList.size() > 0) {
            int priority = handleExceptionInt("Please choose the priority of the tasks", "Invalid Input!");
            boolean taskFound = false;
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);

                if (task.getPriority() == priority) {
                    System.out.println((i) + ".\t Title: " + task.getTitle());
                    System.out.println("\t Description: " + task.getDescription());
                    System.out.println("\t Priority: " + task.getPriority() + "\n");
                    taskFound = true;
                }
            }
            if (!taskFound) {
                System.out.println("No tasks found with that priority!\n");
            }
        } else {
            System.out.println("The task list is empty!\n");
        }
    }

    static int handleExceptionInt(String message, String caughtException) {
        int userInput;
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println(message);
            try {
                userInput = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println(caughtException);
            }
        }
        return userInput;
    }

    static int handleExceptionInt(String caughtException) { //Overloaded handleExceptionInt(), one string is passed in
        int userInput;
        while (true) {
            Scanner input = new Scanner(System.in);
            try {
                userInput = input.nextInt();
                break;
            } catch (Exception e) {
                System.out.println(caughtException); //Only the error message is being repeated each time
            }
        }
        return userInput;
    }
}