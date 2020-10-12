package by.ttre16.briana.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeePermission {
    ORGANIZATION_READ("organization:read"),
    ORGANIZATION_WRITE("organization:write"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    CLIENT_READ("student:read"),
    CLIENT_WRITE("student:write"),
    ORDER_READ("order:read"),
    ORDER_WRITE("order:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write"),
    PRODUCT_READ("product:read"),
    PRODUCT_WRITE("product:write");

    private final String name;
}