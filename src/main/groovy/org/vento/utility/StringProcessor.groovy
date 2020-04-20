package org.vento.utility

/**
 * Created with IntelliJ IDEA.
 * User: lfoppiano
 * Date: 29/06/12
 * Time: 23.54
 * To change this template use File | Settings | File Templates.
 */
public class StringProcessor {
    public static String removeInvalidUtf8Chars(String inString) {
        if (inString == null) return null;

        StringBuilder newString = new StringBuilder();
        char ch;

        for (int i = 0; i < inString.size(); i++){
            ch = inString[i];
            if ((ch < 0x00FD && ch > 0x001F) || ch == '\t' || ch == '\n' || ch == '\r') {
                newString.append(ch);
            }
        }
        return newString.toString();

    }

    public static String textProcessing(String text) {
        //Remove recipients
        text = text.replaceAll(/@\w+(:)?/, '_USER_')

        //Remove hash of tags
        text = text.replaceAll(/#\w+/, '_TAG_')

        //Remove emoticons neutral
        //text = text.replaceAll(/[;:8](-)?[(P)}{D]/, '')

        //Double space to space
        text = text.replaceAll(/\s\s/, ' ')

        //Remove links/urls
        text = text.replaceAll(/(via )?http:\/\/[a-zA-Z0-9\/-=.:]+/, '_LINK_')

        //Remove retweets
        text = text.replaceAll(/^?RT\s?:/, '_RETWEET_')

        //Remove invalid characters
        text = StringProcessor.removeInvalidUtf8Chars(text)

        text = text.trim()

        return text
    }
}
