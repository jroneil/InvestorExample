package com.oneil.investment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInfo;

@Tag("Entity")
public interface EntityTests {

    @BeforeEach
    default void beforeEachConsoleOutputer(TestInfo testInfo){
        System.out.println("Running Test - " + testInfo.getDisplayName());
    }

}
