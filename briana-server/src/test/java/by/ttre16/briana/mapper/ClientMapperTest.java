package by.ttre16.briana.mapper;

import by.ttre16.briana.AbstractTest;
import by.ttre16.briana.assertion.RecursiveAssert;
import by.ttre16.briana.dto.ClientTo;
import by.ttre16.briana.dto.mapper.ClientMapper;
import by.ttre16.briana.entity.Client;
import by.ttre16.briana.entity.Organization;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATION1_ID;
import static by.ttre16.briana.data.OrganizationTestData.ORGANIZATIONS;

public class ClientMapperTest extends AbstractTest {
    @Autowired
    private ClientMapper clientMapper;

    @Test
    public void afterMapping() {
        Organization organization = ORGANIZATIONS.get(ORGANIZATION1_ID);
        ClientTo clientTo = new ClientTo();
        clientTo.setOrganizationId(organization.getId());

        Client mapped = clientMapper.toEntity(clientTo);

        RecursiveAssert.assertMatch(
                organization,
                mapped.getOrganization()
        );
    }
}
