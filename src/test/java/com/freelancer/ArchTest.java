package com.freelancer;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.freelancer");

        noClasses()
            .that()
            .resideInAnyPackage("com.freelancer.service..")
            .or()
            .resideInAnyPackage("com.freelancer.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.freelancer.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
