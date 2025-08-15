package milos.davitkovic.javautil.utills.file;

import lombok.NonNull;

import java.io.File;

/**
 * Puppeteer Utils class for converting HTML to PDF.
 */
public interface PuppeteerUtils {

    /** Converts the given HTML file to a PDF file.
     *
     * @param inputHtml  the input HTML file
     * @param outputPdf  the output PDF file
     */
    void convertHtml2Pdf(@NonNull final File inputHtml, @NonNull final File outputPdf);

    /** Generates a PDF file from the given HTML file.
     *
     * @param inputHtml  the input HTML file
     * @return the PDF file
     */
    File getGeneratedPdfFromHtml(@NonNull final File inputHtml);
}
