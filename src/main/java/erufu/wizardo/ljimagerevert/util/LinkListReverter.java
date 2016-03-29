/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erufu.wizardo.ljimagerevert.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkListReverter {

    public String revert(final String sourceText) {
        final String reverted = revertRegexp(sourceText);
        final String fixedImgTags = fixImgTag(reverted);
        return fixedImgTags;
    }

    String revertRegexp(final String sourceText) {
        final StringBuffer result = new StringBuffer();
        final Pattern pattern = Pattern.compile("<a .*?</a>", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(sourceText);

        while (matcher.find()) {
            final String group = matcher.group();
            result.insert(0, group);
            result.insert(0, "\n\n");
        }

        return result.toString();
    }

    String fixImgTag(String sourceText) {
        final Pattern pattern = Pattern.compile("(<img.*?)>", Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(sourceText);
        final String result = matcher.replaceAll("$1/>");
        return result;
    }
}
