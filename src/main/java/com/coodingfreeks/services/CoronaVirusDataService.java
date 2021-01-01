package com.coodingfreeks.services;

import com.coodingfreeks.beans.CoronaStatsBean;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * @author Prashad
 * Service class for getting the complete summary of data
 */

@Service
public class CoronaVirusDataService {

    private static final String VIRUS_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static final String DEFAULT_COUNTRY = "~";

    @PostConstruct
    public List<CoronaStatsBean> getchVirusData() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_DATA_URL)).build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        return processData(httpResponse.body());
    }

    public static List<CoronaStatsBean> processData(String file) {
        try {
            StringReader filereader = new StringReader(file);
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            int indexOfCountry = 1;
            int indexOfFirstDate = 4;
            List<CoronaStatsBean> coronaStatsTillDate = new ArrayList<>();
            csvReader.readNext();
            while ((nextRecord = csvReader.readNext()) != null) {
                String country = DEFAULT_COUNTRY;
                double avgCases;
                double maxCases;
                double totalCases;
                DoubleSummaryStatistics stats;
                int tempIndex = 0;
                List<Integer> listCasesPerDay = new ArrayList<>();
                for (String cell : nextRecord) {
                    if (tempIndex == indexOfCountry) {
                        country = cell;
                    } else if (tempIndex >= indexOfFirstDate) {
                        listCasesPerDay.add(Integer.parseInt(cell));
                    }
                    tempIndex++;
                }
                stats = listCasesPerDay.stream().mapToDouble(Integer::doubleValue).summaryStatistics();
                avgCases = stats.getAverage();
                maxCases = stats.getMax();
                totalCases = stats.getSum();
                coronaStatsTillDate.add(new CoronaStatsBean(country, avgCases, maxCases, totalCases));
                listCasesPerDay.clear();
            }

            return coronaStatsTillDate;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
