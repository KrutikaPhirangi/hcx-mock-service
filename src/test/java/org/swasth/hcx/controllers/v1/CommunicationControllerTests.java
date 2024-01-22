package org.swasth.hcx.controllers.v1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.swasth.hcx.controllers.BaseSpec;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class CommunicationControllerTests extends BaseSpec {

    @Value("${hcx_application.api_version}")
    private String api_version;

//    @Test
//    public void check_communication_request_success_scenario() throws Exception {
//        postgresService.execute("DROP TABLE IF EXISTS mock_participant");
//        postgresService.execute("CREATE TABLE mock_participant (\n" +
//                "    parent_participant_code character varying,\n" +
//                "    child_participant_code character varying PRIMARY KEY,\n" +
//                "    primary_email character varying,\n" +
//                "    password character varying,\n" +
//                "    private_key character varying\n" +
//                ");");
//        postgresService.execute("INSERT INTO mock_participant(parent_participant_code,child_participant_code,primary_email,password,private_key)"+"VALUES ('hosp_hcx_955522@swasth-hcx-dev ','payr_hcxmoc_903588@swasth-hcx-dev','lagertha+mock_payor@yopmail.com','Opensaber@123','-----BEGIN PRIVATE KEY-----\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCK6oTARsBHWD1M 8KTeMDrzkMPklmLilUqxqUXgjKfACdAsZLZtMlxLgWviFRxRglAgC33gS5On6DIE poiK6DQn2gushScJl6bRlA4vGCmYA+cO2d8O0rpkkJxWGq0K5LGb7umBkSNO0hyG DgngHviH8pYkqn9D1X7FuG2t912X4qg/db5Fp3NuS8ZRWJ9tk7fgWzLveNizsPS3 m8bqxikJ/RJL9g0EHURtzJWc2liBYGTtqhO5oeM2kovmaOn7a//AhnTcOZn2/eOe WiOmPRxSjjFyugREK+bAGTSfVq83003CJQ+kHg+cfOqBbCT+0J0LwLQAeLiKrLPS 07HVns3TAgMBAAECggEAFfns32gx90zH1y9YvbJpQ7/6pY5+/Ukl7xOQM5USMrNl ZKiCzaVCbErWBjGrGwqft2S/WKehfSt4F5FAQk1izDfWp0fMpFwCmYmFHEEuegHg C1Lp/UksEJ2/WxskQKLA+vdhpyGOsY5UJzGlsU2PqXN1+j34x9fzaBR5ps8JK5kF rbwr8En+hsfShSn3nklMiEyBI7bPDbpaq2Qjpfay/XRzqRdkPhsd+n03ADKGd4ax lyAe/VFak6JdpTy+oxWjc2dr2mOWIX9sOCSVOL7sTVX3Wcxhg0FlNbwmbx6nF+UW OJxVO9UNod/OtzSzYsqObJ/z76itUMcBnDl7almtjQKBgQC3Doo8UlvECcQvkrny Nk49oOLaheZA8R2Cw+3oK3ufMvahLEemxCFlZjbkmoeeRySzyPCs7jEr8HK1GQGu tv8yS4mkx7lCR2B3jl0NJ7r7kcitZh4VFaMXJORwpMoawMeWvc/u7Xh+OZRMp7c2 bfc9114dynCDtwp+dLjG8+y+5wKBgQDCRTlxXu7Kg5AL7H07n5q1r9IfOqfL4inB LXNEciddvaOtFzomjr/6sbGdK+ICeFyk9E12esWnbziaI/E8oSogL4dX+t5hbc5T tvrPs/mILztR1xvUdTNLyT9SOr7dEehytvOLsuHlFcCli7H6nZdrRCVFgeYXWZhM KR5Uagl4NQKBgQCOTHY2+AqvtKvWE2gKmh5uF9/g4Q+hUg2PtkD9JrgdhB9mIKa+ Q152lWN8h4d/CWzFeSFmPG7q6ioxDvRY3ZY5gbDI8BzaIeQia/93l3fp0WS/Lk+a MkyqVBpkWiVlcJB2ZKz73Yu6C4Z1pDZu0ELOxtk5rUGTkjlNHez5c2qI5wKBgQC1 NbMWQBIHjt6vcKGEGyVJcj5SaPkZodWG3ulVIBH+S6VAEJlqW99RbayaSdOgsDri lwsmh/CUdJdcmEguYLqVmR/q/hOu16kMx3J+iYcp87ymYzsPW19EwuywvCFKvqiP UH4ugeZaNIclJAAZICScps0JX9iLUURRTk1/OgWpPQKBgAD6pmbZCYk0B8TClPvs FSV12HUdMo9f7jteQP4TkmJhi+IOrMmBWVrwH6JgPf/awJPhmGM+g375JswSeugd u7ovcvivsL5JKNMeM35SfrF3xZ3VxX7jcOt/gM1nK9vXgGMJivf5TQIaF0piuwzG b5tlHhnq33FF0Y8MYB2Y4d4B\n-----END PRIVATE KEY-----')");
//        String requestBody = getCommunicationRequestBody();
//        MvcResult mvcResult = mockMvc.perform(post("/"+api_version+"/communication/request").content(requestBody).contentType(MediaType.APPLICATION_JSON)).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        int status = response.getStatus();
//        assertEquals(202, status);
//    }

//    @Test
//    public void check_communication_request_exception_scenario() throws Exception {
//        String requestBody = getExceptionRequestBody();
//        MvcResult mvcResult = mockMvc.perform(post("/"+api_version+"/communication/request").content(requestBody).contentType(MediaType.APPLICATION_JSON)).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        int status = response.getStatus();
//        assertEquals(500, status);
//    }
//
//    @Test
//    public void check_communication_on_request_success_scenario() throws Exception {
//        String requestBody = getRequestBody();
//        MvcResult mvcResult = mockMvc.perform(post("/"+api_version+"/communication/on_request").content(requestBody).contentType(MediaType.APPLICATION_JSON)).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        int status = response.getStatus();
//        assertEquals(202, status);
//    }
//
//    @Test
//    public void check_communication_on_request_exception_scenario() throws Exception {
//        String requestBody = getExceptionRequestBody();
//        MvcResult mvcResult = mockMvc.perform(post("/"+api_version+"/communication/on_request").content(requestBody).contentType(MediaType.APPLICATION_JSON)).andReturn();
//        MockHttpServletResponse response = mvcResult.getResponse();
//        int status = response.getStatus();
//        assertEquals(500, status);
//    }
}
