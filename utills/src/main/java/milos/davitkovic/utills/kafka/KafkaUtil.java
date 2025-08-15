package sapmarketing.docstore.core.util.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.SendResult;
import sapmarketing.docstore.core.configuration.annotation.UtilClass;
import sapmarketing.docstore.core.util.LoggingUtil;

import java.util.List;
import java.util.Optional;

/**
 * Utility class for Kafka operations
 */
@Slf4j
@UtilClass
public class KafkaUtil {

    @Value("${sbb.sap.marketing.docstore.kafka.enable.batch.producer:false}")
    private Boolean ENABLE_BATCH_KAFKA_PRODUCER;

    public boolean isEnableBatchKafkaProducer() {
        return BooleanUtils.isTrue(ENABLE_BATCH_KAFKA_PRODUCER);
    }

    public static <T> String getKafkaMessageTopic(final SendResult<String, T> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::topic)
                .orElse(StringUtils.EMPTY);
    }

    public static <T> String getKafkaMessagesTopic(final SendResult<String, List<T>> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::topic)
                .orElse(StringUtils.EMPTY);
    }


    public static <T> String getKafkaMessageTimestamp(final SendResult<String, T> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::timestamp)
                .map(LoggingUtil::getLogTimeStampLong)
                .orElse(StringUtils.EMPTY);
    }

    public static <T> String getKafkaMessagesTimestamp(final SendResult<String, List<T>> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::timestamp)
                .map(LoggingUtil::getLogTimeStampLong)
                .orElse(StringUtils.EMPTY);
    }


    public static <T> Long getKafkaMessageOffset(final SendResult<String, T> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::offset)
                .orElse(null);
    }

    public static <T> Long getKafkaMessagesOffset(final SendResult<String, List<T>> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::offset)
                .orElse(null);
    }


    public static <T> Integer getKafkaMessagePartition(final SendResult<String, T> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::partition)
                .orElse(null);
    }

    public static <T> Integer getKafkaMessagesPartition(final SendResult<String, List<T>> kafkaSendingResult) {
        return Optional.ofNullable(kafkaSendingResult)
                .map(SendResult::getRecordMetadata)
                .map(RecordMetadata::partition)
                .orElse(null);
    }
}
