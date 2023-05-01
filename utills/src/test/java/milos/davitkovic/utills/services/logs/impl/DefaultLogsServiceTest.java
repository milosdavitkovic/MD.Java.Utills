package milos.davitkovic.utills.services.logs.impl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DefaultLogsServiceTest
{
	private final String logMessages = "{\"instant\":{\"epochSecond\":1682932816,\"nanoOfSecond\":781000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[DLC] Call Dealer Locator endpoint dlcSearchDealerById with parameters {fields=[*], id=[GS0004708], localeLanguage=[false]} in order to get Vendor information.\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932816,\"nanoOfSecond\":782000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Request: GET 'https://int.api.oneweb.mercedes-benz.com/dlc-dms/v2/dealers/search/byId?fields=*&id=GS0004708&localeLanguage=false'\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":16000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Response: 200 OK 'OK' for URI https://int.api.oneweb.mercedes-benz.com/dlc-dms/v2/dealers/search/byId?fields=*&id=GS0004708&localeLanguage=false\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":23000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[DLC] Call Dealer Locator endpoint dlcSearchDealerById with parameters {fields=[*], id=[GS0012512], localeLanguage=[false]} in order to get Vendor information.\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":23000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Request: GET 'https://int.api.oneweb.mercedes-benz.com/dlc-dms/v2/dealers/search/byId?fields=*&id=GS0012512&localeLanguage=false'\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":256000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Response: 200 OK 'OK' for URI https://int.api.oneweb.mercedes-benz.com/dlc-dms/v2/dealers/search/byId?fields=*&id=GS0012512&localeLanguage=false\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":257000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[DLC] Call Dealer Locator endpoint dlcSearchDealerById with parameters {fields=[*], id=[GS0012512], localeLanguage=[false]} in order to get Vendor information.\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":258000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Request: GET 'https://int.api.oneweb.mercedes-benz.com/dlc-dms/v2/dealers/search/byId?fields=*&id=GS0012512&localeLanguage=false'\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":475000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Response: 200 OK 'OK' for URI https://int.api.oneweb.mercedes-benz.com/dlc-dms/v2/dealers/search/byId?fields=*&id=GS0012512&localeLanguage=false\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":477000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[SSS-D] Get MarketplaceDocument for the Point of Services [GS0004708] from partial order 02687725GC0003327\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":477000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Correlationid : [685bb69c-5440-4f2e-bffc-009ee28a011d] , TrackingId : [4516d586-f74d-4534-85db-6af24eb5bc5b]\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":477000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[SSS-D] Call SSS-D Service to get MarketplaceDocument. Request DcpRequestBuilder [POST 'https://int.api.oneweb.mercedes-benz.com/sssd/v3/api/marketplace/countries/{country}/documents' (uriVariables={country=TR})] for Point of Services [GS0004708], Language tr, Country isoCode TR, Product Types [NCOS_D_STANDARD], Business Model B2C, Partial Order 02687725GC0003327\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":478000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Retry POST request towards URL /sssd/v3/api/marketplace/countries/TR/documents. Retry template is null.\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":478000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Retry POST request towards URL /sssd/v3/api/marketplace/countries/TR/documents\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932817,\"nanoOfSecond\":478000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Request: POST 'https://int.api.oneweb.mercedes-benz.com/sssd/v3/api/marketplace/countries/TR/documents'\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":292000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"WARN\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Invalid cookie header: \\\"Set-Cookie: AWSALB=QKFG7INqRcsg5BlWomqmjjZJUI+4ZS1AaE+5IsbZApkLmTAAAn6t/IGB97oqDCxOQ1859wLgLj0LRxQgMwb5+1wdZRjVrhf/3dkXYWtb9/qCL0IUcBJrvcCXN9RO; Expires=Mon, 08 May 2023 09:20:17 GMT; Path=/\\\". Invalid 'expires' attribute: Mon, 08 May 2023 09:20:17 GMT\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":293000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"WARN\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Invalid cookie header: \\\"Set-Cookie: AWSALBCORS=QKFG7INqRcsg5BlWomqmjjZJUI+4ZS1AaE+5IsbZApkLmTAAAn6t/IGB97oqDCxOQ1859wLgLj0LRxQgMwb5+1wdZRjVrhf/3dkXYWtb9/qCL0IUcBJrvcCXN9RO; Expires=Mon, 08 May 2023 09:20:17 GMT; Path=/; SameSite=None; Secure\\\". Invalid 'expires' attribute: Mon, 08 May 2023 09:20:17 GMT\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":293000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Response: 200 OK 'OK' for URI https://int.api.oneweb.mercedes-benz.com/sssd/v3/api/marketplace/countries/TR/documents\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":293000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[SSS-D] MarketplaceDocument class MarketplaceDocuments {\\n    documents: [class MarketplaceDocument {\\n        metadata: class MarketplaceDocumentMetadata {\\n            documentType: TERMS_AND_CONDITIONS\\n            productTypes: [NCOS_D_STANDARD]\\n            context: ALL\\n        }\\n        companyId: GC0003327\\n        outletId: GS0004708\\n        supplierId: null\\n        title: Araç Rezervasyon Sözleşmesi\\n        fileName: Araç Rezervasyon Sözleşmesi.pdf\\n        mimeType: application/pdf\\n        uri: https://int.api.oneweb.mercedes-benz.com/sssd/v3/api/documents/de738206-6a5c-4d7b-9bb1-216e4222de0a\\n        size: 263589\\n    }]\\n    continueToken: null\\n} found for Point of Services [GS0004708], Language tr, Country isoCode TR, Product Types [NCOS_D_STANDARD], Business Model B2C, Partial Order 02687725GC0003327\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":293000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[SSS-D] PartialOrder 02687725GC0003327 has 1 MarketplaceDocuments.\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":293000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"[SSS-D] Order 02687725 has 1 MarketplaceDocuments.\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":511000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Request: POST 'https://int.api.oneweb.mercedes-benz.com/coms-ms/v1/messages/attachments'\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":891000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Response: 200 OK 'OK' for URI https://int.api.oneweb.mercedes-benz.com/coms-ms/v1/messages/attachments\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":897000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"O2O Email from market TR: {\\n  \\\"market\\\" : \\\"TR\\\",\\n  \\\"origin\\\" : {\\n    \\\"id\\\" : \\\"o2o-eda-adapter\\\",\\n    \\\"address\\\" : \\\"emredmrl@gmail.com\\\",\\n    \\\"descriptor_type\\\" : \\\"DESCRIPTOR_APPLICATION\\\"\\n  },\\n  \\\"destinations\\\" : [ {\\n    \\\"from\\\" : \\\"no-reply-dscs@oneweb.mercedes-benz.com\\\",\\n    \\\"to\\\" : [ {\\n      \\\"id\\\" : null,\\n      \\\"address\\\" : \\\"emredmrl@gmail.com\\\",\\n      \\\"descriptor_type\\\" : \\\"DESCRIPTOR_EMAIL\\\"\\n    } ],\\n    \\\"cc\\\" : [ {\\n      \\\"id\\\" : null,\\n      \\\"address\\\" : \\\"online-store@mercedes.com.tr\\\",\\n      \\\"descriptor_type\\\" : \\\"DESCRIPTOR_EMAIL\\\"\\n    } ],\\n    \\\"bcc\\\" : [ {\\n      \\\"id\\\" : null,\\n      \\\"address\\\" : \\\"rezervasyon_duzce@hasmer.com.tr\\\",\\n      \\\"descriptor_type\\\" : \\\"DESCRIPTOR_EMAIL\\\"\\n    } ],\\n    \\\"content\\\" : {\\n      \\\"id\\\" : \\\"02990eb4-deab-4944-9f80-42244625f1fa\\\",\\n      \\\"contentVersion\\\" : null,\\n      \\\"configurationVersion\\\" : null,\\n      \\\"data\\\" : {\\n        \\\"orderId\\\" : \\\"02687725\\\",\\n        \\\"orderDate\\\" : \\\"01/05/2023\\\",\\n        \\\"total\\\" : \\\"₺ 10\\\",\\n        \\\"subTotal\\\" : \\\"₺ 10\\\",\\n        \\\"items\\\" : 1,\\n        \\\"serviceContractId\\\" : null,\\n        \\\"serviceContractCart\\\" : false,\\n        \\\"productList\\\" : [ ],\\n        \\\"complementaryProducts\\\" : [ ],\\n        \\\"vehicle\\\" : {\\n          \\\"name\\\" : \\\"A 200 Sedan AMG+\\\",\\n          \\\"productCode\\\" : \\\"V177_0158160167_TR_308841\\\",\\n          \\\"reservationFee\\\" : \\\"₺ 10\\\",\\n          \\\"listPrice\\\" : null,\\n          \\\"frequentPayment\\\" : null,\\n          \\\"position\\\" : \\\"0\\\",\\n          \\\"description\\\" : null,\\n          \\\"quantity\\\" : 1,\\n          \\\"stock\\\" : {\\n            \\\"estimatedArrivalDate\\\" : null,\\n            \\\"stockType\\\" : \\\"IN_STOCK\\\"\\n          },\\n          \\\"data\\\" : null,\\n          \\\"image\\\" : \\\"https://assets.oneweb.mercedes-benz.com/iris/iris.png?COSY-EU-100-1713d0VXqNEFqtyO67PobzIr3eWsrrCsdRRzwQZYZ4ZbMw3SGtxX2tsd1ZpcUfwMuXGEuGXJ0l30fOB2Ng1bApjkXI5uVmIQC3qhTkzNRtum7jxOZhKV1bW%25vqwyayLRZY2YaxF4xrH1dHdn8wfT2oiZEkpM4FaIlTg9HgT6PD87mSeWiaItsd4J3cUfgO0XGEPndJ0le6xOB2KrqbApvnpI5uLmIQC3ahpkzNHmwm7j8h1hKVk0Q%25vqmIlyLRhYmYaxU5drH1Gm%25n8w0XwoiZKbYM4FvmQTg9LYV6PDZ%25pSeW0yhtsdB%25ycJtTjqNuMYaxBblrH1LM1n8waTboiZ3bXM4FNRPTg9jfs6PDekbSeWseutsdUvTcUfGL0XGE0nYJ0lBDVOB2AWcbA4wHEcmqN1Iw4jiCp5pnIu2fzzjFm93Su9Q6DF1s1n2nvligKfLlCVz9u2FUI0m3jBZj8nW&&BKGND=9&IMGT=P27&POV=BE030\\\",\\n          \\\"specialOfferPrice\\\" : \\\"₺ 1.276.372\\\",\\n          \\\"additionalPriceInfos\\\" : {\\n            \\\"ecobonus\\\" : null,\\n            \\\"priceBreakDown\\\" : [ {\\n              \\\"label\\\" : \\\"Araç Liste Fiyatı (Vergiler Dahil)\\\",\\n              \\\"displayValue\\\" : \\\"₺ 1.277.372\\\"\\n            }, {\\n              \\\"label\\\" : \\\"İndirim\\\",\\n              \\\"displayValue\\\" : \\\"-₺1.000\\\"\\n            }, {\\n              \\\"label\\\" : \\\"Anahtar Teslim Fiyatı\\\",\\n              \\\"displayValue\\\" : \\\"₺ 1.276.372\\\"\\n            }, {\\n              \\\"label\\\" : \\\"Araç Liste Fiyatı (Vergiler Hariç)\\\",\\n              \\\"displayValue\\\" : \\\"₺ 598.857\\\"\\n            }, {\\n              \\\"label\\\" : \\\"ÖTV (%\u202A80)\\\",\\n              \\\"displayValue\\\" : \\\"₺ 479.086\\\"\\n            }, {\\n              \\\"label\\\" : \\\"KDV (%\u202A18\u202A)\\\",\\n              \\\"displayValue\\\" : \\\"₺ 194.030\\\"\\n            }, {\\n              \\\"label\\\" : \\\"Tescil Masrafları\\\",\\n              \\\"displayValue\\\" : \\\"₺ 4.400\\\"\\n            } ],\\n            \\\"footnotes\\\" : null\\n          },\\n          \\\"wltp\\\" : {\\n            \\\"values\\\" : [ {\\n              \\\"label\\\" : \\\"Karma Yakıt Tüketimi\\\",\\n              \\\"displayImage\\\" : \\\"\\\",\\n              \\\"displayValue\\\" : \\\"6,30 l/100km\\\"\\n            }, {\\n              \\\"label\\\" : \\\"Karma CO₂ Emisyonu\\\",\\n              \\\"displayImage\\\" : \\\"\\\",\\n              \\\"displayValue\\\" : \\\"144 gCO₂/km\\\"\\n            } ],\\n            \\\"footnotes\\\" : \\\"tbd\\\",\\n            \\\"footnotenumber\\\" : \\\"[2]\\\",\\n            \\\"energyefficiency\\\" : null\\n          },\\n          \\\"nefz\\\" : null,\\n          \\\"pickupLocation\\\" : {\\n            \\\"name\\\" : \\\"Hasmer Otomotiv Yatırım ve Pazarlama A.Ş.\\\",\\n            \\\"addressLine\\\" : \\\"Ufuk Mah., 106.Sok., No:64, Üçköprü, Kaynaşlı\\\",\\n            \\\"postCode\\\" : \\\"81902\\\",\\n            \\\"city\\\" : \\\"Düzce\\\",\\n            \\\"email\\\" : \\\"rezervasyon_duzce@hasmer.com.tr\\\"\\n          },\\n          \\\"commissionNumber\\\" : \\\"0158160167\\\",\\n          \\\"vin\\\" : null,\\n          \\\"ucNumber\\\" : null,\\n          \\\"firstRegistrationDate\\\" : null\\n        },\\n        \\\"customer\\\" : {\\n          \\\"firstName\\\" : \\\"Emre\\\",\\n          \\\"middleName\\\" : null,\\n          \\\"title\\\" : \\\"\\\",\\n          \\\"lastName\\\" : \\\"Demirel\\\",\\n          \\\"salutation\\\" : \\\"Sayın\\\",\\n          \\\"salutationType\\\" : \\\"slt_neutral\\\",\\n          \\\"street\\\" : \\\"441/1\\\",\\n          \\\"houseNumber\\\" : null,\\n          \\\"postCode\\\" : null,\\n          \\\"city\\\" : \\\"Merkez\\\",\\n          \\\"addressAdditionalSuburb\\\" : \\\"\\\",\\n          \\\"phone\\\" : \\\"+905322764362\\\",\\n          \\\"email\\\" : \\\"emredmrl@gmail.com\\\",\\n          \\\"country\\\" : \\\"Turkey\\\",\\n          \\\"customerType\\\" : \\\"Bireysel Müşteri\\\",\\n          \\\"addressLine1\\\" : null,\\n          \\\"addressLine2\\\" : null,\\n          \\\"nationalIdNumber\\\" : null,\\n          \\\"business\\\" : {\\n            \\\"name\\\" : null,\\n            \\\"legalForm\\\" : null,\\n            \\\"vatID\\\" : \\\"11111111111\\\",\\n            \\\"streetLine\\\" : \\\"441/1 null\\\",\\n            \\\"cityPostcodeLine\\\" : \\\"null Merkez\\\",\\n            \\\"email\\\" : \\\"emredmrl@gmail.com\\\",\\n            \\\"phone\\\" : \\\"+905322764362\\\",\\n            \\\"country\\\" : \\\"Turkey\\\",\\n            \\\"establishment\\\" : {\\n              \\\"date\\\" : null\\n            },\\n            \\\"businessSalutation\\\" : null\\n          },\\n          \\\"contact\\\" : null,\\n          \\\"purchaseCooperative\\\" : null,\\n          \\\"state\\\" : \\\"Bilecik\\\"\\n        },\\n        \\\"tradeIn\\\" : {\\n          \\\"appraisalValue\\\" : null,\\n          \\\"model\\\" : null,\\n          \\\"requested\\\" : null\\n        },\\n        \\\"payment\\\" : {\\n          \\\"method\\\" : \\\"MasterCard\\\",\\n          \\\"methodType\\\" : null,\\n          \\\"financing\\\" : {\\n            \\\"option\\\" : null,\\n            \\\"dspLink\\\" : \\\"https://shop-uat.mercedes-benz-finansalhizmetler.com/insurance?proposalId=82e88b46-733d-4412-816d-2253f47c6879\\\",\\n            \\\"type\\\" : \\\"Undefined\\\",\\n            \\\"typeLabel\\\" : \\\"\\\"\\n          },\\n          \\\"sepa\\\" : null\\n        },\\n        \\\"delivery\\\" : {\\n          \\\"method\\\" : \\\"Yetkili Bayiden Teslim\\\",\\n          \\\"methodType\\\" : \\\"pickup\\\",\\n          \\\"cost\\\" : \\\"₺ 0\\\",\\n          \\\"description\\\" : \\\"Otomobili satın almak için en geç iki iş günü içerisinde bayiniz ile iletişime geçerek satış sözleşmesini imzalayınız. Otomobilinizin teslimatı ilgili yetkili bayide yapılacaktır.\\\"\\n        },\\n        \\\"dealer\\\" : {\\n          \\\"name1\\\" : \\\"Mercedes-Benz Otomotiv Ticaret ve Hizmetler A.Ş.\\\",\\n          \\\"addressLine1\\\" : \\\"Vadistanbul Bulvar Ayazağa Mah. Azerbaycan Cd. 1C Blok No: 3D\\\",\\n          \\\"cityPostcodeLine\\\" : \\\"  Istanbul\\\",\\n          \\\"postCode\\\" : null,\\n          \\\"city\\\" : \\\" Istanbul\\\",\\n          \\\"email\\\" : \\\"online-store@mercedes.com.tr\\\",\\n          \\\"country\\\" : \\\"Turkey\\\",\\n          \\\"supplierId\\\" : \\\"\\\"\\n        },\\n        \\\"metadata\\\" : {\\n          \\\"processType\\\" : \\\"b2c\\\"\\n        },\\n        \\\"deliveryAddress\\\" : {\\n          \\\"salutation\\\" : null,\\n          \\\"title\\\" : null,\\n          \\\"firstName\\\" : \\\"Hasmer Otomotiv Yatırım ve Pazarlama A.Ş.\\\",\\n          \\\"lastName\\\" : \\\"\\\",\\n          \\\"street\\\" : \\\"Ufuk Mah., 106.Sok., No:64, Üçköprü, Kaynaşlı\\\",\\n          \\\"houseNumber\\\" : \\\"\\\",\\n          \\\"postcode\\\" : \\\"81902\\\",\\n          \\\"city\\\" : \\\"Düzce\\\",\\n          \\\"email\\\" : \\\"rezervasyon_duzce@hasmer.com.tr\\\",\\n          \\\"phone\\\" : \\\"\\\",\\n          \\\"country\\\" : null,\\n          \\\"state\\\" : null\\n        },\\n        \\\"customerComment\\\" : null,\\n        \\\"wallboxInstallationAgreement\\\" : false,\\n        \\\"vin\\\" : null,\\n        \\\"partialOrderId\\\" : \\\"02687725GC0003327\\\",\\n        \\\"isSupplierOrder\\\" : true,\\n        \\\"o2oMailSubjectLine\\\" : \\\"dcp.messaging.order.subject.line.accessory\\\",\\n        \\\"wheelSet\\\" : {\\n          \\\"headline\\\" : null,\\n          \\\"subHeadline\\\" : null,\\n          \\\"imageUrl\\\" : null,\\n          \\\"wheelSizes\\\" : null,\\n          \\\"wheelSetPrice\\\" : null,\\n          \\\"options\\\" : null\\n        },\\n        \\\"chargedLater\\\" : \\\"₺ 1.276.372\\\"\\n      },\\n      \\\"content_type\\\" : \\\"EMAIL_CONTENT_TEMPLATE\\\"\\n    },\\n    \\\"attachments\\\" : [ \\\"5cdffe6a-120c-4ab3-8152-d4a5aaf59424\\\" ],\\n    \\\"locale\\\" : \\\"tr\\\",\\n    \\\"destination_type\\\" : \\\"DESTINATION_EMAIL\\\",\\n    \\\"transactionMetadata\\\" : {\\n      \\\"businessArea\\\" : \\\"CARS\\\",\\n      \\\"shop\\\" : \\\"EMH\\\",\\n      \\\"assortment\\\" : \\\"vehicle\\\",\\n      \\\"orderId\\\" : \\\"02687725\\\"\\n    }\\n  } ]\\n}\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932818,\"nanoOfSecond\":899000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Request: POST 'https://int.api.oneweb.mercedes-benz.com/coms-ms/v1/messages'\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932819,\"nanoOfSecond\":6000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Response: 201 CREATED 'Created' for URI https://int.api.oneweb.mercedes-benz.com/coms-ms/v1/messages\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}{\"instant\":{\"epochSecond\":1682932819,\"nanoOfSecond\":7000000},\"thread\":\"TaskExecutor-master-19713-ProcessTask [8930443297718]\",\"level\":\"INFO\",\"loggerName\":\"de.hybris.platform.util.logging.HybrisLogger\",\"message\":\"Process: dcp-order-process-02687725-1682924032823 in step class com.daimler.dcp.fulfilmentprocess.actions.order.SendOrderPlacedNotificationAction\",\"endOfBatch\":false,\"loggerFqcn\":\"de.hybris.platform.util.logging.HybrisLogger\",\"contextMap\":{\"Tenant\":\"\"},\"threadId\":19713,\"threadPriority\":5}";

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void createClearLogsFile()
	{
	}

	@Test
	public void getO2OEmailPayload()
	{
	}
}