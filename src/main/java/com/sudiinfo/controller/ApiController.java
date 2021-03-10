package com.sudiinfo.controller;

import com.sudiinfo.domain.api.DTOReportSector;
import com.sudiinfo.domain.databaseclasses.city.JudicialSector;
import com.sudiinfo.service.ReportHandlerSector;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api")
@RestController
public class ApiController {

    private ReportHandlerSector reportHandlerSector;

    public ApiController(ReportHandlerSector reportHandlerSector) {
        this.reportHandlerSector = reportHandlerSector;
    }

    @GetMapping("report")
    public DTOReportSector getReportSector(@RequestParam(name = "id",required = false) JudicialSector judicialSector){

        DTOReportSector dtoReportSector=new DTOReportSector(judicialSector);
        dtoReportSector.setMapResultRange(reportHandlerSector.handleReportSector(judicialSector));
        return dtoReportSector;
    }
}
