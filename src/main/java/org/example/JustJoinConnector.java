package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JustJoinConnector {

    public static List<JobData> getJustJoin(String location, String technology, String experience) throws IOException {
        String url = "https://justjoin.it/" + location + "/" + technology + "/experience-level_" + experience;
//        System.out.println(url);
        Document doc = Jsoup.connect(url).get();
        Elements jobs = doc.select("div[data-index]");
        Elements links = doc.select("a.offer_list_offer_link");
//        System.out.println(jobs);

        List<JobData> jobList = new ArrayList<>();

        for (int i = 0; i < jobs.size(); i++) {
            Element job = jobs.get(i);
            Element link = links.get(i);

            String name = job.select("h3").text();
            String salary = job.select(".css-18ypp16").text();
            String href = link.attr("href");

            JobData jobData = new JobData(name, salary, "https://justjoin.it" + href);
            jobList.add(jobData);
        }

        return jobList;
    }
}
