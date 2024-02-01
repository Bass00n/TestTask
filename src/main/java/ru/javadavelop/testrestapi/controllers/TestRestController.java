package ru.javadavelop.testrestapi.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TestRestController {
    @GetMapping(path = "/string", params = "str")
    public ResponseEntity<Map<Character, Integer>> handleFrequencyOfCharactersInString(@RequestParam("str") String str) {
        var unsortedMap = new HashMap<Character, Integer>();

        for (char ch : str.toCharArray()) {
            unsortedMap.merge(ch, 1, Integer::sum);
        }

        var sortedMap = unsortedMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (k,v) -> {throw new AssertionError();},
                        LinkedHashMap::new
                ));

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(sortedMap);
    }
}
