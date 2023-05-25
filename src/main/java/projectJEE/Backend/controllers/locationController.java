package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.locationService;

/**
 * This class is the controller for the location entity
 * It handles the requests from the frontend
 * It calls the service layer to execute the requests
 */
@RestController
@RequestMapping("/api")
public class locationController {
    /**
     * The service layer for the location entity
     */
    @Autowired
    private locationService locationService;

    /**
     * This method is used to get the list of locations
     * @return the list of locations
     */
    @ResponseBody
    @GetMapping("/getLocationslist")
    public ResponseEntity<JSONObject> getLocations() {
        JSONObject response = new JSONObject();
        response.put("locations", locationService.getLocations());
        return ResponseEntity.ok(response);
    }

    /**
     * This method is used to add a location
     * @param request the request from the frontend
     * @return a response entity
     */
    @ResponseBody
    @PostMapping("/addlocation")
    public ResponseEntity<String> addLocation(@RequestBody MultiValueMap<String,String> request) {
        locationService.addLocation(request.getFirst("location_name"), request.getFirst("location_city"), request.getFirst("location_category"));
        return new ResponseEntity<>("location added", HttpStatus.OK);
    }
    /**
     * This method is used to delete a location
     * @param request the request from the frontend
     * @return a response entity
     */
    @ResponseBody
    @PostMapping("/deletelocation")
    public ResponseEntity<String> delLocation(@RequestBody MultiValueMap<String,String> request) {
        locationService.delLocation(Integer.parseInt(request.getFirst("location_id")));
        return new ResponseEntity<>("location deleted", HttpStatus.OK);
    }


}
