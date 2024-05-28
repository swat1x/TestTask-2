package ru.swat1x.records.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.swat1x.records.entity.Record;

public interface RecordsRepository extends JpaRepository<Record, Long> {
}
