package pl.dmcs.brozga.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import pl.dmcs.brozga.service.VisitService;

import java.util.Calendar;

@Configuration
@EnableScheduling
public class SchedulerConfig {
    private VisitService visitService;

    public SchedulerConfig(VisitService visitService) {
        this.visitService = visitService;
    }

    @Scheduled(cron = "0 0 23 28-31 * *")
    public void scheduleApprovedVisitEnd() {
        final Calendar calendar = Calendar.getInstance();
        if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DATE)) {
            visitService.approveVisitsAuto();
        }

    }

    @Scheduled(cron = "0 0/1 * * * *")
    //@Scheduled(cron = "*/10 * * * * *")
    public void scheduleApprovedE2M() {
        visitService.approveVisitsAuto();
    }
}
