import com.mp.d4mentoring.module1.eventservice.api.service.EventService;

module eventServiceClient {
    requires eventServiceApi;
    uses EventService;
    exports com.mp.d4mentoring.module1.eventservice.client;
}