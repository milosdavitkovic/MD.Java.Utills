package milos.davitkovic.javautil.utills.sap;

import lombok.NonNull;

public interface SapUtils {

    /**
     * Get the HTML for the given document
     *
     * @param xHtml the XHTML to convert
     * @return the HTML
     */
    String getSapOutboundId(@NonNull final String xHtml);
}
