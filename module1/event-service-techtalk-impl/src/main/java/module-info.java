import com.mp.d4mentoring.module1.eventservice.api.service.EventService;
import com.mp.d4mentoring.module1.eventservice.techtalkimpl.service.TechTalkEventService;

module techTalkEventService {
    requires eventServiceApi;
    exports com.mp.d4mentoring.module1.eventservice.techtalkimpl.service;
    provides EventService with TechTalkEventService;
}