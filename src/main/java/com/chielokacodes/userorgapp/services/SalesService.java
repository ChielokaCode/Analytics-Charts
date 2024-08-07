package com.chielokacodes.userorgapp.services;

import org.springframework.http.ResponseEntity;

public interface SalesService {
    ResponseEntity<?> getSummary();
    ResponseEntity<?> getPieChartData();
    ResponseEntity<?> getBarChartData();
    ResponseEntity<?> getLineChartData();
}
