package sapmarketing.docstore.core.util.sap;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.DocStoreConstants;
import sapmarketing.docstore.core.util.LoggingUtil;
import sapmarketing.docstore.core.util.file.Base64Util;
import sapmarketing.docstore.core.util.html.HtmlUtil;

@Slf4j
@UtilClass
public class DefaultSapUtils implements SapUtils {

    @Autowired
    private HtmlUtil htmlUtil;

    /**
     * Get the HTML for the given document
     *
     * @param xHtml the XHTML to convert
     * @return the HTML
     */
    @Override
    public String getSapOutboundId(@NonNull final String xHtml) {
        try {
            final String sapOutboundIdStart = getSapOutboudIdStart(xHtml);

            if(StringUtils.isEmpty(sapOutboundIdStart)) {
                log.error("[Sap Marketing DocStore] [{}] SAP outbound ID couldn't be extracted from xHtml, received from ABAP.",
                        LoggingUtil.getLogTimeStamp());
                return StringUtils.EMPTY;
            }

            final String sapOutboundId = StringUtils.substring(sapOutboundIdStart, 0, DocStoreConstants.SAP_OUTBOUND_ID_NUMBER_OF_CHARS);

            if (StringUtils.length(sapOutboundId) == DocStoreConstants.SAP_OUTBOUND_ID_NUMBER_OF_CHARS && sapOutboundId.matches(DocStoreConstants.SAP_OUTBOUND_ID_REGEX)) {
                log.debug("[Sap Marketing DocStore] [{}] SAP outbound ID is valid and has been extracted: {}",
                        LoggingUtil.getLogTimeStamp(), sapOutboundId);
                return sapOutboundId;
            }

            log.error("[Sap Marketing DocStore] [{}] SAP outbound ID [{}] has no standard {} characters length or/and contains invalid characters.",
                    LoggingUtil.getLogTimeStamp(), sapOutboundId, DocStoreConstants.SAP_OUTBOUND_ID_NUMBER_OF_CHARS);
            return sapOutboundId;

        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] Error while extracting SAP outbound ID from JSON string",
                    LoggingUtil.getLogTimeStamp(), e);
        }

        log.error("[Sap Marketing DocStore] [{}] SAP outbound ID couldn't be extracted from XHtml.",
                LoggingUtil.getLogTimeStamp());
        return StringUtils.EMPTY;
    }

    private String getSapOutboudIdStart(@NonNull final String input) {
        final String xHtml = Base64Util.decodeHtmlBase64(input);
        final String substringAfterRawXhtml = StringUtils.substringAfter(xHtml, DocStoreConstants.SAP_OUTBOUND_ID);
        if(StringUtils.isNotEmpty(substringAfterRawXhtml)) {
            log.debug("[Sap Marketing DocStore] [{}] SAP outbound ID has been extracted from raw XHtml: {}",
                    LoggingUtil.getLogTimeStamp(), substringAfterRawXhtml);
            return substringAfterRawXhtml;
        }

        final String cleanHtml = htmlUtil.getCleanHtml(xHtml);
        final String substringAfterCleanXhtml = StringUtils.substringAfter(cleanHtml, DocStoreConstants.SAP_OUTBOUND_ID);
        if(StringUtils.isNotEmpty(substringAfterCleanXhtml)) {
            log.debug("[Sap Marketing DocStore] [{}] SAP outbound ID start has been extracted from clean XHtml and has a prefix {}",
                    LoggingUtil.getLogTimeStamp(), DocStoreConstants.SAP_OUTBOUND_ID);
            return substringAfterCleanXhtml;
        }

        final String substringAfterSapCuanLink = StringUtils.substringAfter(cleanHtml, DocStoreConstants.SAP_PUBLIC_CUAN_LINK);
        if(StringUtils.isNotEmpty(substringAfterSapCuanLink)) {
            log.debug("[Sap Marketing DocStore] [{}] SAP outbound ID start has been extracted from clean XHtml and has a prefix {}",
                    LoggingUtil.getLogTimeStamp(), DocStoreConstants.SAP_PUBLIC_CUAN_LINK);
            return substringAfterSapCuanLink;
        }

        log.error("[Sap Marketing DocStore] [{}] SAP outbound ID is empty. Can't extract SAP outbound ID prefix from xHtml, received from ABAP.",
                LoggingUtil.getLogTimeStamp());
        return StringUtils.EMPTY;
    }
}
