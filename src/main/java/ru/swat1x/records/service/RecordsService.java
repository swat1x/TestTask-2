package ru.swat1x.records.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.swat1x.records.entity.Record;
import ru.swat1x.records.repository.RecordsRepository;

import java.util.NoSuchElementException;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class RecordsService {

  RecordsRepository repository;

  public void removeAllRecords() {
    repository.deleteAll();
  }

  public Record putRecord(long id, String value) {
    return repository.save(new Record().setId(id).setValue(value));
  }

  public Record getRecord(long id) {
    return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Record " + id));
  }

  public Record removeRecord(long id) {
    var record = getRecord(id);
    repository.delete(getRecord(id));
    return record;
  }

}
