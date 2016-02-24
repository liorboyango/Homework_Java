package homework.Utils;

import edu.vt.middleware.password.CharacterCharacteristicsRule;
import edu.vt.middleware.password.DigitCharacterRule;
import edu.vt.middleware.password.LengthRule;
import edu.vt.middleware.password.LowercaseCharacterRule;
import edu.vt.middleware.password.NonAlphanumericCharacterRule;
import edu.vt.middleware.password.Password;
import edu.vt.middleware.password.PasswordData;
import edu.vt.middleware.password.PasswordValidator;
import edu.vt.middleware.password.Rule;
import edu.vt.middleware.password.RuleResult;
import edu.vt.middleware.password.UppercaseCharacterRule;
import edu.vt.middleware.password.WhitespaceRule;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.validator.EmailValidator;

public class InputValidation {

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        if (validator.isValid(email)) {
            System.out.println("Valid Email");
            return true;
        }
        System.out.println("Invalid Email");
        return false;
    }

    public static boolean isValidPassword(String password) {
        if (getPasswordStrength(password) > 0) {
            return true;
        }
        return false;
    }

    public static boolean isValidUsername(String username) {
        CharacterCharacteristicsRule usernameRule = new CharacterCharacteristicsRule();
        LengthRule usernameLengthRule = new LengthRule(4, 32);
        usernameRule.getRules().add(new LowercaseCharacterRule(1));
        usernameRule.getRules().add(new UppercaseCharacterRule(1));
        usernameRule.setNumberOfCharacteristics(1);

        List<Rule> usernameRuleList = new ArrayList<Rule>();
        usernameRuleList.add(usernameLengthRule);
        usernameRuleList.add(usernameRule);

        PasswordData usernameData = new PasswordData(new Password(username));
        PasswordValidator validator = new PasswordValidator(usernameRuleList);

        RuleResult result = validator.validate(usernameData);
        if (result.isValid()) {
            System.out.println("Valid username");
            return true;
        } else {
            System.out.println("Invalid username");
            return false;
        }

    }

    public static int getPasswordStrength(String password) {
        //set levels of rules
        CharacterCharacteristicsRule weakRule = new CharacterCharacteristicsRule();
        CharacterCharacteristicsRule fairRule = new CharacterCharacteristicsRule();
        CharacterCharacteristicsRule goodRule = new CharacterCharacteristicsRule();
        CharacterCharacteristicsRule strongRule = new CharacterCharacteristicsRule();

        //set length rules
        LengthRule weakLengthRule = new LengthRule(8, 32);
        LengthRule fairLengthRule = new LengthRule(9, 32);
        LengthRule goodLengthRule = new LengthRule(11, 32);
        LengthRule strongLengthRule = new LengthRule(16, 32);

        //set no-whitespace rule
        WhitespaceRule whitespaceRule = new WhitespaceRule();

        //set at least 1 DIGIT rule
        weakRule.getRules().add(new DigitCharacterRule(1));
        fairRule.getRules().add(new DigitCharacterRule(1));
        goodRule.getRules().add(new DigitCharacterRule(1));
        strongRule.getRules().add(new DigitCharacterRule(1));

        //set at least 1 LOWER-CASE rule
        weakRule.getRules().add(new LowercaseCharacterRule(1));
        fairRule.getRules().add(new LowercaseCharacterRule(1));
        goodRule.getRules().add(new LowercaseCharacterRule(1));
        strongRule.getRules().add(new LowercaseCharacterRule(1));

        //set at least 1 UPPER-CASE rule
        goodRule.getRules().add(new UppercaseCharacterRule(1));
        strongRule.getRules().add(new UppercaseCharacterRule(1));

        //set at least 1 NON-ALPHANUMERIC rule
        strongRule.getRules().add(new NonAlphanumericCharacterRule(1));

        //set amount of minimum rules
        weakRule.setNumberOfCharacteristics(2);
        fairRule.setNumberOfCharacteristics(2);
        goodRule.setNumberOfCharacteristics(3);
        strongRule.setNumberOfCharacteristics(4);

        //add rules to list
        List<Rule> weakRuleList = new ArrayList<Rule>();
        weakRuleList.add(weakLengthRule);
        weakRuleList.add(whitespaceRule);
        weakRuleList.add(weakRule);

        List<Rule> fairRuleList = new ArrayList<Rule>();
        fairRuleList.add(fairLengthRule);
        fairRuleList.add(whitespaceRule);
        fairRuleList.add(fairRule);

        List<Rule> goodRuleList = new ArrayList<Rule>();
        goodRuleList.add(goodLengthRule);
        goodRuleList.add(whitespaceRule);
        goodRuleList.add(goodRule);

        List<Rule> strongRuleList = new ArrayList<Rule>();
        strongRuleList.add(strongLengthRule);
        strongRuleList.add(whitespaceRule);
        strongRuleList.add(strongRule);

        //get the inputed password
        PasswordData passwordData = new PasswordData(new Password(password));

        //validate the STRONG rules
        PasswordValidator validator = new PasswordValidator(strongRuleList);
        RuleResult result = validator.validate(passwordData);
        if (result.isValid()) {
            System.out.println("Valid strong password");
            return 4;
        } else {
            System.out.println("Invalid strong password");
        }

        //validate the GOOD rules
        validator = new PasswordValidator(goodRuleList);

        result = validator.validate(passwordData);

        if (result.isValid()) {
            System.out.println("Valid good password");
            return 3;
        } else {
            System.out.println("Invalid good password");
        }

        //validate the FAIR rules
        validator = new PasswordValidator(fairRuleList);

        result = validator.validate(passwordData);

        if (result.isValid()) {
            System.out.println("Valid fair password");
            return 2;
        } else {
            System.out.println("Invalid fair password");
        }

        //validate the WEAK rules
        validator = new PasswordValidator(weakRuleList);

        result = validator.validate(passwordData);

        if (result.isValid()) {
            System.out.println("Valid weak password");
            return 1;
        } else {
            System.out.println("Invalid weak password");
            for (String msg : validator.getMessages(result)) {
                System.out.println(msg);
                return 0;
            }
        }
        return 0;
    }

}
