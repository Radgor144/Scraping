package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuldogConnector {

    public static List<JobData> getBuldog(String location, String technology, String experience) throws IOException {
        String url = "https://bulldogjob.pl/companies/jobs/s/city," + location + "/skills," + technology + "/experienceLevel," + experience;
        Document doc = Jsoup.connect(url).get();
        Elements jobs = doc.select(".JobListItem_item__M79JI");
//        System.out.println(jobs);

        List<JobData> jobList = new ArrayList<>();

        for (
                Element job: jobs) {
            String name = job.select(".JobListItem_item__title__Ae2Pm > h3").text();
            String salary = job.select(".JobListItem_item__salary__Jd_AJ").text();
            String href = job.attr("href");
            JobData jobData = new JobData(name, salary, href);
            jobList.add(jobData);
        }

        return jobList;
    }
}
