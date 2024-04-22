package org.example;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.example.BuldogConnector.getBuldog;
import static org.example.JustJoinConnector.getJustJoin;

public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        long startTime = System.nanoTime();
        List<JobData> jobList = new ArrayList<>();

        CompletableFuture<List<JobData>> watek1 = CompletableFuture.supplyAsync(() -> {
            try {
                jobList.addAll(getJustJoin("krakow", "java", "junior"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return jobList;
        });

        CompletableFuture<List<JobData>> watek2 = CompletableFuture.supplyAsync(() -> {
            try {
                jobList.addAll(getBuldog("krakow", "java", "medium"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return jobList;
        });

        watek1.get();

        int i=1;
        for (
                JobData jobData : jobList) {
            System.out.println(i + ": Nazwa: " + jobData.name());
            System.out.println("Wynagrodzenie: " + jobData.salary());
            System.out.println("link: " + jobData.link());
            System.out.println();
            i++;
        }

        long endTime = System.nanoTime();

        // Obliczanie czasu trwania w nanosekundach
        long duration = endTime - startTime;

        // Konwersja czasu na sekundy
        double seconds = (double) duration / 1_000_000_000.0;

        System.out.println("Czas trwania programu: " + seconds + " sekund");
    }
}