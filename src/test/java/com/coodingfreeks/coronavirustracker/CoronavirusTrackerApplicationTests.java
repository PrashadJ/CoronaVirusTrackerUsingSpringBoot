package com.coodingfreeks.coronavirustracker;

import com.coodingfreeks.services.CoronaVirusDataService;
import org.junit.jupiter.api.Test;

class CoronavirusTrackerApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void serviceTest() throws Exception{
        CoronaVirusDataService coronaVirusDataService = new CoronaVirusDataService();
		coronaVirusDataService.getchVirusData();
    }
}
