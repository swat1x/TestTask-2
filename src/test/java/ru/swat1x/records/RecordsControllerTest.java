package ru.swat1x.records;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.swat1x.records.controller.RecordsController;
import ru.swat1x.records.service.RecordsService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class RecordsControllerTest {

//  private static List<Long> requestsPerformance = new ArrayList<>();

  @Mock
  private RecordsService service;
  @InjectMocks
  private RecordsController controller;

  @BeforeEach
  @Transactional
  void testSetup() {
    service.removeAllRecords();
    for (int i = 1; i <= 100_000; i++) {
      service.putRecord(i, "Test value");
    }
  }

  @Test
  void testRecordsSelect() {
    List<Long> requestsPerformance = new ArrayList<>();
    for (int i = 1; i <= 1_000_000; i++) {
      long id = i % 100_000;
      var start = System.currentTimeMillis();
      var record = controller.getRecord(id);
      var performance = System.currentTimeMillis() - start;
      requestsPerformance.add(performance);
    }

    var totalTime = 0L;
    for (Long performanceUnit : requestsPerformance) {
      totalTime += performanceUnit;
    }
    var median = findMedian(requestsPerformance);

    System.out.printf(
            """
                    Requests: %s times
                    Total time: %s ms
                    Median time: %s ms
                    """,
            requestsPerformance.size(), totalTime, median);
  }

  private static double findMedian(List<Long> numbers) {
    if (numbers == null || numbers.isEmpty()) {
      throw new IllegalArgumentException("Список не должен быть пустым");
    }
    Collections.sort(numbers);
    int size = numbers.size();
    if (size % 2 == 1) {
      return numbers.get(size / 2);
    } else {
      long middle1 = numbers.get((size / 2) - 1);
      long middle2 = numbers.get(size / 2);
      return (middle1 + middle2) / 2.0;
    }
  }

}
