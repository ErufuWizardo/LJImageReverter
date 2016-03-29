/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erufu.wizardo.ljimagerevert.util;

import erufu.wizardo.ljimagerevert.util.LinkListReverter;
import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Zorag
 */
public class LinkListRevertTest {

    private LinkListReverter linkListReverter;

    @Before
    public void setUp() {
        linkListReverter = new LinkListReverter();
    }

    @After
    public void tearDown() {
        linkListReverter = null;
    }

    @Test
    public void revertStringsTest() {
        final String sourceData = "<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_600.jpg\" alt=\"\" title=\"\"></a>\n"
                + "<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_600.jpg\" alt=\"\" title=\"\"></a>\n"
                + "<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_600.jpg\" alt=\"\" title=\"\"></a>";

        final String expectedResult = "\n\n<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_600.jpg\" alt=\"\" title=\"\"/></a>\n\n<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_600.jpg\" alt=\"\" title=\"\"/></a>\n\n<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_600.jpg\" alt=\"\" title=\"\"/></a>";

        final String actualResult = linkListReverter.revert(sourceData);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void revertRegexp() {
        final String sourceData = "<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_600.jpg\" alt=\"\" title=\"\"></a>\n"
                + "<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_600.jpg\" alt=\"\" title=\"\"></a>\n"
                + "<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_600.jpg\" alt=\"\" title=\"\"></a>";

        final String expectedResult = "\n\n<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/215040/215040_600.jpg\" alt=\"\" title=\"\"></a>\n\n<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214884/214884_600.jpg\" alt=\"\" title=\"\"></a>\n\n<a target=\"_blank\" href=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_original.jpg\"><img src=\"http://ic.pics.livejournal.com/zorag_ringael/7965041/214736/214736_600.jpg\" alt=\"\" title=\"\"></a>";

        final String actualResult = linkListReverter.revertRegexp(sourceData);

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void imgFixTest() {
        final String sourceData = "<img src=\"bull\"><iMG src=\"bull\"><imG src=\"bull\"> <Img src=\"ddddd\"> \n\nfffssseee\n   <IMg src=\"bull\"> <IMG src=\"bull\"> <IMG src=\"bull\">";
        final String expectedResult = "<img src=\"bull\"/><iMG src=\"bull\"/><imG src=\"bull\"/> <Img src=\"ddddd\"/> \n\nfffssseee\n   <IMg src=\"bull\"/> <IMG src=\"bull\"/> <IMG src=\"bull\"/>";

        final String actualResult = linkListReverter.fixImgTag(sourceData);
        Assert.assertEquals(expectedResult, actualResult);
    }
}
