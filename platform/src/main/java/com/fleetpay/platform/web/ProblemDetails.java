package com.fleetpay.platform.web;

public record ProblemDetails(String type, String title, int status, String detail, String instance) {}
