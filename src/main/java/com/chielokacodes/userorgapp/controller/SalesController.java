package com.chielokacodes.userorgapp.controller;

import com.chielokacodes.userorgapp.dto.ErrorResponse;
import com.chielokacodes.userorgapp.dto.ResponseDTO;
import com.chielokacodes.userorgapp.exeption.UserNotVerifiedException;
import com.chielokacodes.userorgapp.services.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analytics")
@Tag(name = "Sales Analytics", description = "Endpoints for retrieving sales analytics data")
public class SalesController {

    @Autowired
    private SalesService salesService;

    @GetMapping("/summary")
    @Operation(
            summary = "Get Summary Data",
            description = "Retrieve a summary of total users, active users, new users, and total revenue."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved summary data", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Summary data not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not Authorized", content = @Content(schema = @Schema(implementation = UserNotVerifiedException.class))),
    })
    public ResponseEntity<?> getSummaryData() {
            return salesService.getSummary();


        }

    @GetMapping("/pie-chart-data")
    @Operation(
            summary = "Get Pie Chart Data",
            description = "Retrieve data for pie chart visualization, including sales by month and total sales in degrees."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved pie chart data", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Error fetching pie chart data", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not Authorized", content = @Content(schema = @Schema(implementation = UserNotVerifiedException.class))),

    })
    public ResponseEntity<?> getPieChartData() {

        return salesService.getPieChartData();

    }


    @GetMapping("/bar-chart-data")
    @Operation(
            summary = "Get Bar Chart Data",
            description = "Retrieve data for bar chart visualization, including sales by month."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved bar chart data", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Error fetching bar chart data", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not Authorized", content = @Content(schema = @Schema(implementation = UserNotVerifiedException.class))),

    })
    public ResponseEntity<?> getBarChartData() {

        return salesService.getBarChartData();

    }

    @GetMapping("/line-chart-data")
    @Operation(
            summary = "Get Line Chart Data",
            description = "Retrieve data for line chart visualization, including sales by month."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved line chart data", content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Error fetching line chart data", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "User not Authorized", content = @Content(schema = @Schema(implementation = UserNotVerifiedException.class))),

    })
    public ResponseEntity<?> getLineChartData() {

        return salesService.getLineChartData();

    }
}
