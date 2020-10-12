package by.ttre16.briana.data;

import by.ttre16.briana.entity.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.ttre16.briana.data.OrganizationTestData.*;
import static by.ttre16.briana.entity.EmployeePermission.*;

public class PositionTestData {
    public static final Map<Integer, Position> POSITIONS = new HashMap<>();
    public static final Integer POSITION3_ID = 3;
    public static final Integer POSITION4_ID = 4;
    public static final Integer POSITION5_ID = 5;
    public static final Integer POSITION6_ID = 6;

    static {
        Position position3 = new Position();
        position3.setId(POSITION3_ID);
        position3.setName("accountant");
        position3.setDescription("simple-accountant");
        position3.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        position3.setPermissions(List.of(PRODUCT_READ.getName()));

        POSITIONS.put(POSITION3_ID, position3);

        Position position4 = new Position();
        position4.setId(POSITION4_ID);
        position4.setName("administrator");
        position4.setDescription("simple-administrator");
        position4.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        position4.setPermissions(
                List.of(
                        PRODUCT_READ.getName(),
                        PRODUCT_WRITE.getName()
                )
        );

        POSITIONS.put(POSITION4_ID, position4);

        Position position5 = new Position();
        position5.setId(POSITION5_ID);
        position5.setName("root");
        position5.setDescription("simple-root:org1");
        position5.setOrganization(ORGANIZATIONS.get(ORGANIZATION1_ID));
        position5.setPermissions(
                List.of(
                        PRODUCT_READ.getName(),
                        PRODUCT_WRITE.getName(),
                        EMPLOYEE_READ.getName(),
                        EMPLOYEE_WRITE.getName()
                )
        );

        POSITIONS.put(POSITION5_ID, position5);

        Position position6 = new Position();
        position6.setId(POSITION6_ID);
        position6.setName("root");
        position6.setDescription("simple-root:org2");
        position6.setOrganization(ORGANIZATIONS.get(ORGANIZATION2_ID));
        position6.setPermissions(
                List.of(
                        PRODUCT_READ.getName(),
                        PRODUCT_WRITE.getName(),
                        EMPLOYEE_READ.getName(),
                        EMPLOYEE_WRITE.getName()
                )
        );

        POSITIONS.put(POSITION6_ID, position6);
    }
}
