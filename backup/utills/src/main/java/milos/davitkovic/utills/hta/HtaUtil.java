package sapmarketing.docstore.core.util.hta;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.configuration.properties.DocStoreProperties;
import sapmarketing.docstore.core.util.LoggingUtil;
import sapmarketing.docstore.facade.dto.htaplus.KafkaPdfDTO;
import sapmarketing.docstore.facade.dto.htaplus.KafkaPdfDocDataDTO;
import sapmarketing.docstore.facade.dto.htaplus.pdfSend.BatchPdfSendDTO;
import sapmarketing.docstore.facade.dto.htaplus.pdfSend.PdfComDataDTO;
import sapmarketing.docstore.facade.dto.htaplus.pdfSend.PdfDocDataDTO;
import sapmarketing.docstore.facade.dto.htaplus.pdfSend.PdfSendDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@UtilClass
public class HtaUtil {

    private static final int DEFAULT_BATCH_SIZE = 10000;

    @Value("${sbb.sap.marketing.docstore.pdf.save.v3.batch.size}")
    private String V3_BATCH_SIZE;

    private static final String JOB_INPUT_LIST_SIZE_KEY = "jobInputListSize";
    private static final String JOB_INPUT_VALUE = "jobInputValue_%s";

    @Autowired
    private DocStoreProperties docStoreProperties;
    @Autowired
    private ObjectMapper objectMapper;

    public int getHtaPlusBatchSize() {
        if (StringUtils.isEmpty(V3_BATCH_SIZE)) {
            log.debug("[Sap Marketing DocStore] [{}] [v3] Batch size for HTA+ objects is not configured. Using a default value of 100.",
                    LoggingUtil.getLogTimeStamp());
            return DEFAULT_BATCH_SIZE;
        }

        return Math.abs(Integer.parseInt(V3_BATCH_SIZE));
    }

    public boolean isCreatePdf(@NonNull final PdfSendDTO pdfSendDTO) {
        return StringUtils.endsWithIgnoreCase(getStorage(pdfSendDTO), docStoreProperties.getStorageCreatePdf());
    }

    public boolean isCreatePdf(@NonNull final PdfDocDataDTO docData) {
        return StringUtils.endsWithIgnoreCase(docData.getStorage(), docStoreProperties.getStorageCreatePdf());
    }

    public boolean isAnyCreatePdf(final List<PdfSendDTO> pdfSendDTOs) {
        return CollectionUtils.emptyIfNull(pdfSendDTOs).stream()
                .anyMatch(this::isCreatePdf);
    }

    public boolean isAnyCreatePdf(@NonNull final Stream<PdfSendDTO> pdfSendDTOs) {
        return pdfSendDTOs
                .anyMatch(this::isCreatePdf);
    }

    public boolean isOnlyEmail(@NonNull final PdfSendDTO pdfSendDTO) {
        return StringUtils.endsWithIgnoreCase(getStorage(pdfSendDTO), docStoreProperties.getStorageOnlyEmail());
    }

    public boolean isOnlyEmail(@NonNull final PdfDocDataDTO docData) {
        return StringUtils.endsWithIgnoreCase(docData.getStorage(), docStoreProperties.getStorageOnlyEmail());
    }

    public String getStorage(final PdfSendDTO message) {
        return Optional.ofNullable(message)
                .map(PdfSendDTO::getDocData)
                .map(PdfDocDataDTO::getStorage)
                .orElse(StringUtils.EMPTY);
    }

    public String getStorage(final KafkaPdfDTO message) {
        return Optional.ofNullable(message)
                .map(KafkaPdfDTO::getDocData)
                .map(KafkaPdfDocDataDTO::getStorage)
                .orElse(StringUtils.EMPTY);
    }

    public String getSapOutboundId(final PdfSendDTO message) {
        return Optional.ofNullable(message)
                .map(PdfSendDTO::getComData)
                .map(PdfComDataDTO::getOutboundId)
                .orElse(StringUtils.EMPTY);
    }

    public String getSapOutboundId(final KafkaPdfDTO message) {
        return Optional.ofNullable(message)
                .map(KafkaPdfDTO::getComData)
                .map(PdfComDataDTO::getOutboundId)
                .orElse(StringUtils.EMPTY);
    }

    public List<PdfSendDTO> getBatchValue(final ChunkContext chunkContext) {
        if (chunkContext == null) {
            log.error("[Sap Marketing DocStore] [{}] [v3] ChunkContext or StepContext is null. Cannot read job input value for HTA+ objects.",
                    LoggingUtil.getLogTimeStamp());
            return Collections.emptyList();
        }

        try {
            return getPdfSendDTOs(chunkContext);
        } catch (JsonProcessingException e) {
            log.error("[Sap Marketing DocStore] [{}] [v3] Error while reading job input value for HTA+ objects: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage(), e);
            return Collections.emptyList();
        } catch (Exception e) {
            log.error("[Sap Marketing DocStore] [{}] [v3] Unexpected error while reading job input value for HTA+ objects: {}",
                    LoggingUtil.getLogTimeStamp(), e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private List<PdfSendDTO> getPdfSendDTOs(@NonNull final ChunkContext chunkContext) throws JsonProcessingException {
        final long jobInputListSize = getLongInputParameter(chunkContext, JOB_INPUT_LIST_SIZE_KEY);
        if (jobInputListSize == 0L) {
            log.debug("[Sap Marketing DocStore] [{}] [v3] Job input list size for HTA+ objects is 0. Please check the job parameters [{}]. Kafka Messages won't be sent.",
                    LoggingUtil.getLogTimeStamp(), JOB_INPUT_LIST_SIZE_KEY);
            return Collections.emptyList();
        }
        log.debug("[Sap Marketing DocStore] [{}] [v3] Job input list size for HTA+ objects is {}.",
                LoggingUtil.getLogTimeStamp(), jobInputListSize);

        final StringBuilder jobInputValue = new StringBuilder();
        for (int i = 0; i < jobInputListSize; i++) {
            final String jobInputValueParameter = String.format(JOB_INPUT_VALUE, i);
            final String inputParam = getStringInputParameter(chunkContext, jobInputValueParameter);
            log.debug("[Sap Marketing DocStore] [{}] [v3] Job input value for HTA+ objects parameter [{}] is [{}].",
                    LoggingUtil.getLogTimeStamp(), jobInputValueParameter, inputParam);
            jobInputValue.append(inputParam);
        }

        final String jobInputValueString = jobInputValue.toString();
        if (StringUtils.isEmpty(jobInputValueString)) {
            log.error("[Sap Marketing DocStore] [{}] [v3] Job input value for HTA+ objects is empty.",
                    LoggingUtil.getLogTimeStamp());
            return Collections.emptyList();
        }

        final BatchPdfSendDTO batchPdfSendDTO = objectMapper.readValue(jobInputValueString, BatchPdfSendDTO.class);
        final List<PdfSendDTO> messages = Optional.ofNullable(batchPdfSendDTO)
                .map(BatchPdfSendDTO::getPdfSendDTOs)
                .orElse(Collections.emptyList());
        if (CollectionUtils.isNotEmpty(messages)) {
            log.debug("[Sap Marketing DocStore] [{}] [v3] BatchPdfSendDTO has been read from the job input value. Number of messages: [{}].",
                    LoggingUtil.getLogTimeStamp(), CollectionUtils.size(messages));
            return messages;
        }

        final PdfSendDTO pdfSendDTO = objectMapper.readValue(jobInputValueString, PdfSendDTO.class);
        if (pdfSendDTO == null) {
            log.error("[Sap Marketing DocStore] [{}] [v3] No valid HTA+ objects found in the job input value. Kafka Messages won't be sent.",
                    LoggingUtil.getLogTimeStamp());
            return Collections.emptyList();
        }

        return Collections.singletonList(pdfSendDTO);
    }

    private static String getStringInputParameter(final ChunkContext chunkContext, final String jobInputValueParameter) {
        if (StringUtils.isEmpty(jobInputValueParameter)) {
            log.error("[Sap Marketing DocStore] [{}] [V3] Batch objects has not Input parameter. Input parameter is empty.",
                    LoggingUtil.getLogTimeStamp());
            return StringUtils.EMPTY;
        }

        final Optional<String> optionalInputParameter = Optional.of(chunkContext)
                .map(ChunkContext::getStepContext)
                .map(StepContext::getJobParameters)
                .map(jobParametersMap -> (String) jobParametersMap.get(jobInputValueParameter));

        if (optionalInputParameter.isEmpty()) {
            log.debug("[Sap Marketing DocStore] [{}] [V3] Batch objects has no Value for the input parameter Key: [{}].",
                    LoggingUtil.getLogTimeStamp(), jobInputValueParameter);
            return StringUtils.EMPTY;
        }

        return optionalInputParameter.get();
    }

    private static long getLongInputParameter(final ChunkContext chunkContext, final String inputStringParameter) {
        final Optional<Object> optionalInputParameter = Optional.of(chunkContext)
                .map(ChunkContext::getStepContext)
                .map(StepContext::getJobParameters)
                .map(jobParametersMap -> jobParametersMap.get(inputStringParameter));

        if (optionalInputParameter.isEmpty()) {
            log.debug("[Sap Marketing DocStore] [{}] [V3] Long Input parameter {} for HTA+ objects is empty.",
                    LoggingUtil.getLogTimeStamp(), inputStringParameter);
            return 0L;
        }

        return (Long) optionalInputParameter.get();
    }

    public void encodeHTML(@NonNull final PdfSendDTO pdfSendDTO) {
        final String xhtml = Optional.ofNullable(pdfSendDTO.getDocData()).
                map(PdfDocDataDTO::getHtml)
                .orElse(StringUtils.EMPTY);
        if (StringUtils.isEmpty(xhtml)) {
            return;
        }

//        final String decodeHtmlBase64 = htmlUtil.getCleanHtml(xhtml);
//        pdfSendDTO.getDocData().setHtml(decodeHtmlBase64);
        pdfSendDTO.getDocData().setHtml(null);
        log.debug("[Sap Marketing DocStore] [{}] [v3] [Temporary] HTML content for PDF Send DTO has been set to null. PDF file will be created from the EMAIL URL.",
                LoggingUtil.getLogTimeStamp());
    }

    public void encodeHTMLs(@NonNull final List<PdfSendDTO> pdfSendDTO) {
        pdfSendDTO.forEach(this::encodeHTML);
    }

    public String getKafkaMessageKey(final PdfSendDTO message) {
        final String sapOutboundId = Optional.ofNullable(message)
                .map(PdfSendDTO::getComData)
                .map(PdfComDataDTO::getOutboundId)
                .orElse(StringUtils.EMPTY);

        if (StringUtils.isNotEmpty(sapOutboundId)) {
            log.debug("[Sap Marketing DocStore] [{}] [v3] Kafka message key is Sap Outbound ID: [{}]",
                    LoggingUtil.getLogTimeStamp(), sapOutboundId);
            return sapOutboundId;
        }

        log.debug("[{}] [v3] Kafka message key is empty.",
                LoggingUtil.getLogTimeStamp());
        return StringUtils.EMPTY;
    }

    public String getKafkaMessageKey(final KafkaPdfDTO message) {
        final String sapOutboundId = Optional.ofNullable(message)
                .map(KafkaPdfDTO::getComData)
                .map(PdfComDataDTO::getOutboundId)
                .orElse(StringUtils.EMPTY);

        if (StringUtils.isNotEmpty(sapOutboundId)) {
            log.debug("[Sap Marketing DocStore] [{}] [v3] Kafka message key is Sap Outbound ID: [{}]",
                    LoggingUtil.getLogTimeStamp(), sapOutboundId);
            return sapOutboundId;
        }

        log.debug("[{}] [v3] Kafka message key is empty.",
                LoggingUtil.getLogTimeStamp());
        return StringUtils.EMPTY;
    }

    public String getKafkaMessageKey(@NonNull final List<PdfSendDTO> message) {
        final String firstSapOutboundId = getKafkaMessageKey(message.stream().findFirst().orElse(null));
        return firstSapOutboundId + "-" + org.apache.commons.collections.CollectionUtils.size(message);
    }

    public String getKafkaMessagesKey(@NonNull final List<KafkaPdfDTO> message) {
        final String firstSapOutboundId = getKafkaMessageKey(message.stream().findFirst().orElse(null));
        return firstSapOutboundId + "-" + org.apache.commons.collections.CollectionUtils.size(message);
    }

    public String getFirstObjectSapOutboundId(List<PdfSendDTO> htaMessages) {

        return CollectionUtils.emptyIfNull(htaMessages).stream().findFirst().map(message -> {
            PdfComDataDTO comData = message.getComData();
            if (comData != null && StringUtils.isNotBlank(comData.getOutboundId())) {
                log.debug("[Sap Marketing DocStore] [{}] [v3] PDF Send request received for Outbound ID: [{}].",
                        LoggingUtil.getLogTimeStamp(), comData.getOutboundId());
                return comData.getOutboundId();
            } else {
                log.debug("[Sap Marketing DocStore] [{}] [v3] PDF Send request received without Outbound ID.",
                        LoggingUtil.getLogTimeStamp());
                return StringUtils.EMPTY;
            }
        }).orElse(StringUtils.EMPTY);
    }
}
