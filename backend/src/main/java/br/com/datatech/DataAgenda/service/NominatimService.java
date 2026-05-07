package br.com.datatech.DataAgenda.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Service
public class NominatimService {

    private final WebClient webClient;

    public NominatimService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://nominatim.openstreetmap.org")
                .defaultHeader("User-Agent", "DataAgenda/1.0 (datatech@datatech.com)") // Nominatim requires a User-Agent
                .build();
    }

    // A simple representation of coordinates
    public static class Coordinates {
        private final double latitude;
        private final double longitude;

        public Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }

    public Coordinates getCoordinates(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address cannot be empty for geocoding.");
        }

        // Respect Nominatim's usage policy (max 1 request per second)
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Geocoding process was interrupted.");
        }

        String uri = UriComponentsBuilder.fromPath("/search")
                .queryParam("q", address)
                .queryParam("format", "json")
                .queryParam("limit", 1)
                .build()
                .toUriString();

        JsonNode[] results = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(JsonNode[].class)
                .block(Duration.ofSeconds(10)); // Block for a response, with a timeout

        if (results != null && results.length > 0) {
            JsonNode result = results[0];
            double lat = result.get("lat").asDouble();
            double lon = result.get("lon").asDouble();
            return new Coordinates(lat, lon);
        } else {
            // Sir, if an address is not found, we will not throw an error,
            // but return null. The calling service should handle this gracefully.
            System.err.println("Could not find coordinates for the given address: " + address);
            return null;
        }
    }
}
