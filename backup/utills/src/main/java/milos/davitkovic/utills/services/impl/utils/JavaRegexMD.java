package milos.davitkovic.utills.services.impl.utils;

import org.springframework.stereotype.Component;

@Component
public class JavaRegexMD {

    public static final String REGEX_GET_ONLY_NUMBERS = "[^0-9]";
    public static final String REGEX_REMOVE_ALL_WHITESPACES_AND_NONVISIBLE_CHARACTERS = "\\s+";
    public static final String REGEX_REMOVE_ALL_WHITESPACES_NONVISIBLE_CHARACTERS_UNBREAKABLE_SPACES = "[\\\\s|\\\\u00A0]+";
    public static final String REGEX_GET_INTEGERS = "\\d+";

    /**
     * \w = Anything that is a word character
     *
     * \W = Anything that isn't a word character (including punctuation etc)
     *
     * \s = Anything that is a space character (including space, tab characters etc)
     *
     * \S = Anything that isn't a space character (including both letters and numbers, as well as punctuation etc)
     */
}
