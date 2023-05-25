package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.locationService;

import java.net.URI;

@RestController
@RequestMapping("/api")
public class locationController {

    @Autowired
    private locationService locationService;

    @ResponseBody
    @GetMapping("/getLocationslist")
    public ResponseEntity<JSONObject> getLocations() {
        JSONObject response = new JSONObject();
        response.put("locations", locationService.getLocations());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping("/addlocation")
    public ResponseEntity<String> addLocation(@RequestBody MultiValueMap<String,String> request) {
        locationService.addLocation(request.getFirst("location_name"), request.getFirst("location_city"), request.getFirst("location_category"));
        return new ResponseEntity<>("location added", HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/deletelocation")
    public ResponseEntity<String> delLocation(@RequestBody MultiValueMap<String,String> request) {
        locationService.delLocation(Integer.parseInt(request.getFirst("location_id")));
        return new ResponseEntity<>("location deleted", HttpStatus.OK);
    }


}
