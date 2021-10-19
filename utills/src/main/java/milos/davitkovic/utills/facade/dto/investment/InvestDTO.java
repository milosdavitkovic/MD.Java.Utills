package milos.davitkovic.utills.facade.dto.investment;

import lombok.Data;

@Data
public class InvestDTO {

    private boolean buyIt;
    private boolean considerIt;

    private double netOperatingIncomeValue;
    private String netOperatingIncome;
    private String netOperatingIncomeDescription = "Neto prihod od nekretnine trebalo bi da bude uvek veći od iznosa rate koji izdvajate za otplaćivanje kredita banci.";

    private double thresholdValue;
    private String threshold;
    private String thresholdDescription = "Granične vrednosti nekretnine: Ova formula može Vam pomoći da koliko-toliko imate okvir realne tržišne vrednosti i isplativosti nekretnine u datom trenutku.";

    private double cashFlowValue;
    private String cashFlow;
    private String cashFlowDescription = "Nakon primene ove formule, trebalo bi da dobijete pozitivnu vrednost (+)";
    private String cashFlowRule = "> 0%";

    private double cashOnCashReturnValue;
    private String cashOnCashReturn;
    private String cashOnCashReturnDescription = "Brzina povraćaja uloženog učešća: Kao što znate, u Srbiji važi pravilo 20% učešća prilikom kupovine nekretnine i maksimalnih 80% vrednosti nekretnine koje možete dobiti od banke u vidu stambenog kredita.";
    private String cashOnCashReturnRule = "≥ 10%";

    private double capitalizationRateValue;
    private String capitalizationRate;
    private String capitalizationRateDescription = "Stopa kapitalizacije nepokretnosti.";
    private String capitalizationRateRule = "≥ 8%";

    private double investmentReturnEstimationValue;
    private String investmentReturnEstimation;
    private String investmentReturnEstimationDescription = "Isplativost investicije u godinama.";
    private String investmentReturnEstimationRule = "<= 8";

    private boolean cashFlowPositive;
    private boolean cashOnCashReturnPositive;
    private boolean capitalizationRatePositive;
    private boolean investmentReturnEstimationPositive;
}
