package com.isc.npsd.kafa.messegeproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@RestController
public class MessageSenderController {
    @Autowired
    @Output(Source.OUTPUT)
    private MessageChannel messageChannel;

    @Autowired
    @Output(HassanSource.OUTPUT)
    private MessageChannel hassanChannel;

    @GetMapping("/messageMostafa")
    public void sendMessage() {
        sendMessage(messageChannel);

    }

    @GetMapping("/messageHassan")
    public void sendMessageHassan() {
        sendMessage(hassanChannel);

    }

    private void sendMessage(MessageChannel messageChannel) {
        String formattedDate = "1398-09-23";
        String formattedDateTime = formattedDate + "T" + LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        String s = String.valueOf(new Date().getTime());
        String transactionId = s.substring(s.length() - 4);
        messageChannel.send(MessageBuilder.withPayload("test")
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.TEXT_PLAIN)
                .setHeader("message_type", "\"ORDER\"").build());
    }


    private String generateMessage(String currentDate, String currentDateTime, String transactionID, String debtorAgent) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Document xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation= \"iip_pacs.002.001.07.xsd\">\n" +
                "    <pacs.008.001.01>\n" +
                "        <GrpHdr>\n" +
                "            <MsgId>MELI-CT-2010-09-28-" + transactionID + "</MsgId>\n" +
                "            <CreDtTm>" + currentDateTime + "</CreDtTm>\n" +
                "            <NbOfTxs>1</NbOfTxs>\n" +
                "            <TtlIntrBkSttlmAmt Ccy=\"IRR\">100</TtlIntrBkSttlmAmt>\n" +
                "        <IntrBkSttlmDt>" + currentDate + "</IntrBkSttlmDt>\n" +
                "        <SttlmInf>\n" +
                "            <SttlmMtd>CLRG</SttlmMtd>\n" +
                "        </SttlmInf>\n" +
                "    </GrpHdr>\n" +
                "    <CdtTrfTxInf>\n" +
                "        <PmtId>\n" +
                "            <InstrId>9587432156</InstrId>\n" +
                "            <EndToEndId>1234567890123456</EndToEndId>\n" +
                "            <TxId>MELI-CT-" + currentDate + "-" + transactionID + "</TxId>\n" +
                "        </PmtId>\n" +
                "        <PmtTpInf>\n" +
                "            <SvcLvl>\n" +
                "                <Cd>SEPA</Cd>\n" +
                "            </SvcLvl>\n" +
                "            <LclInstrm>\n" +
                "                <Prtry>INST</Prtry>\n" +
                "            </LclInstrm>\n" +
                "        </PmtTpInf>\n" +
                "        <IntrBkSttlmAmt Ccy=\"IRR\">100</IntrBkSttlmAmt>\n" +
                "    <AccptncDtTm>" + currentDateTime + "</AccptncDtTm>\n" +
                "    <ChrgBr>SLEV</ChrgBr>\n" +
                "    <Dbtr>\n" +
                "        <Nm>asghar</Nm>\n" +
                "    </Dbtr>\n" +
                "    <DbtrAcct>\n" +
                "        <Id>\n" +
                "            <IBAN>IR690170000000512687432001</IBAN>\n" +
                "        </Id>\n" +
                "    </DbtrAcct>\n" +
                "    <DbtrAgt>\n" +
                "        <FinInstnId>\n" +
                "            <BIC>" + debtorAgent + "</BIC>\n" +
                "        </FinInstnId>\n" +
                "    </DbtrAgt>\n" +
                "    <CdtrAgt>\n" +
                "        <FinInstnId>\n" +
                "            <BIC>BSIRIRTHXXX</BIC>\n" +
                "        </FinInstnId>\n" +
                "    </CdtrAgt>\n" +
                "    <Cdtr>\n" +
                "        <Nm>akbar</Nm>\n" +
                "    </Cdtr>\n" +
                "    <CdtrAcct>\n" +
                "        <Id>\n" +
                "            <IBAN>IR160190000001853497200009</IBAN>\n" +
                "        </Id>\n" +
                "    </CdtrAcct>\n" +
                "      <Purp>\n" +
                "        <Cd>IPAY</Cd>\n" +
                "      </Purp>\n" +
                "</CdtTrfTxInf>\n" +
                "        </pacs.008.001.01></Document>";
    }

}
