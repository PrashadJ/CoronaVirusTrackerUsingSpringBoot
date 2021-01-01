package com.coodingfreeks.Controller;

import com.coodingfreeks.beans.CoronaStatsBean;
import com.coodingfreeks.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DecimalFormat;

@Controller
public class ConrollerBase {

    @Autowired
    private CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) throws Exception {
        model.addAttribute("CoronaStatsList", coronaVirusDataService.getchVirusData());
        DecimalFormat myFormatter = new DecimalFormat("###,###.###");
        String totalCasesFormatted = myFormatter.format(coronaVirusDataService.getchVirusData().stream().mapToLong(CoronaStatsBean::getTotalCases).sum());
        model.addAttribute("totalCases", totalCasesFormatted);
        return "home";
    }

}
