package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        PersonContainsKeywordsPredicate firstP = new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        PersonContainsKeywordsPredicate secondP = new PersonContainsKeywordsPredicate(secondPredicateKeywordList);

        // sam
        // e object -> returns true
        assertTrue(firstP.equals(firstP));

        // same values -> returns true
        PersonContainsKeywordsPredicate firstPCopy = new PersonContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstP.equals(firstPCopy));

        // different types -> returns false
        assertFalse(firstP.equals(1));

        // null -> returns false
        assertFalse(firstP.equals(null));

        // different person -> returns false
        assertFalse(firstP.equals(secondP));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(
                Collections.singletonList("Alice"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        assertTrue(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    }

    //To be fixed in v1.3
    //
    //    @Test
    //    public void test_nameDoesNotContainKeywords_returnsFalse() {
    //        // Zero keywords
    //        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(Collections.emptyList());
    //        assertFalse(predicate.test(new PersonBuilder().withName("Alice").build()));
    //
    //        // Non-matching keyword
    //        predicate = new PersonContainsKeywordsPredicate(Arrays.asList("Carol"));
    //        assertFalse(predicate.test(new PersonBuilder().withName("Alice Bob").build()));
    //
    //        // Keywords match phone, email and address, but does not match name
    //        predicate = new PersonContainsKeywordsPredicate(Arrays.
    //                                                          asList("12345", "alice@email.com", "Main", "Street"));
    //        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("12345")
    //                .withEmail("alice@email.com").withAddress("Main Street").build()));
    //    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        PersonContainsKeywordsPredicate predicate = new PersonContainsKeywordsPredicate(keywords);

        String expected = PersonContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
