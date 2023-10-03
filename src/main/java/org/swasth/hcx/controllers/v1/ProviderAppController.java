package org.swasth.hcx.controllers.v1;


import kong.unirest.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.swasth.hcx.exception.ClientException;
import org.swasth.hcx.service.PostgresService;
import org.swasth.hcx.utils.Constants;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@RestController()
@RequestMapping(Constants.VERSION_PREFIX)
public class ProviderAppController {

    @Value("${postgres.table.consultation-info}")
    private String consultationInfoTable;

    @Autowired
    private PostgresService postgres;

    @PostMapping("/consultation/add")
    public ResponseEntity<String> addConsultationInfo(@RequestBody Map<String, Object> requestBody) throws ClientException {
        String workflowId = (String) requestBody.getOrDefault("workflow_id", "");
        if (!requestBody.containsKey("workflow_id") && workflowId.toString().isEmpty()) {
            throw new ClientException("Work flow id cannot be empty");
        }
        List<String> supportingDocumentsUrls = (List<String>) requestBody.getOrDefault("supporting_documents_url", "");
        String insertQuery = String.format("INSERT INTO %s (workflow_id, treatment_type, " +
                        "service_type, symptoms, supporting_documents_url) VALUES ('%s', '%s', '%s', '%s', ARRAY[%s]::varchar[])",
                consultationInfoTable, workflowId,
                requestBody.getOrDefault("treatment_type", ""),
                requestBody.getOrDefault("service_type", ""),
                requestBody.getOrDefault("symptoms", ""),
                supportingDocumentsUrls);
        System.out.println("================== insert query ---------" + insertQuery);
        try {
            postgres.execute(insertQuery);
            return ResponseEntity.ok("Insertion successful"); // Return a 200 OK response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Insertion failed"); // Return a 500 Internal Server Error response
        }
    }

    @GetMapping("/consultation/workflow_id")
    public ResponseEntity<Object> getConsultationInfo(@RequestParam("workflow_id") UUID workflowId) {
        try {
            Map<String, Object> consultationInfo = getConsultationInfoByWorkflowId(workflowId.toString());
            if (consultationInfo != null) {
                return ResponseEntity.ok(consultationInfo);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Consultation info not found"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("error", "Unable to fetch the details" + e.getMessage()));
        }
    }

    public Map<String, Object> getConsultationInfoByWorkflowId(String workflowId) throws ClientException, SQLException {
        String searchQuery = String.format("SELECT * FROM %s WHERE workflow_id = '%s'", consultationInfoTable, workflowId);
        System.out.println("search query ----------------------" +  searchQuery);
        ResultSet resultSet = postgres.executeQuery(searchQuery);
        Map<String, Object> consultationInfo = new HashMap<>();
        if (resultSet.next()) {
            consultationInfo.put("treatment_type", resultSet.getString("treatment_type"));
            consultationInfo.put("service_type", resultSet.getString("service_type"));
            consultationInfo.put("symptoms", resultSet.getString("symptoms"));
            consultationInfo.put("supporting_documents_url", resultSet.getString("supporting_documents_url"));
            consultationInfo.put("workflow_id", workflowId);
        } else {
            throw new ClientException("The Record does not exit for workflow id  : " + workflowId);
        }
        return consultationInfo;
    }

}