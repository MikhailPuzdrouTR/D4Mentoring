import com.mp.d4mentoring.module1.eventservice.api.service.EventService;
import com.mp.d4mentoring.module1.eventservice.workshopimpl.service.WorkshopEventService;

module eventServiceWorkshopImpl {
    requires eventServiceApi;
    exports com.mp.d4mentoring.module1.eventservice.workshopimpl.service;
    provides EventService with WorkshopEventService;
}