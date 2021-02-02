package com.sda.iteventapp.register;

import com.sda.iteventapp.form.util.ValidatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidatorUtilTest {
    ValidatorUtil validatorUtil;

    @BeforeEach
    void setUp() {
        validatorUtil = new ValidatorUtil();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test/email.csv", numLinesToSkip = 1)
    void isCorrectEmail(String input, Boolean expected) {
        boolean result = validatorUtil.isCorrectEmail(input);
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test/password.csv", numLinesToSkip = 1)
    void isCorrectPassword(String password, Boolean expected) {
        boolean result = validatorUtil.isCorrectPassword(password, 8, 30);
        assertEquals(result, expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test/username.csv", numLinesToSkip = 1)
    void isUsernameCorrect(String username, Boolean expected) {
        boolean result = validatorUtil.isUsernameCorrect(username);
        assertEquals(result, expected);

    }
}