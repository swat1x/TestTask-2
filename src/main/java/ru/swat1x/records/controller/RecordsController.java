package ru.swat1x.records.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.swat1x.records.entity.Record;
import ru.swat1x.records.service.RecordsService;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/records")
public class RecordsController {

  RecordsService service;

  @PostMapping
  public Record putRecord(@RequestParam long id,
                          @RequestParam String value) {
    return service.putRecord(id, value);
  }

  @GetMapping
  public Record getRecord(@RequestParam long id) {
    return service.getRecord(id);
  }

  @DeleteMapping
  public Record removeRecord(@RequestParam long id) {
    return service.removeRecord(id);
  }

}
