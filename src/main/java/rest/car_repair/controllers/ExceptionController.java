package rest.car_repair.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import rest.car_repair.exceptions.member.MemberExistException;
import rest.car_repair.exceptions.member.MemberNotFoundException;
import rest.car_repair.exceptions.repair.RepairExistException;
import rest.car_repair.exceptions.repair.RepairNotFoundException;
import rest.car_repair.exceptions.repair.RepairNotReferredToVehicleException;
import rest.car_repair.exceptions.vehicle.VehicleExistException;
import rest.car_repair.exceptions.vehicle.VehicleNotFoundException;
import rest.car_repair.exceptions.vehicle.VehicleNotReferredToUserException;
import rest.car_repair.responses.ErrorResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException (Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MemberNotFoundException.class, RepairNotFoundException.class, VehicleNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.parse(LocalDateTime.now().toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                HttpStatus.NOT_FOUND.toString(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MemberExistException.class, RepairExistException.class, VehicleExistException.class})
    public ResponseEntity<ErrorResponse> handleAlreadyExistException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.toString(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({RepairNotReferredToVehicleException.class, VehicleNotReferredToUserException.class})
    public ResponseEntity<ErrorResponse> handleNotRefferedToException(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.toString(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
