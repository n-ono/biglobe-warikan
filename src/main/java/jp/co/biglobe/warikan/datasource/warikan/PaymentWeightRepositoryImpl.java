package jp.co.biglobe.warikan.datasource.warikan;

import jp.co.biglobe.warikan.domain.warikan.PaymentWeight;
import jp.co.biglobe.warikan.domain.warikan.PaymentWeightRepository;
import jp.co.biglobe.warikan.domain.warikan.PaymentWeights;
import jp.co.biglobe.warikan.domain.warikan.WarikanDrinkingPartyId;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;

@Repository
public class PaymentWeightRepositoryImpl implements PaymentWeightRepository {
    private final String FILE_NAME = "payment_weights.csv";
    private final String SEPARATOR = ",";
    private final int DEFAULT_HIGH_PAYMENT_WEIGHT = 12;
    private final int DEFAULT_MIDDLE_PAYMENT_WEIGHT = 10;
    private final int DEFAULT_LOW_PAYMENT_WEIGHT = 8;

    public PaymentWeights findById(WarikanDrinkingPartyId partyId) {
        File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
        try (
                ReversedLinesFileReader fr = new ReversedLinesFileReader(
                        file,
                        Charset.defaultCharset())
        ) {
            String line;
            while ((line = fr.readLine()) != null) {
                String[] columns = line.split(SEPARATOR);
                if (columns[0].equals(partyId.getValue())) {
                    return new PaymentWeights(
                            Integer.parseInt(columns[1]),
                            Integer.parseInt(columns[2]),
                            Integer.parseInt(columns[3])
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ユーザが指定した飲み会の設定がない場合はデフォルト値を使用する
        return new PaymentWeights(
                DEFAULT_HIGH_PAYMENT_WEIGHT,
                DEFAULT_MIDDLE_PAYMENT_WEIGHT,
                DEFAULT_LOW_PAYMENT_WEIGHT
        );
    }

    public void change(
            WarikanDrinkingPartyId partyId,
            PaymentWeight highPaymentWeight,
            PaymentWeight middlePaymentWeight,
            PaymentWeight lowPaymentWeight
    ) {
        File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
        try (BufferedWriter bw = Files.newBufferedWriter(file.toPath())) {
            bw.append(String.format(
                    "%s,%d,%d,%d",
                    partyId.getValue(),
                    highPaymentWeight.getValue(),
                    middlePaymentWeight.getValue(),
                    lowPaymentWeight.getValue())
            );
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
