# LJImageReverter
Small utility for LiveJournal written using Java 8 and Java FX. 
It reverts order of &lt;img> tags generated by LJ editor, fixes unclosed tags and put blank lines between them

This is the operation I do a lot. 
The utility itself is super simple :)

Example of use:
1) Open LiveJournal editor in browser

2) Upload bunch of images

3) Change to HTML mode

4) Copy text containing list of <img> tags into clipboard

5) Open LJImageRevert

6) Click on big text field.


List of <img> will be taken from clipboard, order reversed, unclosed tags fixed and blank lines put between each image element.

7) Switch to browser again and past text from clipboard (Ctrl+V)
