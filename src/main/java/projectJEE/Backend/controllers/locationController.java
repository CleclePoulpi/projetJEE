package projectJEE.Backend.controllers;

import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import projectJEE.Backend.service.locationService;

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
        return ResponseEntity.ok("Location added");
    }

}
